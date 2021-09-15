package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dao.impl.CupomDAO;
import com.les.bebida.core.dao.impl.EstoqueDAO;
import com.les.bebida.core.dao.impl.ItemPedidoDAO;
import com.les.bebida.core.dao.impl.PedidoDAO;
import com.les.bebida.core.dao.impl.ProdutoDAO;
import com.les.bebida.core.dominio.Cupom;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.dominio.ItemPedido;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.dominio.PedidoTroca;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.view.helper.IViewHelper;

public class PedidoTrocaHelper implements IViewHelper {

	PedidoTroca pedidoTroca = null;
	ItemPedido itemPedido = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		pedidoTroca = new PedidoTroca();
		
		if (("CONSULTAR").equals(operacao)) {

		}
		
		else if (("SALVAR").equals(operacao)) {

		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return pedidoTroca;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			// *************************
			// usado a operção "CONSULTAR" para poder salvar os itens selecionados para a troca na Sessão
			// *************************
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				String idPedido = request.getParameter("idPedido");
				String idItemPedido = request.getParameter("idItemPedido");
				
				ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
				PedidoTroca pedidoTroca = new PedidoTroca();
				List<ItemPedido> itemPedidoSelecionado = new ArrayList<>();
				List<PedidoTroca> itensParaAdicionarAoPedidoTroca = new ArrayList<>();		
				
				// busca o Item do Pedido selecionado na tela
				itemPedidoSelecionado = itemPedidoDAO.consultarItemPedidoById(idItemPedido);
				
				// guarda o Item do Pedido no objeto "pedidoTroca"
				pedidoTroca.setItemPedido(itemPedidoSelecionado.get(0));
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// pega o objeto salvo em Sessão com o nome "itensPedidoTroca",
				// e passa para o "itensParaAdicionarAoPedidoTroca" (fazendo o CAST para o tipo List<PedidoTroca>)
				itensParaAdicionarAoPedidoTroca = (List<PedidoTroca>) sessao.getAttribute("itensPedidoTroca");
				
				// ajuste no bug de quando tentar gravar um item do pedido, de um Pedido diferente ao que ja tem na Sessão,
				// se a lista "itensParaAdicionarAoPedidoTroca" estiver vazia, ou
				// se o item do Pedido que esta sendo selecionado é do mesmo Pedido do primeiro item da Sessão,
				// se for do mesmo Pedido, ele será adicionado na lista da Sessão "itensPedidoTroca"
				if (itensParaAdicionarAoPedidoTroca.isEmpty() || itensParaAdicionarAoPedidoTroca.get(0).getItemPedido().getIdPedido().equals(itemPedidoSelecionado.get(0).getIdPedido())) {
					// passa o item selecionado para a variavel que será responsavel para atualizar a sessão dos itens de troca do Pedido
					itensParaAdicionarAoPedidoTroca.add(pedidoTroca);
					
					// adiciona na lista de itens de troca do Pedido selecionado da sessão
					// atualiza o objeto "itensPedidoTroca" que esta salvo em sessão, com o novo item selecionado
					sessao.setAttribute("itensPedidoTroca", itensParaAdicionarAoPedidoTroca);
					
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Item adicionado para a Troca com sucesso!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// pendura o "id" do Pedido na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("idPedido", idPedido);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/detalhe_pedido-mensagem.jsp").forward(request, response);
				}
				else {
					// caso contrário, o Item do Pedido selecionado não é do mesmo Pedido que ja existe na Sessão,
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Selecione um Item do mesmo Pedido para a Troca!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// pendura o "id" do Pedido na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("idPedido", idPedido);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/detalhe_pedido-mensagem.jsp").forward(request, response);
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
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				String trocaPedidoInteiro = request.getParameter("trocaPedidoInteiro");
				String idPedido = request.getParameter("idPedido");
				
				PedidoDAO pedidoDAO = new PedidoDAO();
				ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
				Pedido pedido = new Pedido();
				Pedido novoPedido = new Pedido();
				ItemPedido novoItemPedido = new ItemPedido();
				ItemPedido itemPedidoTrocaInteira = new ItemPedido();
				List<PedidoTroca> itensPedidoParaAdicionarNaSessao = new ArrayList<>();	
				List<PedidoTroca> todosItensPedidoTrocaSessao = new ArrayList<>();
				List<PedidoTroca> ItensPedidoTrocaVazio = new ArrayList<>();
				List<Pedido> pedidoOriginal =  new ArrayList<>();
				double total_itens = 0;
				
				// salva a data atual no Pedido e na sequencia no Item do Pedido
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				String dataAtual;
				dataAtual = dateFormat.format(date);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				// verifica se foi acionado o botão para realizar a troca inteira do Pedido,
				// (acionado pela tela "lista-pedidos-scriptletCLIENTE.jsp"),
				// caso foi acionado, será feito o preenchimento da lista "itensPedidoTroca" que esta salvo em Sessão,
				// com os itens do Pedido que foi acionado a troca inteira
				if (trocaPedidoInteiro.equals("1")) {
					// seta o valor do ID do Pedido com o valor que foi enviado pela tela
					itemPedidoTrocaInteira.setIdPedido(idPedido);
					
					// busca os Itens do Pedido pelo ID do Pedido
					List<EntidadeDominio> itens_pedido = itemPedidoDAO.consultar(itemPedidoTrocaInteira);
					
					for(EntidadeDominio e : itens_pedido) {
						// Aplicado o CAST para poder popular os itens do pedido,
						// fazendo o CAST para uma referência mais genérica, no caso para o item do pedido
						ItemPedido order_items = (ItemPedido) e;
						
						// guarda o Item do Pedido no objeto "pedidoTroca"
						PedidoTroca pedidoTroca = new PedidoTroca();
						pedidoTroca.setItemPedido(order_items);
						
						// passa o item selecionado para a variavel que será responsavel para atualizar a sessão dos itens de troca do Pedido
						itensPedidoParaAdicionarNaSessao.add(pedidoTroca);
					}
					
					// atualiza o objeto "itensPedidoTroca" que esta salvo em sessão, com os novos itens do Pedido selecionado
					sessao.setAttribute("itensPedidoTroca", itensPedidoParaAdicionarNaSessao);
				}
				
				// pega o objeto salvo em Sessão com o nome "itensPedidoTroca",
				// e passa para o "todosItensPedidoTrocaSessao" (fazendo o CAST para o tipo List<PedidoTroca>)
				todosItensPedidoTrocaSessao = (List<PedidoTroca>) sessao.getAttribute("itensPedidoTroca");
				
				// buscar as informações do Pedido que esta vinculado no Item do Pedido de Troca da Sessão,
				// para quando criar um novo Pedido, salvar com as mesmas informações do Pedido Original
				pedidoOriginal = pedidoDAO.consultarPedidoById(todosItensPedidoTrocaSessao.get(0).getItemPedido().getIdPedido());
				
				// faz um laço para calcular o total dos itens do pedido de troca
				for (int i = 0; i< todosItensPedidoTrocaSessao.size(); i++) {
					// faz o calculo dos itens que serão solicitados para a troca
					// calculo do total dos itens (quantidade do item (*) o valor do item "preço de venda")
					total_itens += (Double.parseDouble(todosItensPedidoTrocaSessao.get(i).getItemPedido().getProduto().getQuantidadeSelecionada()) * Double.parseDouble(todosItensPedidoTrocaSessao.get(i).getItemPedido().getProduto().getPrecoDeVenda()));
				}
				
				novoPedido.setTotalPedido(Double.toString(total_itens));
				novoPedido.setStatus("TROCA SOLICITADA");
				novoPedido.setIdCliente(pedidoOriginal.get(0).getIdCliente());
				novoPedido.setIdEndereco(pedidoOriginal.get(0).getIdEndereco());
				novoPedido.setIdCartao(pedidoOriginal.get(0).getIdCartao());
				novoPedido.setTrocado("sim");
				novoPedido.setDtCadastro(dataAtual);
				
				// salva o novo Pedido com o status TROCA SOLICITADA,
				// com base nos Itens do Pedido de Troca da Sessão,
				pedidoDAO.salvar(novoPedido);
				
				// consulta o ultimo Pedido cadastrado para poder pegar o ID do Pedido,
				// para poder salvar na tabela "pedido_item"
				List<Pedido> ultimoPedido = pedidoDAO.consultarUltimoPedidoCadastrado(pedido);
				
				// faz um laço para percorrer todos os itens que esta na Sessão "itensPedidoTroca",
				// para cria os novos Itens do Pedido
				for (int i = 0; i< todosItensPedidoTrocaSessao.size(); i++) {
					novoItemPedido.setProduto(todosItensPedidoTrocaSessao.get(i).getItemPedido().getProduto());
					novoItemPedido.setIdPedido(ultimoPedido.get(0).getId());
					novoItemPedido.setTrocado("sim");
					novoItemPedido.setDtCadastro(ultimoPedido.get(0).getDtCadastro());
					
					// salva o novo Item do Pedido
					itemPedidoDAO.salvar(novoItemPedido);
				}
				
				// altera o Item do Pedido que foi selecionado no Pedido,
				// para o status que "já foi trocado",
				// altera no banco a tabela "pedido_item" da coluna "trocado" para "sim".
				for (int i = 0; i< todosItensPedidoTrocaSessao.size(); i++) {
					itemPedidoDAO.alterarTrocacaoItemPedido(todosItensPedidoTrocaSessao.get(i).getItemPedido().getId());
				}
				
				// verifica se existe algum item desse Pedido selecionado, esta com o status "trocado" como "nao",
				// pois se não estiver, logo todos os itens estará com o status "trocado" como "sim",
				// então também altera o status do Pedido para "já foi trocado",.
				List<ItemPedido> pedidoComTodosOsItensJaTrocados = itemPedidoDAO.consultarItemPedidoByIdPedidoAndTrocadoNao(todosItensPedidoTrocaSessao.get(0).getItemPedido().getIdPedido());
				
				if (pedidoComTodosOsItensJaTrocados.isEmpty()) {
					// altera o status do Pedido selecionado para "já foi trocado",
					// altera no banco a tabela "pedido" da coluna "trocado" para "sim".
					pedidoDAO.alterarTrocacaoPedido(todosItensPedidoTrocaSessao.get(0).getItemPedido().getIdPedido());
					
					// altera o status do Pedido selecionado para CANCELAMENTO ACEITO
					pedidoDAO.alterarStatusPedido(todosItensPedidoTrocaSessao.get(0).getItemPedido().getIdPedido(), "CANCELAMENTO ACEITO");
				}
				
				// limpa os itens dos pedidos de troca selecionados da sessão,
				// atualiza o objeto "itensPedidoTroca" que esta salvo em sessão, com o novo objeto de Item Pedido de Troca vazio
				sessao.setAttribute("itensPedidoTroca", ItensPedidoTrocaVazio);
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Troca solicitada com sucesso!");
				
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
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				String idPedido = request.getParameter("idPedido");
				String idCliente = request.getParameter("idCliente");
				String novoStatusPedido = request.getParameter("alterarStatusPedido");
				String totalPedido = request.getParameter("totalPedido");
				
				PedidoDAO pedidoDAO = new PedidoDAO();
				ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
				EstoqueDAO estoqueDAO = new EstoqueDAO();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				CupomDAO cupomDAO = new CupomDAO();
				ItemPedido itemPedido = new ItemPedido();
				Cupom cupom = new Cupom();
				Estoque estoque = new Estoque();
				int quantidadeFinal;
				
				// salva a data atual no Cupom e no Estoque
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				String dataAtual;
				dataAtual = dateFormat.format(date);
				
				// se for algum desses status, será feito a ReEntrada no Estoque e gerado o Cupom de desconto do mesmo
				if (novoStatusPedido.equals("TROCA EFETUADA") || novoStatusPedido.equals("CANCELAMENTO EFETUADO")) {
					// busca o Pedido pelo "idPedido" que foi capturado na tela
					List<Pedido> pedidoSelecionado = pedidoDAO.consultarPedidoById(idPedido);
					
					// ajusta o bug de não realizar a Reentrada no Estoque mais de uma vez,
					// verifica se o Pedido selecionado já esta com o status "TROCA EFETUADA"
					if (pedidoSelecionado.get(0).getStatus().equals("TROCA EFETUADA")) {
						// atribui a nova mensagem para poder mostra na pagina .JSP
						resultado.setMensagem("Pedido já esta com o status TROCA EFETUADA !");
					}
					// verifica se o Pedido selecionado já esta com o status "CANCELAMENTO EFETUADO"
					else if (pedidoSelecionado.get(0).getStatus().equals("CANCELAMENTO EFETUADO")) {
						// atribui a nova mensagem para poder mostra na pagina .JSP
						resultado.setMensagem("Pedido já esta com o status CANCELAMENTO EFETUADO!");
					}
					// caso contrário, será realizada a ReEntrada no Estoque e gerado o Cupom
					else {
						// altera o status do Pedido conforme foi selecionado na tela
						pedidoDAO.alterarStatusPedido(idPedido, novoStatusPedido);
						
						// seta o valor do ID do Item Pedido com o valor que foi capturado na tela
						itemPedido.setIdPedido(idPedido);
						
						// busca todos os itens desse Pedido para realizar a ReEntrada no Estoque,
						// busca os Itens do Pedido pelo ID do Pedido
						List<EntidadeDominio> itens_pedido = itemPedidoDAO.consultar(itemPedido);
						
						for(EntidadeDominio e : itens_pedido) {
			        		// Aplicado o CAST para poder popular os itens do pedido,
							// fazendo o CAST para uma referência mais genérica, no caso para o item do pedido
							ItemPedido order_items = (ItemPedido) e;
							
							// faz a consulta pelo ID do produto que esta no item do pedido, para poder somar a quantidade anterior do produto, 
							// com a quantidade que dará a entrada, para poder salvar a "Quantidade Final"
							List<Produto> produtoSelecionado = produtoDAO.consultarProdutoById(order_items.getProduto().getId());
							
							// faz a conta de soma da quantidade de entrada, mais a quantidade que já tinha no produto
							quantidadeFinal = (Integer.parseInt(order_items.getProduto().getQuantidadeSelecionada()) + Integer.parseInt(produtoSelecionado.get(0).getQuantidade()));
							
							// realiza a ReEntrada no Estoque,
							// altera a quantidade do estoque do Produto
							estoqueDAO.alterarQuantidadeProduto(Integer.toString(quantidadeFinal), order_items.getProduto().getId());
							
							// ajuste do BUG de quando for realizar a ReEntrada de algum Produto,
							// o mesmo será verificado se esta como "inativo", pois se ele estiver,
							// ele voltará a ficar "ativo", pois com a ReEntrada ao Estque, terá mais quantidade para utilizar
							if (produtoSelecionado.get(0).getStatus().equals("inativo")) {
								estoqueDAO.ativarProdutoEstoque(produtoSelecionado.get(0).getId());
							}
							
							// salva os atributos do ultimo Pedido cadastrado no Estoque, 
							// pra poder dar a entrada no Estoque (tabela estoque)
							estoque.setIdProduto(order_items.getProduto().getId());
							estoque.setTipo("entrada");
							estoque.setQuantidadeEntradaSaida(order_items.getProduto().getQuantidadeSelecionada());
							estoque.setValorCusto(produtoSelecionado.get(0).getPrecoDeCompra());
							estoque.setFornecedor("Entrada no Estoque pelo Pedido: " + order_items.getIdPedido());
							estoque.setDtEntrada(dataAtual);
							estoque.setQuantidadeFinal(Integer.toString(quantidadeFinal));
							estoque.setDtCadastro(dataAtual);
							
							// cria a entrada do produto no "Estoque"
							estoqueDAO.salvar(estoque);
						}
						
						// gera o Cupom de Troca ou de Devolução
						if(novoStatusPedido.equals("TROCA EFETUADA")) {
							cupom.setNome("TROCA" + idPedido);
							cupom.setTipo("troca");
						}
						else {
							cupom.setNome("DEVOLUCAO" + idPedido);
							cupom.setTipo("devolucao");
						}
						
						cupom.setValor(totalPedido);
						cupom.setUtilizado("nao");
						cupom.setIdCliente(idCliente);
						cupom.setDtCadastro(dataAtual);
						
						// gera o novo Cupom
						cupomDAO.salvar(cupom);
						
						// atribui a nova mensagem para poder mostra na pagina .JSP
						resultado.setMensagem("ReEntrada no Estoque e Cupom gerado com sucesso!");
					}
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
				}
				else {
					// altera o status do Pedido conforme foi selecionado na tela
					pedidoDAO.alterarStatusPedido(idPedido, novoStatusPedido);
					
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Status do Pedido alterado com sucesso!");
					
					// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
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
		
		else if (("EXCLUIR").equals(operacao)) {

		}
		
	}

}
