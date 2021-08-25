<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 25/08/2021 -->

<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Cadastro de Estoque</title>
		
		<!-- importações para funcionar o Header e o Footer -->
		<link href="../CSS/bootstrap.min.css" rel="stylesheet">
  		<link href="../CSS/shop-homepage.css" rel="stylesheet">
  		<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
	</head>
	
	<%
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = new Produto();	
	
		// pega todos os produtos cadastrados no sistema
		List<EntidadeDominio> allProdutos = dao.consultar(produto);
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
	  
		<fieldset class="form-group fieldset_form" style="margin-top: 30px; margin-bottom: 10px !important;">
		<legend align="center">Formulário para cadastro de Estoque</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastroEstoque">
			  	
				<div class="form-row">
					<!-- Produtos -->
			  		<div class="form-group col-md-6">
			  		<label>Produtos</label>
	
			  			<select name="selecioneProduto" class="form-control" placeholder="Selecione um Produto" required>
					      	<option value="" disabled selected>Selecione uma opção...</option>
					      	<% 
						      	for(EntidadeDominio e : allProdutos) {
				
								// Aplicado o CAST para poder popular o produto,
								// fazendo o CAST para uma referência mais genérica, no caso para o produto,
								// lista todos os produtos indexado com o ID do produto dentro do "value", de cada da TAG "<option>".
								Produto product = (Produto) e;
							%>
					      	<option value="<%=product.getId()%>"><%=product.getNome()%></option>
					      	<%
								}
							%>
				      	</select>
			  		</div>
			  		
			  		<!-- Tipo -->
			  		<div class="form-group col-md-4">
			  		<label>Tipo</label>

			  			<select name="tipo" class="form-control" placeholder="Selecione um Tipo" required>
					      	<option value="" disabled selected>Selecione uma opção...</option>
					      	<option value="entrada">Entrada</option>
					      	<option value="saida">Saida</option>
				      	</select>
			  		</div>
			  		
   				    <!-- Quantidade Entrada/Saida-->
				    <div class="form-group col-md-2">
				      <label>Quantidade Entrada/Saida</label>
				      <input type="text" class="form-control" name="quantidade_entrada_saida" placeholder="Quantidade Entrada/Saida" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="3" required>
				    </div>
				</div>
				
				<div class="form-row">
   					<!-- Valor de Custo -->
				    <div class="form-group col-md-2">
				      <label>Valor de Custo</label>
				      <input type="text" class="form-control" name="valor_custo" placeholder="Valor de Custo" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="4" required>
				    </div>
				    
					<!-- Fornecedor -->
				    <div class="form-group col-md-8">
				      <label>Fornecedor</label>
				      <input type="text" class="form-control" name="fornecedor" placeholder="Fornecedor" required>
				    </div>

				    <!-- Data Entrada -->
				    <div class="form-group col-md-2">
				      <label>Data Entrada</label>
				      <input type="date" class="form-control" name="dt_entrada" placeholder="Data Entrada" required>
				    </div>
				</div>
			  	
			  	<div class="form-row">
			  		<div class="form-group col-md-4">
			  			<!-- adicionado uma coluna com tamanho md-4 em branca para alinhar os botões do CRUD no final da pagina -->
			  		</div>
			  		<!-- Botões CRUD -->
			  		<div class="form-group col-md-8">
			  			<div align="right" style="margin-top: 50px">
							<button class="btn btn-success" name="operacao" value="SALVAR">Cadastrar</button>
						</div>
			  		</div>
			  	</div>
				
		  		<!-- Botão Voltar -->
				<div align="right" style="margin-top: 10px;">
					<!--<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">-->
					<a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-left: 300px;"></a>
				</div>
			</form>
		</fieldset>
		
		<fieldset class="form-group fieldset_form" style="margin-top: 30px; margin-bottom: 30px !important;">
		<legend align="center">Consulta de Estoque</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastroEstoque">
			
				<div class="form-row">
					<!-- Produtos -->
			  		<div class="form-group col-md-8">
			  		<label>Produtos</label>
	
			  			<select name="selecioneProduto" class="form-control" placeholder="Selecione um Produto" required>
					      	<option value="" disabled selected>Selecione uma opção...</option>
					      	<% 
						      	for(EntidadeDominio e : allProdutos) {
				
								// Aplicado o CAST para poder popular o produto,
								// fazendo o CAST para uma referência mais genérica, no caso para o produto,
								// lista todos os produtos indexado com o ID do produto dentro do "value", de cada da TAG "<option>".
								Produto product = (Produto) e;
							%>
					      	<option value="<%=product.getId()%>"><%=product.getNome()%></option>
					      	<%
								}
							%>
				      	</select>
			  		</div>
			  		
			  		<!-- Botões CRUD -->
			  		<div class="form-group col-md-4">
			  			<div align="right" style="margin-top: 31px">
							<button class="btn btn-primary" name="operacao" value="CONSULTAR">Consultar</button>
						</div>
			  		</div>
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