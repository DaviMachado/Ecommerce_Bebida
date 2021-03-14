<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title>Listagem dos Endere�os</title>
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
		
		// pega o id do cliente que estava pendurado na requisi��o,
		// que foi enviado pelo arquivo "EnderecoHelper"
		String idCliente = (String)request.getAttribute("idCliente");
		endereco.setIdCliente(idCliente);
		
		List<EntidadeDominio> enderecos = dao.consultar(endereco);
		
		for(EntidadeDominio e : enderecos) {
		
		// Aplicado o CAST para poder popular o endere�o,
		// fazendo o CAST para uma refer�ncia mais gen�rica, no caso para o endere�o
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
                <td><a href="/Ecommerce_Bebida/cadastroEndereco?idCliente=<%= address.getIdCliente()%>&idEndereco=<%= address.getId()%>&operacao=EXCLUIR">Deletar</a></td>
                <td><a href="/Ecommerce_Bebida/cadastroEndereco?idCliente=<%= address.getIdCliente()%>&idEndereco=<%= address.getId()%>&operacao=ALTERAR">Alterar</a></td>
			</tr>
		<%
		}
		%>
	</table>
	 <a href="/Ecommerce_Bebida/HTML/formulario_Endereco.html?id=<%= idCliente%>"><input type="button" value="Retornar"></a>
</body>
</html>