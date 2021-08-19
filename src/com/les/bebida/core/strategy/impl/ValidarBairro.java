package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o Bairro do Endereço,
 * @author Davi Rodrigues
 * @date 19/08/2021
 */
public class ValidarBairro implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Endereco endereco = (Endereco) entidade;
		
		// se o "alteraEndereco" for igual a 1, executa essa regra
		if(endereco.getAlteraEndereco().contentEquals("1")) {
			if (endereco.getBairro() == null || endereco.getBairro().equals("")) {
				return ("Favor insira um Bairro.");
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
