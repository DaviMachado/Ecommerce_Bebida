package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.Cupom;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.dominio.ItemPedido;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.dominio.Produto;

public class PedidoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Pedido
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into pedido "+
				"(total_itens, total_frete, total_pedido, status, id_cliente, id_endereco, forma_pagamento, id_cartao_1, valor_cartao_1, id_cartao_2, valor_cartao_2, total_cupons, trocado, dt_cadastro)" +
				"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Pedido pedido = (Pedido) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1, pedido.getTotalItens());
			stmt.setString(2, pedido.getTotalFrete());
			stmt.setString(3, pedido.getTotalPedido());
			stmt.setString(4, pedido.getStatus());
			stmt.setString(5, pedido.getIdCliente());
			stmt.setString(6, pedido.getIdEndereco());
			stmt.setString(7, pedido.getFormaPagamento());
			stmt.setString(8, pedido.getIdCartao1());
			stmt.setString(9, pedido.getValorCartao1());
			stmt.setString(10, pedido.getIdCartao2());
			stmt.setString(11, pedido.getValorCartao2());
			stmt.setString(12, pedido.getTotalCupons());
			stmt.setString(13, pedido.getTrocado());
			stmt.setString(14, pedido.getDtCadastro());
			
			// executa
			stmt.execute();
			stmt.close();
			
			// salva os itens do Pedido e da baixa no Estoque
			salvarItensPedidoAndBaixaEstoque(pedido.getProdutos(), pedido.getCupons());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	/**
	 * Metodo para alterar o Pedido
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir o Pedido
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para consultar o Pedido
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from pedido");
			ResultSet rs = stmt.executeQuery();
			
			List<EntidadeDominio> pedidos = new ArrayList<>();
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
				
				// adicionando o objeto à lista
				pedidos.add(pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar
	
	
	/**
	 * Metodo para consultar o Pedido por ID
	 */
	public List<Pedido> consultarPedidoById (String idPedido) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from pedido where id=?");
			stmt.setString(1, idPedido);
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
				
				// adicionando o objeto à lista
				pedidos.add(pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar o Pedido por ID
	
	
	/**
	 * Metodo para consultar o Pedido por ID do Cliente
	 */
	public List<Pedido> consultarPedidoByIdCliente (String idCliente) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from pedido where id_cliente=?");
			stmt.setString(1, idCliente);
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
				
				// adicionando o objeto à lista
				pedidos.add(pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar o Pedido por ID do Cliente
	
	
	/**
	 * Metodo para Listar o ultimo Pedido cadastrado no sistema
	 * @param entidade
	 * @return
	 */
	public List<Pedido> consultarUltimoPedidoCadastrado (EntidadeDominio entidade){
		openConnection();
		try {
			List<Pedido> pedidos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM pedido WHERE id=(SELECT max(id) FROM pedido)");
			ResultSet rs = stmt.executeQuery();
			
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
				
				// adicionando o objeto à lista
				pedidos.add(pedidoItem);
			}
			rs.close();
			stmt.close();
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar ultimo Pedido cadastrado
	
	
	/**
	 * Metodo para alterar a trocação do Pedido
	 * @param entidade
	 */
	public void alterarTrocacaoPedido (String idPedido) {
		openConnection();
		
		String sql = "update pedido set " +
					 "trocado='sim' " +
					 "where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, idPedido);
			
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar trocação do Pedido
	
	
	/**
	 * Metodo para alterar o status do Pedido
	 * @param entidade
	 */
	public void alterarStatusPedido (String idPedido, String status) {
		openConnection();
		
		String sql = "update pedido set " +
					 "status=? " +
					 "where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, status);
			stmt.setString(2, idPedido);
			
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar o status do Pedido
	
	
	/**
	 * Metodo para consultar os 3 clientes com maiores compras
	 */
	public List<Pedido> consultar3ClientesMaiorCompra () {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select id_cliente, count(id_cliente) as quantidade_comprado from pedido group by id_cliente order by count(id_cliente) desc LIMIT 3;");
			ResultSet rs = stmt.executeQuery();
			
			List<Pedido> pedidos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Pedido
				Pedido pedidoItem = new Pedido();

				pedidoItem.setTotalPedido(rs.getString("quantidade_comprado"));
				pedidoItem.setIdCliente(rs.getString("id_cliente"));
				
				// adicionando o objeto à lista
				pedidos.add(pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar os 3 clientes com maiores compras
	
	
	/**
	 * Metodo para consultar o Pedido por ID do Cliente, com o retorno de uma lista de EntidadeDominio
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultarPedidoByIdClienteEntidadeDominio (String IdCliente) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from pedido where id_cliente=?");
			stmt.setString(1, IdCliente);
			ResultSet rs = stmt.executeQuery();
			
			List<EntidadeDominio> pedidos = new ArrayList<>();
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
				
				// adicionando o objeto à lista
				pedidos.add(pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Consultar o Pedido por ID do Cliente, com o retorno de uma lista de EntidadeDominio
	
	
	/**
	 * Metodo para Listar os Pedidos pela Pesquisa por Filtro
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultarPedidoPesquisaByFiltro (EntidadeDominio entidade, String IdCliente, String StatusPedido) {
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from pedido where id_cliente=? and status=?");
			stmt.setString(1, IdCliente);
			stmt.setString(2, StatusPedido);
			ResultSet rs = stmt.executeQuery();
			
			List<EntidadeDominio> pedidos = new ArrayList<>();
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
				
				// adicionando o objeto à lista
				pedidos.add(pedidoItem);
			}
				
			rs.close();
			stmt.close();
			return pedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
	} // Listar os Pedidos pela Pesquisa por Filtro
	
	
	/**
	 * Metodo para Salvar itens do Pedido e dar baixa no Estoque
	 * @param entidade
	 * @return
	 */
	public void salvarItensPedidoAndBaixaEstoque(List<Produto> produtos, List<Cupom> cupons) {
		PedidoDAO pedidoDAO = new PedidoDAO();
		ItemPedidoDAO pedidoItemDAO = new ItemPedidoDAO();
		EstoqueDAO estoqueDAO = new EstoqueDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		CupomDAO cupomDAO = new CupomDAO();
		Pedido pedido = new Pedido();
		ItemPedido item_pedido = new ItemPedido();
		Estoque estoque = new Estoque();
		List<Produto> produtoAtualizado = new ArrayList<>();
		int quantidadeFinal;
		
		// consulta o ultimo Pedido cadastrado para poder pegar o ID do Pedido,
		// para poder salvar na tabela "pedido_item"
		List<Pedido> ultimoPedido = pedidoDAO.consultarUltimoPedidoCadastrado(pedido);
		
		for(Cupom cupom : cupons) {
			// altera o Cupom que foi selecionado no Pedido,
			// para o status que "já foi utilizado",
			// altera no banco a tabela "cupom" da coluna "utilizado" para "sim".
			cupomDAO.alterarUtilizacaoCupom(cupom.getId());
		}
		
		// após salvar o pedido, será salvo os itens do pedido,
		// faz um laço de repetição com os produtos selecionado da Sessão,
		// para poder salvar na tabela "pedido_item"
		for (int i = 0; i< produtos.size(); i++) {
			item_pedido.setProduto(produtos.get(i));
			item_pedido.setIdPedido(ultimoPedido.get(0).getId());
			item_pedido.setTrocado("nao");
			item_pedido.setDtCadastro(ultimoPedido.get(0).getDtCadastro());
			
			// salva o item do pedido
			pedidoItemDAO.salvar(item_pedido);
			
			// após salvar o item do pedido, o mesmo será dado a baixa no Estoque,
			// faz a conta de subtração da quantidade selecionada, menos a quantidade que já tinha no produto
			quantidadeFinal = (Integer.parseInt(produtos.get(i).getQuantidade()) - Integer.parseInt(produtos.get(i).getQuantidadeSelecionada()));
			
			// altera a quantidade do estoque do Produto (tabela produto)
			estoqueDAO.alterarQuantidadeProduto(Integer.toString(quantidadeFinal), produtos.get(i).getId());
			
			// faz a consulta pelo ID do produto do indice "i" do laço de repetição, 
			// para verificar a quantidade do estoque atualizado (após a subtração feita a cima),
			// se a quantidade for igual a ZERO, o produto será "inativado"
			produtoAtualizado = produtoDAO.consultarProdutoById(produtos.get(i).getId());
			if(produtoAtualizado.get(0).getQuantidade().equals("0")) {
				String motivo_inativacao;
				motivo_inativacao = " - SEM ESTOQUE";
				
				// faz a concatenação da obeservação com a mensagem "SEM ESTOQUE"
				produtoAtualizado.get(0).setObservacao(produtoAtualizado.get(0).getObservacao() + motivo_inativacao);
				
				estoqueDAO.inativaProdutoSemEstoque(produtoAtualizado.get(0).getId(), produtoAtualizado.get(0).getObservacao());
			}
			
			// salva os atributos do ultimo Pedido cadastrado no Estoque, 
			// pra poder dar a saida no Estoque (tabela estoque)
			estoque.setIdProduto(produtos.get(i).getId());
			estoque.setTipo("saida");
			estoque.setQuantidadeEntradaSaida(produtos.get(i).getQuantidadeSelecionada());
			estoque.setValorCusto(produtos.get(i).getPrecoDeCompra());
			estoque.setFornecedor("Saida no Estoque pelo Pedido: " + ultimoPedido.get(0).getId());
			estoque.setDtEntrada(ultimoPedido.get(0).getDtCadastro());
			estoque.setQuantidadeFinal(produtoAtualizado.get(0).getQuantidade());
			estoque.setDtCadastro(ultimoPedido.get(0).getDtCadastro());
			
			// cria a saida do produto no "Estoque"
			estoqueDAO.salvar(estoque);
		}
		
	} // Salvar itens do Pedido e dar baixa no Estoque
	
}
