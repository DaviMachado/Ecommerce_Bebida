<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

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
		ProdutoDAO dao = new ProdutoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		Usuario userLogado = new Usuario();
		
		// pega o id do produto que estava pendurado na requisição,
		// que foi enviado pelo arquivo "ItemCarrinhoHelper"
		String idProduto = (String)request.getAttribute("idProduto");
		
		List<Produto> produto = dao.consultarProdutoById(idProduto);
		
		// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
		HttpSession sessao = request.getSession();
		// pega o objeto salvo em Sessão com o nome "usuarioLogado",
		// e passa para o novo objeto criado com o nome "userLogado", (fazendo o CAST para o tipo Usuario)
		userLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// faz um consulta no banco para pegar todos os dados do cliente logado
		List<Cliente> clienteLogado = clienteDAO.consultarClienteById(userLogado.getId());
	%>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand">Ecommerce de Bebida</a>
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

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">
        <h1 class="my-4">Drink Fast</h1>
        
        <%
        // verifica se o cliente logado é do tipo ADMIN,
        // para mostrar somente as telas do administrador
        	if(clienteLogado.get(0).getTipo().equals("admin")) {
        %>
       	<div class="list-group">
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_ClienteADMIN.jsp" class="list-group-item">Gerenciamento de Clientes</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_produto.jsp" class="list-group-item">Gerenciamento de Produtos</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_estoque.jsp" class="list-group-item">Gerenciamento de Estoque</a>
        </div>
        <%
        	}
        %>
        
        <%
        // verifica se o cliente logado é do tipo CLIENTE,
        // para mostrar somente as telas do cliente
        	if(clienteLogado.get(0).getTipo().equals("cliente")) {
        %>
        <div class="list-group">
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_Cliente.jsp" class="list-group-item">Meus Dados</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_Endereco.jsp" class="list-group-item">Meus Endereços</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_cartaoDeCredito.jsp" class="list-group-item">Meus Cartões de créditos</a>
        </div>
        <%
        	}
        %>
        
      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">

        <div class="card mt-4" style="margin-bottom: 20px;">
          <img class="card-img-top img-fluid" src="http://placehold.it/900x400" alt="">
          <div class="card-body">
            <h3 class="card-title"><%=produto.get(0).getNome() %></h3>
            <h4>$24.99</h4>
            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente dicta fugit fugiat hic aliquam itaque facere, soluta. Totam id dolores, sint aperiam sequi pariatur praesentium animi perspiciatis molestias iure, ducimus!</p>
            <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
            4.0 stars
          </div>
          
		<form class="form" action="http://localhost:8080/Ecommerce_Bebida/carrinho">
			<!-- Botões CRUD -->
  			<div align="right" style="margin-top: 10px; margin-right: 10px;">
				<button class="btn btn-success" name="operacao" value="SALVAR">Adicionar ao carrinho</button>
			</div>
			
			<!-- Botão Voltar -->
			<div align="right" style="margin-top: 10px; margin-right: 10px;">
				<!--<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">-->
				<a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-left: 300px;"></a>
			</div>
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
