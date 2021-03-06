$('document').ready(function(){
    if (document.cookie.indexOf("userid") == -1){
        $(location).attr('href', window.location.protocol + '//' + window.location.host + "/login");
    }

    $("#logout").click(function () {
        Cookies.remove("userid");
        location.reload();
    });

    $.ajax({
        url: window.location.protocol + '//' + window.location.host + '/profile/getUserData?id='+Cookies.get("userid"),
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
    $.ajax({
        url: window.location.protocol + '//' + window.location.host +'/profile/getCars?id='+Cookies.get("userid"),
        type:'get',
        success:function(datas){
            var vehiclelist = "";
            vehiclelist +="<tr>";
            vehiclelist +="<th>Márka</th>";
            vehiclelist +="<th>Típus</th>";
            vehiclelist +="<th>Évjárat</th>";
            vehiclelist +="<th>Rendszám</th>";
            vehiclelist +="<th>Férőhelyek</th>";
            vehiclelist +="<th>Szín</th>";
            vehiclelist +="</tr>";
            $("#vehiclelist").append(vehiclelist);
            for(var i = 0; i <= datas.length; i++){
                var data = JSON.parse(datas[i]);
                vehiclelist = "";
                vehiclelist +="<tr>";
                vehiclelist +="<td>"+data.manufacturer+"</td>";
                vehiclelist +="<td>"+data.type+"</td>";
                vehiclelist +="<td>"+data.year+"</td>";
                vehiclelist +="<td>"+data.plate_number+"</td>";
                vehiclelist +="<td>"+data.seats+"</td>";
                vehiclelist +="<td>"+data.color+"</td>";
                vehiclelist +="<td><button class='cardelete'>Autó törlése</button></td>";
                vehiclelist +="</tr>";
                $("#vehiclelist").append(vehiclelist);
                console.log("html:"+ vehiclelist);
            }
            vehiclelist +="</table>";
            console.log("html:"+ vehiclelist);
            $("#vehiclelist").append(vehiclelist);
        },
        error: function (data) {
            console.log("hiba",data.responseText);
        }
    });

    $('#vehiclelist').on('click','.cardelete', function () {
        var currentrow = $(this).parent().parent();
        var plate = currentrow.find('td:eq(3)').text();
        $.ajax({
            url: window.location.protocol + '//' + window.location.host +'/profile/deleteCar?plate='+plate,
            type:'get',
            contentType: 'application/json',
            dataType:'text',
            success:function(data){
                console.log(data);
                location.reload();
            },
            error: function (data) {
                console.log("hiba",data.responseText);
            }
        })
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
            url: window.location.protocol + '//' + window.location.host +'/profile/addCar',
            type:'post',
            contentType: 'application/json',
            dataType:'text',
            data: JSON.stringify(carData),
            success:function(data){
               console.log(data);
               location.reload();
            },
            error: function (data) {
                console.log("hiba",data.responseText);
            }
        })
    })

    $( "<h2>Meghirdetett fuvarjaim</h2>" ).insertAfter( "#vehiclelist" );
    $.ajax({
        url: window.location.protocol + '//' + window.location.host + '/profile/getRides?id='+Cookies.get("userid"),
        type:'get',
        success:function(datas){
            var heads = "";
            heads += "<tr>";
            heads += "<th>Fuvar Azonosító</th>"
            heads += "<th>Rendszám</th>"
            heads += "<th>Kiindulás hely</th>";
            heads += "<th>Érkezési hely</th>";
            heads += "<th>Indulási idő</th>";
            heads += "<th>Érkezés idő</th>";
            heads += "<th>Ár</th>"
            heads += "<th>Férőhely</th>"
            heads += "</tr>"
            $("#ridelist").append(heads);
            for(var i = 0; i <= datas.length; i++){
                var rides = "";
                var data = JSON.parse(datas[i]);
                rides += "<tr>";
                rides += "<td>"+data.id+"</td>";
                rides += "<td>"+data.car.plate_number+"</td>";
                rides += "<td>"+data.departure+"</td>";
                rides += "<td>"+data.arrival+"</td>";
                var x = new Date(data.departuretime);
                var y = new Date(data.arrivaltime);
                var departuretime = x.getFullYear()  + "-" + (x.getMonth()+1) + "-" + x.getDate()+ " " +x.getHours() + ":" + x.getMinutes();
                var arrivaltime = y.getFullYear()  + "-" + (y.getMonth()+1) + "-" + y.getDate()+ " " +y.getHours() + ":" + y.getMinutes();
                rides += "<td>"+departuretime+"</td>";
                rides += "<td>"+arrivaltime+"</td>";
                rides += "<td>"+data.price+" ft</td>";
                rides += "<td>"+data.car.seats+"</td>";
                rides += "<td><button class='ridedelete'>Fuvar törlése</button></td>";
                rides += "</tr>";
                $("#ridelist").append(rides);
            }
            $("#ridelist").append("</tbody></table>");
            console.log("fuvarok:"+rides);
        },
        error: function (data) {
            console.log("hiba",data.responseText);
        }
    });

    $('#ridelist').on('click','.ridedelete', function () {
        var currentrow = $(this).parent().parent();
        var plate = currentrow.find('td:eq(1)').text();
        var departure = currentrow.find('td:eq(4)').text();
        var arrival = currentrow.find('td:eq(5)').text();
        var deleteride = plate+";"+departure+";"+arrival;
        console.log(deleteride);
        $.ajax({
            url: window.location.protocol + '//' + window.location.host +'/profile/deleteRide',
            type:'post',
            contentType: 'application/json',
            dataType:'text',
            data: deleteride,
            success:function(data){
                console.log(data);
                location.reload();
            },
            error: function (data) {
                console.log("hiba",data.responseText);
            }
        })
    });

    $( "<h2>Fuvarok amikre jelentkeztem</h2>" ).insertAfter( "#ridelist" );
    $.ajax({
        url: window.location.protocol + '//' + window.location.host + '/profile/getReservations?id='+Cookies.get("userid"),
        type:'get',
        success:function(datas){
            console.log(datas);

            var heads = "";
            heads += "<tr>";
            heads += "<th>Fuvar Azonosító</th>"
            heads += "<th>Rendszám</th>"
            heads += "<th>Hirdető neve</th>"
            heads += "<th>Kiindulás hely</th>";
            heads += "<th>Érkezési hely</th>";
            heads += "<th>Indulási idő</th>";
            heads += "<th>Érkezés idő</th>";
            heads += "<th>Ár</th>"
            heads += "</tr>"
            $("#reserved").append(heads);
            for(var i = 0; i <= datas.length; i++){
                var rides = "";
                var data = JSON.parse(datas[i]);
                console.log(data);
                rides += "<tr>";
                rides += "<td>"+data.id+"</td>";
                rides += "<td>"+data.ride.car.plate_number+"</td>";
                rides += "<td>"+data.ride.car.user.realname+"</td>";
                rides += "<td>"+data.ride.departure+"</td>";
                rides += "<td>"+data.ride.arrival+"</td>";
                var x = new Date(data.ride.departuretime);
                var y = new Date(data.ride.arrivaltime);
                var departuretime = x.getFullYear()  + "-" + (x.getMonth()+1) + "-" + x.getDate()+ " " +x.getHours() + ":" + x.getMinutes();
                var arrivaltime = y.getFullYear()  + "-" + (y.getMonth()+1) + "-" + y.getDate()+ " " +y.getHours() + ":" + y.getMinutes();
                rides += "<td>"+departuretime+"</td>";
                rides += "<td>"+arrivaltime+"</td>";
                rides += "<td>"+data.ride.price+" ft</td>";
                rides += "<td><button class='reservationdelete'>Jelentkezés törlése</button></td>";
                rides += "</tr>";
                $("#reserved").append(rides);
            }
            $("#reserved").append("</tbody></table>");
            console.log("fuvarok:"+rides);
        },
        error: function (data) {
            console.log("hiba",data.responseText);
        }
    });

    $('#reserved').on('click','.reservationdelete', function () {
        var currentrow = $(this).parent().parent();
        var id = currentrow.find('td:eq(0)').text();
        $.ajax({
            url: window.location.protocol + '//' + window.location.host +'/profile/deleteReservation?id='+id,
            type:'get',
            contentType: 'application/json',
            dataType:'text',
            success:function(data){
                console.log(data);
                location.reload();
            },
            error: function (data) {
                console.log("hiba",data.responseText);
            }
        })
    });

});