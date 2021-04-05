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
				//document.getElementById('CEP').value = data.cep; 04.542-000 - 01.345-789
				document.getElementById('CEP').value = data.cep.substr(0, 2) + data.cep.substr(3, 3) + data.cep.substr(7, 3);
				document.getElementById('rua').value = data.logradouro;
				document.getElementById('numero').value = data.numero;
				document.getElementById('bairro').value = data.bairro;
				document.getElementById('complemento').value = data.complemento;
			}
		}
	})
}