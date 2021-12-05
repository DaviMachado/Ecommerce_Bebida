<%@page import='com.les.bebida.core.dominio.*'%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
	<title>Listagem dos Cart�es de Cr�ditos</title>
	
	<!-- importa��es para funcionar o Header e o Footer -->
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
  
  	<h2 style="margin-top: 30px; margin-left: 10px">Meus Cart�es de Cr�ditos</h2>

	<table border="1" style="margin-top: 30px;" class="table table-striped">
		<tr>
            <th>Nome impresso</th>
            <th>Numero</th>
            <th>Bandeira</th>
            <th>Validade</th>
            <th>Preferencial</th>
        </tr>
		<%
		// pega todos os cart�es do Cliente que estava pendurado na requisi��o,
		// que foi enviado pelo arquivo "CartaoDeCreditoHelper"
		List<CartaoDeCredito> cartoes = (List<CartaoDeCredito>)request.getAttribute("cartoes");
		
		for(CartaoDeCredito creditCard : cartoes) {
		
		// Aplicado o CAST para poder popular o cart�o de credito,
		// fazendo o CAST para uma refer�ncia mais gen�rica, no caso para o cart�o de credito
		//CartaoDeCredito creditCard = (CartaoDeCredito) e;
		
		// busca o nome da bandeira pelo ID da Bandeira que esta vinculado no cart�o
		//nome_bandeira = bandeiraDAO.consultarBandeiraById(creditCard.getIdBandeira());
		%>
			<tr>
				<td><%=creditCard.getNome() %></td>
				<td><%=creditCard.getNum_cartao() %></td>
				<td><%=creditCard.getNomeBandeira() %></td>
				<td><%=creditCard.getDt_validade() %></td>
				<td><%=creditCard.getFlgPreferencial() %></td>
				<td><a href="/Ecommerce_Bebida/cadastroCartaoCredito?idCartaoDeCredito=<%= creditCard.getId()%>&alteraCartao=<%= "0"%>&operacao=ALTERAR"><button class="btn btn-warning">Alterar</button></a></td>
                <td><a href="/Ecommerce_Bebida/cadastroCartaoCredito?idCartaoDeCredito=<%= creditCard.getId()%>&idCliente=<%= creditCard.getIdCliente()%>&operacao=EXCLUIR"><button class="btn btn-danger">Deletar</button></a></td>
			</tr>
		<%
		}
		%>
	</table>		
	 <a href="/Ecommerce_Bebida/JSP/formulario_cartaoDeCredito.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 10px; margin-bottom: 386px;"></a>
	 
  	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
</body>
</html>