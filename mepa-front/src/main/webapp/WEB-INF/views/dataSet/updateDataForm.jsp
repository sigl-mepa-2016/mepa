<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
  <h2 class="white">Add a piece of data to the selected dataset</h2>

  <c:set var="id" value="${dataset._id}"/>
  <c:url var="addCustomDataFormActionUrl" value="/dataSet/updateData?datasetId=${id}&index=${index}"/>
  <form:form role="form" action="${addCustomDataFormActionUrl}" modelAttribute="addCustomDataFormBean" method="post">
    <div class="form-group">
      <c:forEach items="${columnToValueMap}" var="map" varStatus="loop">
        <label>Enter ${map.key} below:</label>
        <br/>
        <form:input path="fields" type="text" class="form-control" value="${map.value}" />
        <br/>
      </c:forEach>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form:form>

</div>