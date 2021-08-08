<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 08/08/2021 -->

<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Cadastro de Cliente</title>
		<link href="../CSS/bootstrap.css" rel="stylesheet" type="text/css">
		<link href="../CSS/style.css" rel="stylesheet" type="text/css">
	</head>
	<%
		ClienteDAO dao = new ClienteDAO();
		Usuario usuarioLogado = new Usuario();
		
		HttpSession sessao = request.getSession();
		usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		List<Cliente> cliente = dao.consultarClienteById(usuarioLogado.getId());
	%>
	
	<body>
		<fieldset class="form-group">
		<legend align="center">Formul�rio de Cliente</legend>
			<form action="http://localhost:8080/Ecommerce_Bebida/cadastro">
			  	
				<div class="form-row">
					<!-- Nome -->
				    <div class="form-group col-md-8">
				      <label>Nome</label>
				      <input type="text" class="form-control" name="nome" placeholder="Nome" value="<%=cliente.get(0).getNome() %>"> <!-- required -->
				    </div>

				    <!-- CPF -->
				    <div class="form-group col-md-2">
				      <label>CPF</label>
				      <input type="text" class="form-control" name="cpf" placeholder="000.000.000-00" value="<%=cliente.get(0).getCpf() %>">
				    </div>

				    <!-- Data Nascimento -->
				    <div class="form-group col-md-2">
				      <label>Data Nascimento</label>
				      <input type="date" class="form-control" name="dtNasc" placeholder="Data Nascimento" value="<%=cliente.get(0).getDt_nasc() %>">
				    </div>
			  	</div>
			  	
				<div class="form-row">
					<!-- Telefone -->
				    <div class="form-group col-md-2">
				      <label>Telefone</label>
				      <input type="number" class="form-control" name="telefone" placeholder="(11)1234-5678" value="<%=cliente.get(0).getTelefone() %>">
				    </div>

				    <!-- Sexo -->
			  		<div class="form-group col-md-2">
			  		<label>Sexo</label>

			  			<select name="selecioneSexo" class="form-control" placeholder="Selecione um Sexo">
					      	<option disabled>Selecione uma op��o...</option>
					      	<option><%=cliente.get(0).getSexo() %></option>
					      	<option value="masculino">Masculino</option>
					      	<option value="feminino">Feminino</option>
				      	</select>
			  		</div>
			  		
			  		<!-- adicionado mais uma coluna com o tamanho 8, para alinhar os campos na tela -->
			  		<div class="form-group col-md-8" style="margin-top: 10px">
			  		</div>
			  	</div>
				
				<!-- Bot�es CRUD -->
				<div>
					<button class="btn btn-warning" name="operacao" value="ALTERAR">Alterar</button>
					<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">
				</div>
				
				<!-- ID do Cliente -->
			    <input type="hidden" name="id" id="id" value="<%=cliente.get(0).getId() %>">

			</form>
		</fieldset>
		
	</body>
</html>