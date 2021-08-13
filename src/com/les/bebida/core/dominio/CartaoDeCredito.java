package com.les.bebida.core.dominio;

/**
 * Classe para representar o Cartão de Credito
 * @author Davi Rodrigues
 * @date 12/08/2021
 */
public class CartaoDeCredito extends EntidadeDominio {
	private int num_cart;
	private String nome;
	private String bandeira;
	private int cod_seg;
	private String flgPreferencial;
	
	
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
	public String getFlgPreferencial() {
		return flgPreferencial;
	}
	public void setFlgPreferencial(String flgPreferencial) {
		this.flgPreferencial = flgPreferencial;
	}
}
