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

<%
	PedidoDAO dao = new PedidoDAO();
	ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
	Usuario usuarioLogado = new Usuario();
	
	// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
	HttpSession sessao = request.getSession();
	// pega o objeto salvo em Sessão com o nome "usuarioLogado",
	// e passa para o novo objeto criado com o nome "usuarioLogado", (fazendo o CAST para o tipo Usuario)
	usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
	
	// consulta todos os pedidos do Cliente
	List<Pedido> pedidos = dao.consultarPedidoByIdCliente(usuarioLogado.getId());
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
  
  	<h2 style="margin-top: 30px; margin-left: 100px">Meus Pedidos</h2>
  	
  	<!-- Campo Pesquisa-->
    <form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/pesquisaByFiltro">
    	<div class="row justify-content-md-center">
			<div style="margin-top: 10px;" class="form-group col-md-8">
	  			<select name="statusPedido" class="form-control" placeholder="Selecione um Status" required>
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
			
			<div style="margin-top: 10px;" class="form-group col-md-2">
				<button class="btn btn-success" name="operacao" value="CONSULTAR">Pesquisar</button>
			</div>
        </div>
        
        <!-- Nome da tabela que será consultado -->
        <input type="hidden" name="NomeTabela" value="PedidoCliente">
	</form>

	<table border="1" style="margin-top: 30px;" class="table table-striped">
		<tr>
            <th>ID Pedido</th>
            <th>Total Pedido</th>
            <th>Status Atual</th>
        </tr>
		<%		
			for(Pedido order : pedidos) {
			
			boolean itemDoPedidoJaFoiTrocado = false;
			
			// verifica se existe algum item desse Pedido, esta com o status "trocado" como "sim",
			// pois se estiver, o botão de "Trocar Pedido Inteiro" será desabilitado, 
			// para poder realizar a troca inteira somente para Pedidos com itens não trocados,
			// caso contrário, o botão ficará habilitado,.
			List<ItemPedido> pedidoComTodosOsItensJaTrocados = itemPedidoDAO.consultarItemPedidoByIdPedidoAndTrocadoSim(order.getId());
			
			if (pedidoComTodosOsItensJaTrocados.isEmpty()) {
				itemDoPedidoJaFoiTrocado = false;
			}
			else {
				itemDoPedidoJaFoiTrocado = true;
			}
		%>
			<tr>
				<td><%=order.getId() %></td>
				<td><%=order.getTotalPedido() %></td>
				<td><%=order.getStatus() %></td>
				<td><a href="/Ecommerce_Bebida/itemPedido?idPedido=<%= order.getId()%>&operacao=CONSULTAR"><button class="btn btn-warning">Visualizar Itens</button></a></td>
				
				<% if(order.getStatus().equals("ENTREGA REALIZADA") && !itemDoPedidoJaFoiTrocado) { %>
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