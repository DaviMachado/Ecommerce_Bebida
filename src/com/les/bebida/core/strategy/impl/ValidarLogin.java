package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo login do cliente
 * @author Davi Rodrigues
 * @date 27/11/2019
 */
public class ValidarLogin implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Cliente cliente = (Cliente) entidade;
		Usuario usuario = cliente.getUsuario();
		
		if(usuario.getLogin() == null || usuario.getLogin().equals("")) {
			return ("Favor insira um login.");
		}
		else {
			return null;
		}
	}

}
