package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo fornecedor do Estoque
 * @author Davi Rodrigues
 * @date 25/08/2021
 */
public class ValidarDataEntradaSaidaEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getDtEntrada() == null || estoque.getDtEntrada().equals("")) {
			return ("Favor insira uma data de entrada/saida no Estoque.");
		}
		else {
			return null;
		}
	}

}
