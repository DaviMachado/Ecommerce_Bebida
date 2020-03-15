<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title></title>
</head>
<body>
	<table border="1">
		<tr>
            <th>Login</th>
            <th>Senha</th>
            <th>Nome</th>
            <th>CPF</th>
            <th>Dt Nascimento</th>
            <th>Cd Cliente</th>
            <th>Sexo</th>
            <th>Telefone</th>
            <th>Flg Ativo</th>
        </tr>
		<%
		ClienteDAO dao = new ClienteDAO();
		Cliente c2 = new Cliente();
		
		List<EntidadeDominio> clientes = dao.consultar(c2);
		
		for(EntidadeDominio e : clientes) {
		
		// Aplicado o CAST para poder popular o cliente
		Cliente c = (Cliente) e;
		// pega o usuario que esta dentro do cliente
		Usuario u = c.getUsuario();
		%>
			<tr>
				<td><%=u.getLogin() %></td>
				<td><%=u.getSenha() %></td>
				<td><%=c.getNome() %></td>
				<td><%=c.getCpf() %></td>
				<td><%=c.getDt_nasc() %></td>
				<td><%=c.getCdCliente() %></td>
				<td><%=c.getSexo() %></td>
				<td><%=c.getTelefone() %></td>
				<td><%=c.getFlgAtivo() %></td>
				<!-- <td><a href="preAlterar_cliente.jsp?id=<%= c.getCdCliente()%>">Alterar</a></td> -->
                <!-- <td><a href="DeletarCliente?id=<%= c.getCdCliente()%>&acao=deletar">Deletar</a></td> -->
			</tr>
		<%
		}
		%>
	</table>
	 <input type="button" value="Retornar" onclick="history.back()">
</body>
</html>