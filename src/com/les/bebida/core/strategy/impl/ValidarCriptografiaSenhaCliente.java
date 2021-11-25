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
 * @date 21/11/2021
 */
// link de exemplo: https://www.devmedia.com.br/como-funciona-a-criptografia-hash-em-java/31139
public class ValidarCriptografiaSenhaCliente implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		// se o "alteraCliente" for igual a 1, executa essa regra
		if(cliente.getAlteraCliente().contentEquals("1")) {
			if (cliente.getUsuario().getSenha() == null || cliente.getUsuario().getSenha().equals("")) {
				return ("Error ao Criptografar Senha Cliente.");
			}
			else {
				String senhaCriptografada = cliente.getUsuario().getSenha();
				
				// criptografando a senha
				cliente.getUsuario().setSenha(Base64.encodeBase64String(senhaCriptografada.getBytes()));
				
				return null;
			}
		}
		
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

		// se não, o "alteraCliente" é igual a 0, e não executa essa regra,
		// solução provisoria para poder editar um cliente atraves do arquivo de listagem do mesmo,
		// pois quando tentava editar um cliente pela listagem, ele não abria a tela de edição, pois caia nessa validação
		else {
			return null;
		}
	}

}
