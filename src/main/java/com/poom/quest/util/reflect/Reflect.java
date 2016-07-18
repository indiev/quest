package com.poom.quest.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflect {

	static public Method getMethod(Class<?> clazz, String keyword) {
		for(Method method : clazz.getMethods())
			if(method.getName().equalsIgnoreCase(keyword)) return method;
		return null;
	}
	
	static public Field getField(Class<?> clazz, String keyword) {
		for(Field field: clazz.getDeclaredFields())
			if(field.getName().equalsIgnoreCase(keyword)) return field;
		return null;
	}
}
