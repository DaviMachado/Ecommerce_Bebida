package com.les.bebida.view.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.view.command.ICommand;
import com.les.bebida.view.command.impl.AlterarCommand;
import com.les.bebida.view.command.impl.ConsultarCommand;
import com.les.bebida.view.command.impl.ExcluirCommand;
import com.les.bebida.view.command.impl.SalvarCommand;
import com.les.bebida.view.helper.IViewHelper;
import com.les.bebida.view.helper.impl.CartaoCreditoHelper;
import com.les.bebida.view.helper.impl.ClienteHelper;
import com.les.bebida.view.helper.impl.EnderecoHelper;
import com.les.bebida.view.helper.impl.LoginHelper;
import com.les.bebida.view.helper.impl.ProdutoHelper;

/**
 * Responsavel por processar todas as requisições feita pelo usuario,
 * configurado conforme o arquivo web.xml
 * 
 * @author Davi Rodrigues
 * @date 14/08/2021
 */
public class ControllerServlet extends HttpServlet {
	
	private static Map<String, IViewHelper> viewHelper;
	private static Map<String, ICommand> commands;
	
	// Construtor que inicializa os caminhos da URL
	public ControllerServlet() {
		
		// Mapa dos Commands
		commands = new HashMap<String, ICommand>();
		
		commands.put("SALVAR", new SalvarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		
		
		// Mapa das Views
		viewHelper = new HashMap<String, IViewHelper>();
		
		viewHelper.put("/Ecommerce_Bebida/login", new LoginHelper());
		viewHelper.put("/Ecommerce_Bebida/cadastro", new ClienteHelper());
		viewHelper.put("/Ecommerce_Bebida/cadastroEndereco", new EnderecoHelper());
		viewHelper.put("/Ecommerce_Bebida/cadastroCartaoCredito", new CartaoCreditoHelper());
		viewHelper.put("/Ecommerce_Bebida/cadastroProduto", new ProdutoHelper());
	}
	
	// Servlet Principal do sistema
	protected void processRequest (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
        // para aceitar acentuacao
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // Obtém a operação que será executada
        String operacao = request.getParameter("operacao");
        
        // Obtém a uri que invocou esta servlet
        String uri = request.getRequestURI();
        
        // Obtém uma viewhelper indexado pela uri que invocou esta servlet
        IViewHelper vh = viewHelper.get(uri);
        
        // O View Helper retorna a entidade especifica para a tela que chamou esta servlet
        EntidadeDominio entidade = vh.getEntidade(request);
        
        // Recupera o command correspondente com a operacao
        ICommand command = commands.get(operacao);
        
        // Executa o command que chamará a fachada para executar a operação requisitada
        // o retorno é uma instância da classe resultado que pode conter mensagens de erro
        // ou entidades de retorno
        Resultado resultado = command.execute(entidade);
        
        // Executa o método setView do view helper específico para definir como deverá ser apresentado
        // o resultado para o usuário
        vh.setView(resultado, request, response);
	}
	
	// Method doGet que redireciona para o processRequest
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	// Method doPost que redireciona para o processRequest
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
}
