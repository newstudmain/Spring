package com.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class MyAfterAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		System.out.println("切点返回值： "+arg0);
		System.out.println("切点方法对象： "+"arg1"+"方法名： "+arg1.getName());
		System.out.println("切点方法参数： "+arg2);
		System.out.println("切点所在类对象： "+arg3);
		System.out.println("my after adivce...");
	}

}
