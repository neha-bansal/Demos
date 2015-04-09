package com.demo.rest.api.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/HelloWorldOther")
public class HelloWorldResource {

	@GET
	public String message () 
	{
		System.out.println("hello");
		return "hello world Other!!!";
	}
	
	@GET
	@Path("{list}")
	public List<HelloWorldDTO> msg () 
	{
		List<HelloWorldDTO> helloWorldDTOList = new ArrayList<HelloWorldDTO>();
		
		HelloWorldDTO helloWorldDTO = new HelloWorldDTO();
		helloWorldDTO.setMsg("hello");
		helloWorldDTOList.add(helloWorldDTO);
		
		helloWorldDTO = new HelloWorldDTO();
		helloWorldDTO.setMsg("welcome");
		helloWorldDTOList.add(helloWorldDTO);
		
		helloWorldDTO = new HelloWorldDTO();
		helloWorldDTO.setMsg("bye");
		helloWorldDTOList.add(helloWorldDTO);
		
		System.out.println(1/0);
		
		return helloWorldDTOList;
	}
}
