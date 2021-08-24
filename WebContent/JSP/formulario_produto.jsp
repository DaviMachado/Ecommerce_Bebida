<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 15/08/2021 -->

<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Cadastro de Produto</title>
		
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
	  
		<fieldset class="form-group fieldset_form" style="margin-top: 30px">
		<legend align="center">Formulário para cadastro de Produto</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastroProduto">
			  	
				<div class="form-row">
					<!-- Nome -->
				    <div class="form-group col-md-4">
				      <label>Nome</label>
				      <input type="text" class="form-control" name="nome" placeholder="Nome"> <!-- required -->
				    </div>

				    <!-- Descrição -->
				    <div class="form-group col-md-8">
				      <label>Descrição</label>
				      <input type="text" class="form-control" name="descricao" placeholder="Descrição">
				    </div>
				</div>
				
				<div class="form-row">
				    <!-- Categoria -->
			  		<div class="form-group col-md-2">
			  		<label>Categoria</label>

			  			<select name="selecioneCategoria" class="form-control" placeholder="Selecione uma Categoria">
					      	<option disabled selected>Selecione uma opção...</option>
					      	<option value="cervejas">Cervejas</option>
					      	<option value="destilados">Destilados</option>
					      	<option value="refrigerantes">Refrigerantes</option>
					      	<option value="vinhos">Vinhos</option>
				      	</select>
			  		</div>
			  		
					<!-- Preço de Compra -->
				    <div class="form-group col-md-2">
				      <label>Preço de Compra</label>
				      <input type="number" class="form-control" name="preco_de_compra" placeholder="Digite um Preço de Compra">
				    </div>
				    
				    <!-- Preço de Venda -->
				    <div class="form-group col-md-2">
				      <label>Preço de Venda</label>
				      <input type="number" class="form-control" name="preco_de_venda" placeholder="Digite um Preço de Venda">
				    </div>
				    
   				    <!-- Quantidade -->
				    <div class="form-group col-md-2">
				      <label>Quantidade</label>
				      <input type="number" class="form-control" name="quantidade" placeholder="Quantidade"> <!-- required -->
				    </div>
				    
   				    <!-- Grupo de Precificacao -->
				    <div class="form-group col-md-4">
				      <label>Grupo de Precificacao</label>
				      <input type="text" class="form-control" name="grupo_de_precificacao" placeholder="Grupo de Precificacao"> <!-- required -->
				    </div>
			  	</div>
			  	
				<div class="form-row">
				    <!-- Foto -->
				    <div class="form-group col-md-10">
				      <label>Foto</label>
				      <input type="text" class="form-control" name="foto" placeholder="Digite o caminho da pasta que esta localizada a Foto ..."> <!-- required -->
				    </div>
				    
				    <!-- Status -->
			  		<div class="form-group col-md-2">
			  		<label>Status</label>

			  			<select name="status" class="form-control" placeholder="Selecione um Status">
					      	<option disabled selected>Selecione uma opção...</option>
					      	<option value="ativo">Ativo</option>
					      	<option value="inativo">Inativo</option>
				      	</select>
			  		</div>
			  	</div>
			  		
			  	<div class="form-row">
			  		<!-- Observação -->
				    <div class="form-group col-md-12">
				      <label>Observação</label>
				      <textarea  class="form-control" name="observacao" placeholder="Observação" rows="3"></textarea> <!-- required -->
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
				
				<!-- Parametro que é verificado se pode alterar um Produto ou não -->
	    		<input type="hidden" name="alteraProduto" id="alteraProduto" value="1">
			</form>
			
			<!-- Consultar -->
			<form class="form" action="http://localhost:8080/Ecommerce_Bebida/cadastroProduto">
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