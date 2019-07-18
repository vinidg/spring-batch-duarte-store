package br.com.vinidg.rastreio.enums;

public enum PushMessage {

	PAGAMENTO_CONFIRMADO("Pagamento Confirmado", "O pagamento foi confirmado e sua encomenda está sendo embalada para envio."),
	PEDIDO_NA_DISTRIBUIDORA("Pedido na Distribuidora", "Encomenda está na base de distribuição, em processo de separação por destino"),
	PEDIDO_EM_ENTREGA("Pedido em Rota", "A encomenda foi liberada para seguir viagem de entrega."),
	PEDIDO_ENTREGUE("Pedido Entregue", "O pedido foi entregue.");

	private String titulo;
	private String mensagem;
	
	private PushMessage(String titulo, String mensagem) {
		this.titulo = titulo;
		this.mensagem = mensagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

}
