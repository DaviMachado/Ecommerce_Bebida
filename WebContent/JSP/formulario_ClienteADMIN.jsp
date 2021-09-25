<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 25/09/2021 -->

<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Cadastro de Cliente</title>
		
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
	  
		<fieldset class="form-group fieldset_form" style="margin-top: 30px;  margin-bottom: 30px !important;">
		<legend align="center">Formulário para cadastro de Cliente</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastro">
			  	
				<div class="form-row">
					<!-- Nome -->
				    <div class="form-group col-md-8">
				      <label>Nome</label>
				      <input type="text" class="form-control" name="nome" placeholder="Nome" maxlength="50" required>
				    </div>

				    <!-- CPF -->
				    <div class="form-group col-md-2">
				      <label>CPF</label>
				      <input type="text" class="form-control" name="cpf" placeholder="000.000.000-00" onkeypress="return event.charCode >= 48 && event.charCode <= 57" minlength="11" maxlength="11" required>
				    </div>

				    <!-- Data Nascimento -->
				    <div class="form-group col-md-2">
				      <label>Data Nascimento</label>
				      <input type="date" class="form-control" name="dtNasc" placeholder="Data Nascimento" required>
				    </div>
			  	</div>
			  	
				<div class="form-row">
					<!-- Telefone -->
				    <div class="form-group col-md-2">
				      <label>Telefone</label>
				      <input type="text" class="form-control" name="telefone" placeholder="(11)1234-5678" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="11">
				    </div>

				    <!-- Sexo -->
			  		<div class="form-group col-md-2">
			  		<label>Sexo</label>

			  			<select name="selecioneSexo" class="form-control" placeholder="Selecione um Sexo" required>
					      	<option value="" disabled selected>Selecione uma opção...</option>
					      	<option value="masculino">Masculino</option>
					      	<option value="feminino">Feminino</option>
				      	</select>
			  		</div>
			  		
				    <!-- Status -->
			  		<div class="form-group col-md-2">
			  		<label>Status</label>

			  			<select name="status" class="form-control" placeholder="Selecione um Status" required>
					      	<option value="" disabled selected>Selecione uma opção...</option>
					      	<option value="ativo">Ativo</option>
					      	<option value="inativo">Inativo</option>
				      	</select>
			  		</div>
			  		
			  		<div class="form-group col-md-6">
			  			<!-- adicionado uma coluna com tamanho md-6 em branca para alinhar os campos da pagina -->
			  		</div>
			  	</div>
			  	
			  	<div class="form-row">
			  		<!-- E-mail -->
				  	<div class="form-group col-md-4">
				  		<label for="exampleInput">E-mail</label>
                    	<input type="email" class="form-control" name="email" placeholder="E-mail" maxlength="50" required>
				  	</div>
				  	
				  	<!-- Senha -->
				  	<div class="form-group col-md-2">
				  		<label for="exampleInput">Senha</label>
	                	<input type="password" class="form-control" name="senha" placeholder="Senha" minlength="8" required>
				  	</div>
				  	
				  	<!-- Confirmar senha -->
				  	<div class="form-group col-md-2">
				  		<label for="exampleInput">Confirmar senha</label>
	                	<input type="password" class="form-control" name="confirmarSenha" placeholder="Confirmar senha" minlength="8" required>
				  	</div>
				  	
			  		<!-- Botões CRUD -->
			  		<div class="form-group col-md-4">
			  			<div align="right" style="margin-top: 32px">
							<button class="btn btn-success" name="operacao" value="SALVAR">Cadastrar</button>
						</div>
			  		</div>
			  	</div>
			  	
			  	<!-- Parametro que é verificado se pode alterar um Cliente ou não -->
			    <input type="hidden" name="alteraCliente" id="alteraCliente" value="1">
			</form>
			
			<!-- Consultar -->
			<form class="form" action="http://localhost:8080/Ecommerce_Bebida/cadastro">

				<!-- Botões CRUD -->
	  			<div align="right" style="margin-top: 10px">
					<button class="btn btn-primary" name="operacao" value="CONSULTAR">Consultar</button>
				</div>
				
				<!-- Botão Voltar -->
				<div align="right" style="margin-top: 10px;">
					<!--<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">-->
					<a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-left: 300px;"></a>
				</div>
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