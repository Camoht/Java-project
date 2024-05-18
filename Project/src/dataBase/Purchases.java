package dataBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import metier.Purchase;
import metier.User;

public class Purchases {

	// Fields
	private List<Purchase> purchases = new ArrayList<Purchase>();
	private static String[] purchaseFields = {
			"Code :",
		    "User code :",
			"Buying Date :",
		    "Amont :"
	};
	
	// Initialize
	public Purchases() {
		this.purchases = new ArrayList<Purchase>();
	}
	
	// Saver
	public void savePurchases() {
		try {
			
			// Create the necessary folders if there are not
			new File("Bdd").mkdirs();
			new File("Bdd/Purchases").mkdirs();
			
			// Needed variable to write data in files
		    BufferedWriter writer;
		    
			// Save Movies :
			for (int i = 0; i < this.purchases.size(); i++) {
			    
				// Write data in the file
			    writer = new BufferedWriter(new FileWriter("Bdd/Purchases/" +  purchases.get(i).getCode() + ".txt"));
			    
			    writer.write(purchaseFields[0] + "\n");
			    writer.write(purchases.get(i).getCode() + "\n");
			    writer.write(purchaseFields[1] + "\n");
			    writer.write(purchases.get(i).getUser().getCode() + "\n");
			    writer.write(purchaseFields[2] + "\n");
			    writer.write(purchases.get(i).getDateAchat().getTime() + "\n");
			    writer.write(purchaseFields[3] + "\n");
			    writer.write(purchases.get(i).getMontant() + "\n");
			    
			    writer.close();
			}
			
	    } catch (IOException e) { // Display a message if an error has occurred while creating the file / writing in the files
	        System.out.println("An error occurred : We could not save your modifications about purchases");
	    }	
	}
	public void readSavedPurchases(Users users) {
		File bddMoviesDirectory = new File("Bdd/Purchases");
		
		// Check if there is saved data, if not : stop the function (there is no data to read)
		if(! bddMoviesDirectory.exists()) { 
			System.out.println("Bdd does not exist");
			return;
		}

		// Get saved Movies
		File[] listOfFile = bddMoviesDirectory.listFiles();
		for (int i = 0; i < listOfFile.length; i++) { // For each existing file
			
			// Create needed variables to add a movie
			int code = -1;
			int userCode = -1;
			Date buyingDate = null;
			double amont = 0;
			String temp = "";
			
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
			    	for(int j = 0; j < Purchases.purchaseFields.length; j++) {
				    	if (lineInFile.equals(Purchases.purchaseFields[j])){
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
			    			userCode = Integer.parseInt(lineInFile.trim());
			    			break;
			    		case 2 :
			    			buyingDate = new Date(Long.parseLong(lineInFile.trim()));
			    			break;
			    		case 3 :
			    			temp = lineInFile.trim();
			    			if(temp.split("\\.").length >= 2) {
			    				amont = Integer.parseInt(temp.split("\\.")[0]) + 0.01 * Integer.parseInt(temp.split("\\.")[1]);
			    			} else {
			    				amont = Integer.parseInt(temp);
			    			}
			    			break;
		    			default :
		    				break;
			    		}
			    	}

			    	lineInFile = reader.readLine(); // Read the next line of the current file
			    }

			    addPurchase(new Purchase(code, users.getUser(userCode), buyingDate, amont));
			
			} catch (IOException e) { // Display a message if an error has occurred while reading in the files
		        System.out.println("An error occurred : We could not get saved informations");
		    }
		}
	}
	
	// Getter
	public List<Purchase> getPurchases(){
		return this.purchases;
	}
	public Purchase getPurchase(int code) {
		for (int i = 0; i < this.purchases.size(); i++) {
			if (this.purchases.get(i).getCode() == code) {
				return this.purchases.get(i);
			}
		}
		return null;
	}
	
	// Adders and Destroyers
	public void addPurchase(Purchase purchase) {
		this.purchases.add(purchase);
	}
	public void addPurchase(User user, double montant) {
		this.purchases.add(new Purchase(this.purchases.size() + 1, user, new Date(System.currentTimeMillis()), montant));
	}
	public void deletePurchase(Purchase purchase) {
		this.purchases.remove(purchase);
	}
	public void deletePurchase(int code) {
		for(int i = 0; i < this.purchases.size(); i++) {
			if(this.purchases.get(i).getCode() == code) {
				this.purchases.remove(i);
				return;
			}
		}
	}
}
