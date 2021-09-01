package com.les.bebida.core.dominio;

/**
 * Classe para representar os Itens do Pedido
 * @author Davi Rodrigues
 * @date 31/08/2021
 */
public class ItemPedido extends EntidadeDominio {
	private String id_pedido;
	private Produto produto;
	
	public String getIdPedido() {
		return id_pedido;
	}
	public void setIdPedido(String id_pedido) {
		this.id_pedido = id_pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
