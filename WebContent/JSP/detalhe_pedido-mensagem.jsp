<%@page import='com.les.bebida.core.dominio.*'%>

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
		ItemPedido itemPedido = new ItemPedido();
		List<PedidoTroca> itensPedidoTrocaEmSessao = new ArrayList<>();
		
		// pega o Pedido inteiro que estava pendurado na requisição,
		// que foi enviado pelo arquivo "ItemPedidoHelper"
		Pedido pedidoInteiro = (Pedido)request.getAttribute("pedidoInteiro");
		// pega o Endereço do Pedido que estava pendurado na requisição,
		// que foi enviado pelo arquivo "ItemPedidoHelper"
		Endereco enderecoPedido = (Endereco)request.getAttribute("enderecoPedido");
		// pega os Itens do Pedido que estava pendurado na requisição,
		// que foi enviado pelo arquivo "ItemPedidoHelper"
		List<ItemPedido> itensPedido = (List<ItemPedido>)request.getAttribute("itensPedido");
		
		//cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
		HttpSession sessao = request.getSession();
		// pega o objeto salvo em Sessão com o nome "itensPedidoTroca",
		// e passa para o "itensPedidoTrocaEmSessao" (fazendo o CAST para o tipo List<PedidoTroca>)
		itensPedidoTrocaEmSessao = (List<PedidoTroca>) sessao.getAttribute("itensPedidoTroca");
		
		// pega a mensagem que estava pendurado na requisição,
		// que foi enviado pelo arquivo "CarrinhoHelper"
		String mensagemStrategy = (String)request.getAttribute("mensagemStrategy");
		  
		// IF adicionado para não estourar NullPointerException na variavel
		// "mensagemStrategy", pois quando ela esta sendo aberta pela primeira vez,
		// (apos validar o Carrinho), ela pode ficar nula
		if(mensagemStrategy == null){
			mensagemStrategy = "Detalhe do Pedido aberto!";
		}
	%>

