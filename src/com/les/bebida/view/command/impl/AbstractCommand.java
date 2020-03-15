package com.les.bebida.view.command.impl;

import com.les.bebida.core.fachada.IFachada;
import com.les.bebida.core.fachada.impl.Fachada;
import com.les.bebida.view.command.ICommand;

/**
 * Classe abstrata AbstractCommand,
 * para instanciar a Fachada.
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public abstract class AbstractCommand implements ICommand {
	
	protected IFachada fachada = new Fachada();
	
}
