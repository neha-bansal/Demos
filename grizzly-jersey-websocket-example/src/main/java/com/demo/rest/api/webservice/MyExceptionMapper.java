package com.demo.rest.api.webservice;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.JsonMappingException;

//exception mapper to map the json exception according to your needs (can also use JsonMappingExceptionMapper)
@Provider
public class MyExceptionMapper implements ExceptionMapper<JsonMappingException> {

	@Override
	public Response toResponse(JsonMappingException exception) {
		return Response.status(Status.BAD_REQUEST).entity("Exception mapped from MyExceptionMapper" + exception.getMessage()).build();
	}

}
