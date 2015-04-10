package com.grizzly.jersey.chunked.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ChunkedInput;

public class ProxyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("doGet called  : ");
		URL obj = new URL(req.getHeader("domain-url"));
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		
		System.out.println("Connection created...");

		Enumeration<String> headerNames = req.getHeaderNames();
		while (headerNames.hasMoreElements()) { 
			String header = headerNames.nextElement();
			connection.setRequestProperty(header, req.getHeader(header));
		}
		connection.setRequestMethod("GET");
		int responseCode = connection.getResponseCode();
		System.out.println("\nSending 'GET' request to URL :" + obj.toString());
		System.out.println("Response Code : " + responseCode);
		
		resp.setStatus(responseCode);
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String responseLine;
		while ((responseLine = br.readLine()) != null) {
			System.out.println("Next chunk received: " + responseLine);
			resp.getWriter().write(responseLine); 
			resp.getWriter().flush();
		}
		br.close();
		
	}
	
	
	protected void doGet1(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("doGet called  : ");
		final Response response = ClientBuilder.newClient().target(req.getHeader("domain-url")).request().get();
		final ChunkedInput<String> chunkedInput =
		        response.readEntity(new GenericType<ChunkedInput<String>>() {});
		String chunk;
		while ((chunk = chunkedInput.read()) != null) {
		    System.out.println("Next chunk received: " + chunk);
		    resp.getWriter().write(chunk);
		    resp.flushBuffer();
		}
	}

		

}
