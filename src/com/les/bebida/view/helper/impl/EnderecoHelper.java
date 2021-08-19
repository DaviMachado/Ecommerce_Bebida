package com.les.bebida.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.view.helper.IViewHelper;

public class EnderecoHelper implements IViewHelper {
	
	Endereco endereco = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
		String id = null;
        String cep = null;
        String cidade = null;
        String logradouro = null;
        String numero = null;
        String bairro = null;
        String complemento = null;
        String estado = null;
        String apelido = null;
        String tipoEndereco = null;
        String tipoResidencia = null;
        String pais = null;
        String observacao = null;
        
        String idCliente = null;
        String alteraEndereco = null;
		
		if (("CONSULTAR").equals(operacao)) {
			endereco = new Endereco();
			
			idCliente = request.getParameter("idCliente");
			
			endereco.setIdCliente(idCliente);
		}
		
		else if (("SALVAR").equals(operacao)) {
			endereco = new Endereco();
			
			// Atributos da classe endereço
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			logradouro = request.getParameter("logradouro");
			numero = request.getParameter("numero");
			bairro = request.getParameter("bairro");
			complemento = request.getParameter("complemento");
			estado = request.getParameter("selecioneEstado");
			apelido = request.getParameter("apelido");
			tipoEndereco = request.getParameter("tipoEndereco");
			tipoResidencia = request.getParameter("tipoResidencia");
			pais = request.getParameter("pais");
			observacao = request.getParameter("observacao");
			
			idCliente = request.getParameter("idCliente");
			alteraEndereco = request.getParameter("alteraEndereco");
			
			// Atribuindo os valores capturados do HTML para o endereço
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setComplemento(complemento);
			endereco.setEstado(estado);
			endereco.setApelido(apelido);
			endereco.setTipo_Endereco(tipoEndereco);
			endereco.setTipoResidencia(tipoResidencia);
			endereco.setPais(pais);
			endereco.setObservacao(observacao);
			
			endereco.setIdCliente(idCliente);
			endereco.setAlteraEndereco(alteraEndereco);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			endereco = new Endereco();
			
			// Atributos da classe endereço
			id = request.getParameter("idEndereco");
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			logradouro = request.getParameter("logradouro");
			numero = request.getParameter("numero");
			bairro = request.getParameter("bairro");
			complemento = request.getParameter("complemento");
			estado = request.getParameter("selecioneEstado");
			apelido = request.getParameter("apelido");
			tipoEndereco = request.getParameter("tipoEndereco");
			tipoResidencia = request.getParameter("tipoResidencia");
			pais = request.getParameter("pais");
			observacao = request.getParameter("observacao");
			
			idCliente = request.getParameter("idCliente");
			alteraEndereco = request.getParameter("alteraEndereco");
			
			// Atribuindo os valores capturados do HTML para o endereço
			endereco.setId(id);
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setComplemento(complemento);
			endereco.setEstado(estado);
			endereco.setApelido(apelido);
			endereco.setTipo_Endereco(tipoEndereco);
			endereco.setTipoResidencia(tipoResidencia);
			endereco.setPais(pais);
			endereco.setObservacao(observacao);
			
			endereco.setIdCliente(idCliente);
			endereco.setAlteraEndereco(alteraEndereco);
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			endereco = new Endereco();
			
			id = request.getParameter("idEndereco");
			
			endereco.setId(id);
		}
		
		return endereco;
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
				request.getRequestDispatcher("JSP/lista-enderecos-scriptlet.jsp").forward(request, response);
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
				resultado.setMensagem("Cadastro do Endereço salvo com sucesso!");
				
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
				String alteraEndereco = request.getParameter("alteraEndereco");
				String idEndereco = request.getParameter("idEndereco");

				// Se eu estiver pela tela de listagem de endereços (lista-enderecos-scriplet.jsp),
				// não vou mandar o parametro "alteraEndereco" igual a zero, para poder chama o arquivo .JSP para edição do endereço
				if (alteraEndereco.equals("0")) {					
					// pendura o "idEndereco" na requisição para poder mandar para o arquivo .JSP
					request.setAttribute("idEndereco", idEndereco);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/editar_endereco.jsp").forward(request, response);
				}
				// caso contrário, se eu estiver pela tela de edição do endereço (editar_endereco.jsp),
				// vou ter/mandar o parametro "alteraEndereco" igual a um, para poder editar o endereço,
				// dentro da DAO de endereço, vai ter um IF verificando se tem o "alteraEndereco"
				else {
					// atribui a nova mensagem para poder mostra na pagina .JSP
					resultado.setMensagem("Cadastro do Endereço alterado com sucesso!");
					
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
				
				// Redireciona para o arquivo .jsp, para poder listar os endereços atualizados novamente
				request.getRequestDispatcher("JSP/lista-enderecos-scriptlet.jsp").forward(request, response);
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
