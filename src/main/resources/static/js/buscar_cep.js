function checkCep(cep)
{
    $.ajax(
    {
        'url': 'https://viacep.com.br/ws/'+ cep +'/json/',
        'type': "GET",
        'dataType' : 'jsonp',
        'success' : function (data){
            if(data.logradouro == undefined){
                alert(data.status + ' ' + data.message)
            }else{
                document.getElementById('rua').value = data.logradouro;
                document.getElementById('bairro').value = data.bairro;
            }
        }
        })
}