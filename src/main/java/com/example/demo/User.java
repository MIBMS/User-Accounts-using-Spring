package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private static int lastID = 0;
	private int id;
	List<Integer> accounts = new ArrayList<>();
	
	
	public User(String name){
		this.name = name;
		this.id = lastID;
		lastID++;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void addAccount(int accountID){
		accounts.add(accountID);
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof User && ((User)o).getID() == this.getID()){
			return true;
		}
		else{
			return false;
		}
	}
}
