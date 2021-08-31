package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo endereço do Pedido
 * @author Davi Rodrigues
 * @date 30/08/2021
 */
public class ValidarEnderecoPedido implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Pedido pedido = (Pedido) entidade;
		
		if(pedido.getIdEndereco() == null || pedido.getIdEndereco().equals("")) {
			return ("Favor selecione algum Endereço de Entrega ou cadastre um novo.");
		}
		else {
			return null;
		}
	}

}
