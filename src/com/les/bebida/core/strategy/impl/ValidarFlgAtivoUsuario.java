package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o flg de ativo do usuario
 * @author Davi Rodrigues
 * @date 10/08/2021
 */
public class ValidarFlgAtivoUsuario implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		
		// seta a flag do usuario como 1, porque ao cadastrar um novo usuario, ele já estará ativado
		usuario.setFlgAtivo("1");

		return null;
	}

}
