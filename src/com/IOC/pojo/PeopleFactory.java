package com.IOC.pojo;

public class PeopleFactory {

	public People createPeople(String name) {
		
		switch (name) {
		case "A":
			//之前处理简单逻辑
			return new A();
		case "B":
			//之前处理简单逻辑
			return new B();
		default:
			return null;
		}
	}
	
	public People createInstance() {
		return new People();
	}
	
	public static People newInstance() {
		return new People();
	}
}
