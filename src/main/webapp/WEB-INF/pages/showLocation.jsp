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
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA23uWRpiaM0b4tS1BLI3YXVHWrE9Q9_xQ&sensor=false"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript">
        var url = "/api/position/" + "${gpsId}";

		initMap('', true);
    </script>
  </head>
  <body>
    <div id="map-canvas"/>
  </body>
</html>
