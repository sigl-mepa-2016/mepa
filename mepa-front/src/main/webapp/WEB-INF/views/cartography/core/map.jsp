<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<head>
    <c:url var="mapCssUrl" value="/css/map.css" />
    <c:url var="mapJsUrl" value="/js/map.js" />

    <link rel="stylesheet" href="${mapCssUrl}" type="text/css" />
    <script src="${mapJsUrl}"></script>

</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Want to see your localization ?</h1>
    </div>
    <div id="map-canvas" class="canvas"></div>
</div>
</body>