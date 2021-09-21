function graficoChart(){
	var opcao = document.getElementById("selecioneOpcaoGrafico").value;
	var label1 = 0;
	var label2 = 0;
	var label3 = 0;
	var valor1 = 0;
	var valor2 = 0;
	var valor3 = 0;
	
	if (opcao == "0"){
		// ajustado a acentuação para exibir na tela:
		// ç = \u00e7
		// ã = \u00e4
		alert("Selecione alguma op\u00e7\u00e4o !");
	}
	else if (opcao == "1"){
		label1 = document.getElementById("produto1").value;
		label2 = document.getElementById("produto2").value;
		label3 = document.getElementById("produto3").value;
		valor1 = document.getElementById("valorProduto1").value;
		valor2 = document.getElementById("valorProduto2").value;
		valor3 = document.getElementById("valorProduto3").value;
		
		// gera o Gráfico com os 3 maiores Produtos
		var ctx = document.getElementById('myChart').getContext('2d');
		
		var chart = new Chart(ctx, {
		
		    type: 'bar',
		    data: {
		        labels: [label1, label2, label3],
		        
		        
		        datasets: [{
		            label: 'Grafico',
		            backgroundColor: ['green', 'blue', 'yellow'],
		            borderColor: 'rgb(255, 99, 132)',
		            data: [valor1, valor2, valor3]
		        }]
		    },
		
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero: true
		                }
		            }]
		        }
		    }
		});
	}
	else if (opcao == "2"){
		label1 = document.getElementById("cliente1").value;
		label2 = document.getElementById("cliente2").value;
		label3 = document.getElementById("cliente3").value;
		valor1 = document.getElementById("valorCliente1").value;
		valor2 = document.getElementById("valorCliente2").value;
		valor3 = document.getElementById("valorCliente3").value;
		
		// gera o Gráfico com os 3 clientes com maiores compras
		var ctx = document.getElementById('myChart').getContext('2d');
		
		var chart = new Chart(ctx, {
		
		    type: 'bar',
		    data: {
		        labels: [label1, label2, label3],
		        
		        
		        datasets: [{
		            label: 'Grafico',
		            backgroundColor: ['green', 'blue', 'yellow'],
		            borderColor: 'rgb(255, 99, 132)',
		            data: [valor1, valor2, valor3]
		        }]
		    },
		
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero: true
		                }
		            }]
		        }
		    }
		});
	}
}