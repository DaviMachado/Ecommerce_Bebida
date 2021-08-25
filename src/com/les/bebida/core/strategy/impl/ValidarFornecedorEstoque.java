package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo fornecedor do Estoque
 * @author Davi Rodrigues
 * @date 25/08/2021
 */
public class ValidarFornecedorEstoque implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Estoque estoque = (Estoque) entidade;
		
		if(estoque.getFornecedor() == null || estoque.getFornecedor().equals("")) {
			return ("Favor insira um fornecedor no Estoque.");
		}
		else {
			return null;
		}
	}

}
