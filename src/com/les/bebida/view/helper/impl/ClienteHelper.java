package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.view.helper.IViewHelper;

public class ClienteHelper implements IViewHelper {
	
	Cliente cliente = null;
	Usuario usuario = null;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String id = null;
        String nome = null;
        String cpf = null;
        String dtNasc = null;
        String telefone = null;
        String sexo = null;
        String status = null;
        String login = null;
        String senha = null;
        String confirmarSenha = null;
        String alteraCliente = null;
		
		if (("CONSULTAR").equals(operacao)) {
			cliente = new Cliente();
		}
		
		else if (("SALVAR").equals(operacao)) {
			cliente = new Cliente();
			usuario = new Usuario();
			
			// Atributos da classe Usuario
			login = request.getParameter("email");
			senha = request.getParameter("senha");
			confirmarSenha = request.getParameter("confirmarSenha");
						
			// Atributos da classe cliente
			telefone = request.getParameter("telefone");
			alteraCliente = request.getParameter("alteraCliente");
			status = request.getParameter("status");
			
			// Atributos da classe pessoa
			nome = request.getParameter("nome");
			cpf = request.getParameter("cpf");
			dtNasc = request.getParameter("dtNasc");
			sexo = request.getParameter("selecioneSexo");
			
			// Atribuindo os valores capturados do HTML para o cliente
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setConfirmarSenha(confirmarSenha);	
			cliente.setUsuario(usuario);
			cliente.setNome(nome);
			cliente.setCpf(cpf);
			cliente.setDt_nasc(dtNasc);
			cliente.setTelefone(telefone);
			cliente.setSexo(sexo);
			cliente.setAlteraCliente(alteraCliente);
			cliente.setStatus(status);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			cliente = new Cliente();
			usuario = new Usuario();
			
			id = request.getParameter("id");
			
			// Atributos da classe Usuario
			login = request.getParameter("email");
			senha = request.getParameter("senha");
			confirmarSenha = request.getParameter("confirmarSenha");
			
			// Atributos da classe cliente
			telefone = request.getParameter("telefone");
			alteraCliente = request.getParameter("alteraCliente");
			status = request.getParameter("status");
			
			// Atributos da classe pessoa
			nome = request.getParameter("nome");
			cpf = request.getParameter("cpf");
			dtNasc = request.getParameter("dtNasc");
			sexo = request.getParameter("selecioneSexo");
			
			// Atribuindo os valores capturados do HTML para o cliente
			cliente.setId(id);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setConfirmarSenha(confirmarSenha);	
			cliente.setUsuario(usuario);
			cliente.setNome(nome);
			cliente.setCpf(cpf);
			cliente.setDt_nasc(dtNasc);
			cliente.setTelefone(telefone);
			cliente.setSexo(sexo);
			cliente.setAlteraCliente(alteraCliente);
			cliente.setStatus(status);
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			cliente = new Cliente();
			
			id = request.getParameter("id");
			
			cliente.setId(id);
		}
		
		return cliente;
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
				// foi utilizado o getEntidades do resultado para poder pegar o cartao
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Cliente (pegando o primeiro indice de Entidade)
				Cliente clientes = (Cliente) entidades.get(0);
				
				// pendura todos os Clientes na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("todosClientes", clientes.getClienteByTipoSomenteCliente());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/lista-clientes-scriptletADMIN.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
				// pendura o "resultado" na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/Home_Page.jsp").forward(request, response);
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// atribui a nova mensagem para poder mostra na pagina .JSP
				resultado.setMensagem("Cadastro do Cliente salvo com sucesso!");
				
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
				// foi utilizado o getEntidades do resultado para poder pegar o cartao
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Cliente (pegando o primeiro indice de Entidade)
				Cliente clientes = (Cliente) entidades.get(0);
				
				String alteraCliente = request.getParameter("alteraCliente");
				
				// Se eu estiver pela tela de listagem de Clientes (lista-clientes-scripletADMIN.jsp),
				// vou mandar o parametro "alteraCliente" igual a zero, para poder chamar o arquivo .JSP para edição do Cliente
				if (alteraCliente.equals("0")) {
					// pendura o "idCliente" para poder mandar para o arquivo .JSP
					request.setAttribute("clientePesquisado", clientes.getClientePesquisado());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/editar_clienteADMIN.jsp").forward(request, response);
				}
				// caso contrário, se eu estiver pela tela de edição do cliente (editar_clienteADMIN.jsp), ou pela tela (formulario_Cliente.jsp)
				// o parametro "alteraCliente" vai ser igual a um, então pode editar o cliente,
				// dentro da DAO de Cliente, vai ter um IF verificando se tem o "alteraCliente"
				else {
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Cadastro do Cliente alterado com sucesso!");
					
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
				// foi utilizado o getEntidades do resultado para poder pegar o cartao
				List<EntidadeDominio> entidades = resultado.getEntidades();
				// feito o CAST de Entidade para o Cliente (pegando o primeiro indice de Entidade)
				Cliente clientes = (Cliente) entidades.get(0);
				
				// pendura todos os cartões do Cliente na requisição para poder mandar para o arquivo .JSP
				request.setAttribute("todosClientes", clientes.getClienteByTipoSomenteCliente());
				
				// Redireciona para o arquivo .jsp, para poder listar os clientes atualizados novamente
				request.getRequestDispatcher("JSP/lista-clientes-scriptletADMIN.jsp").forward(request, response);
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
