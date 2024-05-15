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

import comparators.commentComparatorByDate;
import comparators.commentComparatorByPositivity;
import comparators.movieComparatorByDate;
import comparators.movieMostAppreciated;
import metier.Comment;

public class Comments {

	// Fields
	private List<Comment> comments = new ArrayList<Comment>();
	private static String[] commentFields = {
		    "Author :",
		    "Movie :",
		    "Description :",
		    "Positive :",
		    "Active :"
	};
	
	// Sorters
	public void sortCommentByDate() {
        Collections.sort(comments, new commentComparatorByDate());
	}
	public void sortCommentByPositivity() {
        Collections.sort(comments, new commentComparatorByPositivity());
	}
	public void sortCommentByNegatity() {
        Collections.sort(comments, new commentComparatorByPositivity());
        Collections.reverse(comments);
	}
	
	// Saver
	public void saveComments() {
		try {
			
			// Create the necessary folders if there are not
			new File("Bdd").mkdirs();
			new File("Bdd/Comments").mkdirs();
			
			// Needed variable to write data in files
		    BufferedWriter writer;

			// Save Comments :
			for (int i = 0; i < this.comments.size(); i++) {
				
				// Write data in the file
			    writer = new BufferedWriter(new FileWriter("Bdd/Comments/" /*+  comments.get(i).getId()*/));
			    
			    /*
			    writer.write(commentFields[0] + "\n");
			    writer.write(comments.get(i).getAuthor() + "\n");
			    writer.write(commentFields[1] + "\n");
			    writer.write(comments.get(i).getMovie() + "\n");
			    writer.write(commentFields[2] + "\n");
			    writer.write(comments.get(i).getDescription() + "\n");
			    writer.write(commentFields[3] + "\n");
			    writer.write(comments.get(i).getPositive() + "\n");
			    writer.write(commentFields[4] + "\n");
			    writer.write(comments.get(i).getActive() + "\n");
				*/
			    
			    writer.close();
			}
	
	    } catch (IOException e) { // Display a message if an error has occurred while creating the file / writing in the files
	        System.out.println("An error occurred : We could not save your modifications");
	    }
	}

	void readSavedComments() {
		File bddCommentsDirectory = new File("Bdd/Comments");
		
		// Check if there is saved data, if not : stop the function (there is no data to read)
		if(! bddCommentsDirectory.exists()) {
			return;
		}

		// Get saved Comments
		File[] listOfFile = bddCommentsDirectory.listFiles();
		for (int i = 0; i < listOfFile.length; i++) { // For each existing file
			
			// Create needed variables to add a user
			String author = "";
			String movie = "";
			String description = "";
			boolean positive = false;
			boolean active = false;
			
			try {
				
				// Create needed variables
				BufferedReader reader = new BufferedReader(new FileReader(listOfFile[i])); // to read the current file
			    int idField = -1; // Correspond to an id of the actual saving field
			    boolean isTitle; // true : the read line is a title corresponding to an idField; false : the read line is a field content

			    String lineInFile = reader.readLine(); // Read the first line of the current file
			    while(lineInFile != null) {
		    		isTitle = false;
			    	
		    		// Get the idFields if the line is one
			    	for(int j = 0; j < Comments.commentFields.length; j++) {
				    	if (lineInFile.equals(Comments.commentFields[j])){
				    		idField = j;
				    		isTitle = true;
				    	}
			    	}
			    	
			    	// Get the value of the current field
			    	if(! isTitle) { // The line is a field content
			    		switch(idField) {
			    		case 0 :
			    			author = lineInFile.trim();
			    			break;
			    		case 1 :
			    			movie = lineInFile.trim();
			    			break;
			    		case 2 :
			    			description += lineInFile.trim();
			    			break;
			    		case 3 :
			    			positive = lineInFile.trim().equals("true");
			    			break;
			    		case 4 :
			    			active = lineInFile.trim().equals("true");
			    			break;
			    		}
			    	}

			    	lineInFile = reader.readLine(); // Read the next line of the current file
			    }

			    reader.close();
			    
			    // addAdmin(new User(listOfFile[i], password, surname, name, subscriber));
			} catch (IOException e) { // Display a message if an error has occurred while reading in the files
		        System.out.println("An error occurred : We could not get saved informations");
		    }
		}
	}
}
