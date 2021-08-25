package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo valor de custo do Estoque
 * @author Davi Rodrigues
 * @date 25/08/2021
 */
public class ValidarValorCustoEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getValorCusto() == null || estoque.getValorCusto().equals("")) {
			return ("Favor insira um valor de custo no Estoque.");
		}
		else {
			return null;
		}
	}

}
