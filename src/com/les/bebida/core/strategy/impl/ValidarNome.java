package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo nome do cliente
 * @author Davi Rodrigues
 * @date 28/02/2021
 */
public class ValidarNome implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getNome() == null || cliente.getNome().equals("")) {
			return "Favor insira um nome.";
		}
		else {
			return null;
		}
	}

}
