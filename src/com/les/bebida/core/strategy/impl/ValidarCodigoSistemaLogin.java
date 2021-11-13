package com.les.bebida.core.strategy.impl;

import java.util.List;

import com.les.bebida.core.dao.impl.LoginDAO;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o codigo do sistema do cliente pela tela do Login
 * @author Davi Rodrigues
 * @date 13/11/2021
 */
public class ValidarCodigoSistemaLogin implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Usuario login = (Usuario) entidade;
		
		LoginDAO loginDAO = new LoginDAO();
		int proximoCodigoSistema = 0;
		
		// consulta o ultimo codigo do sistema cadastrado no Cliente,
		// para poder gerar o proximo codigo disponivel para ser usado
		List<Usuario> ultimoCodigoSistemaDoCliente = loginDAO.consultarUltimoCodigoSistemaCadastrado();
		
		// CAST de String para INT
		proximoCodigoSistema = Integer.parseInt(ultimoCodigoSistemaDoCliente.get(0).getCdSistema()) + 1;
		
		// CAST de INT para String
		login.setCdSistema(Integer.toString(proximoCodigoSistema));
		
		return null;
	}

}
