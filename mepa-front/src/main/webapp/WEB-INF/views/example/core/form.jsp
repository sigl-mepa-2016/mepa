<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="page-header">
        <h1>This is the example using Core module (Database)</h1>
    </div>
    <c:url var="addCustomModelFormActionUrl" value="/example/core/add"/>
    <form:form role="form" action="${addCustomModelFormActionUrl}" modelAttribute="addCustomModelFormBean" method="post">
        <div class="form-group">
            <form:errors path="email" cssStyle="color: #FF0000;" htmlEscape="false"/>
            <p class="help-block">Want to add a custom data and to <b>test form validation</b>?</p>
            <label for="email">Enter an email address below:</label>
            <br/>
            <form:input id="email" path="email" type="text" maxlength="20" placeholder="Email address"/>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form:form>
</div>

<div class="container">
    <h2>Models in database</h2>
    <div class="alert alert-warning">
        A new random model will be added each time this page is called
    </div>
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
