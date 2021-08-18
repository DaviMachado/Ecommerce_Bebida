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
		
		// se o "alteraCliente" for igual a 1, executa essa regra
		if(cliente.getAlteraCliente().contentEquals("1")) {
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
		// se n�o, o "alteraCliente" � igual a 0, e n�o executa essa regra,
		// solu��o provisoria para poder editar um cliente atraves do arquivo de listagem do mesmo,
		// pois quando tentava editar um cliente pela listagem, ele n�o abria a tela de edi��o, pois caia nessa valida��o
		else {
			return null;
		}
	}
	
}
