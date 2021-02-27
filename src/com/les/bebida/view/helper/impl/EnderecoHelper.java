package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.view.helper.IViewHelper;

public class EnderecoHelper implements IViewHelper {
	
	Endereco endereco = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// Verifica qual opera��o do bot�o foi acionada
		String operacao = request.getParameter("operacao");
		
        String cep = null;
        String cidade = null;
        String logradouro = null;
        String numero = null;
        String bairro = null;
        String complemento = null;
        String estado = null;
		
		if (("CONSULTAR").equals(operacao)) {
			endereco = new Endereco();
		}
		
		else if (("SALVAR").equals(operacao)) {
			endereco = new Endereco();
			
			// Atributos da classe endere�o
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			logradouro = request.getParameter("logradouro");
			numero = request.getParameter("numero");
			bairro = request.getParameter("bairro");
			complemento = request.getParameter("complemento");
			estado = request.getParameter("selecioneEstado");
			
			// Atribuindo os valores capturados do HTML para o endere�o
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setComplemento(complemento);
			endereco.setEstado(estado);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			endereco = new Endereco();
			
			// Atributos da classe endere�o
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			logradouro = request.getParameter("logradouro");
			numero = request.getParameter("numero");
			bairro = request.getParameter("bairro");
			complemento = request.getParameter("complemento");
			estado = request.getParameter("selecioneEstado");
			
			// Atribuindo os valores capturados do HTML para o endere�o
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setComplemento(complemento);
			endereco.setEstado(estado);
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			endereco = new Endereco();
			
			cep = request.getParameter("cep");
			
			endereco.setCep(cep);
		}
		
		return endereco;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual opera��o do bot�o foi acionada
		String operacao = request.getParameter("operacao");
				
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// Redireciona para o arquivo .jsp
				//request.getRequestDispatcher("JSP/lista-clientes-scriptlet.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
				writer.println(resultado.getMensagem());
				System.out.println("ERRO PARA CONSULTAR!");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				writer.println("<h1>Endere�o Salvo com sucesso!</h1>");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
			else {
				// mostra as mensagens de ERRO se houver
				writer.println(resultado.getMensagem());
				System.out.println("ERRO PARA SALVAR!");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				writer.println("<h1>Endere�o Alterado com sucesso!</h1>");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			} 
			else {
				// mostra as mensagens de ERRO se houver
				writer.println(resultado.getMensagem());
				System.out.println("ERRO PARA ALTERAR!");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				writer.println("<h1>Endere�o Removido com sucesso!</h1>");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			} 
			else {
				// mostra as mensagens de ERRO se houver
				writer.println(resultado.getMensagem());
				System.out.println("ERRO PARA EXCLUIR!");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
		}
	}

}
