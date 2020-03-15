package com.les.bebida.view.command.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;

/**
 * Classe SalvarCommand
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public class SalvarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.salvar(entidade);
	}

}
