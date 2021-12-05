package com.les.bebida.core.dominio;

import java.util.List;

/**
 * Classe para representar o Cliente
 * @author Davi Rodrigues
 * @date 05/12/2021
 */
public class Cliente extends Pessoa {
	private String status;
	private String cd_sistema;
	private String telefone;
	private Usuario usuario;
	private String tipo;
	private Endereco endereco;
	private String alteraCliente;
	private List<Cliente> clienteByTipoSomenteCliente;
	private List<Cliente> todosClientes;
	private Cliente clientePesquisado;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCdSistema() {
		return cd_sistema;
	}
	public void setCdSistema(String cd_sistema) {
		this.cd_sistema = cd_sistema;
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
	public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getAlteraCliente() {
		return alteraCliente;
	}
	public void setAlteraCliente(String alteraCliente) {
		this.alteraCliente = alteraCliente;
	}
	
	public List<Cliente> getClienteByTipoSomenteCliente() {
        return clienteByTipoSomenteCliente;
    }
    public void setClienteByTipoSomenteCliente(List<Cliente> clienteByTipoSomenteCliente) {
        this.clienteByTipoSomenteCliente = clienteByTipoSomenteCliente;
    }
    
    public List<Cliente> getTodosClientes() {
        return todosClientes;
    }
    public void setTodosClientes(List<Cliente> todosClientes) {
        this.todosClientes = todosClientes;
    }
    
    public Cliente getClientePesquisado() {
		return clientePesquisado;
	}
	public void setClientePesquisado(Cliente clientePesquisado) {
		this.clientePesquisado = clientePesquisado;
	}
}
