package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo CPF do cliente
 * @author Davi Rodrigues
 * @date 27/11/2019
 */
public class ValidarCPF implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getCpf() == null || cliente.getCpf().equals("")) {
			return ("Favor insira um CPF.");
		}
		else {
			return null;
		}
	}

}
