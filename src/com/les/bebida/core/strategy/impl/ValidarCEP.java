package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o CEP do Endere�o,
 * e validar se a quantidade do CEP digitas s�o no minimo 8 caracteres
 * @author Davi Rodrigues
 * @date 19/08/2021
 */
public class ValidarCEP implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Endereco endereco = (Endereco) entidade;
		
		// se o "alteraEndereco" for igual a 1, executa essa regra
		if(endereco.getAlteraEndereco().contentEquals("1")) {
			if (endereco.getCep() == null || endereco.getCep().equals("")) {
				return ("Favor insira um CEP.");
			}
			else if (endereco.getCep().length() < 8) {
				return ("Favor insira um CEP no endere�o com no minimo 8 n�meros.");
			}
			else {
				return null;
			}
		}
		// se n�o, o "alteraEndereco" � igual a 0, e n�o executa essa regra,
		// solu��o provisoria para poder editar um endere�o atraves do arquivo de listagem do mesmo,
		// pois quando tentava editar um endere�o pela listagem, ele n�o abria a tela de edi��o, pois caia nessa valida��o
		else {
			return null;
		}
	}
	
}
