$('document').ready(function(){
    if (document.cookie.indexOf("userid") !== -1){
        $(location).attr('href', window.location.protocol + '//' + window.location.host + "/profile");
    }
    $("#signupbt").click(function(){
        var name = $('#name').val();
        var userName= $('#userName').val();
        var password = $('#pwd').val();
        var passwordagain = $('#pwdCheck').val();
        var email = $('#email').val();

        var registerData = {
            name: name,
            userName: userName,
            password: password,
            email: email
        };
        console.log(name, userName, password, email);
        if (password === passwordagain || name && userName && password && email) {
            $.ajax({
                url: window.location.protocol + '//' + window.location.host + '/signup/signupData',
                type: 'post',
                contentType: 'application/json',
                dataType: 'text',
                data: JSON.stringify(registerData),
                success: function (data) {
                    console.log(data);
                }
            })
        }
        else {
            $("#error").append("Hibás adatok! Ügyelj arra, hogy egyik mező se legyen üres, valamint a jelszavak megegyezenek!");
        }
    })
});