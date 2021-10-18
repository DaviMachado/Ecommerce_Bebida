<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

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
		ItemPedidoDAO pedidoItemDAO = new ItemPedidoDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		ItemPedido itemPedido = new ItemPedido();
		List<PedidoTroca> itensPedidoTrocaEmSessao = new ArrayList<>();
			
		// pega o id do pedido que estava pendurado na requisição,
		// que foi enviado pelo arquivo "ItemPedidoHelper"
		String idPedido = (String)request.getAttribute("idPedido");
		//cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
		HttpSession sessao = request.getSession();
		// pega o objeto salvo em Sessão com o nome "itensPedidoTroca",
		// e passa para o "itensPedidoTrocaEmSessao" (fazendo o CAST para o tipo List<PedidoTroca>)
		itensPedidoTrocaEmSessao = (List<PedidoTroca>) sessao.getAttribute("itensPedidoTroca");
		
		// seta o valor do ID do Pedido com o valor que estava pendurado na requisição
		itemPedido.setIdPedido(idPedido);
		
		// busca o Pedido pelo ID do Pedido
		List<Pedido> pedidos = pedidoDAO.consultarPedidoById(idPedido);
		// busca os Itens do Pedido pelo ID do Pedido
		List<EntidadeDominio> itens_pedido = pedidoItemDAO.consultar(itemPedido);
		// busca o Endereço do Pedido, pelo ID do endereço do Pedido
		List<Endereco> enderecos = enderecoDAO.consultarEnderecoById(pedidos.get(0).getIdEndereco());
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
	        <th>ID Pedido</th>
	        <th>Total Itens</th>
	        <th>Total Frete</th>
	        <th>Total Pedido</th>
	        <th>Status</th>
	        <th>Endereço</th>
	        <th>Forma de Pagamento</th>
	        <th>Total Cupons</th>
	        <th>Trocado</th>
       	</tr>
	    <tr>
			<td><%=pedidos.get(0).getId() %></td>
			<td><%=pedidos.get(0).getTotalItens() %></td>
			<td><%=pedidos.get(0).getTotalFrete() %></td>
			<td><%=pedidos.get(0).getTotalPedido() %></td>
			<td><%=pedidos.get(0).getStatus() %></td>
			<td><%=enderecos.get(0).getLogradouro() %></td>
			<td><%=pedidos.get(0).getFormaPagamento() %></td>
			<td><%=pedidos.get(0).getTotalCupons() %></td>
			<td><%=pedidos.get(0).getTrocado() %></td>
		</tr>
    </table>
	
		<table border="1" style="margin-top: 30px; margin-left: 70px; margin-right: 70px;">
			<tr>
	            <th>Nome do Produto</th>
	            <th>Valor de Venda</th>
	            <th>Quantidade</th>
	            <th>Trocado</th>
	        </tr>
	        <%
	        	for(EntidadeDominio e : itens_pedido) {
	        		
	        		// Aplicado o CAST para poder popular os itens do pedido,
					// fazendo o CAST para uma referência mais genérica, no caso para o item do pedido
					ItemPedido order_items = (ItemPedido) e;
					
					boolean itemJaFoiTrocado = false;
					
					// verifica na lista da Sessão se algum item da lista do Pedido atual, se ja foi marcado como "trocadoSelecionado",
					// se ja foi trocado, o item será apresentado com um risco vermelho listagem
					for (PedidoTroca exchange : itensPedidoTrocaEmSessao){
						// o ID do item que esta salvo na Sessão, é IGUAL ao ID do item da lista atual
						if (exchange.getItemPedido().getId().equals(order_items.getId())){
							itemJaFoiTrocado = true;
						}
					}
					
					// o pedido ja foi trocado? OU o Item do Pedido ja foi trocado?
					// OU esse Item do Pedido ja esta na lista da Sessão? ("itensPedidoTroca")
					// então mostra o item com um risco vermelho na listagem
					if (pedidos.get(0).getTrocado().equals("sim") || order_items.getTrocado().equals("sim") || itemJaFoiTrocado) {
    		%>
		    	<tr>
					<td><strike style="text-decoration-color: red;"><%=order_items.getProduto().getNome() %></strike></td>
					<td><strike style="text-decoration-color: red;"><%=order_items.getProduto().getPrecoDeVenda() %></strike></td>
					<td><strike style="text-decoration-color: red;"><%=order_items.getProduto().getQuantidadeSelecionada() %></strike></td>
					<td><strike style="text-decoration-color: red;"><%=order_items.getTrocado() %></strike></td>
				</tr>
			<%
					}
					// caso contrário, mostra o item normalmente com o botão para solicitar a troca
					else {
			%>
				<tr>
					<td><%=order_items.getProduto().getNome() %></td>
					<td><%=order_items.getProduto().getPrecoDeVenda() %></td>
					<td><%=order_items.getProduto().getQuantidadeSelecionada() %></td>
					<td><%=order_items.getTrocado() %></td>
					
					<% if(pedidos.get(0).getStatus().equals("ENTREGA REALIZADA")) { %>
						<td><a href="/Ecommerce_Bebida/pedidoTroca?idPedido=<%= pedidos.get(0).getId()%>&idItemPedido=<%= order_items.getId()%>&operacao=CONSULTAR"><button class="btn btn-danger">Solicitar Troca</button></a></td>
					<% } %>
					
				</tr>
			<%
					}
				}
			%>
		</table>
		
		<a href="/Ecommerce_Bebida/JSP/lista-pedidos-scriptletCLIENTE.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 70px; margin-bottom: 386px;"></a>
		
		<!-- se tiver itens na lista "itensPedidoTroca" na Sessão, ele mostra o botão para finalizar a troca -->
		<% if (itensPedidoTrocaEmSessao.size() > 0) {
		%>
			<a href="/Ecommerce_Bebida/pedidoTroca?trocaPedidoInteiro=<%= "0"%>&operacao=SALVAR"><button class="btn btn-success" style="margin-top: 10px; margin-left: 710px;">Finalizar Troca</button></a>
		<%
		}
		%>

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
