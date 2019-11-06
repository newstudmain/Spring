package com.IOC.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.IOC.pojo.A;
import com.IOC.pojo.B;
import com.IOC.pojo.People;
import com.IOC.pojo.PeopleFactory;
import com.Spr_Mybatis.Mapper.EmpMapper;
import com.Spr_Mybatis.Service.EmpServiceImpl;
import com.Spr_Mybatis.bean.Emp;

/*
 * 1. 四个核心包一个日志包：
 * 	
	spring-beans-4.1.6.RELEASE.jar
	spring-context-4.1.6.RELEASE.jar
	spring-core-4.1.6.RELEASE.jar
	spring-expression-4.1.6.RELEASE.jar
	commons-logging-1.1.3.jar
 * 
 * 2. 新建 applicationContext.xml
 * 
 	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        				http://www.springframework.org/schema/beans/spring-beans.xsd">
        
        <bean id="peo" class="com.IOC.pojo.People"></bean>
        
	</beans>
 
 			applicationContext.xml 配置的信息最终存储到了 AppliationContext 容器中
 * 
 * 3. 通过<bean/> 创建对象
 * 		类在配置文件加载时创建
 *
	 id: 获取到对象的标识
	 class: 创建哪个类的对象
 * <bean id="peo" class="com.IOC.pojo.People"></bean>
 * 
 * 4. Spring 创建对象的三种方式
		1. 通过构造方法创建
			1.1  无参构造创建:默认情况
					<bean id="peo" class="com.IOC.pojo.People">
	
					</bean>  
			1.2  有参构造创建:需要明确配置
					如果设定的条件 匹配多个构造方法执行最后的构造方法
				
					//	<!-- ref 引用另一个 bean / value 基本数据类型或String 等 -->
					<bean id="peo" class="com.IOC.pojo.People">
						<constructor-arg index="0" value="123"></constructor-arg>
						<constructor-arg index="1" value="zhangsan"></constructor-arg>
					</bean> 
					
					------------
					//index[参数索引 0] 顺序 
					  name[参数名] 属性名 
					  type[类型(区分开关键字和封装类 int 和 Integer)] 数据类型 
					  >> 唯一确定一个重载方法
					  
					赋值
					<bean id="peo" class="com.IOC.pojo.People">
						<constructor-arg name="id" value="123"></constructor-arg>
						<constructor-arg name="name" value="zhangsan"></constructor-arg>
					</bean>
					------------
		
		2. 实例工厂
			工厂设计模式:帮助创建类对象，一个工厂可以生产多个对象.
			2.1  实例工厂:需要先创建工厂，才能生产对象
					2.1.1  必须要有一个实例工厂
							public class PeopleFactory {
								public People createInstance() {
									return new People();
								}
							}
					2.1.2 在 applicationContext.xml 中配置工厂对象和需要创建的对象
								<bean id="factory" class="com.IOC.pojo.PeopleFactory"></bean>
								<bean id="peoIns" factory-bean="factory" factory-method="createInstance"></bean> 
			
		3. 静态工厂
			3.1  不需要创建工厂，快速创建对象.
					3.1.1 编写一个静态工厂(在方法上添加 static)
							public class PeopleFactory {
								public static People newInstance() {
									return new People();
								}
							}					
					3.1.2 在 applicationContext.xml 中配置工厂对象和需要创建的对象
								<bean id="peoInsSt" class="com.IOC.pojo.PeopleFactory" factory-method="newInstance"></bean>   
							
 * 
 * 5. 如何给 Bean 的属性赋值(注入)
		1. 通过构造方法设置值.
					<bean id="peo" class="com.IOC.pojo.People">
						<constructor-arg index="0" value="123"></constructor-arg>
						<constructor-arg index="1" value="zhangsan"></constructor-arg>
					</bean>
		
		2. 设置注入(通过 set 方法)
 				2.1  如果属性是基本数据类型或 String 等简单
				    <bean id="Inpeo" class="com.IOC.pojo.People">
				    	<property name="id" value="001"></property>
				    	
				    	<property name="name">
				    		<value>jack</value>
				    	</property>
				    	
				    	<property name="sets">
				    		<set>
				    			<value>s1</value>
				    			<value>s2</value>
				    		</set>
				    	</property>
				    	
				    	<property name="lists">
				    		<list>
				    			<value>L1</value>
				    			<value>L2</value>
				    		</list>
				    	</property>
				    	<!-- 如果 lists 中就只有一个值 <property name="lists" value="L1"></property> -->
				    	
				    	<property name="arrs">
				    		<array>
				    			<value>a1</value>
				    			<value>a2</value>
				    		</array>
				    	</property>
				    	<!-- 如果 strs_arr 中就只有一个值 <property name="arrs" value="a1"></property> -->
				    	
				    	<property name="maps">
				    		<map>
				    			<entry key="k1" value="v1"></entry>
				    			<entry key="k2" value="v2"></entry>
				    		</map>
				    	</property>
				    	
						  <!-- 
						  		<property name="pro">
						    		<props>
						    			<prop key="a">p01</prop>
						    		</props>
						    	</property>
						   -->  	
				    </bean>  					    
					    
					    
 * 6. DI 依赖注入((Dependency Injection)
		6.1. DI 是什么?
				DI 和 IoC 是一样的
				当一个类(A)中需要依赖另一个类(B)对象时,
				把 B 赋值给 A 的过程就叫做依赖注入
				
				<bean> 			
					<property name="desk" ref="desk">
			    		
			    	</property>
			    </bean> 
			    
			    <bean id="desk" class="com.IOC.pojo.Desk"></bean>
 * 
 * */
