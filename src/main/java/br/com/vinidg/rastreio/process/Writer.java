package br.com.vinidg.rastreio.process;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vinidg.rastreio.domain.Pedido;
import br.com.vinidg.rastreio.repositories.PedidoRepository;

@Service
public class Writer {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> write(List<Pedido> items) {
		return pedidoRepository.saveAll(items);
	}

}
