package com.zh.springmvc4.domain;

public class DemoObj {
	private Long Id;
	private String name;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DemoObj() {
		super();
	}

	public DemoObj(Long id, String name) {
		super();
		Id = id;
		this.name = name;
	}

}
