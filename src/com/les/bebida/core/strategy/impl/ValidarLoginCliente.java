package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo login do cliente
 * @author Davi Rodrigues
 * @date 17/08/2021
 */
public class ValidarLoginCliente implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getUsuario().getLogin() == null || cliente.getUsuario().getLogin().equals("")) {
			return "Favor insira um login.";
		}
		else {
			return null;
		}
	}

}
