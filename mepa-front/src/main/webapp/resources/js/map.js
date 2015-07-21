var map;
var infowindow;
var content;
var latitude;
var longitude;
var pointsToDisplay = [];
var googleMapPoints = [];
var markers = [];
var center;


function initialize() {
    var mapOptions = {
        zoom: 15
    };
    map = new google.maps.Map(document.getElementById('map-canvas'),
        mapOptions);

    infowindow = new google.maps.InfoWindow({
        maxWidth : 250
    });

    // HTML5 geolocation
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {

            latitude = position.coords.latitude;
            longitude = position.coords.longitude;
            var pos = new google.maps.LatLng(position.coords.latitude,
                position.coords.longitude);

            var marker = new google.maps.Marker({
                map: map,
                position: pos
            });

            map.setCenter(pos);
            content = 'You are here !';

            google.maps.event.addListener(marker, 'click', function() {
                infowindow.setContent(content);
                infowindow.open(map, this);
            });

            markers.push(marker);

        }, function() {
            handleNoGeolocation(true);
        });
    } else {
        // Browser doesn't support Geolocation
        handleNoGeolocation(false);
    }

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

    latitude = 48.8586;
    longitude = 2.2945;

    var options = {
        map: map,
        position: new google.maps.LatLng(latitude, longitude)
    };

    var marker = new google.maps.Marker(options);
    map.setCenter(options.position);

    google.maps.event.addListener(marker, 'click', function () {
        infowindow.setContent(content);
        infowindow.open(map, this);

    });
}

function generatePoints(number)
{
    var latitude = 48.8586;
    var longitude = 2.2945;

    for (var i = 0; i < number; i++)
    {
        if ((i % 2) == 0)
        {
            latitude += ((Math.random() / 1000) * i);
            longitude += ((Math.random() / 1000) * i);
        }
        else
        {
            latitude -= ((Math.random() / 1000) * i);
            longitude -= ((Math.random() / 1000) * i);
        }

        var info = "test" + i;

        pointsToDisplay.push({latitude : latitude, longitude : longitude, info : info});
    }
}

function initializePoints()
{
    var latitudeCenter = 0;
    var longitudeCenter = 0;
    var pointsNumber = pointsToDisplay.length;

    for (var i = 0; i < pointsNumber; i++)
    {
        var latitude_i = pointsToDisplay[i].latitude;
        var longitude_i = pointsToDisplay[i].longitude;

        googleMapPoints.push(new google.maps.LatLng(latitude_i,
            longitude_i));

        latitudeCenter += latitude_i;
        longitudeCenter += longitude_i;
    }

    center = new google.maps.LatLng((latitudeCenter / pointsNumber),
        longitudeCenter / pointsNumber);
}

function initializeMultiple() {

    generatePoints(1000);
    initializePoints();

    var mapOptions = {
        zoom: 2,
        center: center
    };

    map = new google.maps.Map(document.getElementById('map-canvas'),
        mapOptions);

    infowindow = new google.maps.InfoWindow({
        maxWidth : 250
    });

    clearMarkers();
    for (var i = 0; i < googleMapPoints.length; i++) {
        addMarker(googleMapPoints[i], pointsToDisplay[i].info);
    }

    var mc = new MarkerClusterer(map, markers);
    google.maps.event.addListener(map, 'click', function() {
        infowindow.close(map, this);
    });
}

function addMarker(position, content) {

    var marker = new google.maps.Marker({
        position: position,
        map: map
    });

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

//google.maps.event.addDomListener(window, 'load', initialize);
google.maps.event.addDomListener(window, 'load', initializeMultiple);
