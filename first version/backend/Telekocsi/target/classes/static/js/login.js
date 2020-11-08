$('document').ready(function(){
    $("#loginbt").click(function(){
        var userName= $('#userName').val();
        var password = $('#pwd').val();
        var loginData = {
            userName: userName,
            password: password
        };
        console.log(userName, password);
        $.ajax({
            url: 'http://localhost:8086/login/checkuser',
            type:'post',
            contentType: 'application/json',
            dataType:'text',
            data: JSON.stringify(loginData),
            success:function(data){
                console.log("suc", data);
            },
            error: function (data) {
                console.log("err",data.responseText);
            }
        })
    })
});