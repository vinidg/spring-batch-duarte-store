package br.com.vinidg.rastreio.process;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vinidg.rastreio.domain.Pedido;
import br.com.vinidg.rastreio.domain.dto.RastreioDTO;
import br.com.vinidg.rastreio.enums.Entrega;
import br.com.vinidg.rastreio.enums.PushMessage;
import br.com.vinidg.rastreio.integrations.OneSignalIntegration;

@Service
public class SendNotification {
	
	@Autowired
	private OneSignalIntegration oneSignalIntegration;

	private static final Logger log = LoggerFactory.getLogger(OneSignalIntegration.class);
	
	List<String> playerIdsPedidoDistribuidora = new ArrayList<>();
	List<String> playerIdsPedidoEmEntrega = new ArrayList<>();
	List<String> playerIdsPedidoEntregue = new ArrayList<>();

	private void processLists(List<Pedido> pedidos) throws Exception {
		try {
			
			int errors = 0;
			int success = 0;
			
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
				if(Entrega.PEDIDO_NA_DISTRIBUIDORA.equals(ultimoRastreio.getEntrega())) {
					if(pedido.getCliente().getPlayerId() != "") {
						playerIdsPedidoDistribuidora.add(pedido.getCliente().getPlayerId());
						success++;
					}
				}
				else if(Entrega.PEDIDO_EM_ENTREGA.equals(ultimoRastreio.getEntrega())) {
					if(pedido.getCliente().getPlayerId() != "") {
						playerIdsPedidoEmEntrega.add(pedido.getCliente().getPlayerId());
						success++;
					}
				}else if(Entrega.PEDIDO_ENTREGUE.equals(ultimoRastreio.getEntrega())) {
					if(pedido.getCliente().getPlayerId() != "") {
						playerIdsPedidoEntregue.add(pedido.getCliente().getPlayerId());
						success++;
					}
				}else {
					log.error("+++++++++++++++++++++++++++++ Entrega não encontrada | id = " + pedido.getId());
					errors++;
				}
				
			}
			log.info("Envio de Push || playerIdsPedidoDistribuidora | TAMANHO = "+playerIdsPedidoDistribuidora.size());
			log.info("Envio de Push || playerIdsPedidoEmEntrega | TAMANHO = "+playerIdsPedidoEmEntrega.size());
			log.info("Envio de Push || playerIdsPedidoEntregue | TAMANHO = "+playerIdsPedidoEntregue.size());
			log.info("Envio de Push || Tamanho da lista de pedidos = "+pedidos.size()+" | Contagem de sucesso = "+success+" | Contagem de erros = "+ errors);
		
		} catch (Exception e) {
			log.error("Erro ao processar listar para envio", e);
			throw new Exception("Erro ao processar listar para envio",e);
		}
	}
	
	public void send(List<Pedido> pedidos) throws Exception {
		processLists(pedidos);
		
		if(playerIdsPedidoDistribuidora.size() > 0) {
			oneSignalIntegration.createNotification(PushMessage.PEDIDO_NA_DISTRIBUIDORA, playerIdsPedidoDistribuidora);
		}
		if(playerIdsPedidoEmEntrega.size() > 0) {
			oneSignalIntegration.createNotification(PushMessage.PEDIDO_EM_ENTREGA, playerIdsPedidoEmEntrega);
		}
		if(playerIdsPedidoEntregue.size() > 0) {
			oneSignalIntegration.createNotification(PushMessage.PEDIDO_ENTREGUE, playerIdsPedidoEntregue);
		}
		playerIdsPedidoDistribuidora.clear();
		playerIdsPedidoEmEntrega.clear();
		playerIdsPedidoEntregue.clear();
	}
}
