package com.les.bebida.core.dominio;

/**
 * Classe entidade dominio
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public class EntidadeDominio implements IEntidade {
	private String id;
	private String dt_cadastro;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDt_cadastro() {
		return dt_cadastro;
	}
	public void setDt_cadastro(String dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}
}
