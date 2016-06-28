package com.demo.rest.api.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

//these annotations are used for generating the rest output in xml format.
@XmlRootElement(name="HelloWorldDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class HelloWorldDTO {

	private String msg;
	
	public String[] emtpyArray = new String[0];

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
