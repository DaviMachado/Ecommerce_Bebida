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
import com.les.bebida.core.dao.impl.EstoqueDAO;
import com.les.bebida.core.dao.impl.PedidoDAO;
import com.les.bebida.core.dao.impl.ItemPedidoDAO;
import com.les.bebida.core.dao.impl.ProdutoDAO;
import com.les.bebida.core.dominio.Cupom;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.dominio.ItemPedido;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.view.helper.IViewHelper;

public class PedidoHelper implements IViewHelper {

	Pedido pedido = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
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
        String id_cupom = null;
		
		if (("CONSULTAR").equals(operacao)) {
			
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
			
			id_cupom = request.getParameter("idCupom");
			
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
			
			// ajuste do bug de quando o pedido não tiver nenhum Cupom vinculado,
			if (id_cupom.equals("null")) {
        		// quando for finalizar o Pedido, e não tiver nenhum Cupom vinculado,
        		// o valor do "id_cupom" será "null", em formato de String, 
        		// então não atribui o valor ao objeto "pedido",
        		// pq se o valor for "null" em formato de String, irá acusar ERRO ao salvar o Pedido na DAO.
        		System.out.println("entrou !!");
        	}
        	else {
        		// caso contrário, se tiver algum Cupom para vincular,
        		// o valor será atribuido no Pedido
        		pedido.setIdCupom(id_cupom);
        	}
			
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
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				PedidoDAO pedidoDAO = new PedidoDAO();
				ItemPedidoDAO pedidoItemDAO = new ItemPedidoDAO();
				EstoqueDAO estoqueDAO = new EstoqueDAO();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				CupomDAO cupomDAO = new CupomDAO();
				Pedido pedido = new Pedido();
				ItemPedido item_pedido = new ItemPedido();
				Estoque estoque = new Estoque();
				Cupom cupomVazio = new Cupom();
				Cupom cupomSessao = new Cupom();
				List<Produto> produtosVazio = new ArrayList<>();
				List<Produto> produtosDaSessao = new ArrayList<>();
				List<Produto> produtoAtualizado = new ArrayList<>();
				int quantidadeFinal;
				
				// consulta o ultimo Pedido cadastrado para poder pegar o ID do Pedido,
				// para poder salvar na tabela "pedido_item"
				List<Pedido> ultimoPedido = pedidoDAO.consultarUltimoPedidoCadastrado(pedido);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// pega o objeto salvo em Sessão com o nome "itensCarrinho",
				// e passa para o "produtosDaSessao" (fazendo o CAST para o tipo List<Produto>)
				produtosDaSessao = (List<Produto>) sessao.getAttribute("itensCarrinho");
				// pega o objeto salvo em Sessão com o nome "cupom",
				// e passa para o "cupomSessao" (fazendo o CAST para o tipo Cupom)
				cupomSessao = (Cupom) sessao.getAttribute("cupom");
				
				// altera o Cupom que foi selecionado no Pedido,
				// para o status que "já foi utilizado",
				// altera no banco a tabela "cupom" da coluna "utilizado" para "sim".
				cupomDAO.alterarUtilizacaoCupom(cupomSessao.getId());
				
				// após salvar o pedido, será salvo os itens do pedido,
				// faz um laço de repetição com os produtos selecionado da Sessão,
				// para poder salvar na tabela "pedido_item"
				for (int i = 0; i< produtosDaSessao.size(); i++) {
					item_pedido.setProduto(produtosDaSessao.get(i));
					item_pedido.setIdPedido(ultimoPedido.get(0).getId());
					item_pedido.setTrocado("nao");
					item_pedido.setDtCadastro(ultimoPedido.get(0).getDtCadastro());
					
					// salva o item do pedido
					pedidoItemDAO.salvar(item_pedido);
					
					// após salvar o item do pedido, o mesmo será dado a baixa no Estoque,
					// faz a conta de subtração da quantidade selecionada, menos a quantidade que já tinha no produto
					quantidadeFinal = (Integer.parseInt(produtosDaSessao.get(i).getQuantidade()) - Integer.parseInt(produtosDaSessao.get(i).getQuantidadeSelecionada()));
					
					// altera a quantidade do estoque do Produto (tabela produto)
					estoqueDAO.alterarQuantidadeProduto(Integer.toString(quantidadeFinal), produtosDaSessao.get(i).getId());
					
					// faz a consulta pelo ID do produto do indice "i" do laço de repetição, 
					// para verificar a quantidade do estoque atualizado (após a subtração feita a cima),
					// se a quantidade for igual a ZERO, o produto será "inativado"
					produtoAtualizado = produtoDAO.consultarProdutoById(produtosDaSessao.get(i).getId());
					if(produtoAtualizado.get(0).getQuantidade().equals("0")) {
						String motivo_inativacao;
						motivo_inativacao = " - SEM ESTOQUE";
						
						// faz a concatenação da obeservação com a mensagem "SEM ESTOQUE"
						produtoAtualizado.get(0).setObservacao(produtoAtualizado.get(0).getObservacao() + motivo_inativacao);
						
						estoqueDAO.inativaProdutoSemEstoque(produtoAtualizado.get(0).getId(), produtoAtualizado.get(0).getObservacao());
					}
					
					// salva os atributos do ultimo Pedido cadastrado no Estoque, 
					// pra poder dar a saida no Estoque (tabela estoque)
					estoque.setIdProduto(produtosDaSessao.get(i).getId());
					estoque.setTipo("saida");
					estoque.setQuantidadeEntradaSaida(produtosDaSessao.get(i).getQuantidadeSelecionada());
					estoque.setValorCusto(produtosDaSessao.get(i).getPrecoDeCompra());
					estoque.setFornecedor("Saida no Estoque pelo Pedido: " + ultimoPedido.get(0).getId());
					estoque.setDtEntrada(ultimoPedido.get(0).getDtCadastro());
					estoque.setQuantidadeFinal(produtoAtualizado.get(0).getQuantidade());
					estoque.setDtCadastro(ultimoPedido.get(0).getDtCadastro());
					
					// cria a saida do produto no "Estoque"
					estoqueDAO.salvar(estoque);
				}
				
				// limpa os produtos selecionados do carrinho da sessão,
				// atualiza o objeto "itensCarrinho" que esta salvo em sessão, com o novo objeto de produto vazio
				sessao.setAttribute("itensCarrinho", produtosVazio);
				
				// limpa o cupom do carrinho da sessão,
				// atualiza o objeto "cupom" que esta salvo em sessão, com o novo objeto de cupom vazio
				sessao.setAttribute("cupom", cupomVazio);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Cadastro do Pedido salvo com sucesso!");
				
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
				request.getRequestDispatcher("JSP/lista-carrinho-scriptlet-mensagem.jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
	}

}
