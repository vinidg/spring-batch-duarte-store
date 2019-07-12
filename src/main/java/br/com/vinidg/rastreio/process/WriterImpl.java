package br.com.vinidg.rastreio.process;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.vinidg.rastreio.domain.Pedido;
import br.com.vinidg.rastreio.repositories.PedidoRepository;

public class WriterImpl implements ItemWriter<List<Pedido>>{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public void write(List<? extends List<Pedido>> items) throws Exception {
		for(List<Pedido> pedidos : items) {
			pedidoRepository.saveAll(pedidos);
		}
	}

}
