<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<head>

    <style>
        .canvas {
            height: 70%;
            width: 80%;
            margin: auto;
            padding: auto;
            margin-bottom: 20px;
        }
    </style>

    <script type="application/javascript">

        var map;
        var latitude;
        var longitude;

        function initialize() {
            var mapOptions = {
                zoom: 15
            };
            map = new google.maps.Map(document.getElementById('map-canvas'),
                    mapOptions);

            // HTML5 geolocation
            if(navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function(position) {

                    latitude = position.coords.latitude;
                    longitude = position.coords.longitude;
                    var pos = new google.maps.LatLng(position.coords.latitude,
                            position.coords.longitude);

                    var infowindow = new google.maps.InfoWindow({
                        map: map,
                        position: pos,
                        content: 'You are here !'
                    });

                    map.setCenter(pos);
                }, function() {
                    handleNoGeolocation(true);
                });
            } else {
                // Browser doesn't support Geolocation
                handleNoGeolocation(false);
            }
        }

        function handleNoGeolocation(errorFlag) {
            if (errorFlag) {
                var content = 'Error: The Geolocation service failed.';
            } else {
                var content = 'Error: Your browser doesn\'t support geolocation.';
            }

            latitude = 48.8586;
            longitude = 2.2945;

            var options = {
                map: map,
                position: new google.maps.LatLng(latitude, longitude),
                content : content
            };

            var infowindow = new google.maps.InfoWindow(options);
            map.setCenter(options.position);
        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>

</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Want to see your localization ?</h1>
    </div>
    <div id="map-canvas" class="canvas"></div>
</div>
</body>