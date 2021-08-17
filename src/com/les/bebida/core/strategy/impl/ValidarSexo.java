package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo sexo do cliente
 * @author Davi Rodrigues
 * @date 17/08/2021
 */
public class ValidarSexo implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getSexo() == null || cliente.getSexo().equals("")) {
			return "Favor insira um sexo.";
		}
		else {
			return null;
		}
	}

}
