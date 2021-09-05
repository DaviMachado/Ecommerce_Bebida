package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.Cupom;
import com.les.bebida.core.dominio.EntidadeDominio;

public class CupomDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Cupom
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cupom "+ 
				"(nome, valor, tipo, utilizado, id_cliente, dt_cadastro)" +
				"values (?,?,?,?,?,?)";
		
		try {
			Cupom cupom = (Cupom) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,cupom.getNome());
			stmt.setString(2,cupom.getValor());
			stmt.setString(3,cupom.getTipo());
			stmt.setString(4,cupom.getUtilizado());
			stmt.setString(5, cupom.getIdCliente());
			stmt.setString(6, cupom.getDtCadastro());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	
	/**
	 * Metodo para alterar o Cupom
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
		openConnection();
		
		String sql = "update cupom set " +
					 "nome=?, valor=?, tipo=?, utilizado=?, id_cliente=? where id=?";
		
		try {
			Cupom cupom = (Cupom) entidade;
			
			// se tiver algo no "alteraCupom", altera o cupom
			if(cupom.getAlteraCupom().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, cupom.getNome());
				stmt.setString(2, cupom.getValor());
				stmt.setString(3, cupom.getTipo());
				stmt.setString(4, cupom.getUtilizado());
				stmt.setString(5, cupom.getIdCliente());

				stmt.setString(6, cupom.getId());
				
				stmt.execute();
				stmt.close();
			}
			// caso contrário, não faz alteração e somente fecha a conexão com o banco,
			// e no CupomHelper, irá ter outra verificação para poder chamar a JSP de edição do cupom
			else {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.close();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar
	
	
	/**
	 * Metodo para Excluir o Cupom
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			Cupom cupom = (Cupom) entidade;
			
			// Exclui o cupom
			PreparedStatement stmt = connection.prepareStatement("delete from cupom where id=?");
			stmt.setString(1, cupom.getId());
			stmt.executeUpdate();
			
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir
	
	
	/**
	 * Metodo para Listar o Cupom
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cupom");
			ResultSet rs = stmt.executeQuery();
			
			List<EntidadeDominio> cupons = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cupom
				Cupom cupomItem = new Cupom();
				
				cupomItem.setId(rs.getString("id"));
				cupomItem.setNome(rs.getString("nome"));
				cupomItem.setValor(rs.getString("valor"));
				cupomItem.setTipo(rs.getString("tipo"));
				cupomItem.setUtilizado(rs.getString("utilizado"));
				cupomItem.setIdCliente(rs.getString("id_cliente"));
				cupomItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cupons.add(cupomItem);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
	
	/**
	 * Metodo para Listar o Cupom por ID
	 * @param entidade
	 * @return
	 */
	public List<Cupom> consultarCupomById (String idCupom){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cupom where id=?");
			stmt.setString(1, idCupom);
			ResultSet rs = stmt.executeQuery();
			
			List<Cupom> cupons = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cupom
				Cupom cupomItem = new Cupom();
				
				cupomItem.setId(rs.getString("id"));
				cupomItem.setNome(rs.getString("nome"));
				cupomItem.setValor(rs.getString("valor"));
				cupomItem.setTipo(rs.getString("tipo"));
				cupomItem.setUtilizado(rs.getString("utilizado"));
				cupomItem.setIdCliente(rs.getString("id_cliente"));
				cupomItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cupons.add(cupomItem);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Cupom por ID
	
	
	/**
	 * Metodo para Listar todos os Cupons pelo ID do Cliente
	 * @param entidade
	 * @return
	 */
	public List<Cupom> consultarCupomByIdCliente (String idCliente){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cupom where id_cliente=?");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			List<Cupom> cupons = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cupom
				Cupom cupomItem = new Cupom();
				
				cupomItem.setId(rs.getString("id"));
				cupomItem.setNome(rs.getString("nome"));
				cupomItem.setValor(rs.getString("valor"));
				cupomItem.setTipo(rs.getString("tipo"));
				cupomItem.setUtilizado(rs.getString("utilizado"));
				cupomItem.setIdCliente(rs.getString("id_cliente"));
				cupomItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cupons.add(cupomItem);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar todos os Cupons pelo ID do Cliente
	
	
	/**
	 * Metodo para Listar todos os Cupons pelo Nome
	 * @param entidade
	 * @return
	 */
	public List<Cupom> consultarCupomByNome (String nome){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cupom where nome=?");
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			
			List<Cupom> cupons = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cupom
				Cupom cupomItem = new Cupom();
				
				cupomItem.setId(rs.getString("id"));
				cupomItem.setNome(rs.getString("nome"));
				cupomItem.setValor(rs.getString("valor"));
				cupomItem.setTipo(rs.getString("tipo"));
				cupomItem.setUtilizado(rs.getString("utilizado"));
				cupomItem.setIdCliente(rs.getString("id_cliente"));
				cupomItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cupons.add(cupomItem);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar todos os Cupons pelo Nome
	
	
	/**
	 * Metodo para Listar todos os Cupons pelo Nome e ID do Cliente
	 * @param entidade
	 * @return
	 */
	public List<Cupom> consultarCupomByNomeAndIdCliente (String nome, String idCliente){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cupom where nome=? and id_cliente=?");
			stmt.setString(1, nome);
			stmt.setString(2, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			List<Cupom> cupons = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cupom
				Cupom cupomItem = new Cupom();
				
				cupomItem.setId(rs.getString("id"));
				cupomItem.setNome(rs.getString("nome"));
				cupomItem.setValor(rs.getString("valor"));
				cupomItem.setTipo(rs.getString("tipo"));
				cupomItem.setUtilizado(rs.getString("utilizado"));
				cupomItem.setIdCliente(rs.getString("id_cliente"));
				cupomItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cupons.add(cupomItem);
			}
			rs.close();
			stmt.close();
			return cupons;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar todos os Cupons pelo Nome e ID do Cliente
	
}
