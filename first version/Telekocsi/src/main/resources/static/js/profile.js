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
    $.ajax({
        url: 'http://localhost:8086/profile/getCars?id='+Cookies.get("userid"),
        type:'get',
        success:function(datas){
            $("#vehiclelist").append("<tr>");
            $("#vehiclelist").append("<th>Márka</th>");
            $("#vehiclelist").append("<th>Típus</th>");
            $("#vehiclelist").append("<th>Évjárat</th>");
            $("#vehiclelist").append("<th>Rendszám</th>");
            $("#vehiclelist").append("<th>Férőhelyek</th>");
            $("#vehiclelist").append("<th>Szín</th>");
            $("#vehiclelist").append("</tr>");
            for(var i = 0; i <= datas.length; i++){
                var data = JSON.parse(datas[i]);
                $("#vehiclelist").append("<tr>");
                $("#vehiclelist").append("<td>"+data.manufacturer+"</td>");
                $("#vehiclelist").append("<td>"+data.type+"</td>");
                $("#vehiclelist").append("<td>"+data.year+"</td>");
                $("#vehiclelist").append("<td>"+data.plate_number+"</td>");
                $("#vehiclelist").append("<td>"+data.seats+"</td>");
                $("#vehiclelist").append("<td>"+data.color+"</td>");
                $("#vehiclelist").append("</tr>");
            }
            $("#vehiclelist").append("</tbody>");
            $("#vehiclelist").append("</table>");
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

    $( "<h2>Meghirdetett fuvarjaim</h2>" ).insertAfter( "#vehiclelist" );
    $.ajax({
        url: 'http://localhost:8086/profile/getRides?id='+Cookies.get("userid"),
        type:'get',
        success:function(datas){
            var heads = "";
            heads += "<tr>";
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
                console.log(data);
                rides += "<tr>";
                rides += "<td>"+data.car.plate_number+"</td>";
                rides += "<td>"+data.departure+"</td>";
                rides += "<td>"+data.arrival+"</td>";
                var x = new Date(data.departuretime);
                var y = new Date(data.arrivaltime);
                var departuretime = x.getFullYear()  + "-" + (x.getMonth()+1) + "-" + x.getDate()+ " " +x.getHours() + ":" + x.getMinutes();
                var arrivaltime = y.getFullYear()  + "-" + (y.getMonth()+1) + "-" + y.getDate()+ " " +y.getHours() + ":" + y.getMinutes();
                rides += "<td>"+departuretime+"</td>";
                rides += "<td>"+arrivaltime+"</td>";
                rides += "<td>"+data.price+"</td>";
                rides += "<td>"+data.car.seats+"</td>";
                rides += "<td><button id='ridedelete'>Fuvar törlése</button></td>";
                rides += "</tr>";
                $("#ridelist").append(rides);
            }
            $("#ridelist").append("</tbody></table>");
            console.log("fuvarok:"+rides);
        },
        error: function (data) {
            console.log("beszoptad",data.responseText);
        }
    });

    $('#ridelist').on('click','#ridedelete', function () {
        var currentrow = $(this).parent().parent();
        var plate = currentrow.find('td:eq(0)').text();
        var departure = currentrow.find('td:eq(3)').text();
        var arrival = currentrow.find('td:eq(4)').text();
        var deleteride = plate+";"+departure+";"+arrival;
        console.log(deleteride);
        $.ajax({
            url: 'http://localhost:8086/profile/deleteRide',
            type:'post',
            contentType: 'application/json',
            dataType:'text',
            data: deleteride,
            success:function(data){
                console.log(data);
            },
            error: function (data) {
                console.log("beszoptad",data.responseText);
            }
        })
    });

});