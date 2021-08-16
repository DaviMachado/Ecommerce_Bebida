package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo codigo do sistema do cliente
 * @author Davi Rodrigues
 * @date 15/08/2021
 */
public class ValidarCodigoSistemaCliente implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		//if(cliente.getCdCliente() == null || cliente.getCdCliente().equals("")) {
			//return ("Favor insira um codigo p/ o sistema.");
		//}
		//else {
			return null;
		//}
	}

}
