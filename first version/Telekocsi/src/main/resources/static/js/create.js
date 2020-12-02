$('document').ready(function() {
    if (document.cookie.indexOf("userid") == -1) {
        $(location).attr('href', window.location.protocol + '//' + window.location.host + "/login");
    }

    $.ajax({
        url: window.location.protocol + '//' + window.location.host +'/profile/getCars?id=' + Cookies.get("userid"),
        type: 'get',
        success: function (datas) {
            for (var i = 0; i <= datas.length; i++) {
                var data = JSON.parse(datas[i]);
                $("#cars").append("<option>" + data.plate_number + "</option>");
            }
            $("#cars").append("</select>");
        },
        error: function (data) {
            console.log("hiba", data.responseText);
        }
    });

    $("#sendRide").click(function () {
        var arrival = $('#arrival').val();
        var departure = $('#departure').val();
        var price = $('#price').val();
        var departuretime = $('#departuretime').val();
        var arrivaltime = $('#arrivaltime').val();

        var selectedcar = $("#cars option:selected").text();

        var rideData = departure +";"+arrival+";"+departuretime+";"+arrivaltime+";"+price+";"+selectedcar;
        console.log(rideData);

        $.ajax({
            url: window.location.protocol + '//' + window.location.host +'/create/addRide',
            type: 'post',
            contentType: 'application/json',
            dataType: 'text',
            data: rideData,
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.log("hiba", data.responseText);
            }
        });
    });
});