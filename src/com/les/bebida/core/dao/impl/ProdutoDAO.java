package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.dominio.Produto;

public class ProdutoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Produto
	 * @param entidade
	 */
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		
		String sql = "insert into produto "+ 
				"(nome, descricao, categoria, preco_de_compra, preco_de_venda, foto, foto_detalhe, grupo_de_precificacao, quantidade, cd_sistema, status, dt_cadastro, observacao)" +
				"values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Produto produto = (Produto) entidade;
			
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,produto.getNome());
			stmt.setString(2,produto.getDescricao());
			stmt.setString(3,produto.getCategoria());
			stmt.setString(4,produto.getPrecoDeCompra());
			stmt.setString(5,produto.getPrecoDeVenda());
			stmt.setString(6, produto.getFoto());
			stmt.setString(7, produto.getFotoDetalhe());
			stmt.setString(8, produto.getGrupoDePrecificacao());
			stmt.setString(9, produto.getQuantidade());
			stmt.setString(10, produto.getCdSistema());
			stmt.setString(11, produto.getStatus());
			stmt.setString(12, produto.getDtCadastro());
			stmt.setString(13, produto.getObservacao());
			
			// executa
			stmt.execute();
			stmt.close();
			
			gravarRegistroEstoque();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Salvar
	
	
	/**
	 * Metodo para alterar o Produto
	 * @param entidade
	 */
	public void alterar (EntidadeDominio entidade) {
		openConnection();
		
		String sql = "update produto set " +
					 "nome=?, descricao=?, categoria=?, preco_de_compra=?, preco_de_venda=?, foto=?, foto_detalhe=?, grupo_de_precificacao=?, quantidade=?, " +
					 "status=?, observacao=? where id=?";
		
		try {
			Produto produtoEntidade = (Produto) entidade;
			
			// se tiver algo no "alteraProduto", altera o produto
			if(produtoEntidade.getAlteraProduto().contentEquals("1")) {
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, produtoEntidade.getNome());
				stmt.setString(2, produtoEntidade.getDescricao());
				stmt.setString(3, produtoEntidade.getCategoria());
				stmt.setString(4, produtoEntidade.getPrecoDeCompra());
				stmt.setString(5, produtoEntidade.getPrecoDeVenda());
				stmt.setString(6, produtoEntidade.getFoto());
				stmt.setString(7, produtoEntidade.getFotoDetalhe());
				stmt.setString(8, produtoEntidade.getGrupoDePrecificacao());
				stmt.setString(9, produtoEntidade.getQuantidade());
				stmt.setString(10, produtoEntidade.getStatus());
				stmt.setString(11, produtoEntidade.getObservacao());
				stmt.setString(12, produtoEntidade.getId());
				
				stmt.execute();
				stmt.close();
			}
			// caso contrário, não faz alteração e somente fecha a conexão com o banco,
			// e no ProdutoHelper, irá ter outra verificação para poder chamar a JSP de edição do produto
			else {
				PreparedStatement stmt = connection.prepareStatement("select * from produto where id=?");
				stmt.setString(1, produtoEntidade.getId());
				ResultSet rs = stmt.executeQuery();
				
				List<Produto> produtos = new ArrayList<>();
				while (rs.next()) {
					// criando o objeto Produto
					Produto produtoItem = new Produto();
					
					produtoItem.setId(rs.getString("id"));
					produtoItem.setNome(rs.getString("nome"));
					produtoItem.setDescricao(rs.getString("descricao"));
					produtoItem.setCategoria(rs.getString("categoria"));
					produtoItem.setPrecoDeCompra(rs.getString("preco_de_compra"));
					produtoItem.setPrecoDeVenda(rs.getString("preco_de_venda"));
					produtoItem.setFoto(rs.getString("foto"));
					produtoItem.setFotoDetalhe(rs.getString("foto_detalhe"));
					produtoItem.setGrupoDePrecificacao(rs.getString("grupo_de_precificacao"));
					produtoItem.setFoto(rs.getString("foto"));
					produtoItem.setQuantidade(rs.getString("quantidade"));
					produtoItem.setCdSistema(rs.getString("cd_sistema"));
					produtoItem.setStatus(rs.getString("status"));
					produtoItem.setDtCadastro(rs.getString("dt_cadastro"));
					produtoItem.setObservacao(rs.getString("observacao"));
					
					// adicionando o objeto à lista
					produtos.add(produtoItem);
				}
				rs.close();
				stmt.close();
				
				// salva o objeto do produto pesquisado, para mandar para a tela de edição
				// salva como REFERENCIA para o mesmo objeto que veio como parametro
				produtoEntidade.setProdutoPesquisado(produtos.get(0));
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Alterar
	
	
	/**
	 * Metodo para Excluir o Produto
	 * @param entidade
	 */
	public void excluir (EntidadeDominio entidade) {
		openConnection();
		
		try {
			Produto produtoEntidade = (Produto) entidade;
			
			// Exclui o estoque relacionado com o produto
			PreparedStatement stmt = connection.prepareStatement("delete from estoque where id_produto=?");
			stmt.setString(1, produtoEntidade.getId());
			stmt.executeUpdate();
			
			// Exclui o produto
			stmt = connection.prepareStatement("delete from produto where id=?");
			stmt.setString(1, produtoEntidade.getId());
			stmt.executeUpdate();
			
			stmt = connection.prepareStatement("select * from produto");
			ResultSet rs = stmt.executeQuery();
			
			List<Produto> produtos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Produto
				Produto produtoItem = new Produto();
				
				produtoItem.setId(rs.getString("id"));
				produtoItem.setNome(rs.getString("nome"));
				produtoItem.setDescricao(rs.getString("descricao"));
				produtoItem.setCategoria(rs.getString("categoria"));
				produtoItem.setPrecoDeCompra(rs.getString("preco_de_compra"));
				produtoItem.setPrecoDeVenda(rs.getString("preco_de_venda"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setFotoDetalhe(rs.getString("foto_detalhe"));
				produtoItem.setGrupoDePrecificacao(rs.getString("grupo_de_precificacao"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setQuantidade(rs.getString("quantidade"));
				produtoItem.setCdSistema(rs.getString("cd_sistema"));
				produtoItem.setStatus(rs.getString("status"));
				produtoItem.setDtCadastro(rs.getString("dt_cadastro"));
				produtoItem.setObservacao(rs.getString("observacao"));
				
				// adicionando o objeto à lista
				produtos.add(produtoItem);
			}
			
			rs.close();
			stmt.close();
			
			// salva como REFERENCIA os produtos, para poder listar os produtos de novo
			produtoEntidade.setTodosProdutos(produtos);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Excluir
	
	
	/**
	 * Metodo para Listar o Produto
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> listProdutos = new ArrayList<>();
			Produto novoProduto = new Produto();
			
			PreparedStatement stmt = connection.prepareStatement("select * from produto");
			ResultSet rs = stmt.executeQuery();
			
			List<Produto> produtos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Produto
				Produto produtoItem = new Produto();
				
				produtoItem.setId(rs.getString("id"));
				produtoItem.setNome(rs.getString("nome"));
				produtoItem.setDescricao(rs.getString("descricao"));
				produtoItem.setCategoria(rs.getString("categoria"));
				produtoItem.setPrecoDeCompra(rs.getString("preco_de_compra"));
				produtoItem.setPrecoDeVenda(rs.getString("preco_de_venda"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setFotoDetalhe(rs.getString("foto_detalhe"));
				produtoItem.setGrupoDePrecificacao(rs.getString("grupo_de_precificacao"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setQuantidade(rs.getString("quantidade"));
				produtoItem.setCdSistema(rs.getString("cd_sistema"));
				produtoItem.setStatus(rs.getString("status"));
				produtoItem.setDtCadastro(rs.getString("dt_cadastro"));
				produtoItem.setObservacao(rs.getString("observacao"));
				
				// adicionando o objeto à lista
				produtos.add(produtoItem);
			}
			
			novoProduto.setTodosProdutos(produtos);
			
			listProdutos.add(novoProduto);
			
			rs.close();
			stmt.close();
			return listProdutos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar
	
	
	/**
	 * Metodo para Listar o Produto por ID
	 * @param entidade
	 * @return
	 */
	public List<Produto> consultarProdutoById (String idProduto){
		openConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from produto where id=?");
			stmt.setString(1, idProduto);
			ResultSet rs = stmt.executeQuery();
			
			List<Produto> produtos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Produto
				Produto produtoItem = new Produto();
				
				produtoItem.setId(rs.getString("id"));
				produtoItem.setNome(rs.getString("nome"));
				produtoItem.setDescricao(rs.getString("descricao"));
				produtoItem.setCategoria(rs.getString("categoria"));
				produtoItem.setPrecoDeCompra(rs.getString("preco_de_compra"));
				produtoItem.setPrecoDeVenda(rs.getString("preco_de_venda"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setFotoDetalhe(rs.getString("foto_detalhe"));
				produtoItem.setGrupoDePrecificacao(rs.getString("grupo_de_precificacao"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setQuantidade(rs.getString("quantidade"));
				produtoItem.setCdSistema(rs.getString("cd_sistema"));
				produtoItem.setStatus(rs.getString("status"));
				produtoItem.setDtCadastro(rs.getString("dt_cadastro"));
				produtoItem.setObservacao(rs.getString("observacao"));
				
				// adicionando o objeto à lista
				produtos.add(produtoItem);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Produto por ID
	
	
	/**
	 * Metodo para Listar os Produtos somente Ativos
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultarSomenteAtivo (EntidadeDominio entidade){
		openConnection();
		try {
			List<EntidadeDominio> produtos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from produto where status='ativo'");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				Produto produtoItem = new Produto();
				
				produtoItem.setId(rs.getString("id"));
				produtoItem.setNome(rs.getString("nome"));
				produtoItem.setDescricao(rs.getString("descricao"));
				produtoItem.setCategoria(rs.getString("categoria"));
				produtoItem.setPrecoDeCompra(rs.getString("preco_de_compra"));
				produtoItem.setPrecoDeVenda(rs.getString("preco_de_venda"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setFotoDetalhe(rs.getString("foto_detalhe"));
				produtoItem.setGrupoDePrecificacao(rs.getString("grupo_de_precificacao"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setQuantidade(rs.getString("quantidade"));
				produtoItem.setCdSistema(rs.getString("cd_sistema"));
				produtoItem.setStatus(rs.getString("status"));
				produtoItem.setDtCadastro(rs.getString("dt_cadastro"));
				produtoItem.setObservacao(rs.getString("observacao"));
				
				// adicionando o objeto à lista
				produtos.add(produtoItem);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar Produtos somente Ativos
	
	
	/**
	 * Metodo para Listar o ultimo Produto cadastrado no sistema
	 * @param entidade
	 * @return
	 */
	public List<Produto> consultarUltimoProdutoCadastrado (EntidadeDominio entidade){
		openConnection();
		try {
			List<Produto> produtos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM produto WHERE id=(SELECT max(id) FROM produto)");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				Produto produtoItem = new Produto();
				
				produtoItem.setId(rs.getString("id"));
				produtoItem.setNome(rs.getString("nome"));
				produtoItem.setDescricao(rs.getString("descricao"));
				produtoItem.setCategoria(rs.getString("categoria"));
				produtoItem.setPrecoDeCompra(rs.getString("preco_de_compra"));
				produtoItem.setPrecoDeVenda(rs.getString("preco_de_venda"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setFotoDetalhe(rs.getString("foto_detalhe"));
				produtoItem.setGrupoDePrecificacao(rs.getString("grupo_de_precificacao"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setQuantidade(rs.getString("quantidade"));
				produtoItem.setCdSistema(rs.getString("cd_sistema"));
				produtoItem.setStatus(rs.getString("status"));
				produtoItem.setDtCadastro(rs.getString("dt_cadastro"));
				produtoItem.setObservacao(rs.getString("observacao"));
				
				// adicionando o objeto à lista
				produtos.add(produtoItem);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar ultimo Produto cadastrado
	
	
	/**
	 * Metodo para Listar o ultimo codigo do Produto cadastrado no sistema
	 * @param entidade
	 * @return
	 */
	public List<Produto> consultarUltimoCodigoSistemaCadastrado (){
		openConnection();
		try {
			List<Produto> produtos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM produto WHERE cd_sistema=(SELECT max(cd_sistema) FROM produto)");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				Produto produtoItem = new Produto();
				
				produtoItem.setId(rs.getString("id"));
				produtoItem.setNome(rs.getString("nome"));
				produtoItem.setDescricao(rs.getString("descricao"));
				produtoItem.setCategoria(rs.getString("categoria"));
				produtoItem.setPrecoDeCompra(rs.getString("preco_de_compra"));
				produtoItem.setPrecoDeVenda(rs.getString("preco_de_venda"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setFotoDetalhe(rs.getString("foto_detalhe"));
				produtoItem.setGrupoDePrecificacao(rs.getString("grupo_de_precificacao"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setQuantidade(rs.getString("quantidade"));
				produtoItem.setCdSistema(rs.getString("cd_sistema"));
				produtoItem.setStatus(rs.getString("status"));
				produtoItem.setDtCadastro(rs.getString("dt_cadastro"));
				produtoItem.setObservacao(rs.getString("observacao"));
				
				// adicionando o objeto à lista
				produtos.add(produtoItem);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar o ultimo codigo do Produto cadastrado no sistema
	
	
	/**
	 * Metodo para Listar os Produtos pela Pesquisa por Filtro
	 * @param entidade
	 * @return
	 */
	public List<EntidadeDominio> consultarProdutoPesquisaByFiltro (EntidadeDominio entidade, String Parametro){
		openConnection();
		try {
			List<EntidadeDominio> produtos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement("select * from produto where nome LIKE ?");
			stmt.setString(1, "%" + Parametro + "%");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Produto
				Produto produtoItem = new Produto();
				
				produtoItem.setId(rs.getString("id"));
				produtoItem.setNome(rs.getString("nome"));
				produtoItem.setDescricao(rs.getString("descricao"));
				produtoItem.setCategoria(rs.getString("categoria"));
				produtoItem.setPrecoDeCompra(rs.getString("preco_de_compra"));
				produtoItem.setPrecoDeVenda(rs.getString("preco_de_venda"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setFotoDetalhe(rs.getString("foto_detalhe"));
				produtoItem.setGrupoDePrecificacao(rs.getString("grupo_de_precificacao"));
				produtoItem.setFoto(rs.getString("foto"));
				produtoItem.setQuantidade(rs.getString("quantidade"));
				produtoItem.setCdSistema(rs.getString("cd_sistema"));
				produtoItem.setStatus(rs.getString("status"));
				produtoItem.setDtCadastro(rs.getString("dt_cadastro"));
				produtoItem.setObservacao(rs.getString("observacao"));
				
				// adicionando o objeto à lista
				produtos.add(produtoItem);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Listar os Produtos pela Pesquisa por Filtro
	
	
	/**
	 * Metodo para Gravar um registro novo no Estoque
	 * @param entidade
	 * @return
	 */
	public void gravarRegistroEstoque() {
		ProdutoDAO dao = new ProdutoDAO();
		EstoqueDAO estoqueDAO = new EstoqueDAO();
		Produto produto = new Produto();
		Estoque estoque = new Estoque();
		
		// consulta o ultimo Produto cadastrado para poder pegar o ID do Produto,
		// e salvar na primeira entrada do Estoque
		List<Produto> ultimoProduto = dao.consultarUltimoProdutoCadastrado(produto);
		
		// salva os atributos do ultimo Produto cadastrado no Estoque, 
		// pra poder dar a primeira entrada no Estoque
		estoque.setIdProduto(ultimoProduto.get(0).getId());
		estoque.setTipo("entrada");
		estoque.setQuantidadeEntradaSaida(ultimoProduto.get(0).getQuantidade());
		estoque.setValorCusto(ultimoProduto.get(0).getPrecoDeCompra());
		estoque.setFornecedor("Primeira entrada no Estoque");
		estoque.setDtEntrada(ultimoProduto.get(0).getDtCadastro());
		estoque.setQuantidadeFinal(ultimoProduto.get(0).getQuantidade());
		estoque.setDtCadastro(ultimoProduto.get(0).getDtCadastro());
		
		// cria a primeira entrada do produto no "Estoque"
		estoqueDAO.salvar(estoque);
	} // Gravar um registro novo no Estoque
	
}
