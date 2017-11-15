package com.beifengioc.xmltools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.beifengioc.xmlinfo.Bean;
import com.beifengioc.xmlinfo.Property;
import com.beifengioc.xmlinfo.PropertyMap;

//���󹤳��������������õĶ���
public class ObjectFactory {

	private static ObjectFactory of=null;
	
	private ObjectFactory(){
		
	}
	
	public static ObjectFactory getObjectFactory(String filename){
		if(null==of){
			of=new ObjectFactory();
		}
		if(null==ConstantIOC.BEANMAP){
			BeifengIOCXMLFactory.readXML(filename);
		}
		return of;
	}
	
	//�������õĶ���
	public Object getObject(String name){
		Bean bean=ConstantIOC.BEANMAP.get(name);
		String classname=bean.getClassname();
		Object result=null;
		try {
			Class c=Class.forName(classname);
			result=c.newInstance();
			//ע��
			Map<String, Property> propertymap=bean.getPropertymap();
			if(propertymap!=null){
				Set<String> set=propertymap.keySet();
				Iterator<String> it=set.iterator();
				while(it.hasNext()){
					Property property=propertymap.get(it.next());
					String pname=property.getName();
					if(property.getPlist()!=null){
						Method m=c.getDeclaredMethod("set"+pname.substring(0,1).toUpperCase()+pname.substring(1), List.class);
						m.invoke(result, property.getPlist().getPropertylist());
					}else if(property.getPmap()!=null){
						Method m=c.getDeclaredMethod("set"+pname.substring(0,1).toUpperCase()+pname.substring(1), Map.class);
						m.invoke(result, property.getPmap().getPropertymap());
					}else if(property.getPprops()!=null){
						Method m=c.getDeclaredMethod("set"+pname.substring(0,1).toUpperCase()+pname.substring(1), Properties.class);
						m.invoke(result, property.getPprops().getPropertyprops());
					}else if(property.getPset()!=null){
						Method m=c.getDeclaredMethod("set"+pname.substring(0,1).toUpperCase()+pname.substring(1), Set.class);
						m.invoke(result, property.getPset().getPropertyset());
					}else if(property.getRefbean()!=null){
						Field f=c.getDeclaredField(pname);
						Method m=c.getDeclaredMethod("set"+pname.substring(0,1).toUpperCase()+pname.substring(1), f.getType());
						//�������bean��Ȼ��õ�����bean��name���ݹ����getObject(String name)�����������������
						//o��������bean����
						Object o=getObject(property.getRefbean().getRefbean().getId());
						m.invoke(result, o);
					}else{
						Method m=c.getDeclaredMethod("set"+pname.substring(0,1).toUpperCase()+pname.substring(1), String.class);
						m.invoke(result, property.getValue());
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
