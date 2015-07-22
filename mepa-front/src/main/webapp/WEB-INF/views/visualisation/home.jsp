<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h2>Visualization</h2>
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="#">Table</a></li>
                <li role="presentation"><a href="#">Chart</a></li>
                <li role="presentation"><a href="#">Map</a></li>
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