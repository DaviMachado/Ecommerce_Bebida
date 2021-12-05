<%@page import='com.les.bebida.core.dominio.*'%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

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
  <link href="./CSS/shop-homepage.css" rel="stylesheet">

</head>

	<%
		Usuario userLogado = new Usuario();
		List<Produto> produtosPesquisaByFiltro = new ArrayList<>();
		
		// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
		HttpSession sessao = request.getSession();
		// pega o objeto salvo em Sessão com o nome "filtroByNomeProdutos",
		// e passa para o novo objeto criado com o nome "produtosPesquisaByFiltro" (fazendo o CAST para o tipo List<Produto>)
		produtosPesquisaByFiltro = (List<Produto>) sessao.getAttribute("filtroByNomeProdutos");
		
		// pega o objeto salvo em Sessão com o nome "usuarioLogado",
		// e passa para o novo objeto criado com o nome "userLogado", (fazendo o CAST para o tipo Usuario)
		userLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// pega a mensagem que estava pendurado na requisição,
		// que foi enviado pelo arquivo "PesquisaByFiltroHelper"
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
        	if(userLogado.getTipo().equals("admin")) {
        %>
       	<div class="list-group">
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_ClienteADMIN.jsp" class="list-group-item">Gerenciamento de Clientes</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_produto.jsp" class="list-group-item">Gerenciamento de Produtos</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_estoque.jsp" class="list-group-item">Gerenciamento de Estoque</a>
          <a href="/Ecommerce_Bebida/carrinho?idCliente=<%= userLogado.getId()%>&operacao=CONSULTAR"class="list-group-item">Carrinho</a>
          <a href="/Ecommerce_Bebida/cadastroPedido?idClienteConsulta=<%= userLogado.getId()%>&operacao=CONSULTAR"class="list-group-item">Gerenciamento de Pedidos</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_cupom.jsp" class="list-group-item">Gerenciamento de Cupons</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/grafico_chart_1.jsp" class="list-group-item">Gerenciamento de Gráficos</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/lista-ranking-clientes.jsp" class="list-group-item">Ranking dos Clientes</a>
        </div>
        <%
        	}
        %>
        
        <%
        // verifica se o cliente logado é do tipo CLIENTE,
        // para mostrar somente as telas do cliente
        	if(userLogado.getTipo().equals("cliente")) {
        %>
        <div class="list-group">
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_Cliente.jsp" class="list-group-item">Meus Dados</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_Endereco.jsp" class="list-group-item">Meus Endereços</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/formulario_cartaoDeCredito.jsp" class="list-group-item">Meus Cartões de créditos</a>
          <a href="/Ecommerce_Bebida/carrinho?idCliente=<%= userLogado.getId()%>&operacao=CONSULTAR"class="list-group-item">Carrinho</a>
          <a href="/Ecommerce_Bebida/cadastroPedido?idClienteConsulta=<%= userLogado.getId()%>&operacao=CONSULTAR"class="list-group-item">Meus Pedidos</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/lista-cupons-scriptletCLIENTE.jsp" class="list-group-item">Meus Cupons</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/JSP/editar_somente_senha_Cliente.jsp" class="list-group-item">Alterar Senha</a>
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
              <img class="d-block img-fluid" src="./Imagens/carousel/DRINK_FAST.png" alt="First slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="./Imagens/carousel/tequilas.png" alt="Second slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="./Imagens/carousel/refrigerantes.png" alt="Third slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="./Imagens/carousel/cervejas.png" alt="Fourth slide">
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
        
        <!-- Campo Pesquisa-->
        <form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/pesquisaByFiltro">
	        <div class="row justify-content-md-center">
	        	<div class="form-group col-md-8">
				    <input type="text" class="form-control" name="nomeProduto" placeholder="Buscar Produto..." required>
				</div>
				
				<div class="form-group col-md-2">
					<button class="btn btn-success" name="operacao" value="CONSULTAR">Pesquisar</button>
				</div>
	        </div>
	        
	        <!-- Nome da tabela que será consultado -->
            <input type="hidden" name="NomeTabela" value="Produto">
		</form>
        
        <!-- Listagens dos produtos cadastrados no sistema -->
        <div class="row">
        	<%
        		for(Produto product : produtosPesquisaByFiltro) {
        			
       			// Aplicado o CAST para poder popular o produto,
       			// fazendo o CAST para uma referência mais genérica, no caso para o produto
       			//Produto product = (Produto) e;
        	%>
	          <div class="col-lg-4 col-md-6 mb-4">
	            <div class="card h-100">
	              <a href="http://localhost:8080/Ecommerce_Bebida/itemCarrinho?idProduto=<%=product.getId() %>&operacao=SALVAR"><img class="card-img-top" src="<%=product.getFoto() %>" alt=""></a>
	              <div class="card-body">
	                <h4 class="card-title">
	                  <a href="http://localhost:8080/Ecommerce_Bebida/itemCarrinho?idProduto=<%=product.getId() %>&operacao=SALVAR"><%=product.getNome() %></a>
	                </h4>
	                <h5>R$ <%=product.getPrecoDeVenda() %></h5>
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