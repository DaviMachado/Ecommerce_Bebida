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
	List<Produto> produtosEmSessao = new ArrayList<>();

	//cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
	HttpSession sessao = request.getSession();
	// pega o objeto salvo em Sessão com o nome "itensCarrinho",
	// e passa para o "produtosEmSessao" (fazendo o CAST para o tipo List<Produto>)
	produtosEmSessao = (List<Produto>) sessao.getAttribute("itensCarrinho");
	
	// pega a mensagem que estava pendurado na requisição,
	// que foi enviado pelo arquivo "CarrinhoHelper"
	String mensagemStrategy = (String)request.getAttribute("mensagemStrategy");
	  
	// IF adicionado para não estourar NullPointerException na variavel
	// "mensagemStrategy", pois quando ela esta sendo aberta pela primeira vez,
	// (apos validar o Login), ela fica nula
	if(mensagemStrategy == null){
		mensagemStrategy = "Carrinho aberto!";
	}
	
	double total_itens = 0;
	double total_frete = 0;
	double total_pedido = 0;
	
	// faz a somatória dos itens selecionados no carrinho
	for(Produto produto : produtosEmSessao) {
		total_itens += (Double.parseDouble(produto.getQuantidadeSelecionada()) * Double.parseDouble(produto.getPrecoDeVenda()));
	}
	
	// faz o arredondamento da variavel "total_itens" para 2 casas decimais
	total_itens = Math.round(total_itens * 100);
	total_itens = total_itens/100;
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
	<p class="card-text" align="right" style="margin-right: 70px;"><b>Total do Frete:</b> <%=total_frete %></p>
	<p class="card-text" align="right" style="margin-right: 70px;"><b>Total do Pedido:</b> <%=total_pedido %></p>
	
	 <a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-left: 70px; margin-top: 10px; margin-bottom: 318px;"></a>
	 
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