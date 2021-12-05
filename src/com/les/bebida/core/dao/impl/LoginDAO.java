package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.les.bebida.core.dominio.Bandeira;
import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.Cupom;
import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Usuario;

public class LoginDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Usuario
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cliente "+
				"(login, senha, nome, telefone, cd_sistema, status, dt_cadastro, tipo)" +
				"values (?,?,?,?,?,?,?,?)";
		
		try {
			Usuario usuario = (Usuario) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,usuario.getLogin());
			stmt.setString(2,usuario.getSenha());
			stmt.setString(3,usuario.getNome());
			stmt.setString(4, usuario.getTelefone());
			stmt.setString(5, usuario.getCdSistema());
			stmt.setString(6, usuario.getStatus());
			stmt.setString(7, usuario.getDtCadastro());
			stmt.setString(8, usuario.getTipo());
			
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
				
				// descriptografando a senha que vem do banco,
				// para não acusar erro na Strategy "ValidarSenhaIgualCliente",
				// para poder validar se a senha esta com letra minuscula e maiscula
				String senhaCriptografada = usuarioItem.getSenha();
			    // Decodifica uma string anteriormente codificada usando o método decodeBase64 e
			    // passando o byte[] da string codificada
			    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
			    // Converte o byte[] decodificado de volta para a string original
			    String decodedString = new String(decoded);
			    usuarioItem.setSenha(decodedString);
				
				usuarioItem.setNome(rs.getString("nome"));
				usuarioItem.setTelefone(rs.getString("telefone"));
				usuarioItem.setCdSistema(rs.getString("cd_sistema"));
				usuarioItem.setStatus(rs.getString("status"));
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
				
				// descriptografando a senha que vem do banco,
				// para não acusar erro na Strategy "ValidarSenhaIgualCliente",
				// para poder validar se a senha esta com letra minuscula e maiscula
				String senhaCriptografada = usuarioItem.getSenha();
			    // Decodifica uma string anteriormente codificada usando o método decodeBase64 e
			    // passando o byte[] da string codificada
			    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
			    // Converte o byte[] decodificado de volta para a string original
			    String decodedString = new String(decoded);
			    usuarioItem.setSenha(decodedString);
				
				usuarioItem.setNome(rs.getString("nome"));
				usuarioItem.setTelefone(rs.getString("telefone"));
				usuarioItem.setCdSistema(rs.getString("cd_sistema"));
				usuarioItem.setStatus(rs.getString("status"));
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
				
				// descriptografando a senha que vem do banco,
				// para não acusar erro na Strategy "ValidarSenhaIgualCliente",
				// para poder validar se a senha esta com letra minuscula e maiscula
				String senhaCriptografada = usuarioItem.getSenha();
			    // Decodifica uma string anteriormente codificada usando o método decodeBase64 e
			    // passando o byte[] da string codificada
			    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
			    // Converte o byte[] decodificado de volta para a string original
			    String decodedString = new String(decoded);
			    usuarioItem.setSenha(decodedString);
				
				usuarioItem.setNome(rs.getString("nome"));
				usuarioItem.setTelefone(rs.getString("telefone"));
				usuarioItem.setCdSistema(rs.getString("cd_sistema"));
				usuarioItem.setStatus(rs.getString("status"));
				usuarioItem.setDtCadastro(rs.getString("dt_cadastro"));
				usuarioItem.setTipo(rs.getString("tipo"));
				usuarioItem.setCpf(rs.getString("cpf"));
				usuarioItem.setDt_nasc(rs.getString("dt_nasc"));
				usuarioItem.setSexo(rs.getString("sexo"));
				
				// adicionando o objeto à lista
				usuarios.add(usuarioItem);
			}
			
			stmt = connection.prepareStatement("select * from produto where status='ativo'");
			rs = stmt.executeQuery();
			
			List<Produto> produtos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Produto
				Produto produtoItem = new Produto();
				
				produtoItem.setId(rs.getString("id"));
				produtoItem.setNome(rs.getString("nome"));
				produtoItem.setDescricao(rs.getString("descricao"));
				produtoItem.setCategoria(rs.getString("categoria"));
				produtoItem.setPrecoDeCompra(rs.getString("preco_de_compra"));
				produtoItem.setPrecoDeVenda(rs.getString("preco_de_venda"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setFotoDetalhe(rs.getString("foto_detalhe"));
				produtoItem.setGrupoDePrecificacao(rs.getString("grupo_de_precificacao"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setQuantidade(rs.getString("quantidade"));
				produtoItem.setCdSistema(rs.getString("cd_sistema"));
				produtoItem.setStatus(rs.getString("status"));
				produtoItem.setDtCadastro(rs.getString("dt_cadastro"));
				produtoItem.setObservacao(rs.getString("observacao"));
				
				// adicionando o objeto à lista
				produtos.add(produtoItem);
			}
			
			stmt = connection.prepareStatement("select * from bandeira");
			rs = stmt.executeQuery();
			
			List<Bandeira> bandeiras = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Bandeira
				Bandeira bandeiraItem = new Bandeira();
				
				bandeiraItem.setId(rs.getString("id"));
				bandeiraItem.setNome(rs.getString("nome"));
				bandeiraItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				bandeiras.add(bandeiraItem);
			}
			
			// crio um novo Usuario, recebendo a REFERENCIA da lista de usuario criado a cima,
			Usuario novoUsuario = (Usuario) usuarios.get(0);
			// com a REFERENCIA da lista de usuario, atribui a lista de Produtos somente ativos
			novoUsuario.setProdutos(produtos);
			novoUsuario.setBandeiras(bandeiras);
				
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
	
	
	/**
	 * Metodo para Listar o ultimo codigo do Cliente cadastrado no sistema
	 * @param entidade
	 * @return
	 */
	public List<Usuario> consultarUltimoCodigoSistemaCadastrado (){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cliente WHERE cd_sistema=(SELECT max(cd_sistema) FROM cliente)");
			ResultSet rs = stmt.executeQuery();
			
			List<Usuario> usuarios = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Usuario
				Usuario usuarioItem = new Usuario();
				
				usuarioItem.setId(rs.getString("id"));
				usuarioItem.setLogin(rs.getString("login"));
				usuarioItem.setSenha(rs.getString("senha"));
				
				// descriptografando a senha que vem do banco,
				// para não acusar erro na Strategy "ValidarSenhaIgualCliente",
				// para poder validar se a senha esta com letra minuscula e maiscula
				String senhaCriptografada = usuarioItem.getSenha();
			    // Decodifica uma string anteriormente codificada usando o método decodeBase64 e
			    // passando o byte[] da string codificada
			    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
			    // Converte o byte[] decodificado de volta para a string original
			    String decodedString = new String(decoded);
			    usuarioItem.setSenha(decodedString);
				
				usuarioItem.setNome(rs.getString("nome"));
				usuarioItem.setTelefone(rs.getString("telefone"));
				usuarioItem.setCdSistema(rs.getString("cd_sistema"));
				usuarioItem.setStatus(rs.getString("status"));
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
	} // Listar o ultimo codigo do Cliente cadastrado no sistema 
	
}
