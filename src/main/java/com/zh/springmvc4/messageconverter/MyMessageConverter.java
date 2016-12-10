package com.zh.springmvc4.messageconverter;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.zh.springmvc4.domain.DemoObj;

public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {//1

	/**
	 * 自动以媒体类型
	 */
	public MyMessageConverter() {
		super(new MediaType("application", "com-zh",Charset.forName("UTF-8")));//2
	}
	
	/**
	 * 3 处理请求的数据。处理由“-”隔开的数据，并转为DemoObj对象
	 */
	
	@Override
	protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		String temp=StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
		String[] tempArr=temp.split("-");
		return new DemoObj(new Long(tempArr[0]),tempArr[1]);
	}
	
	
	
	/**
	 * 4 HttpMessageConverter只处理DemoObj这个类
	 */
	@Override
	protected boolean supports(Class<?> clazz) {
		return DemoObj.class.isAssignableFrom(clazz);
	}
	
	/**
	 * 5 处理如何输出数据到response
	 */
	@Override
	protected void writeInternal(DemoObj obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		String out="hello:"+obj.getId()+"-"+obj.getName();
		outputMessage.getBody().write(out.getBytes());
	}

}
