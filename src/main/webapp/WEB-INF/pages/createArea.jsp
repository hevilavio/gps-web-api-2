<!DOCTYPE html>

<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      html { height: 100% }
      body {
        height: 100%;
        margin: 30px 0 0px 100px;
        padding: 0;
        font-size: 12px;
      }

      #map-canvas {
            width: 900px;
            height: 600px;
       }
       #create-polygon {
            margin: 0 0 10px 0;
       }
       #titulo{
            font-size: 14px;
       }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA23uWRpiaM0b4tS1BLI3YXVHWrE9Q9_xQ&sensor=false"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript">

        var arrayMarker = [];
        var polygon;

        initMap(addMarker, false);

        function addMarker(lat, lng) {
            var latLng = new google.maps.LatLng(lat, lng);
            var marker = createMarker(latLng, "Marker " + (arrayMarker.length + 1));

            arrayMarker.push(marker);

            logger("addMarker, lat=" + lat + ", lng=" + lng);
        }

        $(document).ready(function(){

            $("#create-polygon").click(function(){
                createPolygonUsingMarkers();

                logger("#create-polygon");
            });

            $("#clear-polygon").click(function(){

                if(polygon != undefined){
                    polygon.setMap(null);
                }
                clearMarkers(arrayMarker);
                arrayMarker = [];
                logger("#clear-polygon");
            });

            $("#save-polygon").click(function (){
                var obj = {
                    positions: toAnotherArray(arrayMarker, function(lat, lng) { return { latitude: lat, longitude: lng } })
                };

                var json = JSON.stringify(obj);
                logger(json);

                $.ajax({
                    type: 'POST',
                    url: '/api/area',
                    contentType: 'application/json',
                    data: json,
                    success: function(xhr){
                        alert('Area salva com sucesso');
                        location.reload();
                    },
                    error: function(xhr){
                        alert('Erro ao salvar Area. \nerror=' + xhr.statusText);
                        location.reload();
                    }
                });
            });

            loadCurrentArea();
        });

        function loadCurrentArea(){

            $.get('/api/area', function(area){

                for(var i = 0; i < area.positions.length; i++){
                    var position = area.positions[i];
                    addMarker(position.latitude, position.longitude);
                }
                createPolygonUsingMarkers();

                // TODO - fixme. O ideal � que isso viesse no objeto
                //var middle = Math.round(area.positions.length/2);
                //var center = area.positions[middle];
                //map.setCenter(new google.maps.LatLng(center.latitude, center.longitude));

            }).fail(function(data){
                alert("Erro ao buscar Area atual.\nerror=" + data.statusText);
            });
        }

        function createPolygonUsingMarkers(){
            polygon = new google.maps.Polygon({
                paths: toAnotherArray(arrayMarker, function(lat, lng) { return new google.maps.LatLng(lat, lng) }),
                strokeColor: '#FF0000',
                strokeOpacity: 0.8,
                strokeWeight: 3,
                fillColor: '#FF0000',
                fillOpacity: 0.35
            });

            polygon.setMap(map);
        }
    </script>
  </head>
  <body>
    <div>
        <p id="titulo"><b>Criar uma �rea</b></p>

        <p><b>Instru��es:</b></p>
        <p>1 - Com um duplo clique, marque os pontos no mapa para demarcar uma �rea.</p>
        <p>2 - Clique em 'create', para criar e visualizar a �rea que voc� acabou de demarcar.</p>
        <p>3 - Se estiver tudo OK, clique em 'save'. Se n�o, clique em 'clear' e volte ao passo 1.</p>
        <p>4 - Pronto, a �rea foi salva e j� est� ativa!</p>
    </div>

    <input type="button" id="create-polygon" value="create"></input>
    <input type="button" id="clear-polygon" value="clear"></input>
    <input type="button" id="save-polygon" value="save"></input>

    <div id="map-canvas"/>
  </body>
</html>
