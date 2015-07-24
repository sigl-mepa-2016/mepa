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
                    <tbody>
                    <c:if test="${size != 0}">
                        <c:forEach begin="0" end="${size - 1}" var="index" varStatus="loop1">
                            <tr>
                                <c:forEach items="${fieldKeys}" var="column" varStatus="loop2">
                                    <td>
                                        ${data.getData().get(column).get(index)}
                                    </td>
                                </c:forEach>
                                <td>
                                    <c:url var="deleteDataUrl" value="/dataSet/deleteData?datasetId=${id}"/>
                                    <form action="${deleteDataUrl}" method="post">
                                        <input type="hidden" name="index" value="${index}"/>
                                        <input type="submit" value="Delete"/>
                                    </form>
                                </td>
                                <td>
                                    <c:url var="updateDataUrl" value="/dataSet/updateDataForm?datasetId=${id}"/>
                                    <form action="${updateDataUrl}" method="post">
                                        <input type="hidden" name="index" value="${index}"/>
                                        <input type="submit" value="Update">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
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