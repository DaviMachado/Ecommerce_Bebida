package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo status do cliente
 * @author Davi Rodrigues
 * @date 17/08/2021
 */
public class ValidarStatusCliente implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		// se o "alteraCliente" for igual a 1, executa essa regra
		if(cliente.getAlteraCliente().contentEquals("1")) {
			if(cliente.getStatus() == null || cliente.getStatus().equals("")) {
				return "Favor insira um status.";
			}
			else {
				return null;
			}
		}
		// se não, o "alteraCliente" é igual a 0, e não executa essa regra,
		// solução provisoria para poder editar um cliente atraves do arquivo de listagem do mesmo,
		// pois quando tentava editar um cliente pela listagem, ele não abria a tela de edição, pois caia nessa validação
		else {
			return null;
		}
	}

}
