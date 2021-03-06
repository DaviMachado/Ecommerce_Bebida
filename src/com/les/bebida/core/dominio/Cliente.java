package com.les.bebida.core.dominio;

/**
 * Classe para representar o Cliente
 * @author Davi Rodrigues
 * @date 10/03/2021
 */
public class Cliente extends Pessoa {
	private String cdCliente;
	private String flgAtivo;
	private String telefone;
	private Usuario usuario;
	private TipoCliente tipoCliente;
	private Endereco endereco;
	
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
