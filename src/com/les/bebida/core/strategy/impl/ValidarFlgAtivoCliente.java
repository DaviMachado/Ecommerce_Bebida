package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o flg de ativo do cliente
 * @author Davi Rodrigues
 * @date 09/08/2021
 */
public class ValidarFlgAtivoCliente implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		// seta a flag do cliente como 1, porque ao cadastrar um novo cliente, ele já estará ativado
		cliente.setFlgAtivo("1");

		return null;
	}

}
