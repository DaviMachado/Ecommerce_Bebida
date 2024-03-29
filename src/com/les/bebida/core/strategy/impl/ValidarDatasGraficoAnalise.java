package com.les.bebida.core.strategy.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.GraficoAnalise;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar as datas do Gr�fico An�lise
 * @author Davi Rodrigues
 * @date 11/11/2021
 */
public class ValidarDatasGraficoAnalise implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		GraficoAnalise grafico = (GraficoAnalise) entidade;
		
		// pega a data atual
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);
		
		if(grafico.getDtInicio() == null || grafico.getDtInicio().equals("")) {
			return ("Favor insira uma Data Inicio.");
		}
		else if(grafico.getDtFim() == null || grafico.getDtFim().equals("")) {
			return ("Favor insira uma Data Fim.");
		}
		else {
			try {
				// CAST de String para Date
				Date dateInicio = dateFormat.parse(grafico.getDtInicio());
				Date dateFim = dateFormat.parse(grafico.getDtFim());
				Date dateAtual = dateFormat.parse(dataAtual);
				
				// data inicio vem depois (maior) que data atual?
				if(dateInicio.after(dateAtual)) {
					return ("Favor insira uma Data Inicio menor ou igual a Data Atual.");
				}
				// data fim vem depois (maior) que data atual?
				else if(dateFim.after(dateAtual)) {
					return ("Favor insira uma Data Fim menor ou igual a Data Atual.");
				}
				// data inicio vem depois (maior) que data fim?
				else if(dateInicio.after(dateFim)) {
					return ("Favor insira uma Data Inicio menor do que a Data Fim.");
				}
				else {
					return null;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ("Erro de Exception nas datas do gr�fico!");
			}
		}
	}

}
