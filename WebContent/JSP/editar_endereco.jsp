<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Edição de Endereço</title>
		
		<!-- importações para funcionar o Header e o Footer -->
		<link href="./CSS/bootstrap.min.css" rel="stylesheet">
  		<link href="./CSS/shop-homepage.css" rel="stylesheet">
  		<link href="./CSS/form-default.css" rel="stylesheet" type="text/css">
	</head>
	<%
		EnderecoDAO dao = new EnderecoDAO();
		
		// pega o id do endereço que estava pendurado na requisição,
		// que foi enviado pelo arquivo "EnderecoHelper"
		String idEndereco = (String)request.getAttribute("idEndereco");
		
		List<Endereco> endereco = dao.consultarEnderecoById(idEndereco);
		
		String idCliente = endereco.get(0).getIdCliente();
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
		<legend align="center">Formulário para edição do Endereço</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastroEndereco">
				
				<div class="form-row">
					<!-- Apelido -->
				    <div class="form-group col-md-4">
				      <label>Apelido</label>
				      <input type="text" class="form-control" name="apelido" placeholder="Apelido" value="<%=endereco.get(0).getApelido() %>">
				    </div>
				    
					<!-- CEP -->
				    <div class="form-group col-md-2">
				      <label>CEP</label>
				      <input type="number" class="form-control" name="cep" placeholder="CEP" value="<%=endereco.get(0).getCep() %>"> <!-- required -->
				    </div>
				    
				    <!-- Estado -->
					<div class="form-group col-md-2">
				    	<label>Estado</label>
			        	<select name="selecioneEstado" class="form-control" placeholder="Selecione um Estado">
					      	<option disabled>Selecione uma opção...</option>
					      	<option><%=endereco.get(0).getEstado() %></option> <!-- Acre -->
					      	<option value="AC">AC</option> <!-- Acre -->
					      	<option value="AL">AL</option> <!-- Alagoas -->
					      	<option value="AP">AP</option> <!-- AmapÃ¡ -->
					      	<option value="AM">AM</option> <!-- Amazonas -->
					      	<option value="BA">BA</option> <!-- Bahia -->
					      	<option value="CE">CE</option> <!-- CearÃ¡ -->
					      	<option value="DF">DF</option> <!-- Distrito Federal -->
					      	<option value="ES">ES</option> <!-- Espirito Santo -->
					      	<option value="GO">GO</option> <!-- GoiÃ¡s -->
					      	<option value="MA">MA</option> <!-- MaranhÃ£o -->
					      	<option value="MT">MT</option> <!-- Mato Grosso -->
					      	<option value="MS">MS</option> <!-- Mato Grosso do Sul -->
					      	<option value="MG">MG</option> <!-- Minas Gerais -->
					      	<option value="PA">PA</option> <!-- ParÃ¡ -->
					      	<option value="PB">PB</option> <!-- Paraiba -->
					      	<option value="PR">PR</option> <!-- ParanÃ¡ -->
					      	<option value="PE">PE</option> <!-- Pernambuco -->
					      	<option value="PI">PI</option> <!-- PiauÃ­ -->
					      	<option value="RJ">RJ</option> <!-- Rio de Janeiro -->
					      	<option value="RN">RN</option> <!-- Rio Grande do Norte -->
					      	<option value="RS">RS</option> <!-- Rio Grande do Sul -->
					      	<option value="RO">RO</option> <!-- RondÃ´nia -->
					      	<option value="RR">RR</option> <!-- Roraima -->
					      	<option value="SC">SC</option> <!-- Santa Catarina -->
					      	<option value="SP">SP</option> <!-- SÃ£o Paulo -->
					      	<option value="SE">SE</option> <!-- Sergipe -->
					      	<option value="TO">TO</option> <!-- Tocantins -->
				    	</select>
				    </div>
				  	
				  	<!-- Cidade -->
				    <div class="form-group col-md-4">
				      <label>Cidade</label>
				      <input type="text" class="form-control" name="cidade" placeholder="Cidade" value="<%=endereco.get(0).getCidade() %>"> <!-- required -->
				    </div>
			    </div>
			  	
			  	<div class="form-row">
				  	<!-- Logradouro -->
				    <div class="form-group col-md-6">
				      <label>Logradouro</label>
				      <input type="text" class="form-control" name="logradouro" placeholder="Logradouro (Apenas nome da rua)" value="<%=endereco.get(0).getLogradouro() %>">
				    </div>
				  	
				  	<!-- Numero -->
				    <div class="form-group col-md-2">
				      <label>Numero</label>
				      <input type="number" class="form-control" name="numero" placeholder="Numero" value="<%=endereco.get(0).getNumero() %>">
				    </div>
				  	
					<!-- Bairro -->
				    <div class="form-group col-md-4">
				      <label>Bairro</label>
				      <input type="text" class="form-control" name="bairro" placeholder="Bairro" value="<%=endereco.get(0).getBairro() %>">
				    </div>
			    </div>
			    
			    <div class="form-row">
			  		<!-- Tipo de Endereço -->
			  		<div class="form-group col-md-2">
			  		<label>Tipo de Endereço</label>

			  			<select name="tipoEndereco" class="form-control" placeholder="Selecione um Tipo de Endereço">
					      	<option value="" disabled>Selecione uma opção...</option>
					      	<option><%=endereco.get(0).getTipo_Endereco() %></option>
					      	<option value="entrega">Entrega</option>
					      	<option value="cobranca">Cobrança</option>
				      	</select>
			  		</div>
				    
   				    <!-- Tipo de Residência -->
			  		<div class="form-group col-md-2">
			  		<label>Tipo de Residência</label>

			  			<select name="tipoResidencia" class="form-control" placeholder="Selecione um Tipo de Residência">
					      	<option value="" disabled>Selecione uma opção...</option>
					      	<option><%=endereco.get(0).getTipoResidencia() %></option>
					      	<option value="casa">Casa</option>
					      	<option value="apartamento">Apartamento</option>
					      	<option value="sobrado">Sobrado</option>
					      	<option value="quitinete">Quitinete</option>
					      	<option value="loft">Loft</option>
					      	<option value="mansao">Mansão</option>
				      	</select>
			  		</div>
			  		
				    <!-- País -->
			  		<div class="form-group col-md-2">
			  		<label>País</label>

			  			<select name="pais" class="form-control" placeholder="Selecione um País">
					      	<option value="" disabled>Selecione uma opção...</option>
					      	<option><%=endereco.get(0).getPais() %></option>
					      	<option value="brasil">Brasil</option>
				      	</select>
			  		</div>
				</div>
				
				<div class="form-row">
			  		<!-- Observação -->
				    <div class="form-group col-md-12">
				      <label>Observação</label>
				      <textarea  class="form-control" name="observacao" placeholder="Observação" rows="3"><%=endereco.get(0).getObservacao() %></textarea> <!-- required -->
				    </div>
			    </div>
			  	
			  	<div class="form-row">
					<div class="form-group col-md-8">
				    	<!-- adicionado uma coluna com tamanho md-8 em branca para alinhar o botão Editar da pagina -->
				    </div>
				
					<!-- Botões CRUD-->
					<div class="form-group col-md-4">
						<div align="right" style="margin-top: 50px;">
							<button class="btn btn-warning" name="operacao" value="ALTERAR">Alterar</button>
						</div>
					</div>
				</div>
				
				<!-- Botão Voltar -->
				<div align="right" style="margin-top: 10px;">
					<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">
				</div>
				
				<!-- ID do Cliente -->
			    <input type="hidden" name="idCliente" id="idCliente" value="<%=idCliente %>">
			    <!-- ID do Endereco -->
			    <input type="hidden" name="idEndereco" id="idEndereco" value="<%=idEndereco %>">
			    <!-- Parametro que é verificado se pode alterar um Endereço ou não -->
			    <input type="hidden" name="alteraEndereco" id="alteraEndereco" value="1">
			</form>
			
			<!--  outro jeito de fazer o botão voltar
			<div align="right" style="margin-top: 10px;">
				<button class="btn btn-secondary" onclick="history.back()">Retornar</button>
			</div>
			-->
			
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