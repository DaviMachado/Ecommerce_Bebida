package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;

public class ClienteDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Cliente
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cliente "+ // login,senha,
				"(nome,cpf,dt_nasc,cd_cliente,sexo,telefone,fl_ativo)" +
				"values (?,?,?,?,?,?,?)";
		
		try {
			Cliente cliente = (Cliente) entidade;
//			Usuario usuario = cliente.getUsuario();
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
//			stmt.setString(1,usuario.getLogin());
//			stmt.setString(2,usuario.getSenha());
			stmt.setString(1,cliente.getNome());
			stmt.setString(2,cliente.getCpf());
			stmt.setString(3,cliente.getDt_nasc());
			stmt.setString(4,cliente.getCdCliente());
			stmt.setString(5, cliente.getSexo());
			stmt.setString(6, cliente.getTelefone());
			stmt.setString(7, cliente.getFlgAtivo());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	
	/**
	 * Metodo para alterar o Cliente
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
		openConnection();
		
		String sql = "update cliente set " + // login=?, senha=?,
					 "nome=?, cpf=?, dt_nasc=?, cd_cliente=?, sexo=?, telefone=?, fl_ativo=? where cpf=?";
		
		try {
			Cliente cliente = (Cliente) entidade;
//			Usuario usuario = cliente.getUsuario();
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
//			stmt.setString(1, usuario.getLogin());
//			stmt.setString(2, usuario.getSenha());
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getDt_nasc());
			stmt.setString(4, cliente.getCdCliente());
			stmt.setString(5, cliente.getSexo());
			stmt.setString(6, cliente.getTelefone());
			stmt.setString(7, cliente.getFlgAtivo());
			stmt.setString(8, cliente.getCpf());
			
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar
	
	
	/**
	 * Metodo para Excluir o Cliente
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			Cliente cliente = (Cliente) entidade;
			
			PreparedStatement stmt = connection.prepareStatement("delete from cliente where cpf=?");
			
			stmt.setString(1, cliente.getCpf());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir
	
	
	/**
	 * Metodo para Listar o Cliente
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> clientes = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cliente");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Cliente
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				cliente.setUsuario(usuario);
				
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_Nasc"));
				cliente.setCdCliente(rs.getString("cd_cliente"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setFlgAtivo(rs.getString("fl_ativo"));
				
				// adicionando o objeto à lista
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
}
