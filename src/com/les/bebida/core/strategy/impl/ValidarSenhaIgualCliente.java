package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar se as senhas do Cliente s�o iguais,
 * e validar se a quantidade de senhas digitas s�o no minimo 8 caracteres
 * @author Davi Rodrigues
 * @date 17/08/2021
 */
public class ValidarSenhaIgualCliente implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Cliente cliente = (Cliente) entidade;
		
		if (cliente.getUsuario().getSenha() == null || cliente.getUsuario().getSenha().equals("")) {
			return ("Favor insira uma senha no cliente.");
		}
		else if (cliente.getUsuario().getSenha().length() < 8) {
			return ("Favor insira uma senha no cliente com no minimo 8 caracteres.");
		}
		else if (cliente.getUsuario().getConfirmarSenha() == null || cliente.getUsuario().getConfirmarSenha().equals("")) {
			return ("Favor insira uma confirmar senha no cliente.");
		}
		else if (cliente.getUsuario().getConfirmarSenha().length() < 8) {
			return ("Favor insira uma confirmar senha no cliente com no minimo 8 caracteres.");
		}
		else if (!cliente.getUsuario().getSenha().equals(cliente.getUsuario().getConfirmarSenha())) {
			return ("As senhas digitadas no cliente n�o se correspondem.");
		}
		else {
			return null;
		}
	}
	
}
