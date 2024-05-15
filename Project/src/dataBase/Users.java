package dataBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparators.movieMostAppreciated;
import metier.Admin;
import metier.Movie;
import metier.User;

public class Users {

	// Fields
	private List<User> users = new ArrayList<User>();
	private static String[] userFields = {
		    "Password :",
		    "Surname :",
		    "Name :",
		    "Subscriber :"		
	};

	// Getters
	public List<User> getUsers(){
		return users;
	}
	public User getUser(String mail) {
		for(int i = 0; i < this.users.size(); i++) {
			/*
			if(this.users.get(i).getMail() == mail) {
				return this.users.get(i);
			}
			*/
		}
		return null;
	}
	
	// Adder
	public void addUser(User user) {
		this.users.add(user);
	}
	
	// Makers of statistics
	public int getNbAccount() {
		return this.users.size();
	}
	public int getNbSubscribers() {
		int res = 0;
		
		for(int i = 0; i < this.users.size(); i++) { // For each users
			/*
			if(this.users.get(i).isSubsrcibed()) { // Is subscribed ?
				res++;
			}
			*/
		}
		
		return res;
	}
	
	// Destroyer
	public boolean deleteUser(User user) {
		return this.users.remove(user);
		// And delete associated score
	}
	
	// Saver
	public void savedUsers() {
		try {
			
			// Create the necessary folders if there are not
			new File("Bdd").mkdirs();
			new File("Bdd/Users").mkdirs();
			
			// Needed variable to write data in files
		    BufferedWriter writer;
			
			// Save Users :
			for (int i = 0; i < this.users.size(); i++) {
				
				// Write data in the file
			    writer = new BufferedWriter(new FileWriter("Bdd/Users/" /*+  users.get(i).getEmail()*/));
			    
			    /*
			    writer.write(userFields[0] + "\n");
			    writer.write(users.get(i).getPassword() + "\n");
			    writer.write(userFields[1] + "\n");
			    writer.write(users.get(i).getSurname() + "\n");
			    writer.write(userFields[2] + "\n");
			    writer.write(users.get(i).getName() + "\n");
			    writer.write(userFields[3] + "\n");
			    writer.write(users.get(i).getSubscriber() + "\n");
			    */
			    /*
			    for (int j = 0; j < users.get(i).getPurchases().length; j++){
			    	writer.write(userFields[4] + "\n");
				    writer.write(userFields[5] + "\n");
				    for (int k = 0; k < users.get(i).getPurchases().get(j).length; k++){
				    	writer.write(users.get(i).getPurchases().get(j).getMovies().get(k) + "\n");
				    }
				    writer.write(userFields[6] + "\n");
					writer.write(users.get(i).getPurchases().get(j).getDate() + "\n");
				    writer.write(userFields[7] + "\n");
					writer.write(users.get(i).getPurchases().get(j).getisPayed() + "\n");
			    }
				*/
			    
			    writer.close();
			}
	
	    } catch (IOException e) { // Display a message if an error has occurred while creating the file / writing in the files
	        System.out.println("An error occurred : We could not save your modifications");
	    }
	}

	public User connect(String email, String password) {
		User res = null;
		for (int i = 0; i < this.users.size(); i++) {
			/*
			if(this.users.get(i).getEmail.equals(email) && this.users.get(i).getPassword.equals(password)) {
				return this.users.get(i);
			}
			*/
		}
		return res;
	}
	public int emailAndSecretSentenceInBdd(String email, String secretSentence) {
		for (int i = 0; i < this.users.size(); i++) {
			/*
			if(this.users.get(i).getEmail.equals(email) && this.users.get(i).getSecretSentence.equals(secretSentence)) {
				return 1;
			}
			*/
		}
		return 0;
	}

	@SuppressWarnings("deprecation") void readSavedUsers() {
		File bddUSersDirectory = new File("Bdd/Users");
		
		// Check if there is saved data, if not : stop the function (there is no data to read)
		if(! bddUSersDirectory.exists()) { 
			System.out.println("Bdd does not exist");
			return;
		}

		// Get saved Users
		File[] listOfFile = bddUSersDirectory.listFiles();
		for (int i = 0; i < listOfFile.length; i++) { // For each existing file
			
			// Create needed variables to add a user
			String password;
			String surname;
			String name;
			boolean subscriber;
			
			try {
				
				// Create needed variables
				BufferedReader reader = new BufferedReader(new FileReader(listOfFile[i])); // to read the current file
			    int idField = -1; // Correspond to an id of the actual saving field
			    boolean isTitle; // true : the read line is a title corresponding to an idField; false : the read line is a field content

			    String lineInFile = reader.readLine(); // Read the first line of the current file
			    while(lineInFile != null) {
		    		isTitle = false;
			    	
		    		// Get the idFields if the line is one
			    	for(int j = 0; j < Users.userFields.length; j++) {
				    	if (lineInFile.equals(Users.userFields[j])){
				    		idField = j;
				    		isTitle = true;
				    	}
			    	}
			    	
			    	// Get the value of the current field
			    	if(! isTitle) { // The line is a field content
			    		switch(idField) {
			    		case 0 :
			    			password = lineInFile.trim();
			    			break;
			    		case 1 :
			    			surname = lineInFile.trim();
			    			break;
			    		case 2 :
			    			name = lineInFile.trim();
			    			break;
			    		case 3 :
			    			subscriber = lineInFile.trim().equals("true");
			    			break;
			    		}
			    	}

			    	lineInFile = reader.readLine(); // Read the next line of the current file
			    }

			    reader.close();
			    
			    // addUser(new User(listOfFile[i], password, surname, name, subscriber));
			
			} catch (IOException e) { // Display a message if an error has occurred while reading in the files
		        System.out.println("An error occurred : We could not get saved informations");
		    }
		}
	}
}
