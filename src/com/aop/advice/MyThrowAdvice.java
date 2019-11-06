package com.aop.advice;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;

import org.springframework.aop.ThrowsAdvice;

public class MyThrowAdvice implements ThrowsAdvice{
	
	public void myExpection(IOException e) {
		System.out.println("MyThrowAdvice...myExpection()...Throws: "+e.getMessage());
	}
	
    public void afterThrowing(Exception ex) throws Throwable {
        // Do something with remote exception
    	System.out.println("MyThrowAdvice...afterThrowing(Exception ex)...Throws1: "+ex.getMessage());
    }
    
    public void afterThrowing(Method m, Object[] args, Object target, ServletException ex) {
        // Do something with all arguments
    	System.out.println("MyThrowAdvice...afterThrowing(Exception ex)...Throws4: "+ex.getMessage());
    }


}
