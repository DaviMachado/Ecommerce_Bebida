function TrocarTela(NomeBotao){

   // var funcao = document.getElementById("botao").value;

   //var funcao = document.getElementByName("botao").value;

   var funcao = NomeBotao;

   if (funcao == "endereco"){
   	//window.location = "formulario_Endereco.html";
   	//alert("endereço");
   	window.open("formulario_Endereco.html");
   	window.close();
   }
   else if (funcao == "cartaoCredito"){
   	window.open("formulario_CartaoCredito.html");
   	window.close();
   	//alert("cartão");
   }
}