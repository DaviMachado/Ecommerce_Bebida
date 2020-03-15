package com.les.bebida.core.dominio;

/**
 * Classe para representar o Cliente
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public class Cliente extends Pessoa {
	private String cdCliente;
	private String flgAtivo;
	private Usuario usuario;
	private String telefone;
	
	
	public String getCdCliente() {
		return cdCliente;
	}
	public void setCdCliente(String cdCliente) {
		this.cdCliente = cdCliente;
	}
	public String getFlgAtivo() {
		return flgAtivo;
	}
	public void setFlgAtivo(String flgAtivo) {
		this.flgAtivo = flgAtivo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
