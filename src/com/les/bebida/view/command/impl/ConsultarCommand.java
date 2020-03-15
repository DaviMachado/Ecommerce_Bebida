package com.les.bebida.view.command.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;

/**
 * Classe ConsultarCommand
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public class ConsultarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.consultar(entidade);
	}
	
}
