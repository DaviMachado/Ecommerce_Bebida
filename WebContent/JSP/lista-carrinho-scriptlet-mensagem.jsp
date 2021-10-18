<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
	<title>Listagem do Carrinho</title>
	
	<!-- importações para funcionar o Header e o Footer -->
	<link href="./CSS/bootstrap.min.css" rel="stylesheet">
	<link href="./CSS/shop-homepage.css" rel="stylesheet">
	<link href="./CSS/form-default.css" rel="stylesheet" type="text/css">
</head>

<%
	EnderecoDAO enderecoDAO = new EnderecoDAO();
	CartaoDeCreditoDAO cartaoDAO = new CartaoDeCreditoDAO();
	List<Produto> produtosEmSessao = new ArrayList<>();
	List<Endereco> enderecosCliente = new ArrayList<>();
	List<CartaoDeCredito> cartoesCliente = new ArrayList<>();
	List<Cupom> cuponsSessao = new ArrayList<>();
	Usuario usuarioLogado = new Usuario();

	//cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
	HttpSession sessao = request.getSession();
	// pega o objeto salvo em Sessão com o nome "itensCarrinho",
	// e passa para o "produtosEmSessao" (fazendo o CAST para o tipo List<Produto>)
	produtosEmSessao = (List<Produto>) sessao.getAttribute("itensCarrinho");
	// pega o objeto salvo em Sessão com o nome "usuarioLogado",
	// e passa para o novo objeto criado com o nome "usuarioLogado", (fazendo o CAST para o tipo Usuario)
	usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
	// pega o objeto salvo em Sessão com o nome "cupons",
	// e passa para o "cuponsSessao" (fazendo o CAST para o tipo List<Cupom>)
	cuponsSessao = (List<Cupom>) sessao.getAttribute("cupons");
	
	// consulta todos os endereços cadastrados pelo Cliente
	enderecosCliente = enderecoDAO.consultarEnderecoByIdCliente(usuarioLogado.getId());
	// comsulta todos os cartões de credito cadastrados pelo cliente
	cartoesCliente = cartaoDAO.consultarCartaoDeCreditoByIdCliente(usuarioLogado.getId());
	
	// pega a mensagem que estava pendurado na requisição,
	// que foi enviado pelo arquivo "CarrinhoHelper"
	String mensagemStrategy = (String)request.getAttribute("mensagemStrategy");
	  
	// IF adicionado para não estourar NullPointerException na variavel
	// "mensagemStrategy", pois quando ela esta sendo aberta pela primeira vez,
	// (apos validar o Carrinho), ela pode ficar nula
	if(mensagemStrategy == null){
		mensagemStrategy = "Carrinho aberto!";
	}
	
	double total_itens = 0;
	double total_frete = 0;
	double total_pedido = 0;
	double desconto_cupons = 0;
	
	// ajusta o bug de quando abrir o carrinho pela primeira vez,
	// o valor do cupom criado na sessão, não seja atribuido com o valor nulo
	if (!cuponsSessao.isEmpty()) {
		// calculo do desconto (todos os cupons vinculado na Sessão)
		for(Cupom cupomDaSessao : cuponsSessao) {
			desconto_cupons += Double.parseDouble(cupomDaSessao.getValor());
		}
	}
	
	// faz a somatória dos itens selecionados no carrinho
	for(Produto produto : produtosEmSessao) {
		// calculo do total dos itens (quantidade do item (*) o valor do item "preço de venda")
		total_itens += (Double.parseDouble(produto.getQuantidadeSelecionada()) * Double.parseDouble(produto.getPrecoDeVenda()));
		
		// calculo do frete (quantidade do item (*) 10 centavos)
		total_frete += (Double.parseDouble(produto.getQuantidadeSelecionada()) * 0.10);
	}
	
	// faz o arredondamento da variavel "total_itens" para 2 casas decimais
	total_itens = Math.round(total_itens * 100);
	total_itens = total_itens/100;
	
	// faz o arredondamento da variavel "total_frete" para 2 casas decimais
	total_frete = Math.round(total_frete * 100);
	total_frete = total_frete/100;
	
	// calculo do total do pedido (total dos itens (+) total do frete)
	total_pedido = total_itens + total_frete;
	
	// calculo o total do pedido com o desconto dos cupons
	total_pedido = (total_pedido - desconto_cupons);
	
	// faz o arredondamento da variavel "total_pedido" para 2 casas decimais
	total_pedido = Math.round(total_pedido * 100);
	total_pedido = total_pedido/100;
	
	// faz o arredondamento da variavel "desconto_cupons" para 2 casas decimais
	desconto_cupons = Math.round(desconto_cupons * 100);
	desconto_cupons = desconto_cupons/100;
