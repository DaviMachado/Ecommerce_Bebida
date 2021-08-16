package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o status de ativo do usuario
 * @author Davi Rodrigues
 * @date 15/08/2021
 */
public class ValidarStatusUsuario implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		
		// seta o Status do usuario como "ativo", 
		// porque ao cadastrar um novo usuario, ele já estará ativado
		usuario.setStatus("ativo");

		return null;
	}

}
