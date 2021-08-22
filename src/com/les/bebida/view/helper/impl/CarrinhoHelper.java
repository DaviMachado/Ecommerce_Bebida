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
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String id = null;
		
		if (("CONSULTAR").equals(operacao)) {

		}
		
		else if (("SALVAR").equals(operacao)) {
			carrinho = new Carrinho();
			itemCarrinho = new ItemCarrinho();
			produto = new Produto();
			
			// capturando os valores do HTML e passando para o Produto, logo em sequencia passando para o Item Carrinho
			id = request.getParameter("idProduto");
			
			// Atribuindo os valores capturados do HTML para o Item Carrinho, logo em sequencia passando para o Carrinho
			produto.setId(id);
			itemCarrinho.setProduto(produto);
			carrinho.setItemCarrinho(itemCarrinho);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return itemCarrinho;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {

		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// capturando os valores do HTML
				String id = request.getParameter("idProduto");
				
				ProdutoDAO dao = new ProdutoDAO();
				// pesquisa no banco o produto selecionado
				List<Produto> produtoSelecionado = dao.consultarProdutoById(id);
				List<Produto> produtoParaAdicionarAoCarrinho = new ArrayList<>();
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// pega o objeto salvo em Sessão com o nome "itensCarrinho",
				// e passa para o "produtoParaAdicionarAoCarrinho" (fazendo o CAST para o tipo List<Produto>)
				produtoParaAdicionarAoCarrinho = (List<Produto>) sessao.getAttribute("itensCarrinho");
				
				// passa o produto selecionado para a variavel que será responsavel para atualizar a sessão dos itens do carrinho
				produtoParaAdicionarAoCarrinho.add(produtoSelecionado.get(0));
				
				// adiciona o produto selecionado ao carrinho da sessão
				// atualiza o objeto "itensCarrinho" que esta salvo em sessão, com o novo produto selecionado
				sessao.setAttribute("itensCarrinho", produtoParaAdicionarAoCarrinho);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Item adicionado ao Carrinho com sucesso!");
				
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
