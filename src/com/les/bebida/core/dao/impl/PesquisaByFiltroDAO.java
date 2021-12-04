package com.les.bebida.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.dominio.PesquisaByFiltro;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Usuario;

public class PesquisaByFiltroDAO extends AbstractJdbcDAO {
	
	/**
	 * Metodo para salvar a Pesquisa por Filtro
	 * @param entidade
	 */
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Salvar
	
	
	/**
	 * Metodo para alterar a Pesquisa por Filtro
	 * @param entidade
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Alterar
	
	
	/**
	 * Metodo para excluir a Pesquisa por Filtro
	 * @param entidade
	 */
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	} // Excluir
	
	
	/**
	 * Metodo para Consultar a Pesquisa por Filtro
	 * @param entidade
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		try {
			PesquisaByFiltro pesquisaByFiltro = (PesquisaByFiltro) entidade;
			List<EntidadeDominio> listPesquisaByFiltro = new ArrayList<>();
			
			PreparedStatement stmt = connection.prepareStatement("select * from produto where nome LIKE ?");
			stmt.setString(1, "%" + pesquisaByFiltro.getNomeProduto() + "%");
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
			
			stmt = connection.prepareStatement("select * from cliente where nome LIKE ? and tipo='cliente'");
			stmt.setString(1, "%" + pesquisaByFiltro.getNomeCliente() + "%");
			rs = stmt.executeQuery();
			
			List<Cliente> clientes = new ArrayList<>();
			while (rs.next()) {
				// criando o objeto Cliente
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				
				cliente.setId(rs.getString("id"));
				
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				// descriptografando a senha que vem do banco,
				// para não acusar erro na Strategy "ValidarSenhaIgualCliente",
				// para poder validar se a senha esta com letra minuscula e maiscula
				String senhaCriptografada = usuario.getSenha();
			    // Decodifica uma string anteriormente codificada usando o método decodeBase64 e
			    // passando o byte[] da string codificada
			    byte[] decoded = Base64.decodeBase64(senhaCriptografada.getBytes());
			    // Converte o byte[] decodificado de volta para a string original
			    String decodedString = new String(decoded);
			    usuario.setSenha(decodedString);
			    
				cliente.setUsuario(usuario);
				
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDt_nasc(rs.getString("dt_Nasc"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCdSistema(rs.getString("cd_sistema"));
				cliente.setStatus(rs.getString("status"));
				cliente.setDtCadastro(rs.getString("dt_cadastro"));
				cliente.setTipo(rs.getString("tipo"));
				
				// adicionando o objeto à lista
				clientes.add(cliente);
			}
			
			List<Pedido> pedidos = new ArrayList<>();
			// se foi encontrado algum Cliente dentro da query a cima (LIKE),
			// e tem algum status para o Pedido
			if (clientes.size() > 0 && pesquisaByFiltro.getStatusPedido() != null) {
				stmt = connection.prepareStatement("select * from pedido where id_cliente=? and status=?");
				stmt.setString(1, clientes.get(0).getId());
				stmt.setString(2, pesquisaByFiltro.getStatusPedido());
				rs = stmt.executeQuery();
				
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
			}
			else if (clientes.size() > 0) {
				stmt = connection.prepareStatement("select * from pedido where id_cliente=?");
				stmt.setString(1, clientes.get(0).getId());
				rs = stmt.executeQuery();
				
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
			}
			
			PesquisaByFiltro novoFiltro = new PesquisaByFiltro();
			
			novoFiltro.setProdutos(produtos);
			novoFiltro.setClientes(clientes);
			novoFiltro.setPedidos(pedidos);
			
			listPesquisaByFiltro.add(novoFiltro);
				
			rs.close();
			stmt.close();
			return listPesquisaByFiltro;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} // Consultar
	
}
