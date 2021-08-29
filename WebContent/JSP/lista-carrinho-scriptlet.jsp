<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
	<title>Listagem do Carrinho</title>
	
	<!-- importações para funcionar o Header e o Footer -->
	<link href="../CSS/bootstrap.min.css" rel="stylesheet">
	<link href="../CSS/shop-homepage.css" rel="stylesheet">
	<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
</head>

<%
	List<Produto> produtosEmSessao = new ArrayList<>();

	//cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
	HttpSession sessao = request.getSession();
	// pega o objeto salvo em Sessão com o nome "itensCarrinho",
	// e passa para o "produtosEmSessao" (fazendo o CAST para o tipo List<Produto>)
	produtosEmSessao = (List<Produto>) sessao.getAttribute("itensCarrinho");
	
	double total_itens = 0;
	double total_frete = 0;
	double total_pedido = 0;
	
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
	<p class="card-text" align="right" style="margin-right: 70px;"><b>Total do Pedido:</b> <%=total_pedido %></p>
	
	<hr align="right" width="20%" size="5" color="black" style="margin-right: 70px;"/>
	
	<fieldset class="form-group fieldset_form" style="margin-top: 30px; margin-bottom: 10px !important;">
		<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastroPedido">
			<div class="form-row">
				<!-- Endereço -->
		  		<div class="form-group col-md-5">
		  		<label>Endereço de Entrega</label>
		
		  			<select name="selecioneEndereco" class="form-control" placeholder="Selecione um Endereço" required>
				      	<option value="" disabled selected>Selecione uma opção...</option>
				      	<option value="masculino">teste</option>
				      	<option value="feminino">teste</option>
			      	</select>
		  		</div>
		  		
		  		<!-- Cartão de Crédito -->
		  		<div class="form-group col-md-5">
		  		<label>Cartão de Crédito</label>
		
		  			<select name="selecioneCartao" class="form-control" placeholder="Selecione um Cartão de Crédito" required>
				      	<option value="" disabled selected>Selecione uma opção...</option>
				      	<option value="masculino">teste</option>
				      	<option value="feminino">teste</option>
			      	</select>
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
		  			<div align="right" style="margin-top: 32px">
						<button class="btn btn-success" name="operacao" value="SALVAR">Finalizar Pedido</button>
					</div>
		  		</div>
			</div>
		</form>
	</fieldset>
	
	<!-- Botão Voltar -->
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