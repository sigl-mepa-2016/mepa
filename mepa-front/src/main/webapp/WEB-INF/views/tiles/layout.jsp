<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<html>
    <head>
        <%-- Page title --%>
        <c:set var="titleKey"><tiles:insertAttribute name="title"/></c:set>
        <title><fmt:message key="${titleKey}"/></title>

        <%-- Google Maps API --%>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>

        <%-- Google Marker Clusterer API --%>
        <script src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/src/markerclusterer.js"></script>

        <%-- Bootstrap CSS --%>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.5/yeti/bootstrap.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css" />

        <%-- Application CSS --%>
        <c:url var="defaultCssUrl" value="/css/default.css" />
        <link rel="stylesheet" href="${defaultCssUrl}" type="text/css" />

        <c:url var="colpickCssUrl" value="/css/colpick.css" />
        <link rel="stylesheet" href="${colpickCssUrl}" type="text/css" />

        <%-- jQuery --%>
        <script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
        <%-- Bootstrap JavaScript --%>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <%-- ChartJS JavaScript --%>
        <script src="https://www.google.com/jsapi"></script>

        <%-- prettify JavaScript --%>
        <script src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"></script>

        <%-- Table Sort JavaScript --%>
        <c:url var="gridJsUrl" value="/js/grid.js"/>
        <script src="${gridJsUrl}"></script>
    </head>

    <!-- Define the background image -->
    <c:url var="headerURL" value="/img/header.jpg"/>
    <body background="${headerURL}" style="background-repeat:no-repeat; background-attachment:fixed; background-position:center">

        <%-- Header --%>
        <tiles:insertAttribute name="header" />
        <%-- Body content --%>
        <tiles:insertAttribute name="body" />
        <%-- Footer --%>
        <tiles:insertAttribute name="footer" />
    </body>
</html>