<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Edição de Endereço</title>
		<link href="./CSS/bootstrap.css" rel="stylesheet" type="text/css">
		<link href="./CSS/style.css" rel="stylesheet" type="text/css">
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
		<fieldset class="form-group">
		<legend align="center">Formulário de Alteração do Endereço</legend>
			<form action="http://localhost:8080/Ecommerce_Bebida/cadastroEndereco">
				
				<!-- CEP -->
			    <div class="form-group col-md-6">
			      <label>CEP</label>
			      <input type="number" class="form-control" name="cep" placeholder="CEP" value="<%=endereco.get(0).getCep() %>"> <!-- required -->
			    </div>
			  	
			  	<!-- Cidade -->
			    <div class="form-group col-md-6">
			      <label>Cidade</label>
			      <input type="text" class="form-control" name="cidade" placeholder="Cidade" value="<%=endereco.get(0).getCidade() %>"> <!-- required -->
			    </div>
			  	
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
			  	
				<!-- Complemento -->
			    <div class="form-group col-md-6">
			      <label>Complemento</label>
			      <input type="text" class="form-control" name="complemento" placeholder="Complemento" value="<%=endereco.get(0).getComplemento() %>">
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
				
				<!-- Botões -->
				<div align="right" style="margin-top: 170px;">
					<button class="btn btn-warning" name="operacao" value="ALTERAR">Alterar</button>
				</div>
				
				<!-- ID do Cliente -->
			    <input type="hidden" name="idCliente" id="idCliente" value="<%=idCliente %>">
			    <!-- ID do Endereco -->
			    <input type="hidden" name="idEndereco" id="idEndereco" value="<%=idEndereco %>">
			</form>
			<div align="right" style="margin-top: 10px;">
				<button class="btn btn-secondary" onclick="history.back()">Retornar</button>
			</div>
		</fieldset>
	</body>
</html>