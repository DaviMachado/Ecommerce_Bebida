<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title>Listagem dos Produtos</title>
	
	<!-- importações para funcionar o Header e o Footer -->
	<link href="./CSS/bootstrap.min.css" rel="stylesheet">
	<link href="./CSS/shop-homepage.css" rel="stylesheet">
	<link href="./CSS/form-default.css" rel="stylesheet" type="text/css">
</head>

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
  
  	<h2 style="margin-top: 30px; margin-left: 10px">Listagem de Todos os Produtos</h2>

	<table border="1" style="margin-top: 30px;" class="table table-striped">
		<tr>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Categoria</th>
            <th>Preço de Compra</th>
            <th>Quantidade</th>
            <th>Status</th>
        </tr>
		<%
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = new Produto();
		
		List<EntidadeDominio> produtos = dao.consultar(produto);
		
		for(EntidadeDominio e : produtos) {
		
		// Aplicado o CAST para poder popular o produto,
		// fazendo o CAST para uma referência mais genérica, no caso para o produto
		Produto product = (Produto) e;
		%>
			<tr>
				<td><%=product.getNome() %></td>
				<td><%=product.getDescricao() %></td>
				<td><%=product.getCategoria() %></td>
				<td><%=product.getPrecoDeCompra() %></td>
				<td><%=product.getQuantidade() %></td>
				<td><%=product.getStatus() %></td>
				<td><a href="/Ecommerce_Bebida/cadastroProduto?idProduto=<%= product.getId()%>&alteraProduto=<%= "0"%>&operacao=ALTERAR"><button class="btn btn-warning">Alterar</button></a></td>
                <td><a href="/Ecommerce_Bebida/cadastroProduto?idProduto=<%= product.getId()%>&operacao=EXCLUIR"><button class="btn btn-danger">Deletar</button></a></td>
			</tr>
		<%
		}
		%>
	</table>		
	 <a href="/Ecommerce_Bebida/JSP/formulario_produto.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 10px; margin-bottom: 386px;"></a>
	 
  	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
</body>
</html>