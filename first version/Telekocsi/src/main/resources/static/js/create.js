$('document').ready(function(){
    if (document.cookie.indexOf("userid") == -1){
        $(location).attr('href', window.location.protocol + '//' + window.location.host + "/login");
    }

    $.ajax({
        url: 'http://localhost:8086/profile/getCars?id='+Cookies.get("userid"),
        type:'get',
        success:function(datas){
            for(var i = 0; i <= datas.length; i++){
                var data = JSON.parse(datas[i]);
                $("#cars").append("<option>" + data.plate_number + "</option>");
            }
            $("#cars").append("</select>");
        },
        error: function (data) {
            console.log("beszoptad",data.responseText);
        }
    });

});