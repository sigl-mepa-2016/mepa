<%--
  Created by IntelliJ IDEA.
  User: Raphael
  Date: 22/07/2015
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<div class="container">
    <h2 class="white">Data Visualisation</h2>

    <br/><br/>
    <h4 class="white">Datas</h4>
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
            var parameter = location.search.substring(1);
            var temp = parameter.split("=");
            var l = temp[1]
            var urldata = '/mepa-front/api/dataSet/' + l + '/data'
            var jsondataset = $.ajax({
                dataType: "json",
                url :urldata,
                async : false}) ;
            var b = JSON.parse(jsondataset.responseText);
            var data = new google.visualization.DataTable();
            var size = 0;
            var array = [];
            for (x in  b.data ){
                array.push(x);
                size = b.data[x].length;
            }
            for (var i = 0; i < array.length; i++){
                data.addColumn('string',array[i]);
            }
            for(var i = 0; i < size; i++){
                data.addRows(1);
            }
   //         console.log(b.data);
     //       console.log(b.data.length);
       //     console.log(b.data.non[0]);
            for (var i = 0; i < size;i++){
                for (var j = 0; j < array.length;j++){
                    data.setValue(i,j, b.data[array[j]][i]);//[[b.data[array[0]][i], b.data[array[1]][i]]]);
                }
            }
            return data;
        }
        drawTable()
    }
</script>
</div>