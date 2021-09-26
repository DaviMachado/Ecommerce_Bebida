function escondeCampos(){
	var opcao = document.getElementById("selecioneFormadePagamento").value;
	
	console.log(opcao);
	
	if(opcao == 'boleto'){
		document.getElementById("linhaCartao1").style.visibility = "hidden";
		document.getElementById("linhaCartao2").style.visibility = "hidden";
	}
	else if (opcao == "cartao"){
		document.getElementById("linhaCartao1").style.visibility = "visible";
		document.getElementById("linhaCartao2").style.visibility = "visible";
	}
	
}