package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.Carrinho;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.ItemCarrinho;
import com.les.bebida.core.dominio.Produto;

public class ItemCarrinhoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Item do Carrinho
	 * @param entidade
	 */
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		ItemCarrinho itemCarrinhoEntidade = (ItemCarrinho) entidade;
		
		ProdutoDAO dao = new ProdutoDAO();
		
		// pesquisa no banco o produto selecionado,
		// conforme o ID do produto que foi pega na tela
		List<Produto> produtoSelecionado = dao.consultarProdutoById(itemCarrinhoEntidade.getProduto().getId());
		
		// adiciona o produto pesquisado no banco,
		// para o produto que esta selecionado na tela, para poder pegar todos os dados do produto,
		// o itemCarrinhoEntidade (entidade) que veio como parametro, esse objeto esta sendo alterado como REFERENCIA,
		// logo consigo buscar esse objeto no resultado no SetView do ViewHelper do ItemCarrinhoHelper
		itemCarrinhoEntidade.setProduto(produtoSelecionado.get(0));
	} // Salvar
	
	
	/**
	 * Metodo para alterar o Item do Carrinho
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir o Item do Carrinho
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para Consultar o Item do Carrinho
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	} // Consultar
	
}
