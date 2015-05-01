<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map-canvas {
        border: 1px solid #000000;
        height: 100%;
      }
      #btn-lock{
        margin: 20px 0px 20px 20px;
      }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA23uWRpiaM0b4tS1BLI3YXVHWrE9Q9_xQ&sensor=false"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript">
        var url = "/api/position/" + "${gpsId}";
        var locked = false;

		initMap('', true);

		$(document).ready(function(){
		    $("#btn-lock").click(function(){
                if(locked){
                   $("#btn-lock").attr("value", "Travar");
                }else{
                  $("#btn-lock").attr("value", "Destravar");
		        }
		        locked = !locked;
		    });

		});
    </script>
  </head>
  <body>
    <input type="button" id="btn-lock" value="Travar" />
    <div id="map-canvas"/>

  </body>
</html>
