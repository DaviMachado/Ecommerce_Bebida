package com.les.bebida.core.dominio;

import java.util.List;

/**
 * Classe para representar o Usuario
 * @author Davi Rodrigues
 * @date 13/11/2021
 */
public class Usuario extends EntidadeDominio {
	private String login;
	private String senha;
	private String confirmarSenha;
	private String nome;
	private String telefone;
	private String status;
	private String tipo;
	private String cd_sistema;
	private List<Produto> produtos;
	private List<Bandeira> bandeiras;
	private String cpf;
	private String dt_nasc;
	private String sexo;
	private List<Cupom> cuponsCliente;
	private List<Cliente> todosClientes;
	
	
	public String getLogin() {
		if (login == null || login.equals("")) {
            return null;
        }
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		if (senha == null || senha.equals("")) {
            return null;
        }
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getConfirmarSenha() {
		if (confirmarSenha == null || confirmarSenha.equals("")) {
            return null;
        }
		return confirmarSenha;
	}
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getCdSistema() {
		return cd_sistema;
	}
	public void setCdSistema(String cd_sistema) {
		this.cd_sistema = cd_sistema;
	}
	
	public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
	public List<Bandeira> getBandeiras() {
        return bandeiras;
    }
    public void setBandeiras(List<Bandeira> bandeiras) {
        this.bandeiras = bandeiras;
    }
    
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDt_nasc() {
		return dt_nasc;
	}
	public void setDt_nasc(String dt_nasc) {
		this.dt_nasc = dt_nasc;
	}
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public List<Cupom> getCuponsCliente() {
        return cuponsCliente;
    }
    public void setCuponsCliente(List<Cupom> cuponsCliente) {
        this.cuponsCliente = cuponsCliente;
    }
    
    public List<Cliente> getTodosClientes() {
        return todosClientes;
    }
    public void setTodosClientes(List<Cliente> todosClientes) {
        this.todosClientes = todosClientes;
    }
}
