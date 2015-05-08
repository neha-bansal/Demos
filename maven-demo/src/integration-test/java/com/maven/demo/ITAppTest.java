package com.maven.demo;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ITAppTest {

	
//	@Before
//	public void setUp() {
//		System.out.println("ITAppTest setup method");
//	}
	
	@Test
	public void testmethod() {
		System.out.println("ITAppTest testmethod Demo test class for maven build tool");
		Assert.assertEquals(true, true ); 
		
		org.junit.Assert.assertEquals("test failed...", true, true);
	}
	
	@Test
	public void testmethod1() {
		System.out.println("ITAppTest testmethod1 Demo test class for maven build tool");
		Assert.assertEquals(true, true ); 
		
		org.junit.Assert.assertEquals("test failed...", true, true);
	}
	
//	@After
//	public void tearDown() {
//		System.out.println("tearDpwn method called");
//	}

}
