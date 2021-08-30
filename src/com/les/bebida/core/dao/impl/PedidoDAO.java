package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Pedido;

public class PedidoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Pedido
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into pedido "+
				"(total_itens, total_frete, total_pedido, status, id_cliente, id_endereco, id_cartao, cupom, dt_cadastro)" +
				"values (?,?,?,?,?,?,?,?,?)";
		
		try {
			Pedido pedido = (Pedido) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1, pedido.getTotalItens());
			stmt.setString(2, pedido.getTotalFrete());
			stmt.setString(3, pedido.getTotalPedido());
			stmt.setString(4, pedido.getStatus());
			stmt.setString(5, pedido.getIdCliente());
			stmt.setString(6, pedido.getIdEndereco());
			stmt.setString(7, pedido.getIdCartao());
			stmt.setString(8, pedido.getCupom());
			stmt.setString(9, pedido.getDtCadastro());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	/**
	 * Metodo para alterar o Pedido
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir o Pedido
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para consultar o Pedido
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from pedido");
			ResultSet rs = stmt.executeQuery();
			
			List<EntidadeDominio> pedidos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				Pedido pedidoItem = new Pedido();
				
				pedidoItem.setId(rs.getString("id"));
				pedidoItem.setTotalItens(rs.getString("total_itens"));
				pedidoItem.setTotalFrete(rs.getString("total_frete"));
				pedidoItem.setTotalPedido(rs.getString("total_pedido"));
				pedidoItem.setStatus(rs.getString("status"));
				pedidoItem.setIdCliente(rs.getString("id_cliente"));
				pedidoItem.setIdEndereco(rs.getString("id_endereco"));
				pedidoItem.setIdCartao(rs.getString("id_cartao"));
				pedidoItem.setCupom(rs.getString("cupom"));
				pedidoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				pedidos.add(pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar
	
}
