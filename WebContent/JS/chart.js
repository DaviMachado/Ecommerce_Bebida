function graficoChart(){
	label1 = document.getElementById("produto1").value;
	label2 = document.getElementById("produto2").value;
	label3 = document.getElementById("produto3").value;
	valor1 = document.getElementById("valorProduto1").value;
	valor2 = document.getElementById("valorProduto2").value;
	valor3 = document.getElementById("valorProduto3").value;
	
	// gera o Gráfico com os 3 maiores Produtos
	var ctx = document.getElementById('myChart').getContext('2d');
	
	var chart = new Chart(ctx, {
	
	    type: 'line',
	    data: {
	        labels: [label1, label2, label3],
	        
	        
	        datasets: [
	        	{
		            label: label1,
		            backgroundColor: ['green'],
		            borderColor: 'rgb(0,128,0)',
		            data: [valor1, 0, 0]
	        	},
	        	{
		            label: label2,
		            backgroundColor: ['blue'],
		            borderColor: 'rgb(0,0,255)',
		            data: [0, valor2, 0]
	        	},
	        	{
		            label: label3,
		            backgroundColor: ['yellow'],
		            borderColor: 'rgb(255,255,0)',
		            data: [0, 0, valor3]
	        	}
	        ]
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