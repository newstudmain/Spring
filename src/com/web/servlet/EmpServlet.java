package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.Spr_Mybatis.Service.EmpService;
import com.Spr_Mybatis.Service.EmpServiceImpl;

@WebServlet("/emp")
public class EmpServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private EmpService empService;
	
	@Override
	public void init() throws ServletException {
		
		
		/*
		 * 写一个Servlet时，有时需要我们重写该Servlet的初始化方法，
		 * 然后，究竟是重写init(ServletConfig config)，还是重写init()
		 * //https://www.jb51.net/article/111522.htm
		 * */
		
		//对empService实例化
		//不使用此方法，applicationContext.xml 配置文件还是需要手动获取
//		ApplicationContext ac =new ClassPathXmlApplicationContext("applicationContext.xml");
//		EmpServiceImpl bean = ac.getBean("empServiceImpl",EmpServiceImpl.class);
//		empService = bean;
		
		//tomcat容器提供了方法，使tomcat启动时自动加载Spring配置文件，Spring和web的信息都存到新的WebApplicationContext
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		EmpServiceImpl bean = ac.getBean("empServiceImpl",EmpServiceImpl.class);
		empService = bean;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		empService.show().forEach(System.out::println);
		req.setAttribute("emplist", empService.show());
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
