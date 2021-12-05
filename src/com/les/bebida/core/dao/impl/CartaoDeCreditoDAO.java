package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.les.bebida.core.dominio.Bandeira;
import com.les.bebida.core.dominio.CartaoDeCredito;
import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Usuario;

public class CartaoDeCreditoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Cartao de Credito
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into cartao_de_credito "+ 
				"(nome, num_cartao, cd_seguranca, dt_validade, preferencial, id_cliente, id_bandeira, dt_cadastro)" +
				"values (?,?,?,?,?,?,?,?)";
		
		try {
			CartaoDeCredito cartao = (CartaoDeCredito) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,cartao.getNome());
			stmt.setString(2,cartao.getNum_cartao());
			stmt.setString(3,cartao.getCod_seguranca());
			stmt.setString(4, cartao.getDt_validade());
			stmt.setString(5, cartao.getFlgPreferencial());
			stmt.setString(6, cartao.getIdCliente());
			stmt.setString(7,cartao.getIdBandeira());
			stmt.setString(8, cartao.getDtCadastro());
			
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
		
		String sql = "update cartao_de_credito set " +
					 "nome=?, num_cartao=?, cd_seguranca=?, dt_validade=?, preferencial=?, id_bandeira=? where id=?";
		
		BandeiraDAO bandeiraDAO = new BandeiraDAO();
		
		try {
			CartaoDeCredito cartaoEntidade = (CartaoDeCredito) entidade;
			
			// se tiver algo no "alteraCartao", altera o cartao de credito
			if(cartaoEntidade.getAlteraCartao().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, cartaoEntidade.getNome());
				stmt.setString(2, cartaoEntidade.getNum_cartao());
				stmt.setString(3, cartaoEntidade.getCod_seguranca());
				stmt.setString(4, cartaoEntidade.getDt_validade());
				stmt.setString(5, cartaoEntidade.getFlgPreferencial());
				stmt.setString(6, cartaoEntidade.getIdBandeira());
				stmt.setString(7, cartaoEntidade.getId());
				
				stmt.execute();
				stmt.close();
			}
			// caso contrário, não faz alteração e pesquisa todos os dados do Objeto cartao no banco,
			// e no CartaoDeCreditoHelper, irá ter outra verificação para poder chamar a JSP de edição do cartao de credito
			else {
				PreparedStatement stmt = connection.prepareStatement("select * from cartao_de_credito where id=?");
				stmt.setString(1, cartaoEntidade.getId());
				ResultSet rs = stmt.executeQuery();
				
				List<CartaoDeCredito> cartoes = new ArrayList<>();
				while (rs.next()) {
					// criando o objeto Cartao de Credito
					CartaoDeCredito cartaoItem = new CartaoDeCredito();
					
					cartaoItem.setId(rs.getString("id"));
					cartaoItem.setNome(rs.getString("nome"));
					cartaoItem.setNum_cartao(rs.getString("num_cartao"));
					cartaoItem.setCod_seguranca(rs.getString("cd_seguranca"));
					cartaoItem.setDt_validade(rs.getString("dt_validade"));
					cartaoItem.setFlgPreferencial(rs.getString("preferencial"));
					cartaoItem.setIdCliente(rs.getString("id_cliente"));
					cartaoItem.setIdBandeira(rs.getString("id_bandeira"));
					cartaoItem.setDtCadastro(rs.getString("dt_cadastro"));
					
					// adicionando o objeto à lista
					cartoes.add(cartaoItem);
				}
				// se tiver cartões, será pesquisa os respectivos nome das Bandeiras do mesmo
				if (cartoes.size() > 0) {
					for(CartaoDeCredito cartao: cartoes) {
						List<Bandeira> nome_bandeira = new ArrayList<>();
						
						// busca o nome da bandeira pelo ID da Bandeira que esta vinculado no cartão
						nome_bandeira = bandeiraDAO.consultarBandeiraById(cartao.getIdBandeira());
						
						// salva o nome da bandeira no cartão
						cartao.setNomeBandeira(nome_bandeira.get(0).getNome());
					}
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
				
				rs.close();
				stmt.close();
				
				// salva o obejto do cartao pesquisado, para mandar para a tela de edição
				// salva como REFERENCIA para o mesmo obejto que veio como parametro
				cartaoEntidade.setCartaoPesquisado(cartoes.get(0));
				cartaoEntidade.setBandeiras(bandeiras);
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
			CartaoDeCredito cartaoEntidade = (CartaoDeCredito) entidade;
			
			BandeiraDAO bandeiraDAO = new BandeiraDAO();
			
			// Exclui o cartao de credito
			PreparedStatement stmt = connection.prepareStatement("delete from cartao_de_credito where id=?");
			stmt.setString(1, cartaoEntidade.getId());
			stmt.executeUpdate();
			
			stmt = connection.prepareStatement("select * from cartao_de_credito where id_cliente=?");
			stmt.setString(1, cartaoEntidade.getIdCliente());
			ResultSet rs = stmt.executeQuery();
			
			List<CartaoDeCredito> cartoes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cartao de Credito
				CartaoDeCredito cartaoItem = new CartaoDeCredito();
				
				cartaoItem.setId(rs.getString("id"));
				cartaoItem.setNome(rs.getString("nome"));
				cartaoItem.setNum_cartao(rs.getString("num_cartao"));
				cartaoItem.setCod_seguranca(rs.getString("cd_seguranca"));
				cartaoItem.setDt_validade(rs.getString("dt_validade"));
				cartaoItem.setFlgPreferencial(rs.getString("preferencial"));
				cartaoItem.setIdCliente(rs.getString("id_cliente"));
				cartaoItem.setIdBandeira(rs.getString("id_bandeira"));
				cartaoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cartoes.add(cartaoItem);
			}
			
			// se tiver cartões, será pesquisa os respectivos nome das Bandeiras do mesmo
			if (cartoes.size() > 0) {
				for(CartaoDeCredito cartao: cartoes) {
					List<Bandeira> nome_bandeira = new ArrayList<>();
					
					// busca o nome da bandeira pelo ID da Bandeira que esta vinculado no cartão
					nome_bandeira = bandeiraDAO.consultarBandeiraById(cartao.getIdBandeira());
					
					// salva o nome da bandeira no cartão
					cartao.setNomeBandeira(nome_bandeira.get(0).getNome());
				}
			}
				
			rs.close();
			stmt.close();
			
			// salva como REFERENCIA os cartões do cliente, para poder listar os cartões de novo
			cartaoEntidade.setCartoesCliente(cartoes);
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
			CartaoDeCredito cartaoEntidade = (CartaoDeCredito) entidade;
			CartaoDeCredito novoCartao = new CartaoDeCredito();
			List<EntidadeDominio> listCartoes = new ArrayList<>();
			
			BandeiraDAO bandeiraDAO = new BandeiraDAO();
			
			PreparedStatement stmt = connection.prepareStatement("select * from cartao_de_credito where id_cliente=?");
			stmt.setString(1, cartaoEntidade.getIdCliente());
			ResultSet rs = stmt.executeQuery();
			
			List<CartaoDeCredito> cartoes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cartao de Credito
				CartaoDeCredito cartaoItem = new CartaoDeCredito();
				
				cartaoItem.setId(rs.getString("id"));
				cartaoItem.setNome(rs.getString("nome"));
				cartaoItem.setNum_cartao(rs.getString("num_cartao"));
				cartaoItem.setCod_seguranca(rs.getString("cd_seguranca"));
				cartaoItem.setDt_validade(rs.getString("dt_validade"));
				cartaoItem.setFlgPreferencial(rs.getString("preferencial"));
				cartaoItem.setIdCliente(rs.getString("id_cliente"));
				cartaoItem.setIdBandeira(rs.getString("id_bandeira"));
				cartaoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cartoes.add(cartaoItem);
			}
			
			// se tiver cartões, será pesquisa os respectivos nome das Bandeiras do mesmo
			if (cartoes.size() > 0) {
				for(CartaoDeCredito cartao: cartoes) {
					List<Bandeira> nome_bandeira = new ArrayList<>();
					
					// busca o nome da bandeira pelo ID da Bandeira que esta vinculado no cartão
					nome_bandeira = bandeiraDAO.consultarBandeiraById(cartao.getIdBandeira());
					
					// salva o nome da bandeira no cartão
					cartao.setNomeBandeira(nome_bandeira.get(0).getNome());
				}
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
			
			novoCartao.setCartoesCliente(cartoes);
			novoCartao.setBandeiras(bandeiras);
			
			listCartoes.add(novoCartao);
			
			rs.close();
			stmt.close();
			return listCartoes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
	
	/**
	 * Metodo para Listar o Cartao de Credito por ID
	 * @param entidade
	 * @return
	 */
	public List<CartaoDeCredito> consultarCartaoDeCreditoById (String idCartaoDeCredito){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cartao_de_credito where id=?");
			stmt.setString(1, idCartaoDeCredito);
			ResultSet rs = stmt.executeQuery();
			
			List<CartaoDeCredito> cartoes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cartao de Credito
				CartaoDeCredito cartaoItem = new CartaoDeCredito();
				
				cartaoItem.setId(rs.getString("id"));
				cartaoItem.setNome(rs.getString("nome"));
				cartaoItem.setNum_cartao(rs.getString("num_cartao"));
				cartaoItem.setCod_seguranca(rs.getString("cd_seguranca"));
				cartaoItem.setDt_validade(rs.getString("dt_validade"));
				cartaoItem.setFlgPreferencial(rs.getString("preferencial"));
				cartaoItem.setIdCliente(rs.getString("id_cliente"));
				cartaoItem.setIdBandeira(rs.getString("id_bandeira"));
				cartaoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cartoes.add(cartaoItem);
			}
			rs.close();
			stmt.close();
			return cartoes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Cartao de Credito por ID
	
	
	/**
	 * Metodo para Listar todos os Cartões de Credito pelo ID do Cliente
	 * @param entidade
	 * @return
	 */
	public List<CartaoDeCredito> consultarCartaoDeCreditoByIdCliente (String idCliente){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from cartao_de_credito where id_cliente=?");
			stmt.setString(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			List<CartaoDeCredito> cartoes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cartao de Credito
				CartaoDeCredito cartaoItem = new CartaoDeCredito();
				
				cartaoItem.setId(rs.getString("id"));
				cartaoItem.setNome(rs.getString("nome"));
				cartaoItem.setNum_cartao(rs.getString("num_cartao"));
				cartaoItem.setCod_seguranca(rs.getString("cd_seguranca"));
				cartaoItem.setDt_validade(rs.getString("dt_validade"));
				cartaoItem.setFlgPreferencial(rs.getString("preferencial"));
				cartaoItem.setIdCliente(rs.getString("id_cliente"));
				cartaoItem.setIdBandeira(rs.getString("id_bandeira"));
				cartaoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cartoes.add(cartaoItem);
			}
			rs.close();
			stmt.close();
			return cartoes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar todos os Cartões de Credito pelo ID do Cliente
	
}
