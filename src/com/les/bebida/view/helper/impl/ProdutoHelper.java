package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dao.impl.EstoqueDAO;
import com.les.bebida.core.dao.impl.ProdutoDAO;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.view.helper.IViewHelper;

public class ProdutoHelper implements IViewHelper{
	
	Produto produto = null;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String id = null;
        String nome = null;
        String descricao = null;
        String categoria = null;
        String preco_de_compra = null;
        String preco_de_venda = null;
        String foto = null;
        String foto_detalhe = null;
        String grupo_de_precificacao = null;
        String quantidade = null;
        String status = null;
        String observacao = null;
        String alteraProduto = null;
        
        if (("CONSULTAR").equals(operacao)) {
        	produto = new Produto();
        }
        
        else if (("SALVAR").equals(operacao)) {
        	produto = new Produto();
        	
        	// capturando os valores do HTML e passando para o Produto
        	nome = request.getParameter("nome");
        	descricao = request.getParameter("descricao");
        	categoria = request.getParameter("selecioneCategoria");
        	preco_de_compra = request.getParameter("preco_de_compra");
        	preco_de_venda = request.getParameter("preco_de_venda");
        	foto = request.getParameter("foto");
        	foto_detalhe = request.getParameter("foto_detalhe");
        	grupo_de_precificacao = request.getParameter("grupo_de_precificacao");
        	quantidade = request.getParameter("quantidade");
        	status = request.getParameter("status");
        	observacao = request.getParameter("observacao");
        	alteraProduto = request.getParameter("alteraProduto");
        	
        	// Atribuindo os valores capturados do HTML para o Produto
        	produto.setNome(nome);
        	produto.setDescricao(descricao);
        	produto.setCategoria(categoria);
        	produto.setPrecoDeCompra(preco_de_compra);
        	produto.setPrecoDeVenda(preco_de_venda);
        	produto.setFoto(foto);
        	produto.setFotoDetalhe(foto_detalhe);
        	produto.setGrupoDePrecificacao(grupo_de_precificacao);
        	produto.setQuantidade(quantidade);
        	produto.setStatus(status);
        	produto.setObservacao(observacao);
        	produto.setAlteraProduto(alteraProduto);
        }
        
        else if (("ALTERAR").equals(operacao)) {
        	produto = new Produto();
        	
        	// capturando os valores do HTML e passando para o Produto
        	id = request.getParameter("idProduto");
        	nome = request.getParameter("nome");
        	descricao = request.getParameter("descricao");
        	categoria = request.getParameter("selecioneCategoria");
        	preco_de_compra = request.getParameter("preco_de_compra");
        	preco_de_venda = request.getParameter("preco_de_venda");
        	foto = request.getParameter("foto");
        	foto_detalhe = request.getParameter("foto_detalhe");
        	grupo_de_precificacao = request.getParameter("grupo_de_precificacao");
        	quantidade = request.getParameter("quantidade");
        	status = request.getParameter("status");
        	observacao = request.getParameter("observacao");
        	alteraProduto = request.getParameter("alteraProduto");
        	
        	// Atribuindo os valores capturados do HTML para o Produto
        	produto.setId(id);
        	produto.setNome(nome);
        	produto.setDescricao(descricao);
        	produto.setCategoria(categoria);
        	produto.setPrecoDeCompra(preco_de_compra);
        	produto.setPrecoDeVenda(preco_de_venda);
        	produto.setFoto(foto);
        	produto.setFotoDetalhe(foto_detalhe);
        	produto.setGrupoDePrecificacao(grupo_de_precificacao);
        	produto.setQuantidade(quantidade);
        	produto.setStatus(status);
        	produto.setObservacao(observacao);
        	produto.setAlteraProduto(alteraProduto);
        }
        
        else if (("EXCLUIR").equals(operacao)) {
        	produto = new Produto();
        	
        	id = request.getParameter("idProduto");
        	
        	produto.setId(id);
        }
		
		return produto;
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
				
				// pendura o "entidadesProdutos" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("entidadesProdutos", entidades);
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/lista-produtos-scriptlet.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
				writer.println(resultado.getMensagem());
				System.out.println("ERRO PARA CONSULTAR!");
				writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
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
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Cadastro do Produto salvo com sucesso!");
				
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
				String alteraProduto = request.getParameter("alteraProduto");
				String id = request.getParameter("idProduto");
				
				// Se eu estiver pela tela de listagem de Produtos (lista-produtos-scriplet.jsp),
				// vou mandar o parametro "alteraProduto" igual a zero, para poder chamar o arquivo .JSP para edição do Produto
				if (alteraProduto.equals("0")) {
					// pendura o "idProduto" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("idProduto", id);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/editar_produto.jsp").forward(request, response);
				}
				// caso contrário, se eu estiver pela tela de edição do Produto (editar_produto.jsp),
				// o parametro "alteraProduto" vai ser igual a um, então pode editar o produto,
				// dentro da DAO de Produto, vai ter um IF verificando se tem o "alteraProduto"
				else {
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Cadastro do Produto alterado com sucesso!");
					
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
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// Redireciona para o arquivo .jsp, para poder listar os produtos atualizados novamente
				request.getRequestDispatcher("JSP/lista-produtos-scriptlet.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			}
		}
		
	}

}
