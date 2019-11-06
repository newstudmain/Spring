package com.IOC.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class People {

	private int id;
	private String name;
	private Set<String> sets;
	private List<String> lists;
	private String[] arrs;
	private Map<String, String> maps;
	
	private Desk desk;
	
	public People() {
		System.out.println("People...People()");
	}

	public People(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public People(String name,int id) {
		super();
		this.id = id;
		this.name = name;
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		System.out.println("People...setId(int id)");
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("People...setName(String name)");
		this.name = name;
	}
	
	public Set<String> getSets() {
		return sets;
	}

	public void setSets(Set<String> sets) {
		this.sets = sets;
	}
	
	public List<String> getLists() {
		return lists;
	}

	public void setLists(List<String> lists) {
		this.lists = lists;
	}
	
	public String[] getArrs() {
		return arrs;
	}

	public void setArrs(String[] arrs) {
		this.arrs = arrs;
	}

	public Map<String, String> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, String> maps) {
		this.maps = maps;
	}

	public Desk getDesk() {
		return desk;
	}

	public void setDesk(Desk desk) {
		this.desk = desk;
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + ", sets=" + sets + ", lists=" + lists + ", arrs="
				+ Arrays.toString(arrs) + ", maps=" + maps + ", Obj_desk=" + desk + "]";
	}

}
