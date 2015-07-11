<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<head>

    <c:url var="mapCssUrl" value="/css/map.css" />
    <c:url var="mapJsUrl" value="/js/map.js" />

    <link rel="stylesheet" href="${mapCssUrl}" type="text/css" />
    <script src="${mapJsUrl}"></script>

</head>
<body>
<div id="map-canvas" class="canvas"></div>
</body>