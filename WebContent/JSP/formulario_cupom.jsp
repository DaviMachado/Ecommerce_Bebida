<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 04/09/2021 -->

<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Cadastro de Cupom</title>
		
		<!-- importações para funcionar o Header e o Footer -->
		<link href="../CSS/bootstrap.min.css" rel="stylesheet">
  		<link href="../CSS/shop-homepage.css" rel="stylesheet">
  		<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
	</head>
	
	<%
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente();
		
		// pega todos os clientes cadastrados no sistema
		List<EntidadeDominio> allClientes = clienteDAO.consultar(cliente);
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
		<legend align="center">Formulário para cadastro de Cupom</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cupom">
			  	
				<div class="form-row">
					<!-- Nome -->
				    <div class="form-group col-md-8">
				      <label>Nome</label>
				      <input type="text" class="form-control" name="nome" placeholder="Nome" required>
				    </div>

				    <!-- Valor -->
				    <div class="form-group col-md-2">
				      <label>Valor</label>
				      <input type="text" class="form-control" name="valor" placeholder="Digite um Valor" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="4" required>
				    </div>
				    
				    <!-- Utilizado -->
			  		<div class="form-group col-md-2">
			  		<label>Utilizado</label>

			  			<select name="utilizado" class="form-control" placeholder="Selecione uma opção" required>
					      	<option value="" disabled selected>Selecione uma opção...</option>
					      	<option value="sim">Sim</option>
					      	<option value="nao">Não</option>
				      	</select>
			  		</div>
				</div>
				
				<div class="form-row">
				    <!-- Cliente -->
			  		<div class="form-group col-md-6">
			  		<label>Cliente</label>

			  			<select name="idCliente" class="form-control" placeholder="Selecione um Cliente" required>
					      	<option value="" disabled selected>Selecione uma opção...</option>
					      	<% 
						      	for(EntidadeDominio e : allClientes) {
				
								// Aplicado o CAST para poder popular o cliente,
								// fazendo o CAST para uma referência mais genérica, no caso para o cliente,
								// lista todos os clientes indexado com o ID do cliente dentro do "value", de cada da TAG "<option>".
								Cliente client = (Cliente) e;
							%>
					      	<option value="<%=client.getId()%>"><%=client.getNome()%></option>
					      	<%
								}
							%>
				      	</select>
			  		</div>
			  		
					<!-- Tipo -->
			  		<div class="form-group col-md-2">
			  		<label>Tipo</label>

			  			<select name="tipo" class="form-control" placeholder="Selecione um Tipo" required>
					      	<option value="" disabled selected>Selecione uma opção...</option>
					      	<option value="promocional">Promocional</option>
					      	<option value="troca">Troca</option>
					      	<option value="devolucao">Devolução</option>
				      	</select>
			  		</div>
			  	</div>
			  	
			  	<div class="form-row">
			  		<div class="form-group col-md-4">
			  			<!-- adicionado uma coluna com tamanho md-4 em branca para alinhar os botões do CRUD no final da pagina -->
			  		</div>
			  		<!-- Botões CRUD -->
			  		<div class="form-group col-md-8">
			  			<div align="right" style="margin-top: 10px">
							<button class="btn btn-success" name="operacao" value="SALVAR">Cadastrar</button>
						</div>
			  		</div>
			  	</div>
				
				<!-- Parametro que é verificado se pode alterar um Cupom ou não -->
	    		<input type="hidden" name="alteraCupom" id="alteraCupom" value="1">
			</form>
			
			<!-- Consultar -->
			<form class="form" action="http://localhost:8080/Ecommerce_Bebida/cupom">
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