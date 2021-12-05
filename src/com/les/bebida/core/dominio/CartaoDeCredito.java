package com.les.bebida.core.dominio;

import java.util.List;

/**
 * Classe para representar o Cartão de Credito
 * @author Davi Rodrigues
 * @date 05/12/2021
 */
public class CartaoDeCredito extends EntidadeDominio {
	private String num_cartao;
	private String nome;
	private String cod_seguranca;
	private String flgPreferencial;
	private String id_cliente;
	private String id_bandeira;
	private String dt_validade;
	private String AlteraCartao;
	private List<CartaoDeCredito> cartoesCliente;
	private String nomeBandeira;
	private CartaoDeCredito cartaoPesquisado;
	private Cliente cliente;
	private List<Bandeira> bandeiras;
	
	
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
	public String getIdBandeira() {
		return id_bandeira;
	}
	public void setIdBandeira(String id_bandeira) {
		this.id_bandeira = id_bandeira;
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
	public String getAlteraCartao() {
		return AlteraCartao;
	}
	public void setAlteraCartao(String AlteraCartao) {
		this.AlteraCartao = AlteraCartao;
	}
	
	public List<CartaoDeCredito> getCartoesCliente() {
        return cartoesCliente;
    }
    public void setCartoesCliente(List<CartaoDeCredito> cartoesCliente) {
        this.cartoesCliente = cartoesCliente;
    }
    
	public String getNomeBandeira() {
		return nomeBandeira;
	}
	public void setNomeBandeira(String nomeBandeira) {
		this.nomeBandeira = nomeBandeira;
	}
	
	public CartaoDeCredito getCartaoPesquisado() {
		return cartaoPesquisado;
	}
	public void setCartaoPesquisado(CartaoDeCredito cartaoPesquisado) {
		this.cartaoPesquisado = cartaoPesquisado;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Bandeira> getBandeiras() {
        return bandeiras;
    }
    public void setBandeiras(List<Bandeira> bandeiras) {
        this.bandeiras = bandeiras;
    }
}
