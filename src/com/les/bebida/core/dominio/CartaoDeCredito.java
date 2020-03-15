package com.les.bebida.core.dominio;

/**
 * Classe para representar o Cartão de Credito
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public class CartaoDeCredito extends EntidadeDominio {
	private int num_cart;
	private String nome;
	private String bandeira;
	private int cod_seg;
	
	
	public int getNum_cart() {
		return num_cart;
	}
	public void setNum_cart(int num_cart) {
		this.num_cart = num_cart;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	public int getCod_seg() {
		return cod_seg;
	}
	public void setCod_seg(int cod_seg) {
		this.cod_seg = cod_seg;
	}
}
