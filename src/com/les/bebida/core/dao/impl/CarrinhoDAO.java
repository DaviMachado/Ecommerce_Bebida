package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.les.bebida.core.dominio.Carrinho;
import com.les.bebida.core.dominio.CartaoDeCredito;
import com.les.bebida.core.dominio.Cupom;
import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Produto;

public class CarrinhoDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar o Carrinho
	 * @param entidade
	 */
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		Carrinho carrinho = (Carrinho) entidade;
		
		ProdutoDAO dao = new ProdutoDAO();
		
		// pesquisa no banco o produto selecionado,
		// conforme o ID do produto que foi pega na tela
		List<Produto> produtoSelecionado = dao.consultarProdutoById(carrinho.getItemCarrinho().getProduto().getId());
		
		// adiciona o produto pesquisado no banco,
		// para o produto que esta selecionado na tela, para poder pegar todos os dados do produto,
		// o Carrinho (entidade) que veio como parametro, esse objeto esta sendo alterado como REFERENCIA,
		// logo consigo buscar esse objeto no resultado no SetView do ViewHelper do CarrinhoHelper
		carrinho.getItemCarrinho().setProduto(produtoSelecionado.get(0));
		
	} // Salvar
	
	
	/**
	 * Metodo para alterar o Carrinho
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Carrinho carrinho = (Carrinho) entidade;
		
		ProdutoDAO dao = new ProdutoDAO();
		
		// pesquisa no banco o produto selecionado,
		// conforme o ID do produto que foi pega na tela
		List<Produto> produtoSelecionado = dao.consultarProdutoById(carrinho.getItemCarrinho().getProduto().getId());
		
		// adiciona o produto pesquisado no banco,
		// para o produto que esta selecionado na tela, para poder pegar todos os dados do produto,
		// o Carrinho (entidade) que veio como parametro, esse objeto esta sendo alterado como REFERENCIA,
		// logo consigo buscar esse objeto no resultado no SetView do ViewHelper do CarrinhoHelper
		carrinho.getItemCarrinho().setProduto(produtoSelecionado.get(0));
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir o Carrinho
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		Carrinho carrinho = (Carrinho) entidade;
		
		ProdutoDAO dao = new ProdutoDAO();
		
		// pesquisa no banco o produto selecionado,
		// conforme o ID do produto que foi pega na tela
		List<Produto> produtoSelecionado = dao.consultarProdutoById(carrinho.getItemCarrinho().getProduto().getId());
		
		// adiciona o produto pesquisado no banco,
		// para o produto que esta selecionado na tela, para poder pegar todos os dados do produto,
		// o Carrinho (entidade) que veio como parametro, esse objeto esta sendo alterado como REFERENCIA,
		// logo consigo buscar esse objeto no resultado no SetView do ViewHelper do CarrinhoHelper
		carrinho.getItemCarrinho().setProduto(produtoSelecionado.get(0));
		
	} // Excluir
	
	
	/**
	 * Metodo para Consultar o Carrinho
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		try {
			Carrinho carrinho = (Carrinho) entidade;
			Carrinho novoCarrinho = new Carrinho();
			List<EntidadeDominio> listCarrinho = new ArrayList<>();
			
			PreparedStatement stmt = connection.prepareStatement("select * from endereco where id_cliente=?");
			stmt.setString(1, carrinho.getIdCliente());
			ResultSet rs = stmt.executeQuery();
			
			List<Endereco> enderecos = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Endereço
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
				
				// adicionando o objeto à lista
				enderecos.add(enderecoItem);
			}
			
			stmt = connection.prepareStatement("select * from cartao_de_credito where id_cliente=?");
			stmt.setString(1, carrinho.getIdCliente());
			rs = stmt.executeQuery();
			
			List<CartaoDeCredito> cartoes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cartao de Credito
				CartaoDeCredito cartaoItem = new CartaoDeCredito();
				
				cartaoItem.setId(rs.getString("id"));
				cartaoItem.setNome(rs.getString("nome"));
				cartaoItem.setNum_cartao(rs.getString("num_cartao"));
				cartaoItem.setCod_seguranca(rs.getString("cd_seguranca"));
				cartaoItem.setDt_validade(rs.getString("dt_validade"));
				cartaoItem.setFlgPreferencial(rs.getString("preferencial"));
				cartaoItem.setIdCliente(rs.getString("id_cliente"));
				cartaoItem.setIdBandeira(rs.getString("id_bandeira"));
				cartaoItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cartoes.add(cartaoItem);
			}
			
			stmt = connection.prepareStatement("select * from cupom where id_cliente=?");
			stmt.setString(1, carrinho.getIdCliente());
			rs = stmt.executeQuery();
			
			List<Cupom> cupons = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cupom
				Cupom cupomItem = new Cupom();
				
				cupomItem.setId(rs.getString("id"));
				cupomItem.setNome(rs.getString("nome"));
				cupomItem.setValor(rs.getString("valor"));
				cupomItem.setTipo(rs.getString("tipo"));
				cupomItem.setUtilizado(rs.getString("utilizado"));
				cupomItem.setIdCliente(rs.getString("id_cliente"));
				cupomItem.setDtCadastro(rs.getString("dt_cadastro"));
				
				// adicionando o objeto à lista
				cupons.add(cupomItem);
			}
			
			novoCarrinho.setEnderecos(enderecos);
			novoCarrinho.setCartoes(cartoes);
			novoCarrinho.setCupons(cupons);
			
			listCarrinho.add(novoCarrinho);
			
			rs.close();
			stmt.close();
			return listCarrinho;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Consultar
	
}
