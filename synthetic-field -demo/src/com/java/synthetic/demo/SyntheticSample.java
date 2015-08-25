package com.java.synthetic.demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SyntheticSample {

	public static void main(String args[]) {

		SampleNestedClass nestObj = new SampleNestedClass();
		System.out.println("Nested Variable: " + nestObj.aPrivateVariable);
		Double d = nestObj.d; // access$?
		char ch = nestObj.ch; // access$?
		String str = nestObj.str; // access$?

		Class c = nestObj.getClass();
		Method[] methods = c.getDeclaredMethods();

		for (Method m : methods) {
			System.out.println("m: " + m + " m.isSynthetic: " + m.isSynthetic());
		}
		System.out.println("===============================================");
		Field[] fields = c.getDeclaredFields();

		for (Field f : fields) {
			System.out.println("f: " + f + " f.isSynthetic: " + f.isSynthetic());
		}
		
		System.out.println("===============================================");
		Field[] innerClassFields = Inner.class.getDeclaredFields();

		for (Field f : innerClassFields) {
			System.out.println("f: " + f + " f.isSynthetic: " + f.isSynthetic());
		}

	}

	private static class SampleNestedClass {
		private String aPrivateVariable = "A Private Variable!";
		
		protected Double d = 1.1; //?
		Character ch = 'c'; // ?
		public String str = "str"; //?
	}
	
	class Inner {
		
	}

}