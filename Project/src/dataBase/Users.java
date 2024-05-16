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
import metier.Comment;
import metier.Movie;
import metier.Purchase;
import metier.User;

public class Users {

	// Fields
	private List<User> users = new ArrayList<User>();
	private static String[] userFields = {
		    "Code :",
		    "Password :",
		    "Surname :",
		    "Name :",
		    "Email :",
		    "Adresse :",
		    "Secret Sentence :",
		    "is Admin :",
		    "Subscriber :"
	};

	// Getters
	public List<User> getUsers(){
		return users;
	}
	public User getUser(int code) {
		for(int i = 0; i < this.users.size(); i++) {
			if(this.users.get(i).getCode() == code) {
				return this.users.get(i);
			}
		}
		return null;
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
	
	// Adder & Destroyer
	public void addUser(User user) {
		this.users.add(user);
	}
	public void addUser(boolean isAdmin, String email, String motDePasse, String nom, String prenom, String adresse, String phraseSecrete) {
		this.users.add(new User(this.users.size() + 1, null, isAdmin, null, null, email, motDePasse, nom, prenom, adresse, phraseSecrete));
	}
	public boolean deleteUser(User user) {
		return this.users.remove(user);
		// And delete associated score
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

	// Public functions
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
			    writer = new BufferedWriter(new FileWriter("Bdd/Users/" +  users.get(i).getEmail() + ".txt"));

			    writer.write(userFields[0] + "\n");
			    writer.write(users.get(i).getCode() + "\n");
			    writer.write(userFields[1] + "\n");
			    writer.write(users.get(i).getMotDePasse() + "\n");
			    writer.write(userFields[2] + "\n");
			    writer.write(users.get(i).getNom() + "\n");
			    writer.write(userFields[3] + "\n");
			    writer.write(users.get(i).getPrenom() + "\n");
			    writer.write(userFields[4] + "\n");
			    writer.write(users.get(i).getEmail() + "\n");
			    writer.write(userFields[5] + "\n");
			    writer.write(users.get(i).getAdresse() + "\n");
			    writer.write(userFields[6] + "\n");
			    writer.write(users.get(i).getPhraseSecrete() + "\n");
			    writer.write(userFields[7] + "\n");
			    writer.write(users.get(i).getIsAdmin() + "\n");
			    writer.write(userFields[8] + "\n");
			    /*
			    writer.write(users.get(i).getSubscriber() + "\n");
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
			if(this.users.get(i).getEmail().equals(email) && this.users.get(i).getMotDePasse().equals(password)) {
				return this.users.get(i);
			}
		}
		return res;
	}
	public User connectWithSentence(String email, String secretSentence) {
		User res = null;
		System.out.println(this.users.size());
		for (int i = 0; i < this.users.size(); i++) {
			if(this.users.get(i).getEmail().equals(email) && this.users.get(i).getPhraseSecrete().equals(secretSentence)) {
				return this.users.get(i);
			}
		}
		return res;
	}
	public int emailAndSecretSentenceInBdd(String email, String secretSentence) {
		for (int i = 0; i < this.users.size(); i++) {
			if(this.users.get(i).getEmail().equals(email) && this.users.get(i).getPhraseSecrete().equals(secretSentence)) {
				return 1;
			}
		}
		return 0;
	}
	void readSavedUsers() {
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
			int code = -1;
			String password = "";
			String surname = "";
			String name = "";
			String adresse = "";
			String email = "";
			String secretSentence = "";
			boolean isAdmin = false;
			boolean subscriber = false;
			
			try {
				
				// Create needed variables
				@SuppressWarnings("resource")
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
			    			code = Integer.parseInt(lineInFile.trim());
			    			break;
			    		case 1 :
			    			password = lineInFile.trim();
			    			break;
			    		case 2 :
			    			surname = lineInFile.trim();
			    			break;
			    		case 3 :
			    			name = lineInFile.trim();
			    			break;
			    		case 4 :
			    			email = lineInFile.trim();
			    			break;
			    		case 5 :
			    			adresse = lineInFile.trim();
			    			break;
			    		case 6 :
			    			secretSentence = lineInFile.trim();
			    			break;
			    		case 7 :
			    			isAdmin = lineInFile.trim().equals("true");
			    			break;
			    		case 8 :
			    			subscriber = lineInFile.trim().equals("true");
			    			break;
				    	}
	
				    	lineInFile = reader.readLine(); // Read the next line of the current file
				    }
				    
				    User user = new User(code, null, isAdmin, null, null, email, password, surname, name, adresse, secretSentence);
				    addUser(user);
				    // subscriber
				}
			    
			} catch (IOException e) { // Display a message if an error has occurred while reading in the files
		        System.out.println("An error occurred : We could not get saved informations");
		    }
		}
	}
}
