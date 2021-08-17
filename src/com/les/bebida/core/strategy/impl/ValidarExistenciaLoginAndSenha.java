package com.les.bebida.core.strategy.impl;

import java.util.List;

import com.les.bebida.core.dao.impl.LoginDAO;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar a existencia do Usuario pelo Login e Senha,
 * e validar se o mesmo esta ativo ou inativo
 * @author Davi Rodrigues
 * @date 16/08/2021
 */
public class ValidarExistenciaLoginAndSenha implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Usuario usuario = (Usuario) entidade;
		LoginDAO dao = new LoginDAO();
		
		List<Usuario> usuarios = dao.consultarUsuarioByLoginAndSenha(usuario.getLogin(), usuario.getSenha());
		
		if(usuarios.isEmpty()) {
			return ("Email e/ou senha inválidos. Por favor, tente novamente.");
		}
		else {
			if(usuarios.get(0).getStatus().equals("inativo")) {
				return ("Usuário provisoriamente Inativo!. Por favor, entre em contato com o Administrador!");
			}
			else {
				return null;
			}
		}
	}
	
}
