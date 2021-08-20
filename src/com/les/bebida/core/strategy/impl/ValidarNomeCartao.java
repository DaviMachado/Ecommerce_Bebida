package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.CartaoDeCredito;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo nome do Cartão de Credito
 * @author Davi Rodrigues
 * @date 19/08/2021
 */
public class ValidarNomeCartao implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		CartaoDeCredito cartao = (CartaoDeCredito) entidade;
		
		// se o "alteraCartao" for igual a 1, executa essa regra
		if(cartao.getAlteraCartao().contentEquals("1")) {
			if(cartao.getNome() == null || cartao.getNome().equals("")) {
				return "Favor insira um nome no Cartão de Crédito.";
			}
			else {
				return null;
			}
		}
		// se não, o "alteraCartao" é igual a 0, e não executa essa regra,
		// solução provisoria para poder editar um cartão de credito atraves do arquivo de listagem do mesmo,
		// pois quando tentava editar um cartão de credito pela listagem, ele não abria a tela de edição, pois caia nessa validação
		else {
			return null;
		}
	}

}
