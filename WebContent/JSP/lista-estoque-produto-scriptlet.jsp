<%@page import='com.les.bebida.core.dominio.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title>Listagem do Estoque</title>
	
	<!-- importa��es para funcionar o Header e o Footer -->
	<link href="./CSS/bootstrap.min.css" rel="stylesheet">
	<link href="./CSS/shop-homepage.css" rel="stylesheet">
	<link href="./CSS/form-default.css" rel="stylesheet" type="text/css">
</head>

<%
	//pega todos os endere�os do Cliente que estava pendurado na requisi��o,
	// que foi enviado pelo arquivo "EnderecoHelper"
	List<Estoque> estoqueDoProduto = (List<Estoque>)request.getAttribute("estoqueDoProduto");
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
  
  	<h2 style="margin-top: 30px; margin-left: 10px">Listagem do Estoque: <%=estoqueDoProduto.get(0).getNomeProduto() %></h2>

	<table border="1" style="margin-top: 30px;" class="table table-striped">
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
		for(Estoque stock : estoqueDoProduto) {
		
		// Aplicado o CAST para poder popular o estoque,
		// fazendo o CAST para uma refer�ncia mais gen�rica, no caso para o estoque
		//Estoque stock = (Estoque) e;
		%>
			<tr>
				<td><%=stock.getNomeProduto() %></td>
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
	 <a href="/Ecommerce_Bebida/JSP/formulario_estoque.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 10px; margin-bottom: 386px;"></a>
	 
  	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
</body>
</html>