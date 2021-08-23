<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
	<title>Listagem do Carrinho</title>
	
	<!-- importa��es para funcionar o Header e o Footer -->
	<link href="../CSS/bootstrap.min.css" rel="stylesheet">
	<link href="../CSS/shop-homepage.css" rel="stylesheet">
	<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
</head>

<%
	List<Produto> produtosEmSessao = new ArrayList<>();

	//cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
	HttpSession sessao = request.getSession();
	// pega o objeto salvo em Sess�o com o nome "itensCarrinho",
	// e passa para o "produtosEmSessao" (fazendo o CAST para o tipo List<Produto>)
	produtosEmSessao = (List<Produto>) sessao.getAttribute("itensCarrinho");
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
  
  	<h2 style="margin-top: 30px; margin-left: 100px">Carrinho</h2>

	<table border="1" style="margin-top: 30px; margin-left: 100px;">
		<tr>
			<th>Nome</th>
            <th>Descri��o</th>
        </tr>
		<%
			for(Produto produto : produtosEmSessao) {
		%>
			<tr>
				<td><%=produto.getNome() %></td>
				<td><%=produto.getDescricao() %></td>
				<td>
					<form class="form" action="http://localhost:8080/Ecommerce_Bebida/carrinho">
						<div class="form-row">
							<button class="btn btn-success" name="tipoDeOperacao" value="adicao" style="height: 40px !important; margin-top: 26px;">+</button>
							
							<div style="width: 60px !important; margin-left: 10px;">
						      <label>Qtde</label>
						      <input type="text" class="form-control" name="quantidadeSelecionada" placeholder="Qtde" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="3" value="<%=produto.getQuantidadeSelecionada() %>">
						    </div>
						    
						    <button class="btn btn-danger" name="tipoDeOperacao" value="subtracao" style="height: 40px !important; margin-top: 26px;">-</button>
					    </div>
					    
					    <!-- opera��o que � acionada atrav�s do form -->
			    		<input type="hidden" name="operacao" id="operacao" value="ALTERAR">
			    		<!-- ID do Cliente -->
						<input type="hidden" name="idProduto" id="idProduto" value="<%=produto.getId() %>">
					</form>
				</td>
			</tr>
		<%
		}
		%>
	</table>		
	 <a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-left: 100px; margin-top: 10px; margin-bottom: 318px;"></a>
	 
  	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
</body>
</html>