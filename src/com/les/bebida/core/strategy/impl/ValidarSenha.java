package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar a senha do Login (Usuario)
 * @author Davi Rodrigues
 * @date 24/04/2021
 */
public class ValidarSenha implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Usuario usuario = (Usuario) entidade;
		
		if(usuario.getSenha() == null || usuario.getSenha().equals("")) {
			return ("Favor insira uma senha.");
		}
		else {
			return null;
		}
	}
	
}
