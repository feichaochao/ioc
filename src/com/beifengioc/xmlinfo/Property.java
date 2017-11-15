package com.beifengioc.xmlinfo;

public class Property {

	private String name;
	private String value;
	private PropertyList plist;
	private PropertyMap pmap;
	private PropertyProps pprops;
	private PropertySet pset;
	private RefBean refbean;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public PropertyList getPlist() {
		return plist;
	}
	public void setPlist(PropertyList plist) {
		this.plist = plist;
	}
	public PropertyMap getPmap() {
		return pmap;
	}
	public void setPmap(PropertyMap pmap) {
		this.pmap = pmap;
	}
	public PropertyProps getPprops() {
		return pprops;
	}
	public void setPprops(PropertyProps pprops) {
		this.pprops = pprops;
	}
	public PropertySet getPset() {
		return pset;
	}
	public void setPset(PropertySet pset) {
		this.pset = pset;
	}
	public RefBean getRefbean() {
		return refbean;
	}
	public void setRefbean(RefBean refbean) {
		this.refbean = refbean;
	}
}
