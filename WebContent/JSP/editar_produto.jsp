<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 05/12/2021 -->

<%@page import='com.les.bebida.core.dominio.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Edi��o de Produto</title>
		
		<!-- importa��es para funcionar o Header e o Footer -->
		<link href="./CSS/bootstrap.min.css" rel="stylesheet">
  		<link href="./CSS/shop-homepage.css" rel="stylesheet">
  		<link href="./CSS/form-default.css" rel="stylesheet" type="text/css">
	</head>
	
	<%
		// pega o produto que estava pendurado na requisi��o,
		// que foi enviado pelo arquivo "ProdutooHelper"
		Produto produto = (Produto)request.getAttribute("produtoPesquisado");
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
	  
		<fieldset class="form-group fieldset_form" style="margin-top: 30px; margin-bottom: 30px !important;">
		<legend align="center">Formul�rio para edi��o de Produto</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastroProduto">
			  	
				<div class="form-row">
					<!-- Nome -->
				    <div class="form-group col-md-4">
				      <label>Nome</label>
				      <input type="text" class="form-control" name="nome" placeholder="Nome" value="<%=produto.getNome()%>" required>
				    </div>

				    <!-- Descri��o -->
				    <div class="form-group col-md-8">
				      <label>Descri��o</label>
				      <input type="text" class="form-control" name="descricao" placeholder="Descri��o" value="<%=produto.getDescricao()%>" required>
				    </div>
				</div>
				
				<div class="form-row">
				    <!-- Categoria -->
			  		<div class="form-group col-md-2">
			  		<label>Categoria</label>

			  			<select name="selecioneCategoria" class="form-control" placeholder="Selecione uma Categoria" required>
					      	<option disabled>Selecione uma op��o...</option>
					      	<option><%=produto.getCategoria()%></option>
					      	<option value="cervejas">Cervejas</option>
					      	<option value="destilados">Destilados</option>
					      	<option value="refrigerantes">Refrigerantes</option>
					      	<option value="vinhos">Vinhos</option>
				      	</select>
			  		</div>
			  		
					<!-- Pre�o de Compra -->
				    <div class="form-group col-md-2">
				      <label>Pre�o de Compra</label>
				      <input type="text" class="form-control" name="preco_de_compra" placeholder="Digite um Pre�o de Compra" value="<%=produto.getPrecoDeCompra()%>" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="4" required>
				    </div>
				    
				    <!-- Pre�o de Venda -->
				    <div class="form-group col-md-2">
				      <label>Pre�o de Venda</label>
				      <input type="text" class="form-control" name="preco_de_venda" placeholder="Digite um Pre�o de Venda" value="<%=produto.getPrecoDeVenda()%>" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="4" required>
				    </div>
				    
   				    <!-- Quantidade -->
				    <div class="form-group col-md-2">
				      <label>Quantidade</label>
				      <input type="text" class="form-control" placeholder="Quantidade" value="<%=produto.getQuantidade()%>" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="3" disabled>
				    </div>
				    
   				    <!-- Grupo de Precificacao -->
				    <div class="form-group col-md-4">
				      <label>Grupo de Precificacao</label>
				      <input type="text" class="form-control" name="grupo_de_precificacao" placeholder="Grupo de Precificacao" value="<%=produto.getGrupoDePrecificacao()%>" required>
				    </div>
			  	</div>
			  	
				<div class="form-row">
				    <!-- Foto -->
				    <div class="form-group col-md-5">
				      <label>Foto</label>
				      <input type="text" class="form-control" name="foto" placeholder="Digite o caminho da pasta que esta localizada a Foto ..." value="<%=produto.getFoto()%>" required>
				    </div>
				    
				    <!-- Foto Detalhe -->
				    <div class="form-group col-md-5">
				      <label>Foto Detalhe</label>
				      <input type="text" class="form-control" name="foto_detalhe" placeholder="Digite o caminho da pasta que esta localizada o Detalhe da Foto ..." value="<%=produto.getFotoDetalhe()%>" required>
				    </div>
				    
				    <!-- Status -->
			  		<div class="form-group col-md-2">
			  		<label>Status</label>

			  			<select name="status" class="form-control" placeholder="Selecione um Status" required>
					      	<option disabled>Selecione uma op��o...</option>
					      	<option><%=produto.getStatus()%></option>
					      	<option value="ativo">Ativo</option>
					      	<option value="inativo">Inativo</option>
				      	</select>
			  		</div>
			  	</div>
			  		
			  	<div class="form-row">
			  		<!-- Observa��o -->
				    <div class="form-group col-md-12">
				      <label>Observa��o</label>
				      <textarea  class="form-control" name="observacao" placeholder="Observa��o" rows="3"><%=produto.getObservacao()%></textarea> <!-- required -->
				    </div>
			  	</div>
			  	
			  	<div class="form-row">
			  		<div class="form-group col-md-4">
			  			<!-- adicionado uma coluna com tamanho md-4 em branca para alinhar os bot�es do CRUD no final da pagina -->
			  		</div>
			  		<!-- Bot�es CRUD -->
			  		<div class="form-group col-md-8">
			  			<div align="right" style="margin-top: 10px">
							<button class="btn btn-warning" name="operacao" value="ALTERAR">Alterar</button>
						</div>
			  		</div>
			  	</div>
				
		  		<!-- Bot�o Voltar -->
				<div align="right" style="margin-top: 10px;">
					<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">
				</div>
				
				<!-- ID do Produto -->
			    <input type="hidden" name="idProduto" id="idProduto" value="<%=produto.getId() %>">
			    <!-- Parametro que � verificado se pode alterar um Produto ou n�o -->
	    		<input type="hidden" name="alteraProduto" id="alteraProduto" value="1">
	    		<!-- foi necessario deixar o campo de "quantidade" como hidden, -->
	    		<!-- pois a tag "disabled" desabilitava o campo e ao cair no ViewHelper, ele n�o reconhecia o name e deixava como NULL-->
	    		<!-- posteriormente tamb�m caia na Strategy e retornava a mensagem de erro -->
			    <input type="hidden" name="quantidade" id="quantidade" value="<%=produto.getQuantidade() %>">
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