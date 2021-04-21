package com.les.bebida.core.strategy.impl;

import java.util.List;

import com.les.bebida.core.dao.impl.LoginDAO;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar a existencia do Login (Usuario)
 * @author Davi Rodrigues
 * @date 21/04/2021
 */
public class ValidarExistenciaLogin implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Usuario usuario = (Usuario) entidade;
		LoginDAO dao = new LoginDAO();
		
		List<Usuario> usuarios = dao.consultarUsuarioByLogin(usuario.getLogin());
		
		if(!usuarios.isEmpty()) {
			return ("Usuário já existente.");
		}
		else {
			return null;
		}
	}
	
}
