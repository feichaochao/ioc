package com.beifengioc.xmlinfo;

import java.util.Map;

public class Bean {

	private String id;
	private String classname;
	private Map<String, Property> propertymap;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public Map<String, Property> getPropertymap() {
		return propertymap;
	}
	public void setPropertymap(Map<String, Property> propertymap) {
		this.propertymap = propertymap;
	}
}
