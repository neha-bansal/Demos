package com.maven.demo;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class App1Test {

	
	@Before
	public void setUp() {
		System.out.println("setup method");
	}
	
	@Test
	public void testmethod3() {
		System.out.println("testmethod3 Demo test class for maven build tool");
		Assert.assertEquals(true, true ); 
		
		org.junit.Assert.assertEquals("test failed...", true, true);
	}
	
	@Test
	public void testmethod4() {
		System.out.println("testmethod4 Demo test class for maven build tool");
		Assert.assertEquals(true, true ); 
		
		org.junit.Assert.assertEquals("test failed...", true, true);
	}
	
	@After
	public void tearDown() {
		System.out.println("tearDpwn method called");
	}

}
