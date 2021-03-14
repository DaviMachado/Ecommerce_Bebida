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
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String id = null;
        String cep = null;
        String cidade = null;
        String logradouro = null;
        String numero = null;
        String bairro = null;
        String complemento = null;
        String estado = null;
        String idCliente = null;
		
		if (("CONSULTAR").equals(operacao)) {
			endereco = new Endereco();
			
			idCliente = request.getParameter("idCliente");
			
			endereco.setIdCliente(idCliente);
		}
		
		else if (("SALVAR").equals(operacao)) {
			endereco = new Endereco();
			
			// Atributos da classe endereço
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			logradouro = request.getParameter("logradouro");
			numero = request.getParameter("numero");
			bairro = request.getParameter("bairro");
			complemento = request.getParameter("complemento");
			estado = request.getParameter("selecioneEstado");
			idCliente = request.getParameter("idCliente");
			
			// Atribuindo os valores capturados do HTML para o endereço
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setComplemento(complemento);
			endereco.setEstado(estado);
			endereco.setIdCliente(idCliente);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			endereco = new Endereco();
			
			// Atributos da classe endereço
			id = request.getParameter("idEndereco");
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			logradouro = request.getParameter("logradouro");
			numero = request.getParameter("numero");
			bairro = request.getParameter("bairro");
			complemento = request.getParameter("complemento");
			estado = request.getParameter("selecioneEstado");
			idCliente = request.getParameter("idCliente");
			
			// Atribuindo os valores capturados do HTML para o endereço
			endereco.setId(id);
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setComplemento(complemento);
			endereco.setEstado(estado);
			endereco.setIdCliente(idCliente);
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			endereco = new Endereco();
			
			id = request.getParameter("idEndereco");
			
			endereco.setId(id);
		}
		
		return endereco;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
				
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				String idCliente = request.getParameter("idCliente");
				
				// pendura o "idCliente" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("idCliente", idCliente);
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/lista-enderecos-scriptlet.jsp").forward(request, response);
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
				writer.println("<h1>Endereço Salvo com sucesso!</h1>");
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
				String idCliente = request.getParameter("idCliente");
				String idEndereco = request.getParameter("idEndereco");
				
				// chama o arquivo .JSP para edição do endereço
				if (idCliente == null) {					
					// pendura o "idEndereco" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("idEndereco", idEndereco);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/editar_endereco.jsp").forward(request, response);
				}
				// caso contrário, esta alterando um endereço, então mostrar a mensagem de sucesso
				else {
					writer.println("<h1>Endereço Alterado com sucesso!</h1>");
					writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
				}
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
				String idCliente = request.getParameter("idCliente");
				
				// pendura o "idCliente" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("idCliente", idCliente);
				
				// Redireciona para o arquivo .jsp, para poder listar os endereços atualizados novamente
				request.getRequestDispatcher("JSP/lista-enderecos-scriptlet.jsp").forward(request, response);
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
