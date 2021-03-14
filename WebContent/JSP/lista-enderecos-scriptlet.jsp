<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title>Listagem dos Endereços</title>
	<link href="./CSS/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table border="1">
		<tr>
            <th>CEP</th>
            <th>Logradouro</th>
            <th>Numero</th>
            <th>Bairro</th>
            <th>Complemento</th>
            <th>Cidade</th>
            <th>Estado</th>
        </tr>
		<%
		EnderecoDAO dao = new EnderecoDAO();
		Endereco endereco = new Endereco();
		
		// pega o id do cliente que estava pendurado na requisição,
		// que foi enviado pelo arquivo "EnderecoHelper"
		String idCliente = (String)request.getAttribute("idCliente");
		endereco.setIdCliente(idCliente);
		
		List<EntidadeDominio> enderecos = dao.consultar(endereco);
		
		for(EntidadeDominio e : enderecos) {
		
		// Aplicado o CAST para poder popular o endereço,
		// fazendo o CAST para uma referência mais genérica, no caso para o endereço
		Endereco address = (Endereco) e;
		%>
			<tr>
				<td><%=address.getCep() %></td>
				<td><%=address.getLogradouro() %></td>
				<td><%=address.getNumero() %></td>
				<td><%=address.getBairro() %></td>
				<td><%=address.getComplemento() %></td>
				<td><%=address.getCidade() %></td>
				<td><%=address.getEstado() %></td>
				<td><a href="/Ecommerce_Bebida/cadastroEndereco?idCliente=<%= address.getIdCliente()%>&idEndereco=<%= address.getId()%>&operacao=ALTERAR"><button class="btn btn-warning">Alterar</button></a></td>
                <td><a href="/Ecommerce_Bebida/cadastroEndereco?idCliente=<%= address.getIdCliente()%>&idEndereco=<%= address.getId()%>&operacao=EXCLUIR"><button class="btn btn-danger">Deletar</button></a></td>
			</tr>
		<%
		}
		%>
	</table>
	 <a href="/Ecommerce_Bebida/HTML/formulario_Endereco.html?id=<%= idCliente%>"><input type="button" value="Retornar" style="margin-top: 10px;"></a>
</body>
</html>