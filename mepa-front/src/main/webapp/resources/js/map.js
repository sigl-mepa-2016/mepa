var map;
var infowindow;
var content;
var latitude;
var longitude;
var pointsToDisplay = [];
var googleMapPoints = [];
var markers = [];
var center = new google.maps.LatLng(48.8532, 2.3499); //Centre de paris (Notre-Dame de Paris)


function initialize() {

    var datasetId = document.getElementById("data").value;
    var size = document.getElementById("size").value;
    var dataJson = null;

    var mapOptions = {
        zoom: 10,
        center: center
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
            content = 'You are here !';

            pointsToDisplay.push({pos : pos, info : content, isGeoLoc : true});

        }, function() {
            handleNoGeolocation(true);
        });
    } else {
        // Browser doesn't support Geolocation
        handleNoGeolocation(false);
    }

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

    for (var i = 0; i < googleMapPoints.length; i++) {
        addMarker(googleMapPoints[i].pos, pointsToDisplay[i].info, pointsToDisplay[i].isGeoLoc);
    }

    var mc = new MarkerClusterer(map, markers);

    google.maps.event.addListener(map, 'click', function() {
        infowindow.close(map, this);
    });
}


function handleNoGeolocation(errorFlag) {
    if (errorFlag) {
        content = 'Error: The Geolocation service failed.';
    } else {
        content = 'Error: Your browser doesn\'t support geolocation.';
    }

    pointsToDisplay.push({pos : center, info : content, isGeoLoc : true});
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


google.maps.event.addDomListener(window, 'load', initialize);