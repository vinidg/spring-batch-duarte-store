package br.com.vinidg.rastreio.domain.dto;

import java.time.LocalDateTime;

import br.com.vinidg.rastreio.enums.Entrega;

public class RastreioDTO {
	private Entrega entrega;
	private LocalDateTime data;
	
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Entrega getEntrega() {
		return entrega;
	}
	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}
	
}
