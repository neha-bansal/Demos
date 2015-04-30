package com.maven.demo;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

	
	@Before
	public void setUp() {
		System.out.println("setup method");
	}
	
	@Test
	public void testmethod() {
		System.out.println("Demo test class for maven build tool");
		Assert.assertEquals(true, false ); 
		
		org.junit.Assert.assertEquals("test failed...", true, false);
	}
	
	@After
	public void tearDown() {
		System.out.println("tearDpwn method called");
	}

}