public class Test {

	public static void main(String[] args) {
		//People p =new People();
		
		//类在配置文件加载时创建
		ApplicationContext ac =new ClassPathXmlApplicationContext("applicationContext.xml");
		
		System.out.println("---------");
		
		//getBean(“<bean>标签 id 值”,返回值类型);如果没有第二个参数,默认是 Object
		People peo = ac.getBean("peo",People.class);
		System.out.println(peo);
		
		System.out.println("---------");
		
		//getBeanDefinitionNames() Spring容器中目前所有管理的所有对象
		String[] names = ac.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println("names: "+name);
		}
		
		System.out.println("---------");
		
		//实例工厂
		PeopleFactory factory = new PeopleFactory();
		People people = factory.createPeople("A");
		People peopleIns = factory.createInstance();
		
		People peoIns = ac.getBean("peoIns",People.class);
		System.out.println("peoIns: "+peoIns);
		
		System.out.println("---------");
		
		//静态工厂
		People peoInsSt = PeopleFactory.newInstance();
		
		People peoInsStBean = ac.getBean("peoInsSt", People.class);
		System.out.println("peoInsStBean: "+peoInsStBean);
		
		System.out.println("---------");
		
		//设置注入(通过 set 方法)
		People Inpeo = ac.getBean("Inpeo",People.class);
		System.out.println(Inpeo);
		
		System.out.println("---------");
		
		SqlSessionFactory sqlSessionFactory = ac.getBean("sqlSessionFactory",SqlSessionFactory.class);
		/*
		 *  names: dataSource
			names: sqlSessionFactory
			names: mappers    //<bean id="mappers" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
			names: empMapper //说明此时通过mappers 标签中的package 已经创建了包中相关Mapper
			names: org.springframework.context.annotation.internalConfigurationAnnotationProcessor
			names: org.springframework.context.annotation.internalAutowiredAnnotationProcessor
			names: org.springframework.context.annotation.internalRequiredAnnotationProcessor
			names: org.springframework.context.annotation.internalCommonAnnotationProcessor
			names: org.springframework.context.annotation.ConfigurationClassPostProcessor.importAwareProcessor
			names: org.springframework.context.annotation.ConfigurationClassPostProcessor.enhancedConfigurationProcessor
		 * */
		EmpServiceImpl empServiceImpl = ac.getBean("empServiceImpl",EmpServiceImpl.class);
		List<Emp> list = empServiceImpl.show();
		list.forEach(System.out::println);
		
		/*
		 *报错：Exception in thread "main" java.lang.AbstractMethodError: 
				org.mybatis.spring.transaction.SpringManagedTransaction.getTimeout()Ljava/lang/Integer;
				
				在mybatis+spring整合中，由于版本的问题
					当前MyBatis：	          mybatis-3.5.2.jar
					   MyBatis-Spring     mybatis-spring-1.2.3.jar	[X..1.3.0 or higher]
					   Spring			  Spring4
				
				 MyBatis-Spring	  		MyBatis	        Spring
				1.0.0 and 1.0.1	 	3.0.1 to 3.0.5	    3.0.0 or higher
				1.0.2	3.0.6		3.0.0 or higher
				1.1.0 or higher	 	3.1.0 or higher		3.0.0 or higher
				1.3.0 or higher	 	3.4.0 or higher		3.0.0 or higher
		 * */

	}
	
	
	
	
	
	
	
	
	
	
}
