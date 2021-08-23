package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dao.impl.ProdutoDAO;
import com.les.bebida.core.dominio.Carrinho;
import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.ItemCarrinho;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.view.helper.IViewHelper;

public class CarrinhoHelper implements IViewHelper {

	Carrinho carrinho = null;
	ItemCarrinho itemCarrinho = null;
	Produto produto = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual opera��o do bot�o foi acionada
		String operacao = request.getParameter("operacao");
		
		String id = null;
		String quantidadeSelecionada = null;
		String tipoDeOperacao = null;
		
		if (("CONSULTAR").equals(operacao)) {

		}
		
		else if (("SALVAR").equals(operacao)) {
			carrinho = new Carrinho();
			itemCarrinho = new ItemCarrinho();
			produto = new Produto();
			
			// capturando os valores do HTML e passando para o Produto, logo em sequencia passando para o Item Carrinho
			id = request.getParameter("idProduto");
			quantidadeSelecionada = request.getParameter("quantidadeSelecionada");
			
			// Atribuindo os valores capturados do HTML para o Item Carrinho, logo em sequencia passando para o Carrinho
			produto.setId(id);
			produto.setQuantidadeSelecionada(quantidadeSelecionada);
			itemCarrinho.setProduto(produto);
			carrinho.setItemCarrinho(itemCarrinho);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			carrinho = new Carrinho();
			itemCarrinho = new ItemCarrinho();
			produto = new Produto();
			
			// capturando os valores do HTML e passando para o Produto, logo em sequencia passando para o Item Carrinho
			id = request.getParameter("idProduto");
			quantidadeSelecionada = request.getParameter("quantidadeSelecionada");
			tipoDeOperacao = request.getParameter("tipoDeOperacao");
			
			// verifica o tipo de opera��o que esta sendo realizado no carrinho,
			// se � uma "adi��o", para adicionar mais "1" de quantidade do item selecionado ao carrinho,
			// ou � uma "subtra��o", para retirar mais "1" de quantidade do item selecionado ao carrinho
			if (("adicao").equals(tipoDeOperacao)) {
				int quantidadeSelecionadaInteiro;
				
				// feito o CAST de String para INT, para poder fazer a adi��o de mais 1
				quantidadeSelecionadaInteiro = Integer.parseInt(quantidadeSelecionada);
				quantidadeSelecionadaInteiro ++;
				
				// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com mais 1
				quantidadeSelecionada = Integer.toString(quantidadeSelecionadaInteiro);
			}
			else if (("subtracao").equals(tipoDeOperacao)) {
				int quantidadeSelecionadaInteiro;
				
				// feito o CAST de String para INT, para poder fazer a subtra��o de mais 1
				quantidadeSelecionadaInteiro = Integer.parseInt(quantidadeSelecionada);
				quantidadeSelecionadaInteiro --;
				
				// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com menos 1
				quantidadeSelecionada = Integer.toString(quantidadeSelecionadaInteiro);
			}
			
			// Atribuindo os valores capturados do HTML para o Item Carrinho, logo em sequencia passando para o Carrinho
			produto.setId(id);
			produto.setQuantidadeSelecionada(quantidadeSelecionada);
			itemCarrinho.setProduto(produto);
			carrinho.setItemCarrinho(itemCarrinho);
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return carrinho;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual opera��o do bot�o foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {

		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// capturando os valores do HTML
				String id = request.getParameter("idProduto");
				String quantidadeSelecionada = request.getParameter("quantidadeSelecionada");
				
				ProdutoDAO dao = new ProdutoDAO();
				// pesquisa no banco o produto selecionado
				List<Produto> produtoSelecionado = dao.consultarProdutoById(id);
				List<Produto> produtoParaAdicionarAoCarrinho = new ArrayList<>();
				
				// guarda no produto, o valor da quantidade selecionada da tela, 
				// para poder listar a quantidade selecionada no arquivo "lista-carrinho-scriptlet.jsp"
				produtoSelecionado.get(0).setQuantidadeSelecionada(quantidadeSelecionada);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// pega o objeto salvo em Sess�o com o nome "itensCarrinho",
				// e passa para o "produtoParaAdicionarAoCarrinho" (fazendo o CAST para o tipo List<Produto>)
				produtoParaAdicionarAoCarrinho = (List<Produto>) sessao.getAttribute("itensCarrinho");
				
				// passa o produto selecionado para a variavel que ser� responsavel para atualizar a sess�o dos itens do carrinho
				produtoParaAdicionarAoCarrinho.add(produtoSelecionado.get(0));
				
				// adiciona o produto selecionado ao carrinho da sess�o
				// atualiza o objeto "itensCarrinho" que esta salvo em sess�o, com o novo produto selecionado
				sessao.setAttribute("itensCarrinho", produtoParaAdicionarAoCarrinho);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Item adicionado ao Carrinho com sucesso!");
				
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// capturando os valores do HTML
				String id = request.getParameter("idProduto");
				String quantidadeSelecionada = request.getParameter("quantidadeSelecionada");
				String tipoDeOperacao = request.getParameter("tipoDeOperacao");
				
				ProdutoDAO dao = new ProdutoDAO();
				// pesquisa no banco o produto selecionado
				List<Produto> produtoSelecionado = dao.consultarProdutoById(id);
				List<Produto> produtoParaAdicionarAoCarrinho = new ArrayList<>();
				
				// verifica o tipo de opera��o que esta sendo realizado no carrinho,
				// se � uma "adi��o", para adicionar mais "1" de quantidade do item selecionado ao carrinho,
				// ou � uma "subtra��o", para retirar mais "1" de quantidade do item selecionado ao carrinho
				if (("adicao").equals(tipoDeOperacao)) {
					int quantidadeSelecionadaInteiro;
					
					// feito o CAST de String para INT, para poder fazer a adi��o de mais 1
					quantidadeSelecionadaInteiro = Integer.parseInt(quantidadeSelecionada);
					quantidadeSelecionadaInteiro ++;
					
					// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com mais 1
					quantidadeSelecionada = Integer.toString(quantidadeSelecionadaInteiro);
				}
				else if (("subtracao").equals(tipoDeOperacao)) {
					int quantidadeSelecionadaInteiro;
					
					// feito o CAST de String para INT, para poder fazer a subtra��o de mais 1
					quantidadeSelecionadaInteiro = Integer.parseInt(quantidadeSelecionada);
					quantidadeSelecionadaInteiro --;
					
					// feito o CAST de INT para String, para poder atualizar a quantidade selecionada com menos 1
					quantidadeSelecionada = Integer.toString(quantidadeSelecionadaInteiro);
				}
				
				// guarda no produto, o valor da quantidade selecionada da tela,
				// ja com o valor atualizado com o tipo de opera��o, 
				// para poder listar a quantidade selecionada no arquivo "lista-carrinho-scriptlet.jsp"
				produtoSelecionado.get(0).setQuantidadeSelecionada(quantidadeSelecionada);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// pega o objeto salvo em Sess�o com o nome "itensCarrinho",
				// e passa para o "produtoParaAdicionarAoCarrinho" (fazendo o CAST para o tipo List<Produto>)
				produtoParaAdicionarAoCarrinho = (List<Produto>) sessao.getAttribute("itensCarrinho");
				
				// faz um la�o de repeti��o para encontrar o produto que ser� atualizado na Sess�o
				for (int i = 0; i< produtoParaAdicionarAoCarrinho.size(); i++) {
					// se o ID do carrinho for igual ao ID do "produto atualizado", ele ser� adicionado ao carrinho
					if ((produtoParaAdicionarAoCarrinho.get(i).getId()).equals(produtoSelecionado.get(0).getId())) {
						// ".set" do ArrayList faz o seguinte:
						// set(int index, Object element):
						// Substitui o i-�simo elemento da lista pelo elemento especificado.
						produtoParaAdicionarAoCarrinho.set(i, produtoSelecionado.get(0));
						
						// outra forma de "atualizar" a lista dos produtos:
						//produtoParaAdicionarAoCarrinho.remove(i);
						//produtoParaAdicionarAoCarrinho.add(i, produtoSelecionado.get(0));
					}
				}
				
				// atualiza o objeto "itensCarrinho" que esta salvo em sess�o, com o novo produto selecionado atualizado
				sessao.setAttribute("itensCarrinho", produtoParaAdicionarAoCarrinho);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Item do Carrinho atualizado com sucesso!");
				
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			}
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// capturando os valores do HTML
				String id = request.getParameter("idProduto");

				ProdutoDAO dao = new ProdutoDAO();
				// pesquisa no banco o produto selecionado
				List<Produto> produtoSelecionado = dao.consultarProdutoById(id);
				List<Produto> produtoParaAdicionarAoCarrinho = new ArrayList<>();
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// pega o objeto salvo em Sess�o com o nome "itensCarrinho",
				// e passa para o "produtoParaAdicionarAoCarrinho" (fazendo o CAST para o tipo List<Produto>)
				produtoParaAdicionarAoCarrinho = (List<Produto>) sessao.getAttribute("itensCarrinho");
				
				// faz um la�o de repeti��o para encontrar o produto selecionado e retirar da lista da Sess�o
				for (int i = 0; i< produtoParaAdicionarAoCarrinho.size(); i++) {
					// se o ID do carrinho for igual ao ID do "produto selecionado", ele ser� retirado do carrinho
					if ((produtoParaAdicionarAoCarrinho.get(i).getId()).equals(produtoSelecionado.get(0).getId())) {
						// remove o item inteiro da Sess�o
						produtoParaAdicionarAoCarrinho.remove(i);
					}
				}
				
				// atualiza o objeto "itensCarrinho" que esta salvo em sess�o, a nova lista atualizada
				sessao.setAttribute("itensCarrinho", produtoParaAdicionarAoCarrinho);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Item do Carrinho removido com sucesso!");
				
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			}
		}
		
	}

}
