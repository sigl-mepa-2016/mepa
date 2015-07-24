var map = null;
var infowindow = null;
var content = null;
var center = new google.maps.LatLng(48.8532, 2.3499); //Centre de paris (Notre-Dame de Paris)
var pointsToDisplay = [];
var markers = [];
var datasetId = null;
var size = null;


function initialize()
{
    $("#map-canvas").remove();
    $("#carto-view").append("<div id='map-canvas' class='map-canvas'></div>");

    datasetId = document.getElementById("data").value;
    size = document.getElementById("size").value;

    clearMarkers();

    var mapOptions = {
        zoom: 12
    };

    map = new google.maps.Map(document.getElementById('map-canvas'),
        mapOptions);

    infowindow = new google.maps.InfoWindow({
        maxWidth : 250
    });

    // HTML5 geolocation
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {

            var pos = new google.maps.LatLng(position.coords.latitude,
                position.coords.longitude);

            map.setCenter(pos);
            content = 'You are here !';

            pointsToDisplay.push({pos : pos, info : content, isGeoLoc : true});

            drawMultipleMarkersOnMap();

        }, function() {
            handleNoGeolocation(true);
            drawMultipleMarkersOnMap();
        });
    } else {
        // Browser doesn't support Geolocation
        handleNoGeolocation(false);
        drawMultipleMarkersOnMap();
    }
}

function handleNoGeolocation(errorFlag) {
    if (errorFlag) {
        content = 'Error: The Geolocation service failed.';
    } else {
        content = 'Error: Your browser doesn\'t support geolocation.';
    }

    map.setCenter(center);
    pointsToDisplay.push({pos : center, info : content, isGeoLoc : true});
}

function drawMultipleMarkersOnMap() {

    var dataJson = null;

    $.ajax({
        dataType: "json",
        url : "/mepa-front/api/dataSet/" + datasetId + "/data.json",
        success : function(data)
        {
            dataJson = data;
        },
        async : false});

    for (var i = 0; i <= size; i++)
    {
        var info = "";

        pointsToDisplay.push({pos : new google.maps.LatLng(parseFloat(dataJson.data.latitude[i]),
            parseFloat(dataJson.data.longitude[i])), info : info, isGeoLoc : false});
    }

    for (var j = 0; j < pointsToDisplay.length; j++) {
        addMarker(pointsToDisplay[j].pos, pointsToDisplay[j].info, pointsToDisplay[j].isGeoLoc);
    }

    var mc = new MarkerClusterer(map, markers);

    google.maps.event.addListener(map, 'click', function() {
        infowindow.close(map, this);
    });
}


function addMarker(position, content, isGeoLoc) {

    var marker = null;

    if (isGeoLoc == false)
    {
        marker = new google.maps.Marker({
            position: position,
            map: map
        });
    }
    else
    {
        marker = new google.maps.Marker({
            position: position,
            map: map,
            icon: "http://maps.google.com/mapfiles/ms/icons/green-dot.png"
        });
    }
    google.maps.event.addListener(marker, 'click', function () {
        infowindow.setContent(content);
        infowindow.open(map, this);
    });

    markers.push(marker);
}


function clearMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}


function isCartTabActive()
{
    if ($('#carto-tab').attr('class') == 'active')
        initialize();

    else
        setTimeout(isCartTabActive, 500);
}

google.maps.event.addDomListener(window, 'load', isCartTabActive);