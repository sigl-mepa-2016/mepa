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
            <div id="chart-view" class="tab-pane">
                <h3>CHART VIEW HERE</h3>
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