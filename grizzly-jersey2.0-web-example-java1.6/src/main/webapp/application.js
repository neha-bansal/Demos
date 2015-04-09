var count = 0;
var websocket = null;
var name  = null;

var app = {
    url: 'ws://localhost:1111/chat',
    initialize: function() {
    	app.listen();
        if ("WebSocket" in window || "MozWebSocket" in window) {
        } else {
        	document.getElementById('missing-sockets').style.display = 'inherit';
        }
    },
    listen: function() {
    	//document.getElementsById('websockets-frame').src = app.url + '?' + count;
//    	var script = document.createElement('script');
//    	script.src = 'ws://localhost:1111/chat';
//    	document.getElementsByTagName('head')[0].appendChild(script);
        count ++;
    },
    connect : function() {
        var Socket = "MozWebSocket" in window ? MozWebSocket : WebSocket;
        websocket = new WebSocket(app.url);
        websocket.onopen = function() {
            // Web Socket is connected. You can send data by send() method
            websocket.send('connected...');
        };
        websocket.onmessage = function (evt) {
        	if (evt.data) {
                var p = document.createElement('p');
                var ptext = document.createTextNode(evt.data);
                p.appendChild(ptext);
                document.getElementById("myDiv").appendChild(p);
            }
        };
        websocket.onclose = function() {
            var p = document.createElement('p');
            p.innerHTML = 'closed';

            document.getElementById("myDiv").appendChild(p);
        };
    }
};

app.initialize();
//app.connect();
