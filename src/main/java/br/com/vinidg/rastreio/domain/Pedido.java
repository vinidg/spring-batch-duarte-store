package br.com.vinidg.rastreio.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.vinidg.rastreio.domain.dto.ItemPedidoDTO;
import br.com.vinidg.rastreio.domain.dto.RastreioDTO;

@Document
public class Pedido {

	@Id
	private String id;
	private LocalDateTime dataDaCompra;
	@DBRef
	private Cliente cliente;
	@DBRef
	private Endereco endereco; 
	private List<ItemPedidoDTO> itemPedidos = new ArrayList<ItemPedidoDTO>();
	private List<RastreioDTO> rastreios = new ArrayList<RastreioDTO>();
	private Boolean entregue;
	
	public Pedido() {
	}

	public Pedido(String id, LocalDateTime dataDaCompra, Cliente cliente, Endereco endereco,
			List<ItemPedidoDTO> itemPedidos, List<RastreioDTO> rastreios, Boolean entregue) {
		super();
		this.id = id;
		this.dataDaCompra = dataDaCompra;
		this.cliente = cliente;
		this.endereco = endereco;
		this.itemPedidos = itemPedidos;
		this.rastreios = rastreios;
		this.entregue = entregue;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getDataDaCompra() {
		return dataDaCompra;
	}
	public void setDataDaCompra(LocalDateTime dataDaCompra) {
		this.dataDaCompra = dataDaCompra;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<ItemPedidoDTO> getItemPedidos() {
		return itemPedidos;
	}
	public void setItemPedidos(List<ItemPedidoDTO> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}
	public List<RastreioDTO> getRastreios() {
		return rastreios;
	}
	public void setRastreios(List<RastreioDTO> rastreios) {
		this.rastreios = rastreios;
	}
	public Boolean getEntregue() {
		return entregue;
	}
	public void setEntregue(Boolean entregue) {
		this.entregue = entregue;
	}
	
	
}
