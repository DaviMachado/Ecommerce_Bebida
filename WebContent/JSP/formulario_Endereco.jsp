<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 05/12/2021 -->

<%@page import='com.les.bebida.core.dominio.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Cadastro de Endere�o</title>
		
		<!-- importa��es para funcionar o Header e o Footer -->
		<link href="../CSS/bootstrap.min.css" rel="stylesheet">
  		<link href="../CSS/shop-homepage.css" rel="stylesheet">
  		<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
	</head>
	
	<%
		Usuario usuarioLogado = new Usuario();
		
		// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
		HttpSession sessao = request.getSession();
		// pega o objeto salvo em Sess�o com o nome "usuarioLogado",
		// e passa para o novo objeto criado com o nome "usuarioLogado", (fazendo o CAST para o tipo Usuario)
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
		<legend align="center">Formul�rio para cadastro de Endere�o</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastroEndereco">
				
				<div class="form-row">
					<!-- Apelido -->
				    <div class="form-group col-md-4">
				      <label>Apelido</label>
				      <input type="text" class="form-control" name="apelido" placeholder="Apelido">
				    </div>
				    
					<!-- CEP -->
				    <div class="form-group col-md-2">
				      <label>CEP</label>
				      <input type="text" class="form-control" name="cep" placeholder="CEP" onkeypress="return event.charCode >= 48 && event.charCode <= 57" minlength="8" maxlength="8" required>
				    </div>
				    
				    <!-- Estado -->
					<div class="form-group col-md-2">
				    	<label>Estado</label>
			        	<select name="selecioneEstado" class="form-control" placeholder="Selecione um Estado" required>
					      	<option value="" disabled selected>Selecione uma op��o...</option>
					      	<option value="AC">AC</option> <!-- Acre -->
					      	<option value="AL">AL</option> <!-- Alagoas -->
					      	<option value="AP">AP</option> <!-- Amap� -->
					      	<option value="AM">AM</option> <!-- Amazonas -->
					      	<option value="BA">BA</option> <!-- Bahia -->
					      	<option value="CE">CE</option> <!-- Cear� -->
					      	<option value="DF">DF</option> <!-- Distrito Federal -->
					      	<option value="ES">ES</option> <!-- Espirito Santo -->
					      	<option value="GO">GO</option> <!-- Goi�s -->
					      	<option value="MA">MA</option> <!-- Maranh�o -->
					      	<option value="MT">MT</option> <!-- Mato Grosso -->
					      	<option value="MS">MS</option> <!-- Mato Grosso do Sul -->
					      	<option value="MG">MG</option> <!-- Minas Gerais -->
					      	<option value="PA">PA</option> <!-- Par� -->
					      	<option value="PB">PB</option> <!-- Paraiba -->
					      	<option value="PR">PR</option> <!-- Paran� -->
					      	<option value="PE">PE</option> <!-- Pernambuco -->
					      	<option value="PI">PI</option> <!-- Piau� -->
					      	<option value="RJ">RJ</option> <!-- Rio de Janeiro -->
					      	<option value="RN">RN</option> <!-- Rio Grande do Norte -->
					      	<option value="RS">RS</option> <!-- Rio Grande do Sul -->
					      	<option value="RO">RO</option> <!-- Rond�nia -->
					      	<option value="RR">RR</option> <!-- Roraima -->
					      	<option value="SC">SC</option> <!-- Santa Catarina -->
					      	<option value="SP">SP</option> <!-- S�o Paulo -->
					      	<option value="SE">SE</option> <!-- Sergipe -->
					      	<option value="TO">TO</option> <!-- Tocantins -->
				    	</select>
				    </div>
				  	
				  	<!-- Cidade -->
				    <div class="form-group col-md-4">
				      <label>Cidade</label>
				      <input type="text" class="form-control" name="cidade" placeholder="Cidade" required>
				    </div>
			    </div>
			  	
			  	<div class="form-row">
				  	<!-- Logradouro -->
				    <div class="form-group col-md-6">
				      <label>Logradouro</label>
				      <input type="text" class="form-control" name="logradouro" placeholder="Logradouro (Apenas nome da rua)" required>
				    </div>
				  	
				  	<!-- Numero -->
				    <div class="form-group col-md-2">
				      <label>Numero</label>
				      <input type="text" class="form-control" name="numero" placeholder="Numero" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="6" required>
				    </div>
				  	
					<!-- Bairro -->
				    <div class="form-group col-md-4">
				      <label>Bairro</label>
				      <input type="text" class="form-control" name="bairro" placeholder="Bairro" required>
				    </div>
			    </div>
			  	
			  	<div class="form-row">
			  		<!-- Tipo de Endere�o -->
			  		<div class="form-group col-md-2">
			  		<label>Tipo de Endere�o</label>

			  			<select name="tipoEndereco" class="form-control" placeholder="Selecione um Tipo de Endere�o" required>
					      	<option value="" disabled selected>Selecione uma op��o...</option>
					      	<option value="entrega">Entrega</option>
					      	<option value="cobranca">Cobran�a</option>
				      	</select>
			  		</div>
				    
   				    <!-- Tipo de Resid�ncia -->
			  		<div class="form-group col-md-2">
			  		<label>Tipo de Resid�ncia</label>

			  			<select name="tipoResidencia" class="form-control" placeholder="Selecione um Tipo de Resid�ncia" required>
					      	<option value="" disabled selected>Selecione uma op��o...</option>
					      	<option value="casa">Casa</option>
					      	<option value="apartamento">Apartamento</option>
					      	<option value="sobrado">Sobrado</option>
					      	<option value="quitinete">Quitinete</option>
					      	<option value="loft">Loft</option>
					      	<option value="mansao">Mans�o</option>
				      	</select>
			  		</div>
			  		
				    <!-- Pa�s -->
			  		<div class="form-group col-md-2">
			  		<label>Pa�s</label>

			  			<select name="pais" class="form-control" placeholder="Selecione um Pa�s" required>
					      	<option value="" disabled selected>Selecione uma op��o...</option>
					      	<option value="brasil">Brasil</option>
				      	</select>
			  		</div>
				</div>
				
				<div class="form-row">
			  		<!-- Observa��o -->
				    <div class="form-group col-md-12">
				      <label>Observa��o</label>
				      <textarea  class="form-control" name="observacao" placeholder="Observa��o" rows="3"></textarea>
				    </div>
			    </div>
			    
			    <div class="form-row">
				    <div class="form-group col-md-8">
				    	<!-- adicionado uma coluna com tamanho md-8 em branca para alinhar o bot�o Salvar da pagina -->
				    </div>
				    
   					<!-- Bot�es CRUD-->
					<div class="form-group col-md-4">
						<div align="right" >
							<button class="btn btn-success" name="operacao" value="SALVAR">Cadastrar</button>
						</div>
					</div>
			    </div>
				
				<!-- ID do Cliente -->
				<input type="hidden" name="idCliente" id="idCliente" value="<%=usuarioLogado.getId() %>">
				<!-- Parametro que � verificado se pode alterar um Endere�o ou n�o -->
			    <input type="hidden" name="alteraEndereco" id="alteraEndereco" value="1">
			</form>
			
			<!-- Consultar -->
			<form class="form" action="http://localhost:8080/Ecommerce_Bebida/cadastroEndereco">
				<!-- Bot�es CRUD -->
	  			<div align="right" style="margin-top: 10px">
	  				<button class="btn btn-primary" name="operacao" value="CONSULTAR">Consultar</button>
	  			</div>
	  			
				<!-- Bot�o Voltar -->
				<div align="right" style="margin-top: 10px;">
					<!--<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">-->
					<a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-left: 300px;"></a>
				</div>
				
				<!-- ID do Cliente -->
				<input type="hidden" name="idCliente" id="idCliente" value="<%=usuarioLogado.getId() %>">
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