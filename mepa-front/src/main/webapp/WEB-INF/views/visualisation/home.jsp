<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h2>Visualization</h2>
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#table-view">Table</a></li>
                <li><a data-toggle="tab" href="#chart-view">Chart</a></li>
              <c:url var="cartography" value="/cartography/core/map?datasetId=${dataset._id}"/>
                <li role="presentation"><a href="${cartography}">Map</a></li>
            </ul>
        </div>
        <div class="panel-body">
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
    </div>
    </div>
</div>