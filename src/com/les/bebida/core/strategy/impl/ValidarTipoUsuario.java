package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o tipo do usuario
 * @author Davi Rodrigues
 * @date 10/08/2021
 */
public class ValidarTipoUsuario implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		
		// seta o tipo do usuario como "cliente",
		// para poder listar somente as telas do cliente no arquivo Home_Page.jsp
		usuario.setTipo("cliente");

		return null;
	}

}
