<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <h2>DataSets</h2>

    <c:url var="addCustomDataSetFormActionUrl" value="/dataSet/add"/>
    <form:form role="form" action="${addCustomDataSetFormActionUrl}" modelAttribute="addCustomDataSetFormBean" method="post">
        <div class="form-group">
            <p class="help-block">Create a new dataset</p>
            <label for="name">Enter the name of the dataset below:</label>
            <br/>
            <form:input id="name" path="name" type="text" maxlength="256" placeholder="Name"/>
            <br/>
            <label for="owner">Enter the producer of the dataset below:</label>
            <br/>
            <form:input id="owner" path="owner" type="text" maxlength="32" placeholder="Producer"/>
            <br/>
            <label for="theme">Enter the theme of the dataset below:</label>
            <br/>
            <form:input id="theme" path="theme" type="text" maxlength="32" placeholder="Theme"/>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form:form>
</div>

<div class="container">
    <h2>Datasets in database</h2>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Theme</th>
                <th>Name</th>
                <th>Producer</th>
                <th>Last modified</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${datasets}" var="dataset" varStatus="loop">
                <tr>
                    <td>${dataset.theme}</td>
                    <td>${dataset.name}</td>
                    <td>${dataset.owner}</td>
                    <td><fmt:formatDate value="${dataset.last_modified}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                </tr>
            </c:forEach>
            <!-- Visualization -->
            </tbody>
        </table>
    </div>
</div>
