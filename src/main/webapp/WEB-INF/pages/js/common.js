var map;

var latitude = -23.519697;
var longitude = -46.835308;
var markers = [];


var whenDblClick;
var usePositionListener;

// Para esta funcao, eh necessario ter um elemento <div> com id = map-canvas
function initMap(dbl, posListener){
    google.maps.event.addDomListener(window, 'load', initialize);

    whenDblClick = (dbl == undefined || typeof dbl != "function") ? nill : dbl;
    usePositionListener = (posListener == undefined) ? false : posListener
}

function initialize() {

    var mapOptions = {
        center: new google.maps.LatLng(latitude, longitude),
        zoom: 16,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        disableDoubleClickZoom: true
    };

    map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

    initPositionListener();
    addDblClickEvent();
}

function initPositionListener(){
    if(usePositionListener){
        setInterval(updatePosition, 5000);
    }
}

function updatePosition(){
    var currentPosition = getCurrentPosition();

    var current = createMarker(currentPosition, "Position X");
    controlMarkers(current, true);

    // botao na View showLocation
    if(locked){
        controlCenterMap(current);
    }
}

function getCurrentPosition(){
    logger("M=getCurrentPosition, latitude=" + latitude + ", longitude=" + longitude);

    $.get(url, function(data){
        latitude = data.latitude;
        longitude = data.longitude;
    }).fail(function(data){
        logger("Erro ao buscar Area atual. error=" + data.statusText);
    });

    return new google.maps.LatLng(latitude, longitude);
}

function controlMarkers(marker, removeOthers){

    // remove do mapa Makers antigos
    if(removeOthers){
        clearMarkers(markers);
    }
    // adiciona o novo no array
    markers.push(marker);
}

function controlCenterMap(marker){

    var latlngbounds = new google.maps.LatLngBounds();
    latlngbounds.extend(marker.position);

    map.setCenter(latlngbounds.getCenter());
    map.fitBounds(latlngbounds);
    map.setZoom(16);
}

function addDblClickEvent(){
    google.maps.event.addListener(map, 'dblclick', function(event) {
        var lat = to6Decimal(event.latLng.lat());
        var lng = to6Decimal(event.latLng.lng());

        logger("dblClickEvent, lat=" + lat + ", lng=" + lng);
        whenDblClick(lat, lng);
    });
}

function createMarker(position, title) {

    return new google.maps.Marker({
        position: position,
        map: map,
        title: title
    });
}

function clearMarkers(markers){
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
}

function toAnotherArray(arrayMarker, transformer){
    var another = [];
    for (var i = 0; i < arrayMarker.length; i++) {
        var marker = arrayMarker[i];
        another.push(
            transformer(
                to6Decimal(marker.position.lat()),
                to6Decimal(marker.position.lng())
            )
        );
    }
    return another;
}

function to6Decimal(number){
    return number.toFixed(6);
}

// Esta funcao nao faz nada, mas eh usada como prevencao de undefined
function nill (){
}

var logger = function(msg){
    console.log(msg);
};
