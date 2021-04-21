package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;

public class LoginDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Usuario
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cliente "+
				"(login, senha, nome, telefone)" +
				"values (?,?,?,?)";
		
		try {
			Usuario usuario = (Usuario) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,usuario.getLogin());
			stmt.setString(2,usuario.getSenha());
			stmt.setString(3,usuario.getNome());
			stmt.setString(4, usuario.getTelefone());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
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
				
				cliente.setId(rs.getString("id"));
				
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				cliente.setUsuario(usuario);
				
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_Nasc"));
				cliente.setCdCliente(rs.getString("cd_cliente"));
				cliente.setTelefone(rs.getString("telefone"));
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
	
	/**
	 * Metodo para Listar/Verificar o Usuario por LOGIN
	 * @param entidade
	 * @return
	 */
	public List<Usuario> consultarUsuarioByLogin (String loginUsuario){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where login=?");
			stmt.setString(1, loginUsuario);
			ResultSet rs = stmt.executeQuery();
			
			List<Usuario> usuarios = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Usuario
				Usuario usuarioItem = new Usuario();
				
				usuarioItem.setId(rs.getString("id"));
				usuarioItem.setLogin(rs.getString("login"));
				usuarioItem.setSenha(rs.getString("senha"));
				usuarioItem.setNome(rs.getString("nome"));
				usuarioItem.setTelefone(rs.getString("telefone"));
				
				// adicionando o objeto à lista
				usuarios.add(usuarioItem);
			}
				
			rs.close();
			stmt.close();
			return usuarios;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar/Verificar Usuario por LOGIN

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}
