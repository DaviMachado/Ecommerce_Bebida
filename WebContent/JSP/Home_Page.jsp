<%@page import='com.les.bebida.core.dao.*'%>
<%@page import='com.les.bebida.core.dominio.*'%>
<%@page import='com.les.bebida.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Drink Fast</title>

  <!-- Bootstrap core CSS -->
  <link href="./CSS/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="./CSS/shop-homepage.css" rel="stylesheet">

</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand">Ecommerce de Bebida</a>
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

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">

        <h1 class="my-4">Drink Fast</h1>
        <div class="list-group">
          <a href="http://localhost:8080/Ecommerce_Bebida/HTML/formulario_Cliente.html" class="list-group-item">Cliente's</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/HTML/formulario_Endereco.html" class="list-group-item">Endereço's</a>
          <a href="http://localhost:8080/Ecommerce_Bebida/HTML/CartaoDeCredito.html" class="list-group-item">Cartão de crédito's</a>
        </div>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">

        <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
          </ol>
          <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
              <img class="d-block img-fluid" src="./Imagens/carousel/DRINK_FAST.png" alt="First slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="./Imagens/carousel/tequilas.png" alt="Second slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="./Imagens/carousel/refrigerantes.png" alt="Third slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="./Imagens/carousel/cervejas.png" alt="Fourth slide">
            </div>
          </div>
          <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>

        <div class="row">

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./Imagens/itens/coca_cola.png" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Coca-Cola 350ml</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./Imagens/itens/heineken.png" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Heineken 330ml</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor sit amet.</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./Imagens/itens/vodka_sky.png" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Vodka Sky 980ml</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./Imagens/itens/budweiser.png" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Budweiser 330ml</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./Imagens/itens/guarana.png" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Guaraná 350ml</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor sit amet.</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./Imagens/itens/vinho.png" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Benjamin 750ml</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Drink Fast 2021</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="./JQUERY/jquery.min.js"></script>
  <script src="./JS/bootstrap.bundle.min.js"></script>

</body>

</html>