<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title>Listagem dos Cupons</title>
	
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
  
  <h2 style="margin-top: 30px; margin-left: 10px">Meus Cupons</h2>
  
	<table border="1" style="margin-top: 30px;" class="table table-striped">
		<tr>
            <th>Nome</th>
            <th>Valor</th>
            <th>Tipo</th>
            <th>Utilizado</th>
        </tr>
		<%
		CupomDAO dao = new CupomDAO();
		Usuario usuarioLogado = new Usuario();
		
		//cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
		HttpSession sessao = request.getSession();
		// pega o objeto salvo em Sessão com o nome "usuarioLogado",
		// e passa para o novo objeto criado com o nome "usuarioLogado", (fazendo o CAST para o tipo Usuario)
		usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// busca todos os cupons do Cliente
		List<Cupom> cupons = dao.consultarCupomByIdCliente(usuarioLogado.getId());
		
		for(Cupom coupon : cupons) {
			
			// se o cupom ja foi utilizado, ele será apresentado com um risco vermelho na listagem
			if (coupon.getUtilizado().equals("sim")) {
		%>
			<tr>
				<td><strike style="text-decoration-color: red;"><%=coupon.getNome() %></strike></td>
				<td><strike style="text-decoration-color: red;"><%=coupon.getValor() %></strike></td>
				<td><strike style="text-decoration-color: red;"><%=coupon.getTipo() %></strike></td>
				<td><strike style="text-decoration-color: red;"><%=coupon.getUtilizado() %></strike></td>
			</tr>
		<%
			}
			// caso contrário, ele não foi utilizado, então será apresentado normalmente na listagem
			else {
		%>
			<tr>
				<td><%=coupon.getNome() %></td>
				<td><%=coupon.getValor() %></td>
				<td><%=coupon.getTipo() %></td>
				<td><%=coupon.getUtilizado() %></td>
			</tr>
		<%
			}
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