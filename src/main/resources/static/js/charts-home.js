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
                Plotly.newPlot('myDivts', data);
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
            if(month>9){
            var formattedTime = day+"."+month+"."+year+" "+hours+":"+minute+":"+seconds;
        }else{
            var formattedTime = day+".0"+month+"."+year+" "+hours+":"+minute+":"+seconds;
        }
            return formattedTime;
        }
        function updateDate(){
            $("#myDivts").html("");
            $("#selectedTag").html("");
            var start = $("#startdate").val();
            var end = $("#enddate").val();
            var tag = $("#stag").val();
            var startunixtime = new Date(start).getTime();
            var endunixtime = new Date(end).getTime();
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/timeseries?start="+startunixtime+"&end="+endunixtime+"&tag="+tag,
                cache: false,
                timeout: 600000,
                success: function (data) {
                    var jsonArr = data;
                drawChart(jsonArr);
                $("#selectedTag").append(tag);
                },
                error: function (e) {
                    console.log(e);
                }
            });
        }
