package br.com.vinidg.rastreio.process;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.vinidg.rastreio.domain.Pedido;
import br.com.vinidg.rastreio.repositories.PedidoRepository;

public class ReaderImpl implements ItemReader<List<Pedido>>{

	private static final Logger log = LoggerFactory.getLogger(ReaderImpl.class);

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public List<Pedido> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		List<Pedido> pedidos = pedidoRepository.findByEntregue(false);
		log.info("Pedidos a serem atualizados: " + pedidos.size());
		return pedidos;
	}

}
