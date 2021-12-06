package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dominio.Cupom;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.PesquisaByFiltro;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.Usuario;
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
			String nomeTabela = request.getParameter("NomeTabela");
			String nomeProduto = request.getParameter("nomeProduto");
			String nomeCliente = request.getParameter("nomeCliente");
			String statusPedido = request.getParameter("statusPedido");
			
			pesquisaByFiltro.setNomeTabela(nomeTabela);
			pesquisaByFiltro.setNomeProduto(nomeProduto);
			pesquisaByFiltro.setNomeCliente(nomeCliente);
			pesquisaByFiltro.setStatusPedido(statusPedido);
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
				// foi utilizado o getEntidades do resultado para poder pegar os Filtros consultados
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o PesquisaByFiltro (pegando o primeiro indice de Entidade)
				PesquisaByFiltro filtros = (PesquisaByFiltro) entidades.get(0);
				
				String NomeTabela = request.getParameter("NomeTabela");
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				// salva na sessão o objeto "filtroByNomeProdutos", recebendo o valor do filtro de Produtos
				sessao.setAttribute("filtroByNomeProdutos", filtros.getProdutos());
				
				// salva na sessão o objeto "filtroByNomeClientes", recebendo o valor do filtro de Clientes
				sessao.setAttribute("filtroByNomeClientes", filtros.getClientes());
				
				// salva na sessão o objeto "filtroByPedidos", recebendo o valor do filtro de Pedidos
				sessao.setAttribute("filtroByPedidos", filtros.getPedidos());
				
				if (NomeTabela.equals("Produto")) {
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Pesquisa por Filtro acionada com sucesso!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/Home_Page_PesquisaByFiltro.jsp").forward(request, response);
				}
				else if (NomeTabela.equals("Cliente")) {
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Pesquisa por Filtro acionada com sucesso!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/lista-clientes-scriptletADMIN_PesquisaByFiltro.jsp").forward(request, response);
				}
				else if (NomeTabela.equals("PedidoAdmin")) {
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Pesquisa por Filtro acionada com sucesso!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/lista-todos-pedidos-scriptletADMIN_PesquisaByFiltro.jsp").forward(request, response);
				}
				else if (NomeTabela.equals("PedidoCliente")) {
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Pesquisa por Filtro acionada com sucesso!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/lista-pedidos-scriptletCLIENTE_PesquisaByFiltro.jsp").forward(request, response);
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
