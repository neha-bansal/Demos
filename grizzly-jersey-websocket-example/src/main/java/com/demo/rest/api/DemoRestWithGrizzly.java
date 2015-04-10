package com.demo.rest.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.websockets.WebSocketAddOn;
import org.glassfish.grizzly.websockets.WebSocketApplication;
import org.glassfish.grizzly.websockets.WebSocketEngine;
//import org.glassfish.grizzly.websockets.WebSocketAddOn;
//import org.glassfish.grizzly.websockets.WebSocketEngine;
import org.glassfish.jersey.grizzly2.servlet.GrizzlyWebContainerFactory;
import org.glassfish.jersey.servlet.ServletContainer;

public class DemoRestWithGrizzly {

	private static WebSocketApplication app;

	public static void main(String[] args) {//throws IllegalArgumentException, IOException {
		//URI baseUri = UriBuilder.fromUri(arg0);
		try {
		String uri = "http://localhost:2222/";
		Map<String, String> initParams = new HashMap<String, String>();
		//initParams.put("com.sun.jersey.config.property.packages", "com.demo.rest.api.webservice");
		initParams.put("jersey.config.server.provider.packages", "com.demo.rest.api.webservice");

		
		System.out.println("Starting grizzly...");
		
		HttpServer selectorThread = GrizzlyWebContainerFactory.create(uri, ServletContainer.class, initParams);
		
		final HttpServer server = HttpServer.createSimpleServer("", 1111);
		
		server.getListener("grizzly").registerAddOn(new WebSocketAddOn());
		
		IndicationApplication app = new IndicationApplication();
		
		WebSocketEngine.getEngine().register("/websocket", "/indication", app);
		server.start();

		System.in.read();
		
		selectorThread.stop();
		server.stop();
		System.out.println("Stopping grizzly...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			WebSocketEngine.getEngine().unregister(app);
		}
		
	}

}
