<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">

    <c:set var="id" value="${dataset._id}"/>
    <c:url var="dataSetFormUrl" value="/dataSet/columnForm?datasetId=${id}"/>
    <a class="btn btn-primary" href="${dataSetFormUrl}" role="button">Add a new field to the dataset</a>

    <c:url var="homeUrl" value="/home"/>
    <a href="${homeUrl}">
        <span class="glyphicon glyphicon-arrow-left"></span> Back to home
    </a>
</div>