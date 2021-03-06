package com.demo.rest.api;

import org.glassfish.grizzly.http.HttpRequestPacket;
import org.glassfish.grizzly.websockets.DataFrame;
import org.glassfish.grizzly.websockets.ProtocolHandler;
import org.glassfish.grizzly.websockets.WebSocket;
import org.glassfish.grizzly.websockets.WebSocketApplication;
import org.glassfish.grizzly.websockets.WebSocketListener;

public class IndicationApplication extends WebSocketApplication {

	public void onMessage(WebSocket websocket, String frame) {
		System.out.println("onMessage called.....with " + frame);
		websocket.send("server send : " + frame + "received");
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		websocket.send("server send : " + frame + "received again");
	}
	
	@Override
	public WebSocket createSocket(ProtocolHandler handler,
			HttpRequestPacket requestPacket, WebSocketListener... listeners) {
		System.out.println("createWebSocket called.....");
		return new IndicationWebSocket(handler, requestPacket, listeners);
	}

//	@Override
//	protected IndicationWebSocket createWebSocket(Connection connection,
//			ServerWebSocketMeta meta) {
//		System.out.println("createWebSocket called.....");
//		return new IndicationWebSocket(connection, meta, null);
//	}

	@Override
	public boolean isApplicationRequest(HttpRequestPacket arg0) {
		System.out.println("isApplicationRequest called.....");
		return true;
	}
	
	@Override
    public void onConnect(WebSocket socket) {
		System.out.println("onConnect called.....");
    }
	
	@Override
    public void onClose(WebSocket websocket, DataFrame frame) {
		System.out.println("onClose called.....");
    }
}
