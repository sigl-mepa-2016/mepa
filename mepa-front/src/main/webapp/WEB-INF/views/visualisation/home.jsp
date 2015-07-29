<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<%-- Script to display first the right visualisation --%>
<script>
    var dataType = 1;
    var URL = window.location.origin + window.location.pathname + (window.location.search);

    function setDataType(i) {
        dataType = i;
        URL = URL.substring(0,URL.length-1);
        URL = URL + dataType;
    }

    function openFBPopUp() {
        var FBPopUp = "https://www.facebook.com/sharer/sharer.php?u=" + encodeURIComponent(URL);
        window.open(FBPopUp,'MyWindow',width=100,height=100);
        return false;
    }

    function openTWPopUp() {
        var TWPopUp = "http://twitter.com/share?url=" + encodeURIComponent(URL);
        window.open(TWPopUp,'MyWindow',width=100,height=100);
        return false;
    }

    function openGPPopUp() {
        var GPlusPopUp = "https://plus.google.com/share?url=" + encodeURIComponent(URL);
        window.open(GPlusPopUp,'MyWindow',width=100,height=100);
        return false;
    }

    $(document).ready(function() {
        var sPageURL = window.location.search.substring(1);
        var sURLVariables = sPageURL.split('&');
        for (var i = 0; i < sURLVariables.length; i++)
        {
            var sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] == "dataType")
            {
                dataType = sParameterName[1];
            }
        }

        if(dataType == "2") {
            $("#chart-tab").addClass('active');
            $('#chart-view').addClass('in active');
        }
        else if(dataType == "3") {
            $("#carto-tab").addClass('active');
            $('#carto-view').addClass('in active');
        }
        else if(dataType == "4") {
            $("#info-tab").addClass('active');
            $('#info-view').addClass('in active');
        }
        else {
            $("#table-tab").addClass('active');
            $('#table-view').addClass('in active');
        }
    });
