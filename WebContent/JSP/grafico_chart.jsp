<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 20/09/2021 -->

<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Gr�fico Chart.js</title>
		
		<!-- importa��es para funcionar o Header e o Footer -->
		<link href="../CSS/bootstrap.min.css" rel="stylesheet">
  		<link href="../CSS/shop-homepage.css" rel="stylesheet">
  		<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
	</head>
	<%
		ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		PedidoDAO pedidoDAO = new PedidoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		
		// consulta os 3 Produtos mais vendidos
		List<ItemPedido> itemPedido = itemPedidoDAO.consultar3ProdutosMaisVendidos();
		
		List<Produto> produto1 = produtoDAO.consultarProdutoById(itemPedido.get(0).getProduto().getId());
		List<Produto> produto2 = produtoDAO.consultarProdutoById(itemPedido.get(1).getProduto().getId());
		List<Produto> produto3 = produtoDAO.consultarProdutoById(itemPedido.get(2).getProduto().getId());
		
		// consulta os 3 Clientes com maior compra
		List<Pedido> pedido = pedidoDAO.consultar3ClientesMaiorCompra();
		
		List<Cliente> cliente1 = clienteDAO.consultarClienteById(pedido.get(0).getIdCliente());
		List<Cliente> cliente2 = clienteDAO.consultarClienteById(pedido.get(1).getIdCliente());
		List<Cliente> cliente3 = clienteDAO.consultarClienteById(pedido.get(2).getIdCliente());
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
		
		<fieldset class="form-group fieldset_form" style="margin-top: 48px">
		<legend align="center">Gerenciamento de Gr�ficos</legend>
			<div class="form-row">
				<div class="form-group col-md-4">
		  		<label>Op��es para gerar o Gr�fico</label>
	
		  			<select name="selecioneOpcaoGrafico" id="selecioneOpcaoGrafico" class="form-control" placeholder="Selecione uma op��o" required>
				      	<option value="0" disabled selected>Selecione uma op��o...</option>
				      	<option value="1">3 produtos mais vendidos</option>
				      	<option value="2">3 clientes com maiores compras</option>
			      	</select>
		  		</div>
				
				<div class="form-group col-md-8">
					<div align="right" style="margin-top: 32px">
			  			<button class="btn btn-warning" name="operacao" value="ALTERAR" onclick="graficoChart()">Gerar</button>
			  		</div>
		  		</div>
	  		</div>
	  		
	  		<!-- Produtos -->
            <input type="hidden" name="produto1" id="produto1" value="<%=produto1.get(0).getNome() %>">
            <input type="hidden" name="produto2" id="produto2" value="<%=produto2.get(0).getNome() %>">
            <input type="hidden" name="produto3" id="produto3" value="<%=produto3.get(0).getNome() %>">
            <!-- Valores Produtos -->
            <input type="hidden" name="valorProduto1" id="valorProduto1" value="<%=itemPedido.get(0).getProduto().getQuantidadeSelecionada() %>">
            <input type="hidden" name="valorProduto2" id="valorProduto2" value="<%=itemPedido.get(1).getProduto().getQuantidadeSelecionada() %>">
            <input type="hidden" name="valorProduto3" id="valorProduto3" value="<%=itemPedido.get(2).getProduto().getQuantidadeSelecionada() %>">
            <!-- Clientes -->
            <input type="hidden" name="cliente1" id="cliente1" value="<%=cliente1.get(0).getNome() %>">
            <input type="hidden" name="cliente2" id="cliente2" value="<%=cliente2.get(0).getNome() %>">
            <input type="hidden" name="cliente3" id="cliente3" value="<%=cliente3.get(0).getNome() %>">
            <!-- Valores Clientes -->
            <input type="hidden" name="valorCliente1" id="valorCliente1" value="<%=pedido.get(0).getTotalPedido() %>">
            <input type="hidden" name="valorCliente2" id="valorCliente2" value="<%=pedido.get(1).getTotalPedido() %>">
            <input type="hidden" name="valorCliente3" id="valorCliente3" value="<%=pedido.get(2).getTotalPedido() %>">
		</fieldset>
		
		<!-- Grafico gerado pelo Chart.js -->
		<div style="width: 50%; height: 50%;">
			<canvas id="myChart"></canvas>
		</div>
		<!-- Fim Grafico gerado pelo Chart.js -->
		
	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
  	  
  	  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.1/chart.min.js" integrity="sha512-Wt1bJGtlnMtGP0dqNFH1xlkLBNpEodaiQ8ZN5JLA5wpc1sUlk/O5uuOMNgvzddzkpvZ9GLyYNa8w2s7rqiTk5Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  	  <script src="../JS/chart.js"></script>
  	  
	</body>
</html>