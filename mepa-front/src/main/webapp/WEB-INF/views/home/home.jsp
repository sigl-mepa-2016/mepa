<%@ include file="/WEB-INF/views/includes/common.jsp"%>



<div class="container">

    <div style="color: #FF0000;">${errorMessage}</div>

    <!-- Home message-->
        <div class="welcome-content">
            <div class="welcome-content-inner">
                <h1>WELCOME TO EpiData!</h1>
                <hr>
                <p>Data accessible and usable by everyone</p>
            </div>
        </div>
</div>

<div class="container">

    <!-- Filter -->
    <div class="filtre-content white">
        <h3>There is ${nbDataset} dataset</h3>
        <c:forEach items="${filters}" var="filter" varStatus="loop">
        <div class="css-treeview">
            <ul>
                <li>
                     <c:url var="CancelURL" value="/search/Cancel?CancelFilter=${filter}"/>
                     <c:url var="logoEpitaURL" value="/img/annuler.png"/>
                     <p>${filter} <a href="${CancelURL}">  <img src="${logoEpitaURL}" alt=""/></a></p>
                </li>
            <ul>
        </div>
        </c:forEach>

        <c:choose>
            <c:when test="${empty filters}">

            </c:when>
            <c:otherwise>
                <c:url var="CancelAllURL" value="/search/CancelAll"/>
                <a href="${CancelAllURL}">cancel all</a>
            </c:otherwise>
        </c:choose>

        <br/>
        <h2>Filters</h2>
        <div class="css-treeview">
            <ul>
                <li>
                    <p>Filter by view</p>
                    <ul>
                        <c:url var="Carto" value="/search/FilterCarto"/>
                        <li><a href="${Carto}">Cartography</a> ${resFilterCarto}</li>
                        <c:url var="Graphic" value="/search/FilterGraphic"/>
                        <li><a href="${Graphic}">Graph</a> ${resFilterGraph}</li>
                    </ul>
                </li>
                <li>
                    <p>Filter by theme</p>
                    <ul>
                        <c:forEach items="${themeFilter}" var="themeFilter" varStatus="loop">
                            <c:url var="themeURL" value="/search/themeFilter?theme=${themeFilter.key}"/>
                            <li><a href="${themeURL}">${themeFilter.key}</a> ${themeFilter.value}</li>
                        </c:forEach>
                    </ul>
                </li>
                <li>
                    <p>Filter by date</p>
                    <ul>
                        <c:forEach items="${dateFilter}" var="dateFilter" varStatus="loop">
                            <c:url var="dateURL" value="/search/dateFilter?date=${dateFilter.key}"/>
                            <li><a href="${dateURL}">${dateFilter.key}</a> ${dateFilter.value}</li>
                        </c:forEach>
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
    </form:form>

    <!-- Tri -->
    <c:url var="sortFormActionUrl" value="/search/Sort"/>
    <form:form role="form" action="${sortFormActionUrl}" modelAttribute="sortFormAction" method="post">
        <form:select path="sort">
        <c:choose>
            <c:when test="${sort_selected}">
                <option value="date">Last Modification</option>
                <option value="Title" selected>Title</option>
            </c:when>
            <c:otherwise>
                <option value="date" selected>Last Modification</option>
                <option value="Title">Title</option>
            </c:otherwise>
        </c:choose>


        </form:select>
         <button type="submit" class="submit-button">Sort</button>
    </form:form>

    <!-- Datasets -->
    <h2 class="white">Datasets in database</h2>
    <c:url var="dataSetFormUrl" value="/dataSet/form/"/>
    <a class="btn btn-warning" href="${dataSetFormUrl}" role="button">Create a new dataset</a>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr class="title-table">
                <th>Theme</th>
                <th>Name</th>
                <th>Producer</th>
                <th>Last modified</th>
                <th>Graphic</th>
                <th>Cartography</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${datasets}" var="dataset" varStatus="loop">
                <c:url var="visuHome" value="/visualisation/home?datasetId=${dataset._id}&dataType=1"/>
                <tr onclick="window.location.href = '${visuHome}';">
                    <td>${dataset.theme}</td>
                    <td>${dataset.name}</td>
                    <td>${dataset.owner}</td>
                    <td><fmt:formatDate value="${dataset.lastModified}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td>${dataset.isGraphic}</td>
                    <td>${dataset.isCarto}</td>
                    <c:url var="dataSetUrl" value="/dataSet/details?datasetId=${dataset._id}"/>
                    <td>
                        <form action="${dataSetUrl}" method="post">
                            <input type="submit" class="submit-tab" value="Details"/>
                        </form>
                    </td>
                    <td>
                        <c:url var="deleteUrl" value="/dataSet/delete?datasetId=${dataset._id}"/>
                        <form action="${deleteUrl}" method="post">
                            <input type="submit" class="submit-tab" value="Delete"/>
                        </form>
                        <c:url var="updateUrl" value="/dataSet/updateDatasetForm?datasetId=${dataset._id}"/>
                        <form action="${updateUrl}" method="post">
                            <input type="submit" class="submit-tab" value="Update">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

