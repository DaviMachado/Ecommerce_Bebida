package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar a senha do cliente
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public class ValidarSenha implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade, String msg) {
		
		Cliente cliente = (Cliente) entidade;
		Usuario usuario = cliente.getUsuario();
		
		if(usuario.getSenha() == null || usuario.getSenha().equals("")) {
			return (msg + "- Favor insira uma senha. \n");
		}
		else {
			return msg;
		}
	}
	
}
