package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo status do cliente
 * @author Davi Rodrigues
 * @date 17/08/2021
 */
public class ValidarStatusCliente implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getStatus() == null || cliente.getStatus().equals("")) {
			return "Favor insira um status.";
		}
		else {
			return null;
		}
	}

}
