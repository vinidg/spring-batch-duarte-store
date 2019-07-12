package br.com.vinidg.rastreio.config;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.vinidg.rastreio.domain.Pedido;
import br.com.vinidg.rastreio.process.ProcessorImpl;
import br.com.vinidg.rastreio.process.ReaderImpl;
import br.com.vinidg.rastreio.process.WriterImpl;

@Configuration
@Component
@EnableScheduling
public class BatchConfig {
	
	private static final Logger log = LoggerFactory.getLogger(BatchConfig.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Autowired
	private JobLauncher jobLauncher;

	@Scheduled(fixedDelay = 5000)
	public void processar() throws Exception {
		log.info(" Job Started at :" + new Date());
		JobParameters param = new JobParametersBuilder()
				.addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		
		JobExecution execution = jobLauncher.run(job(), param);
		log.info("Job finished with status :" + execution.getStatus());
	}

	@Bean
	public Job job() {
		return jobBuilderFactory
				.get("job")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory
				.get("step1")
				.<List<Pedido>, List<Pedido>>chunk(1)
				.reader(new ReaderImpl())
				.processor(new ProcessorImpl())
				.writer(new WriterImpl())
				.build();
	}
}
