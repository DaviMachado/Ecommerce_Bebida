<%@page import='com.les.bebida.core.dominio.*'%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
	<title>Listagem dos Pedidos</title>
	
	<!-- importa��es para funcionar o Header e o Footer -->
	<link href="./CSS/bootstrap.min.css" rel="stylesheet">
	<link href="./CSS/shop-homepage.css" rel="stylesheet">
	<link href="./CSS/form-default.css" rel="stylesheet" type="text/css">
</head>

<%
	Usuario usuarioLogado = new Usuario();
	
	// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
	HttpSession sessao = request.getSession();
	// pega o objeto salvo em Sess�o com o nome "usuarioLogado",
	// e passa para o novo objeto criado com o nome "usuarioLogado", (fazendo o CAST para o tipo Usuario)
	usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
	
	// pega o objeto salvo em Sess�o com o nome "todosPedidosCliente",
	// e passa para o novo objeto criado com o nome "pedidosCliente" (fazendo o CAST para o tipo List<Pedido>)
	Pedido pedidosCliente = (Pedido) sessao.getAttribute("todosPedidosCliente");
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
  
  	<h2 style="margin-top: 30px; margin-left: 100px">Meus Pedidos</h2>
  	
  	<!-- Campo Pesquisa-->
    <form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/pesquisaByFiltro">
    	<div class="row justify-content-md-center">
			<div style="margin-top: 10px;" class="form-group col-md-8">
	  			<select name="statusPedido" class="form-control" placeholder="Selecione um Status" required>
			      	<option value="" disabled selected>Selecione uma op��o...</option>
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
			
			<div style="margin-top: 10px;" class="form-group col-md-2">
				<button class="btn btn-success" name="operacao" value="CONSULTAR">Pesquisar</button>
			</div>
        </div>
        
        <!-- Nome da tabela que ser� consultado -->
        <input type="hidden" name="NomeTabela" value="PedidoCliente">
        <!-- Nome do Cliente logado no sistema -->
        <input type="hidden" name="nomeCliente" value="<%=usuarioLogado.getNome() %>">
	</form>

	<table border="1" style="margin-top: 30px;" class="table table-striped">
		<tr>
            <th>ID Pedido</th>
            <th>Total Pedido</th>
            <th>Status Atual</th>
        </tr>
		<%		
			for(Pedido order : pedidosCliente.getPedidosCliente()) {
			
			boolean itemDoPedidoJaFoiTrocado = false;
			
			// verifica se existe algum item desse Pedido, esta com o status "trocado" como "sim",
			// pois se estiver, o bot�o de "Trocar Pedido Inteiro" ser� desabilitado, 
			// para poder realizar a troca inteira somente para Pedidos com itens n�o trocados,
			// caso contr�rio, o bot�o ficar� habilitado,.
			//List<ItemPedido> pedidoComTodosOsItensJaTrocados = itemPedidoDAO.consultarItemPedidoByIdPedidoAndTrocadoSim(order.getId());
			
			//if (pedidoComTodosOsItensJaTrocados.isEmpty()) {
			//	itemDoPedidoJaFoiTrocado = false;
			//}
			//else {
			//	itemDoPedidoJaFoiTrocado = true;
			//}
		%>
			<tr>
				<td><%=order.getId() %></td>
				<td><%=order.getTotalPedido() %></td>
				<td><%=order.getStatus() %></td>
				<td><a href="/Ecommerce_Bebida/itemPedido?idPedido=<%= order.getId()%>&operacao=CONSULTAR"><button class="btn btn-warning">Visualizar Itens</button></a></td>
				
				<% if(order.getStatus().equals("ENTREGA REALIZADA") && order.getTodosItensTrocado().equals("nao")) { %>
                	<td><a href="/Ecommerce_Bebida/pedidoTroca?idPedido=<%= order.getId()%>&trocaPedidoInteiro=<%= "1"%>&operacao=SALVAR"><button class="btn btn-danger">Trocar Pedido Inteiro</button></a></td>
                <% } %>
                
                <% if(order.getStatus().equals("EM PROCESSAMENTO")) { %>
                	<td><a href="/Ecommerce_Bebida/pedidoTroca?idPedido=<%= order.getId()%>&alterarStatusPedido=<%= "CANCELAMENTO SOLICITADO"%>&operacao=ALTERAR"><button class="btn btn-danger">Cancelar Pedido</button></a></td>
                <% } %>
                
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