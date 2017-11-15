package com.beifengioc.test;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TestData {

	private String name;
	private String name1;
	private List<String> booklist;
	private Map<String, String> userinfo;
	private Properties props;
	private HelloWorld hello;
	private Set<String> bookset;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getBooklist() {
		return booklist;
	}
	public void setBooklist(List<String> booklist) {
		this.booklist = booklist;
	}
	public Map<String, String> getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Map<String, String> userinfo) {
		this.userinfo = userinfo;
	}
	public Properties getProps() {
		return props;
	}
	public void setProps(Properties props) {
		this.props = props;
	}
	public HelloWorld getHello() {
		return hello;
	}
	public void setHello(HelloWorld hello) {
		this.hello = hello;
	}
	public Set<String> getBookset() {
		return bookset;
	}
	public void setBookset(Set<String> bookset) {
		this.bookset = bookset;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	
	public void testHello(){
		System.out.println(hello.sayHello("test"));
	}
}
