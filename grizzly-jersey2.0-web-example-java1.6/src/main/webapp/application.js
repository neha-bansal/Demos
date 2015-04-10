var count = 0;
var websocket = null;

var app = {
    //url: 'ws://localhost:2222/grizzly-websockets-chat/chat',
    //url: 'ws://localhost:1111/websocket/indication',
    url: 'ws://10.160.161.108:12001/websocket/indication',
    
    initialize: function() {
        if ("WebSocket" in window || "MozWebSocket" in window) {
        	app.listen();
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
        websocket = new Socket(app.url);
        websocket.onopen = function() {
            // Web Socket is connected. You can send data by send() method
            websocket.send('Connected to ' + app.url);
        };
        websocket.onmessage = function (evt) {
        	if (evt.data) {
        		appendMessages(evt.data);
            }
        };
        websocket.onclose = function() {
        	appendMessages('Closed connection from ' + app.url);
        };
    }
    
//    post : function() {
//    	var msg = document.getElementById("message").value;
//    	websocket.send(msg);
//    }
};

function appendMessages(msg) {
	var p = document.createElement('p');
    var ptext = document.createTextNode(msg);
    p.appendChild(ptext);
    document.getElementById("myDiv").appendChild(p);
}

app.initialize();
