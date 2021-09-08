package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.ItemPedido;
import com.les.bebida.core.dominio.Produto;

public class ItemPedidoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar os Itens do Pedido
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into pedido_item "+
				"(id_produto, nome, valor_de_venda, quantidade, id_pedido, trocado, dt_cadastro)" +
				"values (?,?,?,?,?,?,?)";
		
		try {
			ItemPedido item_pedido = (ItemPedido) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1, item_pedido.getProduto().getId());
			stmt.setString(2, item_pedido.getProduto().getNome());
			stmt.setString(3, item_pedido.getProduto().getPrecoDeVenda());
			stmt.setString(4, item_pedido.getProduto().getQuantidadeSelecionada());
			stmt.setString(5, item_pedido.getIdPedido());
			stmt.setString(6, item_pedido.getTrocado());
			stmt.setString(7, item_pedido.getDtCadastro());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	/**
	 * Metodo para alterar os Itens do Pedido
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir os Itens do Pedido
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para consultar os Itens do Pedido
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		openConnection();
		try {
			ItemPedido item_pedido = (ItemPedido) entidade;
			PreparedStatement stmt = connection.prepareStatement("select * from pedido_item where id_pedido=?");
			stmt.setString(1, item_pedido.getIdPedido());
			ResultSet rs = stmt.executeQuery();
			
			List<EntidadeDominio> itens_pedido = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				ItemPedido item_pedidoItem = new ItemPedido();
				Produto produto = new Produto();
				
				item_pedidoItem.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoDeVenda(rs.getString("valor_de_venda"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade"));
				item_pedidoItem.setProduto(produto);
				
				item_pedidoItem.setIdPedido(rs.getString("id_pedido"));
				item_pedidoItem.setTrocado(rs.getString("trocado"));
				item_pedidoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				itens_pedido.add(item_pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return itens_pedido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar
	
	
	/**
	 * Metodo para consultar o Item do Pedido por ID
	 */
	public List<ItemPedido> consultarItemPedidoById (String idItemPedido) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from pedido_item where id=?");
			stmt.setString(1, idItemPedido);
			ResultSet rs = stmt.executeQuery();
			
			List<ItemPedido> itens_pedido = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				ItemPedido item_pedidoItem = new ItemPedido();
				Produto produto = new Produto();
				
				item_pedidoItem.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoDeVenda(rs.getString("valor_de_venda"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade"));
				item_pedidoItem.setProduto(produto);
				
				item_pedidoItem.setIdPedido(rs.getString("id_pedido"));
				item_pedidoItem.setTrocado(rs.getString("trocado"));
				item_pedidoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				itens_pedido.add(item_pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return itens_pedido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar o Item do Pedido por ID
	
	
	/**
	 * Metodo para alterar a trocação do Item do Pedido
	 * @param entidade
	 */
	public void alterarTrocacaoItemPedido (String idItemPedido) {
		openConnection();
		
		String sql = "update pedido_item set " +
					 "trocado='sim' " +
					 "where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, idItemPedido);
			
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar trocação do Item do Pedido
	
	
	/**
	 * Metodo para consultar se exite algum Item do Pedido que esteja com o status de trocado como "nao"
	 */
	public List<ItemPedido> consultarItemPedidoByIdPedidoAndTrocadoNao (String idPedido) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from pedido_item where id_pedido=? and trocado='nao'");
			stmt.setString(1, idPedido);
			ResultSet rs = stmt.executeQuery();
			
			List<ItemPedido> itens_pedido = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				ItemPedido item_pedidoItem = new ItemPedido();
				Produto produto = new Produto();
				
				item_pedidoItem.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoDeVenda(rs.getString("valor_de_venda"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade"));
				item_pedidoItem.setProduto(produto);
				
				item_pedidoItem.setIdPedido(rs.getString("id_pedido"));
				item_pedidoItem.setTrocado(rs.getString("trocado"));
				item_pedidoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				itens_pedido.add(item_pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return itens_pedido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar se exite algum Item do Pedido que esteja com o status de trocado como "nao"
	
}
