<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">

    <c:choose>
        <c:when  test="${empty dataset.fieldMap}">
            <c:set var="id" value="${dataset._id}"/>
            <c:url var="columnFormUrl" value="/dataSet/columnForm?datasetId=${id}"/>
            <a class="btn btn-primary" href="${columnFormUrl}" role="button">Add a new field to the dataset</a>
        </c:when>
        <c:otherwise>
            <c:set var="id" value="${dataset._id}"/>
            <c:url var="dataFormUrl" value="/dataSet/dataForm?datasetId=${id}"/>
            <a class="btn btn-primary" href="${dataSetFormUrl}" role="button">Add a new data to the dataset</a>


            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <c:forEach items="${fieldKeys}" var="column" varStatus="loop">
                        <tr>
                            <th>${column}</th>
                        </tr>
                    </c:forEach>
                    </thead>
                    <tbody>
                    <!--
                    <c:forEach items="${fieldKeys}" var="column" varStatus="loop">
                        <tr>
                            <td>${column}</td>
                        </tr>
                    </c:forEach>
                    -->
                    </tbody>
                </table>
            </div>

        </c:otherwise>
    </c:choose>
    <!--
    <c:set var="id" value="${dataset._id}"/>
    <c:url var="dataSetFormUrl" value="/dataSet/columnForm?datasetId=${id}"/>
    <a class="btn btn-primary" href="${dataSetFormUrl}" role="button">Add a new field to the dataset</a>
    -->

    <c:url var="homeUrl" value="/home"/>
    <a href="${homeUrl}">
        <span class="glyphicon glyphicon-arrow-left"></span> Back to home
    </a>
</div>