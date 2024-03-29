package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

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
		
		String sql = "insert into cliente "+
				"(login, senha, nome, cpf, dt_nasc, sexo, telefone, cd_sistema, status, dt_cadastro, tipo)" +
				"values (?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Cliente cliente = (Cliente) entidade;
			Usuario usuario = cliente.getUsuario();
			
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,usuario.getLogin());
			stmt.setString(2,usuario.getSenha());
			stmt.setString(3,cliente.getNome());
			stmt.setString(4,cliente.getCpf());
			stmt.setString(5,cliente.getDt_nasc());
			stmt.setString(6, cliente.getSexo());
			stmt.setString(7, cliente.getTelefone());
			stmt.setString(8,cliente.getCdSistema());
			stmt.setString(9, cliente.getStatus());
			stmt.setString(10, cliente.getDtCadastro());
			stmt.setString(11, cliente.getTipo());
			
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
		
		String sql = "update cliente set " +
					 "login=?, senha=?, nome=?, cpf=?, dt_nasc=?, sexo=?, telefone=?, status=? where id=?";
		
		try {
			Cliente clienteEntidade = (Cliente) entidade;
			Usuario usuario = clienteEntidade.getUsuario();
			
			// se tiver algo no "alteraCliente", altera o cliente
			if(clienteEntidade.getAlteraCliente().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, usuario.getLogin());
				stmt.setString(2, usuario.getSenha());
				stmt.setString(3, clienteEntidade.getNome());
				stmt.setString(4, clienteEntidade.getCpf());
				stmt.setString(5, clienteEntidade.getDt_nasc());
				stmt.setString(6, clienteEntidade.getSexo());
				stmt.setString(7, clienteEntidade.getTelefone());
				stmt.setString(8, clienteEntidade.getStatus());
				stmt.setString(9, clienteEntidade.getId());
				
				stmt.execute();
				stmt.close();
			}
			// caso contr�rio, n�o faz altera��o e pesquisa somente 1 cliente no banco,
			// e no ClienteoHelper, ir� ter outra verifica��o para poder chamar a JSP de edi��o do cliente ADMIN
			else {
				PreparedStatement stmt = connection.prepareStatement("select * from cliente where id=?");
				stmt.setString(1, clienteEntidade.getId());
				ResultSet rs = stmt.executeQuery();
				
				List<Cliente> clientes = new ArrayList<>();
				while (rs.next()) {
					Cliente cliente = new Cliente();
					Usuario usuarioById = new Usuario();
					
					cliente.setId(rs.getString("id"));
					
					usuarioById.setLogin(rs.getString("login"));
					usuarioById.setSenha(rs.getString("senha"));
					
					// descriptografando a senha que vem do banco,
					// para n�o acusar erro na Strategy "ValidarSenhaIgualCliente",
					// para poder validar se a senha esta com letra minuscula e maiscula
					String senhaCriptografada = usuarioById.getSenha();
				    // Decodifica uma string anteriormente codificada usando o m�todo decodeBase64 e
				    // passando o byte[] da string codificada
				    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
				    // Converte o byte[] decodificado de volta para a string original
				    String decodedString = new String(decoded);
				    usuarioById.setSenha(decodedString);
				    
					cliente.setUsuario(usuarioById);
					
					cliente.setNome(rs.getString("nome"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setDt_nasc(rs.getString("dt_Nasc"));
					cliente.setSexo(rs.getString("sexo"));
					cliente.setTelefone(rs.getString("telefone"));
					cliente.setCdSistema(rs.getString("cd_sistema"));
					cliente.setStatus(rs.getString("status"));
					cliente.setDtCadastro(rs.getString("dt_cadastro"));
					cliente.setTipo(rs.getString("tipo"));
					
					// adicionando o objeto � lista
					clientes.add(cliente);
				}
				
				rs.close();
				stmt.close();
				
				// salva o obejto do cliente pesquisado, para mandar para a tela de edi��o
				// salva como REFERENCIA para o mesmo objeto que veio como parametro
				clienteEntidade.setClientePesquisado(clientes.get(0));
			}

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
			Cliente clienteEntidade = (Cliente) entidade;
			
			// Exclui os endere�os relacionados com o cliente
			PreparedStatement stmt = connection.prepareStatement("delete from endereco where id_cliente=?");
			stmt.setString(1, clienteEntidade.getId());
			stmt.executeUpdate();
			
			// Exclui os cart�es de creditos relacionados com o cliente
			stmt = connection.prepareStatement("delete from cartao_de_credito where id_cliente=?");
			stmt.setString(1, clienteEntidade.getId());
			stmt.executeUpdate();
			
			// Exclui o cliente
			stmt = connection.prepareStatement("delete from cliente where id=?");
			stmt.setString(1, clienteEntidade.getId());
			stmt.executeUpdate();
			
			stmt = connection.prepareStatement("select * from cliente where tipo = 'cliente'");
			ResultSet rs = stmt.executeQuery();

			List<Cliente> somenteClientes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cliente
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				// descriptografando a senha que vem do banco,
				// para n�o acusar erro na Strategy "ValidarSenhaIgualCliente",
				// para poder validar se a senha esta com letra minuscula e maiscula
				String senhaCriptografada = usuario.getSenha();
			    // Decodifica uma string anteriormente codificada usando o m�todo decodeBase64 e
			    // passando o byte[] da string codificada
			    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
			    // Converte o byte[] decodificado de volta para a string original
			    String decodedString = new String(decoded);
			    usuario.setSenha(decodedString);
			    
				cliente.setUsuario(usuario);
				
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_Nasc"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCdSistema(rs.getString("cd_sistema"));
				cliente.setStatus(rs.getString("status"));
				cliente.setDtCadastro(rs.getString("dt_cadastro"));
				cliente.setTipo(rs.getString("tipo"));
				
				// adicionando o objeto � lista
				somenteClientes.add(cliente);
			}
			
			rs.close();
			stmt.close();
			
			// salva como REFERENCIA os clientes, somente clientes,
			// para poder listar os clientes de novo
			clienteEntidade.setClienteByTipoSomenteCliente(somenteClientes);
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
			List<EntidadeDominio> listClientes = new ArrayList<>();
			Cliente novoCliente = new Cliente();
			
			PreparedStatement stmt = connection.prepareStatement("select * from cliente");
			ResultSet rs = stmt.executeQuery();
			
			List<Cliente> clientes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cliente
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				// descriptografando a senha que vem do banco,
				// para n�o acusar erro na Strategy "ValidarSenhaIgualCliente",
				// para poder validar se a senha esta com letra minuscula e maiscula
				String senhaCriptografada = usuario.getSenha();
			    // Decodifica uma string anteriormente codificada usando o m�todo decodeBase64 e
			    // passando o byte[] da string codificada
			    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
			    // Converte o byte[] decodificado de volta para a string original
			    String decodedString = new String(decoded);
			    usuario.setSenha(decodedString);
				
				cliente.setUsuario(usuario);
				
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_Nasc"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCdSistema(rs.getString("cd_sistema"));
				cliente.setStatus(rs.getString("status"));
				cliente.setDtCadastro(rs.getString("dt_cadastro"));
				cliente.setTipo(rs.getString("tipo"));
				
				// adicionando o objeto � lista
				clientes.add(cliente);
			}
			
			stmt = connection.prepareStatement("select * from cliente where tipo = 'cliente'");
			rs = stmt.executeQuery();
			
			List<Cliente> somenteClientes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cliente
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				// descriptografando a senha que vem do banco,
				// para n�o acusar erro na Strategy "ValidarSenhaIgualCliente",
				// para poder validar se a senha esta com letra minuscula e maiscula
				String senhaCriptografada = usuario.getSenha();
			    // Decodifica uma string anteriormente codificada usando o m�todo decodeBase64 e
			    // passando o byte[] da string codificada
			    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
			    // Converte o byte[] decodificado de volta para a string original
			    String decodedString = new String(decoded);
			    usuario.setSenha(decodedString);
			    
				cliente.setUsuario(usuario);
				
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_Nasc"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCdSistema(rs.getString("cd_sistema"));
				cliente.setStatus(rs.getString("status"));
				cliente.setDtCadastro(rs.getString("dt_cadastro"));
				cliente.setTipo(rs.getString("tipo"));
				
				// adicionando o objeto � lista
				somenteClientes.add(cliente);
			}
			
			novoCliente.setTodosClientes(clientes);
			novoCliente.setClienteByTipoSomenteCliente(somenteClientes);
			
			listClientes.add(novoCliente);
			
			rs.close();
			stmt.close();
			return listClientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
	/**
	 * Metodo para Listar o Cliente por ID
	 * @param entidade
	 * @return
	 */
	public List<Cliente> consultarClienteById (String idCliente){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where id=?");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			List<Cliente> clientes = new ArrayList<>();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				// descriptografando a senha que vem do banco,
				// para n�o acusar erro na Strategy "ValidarSenhaIgualCliente",
				// para poder validar se a senha esta com letra minuscula e maiscula
				String senhaCriptografada = usuario.getSenha();
			    // Decodifica uma string anteriormente codificada usando o m�todo decodeBase64 e
			    // passando o byte[] da string codificada
			    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
			    // Converte o byte[] decodificado de volta para a string original
			    String decodedString = new String(decoded);
			    usuario.setSenha(decodedString);
			    
				cliente.setUsuario(usuario);
				
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_Nasc"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCdSistema(rs.getString("cd_sistema"));
				cliente.setStatus(rs.getString("status"));
				cliente.setDtCadastro(rs.getString("dt_cadastro"));
				cliente.setTipo(rs.getString("tipo"));
				
				// adicionando o objeto � lista
				clientes.add(cliente);
			}
				
			rs.close();
			stmt.close();
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Cliente por ID
	
	/**
	 * Metodo para Listar somente Cliente do tipo CLIENTE
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultarClienteByTipoSomenteCliente (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> clientes = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where tipo = 'cliente'");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Cliente
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				// descriptografando a senha que vem do banco,
				// para n�o acusar erro na Strategy "ValidarSenhaIgualCliente",
				// para poder validar se a senha esta com letra minuscula e maiscula
				String senhaCriptografada = usuario.getSenha();
			    // Decodifica uma string anteriormente codificada usando o m�todo decodeBase64 e
			    // passando o byte[] da string codificada
			    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
			    // Converte o byte[] decodificado de volta para a string original
			    String decodedString = new String(decoded);
			    usuario.setSenha(decodedString);
			    
				cliente.setUsuario(usuario);
				
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_Nasc"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCdSistema(rs.getString("cd_sistema"));
				cliente.setStatus(rs.getString("status"));
				cliente.setDtCadastro(rs.getString("dt_cadastro"));
				cliente.setTipo(rs.getString("tipo"));
				
				// adicionando o objeto � lista
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Cliente do tipo CLIENTE
	
	
	/**
	 * Metodo para Listar o ultimo codigo do Cliente cadastrado no sistema
	 * @param entidade
	 * @return
	 */
	public List<Cliente> consultarUltimoCodigoSistemaCadastrado (){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cliente WHERE cd_sistema=(SELECT max(cd_sistema) FROM cliente)");
			ResultSet rs = stmt.executeQuery();
			
			List<Cliente> clientes = new ArrayList<>();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				// descriptografando a senha que vem do banco,
				// para n�o acusar erro na Strategy "ValidarSenhaIgualCliente",
				// para poder validar se a senha esta com letra minuscula e maiscula
				String senhaCriptografada = usuario.getSenha();
			    // Decodifica uma string anteriormente codificada usando o m�todo decodeBase64 e
			    // passando o byte[] da string codificada
			    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
			    // Converte o byte[] decodificado de volta para a string original
			    String decodedString = new String(decoded);
			    usuario.setSenha(decodedString);
			    
				cliente.setUsuario(usuario);
				
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_Nasc"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCdSistema(rs.getString("cd_sistema"));
				cliente.setStatus(rs.getString("status"));
				cliente.setDtCadastro(rs.getString("dt_cadastro"));
				cliente.setTipo(rs.getString("tipo"));
				
				// adicionando o objeto � lista
				clientes.add(cliente);
			}
				
			rs.close();
			stmt.close();
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar o ultimo codigo do Cliente cadastrado no sistema
	
	
	/**
	 * Metodo para Listar os Clientes pela Pesquisa por Filtro
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultarClientePesquisaByFiltro (EntidadeDominio entidade, String Parametro){
		openConnection();
		try {
			List<EntidadeDominio> clientes = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from cliente where nome LIKE ? and tipo='cliente'");
			stmt.setString(1, "%" + Parametro + "%");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Cliente
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				// descriptografando a senha que vem do banco,
				// para n�o acusar erro na Strategy "ValidarSenhaIgualCliente",
				// para poder validar se a senha esta com letra minuscula e maiscula
				String senhaCriptografada = usuario.getSenha();
			    // Decodifica uma string anteriormente codificada usando o m�todo decodeBase64 e
			    // passando o byte[] da string codificada
			    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
			    // Converte o byte[] decodificado de volta para a string original
			    String decodedString = new String(decoded);
			    usuario.setSenha(decodedString);
			    
				cliente.setUsuario(usuario);
				
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_Nasc"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCdSistema(rs.getString("cd_sistema"));
				cliente.setStatus(rs.getString("status"));
				cliente.setDtCadastro(rs.getString("dt_cadastro"));
				cliente.setTipo(rs.getString("tipo"));
				
				// adicionando o objeto � lista
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar os Clientes pela Pesquisa por Filtro
	
}
