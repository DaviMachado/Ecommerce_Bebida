package com.les.bebida.core.dominio;

/**
 * Classe para representar o Cart�o de Credito
 * @author Davi Rodrigues
 * @date 13/08/2021
 */
public class CartaoDeCredito extends EntidadeDominio {
	private String num_cartao;
	private String nome;
	private String bandeira;
	private String cod_seguranca;
	private String flgPreferencial;
	private String id_cliente;
	private String dt_validade;
	
	
	public String getNum_cartao() {
		return num_cartao;
	}
	public void setNum_cartao(String num_cartao) {
		this.num_cartao = num_cartao;
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
	public String getCod_seguranca() {
		return cod_seguranca;
	}
	public void setCod_seguranca(String cod_seguranca) {
		this.cod_seguranca = cod_seguranca;
	}
	public String getFlgPreferencial() {
		return flgPreferencial;
	}
	public void setFlgPreferencial(String flgPreferencial) {
		this.flgPreferencial = flgPreferencial;
	}
	public String getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(String idCliente) {
		this.id_cliente = idCliente;
	}
	public String getDt_validade() {
		return dt_validade;
	}
	public void setDt_validade(String dt_validade) {
		this.dt_validade = dt_validade;
	}
}
