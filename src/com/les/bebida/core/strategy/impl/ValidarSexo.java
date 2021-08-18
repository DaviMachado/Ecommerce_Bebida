package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo sexo do cliente
 * @author Davi Rodrigues
 * @date 17/08/2021
 */
public class ValidarSexo implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		// se o "alteraCliente" for igual a 1, executa essa regra
		if(cliente.getAlteraCliente().contentEquals("1")) {
			if(cliente.getSexo() == null || cliente.getSexo().equals("")) {
				return "Favor insira um sexo.";
			}
			else {
				return null;
			}
		}
		// se n�o, o "alteraCliente" � igual a 0, e n�o executa essa regra,
		// solu��o provisoria para poder editar um cliente atraves do arquivo de listagem do mesmo,
		// pois quando tentava editar um cliente pela listagem, ele n�o abria a tela de edi��o, pois caia nessa valida��o
		else {
			return null;
		}
	}

}
