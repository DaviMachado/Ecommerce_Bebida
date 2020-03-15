package com.les.bebida.util.teste;

import java.sql.Connection;
import java.sql.SQLException;

import com.les.bebida.util.Conexao;

/**
 * Classe para testar a conexão com o banco de dados
 * @author Davi Rodrigues
 * @date 04/11/2019
 */
public class TestaConexao {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection connection = new Conexao().getConnection();
		System.out.println("Conexão aberta!");
		connection.close();
	}
	
}
