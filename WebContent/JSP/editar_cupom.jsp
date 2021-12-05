<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 05/12/2021 -->

<%@page import='com.les.bebida.core.dominio.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Edição de Cupom</title>
		
		<!-- importações para funcionar o Header e o Footer -->
		<link href="./CSS/bootstrap.min.css" rel="stylesheet">
  		<link href="./CSS/shop-homepage.css" rel="stylesheet">
  		<link href="./CSS/form-default.css" rel="stylesheet" type="text/css">
	</head>
	
	<%
		// cria um objeto "sessao" para poder usar o JSESSAOID criado pelo TomCat
		HttpSession sessao = request.getSession();
	
		// pega o cupom que estava pendurado na requisição,
		// que foi enviado pelo arquivo "EnderecoHelper"
		Cupom cupom = (Cupom)request.getAttribute("cupomPesquisado");
		// pega todos os clientes do sistema salvo na sessão
		List<Cliente> todosClientesSistema = (List<Cliente>)sessao.getAttribute("todasClientesSistemas");
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
	  
		<fieldset class="form-group fieldset_form" style="margin-top: 30px; margin-bottom: 74px !important;">
		<legend align="center">Formulário para edição de Cupom</legend>
			<form class="form_form" action="http://localhost:8080/Ecommerce_Bebida/cupom">
			  	
				<div class="form-row">
					<!-- Nome -->
				    <div class="form-group col-md-8">
				      <label>Nome</label>
				      <input type="text" class="form-control" name="nome" placeholder="Nome" value="<%=cupom.getNome()%>" required>
				    </div>

				    <!-- Valor -->
				    <div class="form-group col-md-2">
				      <label>Valor</label>
				      <input type="text" class="form-control" name="valor" placeholder="Digite um Valor" value="<%=cupom.getValor() %>" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="4" required>
				    </div>
				    
				    <!-- Utilizado -->
			  		<div class="form-group col-md-2">
			  		<label>Utilizado</label>

			  			<select name="utilizado" class="form-control" placeholder="Selecione uma opção" required>
					      	<option disabled>Selecione uma opção...</option>
					      	<option value="<%=cupom.getUtilizado()%>"><%=cupom.getUtilizado()%></option>
					      	<option value="sim">Sim</option>
					      	<option value="nao">Não</option>
				      	</select>
			  		</div>
				</div>
				
				<div class="form-row">
				    <!-- Cliente -->
			  		<div class="form-group col-md-6">
			  		<label>Cliente</label>

			  			<select name="idCliente" class="form-control" placeholder="Selecione um Cliente">
					      	<option disabled>Selecione uma opção...</option>
					      	<option value="<%=cupom.getIdCliente()%>"><%=cupom.getNomeClienteNoCupom()%></option>
					      	<option value="null">NULL</option>
					      	<% 
						      	for(Cliente client : todosClientesSistema) {
				
								// Aplicado o CAST para poder popular o cliente,
								// fazendo o CAST para uma referência mais genérica, no caso para o cliente,
								// lista todos os clientes indexado com o ID do cliente dentro do "value", de cada da TAG "<option>".
								//Cliente client = (Cliente) e;
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
					      	<option disabled>Selecione uma opção...</option>
					      	<option value="<%=cupom.getTipo()%>"><%=cupom.getTipo()%></option>
					      	<option value="promocional">Promocional</option>
					      	<option value="troca">Troca</option>
					      	<option value="devolucao">Devolução</option>
					      	<option value="bonificacao">Bonificação</option>
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
							<button class="btn btn-warning" name="operacao" value="ALTERAR">Alterar</button>
						</div>
			  		</div>
			  	</div>
			  	
			  	<!-- Botão Voltar -->
				<div align="right">
					<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">
				</div>
				
				<!-- ID do Cupom -->
			    <input type="hidden" name="idCupom" id="idCupom" value="<%=cupom.getId() %>">
				<!-- Parametro que é verificado se pode alterar um Cupom ou não -->
	    		<input type="hidden" name="alteraCupom" id="alteraCupom" value="1">
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