package com.les.bebida.core.strategy.impl;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar a data de cadastro das entidades
 * @author Davi Rodrigues
 * @date 14/08/2021
 */
public class ValidarDataCadastro implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		// seta a data atual na entidade
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);
		
		entidade.setDtCadastro(dataAtual);
		
		return null;
	}

}
