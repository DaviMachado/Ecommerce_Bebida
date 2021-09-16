<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
	<title>Listagem do Carrinho</title>
	
	<!-- importa��es para funcionar o Header e o Footer -->
	<link href="../CSS/bootstrap.min.css" rel="stylesheet">
	<link href="../CSS/shop-homepage.css" rel="stylesheet">
	<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
</head>

<%
	EnderecoDAO enderecoDAO = new EnderecoDAO();
	CartaoDeCreditoDAO cartaoDAO = new CartaoDeCreditoDAO();
	List<Produto> produtosEmSessao = new ArrayList<>();
	List<Endereco> enderecosCliente = new ArrayList<>();
	List<CartaoDeCredito> cartoesCliente = new ArrayList<>();
	Usuario usuarioLogado = new Usuario();
	Cupom cupomSessao = new Cupom();

	//cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
	HttpSession sessao = request.getSession();
	// pega o objeto salvo em Sess�o com o nome "itensCarrinho",
	// e passa para o "produtosEmSessao" (fazendo o CAST para o tipo List<Produto>)
	produtosEmSessao = (List<Produto>) sessao.getAttribute("itensCarrinho");
	// pega o objeto salvo em Sess�o com o nome "usuarioLogado",
	// e passa para o novo objeto criado com o nome "usuarioLogado", (fazendo o CAST para o tipo Usuario)
	usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
	// pega o objeto salvo em Sess�o com o nome "cupom",
	// e passa para o novo objeto criado com o nome "cupomSessao", (fazendo o CAST para o tipo Cupom)
	cupomSessao = (Cupom) sessao.getAttribute("cupom");
	
	// consulta todos os endere�os cadastrados pelo Cliente
	enderecosCliente = enderecoDAO.consultarEnderecoByIdCliente(usuarioLogado.getId());
	// comsulta todos os cart�es de credito cadastrados pelo cliente
	cartoesCliente = cartaoDAO.consultarCartaoDeCreditoByIdCliente(usuarioLogado.getId());
	
	double total_itens = 0;
	double total_frete = 0;
	double total_pedido = 0;
	double desconto_cupom = 0;
	
	// ajusta o bug de quando abrir o carrinho pela primeira vez,
	// o valor do cupom criado na sess�o, n�o seja atribuido com o valor nulo
	if (cupomSessao.getValor() != null) {
		desconto_cupom = Double.parseDouble(cupomSessao.getValor());
	}
	
	// faz a somat�ria dos itens selecionados no carrinho
	for(Produto produto : produtosEmSessao) {
		// calculo do total dos itens (quantidade do item (*) o valor do item "pre�o de venda")
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
	
	// calculo o total do pedido com o desconto do cupom
	total_pedido = (total_pedido - desconto_cupom);
%>

<body>
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
            <%// foi utilizado a tag "${}" para poder escrever o objeto salvo em sess�o dentro da tela %>
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
            <th width="65%">Descri��o</th>
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
					    
					    <!-- opera��o que � acionada atrav�s do form -->
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
	<p class="card-text" align="right" style="margin-right: 70px;"><b>Desconto do Cupom:</b> <%=desconto_cupom %></p>
	<p class="card-text" align="right" style="margin-right: 70px;"><b>Total do Pedido:</b> <%=total_pedido %></p>
	
	<hr align="right" width="20%" size="5" color="black" style="margin-right: 70px;"/>
	
	<fieldset class="form-group fieldset_form" style="margin-top: 30px; margin-bottom: 10px !important;">
		
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
		  			<!-- adicionado uma coluna com tamanho md-8 em branca para alinhar o bot�o da pagina -->
		  		</div>
		  		
		  		<!-- Bot�es CRUD -->
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
				<!-- Endere�o -->
		  		<div class="form-group col-md-5">
		  		<label>Endere�o de Entrega</label>
		
		  			<select name="selecioneEndereco" class="form-control" placeholder="Selecione um Endere�o" required>
				      	<option value="" disabled selected>Selecione uma op��o...</option>
				      	<% 
					      	for(Endereco endereco : enderecosCliente) {
					      	
							// lista todos os endere�os do cliente indexado com o ID do endere�o dentro do "value", de cada TAG "<option>".
						%>
						<option value="<%=endereco.getId()%>"><%=endereco.getApelido()%></option>
				      	<%
							}
						%>
			      	</select>
			      	<a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_Endereco.jsp">Novo Endere�o</a>
		  		</div>
		  		
		  		<!-- Cart�o de Cr�dito -->
		  		<div class="form-group col-md-5">
		  		<label>Cart�o de Cr�dito</label>
		
		  			<select name="selecioneCartao" class="form-control" placeholder="Selecione um Cart�o de Cr�dito" required>
				      	<option value="" disabled selected>Selecione uma op��o...</option>
				      	<% 
					      	for(CartaoDeCredito cartao : cartoesCliente) {
					      	
							// lista todos os cart�es de credito do cliente indexado com o ID do cart�o dentro do "value", de cada TAG "<option>".
						%>
						<option value="<%=cartao.getId()%>"><%=cartao.getNome()%></option>
				      	<%
							}
						%>
			      	</select>
			      	<a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_cartaoDeCredito.jsp">Novo Cart�o</a>
		  		</div>
			</div>
			
			<div class="form-row">
				<div class="form-group col-md-8">
		  			<!-- adicionado uma coluna com tamanho md-8 em branca para alinhar o bot�o da pagina -->
		  		</div>
			  		
				<!-- Bot�es CRUD -->
		  		<div class="form-group col-md-4">
		  			<div align="right" style="margin-top: 32px">
						<button class="btn btn-success" name="operacao" value="SALVAR">Finalizar Pedido</button>
					</div>
		  		</div>
			</div>
			
			<!-- ID do Cliente -->
			<input type="hidden" name="idCliente" id="idCliente" value="<%=usuarioLogado.getId() %>">
			<!-- ID do Cupom -->
			<input type="hidden" name="idCupom" id="idCupom" value="<%=cupomSessao.getId() %>">
			<!-- Total dos Itens -->
		    <input type="hidden" name="total_itens" id="total_itens" value="<%=total_itens %>">
		    <!-- Total do Frete -->
		    <input type="hidden" name="total_frete" id="total_frete" value="<%=total_frete %>">
		    <!-- Total do Pedido -->
		    <input type="hidden" name="total_pedido" id="total_pedido" value="<%=total_pedido %>">
		</form>
	</fieldset>
	
	<!-- Bot�o Voltar -->
	<div align="right" style="margin-top: 10px;">
 		<a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-right: 70px; margin-bottom: 318px;"></a>
	</div>
	 
  	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
</body>
</html>