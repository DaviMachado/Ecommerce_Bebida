package com.les.bebida.core.strategy.impl;

import java.util.List;

import com.les.bebida.core.dao.impl.ProdutoDAO;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o codigo do sistema do produto
 * @author Davi Rodrigues
 * @date 18/11/2021
 */
public class ValidarCodigoSistemaProduto implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		int proximoCodigoSistema = 0;
		
		// consulta o ultimo codigo do sistema cadastrado no Produto,
		// para poder gerar o proximo codigo disponivel para ser usado
		List<Produto> ultimoCodigoSistemaDoProduto = produtoDAO.consultarUltimoCodigoSistemaCadastrado();
		
		// CAST de String para INT
		proximoCodigoSistema = Integer.parseInt(ultimoCodigoSistemaDoProduto.get(0).getCdSistema()) + 1;
		
		// CAST de INT para String
		produto.setCdSistema(Integer.toString(proximoCodigoSistema));
		
		return null;
	}

}
