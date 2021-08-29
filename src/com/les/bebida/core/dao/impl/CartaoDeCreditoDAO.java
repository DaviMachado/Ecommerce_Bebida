package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.CartaoDeCredito;
import com.les.bebida.core.dominio.EntidadeDominio;

public class CartaoDeCreditoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Cartao de Credito
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cartao_de_credito "+ 
				"(nome, num_cartao, bandeira, cd_seguranca, dt_validade, preferencial, dt_cadastro, id_cliente)" +
				"values (?,?,?,?,?,?,?,?)";
		
		try {
			CartaoDeCredito cartao = (CartaoDeCredito) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,cartao.getNome());
			stmt.setString(2,cartao.getNum_cartao());
			stmt.setString(3,cartao.getBandeira());
			stmt.setString(4,cartao.getCod_seguranca());
			stmt.setString(5, cartao.getDt_validade());
			stmt.setString(6, cartao.getFlgPreferencial());
			stmt.setString(7, cartao.getDtCadastro());
			stmt.setString(8, cartao.getIdCliente());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	
	/**
	 * Metodo para alterar o Cartao de Credito
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
		openConnection();
		
		String sql = "update cartao_de_credito set " +
					 "nome=?, num_cartao=?, bandeira=?, cd_seguranca=?, dt_validade=?, preferencial=? where id=?";
		
		try {
			CartaoDeCredito cartao = (CartaoDeCredito) entidade;
			
			// se tiver algo no "alteraCartao", altera o cartao de credito
			if(cartao.getAlteraCartao().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, cartao.getNome());
				stmt.setString(2, cartao.getNum_cartao());
				stmt.setString(3, cartao.getBandeira());
				stmt.setString(4, cartao.getCod_seguranca());
				stmt.setString(5, cartao.getDt_validade());
				stmt.setString(6, cartao.getFlgPreferencial());
				stmt.setString(7, cartao.getId());
				
				stmt.execute();
				stmt.close();
			}
			// caso contrário, não faz alteração e somente fecha a conexão com o banco,
			// e no CartaoDeCreditoHelper, irá ter outra verificação para poder chamar a JSP de edição do cartao de credito
			else {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.close();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar
	
	
	/**
	 * Metodo para Excluir o Cartao de Credito
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			CartaoDeCredito cartao = (CartaoDeCredito) entidade;
			
			// Exclui o cartao de credito
			PreparedStatement stmt = connection.prepareStatement("delete from cartao_de_credito where id=?");
			stmt.setString(1, cartao.getId());
			stmt.executeUpdate();
			
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir
	
	
	/**
	 * Metodo para Listar o Cartao de Credito
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			CartaoDeCredito cartao = (CartaoDeCredito) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from cartao_de_credito where id_cliente=?");
			stmt.setString(1, cartao.getIdCliente());
			ResultSet rs = stmt.executeQuery();
			
			List<EntidadeDominio> cartoes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cartao de Credito
				CartaoDeCredito cartaoItem = new CartaoDeCredito();
				
				cartaoItem.setId(rs.getString("id"));
				cartaoItem.setNome(rs.getString("nome"));
				cartaoItem.setNum_cartao(rs.getString("num_cartao"));
				cartaoItem.setBandeira(rs.getString("bandeira"));
				cartaoItem.setCod_seguranca(rs.getString("cd_seguranca"));
				cartaoItem.setDt_validade(rs.getString("dt_validade"));
				cartaoItem.setFlgPreferencial(rs.getString("preferencial"));
				cartaoItem.setDtCadastro(rs.getString("dt_cadastro"));
				cartaoItem.setIdCliente(rs.getString("id_cliente"));
				
				// adicionando o objeto à lista
				cartoes.add(cartaoItem);
			}
			rs.close();
			stmt.close();
			return cartoes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
	
	/**
	 * Metodo para Listar o Cartao de Credito por ID
	 * @param entidade
	 * @return
	 */
	public List<CartaoDeCredito> consultarCartaoDeCreditoById (String idCartaoDeCredito){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cartao_de_credito where id=?");
			stmt.setString(1, idCartaoDeCredito);
			ResultSet rs = stmt.executeQuery();
			
			List<CartaoDeCredito> cartoes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cartao de Credito
				CartaoDeCredito cartaoItem = new CartaoDeCredito();
				
				cartaoItem.setId(rs.getString("id"));
				cartaoItem.setNome(rs.getString("nome"));
				cartaoItem.setNum_cartao(rs.getString("num_cartao"));
				cartaoItem.setBandeira(rs.getString("bandeira"));
				cartaoItem.setCod_seguranca(rs.getString("cd_seguranca"));
				cartaoItem.setDt_validade(rs.getString("dt_validade"));
				cartaoItem.setFlgPreferencial(rs.getString("preferencial"));
				cartaoItem.setDtCadastro(rs.getString("dt_cadastro"));
				cartaoItem.setIdCliente(rs.getString("id_cliente"));
				
				// adicionando o objeto à lista
				cartoes.add(cartaoItem);
			}
			rs.close();
			stmt.close();
			return cartoes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Cartao de Credito por ID
	
	
	/**
	 * Metodo para Listar todos os Cartões de Credito pelo ID do Cliente
	 * @param entidade
	 * @return
	 */
	public List<CartaoDeCredito> consultarCartaoDeCreditoByIdCliente (String idCliente){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cartao_de_credito where id_cliente=?");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			List<CartaoDeCredito> cartoes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cartao de Credito
				CartaoDeCredito cartaoItem = new CartaoDeCredito();
				
				cartaoItem.setId(rs.getString("id"));
				cartaoItem.setNome(rs.getString("nome"));
				cartaoItem.setNum_cartao(rs.getString("num_cartao"));
				cartaoItem.setBandeira(rs.getString("bandeira"));
				cartaoItem.setCod_seguranca(rs.getString("cd_seguranca"));
				cartaoItem.setDt_validade(rs.getString("dt_validade"));
				cartaoItem.setFlgPreferencial(rs.getString("preferencial"));
				cartaoItem.setDtCadastro(rs.getString("dt_cadastro"));
				cartaoItem.setIdCliente(rs.getString("id_cliente"));
				
				// adicionando o objeto à lista
				cartoes.add(cartaoItem);
			}
			rs.close();
			stmt.close();
			return cartoes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar todos os Cartões de Credito pelo ID do Cliente
	
}
