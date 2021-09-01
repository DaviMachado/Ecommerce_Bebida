package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dao.impl.PedidoDAO;
import com.les.bebida.core.dao.impl.PedidoItemDAO;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.ItemPedido;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.view.helper.IViewHelper;

public class PedidoHelper implements IViewHelper {

	Pedido pedido = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual opera��o do bot�o foi acionada
		String operacao = request.getParameter("operacao");
		
		String total_itens = null;
        String total_frete = null;
        String total_pedido = null;
        String status = null;
        String id_cliente = null;
		String id_endereco = null;
        String id_cartao = null;
        String cupom = null;
		
		if (("CONSULTAR").equals(operacao)) {
			pedido = new Pedido();
		}
		
		else if (("SALVAR").equals(operacao)) {
			pedido = new Pedido();
			
			// capturando os valores do HTML e passando para o Pedido
			total_itens = request.getParameter("total_itens");
			total_frete = request.getParameter("total_frete");
			total_pedido = request.getParameter("total_pedido");
			id_cliente = request.getParameter("idCliente");
			id_endereco = request.getParameter("selecioneEndereco");
			id_cartao = request.getParameter("selecioneCartao");
			cupom = request.getParameter("cupom");
			
			// Atribuindo os valores capturados do HTML para o Pedido
			pedido.setTotalItens(total_itens);
			pedido.setTotalFrete(total_frete);
			pedido.setTotalPedido(total_pedido);
			pedido.setIdCliente(id_cliente);
			pedido.setIdEndereco(id_endereco);
			pedido.setIdCartao(id_cartao);
			pedido.setCupom(cupom);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return pedido;
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
				String id_produto = request.getParameter("selecioneProduto");
				
				// pendura o "idProduto" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("idProduto", id_produto);
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/lista-estoque-produto-scriptlet.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				PedidoDAO pedidoDAO = new PedidoDAO();
				PedidoItemDAO pedidoItemDAO = new PedidoItemDAO();
				Pedido pedido = new Pedido();
				ItemPedido item_pedido = new ItemPedido();
				List<Produto> produtosVazio = new ArrayList<>();
				List<Produto> produtosDaSessao = new ArrayList<>();
				
				// consulta o ultimo Pedido cadastrado para poder pegar o ID do Pedido,
				// para poder salvar na tabela "pedido_item"
				List<Pedido> ultimoPedido = pedidoDAO.consultarUltimoPedidoCadastrado(pedido);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// pega o objeto salvo em Sess�o com o nome "itensCarrinho",
				// e passa para o "produtosDaSessao" (fazendo o CAST para o tipo List<Produto>)
				produtosDaSessao = (List<Produto>) sessao.getAttribute("itensCarrinho");
				
				// ap�s salvar o pedido, ser� salvo os itens do pedido,
				// faz um la�o de repeti��o com os produtos selecionado da Sess�o,
				// para poder salvar na tabela "pedido_item"
				for (int i = 0; i< produtosDaSessao.size(); i++) {
					item_pedido.setProduto(produtosDaSessao.get(i));
					item_pedido.setIdPedido(ultimoPedido.get(0).getId());
					item_pedido.setDtCadastro(ultimoPedido.get(0).getDtCadastro());
					
					pedidoItemDAO.salvar(item_pedido);
				}
				
				// limpa os produtos selecionados do carrinho da sess�o,
				// atualiza o objeto "itensCarrinho" que esta salvo em sess�o, com o novo objeto de produto vazio
				sessao.setAttribute("itensCarrinho", produtosVazio);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Cadastro do Pedido salvo com sucesso!");
				
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
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
	}

}
