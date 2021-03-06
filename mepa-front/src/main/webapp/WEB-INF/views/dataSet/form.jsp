<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <h2 class="white">Dataset creation</h2>

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
            <br/>
            <label for="isCarto">Check if the dataset can be see as cartography:</label>
            <input type="checkbox" id="isCarto" name="isCarto" value="1"/>
            <br/>
            <label for="isGraphic">Check if the dataset can be see as a graph:</label>
            <input type="checkbox" id="isGraphic" name="isGraphic" value="1"/>

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


