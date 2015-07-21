<%--
  Created by IntelliJ IDEA.
  User: Raphael
  Date: 15/07/2015
  Time: 00:06
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <h2 class="white">Data Visualisation</h2>


    <br/><br/>
    <div  id="visualization_div" width="400" height="400"></div>
    <br/><br/>
    <script type="application/javascript">

        google.load('visualization', '1.0');
        google.setOnLoadCallback(initialize);

        function initialize() {

            var chart = new google.visualization.ChartWrapper({
                containerId: 'visualization_div'
            });

            function drawTable() {
                {
                    var data = getData();
                    chart.setChartType("Table");
                    chart.setDataTable(data);
                    chart.draw();
                }
            }

            function getData() {
                //Still Sample datas, need to be get on DataBase...
                var jsonData = $.ajax({
                    url: "${}",
                    dataType:"json",
                    async:false
                }).responseText;
                var data = new google.visualization.DataTable(jsonData);
                return data;
            }
            drawTable()
        }
    </script>
</div>
