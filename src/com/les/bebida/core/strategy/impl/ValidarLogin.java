package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo login do Usuario
 * @author Davi Rodrigues
 * @date 21/04/2021
 */
public class ValidarLogin implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Usuario usuario = (Usuario) entidade;
		
		if(usuario.getLogin() == null || usuario.getLogin().equals("")) {
			return ("Favor insira um login.");
		}
		else {
			return null;
		}
	}

}
