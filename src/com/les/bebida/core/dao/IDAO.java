package com.les.bebida.core.dao;

import java.sql.SQLException;
import java.util.List;

import com.les.bebida.core.dominio.EntidadeDominio;;

/**
 * Interface IDAO
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public interface IDAO {
	
	public void salvar(EntidadeDominio entidade) throws SQLException;

    public void alterar(EntidadeDominio entidade) throws SQLException;

    public void excluir(EntidadeDominio entidade) throws SQLException;

    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException;
}
