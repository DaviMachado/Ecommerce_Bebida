package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.view.helper.IViewHelper;

public class EstoqueHelper implements IViewHelper {

	Estoque estoque = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String id_produto = null;
        String tipo = null;
        String quantidade_entrada_saida = null;
        String valor_custo = null;
		String fornecedor = null;
        String dt_entrada = null;
		
		if (("CONSULTAR").equals(operacao)) {
			estoque = new Estoque();
			
			// capturando os valores do HTML e passando para o Estoque
			id_produto = request.getParameter("selecioneProduto");
			
			// Atribuindo os valores capturados do HTML para o Estoque
			estoque.setIdProduto(id_produto);
		}
		
		else if (("SALVAR").equals(operacao)) {
			estoque = new Estoque();
			
			// capturando os valores do HTML e passando para o Estoque
			id_produto = request.getParameter("selecioneProduto");
			tipo = request.getParameter("tipo");
			quantidade_entrada_saida = request.getParameter("quantidade_entrada_saida");
			valor_custo = request.getParameter("valor_custo");
			fornecedor = request.getParameter("fornecedor");
			dt_entrada = request.getParameter("dt_entrada");
			
			// Atribuindo os valores capturados do HTML para o Estoque
			estoque.setIdProduto(id_produto);
			estoque.setTipo(tipo);
			estoque.setQuantidadeEntradaSaida(quantidade_entrada_saida);
			estoque.setValorCusto(valor_custo);
			estoque.setFornecedor(fornecedor);
			estoque.setDtEntrada(dt_entrada);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return estoque;
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
				String id_produto = request.getParameter("selecioneProduto");
				
				// pendura o "idProduto" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("idProduto", id_produto);
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/lista-estoque-produto-scriptlet.jsp").forward(request, response);
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
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Cadastro do Estoque salvo com sucesso!");
				
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
	}

}
