package com.guigu.spring.aop.helloworld;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCaculatorLoggingProxy {
	
	//Ҫ����Ķ���
	private ArithmeticCalculator target;
	
	public ArithmeticCaculatorLoggingProxy(ArithmeticCalculator target) {
		this.target = target;
	}
	
	public ArithmeticCalculator getLoggingProxy() {
		ArithmeticCalculator proxy = null;
		
		//�����������һ����������������
		ClassLoader loader = target.getClass().getClassLoader();
		//������������,����������Щ����
		Class[] interfaces = new Class[]{ArithmeticCalculator.class};
		//�����ô���������еķ���ʱ����ִ�еĴ���
		InvocationHandler h = new InvocationHandler() {
			/**
			 * proxy:���ڷ��ڵ��Ǹ��������һ������£���invoke�����ж������øö���
			 * method:���ڱ����õķ���
			 * args:���÷���ʱ����Ĳ���
			 */
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				String methodName = method.getName();
				//��־
				System.out.println("ATGUIGU->The method" + methodName + "begins with " + Arrays.asList(args));
				//ִ�з���
				Object result = method.invoke(target, args);
				//��־
				System.out.println("ATGUIGU->The method " + methodName + "ends with " + result);
				return result;
			}
		};
		
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
		
		return proxy;
	}
	
}
