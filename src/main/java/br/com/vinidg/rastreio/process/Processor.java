package br.com.vinidg.rastreio.process;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.vinidg.rastreio.domain.Pedido;
import br.com.vinidg.rastreio.domain.dto.RastreioDTO;
import br.com.vinidg.rastreio.enums.Entrega;

@Service
public class Processor {
	
	private static final Logger log = LoggerFactory.getLogger(Processor.class);

	public List<Pedido> process(List<Pedido> pedidos) throws Exception {
		RastreioDTO novoRastreio = new RastreioDTO();
		
		for(Pedido pedido : pedidos) {
			RastreioDTO ultimoRastreio = new RastreioDTO();
			for(RastreioDTO rastreio : pedido.getRastreios()) {
				if(ultimoRastreio.getData() == null) {
					ultimoRastreio = rastreio;
				}
				if(rastreio.getData().isAfter(ultimoRastreio.getData())) {
					ultimoRastreio = rastreio;
				}
				
			}
			
			log.info(" | ID: "+ pedido.getId() + " | Rastreio de: " + ultimoRastreio.getEntrega().getDescricao());

			if(Entrega.PAGAMENTO_CONFIRMADO.equals(ultimoRastreio.getEntrega())) {
				
				novoRastreio.setEntrega(Entrega.PEDIDO_NA_DISTRIBUIDORA);
				novoRastreio.setData(LocalDateTime.now());
				
				pedido.getRastreios().add(novoRastreio);
				
				log.info(" | ID: "+ pedido.getId() + " | Rastreio para : " + novoRastreio.getEntrega().getDescricao());
				
			}else if(Entrega.PEDIDO_NA_DISTRIBUIDORA.equals(ultimoRastreio.getEntrega())) {
				
				novoRastreio.setEntrega(Entrega.PEDIDO_EM_ENTREGA);
				novoRastreio.setData(LocalDateTime.now());
				
				pedido.getRastreios().add(novoRastreio);
				
				log.info(" | ID: "+ pedido.getId() + " | Rastreio para : " + novoRastreio.getEntrega().getDescricao());
				
			}
			else if(Entrega.PEDIDO_EM_ENTREGA.equals(ultimoRastreio.getEntrega())) {
				
				novoRastreio.setEntrega(Entrega.PEDIDO_ENTREGUE);
				novoRastreio.setData(LocalDateTime.now());
				
				pedido.getRastreios().add(novoRastreio);
				pedido.setEntregue(true);
				
				log.info(" | ID: "+ pedido.getId() + " | Rastreio para : " + novoRastreio.getEntrega().getDescricao());
				
			}else {
				log.error("ERRO | ID: "+ pedido.getId());
				throw new Exception("Rastreio com erro");
			}
		}
		
		return pedidos;
	}
	
}
