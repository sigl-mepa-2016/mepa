<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="page-header">
        <h1>search page</h1>
    </div>
    <c:url var="searchFormActionUrl" value="/search/core/searchAction"/>
        <form:form role="form" action="${searchFormActionUrl}" modelAttribute="searchFormAction" method="post">
            <div class="form-group">
                <form:errors path="search" cssStyle="color: #FF0000;" htmlEscape="false"/>
                <label for="email">What do you want to search ?</label>
                <br/>
                <form:input id="search" path="search" type="text" maxlength="20" placeholder="${searchBar}"/>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form:form>
</div>
<div class="container">
    <h2>${tableName}</h2>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Created</th>
                    <th>Data</th>
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
