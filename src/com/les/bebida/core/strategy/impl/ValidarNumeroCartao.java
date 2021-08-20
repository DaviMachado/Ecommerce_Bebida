package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.CartaoDeCredito;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo numero do Cart�o de Credito
 * @author Davi Rodrigues
 * @date 19/08/2021
 */
public class ValidarNumeroCartao implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		CartaoDeCredito cartao = (CartaoDeCredito) entidade;
		
		// se o "alteraCartao" for igual a 1, executa essa regra
		if(cartao.getAlteraCartao().contentEquals("1")) {
			if(cartao.getNum_cartao() == null || cartao.getNum_cartao().equals("")) {
				return "Favor insira um numero no Cart�o de Cr�dito.";
			}
			else if (cartao.getNum_cartao().length() < 16) {
				return "Favor insira um numero no Cart�o de Cr�dito com no minimo 16 caracteres.";
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
