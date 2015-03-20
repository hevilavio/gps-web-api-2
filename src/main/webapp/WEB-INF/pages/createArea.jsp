<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      html { height: 100% }
      body {
        height: 100%;
        margin: 150px 0 0px 100px;
        padding: 0
      }

      #map-canvas {
            width: 900px;
            height: 600px;
       }
       #create-polygon {
            margin: 0 0 10px 0;
       }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA23uWRpiaM0b4tS1BLI3YXVHWrE9Q9_xQ&sensor=false"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript">

        var arrayMarker = [];

        initMap(processDblClick, false);

        function processDblClick(lat, lng) {
            var latLng = new google.maps.LatLng(lat, lng);
            var marker = createMarker(latLng, "Marker " + (arrayMarker.length + 1));

            arrayMarker.push(marker);

            logger("processDblClick, lat=" + lat + ", lng=" + lng);
        }

        $(document).ready(function(){
            var area;
            $("#create-polygon").click(function(){

                area = new google.maps.Polygon({
                    paths: toAnotherArray(arrayMarker, function(lat, lng) { return new google.maps.LatLng(lat, lng) }),
                    strokeColor: '#FF0000',
                    strokeOpacity: 0.8,
                    strokeWeight: 3,
                    fillColor: '#FF0000',
                    fillOpacity: 0.35
                  });

                area.setMap(map);

                logger("#create-polygon");
            });

            $("#clear-polygon").click(function(){

                if(area != undefined){
                    area.setMap(null);
                }
                clearMarkers(arrayMarker);
                arrayMarker = [];
                logger("#clear-polygon");
            });

            $("#save-polygon").click(function (){
                var obj = {
                    area: toAnotherArray(arrayMarker, function(lat, lng) { return { posX: lat, posY: lng } })
                };

                var json = JSON.stringify(obj);

                // fazer o POST
                logger(json);
            });
        });

    </script>
  </head>
  <body>
    <input type="button" id="create-polygon" value="create"></input>
    <input type="button" id="clear-polygon" value="clear"></input>
    <input type="button" id="save-polygon" value="save"></input>

    <div id="map-canvas"/>
  </body>
</html>
