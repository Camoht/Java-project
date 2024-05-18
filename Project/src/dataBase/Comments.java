package dataBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import comparators.commentComparatorByDate;
import comparators.commentComparatorByPositivity;
import metier.Comment;
import metier.Movie;
import metier.User;

public class Comments {

	// Fields
	private List<Comment> comments = new ArrayList<Comment>();
	private static String[] commentFields = {
		    "Code :",
		    "Text :",
		    "Publcation Date :",
		    "Activated :",
		    "Movie Code :",
		    "Author Code :"
	};
	
	// Initialize
	public Comments() {
		this.comments = new ArrayList<Comment>();
	}
	
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
			    writer = new BufferedWriter(new FileWriter("Bdd/Comments/" +  comments.get(i).getCode() + ".txt"));
			    writer.write(commentFields[0] + "\n");
			    writer.write(comments.get(i).getCode() + "\n");
			    writer.write(commentFields[1] + "\n");
			    writer.write(comments.get(i).getText() + "\n");
			    writer.write(commentFields[2] + "\n");
			    writer.write(comments.get(i).getPublicationDate().getTime() + "\n");
			    writer.write(commentFields[3] + "\n");
			    writer.write(comments.get(i).isActivated() + "\n");
			    writer.write(commentFields[4] + "\n");
			    writer.write(comments.get(i).getMovie().getCode() + "\n");
			    writer.write(commentFields[5] + "\n");
			    writer.write(comments.get(i).getAuthor().getCode() + "\n");
			    
			    writer.close();
			}
	
	    } catch (IOException e) { // Display a message if an error has occurred while creating the file / writing in the files
	        System.out.println("An error occurred : We could not save your modifications");
	    }
	}
	@SuppressWarnings("deprecation")
	void readSavedComments(Movies movies, Users users) {
		File bddCommentsDirectory = new File("Bdd/Comments");
		
		// Check if there is saved data, if not : stop the function (there is no data to read)
		if(! bddCommentsDirectory.exists()) {
			return;
		}

		// Get saved Comments
		File[] listOfFile = bddCommentsDirectory.listFiles();
		for (int i = 0; i < listOfFile.length; i++) { // For each existing file
			
			// Create needed variables to add a user
			int code = 0;
			String texte = "";
			Date publicationDate = new Date();
			boolean activated = false;
			int movieCode = 0;
			int authorCode = 0;
			
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
			    			code = Integer.parseInt(lineInFile.trim());
			    			break;
			    		case 1 :
			    			texte = lineInFile.trim();
			    			break;
			    		case 2 :
			    			publicationDate = new Date(lineInFile.trim());
			    			break;
			    		case 3 :
			    			activated = lineInFile.trim().equals("true");
			    			break;
			    		case 4 :
			    			movieCode = Integer.parseInt(lineInFile.trim());
			    			break;
			    		case 5 :
			    			authorCode = Integer.parseInt(lineInFile.trim());
			    			break;
			    		}
			    	}

			    	lineInFile = reader.readLine(); // Read the next line of the current file
			    }

			    addComment(new Comment(code, texte, publicationDate, activated, movies.getMovie(movieCode), users.getUser(authorCode)));
			    
			} catch (IOException e) { // Display a message if an error has occurred while reading in the files
		        System.out.println("An error occurred : We could not get saved informations");
		    }
		}
	}

	// Getter
 	public List<Comment> getComments(){
 		return comments;
 	}
	public Comment getComment(int code) {
		for (int i = 0; i < this.comments.size(); i++) {
			if (this.comments.get(i).getCode() == code) {
				return this.comments.get(i);
			}
		}
		return null;
	}

	// Adders and deletes
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}	
	public void addComment(String text, Movie movie, User author) {
		this.comments.add(new Comment(this.comments.size() + 1, text, new Date(System.currentTimeMillis()), true, movie, author));
	}
	public void deleteComment(Comment comment) {
		this.comments.remove(comment);
	}
	public void deleteComment(int code) {
		for(int i = 0; i < this.comments.size(); i++) {
			if(this.comments.get(i).getCode() == code) {
				this.comments.remove(i);
				return;
			}
		}
	}
}
