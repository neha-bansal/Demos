package com.demo.rest.api.webservice;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * This class is used for the cross domain access. It adds the headers to the response object before it gets back to the client.
 * @author NBansal
 */
@Provider
public class CORSResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		 MultivaluedMap<String, Object> headers = responseContext.getHeaders();

		 //headers.add("Access-Control-Allow-Origin", "*"); 
		 
		 headers.add("Access-Control-Allow-Origin", "http://localhost:8080");
			//headers.add("Access-Control-Allow-Origin", "http://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org		
			//headers.add("Access-Control-Allow-Methods", "POST");			
			headers.add("Access-Control-Allow-Headers", "Content-Type");
			//headers.add("Access-Control-Allow-Credentials", true);

	}

}
