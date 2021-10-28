package com.les.bebida.core.dominio;

/**
 * Classe para representar o Gr�fico da An�lise
 * @author Davi Rodrigues
 * @date 27/10/2021
 */
public class GraficoAnalise extends EntidadeDominio {
	private Produto produto;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
