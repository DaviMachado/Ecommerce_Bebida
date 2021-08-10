package com.les.bebida.core.strategy.impl;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar a data de cadastro do usuario
 * @author Davi Rodrigues
 * @date 10/08/2021
 */
public class ValidarDataCadastroUsuario implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		
		// seta a data atual no usuario
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);
		
		usuario.setDtCadastro(dataAtual);
		
		return null;
	}

}
