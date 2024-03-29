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
 * @date 05/12/2021
 */
public class EnderecoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Endere�o
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into endereco "+
				"(apelido, cep, estado, cidade, numero, bairro, logradouro, tipo_residencia, " +
				"pais, tipo_endereco, observacao, dt_cadastro, id_cliente)" +
				"values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Endereco endereco = (Endereco) entidade;
			
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,endereco.getApelido());
			stmt.setString(2,endereco.getCep());
			stmt.setString(3, endereco.getEstado());
			stmt.setString(4,endereco.getCidade());
			stmt.setString(5,endereco.getNumero());
			stmt.setString(6, endereco.getBairro());
			stmt.setString(7,endereco.getLogradouro());
			stmt.setString(8,endereco.getTipoResidencia());
			stmt.setString(9, endereco.getPais());
			stmt.setString(10, endereco.getTipo_Endereco());
			stmt.setString(11, endereco.getObservacao());
			stmt.setString(12, endereco.getDtCadastro());
			
			stmt.setString(13,endereco.getIdCliente());
			
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
					 "apelido=?, cep=?, estado=?, cidade=?, numero=?, bairro=?, logradouro=?, " +
					 "tipo_residencia=?, pais=?, tipo_endereco=?, observacao=?  where id=?";
		
		try {
			Endereco enderecoEntidade = (Endereco) entidade;
			
			// se tiver algo no "alteraEndereco", altera o endere�o
			if (enderecoEntidade.getAlteraEndereco().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, enderecoEntidade.getApelido());
				stmt.setString(2, enderecoEntidade.getCep());
				stmt.setString(3, enderecoEntidade.getEstado());
				stmt.setString(4, enderecoEntidade.getCidade());
				stmt.setString(5, enderecoEntidade.getNumero());
				stmt.setString(6, enderecoEntidade.getBairro());
				stmt.setString(7, enderecoEntidade.getLogradouro());
				stmt.setString(8, enderecoEntidade.getTipoResidencia());
				stmt.setString(9, enderecoEntidade.getPais());
				stmt.setString(10, enderecoEntidade.getTipo_Endereco());
				stmt.setString(11, enderecoEntidade.getObservacao());
				
				stmt.setString(12, enderecoEntidade.getId());
				
				stmt.execute();
				stmt.close();
			}
			// caso contr�rio, n�o faz altera��o e pesquisa um endere�o no banco,
			// e no EnderecoHelper, ir� ter outra verifica��o para poder chamar a JSP de edi��o do endere�o
			else {
				PreparedStatement stmt = connection.prepareStatement("select * from endereco where id=?");
				stmt.setString(1, enderecoEntidade.getId());
				ResultSet rs = stmt.executeQuery();
				
				List<Endereco> enderecos = new ArrayList<>();
				while (rs.next()) {
					// criando o objeto Endere�o
					Endereco enderecoItem = new Endereco();
					
					enderecoItem.setId(rs.getString("id"));
					enderecoItem.setApelido(rs.getString("apelido"));
					enderecoItem.setCep(rs.getString("cep"));
					enderecoItem.setEstado(rs.getString("estado"));
					enderecoItem.setCidade(rs.getString("cidade"));
					enderecoItem.setNumero(rs.getString("numero"));
					enderecoItem.setBairro(rs.getString("bairro"));
					enderecoItem.setLogradouro(rs.getString("logradouro"));
					enderecoItem.setTipoResidencia(rs.getString("tipo_residencia"));
					enderecoItem.setPais(rs.getString("pais"));
					enderecoItem.setTipo_Endereco(rs.getString("tipo_endereco"));
					enderecoItem.setObservacao(rs.getString("observacao"));
					enderecoItem.setDtCadastro(rs.getString("dt_cadastro"));
					
					enderecoItem.setIdCliente(rs.getString("id_cliente"));
					
					// adicionando o objeto � lista
					enderecos.add(enderecoItem);
				}
				
				rs.close();
				stmt.close();
				
				// salva o obejto do endereco pesquisado, para mandar para a tela de edi��o
				// salva como REFERENCIA para o mesmo objeto que veio como parametro
				enderecoEntidade.setEnderecoPesquisado(enderecos.get(0));
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
			Endereco enderecoEntidade = (Endereco) entidade;
			
			PreparedStatement stmt = connection.prepareStatement("delete from endereco where id=?");
			stmt.setString(1, enderecoEntidade.getId());
			stmt.executeUpdate();
			
			stmt = connection.prepareStatement("select * from endereco where id_cliente=?");
			stmt.setString(1, enderecoEntidade.getIdCliente());
			ResultSet rs = stmt.executeQuery();
			
			List<Endereco> enderecos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Endere�o
				Endereco enderecoItem = new Endereco();
				
				enderecoItem.setId(rs.getString("id"));
				enderecoItem.setApelido(rs.getString("apelido"));
				enderecoItem.setCep(rs.getString("cep"));
				enderecoItem.setEstado(rs.getString("estado"));
				enderecoItem.setCidade(rs.getString("cidade"));
				enderecoItem.setNumero(rs.getString("numero"));
				enderecoItem.setBairro(rs.getString("bairro"));
				enderecoItem.setLogradouro(rs.getString("logradouro"));
				enderecoItem.setTipoResidencia(rs.getString("tipo_residencia"));
				enderecoItem.setPais(rs.getString("pais"));
				enderecoItem.setTipo_Endereco(rs.getString("tipo_endereco"));
				enderecoItem.setObservacao(rs.getString("observacao"));
				enderecoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				enderecoItem.setIdCliente(rs.getString("id_cliente"));
				
				// adicionando o objeto � lista
				enderecos.add(enderecoItem);
			}
			
			rs.close();
			stmt.close();
			
			// salva como REFERENCIA os endere�os do cliente, para poder listar os endere�os de novo
			enderecoEntidade.setEnderecosCliente(enderecos);
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
			Endereco enderecoEntidade = (Endereco) entidade;
			Endereco novoEndereco = new Endereco();
			List<EntidadeDominio> listEnderecos = new ArrayList<>();
			
			PreparedStatement stmt = connection.prepareStatement("select * from endereco where id_cliente=?");
			stmt.setString(1, enderecoEntidade.getIdCliente());
			ResultSet rs = stmt.executeQuery();
			
			List<Endereco> enderecos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Endere�o
				Endereco enderecoItem = new Endereco();
				
				enderecoItem.setId(rs.getString("id"));
				enderecoItem.setApelido(rs.getString("apelido"));
				enderecoItem.setCep(rs.getString("cep"));
				enderecoItem.setEstado(rs.getString("estado"));
				enderecoItem.setCidade(rs.getString("cidade"));
				enderecoItem.setNumero(rs.getString("numero"));
				enderecoItem.setBairro(rs.getString("bairro"));
				enderecoItem.setLogradouro(rs.getString("logradouro"));
				enderecoItem.setTipoResidencia(rs.getString("tipo_residencia"));
				enderecoItem.setPais(rs.getString("pais"));
				enderecoItem.setTipo_Endereco(rs.getString("tipo_endereco"));
				enderecoItem.setObservacao(rs.getString("observacao"));
				enderecoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				enderecoItem.setIdCliente(rs.getString("id_cliente"));
				
				// adicionando o objeto � lista
				enderecos.add(enderecoItem);
			}
			
			novoEndereco.setEnderecosCliente(enderecos);
			
			listEnderecos.add(novoEndereco);
			
			rs.close();
			stmt.close();
			return listEnderecos;
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
				enderecoItem.setApelido(rs.getString("apelido"));
				enderecoItem.setCep(rs.getString("cep"));
				enderecoItem.setEstado(rs.getString("estado"));
				enderecoItem.setCidade(rs.getString("cidade"));
				enderecoItem.setNumero(rs.getString("numero"));
				enderecoItem.setBairro(rs.getString("bairro"));
				enderecoItem.setLogradouro(rs.getString("logradouro"));
				enderecoItem.setTipoResidencia(rs.getString("tipo_residencia"));
				enderecoItem.setPais(rs.getString("pais"));
				enderecoItem.setTipo_Endereco(rs.getString("tipo_endereco"));
				enderecoItem.setObservacao(rs.getString("observacao"));
				enderecoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
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
	
	
	/**
	 * Metodo para Listar todos os Endere�os pelo ID do Cliente
	 * @param entidade
	 * @return
	 */
	public List<Endereco> consultarEnderecoByIdCliente (String idCliente){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from endereco where id_cliente=?");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			List<Endereco> enderecos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Endere�o
				Endereco enderecoItem = new Endereco();
				
				enderecoItem.setId(rs.getString("id"));
				enderecoItem.setApelido(rs.getString("apelido"));
				enderecoItem.setCep(rs.getString("cep"));
				enderecoItem.setEstado(rs.getString("estado"));
				enderecoItem.setCidade(rs.getString("cidade"));
				enderecoItem.setNumero(rs.getString("numero"));
				enderecoItem.setBairro(rs.getString("bairro"));
				enderecoItem.setLogradouro(rs.getString("logradouro"));
				enderecoItem.setTipoResidencia(rs.getString("tipo_residencia"));
				enderecoItem.setPais(rs.getString("pais"));
				enderecoItem.setTipo_Endereco(rs.getString("tipo_endereco"));
				enderecoItem.setObservacao(rs.getString("observacao"));
				enderecoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
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
	} // Listar todos os Endere�os pelo ID do Cliente
	
}
