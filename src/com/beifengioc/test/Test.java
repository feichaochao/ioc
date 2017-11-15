package com.beifengioc.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.beifengioc.xmlinfo.Bean;
import com.beifengioc.xmlinfo.PropertyList;
import com.beifengioc.xmlinfo.PropertyMap;
import com.beifengioc.xmlinfo.PropertyProps;
import com.beifengioc.xmlinfo.PropertySet;
import com.beifengioc.xmlinfo.RefBean;
import com.beifengioc.xmltools.ConstantIOC;
import com.beifengioc.xmltools.ObjectFactory;

public class Test {

	public static void main(String[] args) {
		ObjectFactory of=ObjectFactory.getObjectFactory("bean.xml");
		TestData data=(TestData)of.getObject("data");
		System.out.println(data.getName());
		System.out.println(data.getName1());
		for(String book:data.getBooklist()){
			System.out.println(book);
		}
		System.out.println(data.getUserinfo().get("name"));
		System.out.println(data.getUserinfo().get("sex"));
		System.out.println(data.getProps().get("1"));
		System.out.println(data.getProps().get("2"));
		Set<String> bookset=data.getBookset();
		Iterator<String> it=bookset.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println(data.getHello().sayHello("aaa"));
		data.testHello();
	}
}
