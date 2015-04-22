/*do not understand it's use*/

package com.demo.rest.api.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

@Path("LowLevelXmlResource")
public class LowLevelXmlResource {
	@POST
	@Path("StreamSource")
	public StreamSource getStreamSource(StreamSource streamSource) {
	    return streamSource;
	}
	 
	@POST
	@Path("SAXSource")
	public SAXSource getSAXSource(SAXSource saxSource) {
	    return saxSource;
	}
	 
	@POST
	@Path("DOMSource")
	public DOMSource getDOMSource(DOMSource domSource) {
	    return domSource;
	}
	 
	@POST
	@Path("Document")
	public Document getDocument(Document document) {
	    return document;
	}
}

