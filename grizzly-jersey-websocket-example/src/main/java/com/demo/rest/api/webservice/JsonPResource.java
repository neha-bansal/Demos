/*Resource to test JSONP functionality. Just need to add moxy or jackson for this.*/

package com.demo.rest.api.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.JSONP;

@Path("/JsonPResource")
public class JsonPResource {

	private static List<AddressBean> addressBeanList = new ArrayList<AddressBean>();
	
	static {
		addressBeanList.add(new AddressBean("Mount Prospect Avenue", "Clontarf"));
		addressBeanList.add(new AddressBean("Corrig Avenue", "Dun Laoughaire"));
		addressBeanList.add(new AddressBean("Ankers Bowers", "Athlone"));
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, "application/javascript"})
	@JSONP
	public List<AddressBean> getAddressBeanList() {
		
		return addressBeanList;
	}
	
	@Path("/latest")
	@GET
	@Produces({MediaType.APPLICATION_JSON, "application/javascript"})
	@JSONP(callback="javascript")
	public AddressBean getLatestAddressBean() {
		
		return addressBeanList.get(addressBeanList.size() - 1);
	}
}
