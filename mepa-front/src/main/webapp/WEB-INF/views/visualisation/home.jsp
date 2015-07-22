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
                <div class="tab-pane" id="table-view">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                              <tr>
                                <c:forEach items="${fieldKeys}" var="column" varStatus="loop">
                                  <th>${column}</th>
                                </c:forEach>
                                <th></th>
                                <th></th>
                              </tr>
                            </thead>
                        </table>
                    </div>
                    <c:url var="customTable" value="/dataVisualisationTab/customVisualisationTab?datasetId=${dataset._id}"/>
                    <a role="button" class="btn btn-default" href="${customTable}">Custom table</a>
                    </div>
            <%-- /Tabular view --%>
            <%-- Chart view --%>
            <script type="text/javascript" src="<spring:url value="/js/colpick.js"/>"></script>
            <script type="text/javascript" src="<spring:url value="/js/data_visualisation.js"/>"></script>
            <div id="chart-view" class="tab-pane">
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
            <div id="carto-view" class="tab-pane">
                <h3>CARTOGRAPHY VIEW HERE</h3>
            </div>
            <%-- /Carto view --%>
        </div>
    </div>
</div>