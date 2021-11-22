package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dao.impl.CupomDAO;
import com.les.bebida.core.dominio.Cupom;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.PesquisaByFiltro;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.VerificaCupom;
import com.les.bebida.view.helper.IViewHelper;

public class PesquisaByFiltroHelper implements IViewHelper {

	PesquisaByFiltro pesquisaByFiltro = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		pesquisaByFiltro = new PesquisaByFiltro();
		
		if (("CONSULTAR").equals(operacao)) {

		}
		
		else if (("SALVAR").equals(operacao)) {

		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return pesquisaByFiltro;
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
				String NomeTabela = request.getParameter("NomeTabela");
				String nomeProduto = request.getParameter("nomeProduto");
				String nomeCliente = request.getParameter("nomeCliente");
				
				if (NomeTabela.equals("Produto")) {
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Pesquisa por Filtro acionada com sucesso!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// pendura o "nomeProduto", que foi digitado pelo Usuário na requisição, para poder mandar para o arquivo .JSP
					request.setAttribute("nomeProduto", nomeProduto);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/Home_Page_PesquisaByFiltro.jsp").forward(request, response);
				}
				else if (NomeTabela.equals("Cliente")) {
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Pesquisa por Filtro acionada com sucesso!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// pendura o "nomeCliente", que foi digitado pelo Usuário na requisição, para poder mandar para o arquivo .JSP
					request.setAttribute("nomeCliente", nomeCliente);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/lista-clientes-scriptletADMIN_PesquisaByFiltro.jsp").forward(request, response);
				}
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {

		}
		
	}

}
