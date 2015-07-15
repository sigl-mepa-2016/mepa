<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<header>
    <div class="container">
        <div class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">MEPA</a>
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
                        <li><a href="${dataVisualisationTab}">Data Visualisation Tabulaire</a></li>
                        <c:url var="coreExampleUrl" value="/example/core/"/>
                        <li><a href="${coreExampleUrl}">Core module example</a></li>
                        <c:url var="dataSet" value="/dataSet/"/>
                        <li><a href="${dataSet}">Dataset</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>