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
				"(login, senha, nome, telefone, fl_ativo, dt_cadastro, tipo)" +
				"values (?,?,?,?,?,?,?)";
		
		try {
			Usuario usuario = (Usuario) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,usuario.getLogin());
			stmt.setString(2,usuario.getSenha());
			stmt.setString(3,usuario.getNome());
			stmt.setString(4, usuario.getTelefone());
			stmt.setString(5, usuario.getFlgAtivo());
			stmt.setString(6, usuario.getDtCadastro());
			stmt.setString(7, usuario.getTipo());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar

	
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
				usuarioItem.setFlgAtivo(rs.getString("fl_ativo"));
				usuarioItem.setDtCadastro(rs.getString("dt_cadastro"));
				usuarioItem.setTipo(rs.getString("tipo"));
				
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
	
	
	/**
	 * Metodo para Listar/Verificar o Usuario por LOGIN e SENHA
	 * @param entidade
	 * @return
	 */
	public List<Usuario> consultarUsuarioByLoginAndSenha (String loginUsuario, String loginSenha){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where login=? and senha=?");
			stmt.setString(1, loginUsuario);
			stmt.setString(2, loginSenha);
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
				usuarioItem.setFlgAtivo(rs.getString("fl_ativo"));
				usuarioItem.setDtCadastro(rs.getString("dt_cadastro"));
				usuarioItem.setTipo(rs.getString("tipo"));
				
				// adicionando o objeto à lista
				usuarios.add(usuarioItem);
			}
				
			rs.close();
			stmt.close();
			return usuarios;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar/Verificar Usuario por LOGIN e SENHA
	
	
	/**
	 * Metodo para consultar o Usuario
	 * função criada para consultar o Login após acessar o sistema,
	 * quando a Fachada realizar a função de consulta, esta função de Consultar será chamada para
	 * poder fazer o setEntidades do Resultado, com isso feito, será salvo esse Login em sessão no arquivo LoginHelper.
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		try {
			Usuario usuario = (Usuario) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where login=? and senha=?");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();
			
			List<EntidadeDominio> usuarios = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Usuario
				Usuario usuarioItem = new Usuario();
				
				usuarioItem.setId(rs.getString("id"));
				usuarioItem.setLogin(rs.getString("login"));
				usuarioItem.setSenha(rs.getString("senha"));
				usuarioItem.setNome(rs.getString("nome"));
				usuarioItem.setTelefone(rs.getString("telefone"));
				usuarioItem.setFlgAtivo(rs.getString("fl_ativo"));
				usuarioItem.setDtCadastro(rs.getString("dt_cadastro"));
				usuarioItem.setTipo(rs.getString("tipo"));
				
				// adicionando o objeto à lista
				usuarios.add(usuarioItem);
			}
				
			rs.close();
			stmt.close();
			return usuarios;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar
	
	
	/**
	 * Metodo para alterar o Usuario
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir o Usuario
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
}
