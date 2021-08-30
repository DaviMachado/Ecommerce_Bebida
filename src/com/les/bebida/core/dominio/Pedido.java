package com.les.bebida.core.dominio;

/**
 * Classe para representar o Pedido
 * @author Davi Rodrigues
 * @date 29/08/2021
 */
public class Pedido extends EntidadeDominio{
	private String total_itens;
	private String total_frete;
	private String total_pedido;
	private String status;
	private String id_cliente;
	private String id_endereco;
	private String id_cartao;
	private String cupom;
	
	
	public String getTotalItens() {
		return total_itens;
	}
	public void setTotalItens(String total_itens) {
		this.total_itens = total_itens;
	}
	public String getTotalFrete() {
		return total_frete;
	}
	public void setTotalFrete(String total_frete) {
		this.total_frete = total_frete;
	}
	public String getTotalPedido() {
		return total_pedido;
	}
	public void setTotalPedido(String total_pedido) {
		this.total_pedido = total_pedido;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getIdEndereco() {
		return id_endereco;
	}
	public void setIdEndereco(String id_endereco) {
		this.id_endereco = id_endereco;
	}
	public String getIdCartao() {
		return id_cartao;
	}
	public void setIdCartao(String id_cartao) {
		this.id_cartao = id_cartao;
	}
	public String getCupom() {
		return cupom;
	}
	public void setCupom(String cupom) {
		this.cupom = cupom;
	}
	
	// Metodo
	public void ValidarPrimieraCompra() {
		
	}
}