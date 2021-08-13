package com.les.bebida.core.strategy.impl;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.les.bebida.core.dominio.CartaoDeCredito;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar a data de cadastro do cartao de credito
 * @author Davi Rodrigues
 * @date 13/08/2021
 */
public class ValidarDataCadastroCartaoDeCredito implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		CartaoDeCredito cartao = (CartaoDeCredito) entidade;
		
		// seta a data atual no usuario
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);
		
		cartao.setDtCadastro(dataAtual);
		
		return null;
	}

}
