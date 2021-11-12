package com.les.bebida.core.strategy.impl;

import java.util.List;

import com.les.bebida.core.dao.impl.ClienteDAO;
import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o codigo do sistema do cliente
 * @author Davi Rodrigues
 * @date 12/11/2021
 */
public class ValidarCodigoSistemaCliente implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		int proximoCodigoSistema = 0;
		
		// consulta o ultimo codigo do sistema cadastrado no Cliente,
		// para poder gerar o proximo codigo disponivel para ser usado
		List<Cliente> ultimoCodigoSistemaDoCliente = clienteDAO.consultarUltimoCodigoSistemaCadastrado();
		
		// CAST de String para INT
		proximoCodigoSistema = Integer.parseInt(ultimoCodigoSistemaDoCliente.get(0).getCdSistema()) + 1;
		
		// CAST de INT para String
		cliente.setCdSistema(Integer.toString(proximoCodigoSistema));
		
		return null;
	}

}
