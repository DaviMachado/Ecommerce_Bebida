package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.CartaoDeCredito;
import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;

public class CartaoDeCreditoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Cartao de Credito
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cartao_de_credito "+ 
				"(nome, num_cartao, bandeira, cd_seguranca, dt_validade, flg_preferencial, dt_cadastro, id_cliente)" +
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
		
		String sql = "update cliente set " + // login=?, senha=?,
					 "nome=?, cpf=?, dt_nasc=?, cd_cliente=?, sexo=?, telefone=? where id=?";
		
		try {
			Cliente cliente = (Cliente) entidade;
//			Usuario usuario = cliente.getUsuario();
			
			// se tiver algo no "alteraCliente", altera o cliente
			if(cliente.getAlteraCliente().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
//				stmt.setString(1, usuario.getLogin());
//				stmt.setString(2, usuario.getSenha());
				stmt.setString(1, cliente.getNome());
				stmt.setString(2, cliente.getCpf());
				stmt.setString(3, cliente.getDt_nasc());
				stmt.setString(4, cliente.getCdCliente());
				stmt.setString(5, cliente.getSexo());
				stmt.setString(6, cliente.getTelefone());
				stmt.setString(7, cliente.getId());
				
				stmt.execute();
				stmt.close();
			}
			// caso contrário, não faz alteração e somente fecha a conexão com o banco,
			// e no ClienteoHelper, irá ter outra verificação para poder chamar a JSP de edição do cliente ADMIN
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
			Cliente cliente = (Cliente) entidade;
			
			// Exclui os endereços relacionados com o cliente
			PreparedStatement stmt = connection.prepareStatement("delete from endereco where id_cliente=?");
			stmt.setString(1, cliente.getId());
			stmt.executeUpdate();
			
			// Exclui o cliente
			stmt = connection.prepareStatement("delete from cliente where id=?");
			stmt.setString(1, cliente.getId());
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
				cliente.setSexo(rs.getString("sexo"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setFlgAtivo(rs.getString("fl_ativo"));
				cliente.setDtCadastro(rs.getString("dt_cadastro"));
				cliente.setTipo(rs.getString("tipo"));
				
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
