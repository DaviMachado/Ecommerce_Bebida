package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo quantidade entrada/saida do Estoque
 * @author Davi Rodrigues
 * @date 25/08/2021
 */
public class ValidarQuantidadeEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getQuantidadeEntradaSaida() == null || estoque.getQuantidadeEntradaSaida().equals("")) {
			return ("Favor insira uma quantidade no Estoque.");
		}
		else if (Integer.parseInt(estoque.getQuantidadeEntradaSaida()) <= 0) {
			return ("Selecione uma quantidade no Estoque maior que ZERO!");
		}
		else {
			return null;
		}
	}

}
