package com.aop.test;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

	public void test1() {
		System.out.println("test1");
	}
	public void test2() {
		System.out.println("test2");
	}
	public void test3() throws IOException {
		System.out.println("test3");
		throw new IOException("test3() throws IOException");
	}
	public void test4(String str) {
		System.out.println("test4(String str)..."+str);
	}
	public void test5() throws IOException {
		System.out.println("test5");
		throw new IOException("test5() throws IOException");
	}
	public void test6() {
		System.out.println("test6");
	}
	public void test7() throws IOException {
		System.out.println("test7");
		throw new IOException("test7() throws IOException");
	}
	public void test8(String name,int age) {
		System.out.println("test8(String str,int i)");
	}
	
	/*正常程序执行流程都是纵向执行流程
	 * 	aopTest.test1();
	    |
		aopTest.test2();
		|
		aopTest.test3();
	 *
	 *在原来的纵向执行流程，加上前置通知或者后置通知。
		 - 不需要去修改原有代码     [高扩展]
		 - 原有功能释放了部分逻辑 [职责单一明确]
		 
		 	aopTest.test1();																|
		    |[前置通知]相对			//在切点之前执行的功能     before advice    |						|把切面嵌入到原有功能的过程
			aopTest.test2();	//原有功能: 切点     pointcut             | >> 所有功能总称叫做 切面		|		
			|[后置通知]相对			//在切点之后执行的功能   after advice      |						| 		织入
			aopTest.test3();																|
		
		[前置通知]相对  | aopTest.test2(); | [后置通知]相对                                                 >> 所有功能总称叫做 切面
		
		- 如果切点执行过程中出现异常, 会触发异常通知 	throws advice
		
	 *横向切面
	 *在程序原有纵向执行流程中,针对某一个或某一些方法添加通知,形成横切面
	 *
	 *AOP 面向切面编程
	 *形成横切面过程就叫做面向切面编程.
	 *
	 * */
//	public static void main(String[] args) {
//		AOPTest aopTest = new AOPTest();
//		aopTest.test1();
//		aopTest.test2();
//		aopTest.test3();
//	}
	public static void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		AOPTest aopTest = ac.getBean("aoptest",AOPTest.class);
		aopTest.test1();
								System.out.println("-----");
		aopTest.test2();
								System.out.println("-----");
		try {
			aopTest.test3();
		} catch (IOException e) {
			System.out.println("Exception: 测试test3()throwing...AspectJ");
		}
								System.out.println("-----");
		aopTest.test4("t4");
								System.out.println("-----");
		try {
			aopTest.test5();
		} catch (IOException e) {
			System.out.println("Exception: 测试test5()throwing...Base_on");
		}
								System.out.println("-----");
		aopTest.test6();
								System.out.println("-----");
		try {
			aopTest.test7();
		} catch (IOException e) {
			System.out.println("Exception: 测试test7()Aroundthrowing...AspectJ");
		}
								System.out.println("-----");
		aopTest.test8("args1",01);
	}
	
	public static void main(String[] args) {
		test();
	}
}












