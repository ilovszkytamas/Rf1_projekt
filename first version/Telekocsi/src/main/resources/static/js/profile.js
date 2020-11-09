$('document').ready(function(){
    if (document.cookie.indexOf("userid") == -1){
        $(location).attr('href', window.location.protocol + '//' + window.location.host + "/login");
    }
    $.ajax({
        url: 'http://localhost:8086/profile/getUserData?id='+Cookies.get("userid"),
        type:'get',
        success:function(data){
            var userdata = data.split(";");
            $("#realname").append(userdata[0]);
            $("#username").append(userdata[1]);
        },
        error: function (data) {
            console.log("beszoptad",data.responseText);
        }
    });


    $("#addCar").click(function(){
        var manuf = $('#manufacturer').val();
        var type= $('#type').val();
        var plate = $('#plate').val();
        var seats = $('#seats').val();
        var color = $('#color').val();
        var year = $('#year').val();

        var carData = {
            plate_number: plate,
            manufacturer: manuf,
            type: type,
            seats: seats,
            color: color,
            year: year,
            userid: Cookies.get("userid")
        };


        console.log(JSON.stringify(carData));
        $.ajax({
            url: 'http://localhost:8086/profile/addCar',
            type:'post',
            contentType: 'application/json',
            dataType:'text',
            data: JSON.stringify(carData),
            success:function(data){

               console.log(data);
            },
            error: function (data) {
                console.log("beszoptad",data.responseText);
            }
        })
    })
});