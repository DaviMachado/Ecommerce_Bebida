package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o CEP do Endereço,
 * e validar se a quantidade do CEP digitas são no minimo 8 caracteres
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
				return ("Favor insira um CEP no endereço com no minimo 8 números.");
			}
			else {
				return null;
			}
		}
		// se não, o "alteraEndereco" é igual a 0, e não executa essa regra,
		// solução provisoria para poder editar um endereço atraves do arquivo de listagem do mesmo,
		// pois quando tentava editar um endereço pela listagem, ele não abria a tela de edição, pois caia nessa validação
		else {
			return null;
		}
	}
	
}
