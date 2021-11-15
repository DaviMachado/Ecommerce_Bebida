<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title>Ranking dos Clientes</title>
	
	<!-- importações para funcionar o Header e o Footer -->
	<link href="../CSS/bootstrap.min.css" rel="stylesheet">
	<link href="../CSS/shop-homepage.css" rel="stylesheet">
	<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
</head>

	<%
		PedidoDAO pedidoDAO = new PedidoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		
		// consulta os 3 Clientes com maior compra
		List<Pedido> pedidos = pedidoDAO.consultar3ClientesMaiorCompra();
		
		// variavel para representar a colocação do cliente
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
  
  	<h2 style="margin-top: 30px; margin-left: 100px">Ranking dos Clientes com maiores Compras</h2>
  
	<table border="1" style="margin-top: 30px; margin-left: 100px;">
		<tr>
			<th>Colocação</th>
            <th>Nome</th>
            <th>Total de Compras</th>
        </tr>
		<%
		for(Pedido order : pedidos) {
		
		// busca o Cliente, pelo ID do cliente no Pedido
		List<Cliente> clientes = clienteDAO.consultarClienteById(order.getIdCliente());
		
		// incremento da colocação do cliente
		i++;
		%>
			<tr>
				<td><%=i %></td>
				<td><%=clientes.get(0).getNome() %></td>
				<td><%=order.getTotalPedido() %></td>
			</tr>
		<%
		}
		%>
	</table>
	 <a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 100px; margin-bottom: 386px;"></a>
	 
 	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
</body>
</html>