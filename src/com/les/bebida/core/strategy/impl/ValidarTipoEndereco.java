package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o Tipo de Endere�o do Endere�o,
 * @author Davi Rodrigues
 * @date 19/08/2021
 */
public class ValidarTipoEndereco implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Endereco endereco = (Endereco) entidade;
		
		// se o "alteraEndereco" for igual a 1, executa essa regra
		if(endereco.getAlteraEndereco().contentEquals("1")) {
			if (endereco.getTipo_Endereco() == null || endereco.getTipo_Endereco().equals("")) {
				return ("Favor insira um Tipo de Endere�o.");
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
