package com.zh.springmvc4.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 1.继承HandlerInterceptorAdapter类实现自定义拦截器
 * 2.重写preHandle方法，在请求前发生
 * 3.重写postHandle方法，在请求完成后执行
 * @author Administrator
 *
 */


public class DemoInterceptor extends HandlerInterceptorAdapter {// 1

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {//3
		
		long startTime=(long) request.getAttribute("startTime");
		long endTime=System.currentTimeMillis();
		
		System.out.println("本次请求处理的时间为："+new Long(endTime-startTime)+"ms");
		
		request.setAttribute("handlingTime", endTime-startTime);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {//2
		long startTime=System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

}
