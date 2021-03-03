package com.les.bebida.core.strategy;

import com.les.bebida.core.dominio.EntidadeDominio;

/**
 * Interface IStrategy
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public interface IStrategy {
	
	public String validar (EntidadeDominio entidade);
	
}
