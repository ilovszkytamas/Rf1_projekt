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
        if (userName && password) {
            $.ajax({
                url: window.location.protocol + '//' + window.location.host +'/login/checkuser',
                type: 'post',
                contentType: 'application/json',
                dataType: 'text',
                data: JSON.stringify(loginData),
                success: function (data) {
                    console.log("suc", data);
                    Cookies.set("userid", data);
                    $(location).attr('href', window.location.protocol + '//' + window.location.host + "/profile");
                },
                error: function (data) {
                    console.log("err", data.responseText);
                    $("#error").append("Hibás a felhasználónév vagy a jelszó!");
                }
            })
        }
        else{
            alert("Hibás adatok!");
        }
    })
});