package com.les.bebida.core.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.les.bebida.core.dao.IDAO;
import com.les.bebida.util.Conexao;

/**
 * Classe abstrata AbstractJdbcDAO,
 * para abrir a conexão com o BD.
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public abstract class AbstractJdbcDAO implements IDAO {
	
	protected Connection connection;
	
	protected void openConnection() {
        try {

            if (connection == null || connection.isClosed()) {
            	connection = Conexao.getConnection();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
