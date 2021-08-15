package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;

public class EstoqueDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Estoque
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into estoque "+
				"(id_produto, tipo, quantidade_entrada_saida, valor_custo, fornecedor, dt_entrada, quantidade_final, dt_cadastro)" +
				"values (?,?,?,?,?,?,?,?)";
		
		try {
			Estoque estoque = (Estoque) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1, estoque.getIdProduto());
			stmt.setString(2, estoque.getTipo());
			stmt.setString(3, estoque.getQuantidadeEntradaSaida());
			stmt.setString(4, estoque.getValorCusto());
			stmt.setString(5, estoque.getFornecedor());
			stmt.setString(6, estoque.getDtEntrada());
			stmt.setString(7, estoque.getQuantidadeFinal());
			stmt.setString(8, estoque.getDtCadastro());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	/**
	 * Metodo para alterar o Estoque
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir o Estoque
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para consultar o Estoque
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		try {
			Estoque estoque = (Estoque) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from estoque where id_produto=?");
			stmt.setString(1, estoque.getIdProduto());
			ResultSet rs = stmt.executeQuery();
			
			List<EntidadeDominio> estoques = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Estoque
				Estoque estqoueItem = new Estoque();
				
				estqoueItem.setId(rs.getString("id"));
				estqoueItem.setIdProduto(rs.getString("id_produto"));
				estqoueItem.setTipo(rs.getString("tipo"));
				estqoueItem.setQuantidadeEntradaSaida(rs.getString("quantidade_entrada_saida"));
				estqoueItem.setValorCusto(rs.getString("valor_custo"));
				estqoueItem.setFornecedor(rs.getString("fornecedor"));
				estqoueItem.setDtEntrada(rs.getString("dt_entrada"));
				estqoueItem.setQuantidadeFinal(rs.getString("quantidade_final"));
				estqoueItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				estoques.add(estqoueItem);
			}
				
			rs.close();
			stmt.close();
			return estoques;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar
	
}
