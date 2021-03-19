function checkCnpj(cnpj)
{
	$.ajax(
	{
		'url': 'https://www.receitaws.com.br/v1/cnpj/' + cnpj.replace(/\D/g, ''),
		'type': "GET",
		'dataType': 'jsonp',
		'success': function(data){
			if(data.nome == undefined){
				alert(data.status + ' ' + data.message)
			}else{
				document.getElementById('razaoSocial').value = data.nome;
				document.getElementById('nomeFantasia').value = data.fantasia;
				document.getElementById('email').value = data.email;
			}
		}
	})
}