%>

<body onload="AtivaModal()">

  <!-- Header -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp" class="navbar-brand">Ecommerce de Bebida</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <%// foi utilizado a tag "${}" para poder escrever o objeto salvo em sessão dentro da tela %>
            <a class="nav-link">Bem vindo ${usuarioLogado.nome} !</a>
            <span class="sr-only">(current)</span>
          </li>
          <li class="nav-item">
            <form action="http://localhost:8080/Ecommerce_Bebida/login">
            	<button type="submit" class="btn btn-danger pull-right" name="operacao" value="EXCLUIR">Sair</button>
            </form>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- Fim Header -->
  
  	<h2 style="margin-top: 30px; margin-left: 70px">Carrinho</h2>

	<table border="1" style="margin-top: 30px; margin-left: 70px; margin-right: 70px;">
		<tr>
			<th>Nome</th>
            <th width="65%">Descrição</th>
            <th>Valor de Venda</th>
        </tr>
		<%
			for(Produto produto : produtosEmSessao) {
		%>
			<tr>
				<td><%=produto.getNome() %></td>
				<td><%=produto.getDescricao() %></td>
				<td><%=produto.getPrecoDeVenda() %></td>
				<td>
					<!-- form responsavel por adicionar ou retirar 1 item do carrinho selecionado -->
					<form class="form" action="http://localhost:8080/Ecommerce_Bebida/carrinho">
						<div class="form-row">
							<button class="btn btn-success" name="tipoDeOperacao" value="adicao" style="height: 40px !important; margin-top: 26px;">+</button>
							
							<div style="width: 60px !important; margin-left: 10px;">
						      <label>Qtde</label>
						      <input type="text" class="form-control" name="quantidadeSelecionada" placeholder="Qtde" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="3" value="<%=produto.getQuantidadeSelecionada() %>">
						    </div>
						    
						    <button class="btn btn-danger" name="tipoDeOperacao" value="subtracao" style="height: 40px !important; margin-top: 26px;">-</button>
					    </div>
					    
					    <!-- operação que é acionada através do form -->
			    		<input type="hidden" name="operacao" id="operacao" value="ALTERAR">
			    		<!-- ID do Produto -->
						<input type="hidden" name="idProduto" id="idProduto" value="<%=produto.getId() %>">
					</form>
					
					<!-- form responsavel por excluir o item inteiro do carrinho selecionado -->
					<form class="form" action="http://localhost:8080/Ecommerce_Bebida/carrinho">
						<button class="btn btn-danger" name="operacao" value="EXCLUIR">Deletar</button>
						
						<!-- ID do Produto -->
						<input type="hidden" name="idProduto" id="idProduto" value="<%=produto.getId() %>">
					</form>
				</td>
			</tr>
		<%
		}
		%>
	</table>
	
	<hr align="right" width="20%" size="5" color="black" style="margin-right: 70px;"/>
	
	<p class="card-text" align="right" style="margin-right: 70px;"><b>Total dos Itens:</b> <%=total_itens %></p>
	<p class="card-text" align="right" style="margin-right: 70px;"><b>Total do Frete:</b> <%=total_frete %>0</p>
	<p class="card-text" align="right" style="margin-right: 70px;"><b>Desconto do Cupom:</b> <%=desconto_cupons %></p>
	<p class="card-text" align="right" style="margin-right: 70px;"><b>Total do Pedido:</b> <%=total_pedido %></p>
	
 	<hr align="right" width="20%" size="5" color="black" style="margin-right: 70px;"/>
	
	<fieldset class="form-group fieldset_form" style="margin-top: 30px; margin-bottom: 10px !important;">
		
		<table border="1" style="margin-top: 30px; margin-left: 70px; margin-right: 70px;">
			<tr>
				<th>Nome</th>
	            <th>Valor do Cupom</th>
	        </tr>
			<%
				for(Cupom cupomDaSessao : cuponsSessao) {
			%>
			<tr>
				<td><%=cupomDaSessao.getNome() %></td>
				<td><%=cupomDaSessao.getValor() %></td>
			</tr>
			<%
				}
			%>
		</table>
		
		<!-- form para o verificar o cupom -->
		<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/verificaCupom">
			<div class="form-row">
				<div class="form-group col-md-10">
		  			<!-- adicionado uma coluna com tamanho md-10 em branca para alinhar o campo na pagina -->
		  		</div>
		  		
		  		<!-- Cupom -->
			    <div class="form-group col-md-2">
			      <label>Cupom</label>
			      <input type="text" class="form-control" name="cupom" placeholder="Cupom" maxlength="15" required>
			    </div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-8">
		  			<!-- adicionado uma coluna com tamanho md-8 em branca para alinhar o botão da pagina -->
		  		</div>
		  		
		  		<!-- Botões CRUD -->
		  		<div class="form-group col-md-4">
		  			<div align="right">
						<button class="btn btn-warning" name="operacao" value="CONSULTAR">Validar Cupom</button>
					</div>
		  		</div>
			</div>
			
			<!-- ID do Cliente -->
			<input type="hidden" name="idCliente" id="idCliente" value="<%=usuarioLogado.getId() %>">
		</form>
		
		<!-- form para o cadastro do pedido -->
		<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastroPedido">
			<div class="form-row">
				<!-- Endereço -->
		  		<div class="form-group col-md-4">
		  		<label>Endereço de Entrega</label>
		
		  			<select name="selecioneEndereco" class="form-control" placeholder="Selecione um Endereço" required>
				      	<option value="" disabled selected>Selecione uma opção...</option>
				      	<% 
					      	for(Endereco endereco : enderecosCliente) {
					      	
							// lista todos os endereços do cliente indexado com o ID do endereço dentro do "value", de cada TAG "<option>".
						%>
						<option value="<%=endereco.getId()%>"><%=endereco.getApelido()%></option>
				      	<%
							}
						%>
			      	</select>
			      	<a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_Endereco.jsp">Novo Endereço</a>
		  		</div>
		  		
		  		<!-- Forma de Pagamento -->
		  		<div class="form-group col-md-4">
		  		<label>Forma de Pagamento</label>
	
		  			<select name="selecioneFormadePagamento" id="selecioneFormadePagamento" class="form-control" placeholder="Selecione uma Forma de Pagamento" onclick="escondeCampos()" required>
				      	<option value="" disabled selected>Selecione uma opção...</option>
				      	<option value="boleto">Boleto</option>
				      	<option value="cartao">Cartão de Crédito</option>
			      	</select>
		  		</div>
			</div>
			
			<div id="linhaCartao1" class="form-row" style="visibility: hidden">
		  		<!-- Cartão de Crédito 1 -->
		  		<div class="form-group col-md-4">
		  		<label>Cartão de Crédito 1</label>
		
		  			<select name="selecioneCartao1" class="form-control" placeholder="Selecione um Cartão de Crédito">
				      	<option value="" disabled selected>Selecione uma opção...</option>
				      	<% 
					      	for(CartaoDeCredito cartao : cartoesCliente) {
					      	
							// lista todos os cartões de credito do cliente indexado com o ID do cartão dentro do "value", de cada TAG "<option>".
						%>
						<option value="<%=cartao.getId()%>"><%=cartao.getNome()%></option>
				      	<%
							}
						%>
			      	</select>
		  		</div>
		  		
		  		<!-- Valor Cartão de Crédito 1 -->
			    <div class="form-group col-md-4">
			      <label>Valor 1</label>
			      <input type="text" class="form-control" name="valorCartao1" placeholder="Valor" maxlength="10">
			    </div>
	  		</div>
	  		
	  		<div id="linhaCartao2" class="form-row" style="visibility: hidden">
		  		<!-- Cartão de Crédito 2 -->
		  		<div class="form-group col-md-4">
		  		<label>Cartão de Crédito 2</label>
		
		  			<select name="selecioneCartao2" class="form-control" placeholder="Selecione um Cartão de Crédito">
				      	<option value="" disabled selected>Selecione uma opção...</option>
				      	<% 
					      	for(CartaoDeCredito cartao : cartoesCliente) {
					      	
							// lista todos os cartões de credito do cliente indexado com o ID do cartão dentro do "value", de cada TAG "<option>".
						%>
						<option value="<%=cartao.getId()%>"><%=cartao.getNome()%></option>
				      	<%
							}
						%>
			      	</select>
			      	<a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_cartaoDeCredito.jsp">Novo Cartão</a>
		  		</div>
		  		
		  		<!-- Valor Cartão de Crédito 2 -->
			    <div class="form-group col-md-4">
			      <label>Valor 2</label>
			      <input type="text" class="form-control" name="valorCartao2" placeholder="Valor" maxlength="10">
			    </div>
	  		</div>
			
			<div class="form-row">
				<div class="form-group col-md-8">
		  			<!-- adicionado uma coluna com tamanho md-8 em branca para alinhar o botão da pagina -->
		  		</div>
			  		
				<!-- Botões CRUD -->
		  		<div class="form-group col-md-4">
		  			<div align="right" style="margin-top: 10px">
						<button class="btn btn-success" name="operacao" value="SALVAR">Finalizar Pedido</button>
					</div>
		  		</div>
			</div>
			
			<!-- ID do Cliente -->
			<input type="hidden" name="idCliente" id="idCliente" value="<%=usuarioLogado.getId() %>">
			<!-- ID do Cupom -->
			<input type="hidden" name="total_cupons" id="total_cupons" value="<%=desconto_cupons %>">
			<!-- Total dos Itens -->
		    <input type="hidden" name="total_itens" id="total_itens" value="<%=total_itens %>">
		    <!-- Total do Frete -->
		    <input type="hidden" name="total_frete" id="total_frete" value="<%=total_frete %>">
		    <!-- Total do Pedido -->
		    <input type="hidden" name="total_pedido" id="total_pedido" value="<%=total_pedido %>">
		</form>
	</fieldset>
	
	<!-- Botão Voltar -->
	<div align="right" style="margin-top: 10px;">
 		<a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-right: 70px; margin-bottom: 10px;"></a>
	</div>
	 
  	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
  	  
  	  <!-- Bootstrap core JavaScript -->
  	  <script src="./JQUERY/jquery.min.js"></script>
  	  <script src="./JS/bootstrap.bundle.min.js"></script>
  	  
  	  <!-- Função JavaScript para esconder os campos -->
  	  <script src="./JS/formaDePagamento.js"></script>
  	  
  	<!-- Modal -->
	<div class="modal fade" id="modal-mensagem">
	   <div class="modal-dialog">
	   		<div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
	                <h4 class="modal-title">Mensagem</h4>
	            </div>
	            <div class="modal-body">
	                <p><% out.println(mensagemStrategy); %></p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
	            </div>
	        </div>
	    </div>
	</div>
		
	<!-- Botão para chamar a Modal -->
	<button style="display: none" id="idModal" class="btn btn-primary" data-toggle="modal" data-target="#modal-mensagem">
		Exibir mensagem da modal
	</button>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
    // Função que irá ativar a Modal com a mensagem retornada do BackEnd,
    // essa função é carregada junto ao carregamento da página com o evento ONLOAD, dentro da tag <body>.
	    function AtivaModal(){
    		// metodo para poder ativar o "onClick" sem precisar clicar no botão
	    	document.getElementById('idModal').click();
	    }
    </script>
   	<!-- Fim Modal -->
</body>
</html>