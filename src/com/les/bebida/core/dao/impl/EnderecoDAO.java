package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;

/**
 * Classe EnderecoDAO,
 * respons�vel para salvar o endere�o no BD.
 * @author Davi Rodrigues
 * @date 14/03/2021
 */
public class EnderecoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Endere�o
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into endereco "+
				"(cep, cidade, logradouro, numero, bairro, complemento, estado, id_cliente)" +
				"values (?,?,?,?,?,?,?,?)";
		
		try {
			Endereco endereco = (Endereco) entidade;
			
			// prepared statement para inser��o
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
	 * Metodo para alterar o Endere�o
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
		openConnection();
		
		String sql = "update endereco set " +
					 "cep=?, cidade=?, logradouro=?, numero=?, bairro=?, complemento=?, estado=? where id=?";
		
		try {
			Endereco endereco = (Endereco) entidade;
			
			// se tiver algo no "idCliente", altera o endere�o
			if (endereco.getIdCliente() != null) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, endereco.getCep());
				stmt.setString(2, endereco.getCidade());
				stmt.setString(3, endereco.getLogradouro());
				stmt.setString(4, endereco.getNumero());
				stmt.setString(5, endereco.getBairro());
				stmt.setString(6, endereco.getComplemento());
				stmt.setString(7, endereco.getEstado());
				stmt.setString(8, endereco.getId());
				
				stmt.execute();
				stmt.close();
			}
			// caso contr�rio, n�o faz altera��o e somente fecha a conex�o com o banco,
			// e no EnderecoHelper, ir� ter outra verifica��o para poder chamar a JSP de edi��o do endere�o
			else {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar
	
	
	/**
	 * Metodo para Excluir o Endere�o
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			Endereco endereco = (Endereco) entidade;
			
			PreparedStatement stmt = connection.prepareStatement("delete from endereco where id=?");
			
			stmt.setString(1, endereco.getId());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir
	
	
	/**
	 * Metodo para Listar o Endere�o
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			Endereco endereco = (Endereco) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from endereco where id_cliente=?");
			stmt.setString(1, endereco.getIdCliente());
			ResultSet rs = stmt.executeQuery();
			
			List<EntidadeDominio> enderecos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Endere�o
				Endereco enderecoItem = new Endereco();
				
				enderecoItem.setId(rs.getString("id"));
				enderecoItem.setCep(rs.getString("cep"));
				enderecoItem.setCidade(rs.getString("cidade"));
				enderecoItem.setLogradouro(rs.getString("logradouro"));
				enderecoItem.setNumero(rs.getString("numero"));
				enderecoItem.setBairro(rs.getString("bairro"));
				enderecoItem.setComplemento(rs.getString("complemento"));
				enderecoItem.setEstado(rs.getString("estado"));
				enderecoItem.setIdCliente(rs.getString("id_cliente"));
				
				// adicionando o objeto � lista
				enderecos.add(enderecoItem);
			}
			rs.close();
			stmt.close();
			return enderecos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
	/**
	 * Metodo para Listar o Endere�o por ID
	 * @param entidade
	 * @return
	 */
	public List<Endereco> consultarEnderecoById (String idEndereco){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from endereco where id=?");
			stmt.setString(1, idEndereco);
			ResultSet rs = stmt.executeQuery();
			
			List<Endereco> enderecos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Endere�o
				Endereco enderecoItem = new Endereco();
				
				enderecoItem.setId(rs.getString("id"));
				enderecoItem.setCep(rs.getString("cep"));
				enderecoItem.setCidade(rs.getString("cidade"));
				enderecoItem.setLogradouro(rs.getString("logradouro"));
				enderecoItem.setNumero(rs.getString("numero"));
				enderecoItem.setBairro(rs.getString("bairro"));
				enderecoItem.setComplemento(rs.getString("complemento"));
				enderecoItem.setEstado(rs.getString("estado"));
				enderecoItem.setIdCliente(rs.getString("id_cliente"));
				
				// adicionando o objeto � lista
				enderecos.add(enderecoItem);
			}
				
			rs.close();
			stmt.close();
			return enderecos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Endere�o por ID
	
}