</script>
<%-- /Script to display first the right visualisation --%>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h2>Visualization</h2>
            <div class="btn-group pull-right">
                <c:url var="logoFbURL" value="/img/fb.png"/>
                <c:url var="logoTwURL" value="/img/tw.png"/>
                <c:url var="logoGPlusURL" value="/img/gplus.png"/>
                <img class="tw-share-button" onClick="openTWPopUp();" alt="" src="${logoTwURL}"/>
                <img class="fb-share-button" onClick="openFBPopUp();" alt="" src="${logoFbURL}"/>
                <img class="gplus-share-button" onClick="openGPPopUp();" alt="" src="${logoGPlusURL}"/>
            </div>
            <ul class="nav nav-tabs">
                <li id="info-tab"><a data-toggle="tab" href="#info-view" onclick="setDataType(4)">Info</a></li>
                <li id="table-tab"><a data-toggle="tab" href="#table-view" onclick="setDataType(1)">Table</a></li>
                <li id="chart-tab"><a data-toggle="tab" href="#chart-view" onclick="setDataType(2)">Chart</a></li>
                <li id="carto-tab"><a data-toggle="tab" href="#carto-view" onclick="setDataType(3)">Map</a></li>
            </ul>
        </div>
        <div class="tab-content">
                <%-- Info view --%>
                <div id="info-view" class="tab-pane fade">
                    <p>   <u>Theme</u> : ${dataset.theme}</p>
                    <br/>
                    <p>   <u>Name</u> : ${dataset.name}</p>
                    <br/>
                    <p>   <u>Owner</u> : ${dataset.owner}</p>
                    <br/>
                    <p>   <u>Date of last modification</u> : <fmt:formatDate value="${dataset.lastModified}" pattern="dd/MM/yyyy HH:mm:ss"/></p>
                    <br/>
                    <p>   <u>This dataset can be seen as a graph</u> : ${dataset.isGraphic}</p>
                    <br/>
                    <p>   <u>This dataset can be seen as a cartography</u> : ${dataset.isCarto}</p>
                    <br/>
                </div>
                <%-- /Info view --%>
                <%-- Tabular view --%>
                    <div class="tab-pane fade" id="table-view">
                        <c:choose>
                        <c:when test="${cookie.token != null && cookie.token != ''}">
                        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                           <div class="panel panel-default">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    <div class="panel-heading" role="tab" id="headingOne">
                                        <h4 class="panel-title">
                                            [ADMIN] Custom table
                                        </h4>
                                    </div>
                                </a>

                                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                    <div class="panel-body">
                                        <%@ include file="hideShowColumn.jsp"%>
                                    </div>
                                </div>
                            </div>
                        </div>
                            </c:when>
                            </c:choose>
                    <table id="visualization_tab_div" class="table">
                        <script type="application/javascript">

                            // Recover java's ArrayList (need to convert elements to String)
                            var columns = new Array();
                            <c:forEach items="${fieldKeys}" var="col" varStatus="loop">
                                columns.push("${col}");
                            </c:forEach>;


                            google.load('visualization', '1.0',{packages:["table"]});
                            google.setOnLoadCallback(initialize);

                            function drawTable() {
                                {
                                    var div = document.getElementById('visualization_tab_div');
                                    var data = getData();
                                    var table = new google.visualization.Table(div);
                                    table.draw(data, {
                                        width: '100%',
                                        allowHtml: true,
                                        page: 'enable',
                                        pageSize: 50
                                    });
                                    document.getElementById('line').innerHTML = "Number of line : " + data.getNumberOfRows();
                                }
                            }

                            function getData() {
                                var parameter = location.search.substring(1).split('&');
                                var temp = [];

                                // get the different parameters in the temp array in json format
                                for (var i = 0; i < parameter.length; ++i){
                                    var x = parameter[i].split("=");
                                    temp[x[0]]=x[1];
                                }
                                var l = temp.datasetId;
                                var urldata = '/mepa-front/api/dataSet/' + l + '/data'
                                var jsondataset = $.ajax({
                                    dataType: "json",
                                    url :urldata,
                                    async : false}) ;
                                var b = JSON.parse(jsondataset.responseText);
                                var data = new google.visualization.DataTable();
                                var size = columns.length;

                                for (var i = 0; i < columns.length; i++){
                                    data.addColumn('string', columns[i]);
                                }

                                //columns only has the title of the columns, not the value inside
                                for(var i = 0; i < b.data[columns[0]].value.length; i++){
                                    data.addRows(1);
                                }
                                //i is column, j is row
                                for (var i = 0; i < size; i++){
                                    for (var j = 0; j < b.data[columns[0]].value.length;j++) {
                                        data.setValue(j, i, b.data[columns[i]].value[j]);
                                    }
                                }
                                return data;
                            }
                            function initialize() {

                                drawTable()
                            }
                            function updateTable(c){
                                var checkVal = document.getElementById(c);
                                console.log("updateTable : c is " + c + " entered with " + checkVal + " checked ? : " + checkVal.checked);
                                if (!checkVal.checked) {
                                    var i_col = columns.indexOf(c);
                                    // remove value from array
                                    console.log("updateTable : " + c + " removed");
                                    columns.splice(i_col, 1);
                                    drawTable();
                                }
                                else {
                                    // true, we add it (again)
                                    console.log("updateTable : " + c + " added again");
                                    columns.push(c);
                                    drawTable();
                                }
                            }
                            function updateTable2(c){
                                var checkVal2 = document.getElementById(c);
                                var i_col = columns.indexOf(c);
                                columns.splice(i_col, 1);
                                columns.push(c);
                                drawTable();
                            }
                        </script>
                    </table>
                        <div id="line"></div>
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
                        <td>
                            <strong>Number of Points :</strong>
                            <select id="points-quantity">
                                <option value="">Display All</option>
                                <option value="1">1</option>
                                <option value="3">3</option>
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="20">20</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                                <option value="500">500</option>
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
                            <strong>Agregation function :</strong>
                            <select id="agregation-axe1">
                                <option></option>
                                <option>Count</option>
                                <option>Min</option>
                                <option>Max</option>
                            </select>
                        </td>
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

                <div id="carto-div-left" class="carto-div-left panel panel-default">
                    <div class="panel-body">
                        Arrondissements<br>
                        <div class="btn-group" role="group">
                            <button id="tous" type="button" class="btn btn-default btn-xs">Tous</button><br><br><br>
                            <button id="1er" type="button" class="btn btn-default btn-xs">1er</button>
                            <button id="2e" type="button" class="btn btn-default btn-xs">2e</button>
                            <button id="3e" type="button" class="btn btn-default btn-xs">3e</button>
                            <button id="4e" type="button" class="btn btn-default btn-xs">4e</button><br><br>
                            <button id="5e" type="button" class="btn btn-default btn-xs">5e</button>
                            <button id="6e" type="button" class="btn btn-default btn-xs">6e</button>
                            <button id="7e" type="button" class="btn btn-default btn-xs">7e</button>
                            <button id="8e" type="button" class="btn btn-default btn-xs">8e</button><br><br>
                            <button id="9e" type="button" class="btn btn-default btn-xs">9e</button>
                            <button id="10e" type="button" class="btn btn-default btn-xs">10e</button>
                            <button id="11e" type="button" class="btn btn-default btn-xs">11e</button>
                            <button id="12e" type="button" class="btn btn-default btn-xs">12e</button><br><br>
                            <button id="13e" type="button" class="btn btn-default btn-xs">13e</button>
                            <button id="14e" type="button" class="btn btn-default btn-xs">14e</button>
                            <button id="15e" type="button" class="btn btn-default btn-xs">15e</button>
                            <button id="16e" type="button" class="btn btn-default btn-xs">16e</button><br><br>
                            <button id="17e" type="button" class="btn btn-default btn-xs">17e</button>
                            <button id="18e" type="button" class="btn btn-default btn-xs">18e</button>
                            <button id="19e" type="button" class="btn btn-default btn-xs">19e</button>
                            <button id="20e" type="button" class="btn btn-default btn-xs">20e</button><br><br>
                            <button id="seine" type="button" class="btn btn-default btn-xs">Seine</button>
                        </div>
                    </div>
                </div>
                <div id ="carto-div-right" class="carto-div-right"></div>
                <input id="data" name="data" type="hidden" value='${dataset._id}'>
            </div>
            <c:url var="mapCssUrl" value="/css/map.css" />
            <link rel="stylesheet" href="${mapCssUrl}" type="text/css" />
            <script type="text/javascript" src="<spring:url value="/js/map.js"/>"></script>
            <%-- /Carto view --%>

        </div>
    </div>
</div>