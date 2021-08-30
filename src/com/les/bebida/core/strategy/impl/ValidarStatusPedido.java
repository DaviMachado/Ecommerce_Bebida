package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o status do Pedido
 * @author Davi Rodrigues
 * @date 29/08/2021
 */
public class ValidarStatusPedido implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Pedido pedido = (Pedido) entidade;
		
		// seta o status do pedido como "EM PROCESSAMENTO",
		// para quando for salvar o pedido, o primeiro status dele será "EM PROCESSAMENTO"
		pedido.setStatus("EM PROCESSAMENTO");

		return null;
	}

}
