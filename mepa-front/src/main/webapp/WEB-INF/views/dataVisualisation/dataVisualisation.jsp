<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <h2 class="white">Data Visualisation</h2>

    <br/>
   <strong class="white">Horizontal Axe :</strong>
    <select id="horizontal-axe">
        <option></option>
       <option value="student_name">Student's Name</option>
   </select>

    <strong class="white"> - Vertical Axe :</strong>
    <select id="vertical-axe">
        <option></option>
        <option value="height">Height</option>
        <option value="birthdate">Birth Date</option>
        <option value="average_grade">Average Grade</option>
    </select>

    <strong class="white"> - Graph Type :</strong>
    <select id="graph-type">
        <option value="LineChart">LineChart</option>
        <option value="ColumnChart">ColumnChart</option>
        <option value="Table">Table</option>
    </select>
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

           var graphType = document.getElementById('graph-type');
           var verticalAxe = document.getElementById('vertical-axe');
           var horizontalAxe = document.getElementById('horizontal-axe');

           function drawGraph() {
               if(verticalAxe.value != "" && horizontalAxe.value != "") {
                   var data = getDatas(verticalAxe.value, horizontalAxe.value);
                   chart.setChartType(graphType.value);
                   chart.setDataTable(data);
                   chart.draw();
               }
           }

           function getDatas(x, y) {
                //Still Sample datas, need to be get on DataBase...
               var data = new google.visualization.DataTable();
               data.addColumn('string', 'Student\'s name');
               data.addColumn('number', 'Height');
               data.addRows([
                   ['Rosa', 180],
                   ['Carla', 160],
                   ['Joe', 190],
                   ['Linda', 170],
                   ['Sarah', 175]
               ]);

               var data2 = new google.visualization.DataTable();
               data2.addColumn('string', 'Student\'s name');
               data2.addColumn('date', 'Birth Date');
               data2.addRows([
                   ['Rosa', new Date(1993, 2, 28)],
                   ['Carla', new Date(1994, 5, 17)],
                   ['Joe', new Date(1990, 10, 18)],
                   ['Linda', new Date(1950, 11, 14)],
                   ['Sarah', new Date(2008, 1, 10)]
               ]);

               var formatter = new google.visualization.DateFormat({formatType: 'long'});
               formatter.format(data2, 1);

               var data3 = new google.visualization.DataTable();
               data3.addColumn('string', 'Student\'s name');
               data3.addColumn('number', 'Average Grade');
               data3.addRows([
                   ['Rosa', 18],
                   ['Carla', 16],
                   ['Joe', 9],
                   ['Linda', 10],
                   ['Sarah', 8]
               ]);

               if (graphType.value == "Table")
               {
                   data = google.visualization.data.join(data,data2,'inner',[[0,0]],[1],[1]);
                   data = google.visualization.data.join(data,data3,'inner',[[0,0]],[1,2],[1]);
                   return data;
               }
               if(verticalAxe.value == "height")
               {
                    return data;
               }
               if(verticalAxe.value == "birthdate")
               {
                   return data2;
               }
                   return data3;
           }

           graphType.onchange = function () {
               drawGraph();
           }

           verticalAxe.onchange = function () {
               drawGraph();
           }

           horizontalAxe.onchange = function () {
               drawGraph();
           }
       }

       function drawTable(){

       }
   </script>
</div>
