package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.ItemPedido;
import com.les.bebida.core.dominio.PedidoTroca;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.view.helper.IViewHelper;

public class ItemPedidoHelper implements IViewHelper {

	ItemPedido itemPedido = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String idPedido = null;
		
		if (("CONSULTAR").equals(operacao)) {
			itemPedido = new ItemPedido();
			
			// capturando os valores do HTML e passando para o Item Pedido
			idPedido = request.getParameter("idPedido");
			
			// Atribuindo os valores capturados do HTML para o Item Pedido
			itemPedido.setIdPedido(idPedido);
		}
		
		else if (("SALVAR").equals(operacao)) {
			
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return itemPedido;
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
				// foi utilizado o getEntidades do resultado para poder pegar o Login consultado
				List<EntidadeDominio> entidades = resultado.getEntidades();
				
				// feito o CAST de Entidade para o ItemPedido (pegando o primeiro indice de Entidade)
				ItemPedido itemPedidoEntidade = (ItemPedido) entidades.get(0);
				
				// pendura o "Pedido inteiro" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("pedidoInteiro", itemPedidoEntidade.getPedidos().get(0));
				
				// pendura os " Itens do Pedido" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("itensPedido", itemPedidoEntidade.getItensPedido());
				
				// pendura o "Endereço do Pedido" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("enderecoPedido", itemPedidoEntidade.getEnderecoPedido().get(0));
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/detalhe_pedido.jsp").forward(request, response);
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
