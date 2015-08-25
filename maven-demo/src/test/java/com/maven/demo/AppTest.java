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
		System.out.println("testmethod Demo test class for maven build tool");
		Assert.assertEquals(true, true ); 
		
		org.junit.Assert.assertEquals("test failed...", true, true);
	}
	
	@Test
	public void testmethod2() {
		System.out.println("testmethod2 Demo test class for maven build tool");
		Assert.assertEquals(true, true ); 
		
		org.junit.Assert.assertEquals("test failed...", true, true);
	}
	
	@Test
	public void testmethod5() {
		System.out.println("testmethod5 Demo test class for maven build tool");
		Assert.assertEquals(true, true ); 
		
		org.junit.Assert.assertEquals("test failed...", true, true);
	}
	
	@After
	public void tearDown() {
		System.out.println("tearDpwn method called");
	}

}
