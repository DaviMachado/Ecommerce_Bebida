<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 17/09/2021 -->

<%@page import='com.les.bebida.core.dominio.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Edição da Senha do Cliente</title>
		
		<!-- importações para funcionar o Header e o Footer -->
		<link href="../CSS/bootstrap.min.css" rel="stylesheet">
  		<link href="../CSS/shop-homepage.css" rel="stylesheet">
  		<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
	</head>
	<%
		Usuario usuarioLogado = new Usuario();
		
		HttpSession sessao = request.getSession();
		usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
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
	
		<fieldset class="form-group fieldset_form" style="margin-top: 80px">
		<legend align="center">Formulário para editar somente a Senha do Cliente</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastro">
			  	
				<div class="form-row">
					<!-- Senha -->
				    <div class="form-group col-md-6">
				      <label>Nova Senha</label>
				      <input type="password" class="form-control" name="senha" placeholder="Senha" minlength="8" required>
				    </div>

				    <!-- Confirmar Senha -->
				    <div class="form-group col-md-6">
				      <label>Confirmar Nova Senha</label>
				      <input type="password" class="form-control" name="confirmarSenha" placeholder="Senha" minlength="8" required>
				    </div>
			  	</div>
			  	
				<div class="form-row">
					<div class="form-group col-md-8">
			  			<!-- adicionado uma coluna com tamanho md-8 em branca para alinhar os botões do CRUD no final da pagina -->
			  		</div>
			  		<!-- Botóes CRUD -->
			  		<div class="form-group col-md-4">
				  		<div align="right" style="margin-top: 10px;">
				  			<button class="btn btn-warning" name="operacao" value="ALTERAR">Alterar</button>
				  		</div>
			  		</div>
			  	</div>
				
				<!-- Botão Voltar -->
				<div align="right">
					<!--<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">-->
					<a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-left: 300px;"></a>
				</div>
				
				<!-- ID do Cliente -->
			    <input type="hidden" name="id" id="id" value="<%=usuarioLogado.getId() %>">
			    <!-- Parametro que é verificado se pode alterar um Cliente ou não -->
			    <input type="hidden" name="alteraCliente" id="alteraCliente" value="1">
			    
			    <!-- os atributos a baixo foram preenchidos, porem ficaram com o tipo "hidden", -->
			    <!-- para não mandar NULL quando estiver alterando um cliente, e consequentemente salvar NULL no banco de dados -->
			    <!-- Nome -->
		  		<input type="hidden" name="nome" value="<%=usuarioLogado.getNome() %>">
		  		<!-- CPF -->
		  		<input type="hidden" name="cpf" value="<%=usuarioLogado.getCpf() %>">
		  		<!-- Data Nascimento -->
		  		<input type="hidden" name="dtNasc" value="<%=usuarioLogado.getDt_nasc() %>">
		  		<!-- Telefone -->
		  		<input type="hidden" name="telefone" value="<%=usuarioLogado.getTelefone() %>">
		  		<!-- Sexo -->
		  		<input type="hidden" name="selecioneSexo" value="<%=usuarioLogado.getSexo() %>">
		  		<!-- Status -->
		  		<input type="hidden" name="status" value="<%=usuarioLogado.getStatus() %>">
		  		<!-- E-mail -->
               	<input type="hidden" name="email" value="<%=usuarioLogado.getLogin() %>">

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