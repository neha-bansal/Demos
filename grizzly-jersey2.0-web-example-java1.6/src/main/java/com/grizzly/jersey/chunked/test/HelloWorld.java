package com.grizzly.jersey.chunked.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/HelloWorld")
public class HelloWorld {

	@GET
	public String message () 
	{
		return "hello world!!!";
	}
}
