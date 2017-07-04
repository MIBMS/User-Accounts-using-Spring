package com.example.demo;

public class Account {
	private int number;
	private double balance;
	
	
	public Account(){
		
	}
	
	public Account(int number, double balance){
		this.balance = balance;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	
	public int getNumber(){
		return number;
	}
	
	public void setBalance(double balance){
		this.balance = balance;
	}
	
	public double getBalance(){
		return balance;
	}
	
	
	@Override
	public boolean equals(Object o){
		if (o instanceof Account && ((Account)o).getNumber() == this.getNumber()){
			return true;
		}
		else{
			return false;
		}
	}
}
