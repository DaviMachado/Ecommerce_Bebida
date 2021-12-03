package com.les.bebida.core.dominio;

import java.util.List;

/**
 * Classe para representar um Carrinho
 * @author Davi Rodrigues
 * @date 03/12/2021
 */
public class Carrinho extends EntidadeDominio {
	private ItemCarrinho itemCarrinho;
	private List<Endereco> enderecos;
	private List<CartaoDeCredito> cartoes;
	private List<Cupom> cupons;
	private String idCliente;
	
	public ItemCarrinho getItemCarrinho() {
        return itemCarrinho;
    }
    public void setItemCarrinho(ItemCarrinho itemCarrinho) {
        this.itemCarrinho = itemCarrinho;
    }
    
    public List<Endereco> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    public List<CartaoDeCredito> getCartoes() {
        return cartoes;
    }
    public void setCartoes(List<CartaoDeCredito> cartoes) {
        this.cartoes = cartoes;
    }
    
    public List<Cupom> getCupons() {
        return cupons;
    }
    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }
    
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
}
