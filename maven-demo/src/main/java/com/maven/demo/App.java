package com.maven.demo;

import java.util.ArrayList;
import java.util.List;

public class App {

	public static void main(String[] args) {
		System.out.println("Demo project for maven build tool");
		List<String> list = new ArrayList<String>();
		
		list.add("hello");
		
		System.out.println(list.get(0)); 
	}

}
