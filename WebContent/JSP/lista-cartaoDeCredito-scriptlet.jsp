<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title>Listagem dos Cartões de Créditos</title>
	
	<!-- importações para funcionar o Header e o Footer -->
	<link href="./CSS/bootstrap.min.css" rel="stylesheet">
	<link href="./CSS/shop-homepage.css" rel="stylesheet">
	<link href="./CSS/form-default.css" rel="stylesheet" type="text/css">
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

	<table border="1" style="margin-top: 30px; margin-left: 300px;">
		<tr>
            <th>Nome impresso</th>
            <th>Numero</th>
            <th>Bandeira</th>
            <th>Validade</th>
        </tr>
		<%
		CartaoDeCreditoDAO dao = new CartaoDeCreditoDAO();
		CartaoDeCredito cartao = new CartaoDeCredito();
		
		// pega o "id" do usuario logado que estava pendurado na requisição,
		// que foi enviado pelo arquivo "CartaoDeCreditoHelper"
		String idCliente = (String)request.getAttribute("idCliente");
		cartao.setIdCliente(idCliente);
		
		List<EntidadeDominio> cartoes = dao.consultar(cartao);
		
		for(EntidadeDominio e : cartoes) {
		
		// Aplicado o CAST para poder popular o cartão de credito,
		// fazendo o CAST para uma referência mais genérica, no caso para o cartão de credito
		CartaoDeCredito creditCard = (CartaoDeCredito) e;
		%>
			<tr>
				<td><%=creditCard.getNome() %></td>
				<td><%=creditCard.getNum_cartao() %></td>
				<td><%=creditCard.getBandeira() %></td>
				<td><%=creditCard.getDt_validade() %></td>
				<td><a href="/Ecommerce_Bebida/cadastroCartaoCredito?idCartaoDeCredito=<%= creditCard.getId()%>&alteraCartao=<%= "0"%>&operacao=ALTERAR"><button class="btn btn-warning">Alterar</button></a></td>
                <td><a href="/Ecommerce_Bebida/cadastroCartaoCredito?idCartaoDeCredito=<%= creditCard.getId()%>&idCliente=<%= creditCard.getIdCliente()%>&operacao=EXCLUIR"><button class="btn btn-danger">Deletar</button></a></td>
			</tr>
		<%
		}
		%>
	</table>		
	 <a href="/Ecommerce_Bebida/JSP/formulario_cartaoDeCredito.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 300px; margin-bottom: 386px;"></a>
	 
  	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
</body>
</html>