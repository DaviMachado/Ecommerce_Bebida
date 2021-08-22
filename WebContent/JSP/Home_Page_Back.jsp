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
  <link href="../CSS/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="../CSS/shop-homepage.css" rel="stylesheet">

</head>

	<%
		ClienteDAO dao = new ClienteDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Usuario userLogado = new Usuario();
		Produto produto = new Produto();
		
		// guarda todos os produtos cadastrados no sistema na variavel "produtos", para ser listada na Home Page
		List<EntidadeDominio> produtos = produtoDAO.consultar(produto);
		
		// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
		HttpSession sessao = request.getSession();
		// pega o objeto salvo em Sessão com o nome "usuarioLogado",
		// e passa para o novo objeto criado com o nome "userLogado", (fazendo o CAST para o tipo Usuario)
		userLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// faz um consulta no banco para pegar todos os dados do cliente logado
		List<Cliente> clienteLogado = dao.consultarClienteById(userLogado.getId());
		
		// pega a mensagem que estava pendurado na requisição,
		// que foi enviado pelo arquivo "ClienteHelper"
		String mensagemStrategy = (String)request.getAttribute("mensagemStrategy");
		  
		// IF adicionado para não estourar NullPointerException na variavel
		// "mensagemStrategy", pois quando ela esta sendo aberta pela primeira vez,
		// (apos validar o Login), ela fica nula
		if(mensagemStrategy == null){
			mensagemStrategy = "Bem Vindo(a) ao site Drink Fast !";
		}
	%>

<body onload="AtivaModal()">

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
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/lista-carrinho-scriptlet.jsp" class="list-group-item">Carrinho</a>
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
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/lista-carrinho-scriptlet.jsp" class="list-group-item">Carrinho</a>
        </div>
        <%
        	}
        %>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">

        <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
          </ol>
          <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
              <img class="d-block img-fluid" src="../Imagens/carousel/DRINK_FAST.png" alt="First slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="../Imagens/carousel/tequilas.png" alt="Second slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="../Imagens/carousel/refrigerantes.png" alt="Third slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="../Imagens/carousel/cervejas.png" alt="Fourth slide">
            </div>
          </div>
          <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
        
        <!-- Listagens dos produtos cadastrados no sistema -->
        <div class="row">
        	<%
        		for(EntidadeDominio e : produtos) {
        			
       			// Aplicado o CAST para poder popular o produto,
       			// fazendo o CAST para uma referência mais genérica, no caso para o produto
       			Produto product = (Produto) e;
        	%>
	          <div class="col-lg-4 col-md-6 mb-4">
	            <div class="card h-100">
	              <a href="http://localhost:8080/Ecommerce_Bebida/itemCarrinho?idProduto=<%=product.getId() %>&operacao=SALVAR"><img class="card-img-top" src=".<%=product.getFoto() %>" alt=""></a>
	              <div class="card-body">
	                <h4 class="card-title">
	                  <a href="http://localhost:8080/Ecommerce_Bebida/itemCarrinho?idProduto=<%=product.getId() %>&operacao=SALVAR"><%=product.getNome() %></a>
	                </h4>
	                <h5>$24.99</h5>
	                <p class="card-text"><%=product.getDescricao() %></p>
	              </div>
	              <div class="card-footer">
	                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
	              </div>
	            </div>
	          </div>
        	<%
			}
			%>
        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

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
  <script src="../JQUERY/jquery.min.js"></script>
  <script src="../JS/bootstrap.bundle.min.js"></script>
  
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