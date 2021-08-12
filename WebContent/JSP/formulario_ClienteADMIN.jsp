<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 12/08/2021 -->

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
	      <a class="navbar-brand">Ecommerce de Bebida</a>
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
		<legend align="center">Formulário para cadastro de Cliente</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastro">
			  	
				<div class="form-row">
					<!-- Nome -->
				    <div class="form-group col-md-8">
				      <label>Nome</label>
				      <input type="text" class="form-control" name="nome" placeholder="Nome"> <!-- required -->
				    </div>

				    <!-- CPF -->
				    <div class="form-group col-md-2">
				      <label>CPF</label>
				      <input type="text" class="form-control" name="cpf" placeholder="000.000.000-00">
				    </div>

				    <!-- Data Nascimento -->
				    <div class="form-group col-md-2">
				      <label>Data Nascimento</label>
				      <input type="date" class="form-control" name="dtNasc" placeholder="Data Nascimento">
				    </div>
			  	</div>
			  	
				<div class="form-row">
					<!-- Telefone -->
				    <div class="form-group col-md-2">
				      <label>Telefone</label>
				      <input type="number" class="form-control" name="telefone" placeholder="(11)1234-5678">
				    </div>

				    <!-- Sexo -->
			  		<div class="form-group col-md-2">
			  		<label>Sexo</label>

			  			<select name="selecioneSexo" class="form-control" placeholder="Selecione um Sexo">
					      	<option disabled selected>Selecione uma opção...</option>
					      	<option value="masculino">Masculino</option>
					      	<option value="feminino">Feminino</option>
				      	</select>
			  		</div>
			  		
			  		<!-- Botões CRUD -->
			  		<div class="form-group col-md-8">
			  			<div align="right" style="margin-top: 50px">
							<button class="btn btn-success" name="operacao" value="SALVAR">Cadastrar</button>
							<button class="btn btn-primary" name="operacao" value="CONSULTAR">Consultar</button>
						</div>
			  		</div>
			  	</div>
				
		  		<!-- Botão Voltar -->
				<div align="right" style="margin-top: 10px;">
					<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">
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