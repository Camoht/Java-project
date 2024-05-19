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

import metier.Movie;
import metier.Score;
import metier.User;

public class Scores {

	// Fields
	private List<Score> scores = new ArrayList<Score>();
	private static String[] scoreFields = {
			"Code :",
		    "Value :",
			"Publication Date :",
		    "Movie Code :",
		    "User Code :"
	};
    
	// Initialize
	public Scores() {
		this.scores = new ArrayList<Score>();
	}
	
	// Getter
 	public List<Score> getScores(){
 		return scores;
 	}
	public Score getScore(int code) {
		for (int i = 0; i < this.scores.size(); i++) {
			if (this.scores.get(i).getCode() == code) {
				return this.scores.get(i);
			}
		}
		return null;
	}
	
	// Saver
	public void saveScores() {
		try {
			
			// Create the necessary folders if there are not
			new File("Bdd").mkdirs();
			new File("Bdd/Scores").mkdirs();
			
			// Needed variable to write data in files
		    BufferedWriter writer;
		    
			// Save Movies :
			for (int i = 0; i < this.scores.size(); i++) {
			    
				// In the file
			    writer = new BufferedWriter(new FileWriter("Bdd/Purchases/" +  scores.get(i).getCode() + ".txt"));
			    
			    // Write data
			    writer.write(scoreFields[0] + "\n");
			    writer.write(scores.get(i).getCode() + "\n");
			    writer.write(scoreFields[1] + "\n");
			    writer.write(scores.get(i).getValue() + "\n");
			    writer.write(scoreFields[2] + "\n");
			    writer.write(scores.get(i).getDate() + "\n");
			    writer.write(scoreFields[3] + "\n");
			    writer.write(scores.get(i).getMovie().getCode() + "\n");
			    writer.write(scoreFields[4] + "\n");
			    writer.write(scores.get(i).getUser().getCode() + "\n");
			    
			    writer.close();
			}
			
	    } catch (IOException e) { // Display a message if an error has occurred while creating the file / writing in the files
	        System.out.println("An error occurred : We could not save your modifications about scores");
	    }	
	}
	public void readSavedScores(Users users, Movies movies) {
		
		File bddMoviesDirectory = new File("Bdd/Scores");
		
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
			long value = -1;
			Date publicationDate = null;
			int movieCode = -1;
			int userCode = -1;
			
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
			    	for(int j = 0; j < Scores.scoreFields.length; j++) {
				    	if (lineInFile.equals(Scores.scoreFields[j])){
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
			    			value = Long.parseLong(lineInFile.trim());
			    			break;
			    		case 2 :
			    			publicationDate = new Date(Long.parseLong(lineInFile.trim()));
			    			break;
			    		case 3 :
			    			movieCode = Integer.parseInt(lineInFile.trim());
			    			break;
			    		case 4 :
			    			userCode = Integer.parseInt(lineInFile.trim());
			    			break;
		    			default :
		    				break;
			    		}
			    	}

			    	lineInFile = reader.readLine(); // Read the next line of the current file
			    }

			    addScore(new Score(code, value, publicationDate, movies.getMovie(movieCode), users.getUser(userCode)));
			
			} catch (IOException e) { // Display a message if an error has occurred while reading in the files
		        System.out.println("An error occurred : We could not get saved informations");
		    }
		}
	}
	
	// Adders
	public void addScore(Score score) {
		this.scores.add(score);
	}
	public void addScore(long value, Movie movie, User author) {
		this.scores.add(new Score(this.scores.size() + 1, value, new Date(System.currentTimeMillis()), movie, author));
	}
	
	public void deleteScore(Score score) {
		this.scores.remove(score);
	}
	public void deleteScore(int code) {
		
		for(int i = 0; i < this.scores.size(); i++) { // For each score
			if(this.scores.get(i).getCode() == code) { // If his code is the one we are searching or
				this.scores.remove(i);
				return;
			}
		}
	}
}
