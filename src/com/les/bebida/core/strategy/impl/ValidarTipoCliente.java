package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o tipo do cliente
 * @author Davi Rodrigues
 * @date 10/08/2021
 */
public class ValidarTipoCliente implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		// seta o tipo do cliente como "cliente",
		// para poder listar somente as telas do cliente no arquivo Home_Page.jsp
		cliente.setTipo("cliente");

		return null;
	}

}
