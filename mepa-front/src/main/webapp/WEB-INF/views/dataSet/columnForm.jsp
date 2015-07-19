<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
  <h2 class="white">Dataset creation</h2>

  <c:set var="id" value="${dataset.id}"/>
  <c:url var="addCustomColumnFormActionUrl" value="/dataSet/addColumn?datasetId=${id}"/>
  <form:form role="form" action="${addCustomColumnFormActionUrl}" modelAttribute="addCustomColumnFormBean" method="post">
    <div class="form-group">
      <p class="help-block">Create a new dataset</p>
      <label for="name">Enter the name of the field below:</label>
      <br/>
      <form:input id="name" path="name" type="text" maxlength="256" placeholder="Name"/>
      <br/>
      <label for="owner">Enter the type of the field below:</label>
      <br/>
      <form:input id="type" path="type" type="text" maxlength="32" placeholder="Producer"/>
    </div>

    <button type="submit" class="btn btn-default">Submit</button>
  </form:form>

  <c:set var="id" value="${dataset.id}"/>
  <c:url var="detailsUrl" value="/dataSet/details?datasetId=${id}"/>
  <a href="${detailsUrl}">
    <span class="glyphicon glyphicon-arrow-left"></span> Back to dataset details
  </a>

</div>


