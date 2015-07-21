<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<head>

    <c:url var="mapCssUrl" value="/css/map.css" />
    <link rel="stylesheet" href="${mapCssUrl}" type="text/css" />

    <c:url var="mapJsUrl" value="/js/map.js" />
    <script src="${mapJsUrl}" type="application/javascript" ></script>

</head>
<body>
<div class="container">
    <div class="page-header">
        <h1 class="white">Want to see your localization ?</h1>
    </div>
    <div id="map-canvas" class="canvas"></div>
</div>
</body>