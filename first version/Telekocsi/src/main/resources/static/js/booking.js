$('document').ready(function() {
    if (document.cookie.indexOf("userid") == -1) {
        $(location).attr('href', window.location.protocol + '//' + window.location.host + "/login");
    }

    $("#send").click(function () {
        var from = $("#from").val();
        var to = $("#to").val();
        var directions = from+";"+to;
        $.ajax({
            url: 'http://localhost:8086/booking/getRides?from='+from+"&to="+to,
            type: 'get',
            dataType:'json',
            success: function (datas) {
                console.log(datas);
                $("#ridelist").append("<tr>");
                $("#ridelist").append("<th>Rendszám</th>");
                $("#ridelist").append("<th>Felhasználónév</th>");
                $("#ridelist").append("<th>Kiindulás hely</th>");
                $("#ridelist").append("<th>Érkezési hely</th>");
                $("#ridelist").append("<th>Indulási idő</th>");
                $("#ridelist").append("<th>Érkezés idő</th>");
                $("#ridelist").append("<th>Ár</th>");
                $("#ridelist").append("<th>Férőhely</th>");
                $("#ridelist").append("</tr>");
                for(var i = 0; i <= datas.length; i++){
                    var data = JSON.parse(datas[i]);
                    console.log("faszfeeeeej"+data);
                    $("#ridelist").append("<tr>");
                    $("#ridelist").append("<td>"+data.car.plate_number+"</td>");
                    $("#ridelist").append("<td>"+data.car.user.username+"</td>");
                    $("#ridelist").append("<td>"+data.departure+"</td>");
                    $("#ridelist").append("<td>"+data.arrival+"</td>");
                    var x = new Date(data.departuretime);
                    var y = new Date(data.arrivaltime);
                    var departuretime = x.getFullYear()  + "-" + (x.getMonth()+1) + "-" + x.getDate()+ " " +x.getHours() + ":" + x.getMinutes();
                    var arrivaltime = y.getFullYear()  + "-" + (y.getMonth()+1) + "-" + y.getDate()+ " " +y.getHours() + ":" + y.getMinutes();
                    $("#ridelist").append("<td>"+departuretime+"</td>");
                    $("#ridelist").append("<td>"+arrivaltime+"</td>");
                    $("#ridelist").append("<td>"+data.price+"</td>");
                    $("#ridelist").append("<td>"+data.car.seats+"</td>");
                    $("#ridelist").append('<td><button id="choice">Foglalás</button></td>');
                    $("#ridelist").append("</tr>");
                }
                $("#ridelist").append("</tbody>");
                $("#ridelist").append("</table>");
            },
            error: function (data) {
                console.log("hiba", data.responseText);
            }
        });
    })

    $('#ridelist').on('click','#choice', function () {
        var currentrow = $(this).parent().parent();
        var plate = currentrow.find('td:eq(0)').text();
        console.log(plate);
    });
});