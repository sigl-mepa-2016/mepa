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
                    var data = getDatas();
                    chart.setChartType("Table");
                    chart.setDataTable(data);
                    chart.draw();
                }
            }

            function getDatas() {
                //Still Sample datas, need to be get on DataBase...
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Student\'s name');
                data.addColumn('number', 'Height');
                data.addColumn('date', 'Birth Date');
                data.addColumn('number', 'Average Grade');
                data.addRows([
                    ['Rosa', 180, new Date(1993, 2, 28), 18],
                    ['Carla', 160, new Date(1994, 5, 17), 16],
                    ['Joe', 190, new Date(1990, 10, 18), 9],
                    ['Linda', 170, new Date(1950, 11, 14), 10],
                    ['Sarah', 175, new Date(2008, 1, 10), 8]
                ]);
                return data;
            }
            drawTable()
        }
    </script>
</div>