<body onload="AtivaModal()">

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

	<h2 style="margin-top: 30px; margin-left: 10px">Itens do Pedidos</h2>
	
	<table border="1" style="margin-top: 30px;" class="table table-striped">
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
			<td><%=pedidoInteiro.getId() %></td>
			<td><%=pedidoInteiro.getTotalItens() %></td>
			<td><%=pedidoInteiro.getTotalFrete() %></td>
			<td><%=pedidoInteiro.getTotalPedido() %></td>
			<td><%=pedidoInteiro.getStatus() %></td>
			<td><%=enderecoPedido.getLogradouro() %></td>
			<td><%=pedidoInteiro.getFormaPagamento() %></td>
			<td><%=pedidoInteiro.getTotalCupons() %></td>
			<td><%=pedidoInteiro.getTrocado() %></td>
		</tr>
    </table>
	
		<table border="1" style="margin-top: 30px;" class="table table-striped">
			<tr>
	            <th>Nome do Produto</th>
	            <th>Valor de Venda</th>
	            <th>Quantidade</th>
	            <th>Trocado</th>
	            <% if(pedidoInteiro.getStatus().equals("ENTREGA REALIZADA")) { %>
		            <th>Qtde p/ Troca</th>
		            <th></th>
		            <th>Total p/ Troca</th>
	            <%} %>
	        </tr>
	        <%
	        	for(ItemPedido order_items : itensPedido) {
	        		
	        		// Aplicado o CAST para poder popular os itens do pedido,
					// fazendo o CAST para uma referência mais genérica, no caso para o item do pedido
					//ItemPedido order_items = (ItemPedido) e;
					
					boolean itemJaFoiTrocado = false;
					
					//
					// esse trecho foi criado para quando adicionar um item na lista para troca,
					// o mesmo será verificado aqui na tela, para não deixar adicionar o mesmo item para a lista de troca,
					// porem foi mudado a forma de solicitar a troca do item do pedido,
					// então esse trecho não é mais necessario,
					// podendo selecionar a quantidade mais de um item para troca
					//
					// verifica na lista da Sessão se algum item da lista do Pedido atual, se ja foi marcado como "trocadoSelecionado",
					// se ja foi trocado, o item será apresentado com um risco vermelho listagem
					//for (PedidoTroca exchange : itensPedidoTrocaEmSessao){
						// o ID do item que esta salvo na Sessão, é IGUAL ao ID do item da lista atual
						//if (exchange.getItemPedido().getId().equals(order_items.getId())){
							//itemJaFoiTrocado = true;
						//}
					//}
					
					// o pedido ja foi trocado? OU o Item do Pedido ja foi trocado?
					// OU esse Item do Pedido ja esta na lista da Sessão? ("itensPedidoTroca")
					// então mostra o item com um risco vermelho na listagem
					if (pedidoInteiro.getTrocado().equals("sim") || order_items.getTrocado().equals("sim")) { // || itemJaFoiTrocado
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
					
					<% if(pedidoInteiro.getStatus().equals("ENTREGA REALIZADA")) { %>
						<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/pedidoTroca">
							<td>
								<!-- Quantidade do Item para ser Trocado -->
								<input style="width: 50px; height: 30px;" type="text" class="form-control" name="qtdeItemParaTroca" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="3" required>
							</td>
							
							<td><button class="btn btn-danger" name="operacao" value="CONSULTAR">Solicitar Troca</button></td>
					
							<!-- ID do Pedido -->
		  					<input type="hidden" name="idPedido" value="<%=pedidoInteiro.getId() %>">
		  					<!-- ID do Item do Pedido -->
		  					<input type="hidden" name="idItemPedido" value="<%=order_items.getId() %>">
						</form>
					<% } %>
					
					<!-- verifica se esse item do pedido foi acionado para troca, 
					caso esse item esteja na lista de troca, será mostrado a quantidade dele na tela,
					para poder saber a quantidade do item que esta sendo trocado -->
					<% for (PedidoTroca exchange : itensPedidoTrocaEmSessao){
						// o ID do item que esta salvo na Sessão, é IGUAL ao ID do item da lista atual
						if (exchange.getItemPedido().getId().equals(order_items.getId())){ %>
							<td>
								<!-- Mostra a quantidade do Item que esta sendo Trocado -->
								<input style="width: 50px; height: 30px;" type="text" class="form-control" name="qtdeItemParaTroca" value="<%=exchange.getItemPedido().getProduto().getQuantidadeSelecionada()%>" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="3" required>
							</td>
					<%
						}
					 } %>
				</tr>
			<%
					}
				}
			%>
		</table>
		
		<a href="/Ecommerce_Bebida/JSP/lista-pedidos-scriptletCLIENTE.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 10px; margin-bottom: 386px;"></a>
		
		<!-- se tiver itens na lista "itensPedidoTroca" na Sessão, ele mostra o botão para finalizar a troca -->
		<% if (itensPedidoTrocaEmSessao.size() > 0) {
		%>
			<a href="/Ecommerce_Bebida/pedidoTroca?trocaPedidoInteiro=<%= "0"%>&operacao=SALVAR"><button class="btn btn-success" style="margin-top: 10px; float: right">Finalizar Troca</button></a>
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
  
  <!-- Modal -->
	<div class="modal fade" id="modal-mensagem">
	   <div class="modal-dialog">
	   		<div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
	                <h4 class="modal-title">Mensagem</h4>
	            </div>
	            <div class="modal-body">
	                <p><% out.println(mensagemStrategy); %></p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
	            </div>
	        </div>
	    </div>
	</div>
		
	<!-- Botão para chamar a Modal -->
	<button style="display: none" id="idModal" class="btn btn-primary" data-toggle="modal" data-target="#modal-mensagem">
		Exibir mensagem da modal
	</button>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
    // Função que irá ativar a Modal com a mensagem retornada do BackEnd,
    // essa função é carregada junto ao carregamento da página com o evento ONLOAD, dentro da tag <body>.
	    function AtivaModal(){
    		// metodo para poder ativar o "onClick" sem precisar clicar no botão
	    	document.getElementById('idModal').click();
	    }
    </script>
   	<!-- Fim Modal -->

</body>

</html>
