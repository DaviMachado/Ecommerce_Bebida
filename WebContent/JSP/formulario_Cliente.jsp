<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 16/08/2021 -->

<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Edição do Cliente</title>
		
		<!-- importações para funcionar o Header e o Footer -->
		<link href="../CSS/bootstrap.min.css" rel="stylesheet">
  		<link href="../CSS/shop-homepage.css" rel="stylesheet">
  		<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
	</head>
	<%
		ClienteDAO dao = new ClienteDAO();
		Usuario usuarioLogado = new Usuario();
		
		HttpSession sessao = request.getSession();
		usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		List<Cliente> cliente = dao.consultarClienteById(usuarioLogado.getId());
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
	
		<fieldset class="form-group fieldset_form" style="margin-top: 30px">
		<legend align="center">Formulário para edição de Cliente</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastro">
			  	
				<div class="form-row">
					<!-- Nome -->
				    <div class="form-group col-md-8">
				      <label>Nome</label>
				      <input type="text" class="form-control" name="nome" placeholder="Nome" value="<%=cliente.get(0).getNome() %>" required>
				    </div>

				    <!-- CPF -->
				    <div class="form-group col-md-2">
				      <label>CPF</label>
				      <input type="text" class="form-control" name="cpf" placeholder="000.000.000-00" value="<%=cliente.get(0).getCpf() %>" onkeypress="return event.charCode >= 48 && event.charCode <= 57" minlength="11" maxlength="11" required>
				    </div>

				    <!-- Data Nascimento -->
				    <div class="form-group col-md-2">
				      <label>Data Nascimento</label>
				      <input type="date" class="form-control" name="dtNasc" placeholder="Data Nascimento" value="<%=cliente.get(0).getDt_nasc() %>" required>
				    </div>
			  	</div>
			  	
				<div class="form-row">
					<!-- Telefone -->
				    <div class="form-group col-md-2">
				      <label>Telefone</label>
				      <input type="text" class="form-control" name="telefone" placeholder="(11)1234-5678" value="<%=cliente.get(0).getTelefone() %>" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="11">
				    </div>

				    <!-- Sexo -->
			  		<div class="form-group col-md-2">
			  		<label>Sexo</label>

			  			<select name="selecioneSexo" class="form-control" placeholder="Selecione um Sexo" required>
					      	<option disabled>Selecione uma opção...</option>
					      	<option value="<%=cliente.get(0).getSexo() %>"><%=cliente.get(0).getSexo() %></option>
					      	<option value="masculino">Masculino</option>
					      	<option value="feminino">Feminino</option>
				      	</select>
			  		</div>
			  		
			  		<!-- Botóes CRUD -->
			  		<div class="form-group col-md-8">
				  		<div align="right" style="margin-top: 32px">
				  			<button class="btn btn-warning" name="operacao" value="ALTERAR">Alterar</button>
				  		</div>
			  		</div>
			  	</div>
				
				<!-- Botão Voltar -->
				<div align="right" style="margin-top: 10px;">
					<!--<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">-->
					<a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-left: 300px;"></a>
				</div>
				
				<!-- ID do Cliente -->
			    <input type="hidden" name="id" id="id" value="<%=cliente.get(0).getId() %>">
			    <!-- Parametro que é verificado se pode alterar um Cliente ou não -->
			    <input type="hidden" name="alteraCliente" id="alteraCliente" value="1">
			    
			    <!-- os atributos "status", "login", "senha" e "confirmarSenha", foram preenchidos, porem ficaram com o tipo "hidden", -->
			    <!-- para não mandar NULL quando estiver alterando um cliente, e consequentemente salvar NULL no banco de dados -->
		  		<!-- Status -->
		  		<input type="hidden" name="status" value="<%=cliente.get(0).getStatus() %>">
		  		<!-- E-mail -->
               	<input type="hidden" name="email" value="<%=cliente.get(0).getUsuario().getLogin() %>">
			  	<!-- Senha -->
               	<input type="hidden" name="senha" value="<%=cliente.get(0).getUsuario().getSenha() %>">
               	<!-- Confirmar Senha -->
               	<input type="hidden" name="confirmarSenha" value="<%=cliente.get(0).getUsuario().getSenha() %>">

			</form>
		</fieldset>
		
	  <!-- Footer -->
	  <footer class="py-5 bg-dark">
	    <div class="container">
	      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
	    </div>
	  </footer>
  	  <!-- Fim Footer -->
		
	</body>
</html>