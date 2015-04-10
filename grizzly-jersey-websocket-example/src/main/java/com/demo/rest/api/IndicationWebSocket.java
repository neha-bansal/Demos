package com.demo.rest.api;

import org.glassfish.grizzly.http.HttpRequestPacket;
import org.glassfish.grizzly.websockets.DefaultWebSocket;
import org.glassfish.grizzly.websockets.ProtocolHandler;
import org.glassfish.grizzly.websockets.WebSocketListener;

public class IndicationWebSocket extends DefaultWebSocket {

	public IndicationWebSocket(ProtocolHandler handler,
			HttpRequestPacket requestPacket, WebSocketListener... listeners) {
		super(handler, requestPacket, listeners);
	}

	
//	public IndicationWebSocket(Connection connection, WebSocketMeta meta,
//			WebSocketHandler<? extends WebSocketBase> handler) {
//		super(connection, meta, handler);
//	}


}
