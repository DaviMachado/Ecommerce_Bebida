package com.les.bebida.core.strategy.impl;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar se as senhas do Login são iguais (Usuario),
 * e validar se a quantidade de senhas digitas são no minimo 8 caracteres
 * @author Davi Rodrigues
 * @date 06/10/2021
 */
public class ValidarSenhaIgual implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Usuario usuario = (Usuario) entidade;
		
		if (usuario.getSenha() == null || usuario.getSenha().equals("")) {
			return ("Favor insira uma senha.");
		}
		else if (usuario.getSenha().length() < 8) {
			return ("Favor insira uma senha com no minimo 8 caracteres.");
		}
		else if (usuario.getConfirmarSenha() == null || usuario.getConfirmarSenha().equals("")) {
			return ("Favor insira uma confirmar senha.");
		}
		else if (usuario.getConfirmarSenha().length() < 8) {
			return ("Favor insira uma confirmar senha com no minimo 8 caracteres.");
		}
		else if (!usuario.getSenha().matches(".*[A-Z].*")) {
			return ("Favor insira pelo menos uma letra MAIÚSCULA na senha.");
		}
		else if (!usuario.getSenha().matches(".*[a-z].*")) {
			return ("Favor insira pelo menos uma letra MINÚSCULA na senha.");
		}
		else if (!usuario.getSenha().matches(".*\\d.*")) {
			return ("Favor insira pelo menos um NÚMERO na senha.");
		}
		// ".*[@#$%^&+=].*" (<- funcionou)
		// []{}/-\ (<- deu erro se colocasse algum desses caracteres)
		 if (!usuario.getSenha().matches(".*[~´`_!@#$%^&*()+|=.,;:\"\'<>?].*")) {
			return ("Favor insira pelo menos um CARACTERE ESPECIAL na senha.");
		}
		else if (!usuario.getSenha().equals(usuario.getConfirmarSenha())) {
			return ("As senhas digitadas não se correspondem.");
		}
		else {
			return null;
		}
	}
	
}
