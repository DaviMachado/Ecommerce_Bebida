package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo nome ao criar um novo login
 * @author Davi Rodrigues
 * @date 07/08/2021
 */
public class ValidarNomeLogin implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Usuario usuario = (Usuario) entidade;
		
		if(usuario.getNome() == null || usuario.getNome().equals("")) {
			return ("Favor insira um nome.");
		}
		else {
			return null;
		}
	}

}
