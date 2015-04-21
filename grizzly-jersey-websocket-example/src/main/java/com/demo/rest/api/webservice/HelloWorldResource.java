package com.demo.rest.api.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
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
		
		//System.out.println(1/0);
		
		return helloWorldDTOList;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public HelloWorldDTO save (HelloWorldDTO dto) {
		return dto;
	}
	
	@GET
	@Path("emptyBeanResource")
	public EmptyArrayBean getEmptyArrayBean() {
		return new EmptyArrayBean();
	}
}
