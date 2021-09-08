package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dominio.Cupom;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.PedidoTroca;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.view.helper.IViewHelper;

public class LoginHelper implements IViewHelper {

	Usuario usuario = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
        String login = null;
        String senha = null;
        String confirmarSenha = null;
		String id = null;
        String nome = null;
        String telefone = null;
		
		if (("CONSULTAR").equals(operacao)) {
			usuario = new Usuario();
			
			// Atributos da classe Usuario
			login = request.getParameter("email");
			senha = request.getParameter("senha");
			
			// Atribuindo os valores capturados do HTML para o Usuario
			usuario.setLogin(login);
			usuario.setSenha(senha);
		}
		
		else if (("SALVAR").equals(operacao)) {
			usuario = new Usuario();
			
			// Atributos da classe Usuario
			login = request.getParameter("email");
			senha = request.getParameter("senha");
			confirmarSenha = request.getParameter("confirmarSenha");
			nome = request.getParameter("nome");
			telefone = request.getParameter("telefone");
			
			// Atribuindo os valores capturados do HTML para o Usuario
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setConfirmarSenha(confirmarSenha);	
			usuario.setNome(nome);
			usuario.setTelefone(telefone);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			
		}
		
		return usuario;
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
				// feito o CAST de Entidade para o Usuario (pegando o primeiro indice de Entidade)
				Usuario usuario = (Usuario) entidades.get(0);
				
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				// salva na sessão o objeto "usuarioLogado", recebendo o valor de "usuario"
				sessao.setAttribute("usuarioLogado", usuario);
				
				List<Produto> itensCarrinho = new ArrayList<>();
				// salva na sessão o objeto "itensCarrinho", para quando for clicado no botão de "Adicionar ao carrinho",
				// da tela do detalhes do produto, ele poder adicionar os produtos selecionados para o carrinho
				sessao.setAttribute("itensCarrinho", itensCarrinho);
				
				Cupom cupom = new Cupom();
				// salva na sessão o objeto "cupom", que será calculado dentro da tela do carrinho
				sessao.setAttribute("cupom", cupom);
				
				List<PedidoTroca> itensPedidoTroca = new ArrayList<>();
				// salva na sessão o objeto "itensPedidoTroca", para quando for clicado no botão de "Solicitar Troca",
				// da tela do detalhes do pedido, ele poder adicionar os itens do pedido selecionados para gerar um pedido de troca
				sessao.setAttribute("itensPedidoTroca", itensPedidoTroca);
				
				// Redireciona para o arquivo .JSP
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			} 
			else {
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/login.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Cadastro salvo com sucesso!");
				
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/login.jsp").forward(request, response);
			}
			else {				
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/login.jsp").forward(request, response);
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// Limpa a sessão
				// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
				HttpSession sessao = request.getSession();
				//sessao.removeAttribute("usuarioLogado"); // remove somente 1 atributo criado
				sessao.invalidate(); // destroi o cookie JSESSIONID inteiro e cria outro
				  
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Logout com sucesso!");
				
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/login.jsp").forward(request, response);
			}
			else {
				// se tiver alguma mensagem da Strategy, irá redirecionar para a tela de Login do mesmo jeito
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/login.jsp").forward(request, response);
			}
		}
		
	}

}
