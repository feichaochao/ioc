package com.beifengioc.xmltools;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.beifengioc.xmlinfo.Bean;
import com.beifengioc.xmlinfo.Property;
import com.beifengioc.xmlinfo.PropertyList;
import com.beifengioc.xmlinfo.PropertyMap;
import com.beifengioc.xmlinfo.PropertyProps;
import com.beifengioc.xmlinfo.PropertySet;
import com.beifengioc.xmlinfo.RefBean;

public class BeifengIOCXMLFactory {

	public static void readXML(String filename){
		InputStream in=(InputStream)BeifengIOCXMLFactory.class.getResourceAsStream("/"+filename);
		SAXReader reader=new SAXReader();
		Document doc=null;
		Map<String, Bean> map=new HashMap<String, Bean>();
		try {
			doc=reader.read(in);
			Element root=doc.getRootElement();
			List<Element> rootlist=root.elements();
			Bean bean=null;
			for(Element beanelement:rootlist){
				bean=new Bean();
				bean.setId(beanelement.attributeValue("id"));
				bean.setClassname(beanelement.attributeValue("class"));
				//读取bean所对应的所有的property属性
				//获得property属性的列表
				List<Element> propertylist=beanelement.elements();
				Map<String, Property> propertymap=null;
				if(propertylist!=null){
					//遍历所有的property属性，并且设置值
					if(null==propertymap){
						propertymap=new HashMap<String, Property>();
					}
					for(Element propertyele:propertylist){
						Property property=new Property();
						String propertyname=propertyele.attributeValue("name");
						property.setName(propertyname);
						List<Element> sonpropertylist=propertyele.elements();
						if(sonpropertylist!=null && sonpropertylist.size()>0){
							//遍历property标签的子标签，从而知道到底是哪种数据类型
							for(Element sone:sonpropertylist){
								if(sone.getName().equals("list")){
									PropertyList plist=new PropertyList();
									List<String> list=new ArrayList<String>();
									List<Element> valuelist=sone.elements();
									//遍历读取list标签下的所有value标签的值，并添加到列表中
									for(Element valuee:valuelist){
										if(valuee.attributeValue("value")!=null){
											list.add(valuee.attributeValue("value"));
										}else{
											list.add(valuee.getText());
										}
									}
									plist.setPropertylist(list);
									property.setPlist(plist);
								}else if(sone.getName().equals("map")){
									PropertyMap pmap=new PropertyMap();
									Map<String,String> valuemap=new HashMap<String, String>();
									List<Element> valuelist=sone.elements();
									for(Element valuee:valuelist){
										if(valuee.attributeValue("value")!=null){
											valuemap.put(valuee.attributeValue("key"), valuee.attributeValue("value"));
										}else{
											valuemap.put(valuee.attributeValue("key"), valuee.getText());
										}
									}
									pmap.setPropertymap(valuemap);
									property.setPmap(pmap);
								}else if(sone.getName().equals("props")){
									PropertyProps props=new PropertyProps();
									Properties prop=new Properties();
									List<Element> valuelist=sone.elements();
									for(Element valuee:valuelist){
										if(valuee.attributeValue("value")!=null){
											prop.put(valuee.attributeValue("key"), valuee.attributeValue("value"));
										}else{
											prop.put(valuee.attributeValue("key"), valuee.getText());
										}
									}
									props.setPropertyprops(prop);
									property.setPprops(props);
								}else if(sone.getName().equals("set")){
									PropertySet pset=new PropertySet();
									Set<String> set=new HashSet<String>();
									List<Element> valuelist=sone.elements();
									for(Element valuee:valuelist){
										if(valuee.attributeValue("value")!=null){
											set.add(valuee.attributeValue("value"));
										}else{
											set.add(valuee.getText());
										}
									}
									pset.setPropertyset(set);
									property.setPset(pset);
								}else if(sone.getName().equals("ref")){
									RefBean ref=new RefBean();
									String beanname=sone.attributeValue("bean");
									Bean refbean=map.get(beanname);
									ref.setRefbean(refbean);
									property.setRefbean(ref);
								}
							}
						}else{
							if(propertyele.attributeValue("value")!=null){
								property.setValue(propertyele.attributeValue("value"));
							}else{
								property.setValue(propertyele.getText());
							}							
						}
						propertymap.put(property.getName(), property);
					}
				}
				bean.setPropertymap(propertymap);
				map.put(bean.getId(), bean);
			}
			ConstantIOC.BEANMAP=map;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
