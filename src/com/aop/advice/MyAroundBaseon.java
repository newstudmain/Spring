package com.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAroundBaseon implements MethodInterceptor{
	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("环绕-前置...Base_on");
		Object result = arg0.proceed();//放行,调用切点方式System.out.println("环绕-后置");
		System.out.println("环绕-后置...Base_on");
		return result;
	}
}

class MyAroundAspectJ {
	public void befor() {
		System.out.println("环绕-前置...AspectJ");
	}
	public void after() {
		System.out.println("环绕-后置...AspectJ");
	}
	
	public void throwsEx(Exception e) {
		System.out.println("环绕中异常通知/MyAroundAspectJ...throwsEx(e)...AspectJ: "+e.getMessage());
	}
	
	public Object myarround(ProceedingJoinPoint p,String name,int age) throws Throwable{
		System.out.println("====执行环绕...proceed()====");
			System.out.println("环绕-前置"+"arg1: "+name);
			Object result = p.proceed();
			System.out.println("环绕-后置"+"arg2: "+age);
		System.out.println("====执行环绕...proceed()====");
		
		return result;
	}
	
	public void befor1(String name,int age) {
		System.out.println("环绕-前置...AspectJ"+" befor(String str,int i):"+name+"/"+age);
	}
}
