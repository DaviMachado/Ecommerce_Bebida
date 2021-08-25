package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo grupo de precificação do Produto
 * @author Davi Rodrigues
 * @date 25/08/2021
 */
public class ValidarGrupoPrecificacaoProduto implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		
		// se o "alteraProduto" for igual a 1, executa essa regra
		if(produto.getAlteraProduto().contentEquals("1")) {
			if(produto.getGrupoDePrecificacao() == null || produto.getGrupoDePrecificacao().equals("")) {
				return "Favor insira um grupo de precificação no Produto.";
			}
			else {
				return null;
			}
		}
		// se não, o "alteraProduto" é igual a 0, e não executa essa regra,
		// solução provisoria para poder editar um produto atraves do arquivo de listagem do mesmo,
		// pois quando tentava editar um produto pela listagem, ele não abria a tela de edição, pois caia nessa validação
		else {
			return null;
		}
	}

}
