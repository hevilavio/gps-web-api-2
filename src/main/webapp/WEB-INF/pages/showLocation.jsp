<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map-canvas { height: 100% }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA23uWRpiaM0b4tS1BLI3YXVHWrE9Q9_xQ&sensor=false">
    </script>
	<script type="text/javascript">
		var map;
		var gpsId = 0;
		var posX = -23.519697;
		var posY = -46.835308;
		var markers = [];
		var url = "http://ip-api.com/json";

		var logger = function(msg){
			console.log(msg);
		};
		function initialize() {

			var mapOptions = {
				center: new google.maps.LatLng(posX, posY),
				zoom: 16,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};

			map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
			initPositionListener();
		}

		function initPositionListener(){
			setInterval(updatePosition, 2000);
		}

		function updatePosition(){
			var currentPosition = getCurrentPosition();

			var current = new google.maps.Marker({
				position: currentPosition,
				map: map,
				title: 'Now, current is here!'
			});

			controlMarkers(current, true);
			controlCenterMap(current);
		}
		function controlMarkers(marker, removeOthers){

			// remove do mapa Makers antigos
			if(removeOthers){
				for (var i = 0; i < markers.length; i++) {
			    	markers[i].setMap(null);
			  	}
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

		function getCurrentPosition(){
			logger("M=getCurrentPosition, posX=" + posX + ", posY=" + posY);
			
			$.get(url, function(data){
				posX = data.lat;
				posY = data.lon;
			});		

			return new google.maps.LatLng(posX, posY);
		}
		google.maps.event.addDomListener(window, 'load', initialize);
    </script>
  </head>
  <body>
    <div id="map-canvas"/>
  </body>
</html>
