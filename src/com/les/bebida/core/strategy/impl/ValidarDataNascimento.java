package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo data nascimento do cliente
 * @author Davi Rodrigues
 * @date 27/11/2019
 */
public class ValidarDataNascimento implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getDt_nasc() == null || cliente.getDt_nasc().equals("")) {
			return ("Favor insira uma Data de Nascimento.");
		}
		else {
			return null;
		}
	}

}
