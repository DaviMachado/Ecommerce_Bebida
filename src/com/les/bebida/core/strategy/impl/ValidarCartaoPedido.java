package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo cart�o de cr�dito do Pedido
 * @author Davi Rodrigues
 * @date 30/08/2021
 */
public class ValidarCartaoPedido implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Pedido pedido = (Pedido) entidade;
		
		if(pedido.getIdCartao1() == null || pedido.getIdCartao1().equals("")) {
			return ("Favor selecione algum Cart�o de Cr�dito ou cadastre um novo.");
		}
		else {
			return null;
		}
	}

}
