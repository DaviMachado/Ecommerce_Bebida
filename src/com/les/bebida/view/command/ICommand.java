package com.les.bebida.view.command;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;

/**
 * Interface ICommand
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public interface ICommand {
	
	public Resultado execute (EntidadeDominio entidade);
	
}
