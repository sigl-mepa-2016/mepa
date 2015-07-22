<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">

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