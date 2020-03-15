package com.les.bebida.core.dominio;

/**
 * Classe para representar o Usuario
 * @author Davi Rodrigues
 * @date 26/12/2019
 */
public class Usuario extends EntidadeDominio {
	private String login;
	private String senha;
	
	
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
}
