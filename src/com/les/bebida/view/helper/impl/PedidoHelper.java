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
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.dominio.ItemPedido;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.Usuario;
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
		String forma_pagamento = null;
        String id_cartao_1 = null;
        String valor_cartao_1 = null;
        String id_cartao_2 = null;
        String valor_cartao_2 = null;
        String total_cupons = null;
        String id_cliente_consulta = null;
        
        List<Produto> produtosDaSessao = new ArrayList<>();
        List<Cupom> cuponsDaSessao = new ArrayList<>();
		
		if (("CONSULTAR").equals(operacao)) {
			pedido = new Pedido();
			
			id_cliente_consulta = request.getParameter("idClienteConsulta");
			
			pedido.setIdClienteConsulta(id_cliente_consulta);
		}
		
		else if (("SALVAR").equals(operacao)) {
			pedido = new Pedido();
			
			// capturando os valores do HTML e passando para o Pedido
			total_itens = request.getParameter("total_itens");
			total_frete = request.getParameter("total_frete");
			total_pedido = request.getParameter("total_pedido");
			id_cliente = request.getParameter("idCliente");
			id_endereco = request.getParameter("selecioneEndereco");
			forma_pagamento = request.getParameter("selecioneFormadePagamento");
			id_cartao_1 = request.getParameter("selecioneCartao1");
			valor_cartao_1 = request.getParameter("valorCartao1");
			id_cartao_2 = request.getParameter("selecioneCartao2");
			valor_cartao_2 = request.getParameter("valorCartao2");
			
			total_cupons = request.getParameter("total_cupons");
			
			// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
			HttpSession sessao = request.getSession();
			// pega o objeto salvo em Sess�o com o nome "itensCarrinho",
			// e passa para o "produtosDaSessao" (fazendo o CAST para o tipo List<Produto>)
			produtosDaSessao = (List<Produto>) sessao.getAttribute("itensCarrinho");
			// pega o objeto salvo em Sess�o com o nome "cupons",
			// e passa para o "cuponsDaSessao" (fazendo o CAST para o tipo List<Cupom>)
			cuponsDaSessao = (List<Cupom>) sessao.getAttribute("cupons");
			
			// Atribuindo os valores capturados do HTML para o Pedido
			pedido.setTotalItens(total_itens);
			pedido.setTotalFrete(total_frete);
			pedido.setTotalPedido(total_pedido);
			pedido.setIdCliente(id_cliente);
			pedido.setIdEndereco(id_endereco);
			pedido.setFormaPagamento(forma_pagamento);
			pedido.setIdCartao1(id_cartao_1);
			pedido.setValorCartao1(valor_cartao_1);
			pedido.setIdCartao2(id_cartao_2);
			pedido.setValorCartao2(valor_cartao_2);
			pedido.setTrocado("nao");
			pedido.setTotalCupons(total_cupons);
			pedido.setProdutos(produtosDaSessao);
			pedido.setCupons(cuponsDaSessao);
			
			//// ajuste do bug de quando o pedido n�o tiver nenhum Cupom vinculado,
			//if (id_cupom.equals("null")) {
        	//	// quando for finalizar o Pedido, e n�o tiver nenhum Cupom vinculado,
        	//	// o valor do "id_cupom" ser� "null", em formato de String, 
        	//	// ent�o n�o atribui o valor ao objeto "pedido",
        	//	// pq se o valor for "null" em formato de String, ir� acusar ERRO ao salvar o Pedido na DAO.
        	//	System.out.println("entrou !!");
        	//}
        	//else {
        	//	// caso contr�rio, se tiver algum Cupom para vincular,
        	//	// o valor ser� atribuido no Pedido
        	//	pedido.setIdCupom(id_cupom);
        	//}
			
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
				Usuario userLogado = new Usuario();
				
				// foi utilizado o getEntidades do resultado para poder pegar todos os Pedidos no sistema
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				// pega somente os pedidos do Cliente, salvo somente no index 0 (primeiro pedido)
				Pedido pedidos = (Pedido) entidades.get(0);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				// salva na sess�o o objeto "todosPedidosADMIN", recebendo todos os Pedidos feitos no sistema
				sessao.setAttribute("todosPedidosADMIN", entidades);
				
				// salva na sess�o o objeto "todosPedidosADMIN", recebendo todos os Pedidos feitos no sistema
				sessao.setAttribute("todosPedidosCliente", pedidos);
				
				// pega o objeto salvo em Sess�o com o nome "usuarioLogado",
				// e passa para o novo objeto criado com o nome "userLogado", (fazendo o CAST para o tipo Usuario)
				userLogado = (Usuario) sessao.getAttribute("usuarioLogado");
				
				if(userLogado.getTipo().equals("admin")) {
					// Redireciona para o arquivo .JSP
					request.getRequestDispatcher("JSP/lista-todos-pedidos-scriptletADMIN.jsp").forward(request, response);
				}
				if(userLogado.getTipo().equals("cliente")) {
					// Redireciona para o arquivo .JSP
					request.getRequestDispatcher("JSP/lista-pedidos-scriptletCLIENTE.jsp").forward(request, response);
				}
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
				List<Cupom> cuponsVazio = new ArrayList<>();
				List<Produto> produtosVazio = new ArrayList<>();

				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();

				// limpa os produtos selecionados do carrinho da sess�o,
				// atualiza o objeto "itensCarrinho" que esta salvo em sess�o, com o novo objeto de produto vazio
				sessao.setAttribute("itensCarrinho", produtosVazio);
				
				// limpa os cupons selecionados do carrinho da sess�o,
				// atualiza o objeto "cupons" que esta salvo em sess�o, com o novo objeto de cupom vazio
				sessao.setAttribute("cupons", cuponsVazio);
				
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
				request.getRequestDispatcher("JSP/lista-carrinho-scriptlet-mensagem.jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
	}

}
