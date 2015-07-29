<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<header>
    <div class="container">
        <div class="navbar navbar-inverse" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/mepa-front/">MEPA</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <c:url var="homeUrl" value="/home"/>
                        <li><a href="${homeUrl}">Home page</a></li>
                        <c:url var="api" value="/api/"/>
                        <li><a href="${api}">API</a></li>
                        <c:choose>
                            <c:when test="${cookie.token != null && cookie.token != ''}">
                                <li></li>
                            </c:when>
                            <c:otherwise>
                                <c:url var="loginUrl" value="/login"/>
                                <li><a href="${loginUrl}">Log in</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                    <c:choose>
                         <c:when test="${cookie.token != null && cookie.token != ''}">
                             <p class="connected" id="userConnectedText">User connected</p>
                         </c:when>
                         <c:otherwise>
                             <p class="connected">User not connected</p>
                         </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</header>