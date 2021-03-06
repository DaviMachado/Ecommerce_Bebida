package com.les.bebida.core.dominio;

import java.util.List;

import com.les.bebida.core.dominio.EntidadeDominio;;

/**
 * Classe resultado
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public class Resultado {
	private String mensagem;
    private List<EntidadeDominio> entidades;
    
    
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
}
