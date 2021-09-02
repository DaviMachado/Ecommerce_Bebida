<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
	<title>Listagem dos Pedidos</title>
	
	<!-- importações para funcionar o Header e o Footer -->
	<link href="../CSS/bootstrap.min.css" rel="stylesheet">
	<link href="../CSS/shop-homepage.css" rel="stylesheet">
	<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
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
  
  	<h2 style="margin-top: 30px; margin-left: 70px">Gerenciamento de Pedidos</h2>

	<table border="1" style="margin-top: 30px; margin-left: 70px; margin-right: 70px;">
		<tr>
            <th>ID Pedido</th>
            <th>Cliente</th>
            <th>Total Pedido</th>
            <th>Status Atual</th>
            <th width="35%"></th>
        </tr>
		<%
		PedidoDAO dao = new PedidoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		Pedido pedido = new Pedido();
		List<Cliente> clientes = new ArrayList<>();
		
		List<EntidadeDominio> pedidos = dao.consultar(pedido);
		
		for(EntidadeDominio e : pedidos) {
		
		// Aplicado o CAST para poder popular o pedido,
		// fazendo o CAST para uma referência mais genérica, no caso para o pedido
		Pedido order = (Pedido) e;
		
		// busca o nome do cliente, conforme o ID do cliente no pedido
		clientes = clienteDAO.consultarClienteById(order.getIdCliente());
		%>
			<tr>
				<td><%=order.getId() %></td>
				<td><%=clientes.get(0).getNome() %></td>
				<td><%=order.getTotalPedido() %></td>
				<td><%=order.getStatus() %></td>
				<td>
					<form class="form_form" style="width: 500px !important;" action="http://localhost:8080/Ecommerce_Bebida/cadastroPedidoTroca">
						<div class="form-row">
							<div class="form-group col-md-8">
								<label>Status</label>

					  			<select name="alterarStatusPedido" class="form-control" placeholder="Selecione um Status" required>
							      	<option value="" disabled selected>Selecione uma opção...</option>
							      	<option value="EM PROCESSAMENTO">EM PROCESSAMENTO</option>
							      	<option value="PAGAMENTO REALIZADO">PAGAMENTO REALIZADO</option>
							      	<option value="EM TRANSPORTE">EM TRANSPORTE</option>
							      	<option value="TROCA SOLICITADA">TROCA SOLICITADA</option>
							      	<option value="TROCA AUTORIZADA">TROCA AUTORIZADA</option>
							      	<option value="TROCA REJEITADA">TROCA REJEITADA</option>
							      	<option value="TROCA ACEITA">TROCA ACEITA</option>
							      	<option value="TROCA EFETUADA">TROCA EFETUADA</option>
							      	<option value="CANCELAMENTO SOLICITADO">CANCELAMENTO SOLICITADO</option>
							      	<option value="CANCELAMENTO REJEITADA">CANCELAMENTO REJEITADA</option>
							      	<option value="CANCELAMENTO ACEITO">CANCELAMENTO ACEITO</option>
							      	<option value="CANCELAMENTO EFETUADO">CANCELAMENTO EFETUADO</option>
							      	<option value="ENTREGA REALIZADA">ENTREGA REALIZADA</option>
						      	</select>
							</div>
							
							<!-- Botões CRUD-->
							<div class="form-group col-md-4">
								<div align="right" style="margin-top: 29px;">
									<button class="btn btn-warning" name="operacao" value="ALTERAR">Alterar</button>
								</div>
							</div>
						</div>
						
						<!-- ID do Pedido -->
			    		<input type="hidden" name="idPedido" id="idPedido" value="<%=order.getId() %>">
			    		<!-- Total do Pedido -->
			    		<input type="hidden" name="totalPedido" id="totalPedido" value="<%=order.getTotalPedido() %>">
					</form>
				</td>
			</tr>
		<%
		}
		%>
	</table>		
	 <a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 70px; margin-bottom: 386px;"></a>
	 
  	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
</body>
</html>