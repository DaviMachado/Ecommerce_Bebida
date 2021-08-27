<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title>Listagem do Estoque</title>
	
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

	<table border="1" style="margin-top: 30px; margin-left: 200px;">
		<tr>
            <th>Nome do Produto</th>
            <th>Tipo</th>
            <th>Quantidade Entrada/Saida</th>
            <th>Valor de Custo</th>
            <th>Fornecedor</th>
            <th>Data Entrada</th>
            <th>Quantidade Final</th>
        </tr>
		<%
		EstoqueDAO dao = new EstoqueDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Estoque estoque = new Estoque();
		
		// pega o "id" do produto que estava pendurado na requisição,
		// que foi enviado pelo arquivo "EstoqueHelper"
		String idProduto = (String)request.getAttribute("idProduto");
		
		// pesquisa o produto que esta sendo passado pela requisição,
		// para poder pegar o nome e adicionar o nome na listagem da tabela
		List<Produto> produto = produtoDAO.consultarProdutoById(idProduto);
		
		// seta o atributo "id_produto" do objeto "estoque", com o valor que estava pendurado na requisição (idProduto)
		estoque.setIdProduto(idProduto);
		// consulta somente o estoque do produto que esta sendo mandado na requisição
		List<EntidadeDominio> estoques = dao.consultar(estoque);
		
		for(EntidadeDominio e : estoques) {
		
		// Aplicado o CAST para poder popular o estoque,
		// fazendo o CAST para uma referência mais genérica, no caso para o estoque
		Estoque stock = (Estoque) e;
		%>
			<tr>
				<td><%=produto.get(0).getNome() %></td>
				<td><%=stock.getTipo() %></td>
				<td><%=stock.getQuantidadeEntradaSaida() %></td>
				<td><%=stock.getValorCusto() %></td>
				<td><%=stock.getFornecedor() %></td>
				<td><%=stock.getDtEntrada() %></td>
				<td><%=stock.getQuantidadeFinal() %></td>
			</tr>
		<%
		}
		%>
	</table>		
	 <a href="/Ecommerce_Bebida/JSP/formulario_estoque.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 200px; margin-bottom: 386px;"></a>
	 
  	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
</body>
</html>