$('document').ready(function(){
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
        if (password === passwordagain) {
            $.ajax({
                url: 'http://localhost:8086/signup/signupData',
                type: 'post',
                contentType: 'application/json',
                dataType: 'text',
                data: JSON.stringify(registerData),
                success: function (data) {
                    console.log(data);
                }
            })
        }
        else console.log("szarjelszó");
    })
});