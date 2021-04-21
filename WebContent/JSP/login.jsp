<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
	<head>
    	<meta charset="UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Login</title>			
        <link rel="stylesheet" href="./CSS/AdminLTE.min.css">
        <link rel="stylesheet" href="./CSS/_all-skins.min.css">
        <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
        <script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
    </head>
    
	<%		
		// pega a mensagem que estava pendurado na requisição,
		// que foi enviado pelo arquivo "LoginHelper"
		String mensagemStrategy = (String)request.getAttribute("mensagemStrategy");
	%>
	
	<!-- Verifica se tem alguma mensagem do BackEnd, para poder ativar a Modal -->
	<%
		if(mensagemStrategy != null || !mensagemStrategy.equals("")) {
	%>
		<body onload="AtivaModal()" class="hold-transition skin-blue sidebar-mini" style="background-color: lightgray">
	<%
		}
	%>
	
	<!-- Se não tiver nenhuma mensagem do BackEnd, mostra o body sem ativar a Modal -->
	<%
		if(mensagemStrategy == null || mensagemStrategy.equals("")) {
	%>
		<body class="hold-transition skin-blue sidebar-mini" style="background-color: lightgray">
	<%
		}
	%>
	
        <div id="body" class="hold-transition skin-red sidebar-mini">
            <header class="main-header">
                <a href="index.html" class="logo"> <span class="logo-lg">Drink Fast</span> </a>
                <nav class="navbar navbar-static-top" role="navigation">
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <li class="dropdown user user-menu">
                                <a href="/marmitex">
                                    <span class="hidden-xs">Inicio</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <div class="content">
                <div class="box-body">
					
					<!-- SOU CADASTRADO -->
					<form action="http://localhost:8080/Ecommerce_Bebida/login">
	                    <div class="col-md-6">
	                        <div class="box box-default">
	                            <div class="box-body">
	                                <div class="box-header with-border">
	                                    <i class="glyphicon glyphicon-user"></i>
	                                    <h3 class="box-title">Sou cadastrado</h3>
	                                </div><!-- /.box-header -->
	                                <br>
	                                <div class="row">
	                                    <div class="form-group col-md-11">
	                                        <label for="txtEmail">E-mail</label>
	                                        <input type="email" class="form-control" name="email" placeholder="E-mail">
	                                    </div>
	                                </div>
	                                <div class="row">
	                                    <div class="form-group col-md-11">
	                                        <label for="txtSenha">Senha</label>
	                                        <input type="password" class="form-control" name="senha" placeholder="Senha">
	                                    </div>
	                                </div>
	                                <div class="row">
	                                    <div class="form-group col-md-7">
	                                        <button type="submit" class="btn btn-google pull-right" name="operacao" value="CONSULTAR">Entrar</button>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
                    </form>
					
					<!-- NÃO SOU CADASTRADO -->
					<form action="http://localhost:8080/Ecommerce_Bebida/login">
	                    <div class="col-md-6">
	                        <div class="box box-default">
	                            <div class="box-body">
	                                <div class="box-header with-border">
	                                    <i class="glyphicon glyphicon-plus-sign"></i>
	                                    <h3 class="box-title">Não sou cadastrado</h3>
	                                </div>
	                                <br>
	                                <!-- PRIMEIRA LINHA -->
	                                <div class="row">
	                                    <div class="form-group col-md-6">
	                                        <label for="exampleInput">Nome</label>
	                                        <input type="text" class="form-control" name="nome" placeholder="Nome">
	                                    </div>
	                                    <div class="form-group col-md-6">
	                                        <label for="exampleInput">Telefone</label>
	                                        <input type="tel" class="form-control" name="telefone" placeholder="Telefone">
	                                    </div>
	                                </div>
	
	                                <br>
	                                <div class="box-header with-border">
	                                    <i class="glyphicon glyphicon-log-in"></i>
	                                    <h3 class="box-title">Dados para login</h3>
	                                </div>
	                                <!-- QUARTA LINHA -->
	                                <div class="row">
	                                    <div class="form-group col-md-10">
	                                        <label for="exampleInput">E-mail</label>
	                                        <input type="email" class="form-control" name="email" placeholder="E-mail">
	                                    </div>
	                                </div>
	                                <div class="row">
	                                    <div class="form-group col-md-10">
	                                        <label for="exampleInput">Senha</label>
	                                        <input type="password" class="form-control col-md-10" name="senha" placeholder="Senha">
	                                    </div>
	                                </div>
	                                <div class="row">
	                                    <div class="form-group col-md-10">
	                                        <label for="exampleInput">Confirmar senha</label>
	                                        <input type="password" class="form-control col-md-10" name="confirmarSenha" placeholder="Confirmar senha">
	                                    </div>
	                                </div>
	                                <!-- QUINTA LINHA -->
	                                <div class="row">
	                                    <div class="form-group col-md-7">
	                                        <button type="submit" class="btn btn-success pull-right" name="operacao" value="SALVAR">Cadastrar</button>
	                                    </div>
	                                </div>
	                            </div>
	                        </div><!-- /.box-body -->
	                    </div>
                    </form>

                </div><!-- /.box-body -->
            </div><!-- /.content-wrapper -->
        </div>
        
    <!-- Modal -->
	<div class="modal fade" id="modal-mensagem">
	   <div class="modal-dialog">
	   		<div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
	                <h4 class="modal-title">Mensagem</h4>
	            </div>
	            <div class="modal-body">
	                <p><% out.println(mensagemStrategy); %></p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- Botão para chamar a Modal -->
	<button style="display: none" id="idModal" class="btn btn-primary" data-toggle="modal" data-target="#modal-mensagem">
		Exibir mensagem da modal
	</button>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
    // Função que irá ativar a Modal com a mensagem retornada do BackEnd,
    // essa função é carregada junto ao carregamento da página com o evento ONLOAD, dentro da tag <body>.
	    function AtivaModal(){
    		// metodo para poder ativar o "onClick" sem precisar clicar no botão
	    	document.getElementById('idModal').click();
	    }
    </script>
    <!-- Fim Modal -->
    
    </body>
</html>