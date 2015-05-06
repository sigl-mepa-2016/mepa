<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<html>
    <head>
        <%-- Page title --%>
        <c:set var="titleKey"><tiles:insertAttribute name="title"/></c:set>
        <title><fmt:message key="${titleKey}"/></title>

        <%-- Bootstrap CSS --%>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" />
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css" />

        <%-- Application CSS --%>
        <c:url var="defaultCssUrl" value="/css/default.css" />
        <link rel="stylesheet" href="${defaultCssUrl}" type="text/css" />

        <%-- jQuery --%>
        <script src="//code.jquery.com/jquery-2.1.1.min.js"></script>
        <%-- Bootstrap JavaScript --%>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
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