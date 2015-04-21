package com.demo.rest.api;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

import org.codehaus.jackson.jaxrs.JsonMappingExceptionMapper;
import org.codehaus.jackson.jaxrs.JsonParseExceptionMapper;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.websockets.WebSocketAddOn;
import org.glassfish.grizzly.websockets.WebSocketApplication;
import org.glassfish.grizzly.websockets.WebSocketEngine;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.grizzly2.servlet.GrizzlyWebContainerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
//import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class DemoRestWithGrizzly {

	private static WebSocketApplication app;

	//for websocket functionality, the websocket addon needs to be added to not-yet-started httpserver.
	//below are the different ways to support rest and websocket using jersey and grizzly
	
	public static void main(String[] args) {
		
//		MoxyJsonConfig jsonConfig = new MoxyJsonConfig();
//		HashMap<String, String> namespacePrefixMapper = new HashMap<String, String>();
//		namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
//		jsonConfig.setNamespacePrefixMapper(namespacePrefixMapper).setNamespaceSeparator(':');
		
		ResourceConfig config = new ResourceConfig();
		config.packages("com.demo.rest.api.webservice")
		
			.register(JacksonFeature.class); // need to register this feature if rest output needs to be produced in json format.
		
//			.register(JsonMappingExceptionMapper.class) //for mapping json related exception such as malformed json in request(already registered in jersey2.17)
		
//			.register(JsonParseExceptionMapper.class); //for mapping json related exception such as malformed json in request(already registered in jersey2.17)
		
		URI uri = URI.create("http://localhost:1111/");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config, false);
		
		server.getListener("grizzly").registerAddOn(new WebSocketAddOn());
		IndicationApplication app = new IndicationApplication();
		WebSocketEngine.getEngine().register("/websocket", "/indication", app);
		
		try {
			server.start();
			
			System.in.read();
			
			server.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	

//	public static void main(String[] args) {
//		WebappContext context = new WebappContext("grizzly-jersey-websocket-example", "");
//		ServletRegistration reg = context.addServlet("jersey", ServletContainer.class);
//		reg.addMapping("/rest/*");
//		reg.setInitParameter("jersey.config.server.provider.packages", "com.demo.rest.api.webservice");
//		
//		HttpServer server = HttpServer.createSimpleServer("", 1111);
//		
//		server.getListener("grizzly").registerAddOn(new WebSocketAddOn());
//		IndicationApplication app = new IndicationApplication();
//		WebSocketEngine.getEngine().register("/websocket", "/indication", app);
//		
//		context.deploy(server);
//		try {
//			server.start();
//			
//			System.in.read();
//			
//			server.stop();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}	
	
	
// rest and websocket on different servers.	
//	public static void main(String[] args) {//throws IllegalArgumentException, IOException {
//		//URI baseUri = UriBuilder.fromUri(arg0);
//		try {
//		String uri = "http://localhost:2222/";
//		Map<String, String> initParams = new HashMap<String, String>();
//		//initParams.put("com.sun.jersey.config.property.packages", "com.demo.rest.api.webservice");
//		initParams.put("jersey.config.server.provider.packages", "com.demo.rest.api.webservice");
//
//		
//		System.out.println("Starting grizzly...");
//		
//		HttpServer selectorThread = GrizzlyWebContainerFactory.create(uri, ServletContainer.class, initParams);
//		
//		final HttpServer server = HttpServer.createSimpleServer("", 1111);
//		
//		server.getListener("grizzly").registerAddOn(new WebSocketAddOn());
//		
//		IndicationApplication app = new IndicationApplication();
//		
//		WebSocketEngine.getEngine().register("/websocket", "/indication", app);
//		server.start();
//
//		System.in.read();
//		
//		selectorThread.stop();
//		server.stop();
//		System.out.println("Stopping grizzly...");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			WebSocketEngine.getEngine().unregister(app);
//		}
//		
//	}

}
