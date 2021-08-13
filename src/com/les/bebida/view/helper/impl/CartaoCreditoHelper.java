package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.les.bebida.core.dominio.CartaoDeCredito;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;
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
        
        if (("CONSULTAR").equals(operacao)) {
        	cartaoDeCredito = new CartaoDeCredito();
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
        	
        	// Atribuindo os valores capturados do HTML para o Cartao de Credito
        	cartaoDeCredito.setNome(nome);
        	cartaoDeCredito.setNum_cartao(num_cartao);
        	cartaoDeCredito.setDt_validade(dt_validade);
        	cartaoDeCredito.setBandeira(bandeira);
        	cartaoDeCredito.setCod_seguranca(cod_seguranca);
        	cartaoDeCredito.setFlgPreferencial(flg_preferencial);
        	cartaoDeCredito.setIdCliente(idCliente);
        }
        
        else if (("ALTERAR").equals(operacao)) {
        	
        }
        
        else if (("EXCLUIR").equals(operacao)) {
        	
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
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
	}

}
