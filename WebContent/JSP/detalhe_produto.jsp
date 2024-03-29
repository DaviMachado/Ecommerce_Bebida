<%@page import='com.les.bebida.core.dominio.*'%>

<%@page import="java.util.List"%>

<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Drink Fast</title>

  <!-- Bootstrap core CSS -->
  <link href="./CSS/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="./CSS/shop-item.css" rel="stylesheet">

</head>

	<%
		Usuario userLogado = new Usuario();
		
		// pega o Produto que estava pendurado na requisi��o,
		// que foi enviado pelo arquivo "ItemCarrinhoHelper"
		Produto produto = (Produto)request.getAttribute("produto");
		
		// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
		HttpSession sessao = request.getSession();
		// pega o objeto salvo em Sess�o com o nome "usuarioLogado",
		// e passa para o novo objeto criado com o nome "userLogado", (fazendo o CAST para o tipo Usuario)
		userLogado = (Usuario) sessao.getAttribute("usuarioLogado");
	%>

<body>

  <!-- Navigation -->
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

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">
        <h1 class="my-4">Drink Fast</h1>
        
        <%
        // verifica se o cliente logado � do tipo ADMIN,
        // para mostrar somente as telas do administrador
        	if(userLogado.getTipo().equals("admin")) {
        %>
       	<div class="list-group">
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_ClienteADMIN.jsp" class="list-group-item">Gerenciamento de Clientes</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_produto.jsp" class="list-group-item">Gerenciamento de Produtos</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_estoque.jsp" class="list-group-item">Gerenciamento de Estoque</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/lista-carrinho-scriptlet.jsp" class="list-group-item">Carrinho</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/lista-todos-pedidos-scriptletADMIN.jsp" class="list-group-item">Gerenciamento de Pedidos</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_cupom.jsp" class="list-group-item">Gerenciamento de Cupons</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/grafico_chart.jsp" class="list-group-item">Gerenciamento de Gr�ficos</a>
        </div>
        <%
        	}
        %>
        
        <%
        // verifica se o cliente logado � do tipo CLIENTE,
        // para mostrar somente as telas do cliente
        	if(userLogado.getTipo().equals("cliente")) {
        %>
        <div class="list-group">
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_Cliente.jsp" class="list-group-item">Meus Dados</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_Endereco.jsp" class="list-group-item">Meus Endere�os</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_cartaoDeCredito.jsp" class="list-group-item">Meus Cart�es de cr�ditos</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/lista-carrinho-scriptlet.jsp" class="list-group-item">Carrinho</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/lista-pedidos-scriptletCLIENTE.jsp" class="list-group-item">Meus Pedidos</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/lista-cupons-scriptletCLIENTE.jsp" class="list-group-item">Meus Cupons</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/editar_somente_senha_Cliente.jsp" class="list-group-item">Alterar Senha</a>
        </div>
        <%
        	}
        %>
        
      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">

        <div class="card mt-4" style="margin-bottom: 20px;">
          <img class="card-img-top img-fluid" src="<%=produto.getFotoDetalhe() %>" alt="">
          <div class="card-body">
            <h3 class="card-title"><%=produto.getNome() %></h3>
            <h4>R$ <%=produto.getPrecoDeVenda() %></h4>
            <p class="card-text"><%=produto.getDescricao() %></p>
            <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
            4.0 stars
          </div>
          
		<form class="form" action="http://localhost:8080/Ecommerce_Bebida/carrinho">
			<div class="form-row" style="margin-right: 10px;">
				<div class="form-group col-md-10">
					<!-- adicionado uma coluna com tamanho md-10 em branca para alinhar os bot�o de quantidade da pagina -->
				</div>
				
				<!-- Quantidade -->
			    <div class="form-group col-md-2">
			      <label>Quantidade</label>
			      <input type="text" class="form-control" name="quantidadeSelecionada" placeholder="Qtde" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="3">
			    </div>
		    </div>
			
				
			<!-- Bot�es CRUD -->
  			<div align="right" style="margin-right: 10px;">
				<button class="btn btn-success" name="operacao" value="SALVAR">Adicionar ao carrinho</button>
			</div>
			
			<!-- Bot�o Voltar -->
			<div align="right" style="margin-top: 10px; margin-right: 10px;">
				<!--<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">-->
				<a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-left: 300px;"></a>
			</div>
			
			<!-- ID do Cliente -->
			<input type="hidden" name="idProduto" id="idProduto" value="<%=produto.getId() %>">
		</form>
          
        </div>
        <!-- /.card -->

      </div>
      <!-- /.col-lg-9 -->

    </div>

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="./JQUERY/jquery.min.js"></script>
  <script src="./JS/bootstrap.bundle.min.js"></script>

</body>

</html>
