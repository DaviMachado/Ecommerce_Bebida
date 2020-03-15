package com.les.bebida.core.dominio;

/**
 * Classe para representar o Pedido
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public class Pedido extends EntidadeDominio{
	private int codigo;
	private String dt_pedido;
	private String nome_cliente;
	private String observavao;
	private float precoTotal;
	private String status;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDt_pedido() {
		return dt_pedido;
	}
	public void setDt_pedido(String dt_pedido) {
		this.dt_pedido = dt_pedido;
	}
	public String getNome_cliente() {
		return nome_cliente;
	}
	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}
	public String getObservavao() {
		return observavao;
	}
	public void setObservavao(String observavao) {
		this.observavao = observavao;
	}
	public float getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(float precoTotal) {
		this.precoTotal = precoTotal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	// Metodo
	public void ValidarPrimieraCompra() {
		
	}
}