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
		PedidoDAO pedidoDAO = new PedidoDAO();
		PedidoItemDAO pedidoItemDAO = new PedidoItemDAO();
		ItemPedido itemPedido = new ItemPedido();
		
		// pega o id do pedido que estava pendurado na requisição,
		// que foi enviado pelo arquivo "ItemPedidoHelper"
		String idPedido = (String)request.getAttribute("idPedido");
		
		itemPedido.setIdPedido(idPedido);
		
		// busca o Pedido pelo ID do Pedido
		List<Pedido> pedidos = pedidoDAO.consultarPedidoById(idPedido);
		// busca os Itens do Pedido pelo ID do Pedido
		List<EntidadeDominio> itens_pedido = pedidoItemDAO.consultar(itemPedido);
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

	<h2 style="margin-top: 30px; margin-left: 70px">Itens do Pedidos</h2>
	
		<table border="1" style="margin-top: 30px; margin-left: 70px; margin-right: 70px;">
			<tr>
	            <th>Nome do Produto</th>
	            <th>Valor de Venda</th>
	            <th>Quantidade</th>
	        </tr>
	        <%
	        	for(EntidadeDominio e : itens_pedido) {
	        		
        		// Aplicado o CAST para poder popular os itens do pedido,
				// fazendo o CAST para uma referência mais genérica, no caso para o item do pedido
				ItemPedido order_items = (ItemPedido) e;
    		%>
	    	<tr>
				<td><%=order_items.getProduto().getNome() %></td>
				<td><%=order_items.getProduto().getPrecoDeVenda() %></td>
				<td><%=order_items.getProduto().getQuantidadeSelecionada() %></td>
			</tr>
			<%
				}
			%>
		</table>
		<a href="/Ecommerce_Bebida/JSP/lista-pedidos-scriptletCLIENTE.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 70px; margin-bottom: 386px;"></a>

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
