package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.view.helper.IViewHelper;

public class LoginHelper implements IViewHelper {

	Usuario usuario = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
        String login = null;
        String senha = null;
        String confirmarSenha = null;
		String id = null;
        String nome = null;
        String telefone = null;
		
		if (("CONSULTAR").equals(operacao)) {
			usuario = new Usuario();
			
			// Atributos da classe Usuario
			login = request.getParameter("emailSouCadastrado");
			senha = request.getParameter("senhaSouCadastrado");
			
			// Atribuindo os valores capturados do HTML para o Usuario
			usuario.setLogin(login);
			usuario.setSenha(senha);
		}
		
		else if (("SALVAR").equals(operacao)) {
			usuario = new Usuario();
			
			// Atributos da classe Usuario
			login = request.getParameter("emailNaoSouCadastrado");
			senha = request.getParameter("senhaNaoSouCadastrado");
			confirmarSenha = request.getParameter("confirmarSenhaNaoSouCadastrado");
			nome = request.getParameter("nomeNaoSouCadastrado");
			telefone = request.getParameter("telefoneNaoSouCadastrado");
			
			// Atribuindo os valores capturados do HTML para o Usuario
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setConfirmarSenha(confirmarSenha);	
			usuario.setNome(nome);
			usuario.setTelefone(telefone);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return usuario;
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
				// Redireciona para o arquivo .HTML
				request.getRequestDispatcher("HTML/Home_Page.html").forward(request, response);
			} 
			else {
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/login.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Cadastro salvo com sucesso!");
				
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/login.jsp").forward(request, response);
			}
			else {				
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/login.jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
	}

}
