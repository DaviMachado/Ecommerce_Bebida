<%@page import='com.les.bebida.core.dominio.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title>Listagem dos Clientes</title>
	
	<!-- importa��es para funcionar o Header e o Footer -->
	<link href="./CSS/bootstrap.min.css" rel="stylesheet">
	<link href="./CSS/shop-homepage.css" rel="stylesheet">
	<link href="./CSS/form-default.css" rel="stylesheet" type="text/css">
</head>

<%
	//pega todos clientes (somente clientes) que estava pendurado na requisi��o,
	// que foi enviado pelo arquivo "ClienteHelper"
	List<Cliente> clientes = (List<Cliente>)request.getAttribute("todosClientes");
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
  
  	<h2 style="margin-top: 30px; margin-left: 100px;">Listagem de Todos os Clientes</h2>
  	
  	<!-- Campo Pesquisa-->
    <form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/pesquisaByFiltro">
    	<div class="row justify-content-md-center">
        	<div style="margin-top: 10px;" class="form-group col-md-8">
				<input type="text" class="form-control" name="nomeCliente" placeholder="Buscar Cliente..." required>
			</div>
			
			<div style="margin-top: 10px;" class="form-group col-md-2">
				<button class="btn btn-success" name="operacao" value="CONSULTAR">Pesquisar</button>
			</div>
        </div>
        
        <!-- Nome da tabela que ser� consultado -->
        <input type="hidden" name="NomeTabela" value="Cliente">
	</form>
  
	<table border="1" style="margin-top: 30px;" class="table table-striped">
		<tr>
            <th>E-mail</th>
            <!--<th>Senha</th>-->
            <th>Nome</th>
            <th>CPF</th>
            <th>Dt Nascimento</th>
            <th>Sexo</th>
            <th>Telefone</th>
            <th>C�digo Sistema</th>
            <th>Status</th>
        </tr>
		<%
		for(Cliente c : clientes) {
		
		// Aplicado o CAST para poder popular o cliente,
		// fazendo o CAST para uma refer�ncia mais gen�rica, no caso para o cliente
		//Cliente c = (Cliente) e;
		// pega o usuario que esta dentro do cliente
		Usuario u = c.getUsuario();
		%>
			<tr>
				<td><%=u.getLogin() %></td>
				<!--<td><%=u.getSenha() %></td>-->
				<td><%=c.getNome() %></td>
				<td><%=c.getCpf() %></td>
				<td><%=c.getDt_nasc() %></td>
				<td><%=c.getSexo() %></td>
				<td><%=c.getTelefone() %></td>
				<td><%=c.getCdSistema() %></td>
				<td><%=c.getStatus()%></td>
				<td><a href="/Ecommerce_Bebida/cadastro?id=<%= c.getId()%>&alteraCliente=<%= "0"%>&operacao=ALTERAR"><button class="btn btn-warning">Alterar</button></a></td>
                <td><a href="/Ecommerce_Bebida/cadastro?id=<%= c.getId()%>&operacao=EXCLUIR"><button class="btn btn-danger">Deletar</button></a></td>
			</tr>
		<%
		}
		%>
	</table>
	 <a href="/Ecommerce_Bebida/JSP/formulario_ClienteADMIN.jsp"><input type="button" value="Voltar" style="margin-top: 10px; margin-left: 10px; margin-bottom: 386px;"></a>
	 
 	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
</body>
</html>