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
