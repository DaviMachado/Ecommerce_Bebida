package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;

/**
 * Classe EnderecoDAO,
 * responsável para salvar o endereço no BD.
 * @author Davi Rodrigues
 * @date 24/02/2021
 */
public class EnderecoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Endereço
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into endereco "+
				"(cep, cidade, logradouro, numero, bairro, complemento, estado, id_cliente)" +
				"values (?,?,?,?,?,?,?,?)";
		
		try {
			Endereco endereco = (Endereco) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,endereco.getCep());
			stmt.setString(2,endereco.getCidade());
			stmt.setString(3,endereco.getLogradouro());
			stmt.setString(4,endereco.getNumero());
			stmt.setString(5, endereco.getBairro());
			stmt.setString(6, endereco.getComplemento());
			stmt.setString(7, endereco.getEstado());
			stmt.setString(8,endereco.getIdCliente());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	
	/**
	 * Metodo para alterar o Endereço
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
		openConnection();
		
		String sql = "update endereco set " +
					 "cep=?, cidade=?, logradouro=?, numero=?, bairro=?, complemento=?, estado=? where id_cliente=?";
		
		try {
			Endereco endereco = (Endereco) entidade;
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, endereco.getCep());
			stmt.setString(2, endereco.getCidade());
			stmt.setString(3, endereco.getLogradouro());
			stmt.setString(4, endereco.getNumero());
			stmt.setString(5, endereco.getBairro());
			stmt.setString(6, endereco.getComplemento());
			stmt.setString(7, endereco.getEstado());
			stmt.setString(8, endereco.getIdCliente());
			
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar
	
	
	/**
	 * Metodo para Excluir o Endereço
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			Endereco endereco = (Endereco) entidade;
			
			PreparedStatement stmt = connection.prepareStatement("delete from endereco where id_cliente=?");
			
			stmt.setString(1, endereco.getIdCliente());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir
	
	
	/**
	 * Metodo para Listar o Endereço
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> enderecos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from endereco");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Endereço
				Endereco endereco = new Endereco();
				
				endereco.setCep(rs.getString("cep"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setIdCliente(rs.getString("id_cliente"));
				
				// adicionando o objeto à lista
				enderecos.add(endereco);
			}
			rs.close();
			stmt.close();
			return enderecos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
}
