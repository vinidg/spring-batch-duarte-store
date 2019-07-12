package br.com.vinidg.rastreio.enums;

public enum Entrega {

	PAGAMENTO_CONFIRMADO("1", "O pagamento foi confirmado"),
	PEDIDO_NA_DISTRIBUIDORA("2", "O pedido está na distribuidora"),
	PEDIDO_EM_ENTREGA("3", "O pedido na rota de entrega"),
	PEDIDO_ENTREGUE("4", "O pedido foi entregue");

	private String id;
	private String descricao;
	
	
	private Entrega(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static Entrega toEnum(String id) {
		if (id == null)
			return null;
		
		for(Entrega x : Entrega.values()) {
			if(id.equals(x.getId()))
				return x;
		}
		throw new IllegalArgumentException("id inv�lido " + id);
	}

	public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
