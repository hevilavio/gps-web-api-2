<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map-canvas { height: 100% }
    </style>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA23uWRpiaM0b4tS1BLI3YXVHWrE9Q9_xQ&sensor=false">
    </script>
    <script type="text/javascript">
		var map;
		var initX = -23.519697;
		var initY = -46.835308;
		
		var logger = function(msg){
			console.log(msg);
		};
		function initialize() {

			var mapOptions = {
				center: new google.maps.LatLng(initX, initY),
				zoom: 15,
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
		}
		
		function getCurrentPosition(){
			initY = initY + 0.0015000;
		
			// TODO - Trocar isto para uma requisição ajax
			logger("initY=" + initY);
			return new google.maps.LatLng(initX, initY);
		}
		google.maps.event.addDomListener(window, 'load', initialize);
    </script>
  </head>
  <body>
    <div id="map-canvas"/>
  </body>
</html>
