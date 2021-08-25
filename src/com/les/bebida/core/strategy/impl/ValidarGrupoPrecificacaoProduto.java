package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo grupo de precifica��o do Produto
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
				return "Favor insira um grupo de precifica��o no Produto.";
			}
			else {
				return null;
			}
		}
		// se n�o, o "alteraProduto" � igual a 0, e n�o executa essa regra,
		// solu��o provisoria para poder editar um produto atraves do arquivo de listagem do mesmo,
		// pois quando tentava editar um produto pela listagem, ele n�o abria a tela de edi��o, pois caia nessa valida��o
		else {
			return null;
		}
	}

}
