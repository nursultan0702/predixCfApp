
var dataArray = new Array();
var dateArray = new Array();
$(document).ready(function () {
    addTable();
         $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/timeseries",
                cache: false,
                timeout: 600000,
                success: function (data) {
                    var AjsonArr = data;
                   drawChart(AjsonArr);
                  // pointChart(AjsonArr);
                },
                error: function (e) {
                    console.log(e);
                }
            });
});
function addTable() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/timeseries/table",
        cache: false,
        timeout: 600000,
        success: function (data) {
            drawTable(data);
        },
        error: function (e) {
            drawTable(e.responseText);
        }
    });
}
function drawTable(jsonArr) {
    debugger;
    for (var i = 0; i < jsonArr.values.length; i++) {
        var values = jsonArr.values[i];
        var str = values.value.toString().split(",");
        if(str.length>1) {
            $("#tble").append('<tr><td>' + values.name + '</td><td>' + todate(str[0]) + '</td><td>' + str[1] + '</td><td>units</td></tr>');
            console.log(values.name);
        }
    }
}

function drawChart(jsonArr){
     for(var i=jsonArr.length-1; i>=0;i--){
                    var str = jsonArr[i].toString().split(",");
                        dateArray.push(todate(str[0]));
                        dataArray.push(str[1]);
                        var data = [
                {
                  x: dateArray,
                  y: dataArray,
                  type: 'scatter'
                }
              ];
  }
    var layout = {
        title: 'Timeseries',
        xaxis: {
            title: 'Date',
            showgrid: false,
            zeroline: false
        },
        yaxis: {
            title: 'Unit',
            showline: false
        }
    };
                Plotly.newPlot('myDivts', data,layout);
       dataArray = [];
       dateArray = [];

}
        function logout() {
            location.reload(true);
        }
        function todate(stampDate){
            var date = new Date(stampDate*1);
            var day = date.getDate();
            var month = 1 + date.getMonth();
            var year = date.getFullYear();
            var hours = date.getHours();
            var minute = date.getMinutes();
            var seconds = date.getSeconds();
            if(day<9) {day = "0"+day}
            if(month>9){
            var formattedTime = day+"."+month+"."+year+" "+hours+":"+minute+":"+seconds;
        }else{
            var formattedTime = day+".0"+month+"."+year+" "+hours+":"+minute+":"+seconds;
        }
            return formattedTime;
        }
        function updateDate() {
            if ($('#checkboxCustom1').prop('checked')) {
                $("#myDivPoint").html("");
                $("#myDivts").html("");
                livedata();
            } else {
                $("#myDivts").html("");
                $("#selectedTag").html("");
                var start = $("#startdate").val();
                var end = $("#enddate").val();
                var tag = $("#stag").val();
                var startunixtime = new Date(start).getTime();
                var endunixtime = new Date(end).getTime();
                if (start == "") {
                    start = "nodate";
                }
                if (end == "") {
                    end = "nodate";
                }
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "/timeseries?start=" + startunixtime + "&end=" + endunixtime + "&tag=" + tag,
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
                        var jsonArr = data;
                        var chartType = $("#chartType").val();
                        if (chartType == "timeseries") {
                            $("#myDivPoint").html("");
                            $("#graph").html("");
                            drawChart(jsonArr);
                        } else if (chartType == "pointchart") {
                            $("#myDivts").html("");
                            $("#graph").html("");
                            pointChart(jsonArr);
                        }
                        $("#selectedTag").append(tag);
                    },
                    error: function (e) {
                        console.log(e);
                    }
                });
            }
        }

        function pointChart(jsonArr) {
            var nameofTag = $("#stag").val();
            for(var i=jsonArr.length-1; i>=0;i--){
                var str = jsonArr[i].toString().split(",");
                dateArray.push(todate(str[0]));
                dataArray.push(str[1]);
            }
            var trace1 = {
                x: dateArray,
                y: dataArray,
                mode: 'markers',
                name: nameofTag,
                marker: {
                    color: 'rgb(164, 194, 244)',
                    size: 12
                },
                type: 'scatter'
            };

            var data = [trace1];

            var layout = {
                title: 'Point chart',
                xaxis: {
                    title: 'Date',
                    showgrid: false,
                    zeroline: false
                },
                yaxis: {
                    title: 'unit',
                    showline: false
                }
            };

            Plotly.newPlot('myDivPoint', data, layout);
            dateArray=[];
            dataArray=[];

        }

function rand() {
    return Math.random();
}
function livedata() {
    var time = new Date();

    var data = [{
        x: [time],
        y: [rand],
        mode: 'lines',
        line: {color: '#80CAF6'}
    }]

    Plotly.plot('graph', data);

    var cnt = 0;

    var interval = setInterval(function() {

        var time = new Date();

        var update = {
            x:  [[time]],
            y: [[rand()]]
        }

        var olderTime = time.setMinutes(time.getMinutes() - 1);
        var futureTime = time.setMinutes(time.getMinutes() + 1);

        var minuteView = {
            xaxis: {
                type: 'date',
                range: [olderTime,futureTime]
            }
        };

        Plotly.relayout('graph', minuteView);
        Plotly.extendTraces('graph', update, [0])

        if(cnt === 100) clearInterval(interval);
    }, 1000);


}

