<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="alert alert-success">
        A new Model with data '${model.data}' has been added!
    </div>
    <p>
        <c:url var="coreExampleUrl" value="/example/core/"/>
        <a href="${coreExampleUrl}">
            <span class="glyphicon glyphicon-arrow-left"></span> Back to Core example form
        </a>
    </p>
</div>
<div class="container">
    <h2>Models in database</h2>
    <div class="alert alert-warning">
        Results of your search
    </div>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Created</th>
                    <th>Title</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${models}" var="model" varStatus="loop">
                    <tr>
                        <td><fmt:formatDate value="${model.created}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                        <td>${model.data}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
