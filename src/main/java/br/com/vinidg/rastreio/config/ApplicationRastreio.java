package br.com.vinidg.rastreio.config;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import br.com.vinidg.rastreio.domain.Pedido;
import br.com.vinidg.rastreio.process.Processor;
import br.com.vinidg.rastreio.process.Reader;
import br.com.vinidg.rastreio.process.SendNotification;
import br.com.vinidg.rastreio.process.Writer;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = { 
		"br.com.vinidg.rastreio.domain", 
		"br.com.vinidg.rastreio.config", 
		"br.com.vinidg.rastreio.process",
		"br.com.vinidg.rastreio.integrations"
		})
@EnableMongoRepositories("br.com.vinidg.rastreio.repositories")
public class ApplicationRastreio {
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationRastreio.class);
	
	@Autowired
	private Reader reader;
	
	@Autowired
	private Processor processor;
	
	@Autowired
	private Writer writer;
	
	@Autowired
	private SendNotification sendNotification;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApplicationRastreio.class, args);
	}


	@Scheduled(fixedDelay = 300000)
	public void run() throws Exception {
		log.info("Job Started at : " + LocalDateTime.now());
		
		try {
			List<Pedido> read = reader.read();
			List<Pedido> process = processor.process(read);
			List<Pedido> write = writer.write(process);
			sendNotification.send(write);
		} catch (Exception e) {
			log.error("Erro ao processar ", e);
		}
		
		log.info("Job finished at : " + LocalDateTime.now());
		
	}
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

	
}
