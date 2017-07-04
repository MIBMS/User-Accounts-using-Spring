package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.PrintStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



@SuppressWarnings("restriction")
@SpringBootApplication
public class UserAccountsApplication extends Application{
	 private Teller teller;
	 private static ApplicationContext context = new ClassPathXmlApplicationContext("com/example/demo/context.xml");
	 
	 private PrintStream stream = context.getBean("stream", java.io.PrintStream.class);
	 
	 public UserAccountsApplication(){
	 }
	 
	 /* For some reason the constructor injection does not work
	 public UserAccountsApplication(PrintStream stream){
		 this.stream = stream;
	 }
	 */
	 public Teller getTeller(){
		 return teller;
	 }
	 
	 
	 @Override
	 public void start(Stage stage){
		 stage.setTitle("User accounts");
	     StackPane root = new StackPane();
	     HBox userAccount = new HBox();
	     HBox userAccount2 = new HBox();
	     TextField userName = new TextField();
	     TextField accountNumber = new TextField();
	     TextField accountBalance = new TextField();
	     
	     
	     Button addUser = new Button("Add user");
	     Button addAccount = new Button("Add account");
	     Button linkUserAccount = new Button("Link User ID and account");
	     HBox linkBox = new HBox();
	     TextField userID = new TextField();
	     TextField accountNumberLink = new TextField();
	     userID.setPrefColumnCount(3);
	     accountNumberLink.setPrefColumnCount(3);
	     linkBox.setSpacing(10);
	     HBox getAccountBox = new HBox();
	     getAccountBox.setSpacing(10);
	     TextField userIDGetAccounts = new TextField();
	     Button getAccountsBtn = new Button("Get user's accounts");
	     
	     
	     GridPane displayPane = new GridPane();
	       
	     userAccount.getChildren().addAll(new Label("Add a new user: "), userName);
	     userAccount2.getChildren().addAll(new Label("Add a new account: "), accountNumber, 
	    		 new Label("Account balance: "), accountBalance);
	     userAccount.setSpacing(10);
	     userAccount2.setSpacing(10);
	     linkBox.getChildren().addAll(new Label("User ID:"), userID, new Label("Account:"), accountNumberLink);
	     userAccount.setAlignment(Pos.CENTER_LEFT);
	     userAccount2.setAlignment(Pos.CENTER_LEFT);
	     linkBox.setAlignment(Pos.CENTER_LEFT);
	     getAccountBox.setAlignment(Pos.CENTER_LEFT);
	     getAccountBox.getChildren().addAll(new Label("Get user's accounts:"), 
	    		 userIDGetAccounts, getAccountsBtn);
	     
	     
	     displayPane.add(userAccount, 0, 0);
	     displayPane.add(addUser, 1, 0);
	     displayPane.add(userAccount2, 0, 1);
	     displayPane.add(addAccount, 1, 1);
	     displayPane.add(linkBox, 0, 2);
	     displayPane.add(linkUserAccount, 1, 2);
	     displayPane.add(getAccountBox, 0, 3);
	     displayPane.add(getAccountsBtn, 1, 3);
	     

	     
	     displayPane.setAlignment(Pos.CENTER);
	     displayPane.setVgap(8);
	     displayPane.setHgap(8);
	     displayPane.setPadding(new Insets(12));
	     
	       
	     addUser.setOnAction(e -> {
	    	 User user = (User) context.getBean("user");
	    	 user.setName(userName.getText());
	    	 teller = (Teller) context.getBean("teller");
	    	 teller.addUser(user);
	    	 stream.println("User added - ID: " + user.getID() + " Name: " + user.getName());
	     });
	     
	     addAccount.setOnAction(e -> {
	    	 Account account = (Account) context.getBean("account");
	    	 try{
		    	 account.setNumber(Integer.parseInt(accountNumber.getText()));
		    	 account.setBalance(Integer.parseInt(accountBalance.getText()));
	    	 }
	    	 catch(NumberFormatException ex){
	    		 stream.println("Please enter a number.");
	    	 }
		    	 teller = (Teller) context.getBean("teller");	    	 
		    	 teller.addAccount(account);
		    	 stream.println("Account added - Number: " + account.getNumber() + " Balance: " + account.getBalance());
	    	 
	     });
	     
	     linkUserAccount.setOnAction(e -> {
	    	 teller = (Teller) context.getBean("teller");
	    	 try{
	    		 teller.linkUserAccount(Integer.parseInt(userID.getText()), 
	    			 Integer.parseInt(accountNumberLink.getText()));
	    		 teller.printUserAccounts();
	    	 }
	    	 catch(NumberFormatException ex){
	    		 stream.println("Please enter a number.");
	    	 }
	     });
	     
	     getAccountsBtn.setOnAction(e -> {
	    	 teller = (Teller) context.getBean("teller");
	    	 try{
	    		 teller.printAccounts(Integer.parseInt(userIDGetAccounts.getText()));
	    	 }
	    	 catch(NumberFormatException ex){
	    		 stream.println("Please enter a number.");
	    	 }
	     });
	       
	     root.getChildren().addAll(displayPane);
	       
	     stage.setScene(new Scene(root));
	     stage.sizeToScene();
	     stage.show();
	 }
		

	 public static void main(String[] args) {
		 SpringApplication.run(UserAccountsApplication.class, args);
		 Application.launch(args);		
	 }
}
