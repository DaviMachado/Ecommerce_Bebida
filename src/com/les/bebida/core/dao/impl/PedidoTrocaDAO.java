package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.les.bebida.core.dominio.Cupom;
import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.dominio.ItemPedido;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.dominio.PedidoTroca;
import com.les.bebida.core.dominio.Produto;

public class PedidoTrocaDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar a Troca do Pedido ou dos Itens do Pedido
	 * @param entidade
	 */
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		PedidoTroca pedidoTrocaEntidade = (PedidoTroca) entidade;
		
		PedidoDAO pedidoDAO = new PedidoDAO();
		ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
		Pedido pedido = new Pedido();
		Pedido novoPedido = new Pedido();
		ItemPedido novoItemPedido = new ItemPedido();
		ItemPedido itemPedidoTrocaInteira = new ItemPedido();
		List<ItemPedido> alteraQtdeAndStatusItemPedido = new ArrayList<>();	
		List<PedidoTroca> itensPedidoParaAdicionarNaSessao = new ArrayList<>();	
		List<Pedido> pedidoOriginal =  new ArrayList<>();
		double total_itens = 0;
		
		// salva a data atual no Pedido e na sequencia no Item do Pedido
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);
		
		// verifica se foi acionado o bot�o para realizar a troca inteira do Pedido,
		// (acionado pela tela "lista-pedidos-scriptletCLIENTE.jsp"),
		// caso foi acionado, ser� feito o preenchimento da lista "itensPedidoTroca" que esta salvo em Sess�o,
		// com os itens do Pedido que foi acionado a troca inteira
		if (pedidoTrocaEntidade.getTrocaPedidoInteiro().equals("1")) {
			// seta o valor do ID do Pedido com o valor que foi enviado pela tela
			itemPedidoTrocaInteira.setIdPedido(pedidoTrocaEntidade.getIdPedido());
			
			// busca os Itens do Pedido pelo ID do Pedido
			List<EntidadeDominio> entidades = itemPedidoDAO.consultar(itemPedidoTrocaInteira);
			// por causa da mudan�a na consulta do ItemPedidoDAO, tbm foi alterado o jeito que � chamada
			// feito o CAST de Entidade para o ItemPedido (pegando o primeiro indice de Entidade)
			ItemPedido itemPedidoEntidade = (ItemPedido) entidades.get(0);
			
			for(ItemPedido order_items : itemPedidoEntidade.getItensPedido()) {
				// Aplicado o CAST para poder popular os itens do pedido,
				// fazendo o CAST para uma refer�ncia mais gen�rica, no caso para o item do pedido
				//ItemPedido order_items = (ItemPedido) e;
				
				// guarda o Item do Pedido no objeto "pedidoTroca"
				PedidoTroca pedidoTroca = new PedidoTroca();
				pedidoTroca.setItemPedido(order_items);
				
				// passa o item selecionado para a variavel que ser� responsavel para atualizar a sess�o dos itens de troca do Pedido
				itensPedidoParaAdicionarNaSessao.add(pedidoTroca);
			}
			
			// atualiza o objeto "itensPedidoTroca" que esta salvo em sess�o, com os novos itens do Pedido selecionado
			pedidoTrocaEntidade.setItensPedidoTroca(itensPedidoParaAdicionarNaSessao);
		}
		
		// buscar as informa��es do Pedido que esta vinculado no Item do Pedido de Troca da Sess�o,
		// para quando criar um novo Pedido, salvar com as mesmas informa��es do Pedido Original
		pedidoOriginal = pedidoDAO.consultarPedidoById(pedidoTrocaEntidade.getItensPedidoTroca().get(0).getItemPedido().getIdPedido());
		
		// faz um la�o para calcular o total dos itens do pedido de troca
		for (int i = 0; i< pedidoTrocaEntidade.getItensPedidoTroca().size(); i++) {
			// faz o calculo dos itens que ser�o solicitados para a troca
			// calculo do total dos itens (quantidade do item (*) o valor do item "pre�o de venda")
			total_itens += (Double.parseDouble(pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getProduto().getQuantidadeSelecionada()) * Double.parseDouble(pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getProduto().getPrecoDeVenda()));
		}
		
		novoPedido.setTotalItens("0");
		novoPedido.setTotalFrete("0");
		novoPedido.setTotalPedido(Double.toString(total_itens));
		novoPedido.setStatus("TROCA SOLICITADA");
		novoPedido.setIdCliente(pedidoOriginal.get(0).getIdCliente());
		novoPedido.setIdEndereco(pedidoOriginal.get(0).getIdEndereco());
		novoPedido.setFormaPagamento(pedidoOriginal.get(0).getFormaPagamento());
		novoPedido.setIdCartao1(pedidoOriginal.get(0).getIdCartao1());
		novoPedido.setValorCartao1(pedidoOriginal.get(0).getValorCartao1());
		novoPedido.setIdCartao2(pedidoOriginal.get(0).getIdCartao2());
		novoPedido.setValorCartao2(pedidoOriginal.get(0).getValorCartao2());
		novoPedido.setTotalCupons("0");
		novoPedido.setTrocado("sim");
		novoPedido.setDtCadastro(dataAtual);
		novoPedido.setDarBaixaEstoque("NAO");
		
		// salva o novo Pedido com o status TROCA SOLICITADA,
		// com base nos Itens do Pedido de Troca da Sess�o,
		pedidoDAO.salvar(novoPedido);
		
		// consulta o ultimo Pedido cadastrado para poder pegar o ID do Pedido,
		// para poder salvar na tabela "pedido_item"
		List<Pedido> ultimoPedido = pedidoDAO.consultarUltimoPedidoCadastrado(pedido);
		
		// faz um la�o para percorrer todos os itens que esta na Sess�o "itensPedidoTroca",
		// para cria os novos Itens do Pedido
		for (int i = 0; i< pedidoTrocaEntidade.getItensPedidoTroca().size(); i++) {
			novoItemPedido.setProduto(pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getProduto());
			novoItemPedido.setIdPedido(ultimoPedido.get(0).getId());
			novoItemPedido.setTrocado("sim");
			novoItemPedido.setDtCadastro(ultimoPedido.get(0).getDtCadastro());
			
			// salva o novo Item do Pedido
			itemPedidoDAO.salvar(novoItemPedido);
		}
		
		// altera a Quantidade e o Status do Item do Pedido que foi selecionado no Pedido,
		// altera para o status que "j� foi trocado", caso a quantidade do item do pedido seja igual a ZERO,
		for (int i = 0; i< pedidoTrocaEntidade.getItensPedidoTroca().size(); i++) {
			int novaQuantidadeDoItemPedido = 0;
			
			// busca o item do pedido para fazer a somatoria
			alteraQtdeAndStatusItemPedido = itemPedidoDAO.consultarItemPedidoById(pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getId());
			
			// faz o calculo do novo valor da quantidade do item do pedido
			// calculo da nova quantidade (quantidade do item que esta salvo no banco (-) o valor da quantidade selecionada na Sess�o)
			novaQuantidadeDoItemPedido = (Integer.parseInt(alteraQtdeAndStatusItemPedido.get(0).getProduto().getQuantidadeSelecionada()) - Integer.parseInt(pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getProduto().getQuantidadeSelecionada()));
			
			// faz a altera��o da nova quantidade do item do pedido no banco
			itemPedidoDAO.alterarQuantidadeItemPedido(Integer.toString(novaQuantidadeDoItemPedido), pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getId());
			
			// se a quantidade do item do pedido ficar igual a ZERO,
			// ser� trocado o status do item para "j� foi trocado",
			// altera no banco a tabela "pedido_item" da coluna "trocado" para "sim".
			if (novaQuantidadeDoItemPedido == 0) {
				itemPedidoDAO.alterarTrocacaoItemPedido(pedidoTrocaEntidade.getItensPedidoTroca().get(i).getItemPedido().getId());
			}
		}
		
		// verifica se existe algum item desse Pedido selecionado, esta com o status "trocado" como "nao",
		// pois se n�o estiver, logo todos os itens estar� com o status "trocado" como "sim",
		// ent�o tamb�m altera o status do Pedido para "j� foi trocado",.
		List<ItemPedido> pedidoComTodosOsItensJaTrocados = itemPedidoDAO.consultarItemPedidoByIdPedidoAndTrocadoNao(pedidoTrocaEntidade.getItensPedidoTroca().get(0).getItemPedido().getIdPedido());
		
		if (pedidoComTodosOsItensJaTrocados.isEmpty()) {
			// altera o status do Pedido selecionado para "j� foi trocado",
			// altera no banco a tabela "pedido" da coluna "trocado" para "sim".
			pedidoDAO.alterarTrocacaoPedido(pedidoTrocaEntidade.getItensPedidoTroca().get(0).getItemPedido().getIdPedido());
			
			// altera o status do Pedido selecionado para CANCELAMENTO ACEITO
			pedidoDAO.alterarStatusPedido(pedidoTrocaEntidade.getItensPedidoTroca().get(0).getItemPedido().getIdPedido(), "CANCELAMENTO ACEITO");
		}
		
	} // Salvar
	
	
	/**
	 * Metodo para alterar a Troca do Pedido ou dos Itens do Pedido
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		PedidoTroca pedidoTrocaEntidade = (PedidoTroca) entidade;
		
		PedidoDAO pedidoDAO = new PedidoDAO();
		ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
		EstoqueDAO estoqueDAO = new EstoqueDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		CupomDAO cupomDAO = new CupomDAO();
		ItemPedido itemPedido = new ItemPedido();
		Cupom cupom = new Cupom();
		Estoque estoque = new Estoque();
		int quantidadeFinal;
		
		List<Pedido> listPedido = pedidoDAO.consultarPedidoById(pedidoTrocaEntidade.getIdPedido());
		
		// salva a data atual no Cupom e no Estoque
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dataAtual;
		dataAtual = dateFormat.format(date);
		
		// na REFERENCIA de "pedidoTrocaEntidade", ser� setado um Pedido inteiro,
		// conforme o idPedido que foi digitado na tela
		pedidoTrocaEntidade.setPedido(listPedido.get(0));
		
		// se for algum desses status, ser� feito a ReEntrada no Estoque e gerado o Cupom de desconto do mesmo
		if (pedidoTrocaEntidade.getNovoStatusPedido().equals("TROCA EFETUADA") || pedidoTrocaEntidade.getNovoStatusPedido().equals("CANCELAMENTO EFETUADO")) {
			// ajusta o bug de n�o realizar a Reentrada no Estoque mais de uma vez,
			// verifica se o Pedido selecionado j� esta com o status "TROCA EFETUADA"
			if (pedidoTrocaEntidade.getPedido().getStatus().equals("TROCA EFETUADA")) {
				
			}
			// verifica se o Pedido selecionado j� esta com o status "CANCELAMENTO EFETUADO"
			else if (pedidoTrocaEntidade.getPedido().getStatus().equals("CANCELAMENTO EFETUADO")) {
				
			}
			else {
				// caso contr�rio, ser� realizada a ReEntrada no Estoque e gerado o Cupom
				// altera o status do Pedido conforme foi selecionado na tela
				pedidoDAO.alterarStatusPedido(pedidoTrocaEntidade.getIdPedido(), pedidoTrocaEntidade.getNovoStatusPedido());
				
				// seta o valor do ID do Item Pedido com o valor que foi capturado na tela
				itemPedido.setIdPedido(pedidoTrocaEntidade.getIdPedido());
				
				// busca todos os itens desse Pedido para realizar a ReEntrada no Estoque,
				// busca os Itens do Pedido pelo ID do Pedido
				List<EntidadeDominio> entidades = itemPedidoDAO.consultar(itemPedido);
				// por causa da mudan�a na consulta do ItemPedidoDAO, tbm foi alterado o jeito que � chamada
				// feito o CAST de Entidade para o ItemPedido (pegando o primeiro indice de Entidade)
				ItemPedido itens_pedido = (ItemPedido) entidades.get(0);
				
				for(EntidadeDominio e : itens_pedido.getItensPedido()) {
		    		// Aplicado o CAST para poder popular os itens do pedido,
					// fazendo o CAST para uma refer�ncia mais gen�rica, no caso para o item do pedido
					ItemPedido order_items = (ItemPedido) e;
					
					// faz a consulta pelo ID do produto que esta no item do pedido, para poder somar a quantidade anterior do produto, 
					// com a quantidade que dar� a entrada, para poder salvar a "Quantidade Final"
					List<Produto> produtoSelecionado = produtoDAO.consultarProdutoById(order_items.getProduto().getId());
					
					// faz a conta de soma da quantidade de entrada, mais a quantidade que j� tinha no produto
					quantidadeFinal = (Integer.parseInt(order_items.getProduto().getQuantidadeSelecionada()) + Integer.parseInt(produtoSelecionado.get(0).getQuantidade()));
					
					// realiza a ReEntrada no Estoque,
					// altera a quantidade do estoque do Produto
					estoqueDAO.alterarQuantidadeProduto(Integer.toString(quantidadeFinal), order_items.getProduto().getId());
					
					// ajuste do BUG de quando for realizar a ReEntrada de algum Produto,
					// o mesmo ser� verificado se esta como "inativo", pois se ele estiver,
					// ele voltar� a ficar "ativo", pois com a ReEntrada ao Estque, ter� mais quantidade para utilizar
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
				
				// gera o Cupom de Troca ou de Devolu��o
				if(pedidoTrocaEntidade.getNovoStatusPedido().equals("TROCA EFETUADA")) {
					cupom.setNome("TROCA" + pedidoTrocaEntidade.getIdPedido());
					cupom.setTipo("troca");
				}
				else {
					cupom.setNome("DEVOLUCAO" + pedidoTrocaEntidade.getIdPedido());
					cupom.setTipo("devolucao");
				}
				
				cupom.setValor(pedidoTrocaEntidade.getTotalPedido());
				cupom.setUtilizado("nao");
				cupom.setIdCliente(pedidoTrocaEntidade.getIdCliente());
				cupom.setDtCadastro(dataAtual);
				
				// gera o novo Cupom
				cupomDAO.salvar(cupom);
			}
		}
		else {
			// altera o status do Pedido conforme foi selecionado na tela
			pedidoDAO.alterarStatusPedido(pedidoTrocaEntidade.getIdPedido(), pedidoTrocaEntidade.getNovoStatusPedido());
		}
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir a Troca do Pedido ou dos Itens do Pedido
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para Consultar a Troca do Pedido ou dos Itens do Pedido
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		try {
			PedidoTroca pedidoTrocaEntidade = (PedidoTroca) entidade;
			PedidoTroca novoPedidoTroca = new PedidoTroca();
			List<EntidadeDominio> listPedidoTroca = new ArrayList<>();
			
			PreparedStatement stmt = connection.prepareStatement("select * from pedido where id=?");
			stmt.setString(1, pedidoTrocaEntidade.getIdPedido());
			ResultSet rs = stmt.executeQuery();
			
			List<Pedido> pedidos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				Pedido pedidoItem = new Pedido();
				
				pedidoItem.setId(rs.getString("id"));
				pedidoItem.setTotalItens(rs.getString("total_itens"));
				pedidoItem.setTotalFrete(rs.getString("total_frete"));
				pedidoItem.setTotalPedido(rs.getString("total_pedido"));
				pedidoItem.setStatus(rs.getString("status"));
				pedidoItem.setIdCliente(rs.getString("id_cliente"));
				pedidoItem.setIdEndereco(rs.getString("id_endereco"));
				pedidoItem.setFormaPagamento(rs.getString("forma_pagamento"));
				pedidoItem.setIdCartao1(rs.getString("id_cartao_1"));
				pedidoItem.setValorCartao1(rs.getString("valor_cartao_1"));
				pedidoItem.setIdCartao2(rs.getString("id_cartao_2"));
				pedidoItem.setValorCartao2(rs.getString("valor_cartao_2"));
				pedidoItem.setTotalCupons(rs.getString("total_cupons"));
				pedidoItem.setTrocado(rs.getString("trocado"));
				pedidoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto � lista
				pedidos.add(pedidoItem);
			}
			
			stmt = connection.prepareStatement("select * from pedido_item where id=?");
			stmt.setString(1, pedidoTrocaEntidade.getIdItemPedido());
			rs = stmt.executeQuery();
			
			List<ItemPedido> item_pedido_selecionado = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				ItemPedido item_pedidoItem = new ItemPedido();
				Produto produto = new Produto();
				
				item_pedidoItem.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoDeVenda(rs.getString("valor_de_venda"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade"));
				item_pedidoItem.setProduto(produto);
				
				item_pedidoItem.setIdPedido(rs.getString("id_pedido"));
				item_pedidoItem.setTrocado(rs.getString("trocado"));
				item_pedidoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto � lista
				item_pedido_selecionado.add(item_pedidoItem);
			}
			
			stmt = connection.prepareStatement("select * from pedido_item where id_pedido=?");
			stmt.setString(1, pedidoTrocaEntidade.getIdPedido());
			rs = stmt.executeQuery();
			
			List<ItemPedido> todos_itens_pedido = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				ItemPedido item_pedidoItem = new ItemPedido();
				Produto produto = new Produto();
				
				item_pedidoItem.setId(rs.getString("id"));
				
				produto.setId(rs.getString("id_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setPrecoDeVenda(rs.getString("valor_de_venda"));
				produto.setQuantidadeSelecionada(rs.getString("quantidade"));
				item_pedidoItem.setProduto(produto);
				
				item_pedidoItem.setIdPedido(rs.getString("id_pedido"));
				item_pedidoItem.setTrocado(rs.getString("trocado"));
				item_pedidoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto � lista
				todos_itens_pedido.add(item_pedidoItem);
			}
			
			stmt = connection.prepareStatement("select * from endereco where id=?");
			stmt.setString(1, pedidos.get(0).getIdEndereco());
			rs = stmt.executeQuery();
			
			List<Endereco> enderecos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Endere�o
				Endereco enderecoItem = new Endereco();
				
				enderecoItem.setId(rs.getString("id"));
				enderecoItem.setApelido(rs.getString("apelido"));
				enderecoItem.setCep(rs.getString("cep"));
				enderecoItem.setEstado(rs.getString("estado"));
				enderecoItem.setCidade(rs.getString("cidade"));
				enderecoItem.setNumero(rs.getString("numero"));
				enderecoItem.setBairro(rs.getString("bairro"));
				enderecoItem.setLogradouro(rs.getString("logradouro"));
				enderecoItem.setTipoResidencia(rs.getString("tipo_residencia"));
				enderecoItem.setPais(rs.getString("pais"));
				enderecoItem.setTipo_Endereco(rs.getString("tipo_endereco"));
				enderecoItem.setObservacao(rs.getString("observacao"));
				enderecoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				enderecoItem.setIdCliente(rs.getString("id_cliente"));
				
				// adicionando o objeto � lista
				enderecos.add(enderecoItem);
			}
			
			novoPedidoTroca.setPedidos(pedidos);
			novoPedidoTroca.setItemPedidoSelecionado(item_pedido_selecionado);
			novoPedidoTroca.setTodosItensPedido(todos_itens_pedido);
			novoPedidoTroca.setEnderecoPedido(enderecos);
			
			listPedidoTroca.add(novoPedidoTroca);
			
			rs.close();
			stmt.close();
			return listPedidoTroca;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Consultar
	
}
