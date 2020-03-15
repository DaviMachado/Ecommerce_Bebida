package com.les.bebida.core.dominio;

/**
 * Classe para representar o Bolo
 * @author Davi Rodrigues
 * @date 23/10/2019
 */
public class Bolo extends EntidadeDominio {
	private String nome;
	private String sabor;
	private float preco;
	private char tamanho;
	private String observavao;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSabor() {
		return sabor;
	}
	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public char getTamanho() {
		return tamanho;
	}
	public void setTamanho(char tamanho) {
		this.tamanho = tamanho;
	}
	public String getObservavao() {
		return observavao;
	}
	public void setObservavao(String observavao) {
		this.observavao = observavao;
	}
}
