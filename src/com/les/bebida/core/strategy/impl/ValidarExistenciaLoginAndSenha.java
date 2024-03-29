package com.les.bebida.core.strategy.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.les.bebida.core.dao.impl.LoginDAO;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar a existencia do Usuario pelo Login e Senha,
 * e validar se o mesmo esta ativo ou inativo
 * @author Davi Rodrigues
 * @date 19/11/2021
 */
//link de exemplo: https://www.devmedia.com.br/como-funciona-a-criptografia-hash-em-java/31139
public class ValidarExistenciaLoginAndSenha implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Usuario usuario = (Usuario) entidade;
		LoginDAO dao = new LoginDAO();
		
		String senhaCriptografada = usuario.getSenha();
		
		// criptografando a senha
        usuario.setSenha(Base64.encodeBase64String(senhaCriptografada.getBytes()));
		
		// OUTRA FORMA DE CRIPTOGRAFAR A SENHA
//		try {
//			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
//			byte messageDigestSenha[] = algorithm.digest(usuario.getSenha().getBytes("UTF-8"));
//			
//			StringBuilder hexStringSenha = new StringBuilder();
//		     for (byte b : messageDigestSenha) {
//		              hexStringSenha.append(String.format("%02X", 0xFF & b));
//		     }
//		     
//		     String senhahex = hexStringSenha.toString();
//		     
//		     // salvando a senha criptografada no Usu�rio
//		     usuario.setSenha(senhahex);
//			
//		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return ("Erro de Exception na verifica��o da criptografia da senha do Usu�rio!");
//		}
		
		// consulta se o Login e a Senha do Usu�rio est�o corretos para acessar o sistema
		List<Usuario> usuarios = dao.consultarUsuarioByLoginAndSenha(usuario.getLogin(), usuario.getSenha());
		
		if(usuarios.isEmpty()) {
			return ("Email e/ou senha inv�lidos. Por favor, tente novamente.");
		}
		else {
			if(usuarios.get(0).getStatus().equals("inativo")) {
				return ("Usu�rio provisoriamente Inativo!. Por favor, entre em contato com o Administrador!");
			}
			else {
				return null;
			}
		}
	}
	
}
