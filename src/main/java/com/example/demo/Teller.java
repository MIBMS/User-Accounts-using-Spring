package com.example.demo;
 
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
 
public class Teller {
    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Account> accounts = new HashMap<>();
    private Map<Integer, ArrayList<Integer>> userAccountMap = new HashMap<>();
    private PrintStream stream;
    
    public Teller(){
    }
    
    public void setStream(PrintStream stream){
    	this.stream = stream;
    }
    
    public void addUser(User user){
        users.put(user.getID(), user);
    }
   
    public void addAccount(Account account){
        accounts.put(account.getNumber(), account);
    }
   
    public void linkUserAccount(int userID, int accountNumber){
        if (users.get(userID) != null && accounts.get(accountNumber) != null){
        	if (userAccountMap.get(userID) == null){
        		userAccountMap.put(userID, new ArrayList<Integer>(Arrays.asList(accountNumber)));
        	}
        	else{
        		if (!userAccountMap.get(userID).contains(accountNumber)){
        			userAccountMap.get(userID).add(accountNumber);
        		}
        	}
        }
    }
    
    public void printUserAccounts(){
    	stream.println("The following users and accounts are linked: ");
    	for(Integer userID: userAccountMap.keySet()){
    		stream.println("User: " + userID + " Accounts: "+ userAccountMap.get(userID));
    	}
    }
    
    public void printAccounts(int userID){
    	if (userAccountMap.get(userID) != null){
    		stream.print("The user is linked to the following accounts:");
    		stream.print("\nUser: " + userID);
    		for (int accountNumber: userAccountMap.get(userID)){
    			stream.print("\nAccount: " + accountNumber + 
    					" Balance: " + accounts.get(accountNumber).getBalance());
    		}
    		stream.println();
    	}
    }
}