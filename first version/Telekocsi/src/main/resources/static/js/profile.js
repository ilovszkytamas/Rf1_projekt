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
            $("#ridelist").append("<tr>");
            $("#ridelist").append("<th>Rendszám</th>");
            $("#ridelist").append("<th>Kiindulás hely</th>");
            $("#ridelist").append("<th>Érkezési hely</th>");
            $("#ridelist").append("<th>Indulási idő</th>");
            $("#ridelist").append("<th>Érkezés idő</th>");
            $("#ridelist").append("<th>Ár</th>");
            $("#ridelist").append("<th>Férőhely</th>");
            $("#ridelist").append("</tr>");
            for(var i = 0; i <= datas.length; i++){
                var data = JSON.parse(datas[i]);
                $("#ridelist").append("<tr>");
                $("#ridelist").append("<td>"+data.car.plate_number+"</td>");
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
                $("#ridelist").append("</tr>");
            }
            $("#ridelist").append("</tbody>");
            $("#ridelist").append("</table>");

        },
        error: function (data) {
            console.log("beszoptad",data.responseText);
        }
    });

});