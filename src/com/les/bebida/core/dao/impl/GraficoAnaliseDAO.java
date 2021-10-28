package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.GraficoAnalise;
import com.les.bebida.core.dominio.ItemPedido;
import com.les.bebida.core.dominio.Produto;

public class GraficoAnaliseDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Gráfico da Análise
	 * @param entidade
	 */
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Salvar
	
	
	/**
	 * Metodo para alterar o Gráfico da Análise
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir o Gráfico da Análise
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para Consultar o Gráfico da Análise
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	} // Consultar
	
	
	/**
	 * Metodo para consultar os 3 Produtos mais vendidos com filtros de datas
	 */
	public List<GraficoAnalise> consultar3ProdutosMaisVendidos (String dtInicio, String dtFim) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select id_produto, sum(quantidade) as quantidade_vendido from pedido_item where dt_cadastro BETWEEN (?) AND (?) group by id_produto order by sum(quantidade) desc LIMIT 3;");
			stmt.setString(1, dtInicio);
			stmt.setString(2, dtFim);
			ResultSet rs = stmt.executeQuery();
			
			List<GraficoAnalise> itens_grafico = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Grafico Analise
				GraficoAnalise item_grafico = new GraficoAnalise();
				Produto produto = new Produto();
				
				produto.setId(rs.getString("id_produto"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade_vendido"));
				item_grafico.setProduto(produto);
				
				// adicionando o objeto à lista
				itens_grafico.add(item_grafico);
			}
				
			rs.close();
			stmt.close();
			return itens_grafico;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // consultar os 3 Produtos mais vendidos com filtros de datas
	
}
