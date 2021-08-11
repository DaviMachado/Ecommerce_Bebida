<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title>Listagem dos Clientes</title>
	<link href="./CSS/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table border="1">
		<tr>
            <!--<th>Login</th>-->
            <!--<th>Senha</th>-->
            <th>Nome</th>
            <th>CPF</th>
            <th>Dt Nascimento</th>
            <th>Sexo</th>
            <th>Telefone</th>
            <th>Cd Cliente</th>
            <th>Flg Ativo</th>
        </tr>
		<%
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = new Cliente();
		
		List<EntidadeDominio> clientes = dao.consultar(cliente);
		
		for(EntidadeDominio e : clientes) {
		
		// Aplicado o CAST para poder popular o cliente,
		// fazendo o CAST para uma referência mais genérica, no caso para o cliente
		Cliente c = (Cliente) e;
		// pega o usuario que esta dentro do cliente
		Usuario u = c.getUsuario();
		%>
			<tr>
				<!--<td><%=u.getLogin() %></td>-->
				<!--<td><%=u.getSenha() %></td>-->
				<td><%=c.getNome() %></td>
				<td><%=c.getCpf() %></td>
				<td><%=c.getDt_nasc() %></td>
				<td><%=c.getSexo() %></td>
				<td><%=c.getTelefone() %></td>
				<td><%=c.getCdCliente() %></td>
				<td><%=c.getFlgAtivo() %></td>
				<td><a href="/Ecommerce_Bebida/HTML/formulario_Endereco.html?id=<%= c.getId()%>"><button class="btn btn-secondary">Endereço</button></a></td>
                <td><a href="/Ecommerce_Bebida/cadastro?id=<%= c.getId()%>&operacao=EXCLUIR"><button class="btn btn-danger">Deletar</button></a></td>
			</tr>
		<%
		}
		%>
	</table>
	 <a href="/Ecommerce_Bebida/HTML/formulario_Cliente.html"><input type="button" value="Retornar" style="margin-top: 10px;"></a>
</body>
</html>