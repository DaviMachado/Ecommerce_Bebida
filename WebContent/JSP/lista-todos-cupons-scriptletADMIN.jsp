<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title>Listagem dos Cupons</title>
	
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
  
	<table border="1" style="margin-top: 30px; margin-left: 200px;">
		<tr>
            <th>Nome</th>
            <th>Valor</th>
            <th>Tipo</th>
            <th>Utilizado</th>
            <th>Cliente</th>
        </tr>
		<%
		CupomDAO dao = new CupomDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		Cupom cupom = new Cupom();
		Cliente cliente = new Cliente();
		
		List<EntidadeDominio> cupons = dao.consultar(cupom);
		
		for(EntidadeDominio e : cupons) {
		
		// Aplicado o CAST para poder popular o cupom,
		// fazendo o CAST para uma refer�ncia mais gen�rica, no caso para o cupom
		Cupom coupon = (Cupom) e;
		
		// busca o Cliente do Cupom, pelo ID do cliente no Cupom
		List<Cliente> clientes = clienteDAO.consultarClienteById(coupon.getIdCliente());
		
		// ajuste do bug de quando o cupom n�o tiver nenhum Cliente vinculado,
		// ent�o ser� adicionado um cliente vazio na lista
		if (clientes.isEmpty()) {
			clientes.add(0, cliente);
		}
		%>
			<tr>
				<td><%=coupon.getNome() %></td>
				<td><%=coupon.getValor() %></td>
				<td><%=coupon.getTipo() %></td>
				<td><%=coupon.getUtilizado() %></td>
				<td><%=clientes.get(0).getNome() %></td>
				<td><a href="/Ecommerce_Bebida/cupom?idCupom=<%= coupon.getId()%>&alteraCupom=<%= "0"%>&operacao=ALTERAR"><button class="btn btn-warning">Alterar</button></a></td>
                <td><a href="/Ecommerce_Bebida/cupom?idCupom=<%= coupon.getId()%>&operacao=EXCLUIR"><button class="btn btn-danger">Deletar</button></a></td>
			</tr>
		<%
		}
		%>
	</table>
	 <a href="/Ecommerce_Bebida/JSP/formulario_cupom.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 200px; margin-bottom: 386px;"></a>
	 
 	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
</body>
</html>