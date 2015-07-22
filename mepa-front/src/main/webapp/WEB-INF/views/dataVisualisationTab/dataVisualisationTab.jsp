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
    <div id="line" class="white" /></div>
    <c:url var="customTable" value="/dataVisualisationTab/customVisualisationTab"></c:url>
    <a class="btn btn-default" href="${customTable}" role="button">Custom table</a>
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
                    table.draw(data,{width:'100%', allowHtml: true});
                    document.getElementById('line').innerHTML = "Number of line : " + data.getNumberOfRows();
                }
            }
            function getData() {

                var jsondataset = $.ajax({
                    dataType: "json",
                    url :'/mepa-front/api/dataSet.json',
                    async : false}) ;
                var b = JSON.parse(jsondataset.responseText);
                var data = new google.visualization.DataTable();

                data.addColumn('string', 'Name');
                data.addColumn('string', 'id');
                for (var i = 0; i < b.items.length;i++){
                    data.addRows([['<a href="/mepa-front/dataVisualisationTab/dataVisualisation?value='+b.items[i].id+'">'+b.items[i].name+'</a>', b.items[i].id]]);
                }
                return data;
            }
            drawTable()
        }
    </script>
</div>
