<%@ include file="/WEB-INF/views/includes/common.jsp"%>


<div class="container">

    <!-- Home message-->
        <div class="welcome-content">
                    <div class="welcome-content-inner">
                        <h1>WELCOME TO EpiData!</h1>
                        <hr>
                        <p>Data accessible and useable by everyone</p>
                    </div>
                </div>

    <!-- Exemple button to delete-->
        <!--<p class="text-center">
            <c:url var="coreExampleUrl" value="/example/core/"/>
            <a class="btn btn-lg btn-primary" href="${coreExampleUrl}" role="button">View Core (Database) module and form validation example</a>
        </p>-->
</div>

<div class="container">
    <!-- Filter -->
    <div class="filtre-content">
        <h2>Filters</h2>
        <div class="css-treeview">
            <ul>
                <li>
                    <p>filtre par vue </p>
                    <ul>
                        <li><a href="./">Cartographie</a></li>
                        <li><a href="./">Graphique</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>


    <!-- Search bar -->
    <c:url var="searchFormActionUrl" value="/search/searchAction"/>
    <form:form role="form" action="${searchFormActionUrl}" modelAttribute="searchFormAction" method="post">
        <form:errors path="search" cssStyle="color: #FF0000;" htmlEscape="false"/>
        <form:input id="search" path="search" type="text" placeholder="Search data" class="form"/>
        <button type="submit" class="submit-button">Search</button>
    </form:form>

    <!-- Datasets -->
    <h2 class="white">Datasets in database</h2>
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
                    <td><fmt:formatDate value="${dataset.lastModified}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                </tr>
            </c:forEach>
            <!-- Visualization -->
            </tbody>
        </table>
    </div>
</div>

