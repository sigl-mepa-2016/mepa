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
    <h4 class="white">Data sets</h4>
    <div  id="visualization_div"></div>
    <div id="line" class="white" />test</div>
    <br/><br/>

<script type="application/javascript">

    google.load('visualization', '1.0',{packages:["table"]});
    google.setOnLoadCallback(initialize);
    function initialize() {

            function drawTable() {
                {
                    var div = document.getElementById('visualization_div');
                    var data = getData();
                    var table = new google.visualization.Table(div);
                    table.draw(data,{width:'100%'});
                    document.getElementById('line').innerHTML = "Number of line : " + data.getNumberOfRows();
                }
            }
            function getData() {
                //Still Sample datas, need to be get on DataBase...

                var jsondataset = $.ajax({
                    dataType: "json",
                    url :'/mepa-front/api/dataSet.json',
                    async : false}) ;
                var b = JSON.parse(jsondataset.responseText);
                var data = new google.visualization.DataTable();

                data.addColumn('string', 'Name');
                data.addColumn('string', 'id');
                for (var i = 0; i < ${datasets.size()};i++){
                    data.addRows([[b.items[i].name, b.items[i].id]]);
                }

                return data;
            }
            drawTable()
        }
    </script>
</div>
