package com.les.bebida.core.strategy.impl;

import java.util.InputMismatchException;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.strategy.IStrategy;

/**
 * Classe para validar o campo CPF do cliente
 * @author Davi Rodrigues
 * @date 17/08/2021
 */
public class ValidarCPF implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		// se o "alteraCliente" for igual a 1, executa essa regra
		if(cliente.getAlteraCliente().contentEquals("1")) {
			if(cliente.getCpf() == null || cliente.getCpf().equals("")) {
				return ("Favor insira um CPF.");
			}
			else if (cliente.getCpf().length() < 11) {
				return ("Favor insira um CPF com no minimo 11 caracteres.");
			}
			else {
				String CPF = cliente.getCpf();
				
				// considera-se erro CPF's formados por uma sequencia de numeros iguais
		        if (CPF.equals("00000000000") ||
		            CPF.equals("11111111111") ||
		            CPF.equals("22222222222") || CPF.equals("33333333333") ||
		            CPF.equals("44444444444") || CPF.equals("55555555555") ||
		            CPF.equals("66666666666") || CPF.equals("77777777777") ||
		            CPF.equals("88888888888") || CPF.equals("99999999999") ||
		            (CPF.length() != 11))
		            return("CPF invalido !!!");
	
		        char dig10, dig11;
		        int sm, i, r, num, peso;
	
		        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		        try {
		        // Calculo do 1o. Digito Verificador
		            sm = 0;
		            peso = 10;
		            for (i=0; i<9; i++) {
		        // converte o i-esimo caractere do CPF em um numero:
		        // por exemplo, transforma o caractere '0' no inteiro 0
		        // (48 eh a posicao de '0' na tabela ASCII)
		            num = (int)(CPF.charAt(i) - 48);
		            sm = sm + (num * peso);
		            peso = peso - 1;
		            }
	
		            r = 11 - (sm % 11);
		            if ((r == 10) || (r == 11))
		                dig10 = '0';
		            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
	
		        // Calculo do 2o. Digito Verificador
		            sm = 0;
		            peso = 11;
		            for(i=0; i<10; i++) {
		            num = (int)(CPF.charAt(i) - 48);
		            sm = sm + (num * peso);
		            peso = peso - 1;
		            }
	
		            r = 11 - (sm % 11);
		            if ((r == 10) || (r == 11))
		                 dig11 = '0';
		            else dig11 = (char)(r + 48);
	
		            // Verifica se os digitos calculados conferem com os digitos informados.
		            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
		                 return(null);
		            else 
		            	return("CPF invalido !!!");
		        } catch (InputMismatchException erro) {
		        	return("CPF invalido !!!");
		        }
			}
		}
		// se n�o, o "alteraCliente" � igual a 0, e n�o executa essa regra,
		// solu��o provisoria para poder editar um cliente atraves do arquivo de listagem do mesmo,
		// pois quando tentava editar um cliente pela listagem, ele n�o abria a tela de edi��o, pois caia nessa valida��o
		else {
			return null;
		}
	}

}
