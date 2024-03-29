<%@page import='com.les.bebida.core.dominio.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title>Ranking dos Clientes</title>
	
	<!-- importa��es para funcionar o Header e o Footer -->
	<link href="../CSS/bootstrap.min.css" rel="stylesheet">
	<link href="../CSS/shop-homepage.css" rel="stylesheet">
	<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
</head>

	<%
		//cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
		HttpSession sessao = request.getSession();
	
		// pega os 3 Clientes com maior compra na sess�o,
		// pesquisado no LoginDAO e salvo LoginHelper
		List<Pedido> clientesMaiorCompra = (List<Pedido>) sessao.getAttribute("clientesMaiorCompraSistema");
		
		// variavel para representar a coloca��o do cliente
		int i = 0;
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
  
  	<h2 style="margin-top: 30px; margin-left: 10px">Ranking dos Clientes com maiores Compras</h2>
  
	<table border="1" style="margin-top: 30px;" class="table table-striped">
		<tr>
			<th>Coloca��o</th>
            <th>Nome</th>
            <th>Total de Compras</th>
        </tr>
		<%
		for(Pedido order : clientesMaiorCompra) {
		
		// incremento da coloca��o do cliente
		i++;
		%>
			<tr>
				<td><%=i %></td>
				<td><%=order.getNomeCliente() %></td>
				<td><%=order.getTotalPedido() %></td>
			</tr>
		<%
		}
		%>
	</table>
	 <a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 10px; margin-bottom: 386px;"></a>
	 
 	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
</body>
</html>