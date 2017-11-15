package com.beifengioc.test;

public class HelloWorldImpl implements HelloWorld {

	@Override
	public String sayHello(String name) {
		return "hello "+name;
	}

}
