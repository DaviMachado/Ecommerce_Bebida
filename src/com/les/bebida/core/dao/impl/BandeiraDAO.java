package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.Bandeira;
import com.les.bebida.core.dominio.EntidadeDominio;

public class BandeiraDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar a Bandeira
	 * @param entidade
	 */
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Salvar
	
	
	/**
	 * Metodo para alterar a Bandeira
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir a Bandeira
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para Consultar a Bandeira
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	} // Consultar
	
	
	/**
	 * Metodo para Consultar todas as Bandeiras
	 * @param
	 */
	public List<Bandeira> consultar() throws SQLException {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from bandeira");
			ResultSet rs = stmt.executeQuery();
			
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
			return bandeiras;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Consultar todas as Bandeiras
	
	
	/**
	 * Metodo para Consultar a Bandeira por ID
	 * @param
	 */
	public List<Bandeira> consultarBandeiraById (String idBandeira){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from bandeira where id=?");
			stmt.setString(1, idBandeira);
			ResultSet rs = stmt.executeQuery();
			
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
			return bandeiras;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Consultar Bandeira por ID
	
}
