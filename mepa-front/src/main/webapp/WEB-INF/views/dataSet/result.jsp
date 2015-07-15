<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
  <div class="alert alert-success">
    A new dataset has been added!
  </div>
  <p>
    <c:url var="dataSetUrl" value="/dataSet/"/>
    <a href="${dataSetUrl}">
      <span class="glyphicon glyphicon-arrow-left"></span> Back to Dataset form
    </a>
  </p>
</div>