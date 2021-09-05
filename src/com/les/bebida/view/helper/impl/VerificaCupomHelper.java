package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dao.impl.CupomDAO;
import com.les.bebida.core.dominio.Cupom;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.VerificaCupom;
import com.les.bebida.view.helper.IViewHelper;

public class VerificaCupomHelper implements IViewHelper {

	VerificaCupom verificaCupom = null;
	Cupom cupom = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String nomeCupom = null;
		String idCliente = null;
		
		if (("CONSULTAR").equals(operacao)) {
			verificaCupom = new VerificaCupom();
			cupom = new Cupom();
			
			// capturando os valores do HTML e passando para o Cupom, logo em sequencia passando para o Verifica Cupom
			nomeCupom = request.getParameter("cupom");
			idCliente = request.getParameter("idCliente");
			
			// Atribuindo os valores capturados do HTML para o Verifica Cupom
			cupom.setNome(nomeCupom);
			verificaCupom.setCupom(cupom);
			verificaCupom.setIdCliente(idCliente);
		}
		
		else if (("SALVAR").equals(operacao)) {

		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return verificaCupom;
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
				String nomeCupom = request.getParameter("cupom");
				String idCliente = request.getParameter("idCliente");
				CupomDAO dao = new CupomDAO();
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				
				// busca todos os Cupons no sistema com o mesmo nome que foi digitado na tela
				List<Cupom> cupons = dao.consultarCupomByNome(nomeCupom);
				
				// faz um laço com todos os Cupons
				for (Cupom coupon : cupons) {
					// 1º se o primeiro Cupom encontrado for do tipo "promocional",
					// o mesmo será salvo na Sessão
					if (coupon.getTipo().equals("promocional")) {
						// salva na Sessão o Cupom,
						sessao.setAttribute("cupom", coupon);
						break;
					}
					// 2º caso contrário, verifica se o Cupom é do mesmo cliente logado,
					// e se o Cupom ja foi utilizado, se ainda não foi utilizado, o mesmo será salvo na Sessão
					else {
						if (coupon.getIdCliente().equals(idCliente)) {
							if ( coupon.getUtilizado().equals("nao")) {
								// salva na Sessão o Cupom,
								sessao.setAttribute("cupom", coupon);
								break;
							}
						}
					}
				}
				
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Cupom aplicado com sucesso!");
				
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/lista-carrinho-scriptlet-mensagem.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/lista-carrinho-scriptlet-mensagem.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {

		}
		
	}

}
