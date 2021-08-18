<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 17/08/2021 -->

<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Cadastro de Endereço</title>
		
		<!-- importações para funcionar o Header e o Footer -->
		<link href="../CSS/bootstrap.min.css" rel="stylesheet">
  		<link href="../CSS/shop-homepage.css" rel="stylesheet">
  		<link href="../CSS/form-default.css" rel="stylesheet" type="text/css">
	</head>
	
	<%
		ClienteDAO dao = new ClienteDAO();
		Usuario usuarioLogado = new Usuario();
		
		// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
		HttpSession sessao = request.getSession();
		// pega o objeto salvo em Sessão com o nome "usuarioLogado",
		// e passa para o novo objeto criado com o nome "usuarioLogado", (fazendo o CAST para o tipo Usuario)
		usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// faz um consulta no banco para pegar todos os dados do cliente logado
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
		<legend align="center">Formulário para cadastro de Endereço</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cadastroEndereco">
				
				<div class="form-row">
					<!-- CEP -->
				    <div class="form-group col-md-6">
				      <label>CEP</label>
				      <input type="number" class="form-control" name="cep" placeholder="CEP"> <!-- required -->
				    </div>
				  	
				  	<!-- Cidade -->
				    <div class="form-group col-md-6">
				      <label>Cidade</label>
				      <input type="text" class="form-control" name="cidade" placeholder="Cidade"> <!-- required -->
				    </div>
			    </div>
			  	
			  	<div class="form-row">
				  	<!-- Logradouro -->
				    <div class="form-group col-md-6">
				      <label>Logradouro</label>
				      <input type="text" class="form-control" name="logradouro" placeholder="Logradouro (Apenas nome da rua)">
				    </div>
				  	
				  	<!-- Numero -->
				    <div class="form-group col-md-2">
				      <label>Numero</label>
				      <input type="number" class="form-control" name="numero" placeholder="Numero">
				    </div>
				  	
					<!-- Bairro -->
				    <div class="form-group col-md-4">
				      <label>Bairro</label>
				      <input type="text" class="form-control" name="bairro" placeholder="Bairro">
				    </div>
			    </div>
			  	
			  	<div class="form-row">
					<!-- Complemento -->
				    <div class="form-group col-md-6">
				      <label>Complemento</label>
				      <input type="text" class="form-control" name="complemento" placeholder="Complemento">
				    </div>
			  	
					<!-- Estado -->
					<div class="form-group col-md-2">
				    	<label>Estado</label>
			        	<select name="selecioneEstado" class="form-control" placeholder="Selecione um Estado">
					      	<option disabled selected>Selecione uma opção...</option>
					      	<option value="AC">AC</option> <!-- Acre -->
					      	<option value="AL">AL</option> <!-- Alagoas -->
					      	<option value="AP">AP</option> <!-- Amapá -->
					      	<option value="AM">AM</option> <!-- Amazonas -->
					      	<option value="BA">BA</option> <!-- Bahia -->
					      	<option value="CE">CE</option> <!-- Ceará -->
					      	<option value="DF">DF</option> <!-- Distrito Federal -->
					      	<option value="ES">ES</option> <!-- Espirito Santo -->
					      	<option value="GO">GO</option> <!-- Goiás -->
					      	<option value="MA">MA</option> <!-- Maranhão -->
					      	<option value="MT">MT</option> <!-- Mato Grosso -->
					      	<option value="MS">MS</option> <!-- Mato Grosso do Sul -->
					      	<option value="MG">MG</option> <!-- Minas Gerais -->
					      	<option value="PA">PA</option> <!-- Pará -->
					      	<option value="PB">PB</option> <!-- Paraiba -->
					      	<option value="PR">PR</option> <!-- Paraná -->
					      	<option value="PE">PE</option> <!-- Pernambuco -->
					      	<option value="PI">PI</option> <!-- Piauí -->
					      	<option value="RJ">RJ</option> <!-- Rio de Janeiro -->
					      	<option value="RN">RN</option> <!-- Rio Grande do Norte -->
					      	<option value="RS">RS</option> <!-- Rio Grande do Sul -->
					      	<option value="RO">RO</option> <!-- Rondônia -->
					      	<option value="RR">RR</option> <!-- Roraima -->
					      	<option value="SC">SC</option> <!-- Santa Catarina -->
					      	<option value="SP">SP</option> <!-- São Paulo -->
					      	<option value="SE">SE</option> <!-- Sergipe -->
					      	<option value="TO">TO</option> <!-- Tocantins -->
				    	</select>
				    </div>
				
					<!-- Botões CRUD-->
					<div class="form-group col-md-4">
						<div align="right" style="margin-top: 50px;">
							<button class="btn btn-success" name="operacao" value="SALVAR">Cadastrar</button>
							<button class="btn btn-primary" name="operacao" value="CONSULTAR">Consultar</button>
						</div>
					</div>
				</div>
				
				<!-- Botão Voltar -->
				<div align="right" style="margin-top: 10px;">
					<!--<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">-->
					<a href="/Ecommerce_Bebida/JSP/Home_Page_Back.jsp"><input type="button" value="Voltar" style="margin-left: 300px;"></a>
				</div>
			
				<br />
				
				<!-- ID do Cliente -->
				<input type="hidden" name="idCliente" id="idCliente" value="<%=cliente.get(0).getId() %>">
				<!-- Parametro que é verificado se pode alterar um Endereço ou não -->
			    <input type="hidden" name="alteraEndereco" id="alteraEndereco" value="1">
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