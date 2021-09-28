package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo forma de pagamento do Pedido
 * @author Davi Rodrigues
 * @date 27/09/2021
 */
public class ValidarFormaDePagamento implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Pedido pedido = (Pedido) entidade;
		
		if(pedido.getFormaPagamento() == null || pedido.getFormaPagamento().equals("")) {
			return ("Favor selecione uma Forma de Pagamento.");
		}
		else {
			return null;
		}
	}

}
