package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.CartaoDeCredito;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo bandeira do Cart�o de Credito
 * @author Davi Rodrigues
 * @date 05/10/2021
 */
public class ValidarBandeiraCartao implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		CartaoDeCredito cartao = (CartaoDeCredito) entidade;
		
		// se o "alteraCartao" for igual a 1, executa essa regra
		if(cartao.getAlteraCartao().contentEquals("1")) {
			if(cartao.getIdBandeira() == null || cartao.getIdBandeira().equals("")) {
				return "Favor insira uma bandeira no Cart�o de Cr�dito.";
			}
			else {
				return null;
			}
		}
		// se n�o, o "alteraCartao" � igual a 0, e n�o executa essa regra,
		// solu��o provisoria para poder editar um cart�o de credito atraves do arquivo de listagem do mesmo,
		// pois quando tentava editar um cart�o de credito pela listagem, ele n�o abria a tela de edi��o, pois caia nessa valida��o
		else {
			return null;
		}
	}

}
