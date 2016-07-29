package com.poom.quest.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.poom.quest.services.model.Model;
import com.poom.quest.services.model.user.User;

public class Reflect {

	static public Method getMethod(Class<?> clazz, String keyword) {
		if(!Model.class.isAssignableFrom(clazz)) return null;
		for(Method method : clazz.getMethods()) {
			if(method.getName().equalsIgnoreCase(keyword)) return method;
		}
		Class<?> superClass = clazz.getSuperclass();
		if(superClass != null && superClass != Model.class) return getMethod(superClass, keyword);
		return null;
	}
	
	static public Field getField(Class<?> clazz, String keyword) {
		if(!Model.class.isAssignableFrom(clazz)) return null;
		for(Field field: clazz.getDeclaredFields()) 
			if(field.getName().equalsIgnoreCase(keyword)) return field;
		Class<?> superClass = clazz.getSuperclass();
		if(superClass != null && superClass != Model.class) return getField(superClass, keyword);
		return null;
	}
}
