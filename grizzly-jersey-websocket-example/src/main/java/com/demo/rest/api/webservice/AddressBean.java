package com.demo.rest.api.webservice;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddressBean {

	private String street;
	private String city;
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public AddressBean () {
		
	}
	
	public AddressBean(String street, String city) {
		this.street = street;
		this.city = city;
	}
}
