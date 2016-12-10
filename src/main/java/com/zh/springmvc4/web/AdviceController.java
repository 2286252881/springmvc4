package com.zh.springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.springmvc4.domain.DemoObj;


@Controller
public class AdviceController {
	@RequestMapping("/advice")
	public String getSomething(@ModelAttribute("msg") String msg,DemoObj demoObj){
		throw new IllegalArgumentException("I'm sorry 参数错误/"+"来自@ModelAttributes:"+msg);
	}
}
