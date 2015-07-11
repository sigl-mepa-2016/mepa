<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<html>
    <head>
        <%-- Page title --%>
        <c:set var="titleKey"><tiles:insertAttribute name="title"/></c:set>
        <title><fmt:message key="${titleKey}"/></title>

        <%-- Google Maps API --%>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>

        <%-- Bootstrap CSS --%>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css" />

        <%-- Application CSS --%>
        <c:url var="defaultCssUrl" value="/css/default.css" />
        <link rel="stylesheet" href="${defaultCssUrl}" type="text/css" />

        <%-- jQuery --%>
        <script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
        <%-- Bootstrap JavaScript --%>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    </head>
    <body>
        <%-- Header --%>
        <tiles:insertAttribute name="header" />
        <%-- Body content --%>
        <tiles:insertAttribute name="body" />
        <%-- Footer --%>
        <tiles:insertAttribute name="footer" />
    </body>
</html>