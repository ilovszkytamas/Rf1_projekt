$('document').ready(function(){
    if (document.cookie.indexOf("userid") != -1){
        console.log("már bejelentkeztél");
        $(location).attr('href', window.location.protocol + '//' + window.location.host + "/profile");
    }
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
                Cookies.set("userid", data);
                $(location).attr('href', window.location.protocol + '//' + window.location.host + "/profile");
            },
            error: function (data) {
                console.log("err",data.responseText);
            }
        })
    })
});