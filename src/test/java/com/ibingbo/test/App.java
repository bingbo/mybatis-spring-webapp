package com.ibingbo.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.ibingbo.models.User;

public class App {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		ClassLoader loader=Thread.currentThread().getContextClassLoader();
		Class class1=loader.loadClass("com.ibingbo.models.User");
		Constructor constructor= class1.getDeclaredConstructor((Class[])null);
		User user=(User) constructor.newInstance(null);
		Method method=class1.getMethod("setName", String.class);
		method.invoke(user, "bill");
		System.out.println(user.getName());

	}

}
