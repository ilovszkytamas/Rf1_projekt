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
                var rides = "";
                rides +=("<tr>");
                rides +=("<th>Azonosító</th>");
                rides +=("<th>Rendszám</th>");
                rides +=("<th>Felhasználónév</th>");
                rides +=("<th>Kiindulás hely</th>");
                rides +=("<th>Érkezési hely</th>");
                rides +=("<th>Indulási idő</th>");
                rides +=("<th>Érkezés idő</th>");
                rides +=("<th>Ár</th>");
                rides +=("<th>Férőhely</th>");
                rides +=("</tr>");
                $("#ridelist").append(rides);
                for(var i = 0; i <= datas.length; i++) {
                    rides = "";
                    var data = JSON.parse(datas[i]);
                    console.log("faszfeeeeej" + data);
                    rides +=("<tr>");
                    rides +=("<td>" + data.id + "</td>");
                    rides +=("<td>" + data.car.plate_number + "</td>");
                    rides +=("<td>" + data.car.user.username + "</td>");
                    rides +=("<td>" + data.departure + "</td>");
                    rides +=("<td>" + data.arrival + "</td>");
                    var x = new Date(data.departuretime);
                    var y = new Date(data.arrivaltime);
                    var departuretime = x.getFullYear() + "-" + (x.getMonth() + 1) + "-" + x.getDate() + " " + x.getHours() + ":" + x.getMinutes();
                    var arrivaltime = y.getFullYear() + "-" + (y.getMonth() + 1) + "-" + y.getDate() + " " + y.getHours() + ":" + y.getMinutes();
                    rides +=("<td>" + departuretime + "</td>");
                    rides +=("<td>" + arrivaltime + "</td>");
                    rides +=("<td>" + data.price + "</td>");
                    rides +=("<td>" + data.car.seats + "</td>");
                    rides +=('<td><button class="choice">Foglalás</button></td>');
                    rides +=("</tr>");
                    $("#ridelist").append(rides);
                }
                $("#ridelist").append("</table>");
            },
            error: function (data) {
                console.log("hiba", data.responseText);
            }
        });
    })

    $('#ridelist').on('click','.choice', function () {
        var currentrow = $(this).parent().parent();
        var rideid = currentrow.find('td:eq(0)').text();
        $.ajax({
            url: 'http://localhost:8086/booking/bookRide?rideid='+rideid+"&userid="+Cookies.get("userid"),
            type:'get',
            contentType: 'application/json',
            dataType:'text',
            success:function(data){
                console.log(data);
            },
            error: function (data) {
                console.log("hiba",data.responseText);
            }
        })
    });
});