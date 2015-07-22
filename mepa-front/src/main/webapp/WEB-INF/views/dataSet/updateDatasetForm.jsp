<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <h2 class="white">Dataset modification</h2>

    <c:set var="dataset" value="${dataset}"/>
    <c:url var="addCustomDataSetFormActionUrl" value="/dataSet/update?datasetId=${dataset._id}"/>
    <form:form role="form" action="${addCustomDataSetFormActionUrl}" modelAttribute="addCustomDataSetFormBean" method="post">
        <div class="form-group">
            <p class="help-block">Create a new dataset</p>
            <label for="name">Enter the name of the dataset below:</label>
            <br/>
            <form:input id="name" path="name" type="text" maxlength="256" value="${dataset.name}"/>
            <br/>
            <label for="owner">Enter the producer of the dataset below:</label>
            <br/>
            <form:input id="owner" path="owner" type="text" maxlength="32" value="${dataset.owner}"/>
            <br/>
            <label for="theme">Enter the theme of the dataset below:</label>
            <br/>
            <form:input id="theme" path="theme" type="text" maxlength="32" value="${dataset.theme}"/>
        </div>

        <button type="submit" class="btn btn-default">Submit</button>
    </form:form>
    <div>
        <c:url var="homeUrl" value="/home/"/>
        <a href="${homeUrl}">
            <span class="glyphicon glyphicon-arrow-left"></span> Back to home
        </a>
    </div>
</div>


