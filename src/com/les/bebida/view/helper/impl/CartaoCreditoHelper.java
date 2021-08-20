package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dominio.CartaoDeCredito;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.view.helper.IViewHelper;

public class CartaoCreditoHelper implements IViewHelper{
	
	CartaoDeCredito cartaoDeCredito = null;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String id = null;
        String nome = null;
        String num_cartao = null;
        String dt_validade = null;
        String bandeira = null;
        String cod_seguranca = null;
        String flg_preferencial = null;
        String idCliente = null;
        String alteraCartao = null;
        
        if (("CONSULTAR").equals(operacao)) {
        	cartaoDeCredito = new CartaoDeCredito();
        	
        	idCliente = request.getParameter("idCliente");
        	
        	cartaoDeCredito.setIdCliente(idCliente);
        }
        
        else if (("SALVAR").equals(operacao)) {
        	cartaoDeCredito = new CartaoDeCredito();
        	
        	// capturando os valores do HTML e passando para o Cartao de Credito
        	nome = request.getParameter("nome");
        	num_cartao = request.getParameter("num_cartao");
        	dt_validade = request.getParameter("dt_validade");
        	bandeira = request.getParameter("bandeira");
        	cod_seguranca = request.getParameter("cod_seguranca");
        	flg_preferencial = request.getParameter("flg_preferencial");
        	idCliente = request.getParameter("idCliente");
        	alteraCartao = request.getParameter("alteraCartao");
        	
        	// Atribuindo os valores capturados do HTML para o Cartao de Credito
        	cartaoDeCredito.setNome(nome);
        	cartaoDeCredito.setNum_cartao(num_cartao);
        	cartaoDeCredito.setDt_validade(dt_validade);
        	cartaoDeCredito.setBandeira(bandeira);
        	cartaoDeCredito.setCod_seguranca(cod_seguranca);
        	cartaoDeCredito.setFlgPreferencial(flg_preferencial);
        	cartaoDeCredito.setIdCliente(idCliente);
        	cartaoDeCredito.setAlteraCartao(alteraCartao);
        }
        
        else if (("ALTERAR").equals(operacao)) {
        	cartaoDeCredito = new CartaoDeCredito();
        	
        	// capturando os valores do HTML e passando para o Cartao de Credito
        	id = request.getParameter("idCartaoDeCredito");
        	nome = request.getParameter("nome");
        	num_cartao = request.getParameter("num_cartao");
        	dt_validade = request.getParameter("dt_validade");
        	bandeira = request.getParameter("bandeira");
        	cod_seguranca = request.getParameter("cod_seguranca");
        	flg_preferencial = request.getParameter("flg_preferencial");
        	alteraCartao = request.getParameter("alteraCartao");
        	
        	// Atribuindo os valores capturados do HTML para o Cartao de Credito
        	cartaoDeCredito.setId(id);
        	cartaoDeCredito.setNome(nome);
        	cartaoDeCredito.setNum_cartao(num_cartao);
        	cartaoDeCredito.setDt_validade(dt_validade);
        	cartaoDeCredito.setBandeira(bandeira);
        	cartaoDeCredito.setCod_seguranca(cod_seguranca);
        	cartaoDeCredito.setFlgPreferencial(flg_preferencial);
        	cartaoDeCredito.setAlteraCartao(alteraCartao);
        }
        
        else if (("EXCLUIR").equals(operacao)) {
        	cartaoDeCredito = new CartaoDeCredito();
        	
        	id = request.getParameter("idCartaoDeCredito");
        	
        	cartaoDeCredito.setId(id);
        }
		
		return cartaoDeCredito;
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
				Usuario usuarioLogado = new Usuario();
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// pega o objeto salvo em Sessão com o nome "usuarioLogado",
				// e passa para o novo objeto criado com o nome "usuarioLogado", (fazendo o CAST para o tipo Usuario)
				usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
				
				// pendura o "id" do usuario logado na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("idCliente", usuarioLogado.getId());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/lista-cartaoDeCredito-scriptlet.jsp").forward(request, response);
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
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Cadastro do Cartão de Crédito salvo com sucesso!");
				
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
				String alteraCartao = request.getParameter("alteraCartao");
				String id = request.getParameter("idCartaoDeCredito");
				
				// Se eu estiver pela tela de listagem de Cartao de Credito (lista-cartaoDeCredito-scriplet.jsp),
				// vou mandar o parametro "alteraCartao" igual a zero, para poder chamar o arquivo .JSP para edição do Cartao de Credito
				if (alteraCartao.equals("0")) {
					// pendura o "idCartaoDeCredito" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("idCartaoDeCredito", id);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/editar_cartaoDeCredito.jsp").forward(request, response);
				}
				// caso contrário, se eu estiver pela tela de edição do Cartao de Credito (editar_cartaoDeCredito.jsp),
				// o parametro "alteraCartao" vai ser igual a um, então pode editar o cartao de credito,
				// dentro da DAO de Cartao de Credito, vai ter um IF verificando se tem o "alteraCartao"
				else {
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Cadastro do Cartão de Crédito alterado com sucesso!");
					
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
				String idCliente = request.getParameter("idCliente");
				
				// pendura o "idCliente" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("idCliente", idCliente);
				
				// Redireciona para o arquivo .jsp, para poder listar os cartões de creditos atualizados novamente
				request.getRequestDispatcher("JSP/lista-cartaoDeCredito-scriptlet.jsp").forward(request, response);
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
