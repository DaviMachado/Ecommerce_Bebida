<!DOCTYPE html>
<!-- @author Davi Rodrigues-->
<!-- @date 09/08/2021 -->

<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Cadastro de Endereço</title>
		<link href="../CSS/bootstrap.css" rel="stylesheet" type="text/css">
		<link href="../CSS/style.css" rel="stylesheet" type="text/css">
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
		<fieldset class="form-group">
		<legend align="center">Formulário de Endereço</legend>
			<form action="http://localhost:8080/Ecommerce_Bebida/cadastroEndereco">
				
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
				
				<!-- Botões -->
				<div align="right" style="margin-top: 170px;">
					<button class="btn btn-success" name="operacao" value="SALVAR">Cadastrar</button>
					<button class="btn btn-primary" name="operacao" value="CONSULTAR">Consultar</button>
					<input style="margin-left: 600px" type="button" value="Voltar" onclick="history.back()">
				</div>
			
			<br />
				
				<!-- ID do Cliente -->
				<input type="hidden" name="idCliente" id="idCliente" value="<%=cliente.get(0).getId() %>">
			</form>
		</fieldset>
	</body>
</html>