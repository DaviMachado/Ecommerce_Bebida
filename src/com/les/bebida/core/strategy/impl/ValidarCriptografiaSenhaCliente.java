package com.les.bebida.core.strategy.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar a criptografia da senha do Cliente
 * @author Davi Rodrigues
 * @date 19/11/2021
 */
// link de exemplo: https://www.devmedia.com.br/como-funciona-a-criptografia-hash-em-java/31139
public class ValidarCriptografiaSenhaCliente implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		String senhaCriptografada = cliente.getUsuario().getSenha();
		
		// criptografando a senha
		cliente.getUsuario().setSenha(Base64.encodeBase64String(senhaCriptografada.getBytes()));
		
		// OUTRA FORMA DE CRIPTOGRAFAR A SENHA
//		try {
//			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
//			byte messageDigestSenha[] = algorithm.digest(cliente.getUsuario().getSenha().getBytes("UTF-8"));
//			
//			StringBuilder hexStringSenha = new StringBuilder();
//		     for (byte b : messageDigestSenha) {
//		              hexStringSenha.append(String.format("%02X", 0xFF & b));
//		     }
//		     
//		     String senhahex = hexStringSenha.toString();
//		     
//		     // salvando a senha criptografada no Cliente
//		     cliente.getUsuario().setSenha(senhahex);
//			
//		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return ("Erro de Exception na criação da criptografia da senha do Cliente!");
//		}

		return null;
	}

}
