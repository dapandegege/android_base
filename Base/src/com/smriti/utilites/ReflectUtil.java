package com.smriti.utilites;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ReflectUtil {
	
	public static Object getPropertyValue(Object object, String pname){
		Object pObject = null;
		try {
			Field field = object.getClass().getField(pname);
			pObject = field.get(object);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pObject;
	}
	
	public static void invokeMethod(Object object, String methodName, Object ...objects){
		try {
			Method method = object.getClass().getMethod(methodName);
			method.invoke(object, objects);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
