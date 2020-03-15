package com.les.bebida.view.command.impl;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;

/**
 * Classe ExcluirCommand
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public class ExcluirCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.excluir(entidade);
	}

}
