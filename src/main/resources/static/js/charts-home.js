/*global $, document, Chart, LINECHART, data, options, window*/
var dataArray = new Array();
var dateArray = new Array();
$(document).ready(function () {
     $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/timeseries",
                cache: false,
                timeout: 600000,
                success: function (data) {
                    debugger;
                    var AjsonArr = data;
                   drawChart(AjsonArr);
                },
                error: function (e) {
                    console.log(e);
                }
            });
});
function drawChart(jsonArr){
     for(var i=jsonArr.length-1; i>=0;i--){
                    var str = jsonArr[i].toString().split(",");
                    $("#tble").append("<tr><td>"+todate(str[0])+"</td>\n\
                                        <td>"+str[1]+"</td>\n\
                                        <td>"+str[2]+"</td></tr>");
                        dateArray.push(todate(str[0]));
                        dataArray.push(str[1]);

              
    'use strict';

    // Main Template Color
    var brandPrimary = '#33b35a';


    // ------------------------------------------------------- //
    // Line Chart
    // ------------------------------------------------------ //
    var LINECHART = $('#lineCahrt');
    var myLineChart = new Chart(LINECHART, {
        type: 'line',
        options: {
            legend: {
                display: false
            }
        },
        data: {
            labels: dateArray.reverse(),
            datasets: [
                {
                    label: "My First dataset",
                    fill: true,
                    lineTension: 0.3,
                    backgroundColor: "rgba(77, 193, 75, 0.4)",
                    borderColor: brandPrimary,
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    borderWidth: 1,
                    pointBorderColor: brandPrimary,
                    pointBackgroundColor: "#fff",
                    pointBorderWidth: 1,
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: brandPrimary,
                    pointHoverBorderColor: "rgba(220,220,220,1)",
                    pointHoverBorderWidth: 2,
                    pointRadius: 1,
                    pointHitRadius: 0,
                    data: dataArray.reverse(),
                    spanGaps: false
                }
            ]
        }
    });

  }
}
        function logout() {
            location.reload(true);
        }
        function todate(stampDate){
            var date = new Date(stampDate*1);
            var day = date.getDate();
            var month = 1 + date.getMonth();
            var year = date.getFullYear();
            if(month>9){
            var formattedTime = day+"."+month+"."+year;
        }else{
            var formattedTime = day+".0"+month+"."+year;
        }
            return formattedTime;
        }
        function updateDate(){
            $("#tble").html("");
            var start = $("#startdate").val();
            var end = $("#enddate").val();
            var startunixtime = new Date(start).getTime();
            var endunixtime = new Date(end).getTime();
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/timeseries?start="+startunixtime+"&end="+endunixtime+"",
                cache: false,
                timeout: 600000,
                success: function (data) {
                    var jsonArr = data;
                    for(var i=0; i<jsonArr.length;i++){
                    console.log(jsonArr[i]+"OSOSOS");
                    var str = jsonArr[i].toString().split(",");
                    $("#tble").append("<tr><td>"+todate(str[0])+"</td>\n\
                                        <td>"+str[1]+"</td>\n\
                                        <td>"+str[2]+"</td></tr>");
                }
                drawChart(jsonArr);
                },
                error: function (e) {
                    console.log(e);
                }
            });
        }
