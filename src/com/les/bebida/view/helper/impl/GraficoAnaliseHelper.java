package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dao.impl.GraficoAnaliseDAO;
import com.les.bebida.core.dao.impl.ProdutoDAO;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.GraficoAnalise;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.view.helper.IViewHelper;

public class GraficoAnaliseHelper implements IViewHelper {

	GraficoAnalise grafico = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		grafico = new GraficoAnalise();
		
		if (("CONSULTAR").equals(operacao)) {

		}
		
		else if (("SALVAR").equals(operacao)) {

		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return grafico;
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
				String dtInicio = request.getParameter("dtInicio");
				String dtFim = request.getParameter("dtFim");
				
				GraficoAnaliseDAO graficoDAO = new GraficoAnaliseDAO();
				ProdutoDAO produtoDAO = new ProdutoDAO();

				// busca os 3 produtos mais vendidos, conforme as datas selecionadas na tela como parametros
				List<GraficoAnalise> graficos = graficoDAO.consultar3ProdutosMaisVendidos(dtInicio, dtFim);
				List<Produto> produto1 = new ArrayList<>();
				List<Produto> produto2 = new ArrayList<>();
				List<Produto> produto3 = new ArrayList<>();
				
				Produto produtoVazio = new Produto();
				GraficoAnalise graficoVazio = new GraficoAnalise();
				
				produtoVazio.setNome("VAZIO");
				produtoVazio.setQuantidadeSelecionada("0");
				
				graficoVazio.setProduto(produtoVazio);
				
				// ajuste do BUG de quando tiver nenhum pedido feito,
				// (se tiver nenhum pedido, a lista será retornada zerada),
				// solução: verificação da quantidade da lista "graficos" retornada do banco
				if (graficos.size() == 0) {
					// adição do produto VAZIO nas variaveis que serão usados no Gráfico
					produto1.add(produtoVazio);
					produto2.add(produtoVazio);
					produto3.add(produtoVazio);
					
					// adição do produto VAZIO na lista que retornou zerada do banco
					graficos.add(graficoVazio);
					graficos.add(graficoVazio);
					graficos.add(graficoVazio);
				}
				else if (graficos.size() == 1) {
					// adiciona somente 2 produtos VAZIO nas variaveis que serão usados no Gráfico
					produto1 = produtoDAO.consultarProdutoById(graficos.get(0).getProduto().getId());
					produto2.add(produtoVazio);
					produto3.add(produtoVazio);
					
					// adiciona somente 2 produtos VAZIO na lista que retornou do banco
					graficos.add(graficoVazio);
					graficos.add(graficoVazio);
				}
				else if (graficos.size() == 2) {
					// adiciona somente 1 produto VAZIO nas variaveis que serão usados no Gráfico
					produto1 = produtoDAO.consultarProdutoById(graficos.get(0).getProduto().getId());
					produto2 = produtoDAO.consultarProdutoById(graficos.get(1).getProduto().getId());
					produto3.add(produtoVazio);
					
					// adiciona somente 1 produto VAZIO na lista que retornou do banco
					graficos.add(graficoVazio);
				}
				// se o banco retornar 3 produtos
				else {
					// busca os nomes dos produtos, conforme a consulta realizada acima (3 produtos mais vendidos)
					produto1 = produtoDAO.consultarProdutoById(graficos.get(0).getProduto().getId());
					produto2 = produtoDAO.consultarProdutoById(graficos.get(1).getProduto().getId());
					produto3 = produtoDAO.consultarProdutoById(graficos.get(2).getProduto().getId());
				}
				
				// pendura o "produto1" e o "valorProduto1", para poder mandar para o arquivo .JSP
				request.setAttribute("produto1", produto1.get(0).getNome());
				request.setAttribute("valorProduto1", graficos.get(0).getProduto().getQuantidadeSelecionada());
				
				// pendura o "produto2" e o "valorProduto2", para poder mandar para o arquivo .JSP
				request.setAttribute("produto2", produto2.get(0).getNome());
				request.setAttribute("valorProduto2", graficos.get(1).getProduto().getQuantidadeSelecionada());
				
				// pendura o "produto3" e o "valorProduto3", para poder mandar para o arquivo .JSP
				request.setAttribute("produto3", produto3.get(0).getNome());
				request.setAttribute("valorProduto3", graficos.get(2).getProduto().getQuantidadeSelecionada());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/grafico_chart_2.jsp").forward(request, response);
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
