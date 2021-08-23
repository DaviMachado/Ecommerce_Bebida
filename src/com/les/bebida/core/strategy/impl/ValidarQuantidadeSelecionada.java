package com.les.bebida.core.strategy.impl;

import java.util.List;

import com.les.bebida.core.dao.impl.ProdutoDAO;
import com.les.bebida.core.dominio.Carrinho;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar se a quantidade selecionada no item do carrinho, 
 * não seja maior que a quantidade disponivel do estoque,
 * e verifica se a quantidade selecionada é menor que zero
 * @author Davi Rodrigues
 * @date 22/08/2021
 */
public class ValidarQuantidadeSelecionada implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Carrinho carrinho = (Carrinho) entidade;
		ProdutoDAO dao = new ProdutoDAO();
		
		// pesquisa no banco o produto selecionado
		List<Produto> produtoSelecionado = dao.consultarProdutoById(carrinho.getItemCarrinho().getProduto().getId());
		
		// verifica se a quantidade selecionada é menor que zero
		if (Integer.parseInt(carrinho.getItemCarrinho().getProduto().getQuantidadeSelecionada()) < 0) {
			return ("Selecione uma quantidade maior que ZERO!");
		}
		// verifica se a quantidade selecionada no item do carrinho, é maior que disponivel no estoque,
		// feito o CAST de String para INT, para poder fazer o calculo "Integer.parseInt(String)"
		else if(Integer.parseInt(carrinho.getItemCarrinho().getProduto().getQuantidadeSelecionada()) > Integer.parseInt(produtoSelecionado.get(0).getQuantidade())) {
			return ("Quantidade selecionada é maior que disponivel no estoque!");
		}
		else {
			return null;
		}
	}
	
}
