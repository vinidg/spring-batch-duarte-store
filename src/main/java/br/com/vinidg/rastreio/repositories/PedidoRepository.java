package br.com.vinidg.rastreio.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.vinidg.rastreio.domain.Pedido;


@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String>{

	@Transactional(readOnly=true)
	List<Pedido> findByEntregue(Boolean entregue);
}
