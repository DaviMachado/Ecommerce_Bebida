package com.les.bebida.core.dominio;

import java.util.Date;

/**
 * Classe entidade dominio
 * @author Davi Rodrigues
 * @date 10/03/2021
 */
public class EntidadeDominio implements IEntidade {
	private String id;
	private Date dtCadastro;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}
