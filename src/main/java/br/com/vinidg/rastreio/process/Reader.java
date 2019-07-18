package br.com.vinidg.rastreio.process;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vinidg.rastreio.domain.Pedido;
import br.com.vinidg.rastreio.repositories.PedidoRepository;

@Service
public class Reader {

	private static final Logger log = LoggerFactory.getLogger(Reader.class);

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> read() {
		List<Pedido> pedidos = pedidoRepository.findByEntregue(false);
		log.info("Pedidos a serem atualizados: " + pedidos.size());
		return pedidos;
	}

}
