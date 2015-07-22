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
                        <c:url var="dataVisualisation" value="/dataVisualisation/"/>
                        <li><a href="${dataVisualisation}">Data Visualisation</a></li>
                        <c:url var="cartographyUrl" value="/cartography/core/map"/>
                        <li><a href="${cartographyUrl}">Cartography module</a></li>
                        <c:url var="dataVisualisationTab" value="/dataVisualisationTab/"/>
                        <li><a href="${dataVisualisationTab}">Tableau</a></li>
                        <c:url var="api" value="/api/"/>
                        <li><a href="${api}">API</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>