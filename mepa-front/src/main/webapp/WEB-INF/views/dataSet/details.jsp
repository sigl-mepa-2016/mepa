<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">

    <c:set var="id" value="${dataset._id}"/>
    <c:choose>
        <c:when  test="${empty dataset.fieldMap}">
            <c:url var="columnFormUrl" value="/dataSet/columnForm?datasetId=${id}"/>
            <a class="btn btn-primary" href="${columnFormUrl}" role="button">Add a new field to the dataset</a>
        </c:when>
        <c:otherwise>
            <c:url var="dataFormUrl" value="/dataSet/dataForm?datasetId=${id}"/>
            <a class="btn btn-primary" href="${dataFormUrl}" role="button">Add a new data to the dataset</a>


            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <c:forEach items="${fieldKeys}" var="column" varStatus="loop">
                            <th>${column}</th>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <!--
                    Display data when existing
                    -->
                    </tbody>
                </table>
            </div>

        </c:otherwise>
    </c:choose>

    <c:url var="homeUrl" value="/home"/>
    <a href="${homeUrl}">
        <span class="glyphicon glyphicon-arrow-left"></span> Back to home
    </a>
</div>