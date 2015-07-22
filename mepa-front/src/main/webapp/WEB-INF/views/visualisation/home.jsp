<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h2>Visualization</h2>
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#table-view">Table</a></li>
                <li><a data-toggle="tab" href="#chart-view">Chart</a></li>
                <li><a data-toggle="tab" href="#carto-view">Map</a></li>
            </ul>
        </div>
        <div class="tab-content">
                <%-- Tabular view --%>
                <div class="tab-pane fade in active" id="table-view">
                    <%--<div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                              <tr>
                                <c:forEach items="${fieldKeys}" var="column" varStatus="loop">
                                  <th>${column}</th>
                                </c:forEach>
                              </tr>
                              <c:forEach begin="0" end="${size}" var="index" varStatus="loop1">
                              <tr>
                                  <c:forEach items="${fieldKeys}" var="column" varStatus="loop2">
                                  <td>
                                          ${data.getData().get(column).get(index)}
                                  </td>
                                  </c:forEach>
                              </tr>
                              </c:forEach>
                            </thead>
                        </table>
                    </div>--%>
                    <table id="visualization_div" class="table">
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
                                    for (var i = 0; i < size;i++){
                                        for (var j = 0; j < array.length;j++) {
                                            data.setValue(i, j, b.data[array[j]][i]);
                                        }
                                    }
                                    return data;
                                }
                                drawTable()
                            }
                        </script>
                    </table>
                    <c:url var="customTable" value="/dataVisualisationTab/customVisualisationTab?datasetId=${dataset._id}"/>
                    <a role="button" class="btn btn-default" href="${customTable}">Custom table</a>
                    </div>
            <%-- /Tabular view --%>
            <%-- Chart view --%>
            <script type="text/javascript" src="<spring:url value="/js/colpick.js"/>"></script>
            <script type="text/javascript" src="<spring:url value="/js/data_visualisation.js"/>"></script>
            <div id="chart-view" class="tab-pane fade">
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <strong>Graph Type :</strong>
                            <select id="graph-type">
                                <option value="LineChart">LineChart</option>
                                <option value="ColumnChart">ColumnChart</option>
                            </select>
                            <br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Horizontal Axe :</strong>
                            <select id="horizontal-axe">
                                <option></option>
                            </select>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <table>
                    <tbody id="table">
                    <tr id="line1">
                        <td>
                            <strong>Vertical Axe 1 :</strong>
                            <select id="vertical-axe1">
                                <option></option>
                            </select>
                        </td>
                        <td>
                            <div class="color-box1" style="width:30px; height:30px; margin:5px; border: 1px solid white;"></div>
                        </td>
                        <td>
                            <button type="button" id="add-chart">Add Chart</button>
                        </td>
                    </tr>
                    <tr id="line2">
                        <td>
                            <strong>Vertical Axe 2 :</strong>
                            <select id="vertical-axe2">
                                <option></option>
                            </select>
                        </td>
                        <td>
                            <div class="color-box2" style="width:30px; height:30px; margin:5px; border: 1px solid white;"></div>
                        </td>
                        <td>
                            <button type="button" id="delete2">Delete</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <br/><br/>
                <div  id="visualization_div" width="400" height="400"></div>
                <br/><br/>
            </div>
            <%-- /Chart view --%>
            <%-- Carto view --%>
            <div id="carto-view" class="tab-pane fade">
                <h3>CARTOGRAPHY VIEW HERE</h3>
            </div>
            <%-- /Carto view --%>
        </div>
    </div>
</div>