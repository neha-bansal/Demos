package com.demo.rest.api.webservice;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

@Provider
public class MyObjectMapper implements ContextResolver<ObjectMapper> {

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return createDefaultObjectMapper();
	}

	private ObjectMapper createDefaultObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		mapper.enable(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS);
		return mapper;
	}
}
