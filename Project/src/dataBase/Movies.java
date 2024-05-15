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
import comparators.movieComparatorByDate;
import comparators.movieMostAppreciated;
import metier.Admin;
import metier.Movie;
import metier.User;

public class Movies {

	// Fields
	private List<Movie> movies = new ArrayList<Movie>();
	private static String[] movieFields = {
		    "Theme :",
		    "Producers :",
		    "Main Actors :",
			"Production Year :",
		    "Rating :",
		    "Description :",
		    "Price :"
	};


	// Getters
	public List<Movie> getMovies(){
		return movies;
	}
	public List<Movie> getMoviesOfTheme(String theme){
		List<Movie> res = new ArrayList<Movie>();
		
		for(int i = 0; i < this.movies.size(); i++) {
			if (this.movies.get(i).getTheme().contains(theme)) {
				res.add(this.movies.get(i));
			}
		}
		
		return res;
	}
	
	// Adder
	public void addMovie(Movie movie) {
		this.movies.add(movie);
	}
	
	// Sorters
	public void sortMovieByScore() {
        Collections.sort(movies, new movieMostAppreciated());
        Collections.reverse(movies);
	}
	public void sortMovieByDate() {
        Collections.sort(movies, new movieComparatorByDate());
	}
	public int getMediumNumberOfCommentPerMovie() {
		int nbComment = 0;
		
		for(int i = 0; i < this.movies.size(); i++) { // For each movie
			nbComment += this.movies.get(i).getComments().size(); // Get number of comment
		}		
		
		return nbComment / this.movies.size();
	}
	public int getMediumScorePerMovie() {
		int res = 0;
		int nbComment = 0;
		
		for(int i = 0; i < this.movies.size(); i++) { // For each movie
			for (int j = 0; j < this.movies.get(i).getScores().size(); j++) { // For each score
				res += this.movies.get(i).getScores().get(j).getValue();
				nbComment ++;
			}
		}		
		
		return res / nbComment;
	}
	
	// Destroyers
	public boolean deleteMovie(Movie movie) {
		return this.movies.remove(movie);
		// And delete associated score
	}
	
	// Searchers
	public List<Movie> searchMovieByTitle(String title){
		List<Movie> res = new ArrayList<Movie>();
		
		for(int i = 0; i < this.movies.size(); i++) {
			/*if(this.movies.get(i).getTitle().contains(title)) {
				res.add(this.movies.get(i));
			}*/
		}
		
		return res;
	}
	public List<Movie> searchMovieByTheme(String theme){
		List<Movie> res = new ArrayList<Movie>();
		
		for(int i = 0; i < this.movies.size(); i++) { // For each movie
			/*for(j = 0; j < this.movies.get(i).getTheme().size(); j++) { // For each theme
				if(this.movies.get(i).getTheme().get(j).contains(theme)) {
					res.add(this.movies.get(i));
				}
			}*/
		}
		
		return res;
	}
	public List<Movie> searchMovieByProducer(String producer){
		List<Movie> res = new ArrayList<Movie>();
		
		for(int i = 0; i < this.movies.size(); i++) { // For each movie
			/*for(j = 0; j < this.movies.get(i).getProducers().size(); j++) { // For each producer
				if(this.movies.get(i).getProducers().get(j).contains(theme)) {
					res.add(this.movies.get(i));
				}
			}*/
		}
		
		return res;
	}
	public List<Movie> searchMovieByActor(String actor){
		List<Movie> res = new ArrayList<Movie>();
		
		for(int i = 0; i < this.movies.size(); i++) { // For each movie
			/*for(j = 0; j < this.movies.get(i).getProducers().size(); j++) { // For each producer
				if(this.movies.get(i).getProducers().get(j).contains(theme)) {
					res.add(this.movies.get(i));
				}
			}*/
		}
		
		return res;
	}
	public List<Movie> searchMovieByProductionDate(Date date){
		List<Movie> res = new ArrayList<Movie>();
		
		for(int i = 0; i < this.movies.size(); i++) {
			/*if(this.movies.get(i).getProductionDate().getYear() == date.getYear()) {
				res.add(this.movies.get(i));
			}*/
		}
		
		return res;
	}
	public List<Movie> searchMovieByScore(long score){
		List<Movie> res = new ArrayList<Movie>();
		
		for(int i = 0; i < this.movies.size(); i++) {
			/*if(this.movies.get(i).getScore() >= score) {
				res.add(this.movies.get(i));
			}*/
		}
		
		return res;
	}
	public List<Movie> searchMovieByPrice(long price){
		List<Movie> res = new ArrayList<Movie>();
		
		for(int i = 0; i < this.movies.size(); i++) {
			/*if(this.movies.get(i).getPrice() <= price) {
				res.add(this.movies.get(i));
			}*/
		}
		
		return res;
	}
	
	// Saver
	public void saveMovies() {
		try {
			
			// Create the necessary folders if there are not
			new File("Bdd").mkdirs();
			new File("Bdd/Movies").mkdirs();
			
			// Needed variable to write data in files
		    BufferedWriter writer;
		    
			// Save Movies :
			for (int i = 0; i < this.movies.size(); i++) {
				
				// Write data in the file
			    writer = new BufferedWriter(new FileWriter("Bdd/Movies/" /*+  movies.get(i).getCode()*/));
			    
			    /*
			    writer.write(movieFields[0] + "\n");
			    writer.write(movies.get(i).getTheme() + "\n");
			    writer.write(movieFields[1] + "\n");
			    for (int j = 0; j < movies.get(i).getProducers().length; j++){
			    	writer.write(movies.get(i).getProducers().get(j) + "\n");
			    }
			    writer.write(movieFields[2] + "\n");
			    for (int j = 0; j < movies.get(i).getMainActors().length; j++){
			    	writer.write(movies.get(i).getMainActors().get(j) + "\n");
			    }
			    writer.write(movieFields[3] + "\n");
			    writer.write(movies.get(i).getProductionYear() + "\n");
			    writer.write(movieFields[4] + "\n");
			    writer.write(movies.get(i).getRating() + "\n");
			    writer.write(movieFields[5] + "\n");
			    writer.write(movies.get(i).getDescription() + "\n");
			    writer.write(movieFields[6] + "\n");
			    writer.write(movies.get(i).getPrice() + "\n");
				*/
			    
			    writer.close();
			}
			
	    } catch (IOException e) { // Display a message if an error has occurred while creating the file / writing in the files
	        System.out.println("An error occurred : We could not save your modifications about movies");
	    }	
	}
	
	void readSavedMovies() {
		File bddMoviesDirectory = new File("Bdd/Movies");
		
		// Check if there is saved data, if not : stop the function (there is no data to read)
		if(! bddMoviesDirectory.exists()) { 
			System.out.println("Bdd does not exist");
			return;
		}

		// Get saved Movies
		File[] listOfFile = bddMoviesDirectory.listFiles();
		for (int i = 0; i < listOfFile.length; i++) { // For each existing file
			
			// Create needed variables to add a movie
			String theme = "";
			List<String> producers = new ArrayList<String>();
			List<String> mainActors = new ArrayList<String>();
			int productionYear = 0;
			int rating = 0;
			String description = "";
			String price = "";
			
			try {
				
				// Create needed variables
				BufferedReader reader = new BufferedReader(new FileReader(listOfFile[i])); // to read the current file
			    int idField = -1; // Correspond to an id of the actual saving field
			    boolean isTitle; // true : the read line is a title corresponding to an idField; false : the read line is a field content

			    String lineInFile = reader.readLine(); // Read the first line of the current file
			    while(lineInFile != null) {
		    		isTitle = false;
			    	
		    		// Get the idFields if the line is one
			    	for(int j = 0; j < Movies.movieFields.length; j++) {
				    	if (lineInFile.equals(Movies.movieFields[j])){
				    		idField = j;
				    		isTitle = true;
				    	}
			    	}
			    	
			    	// Get the value of the current field
			    	if(! isTitle) { // The line is a field content
			    		switch(idField) {
			    		case 0 :
			    			theme = lineInFile.trim();
			    			break;
			    		case 1 :
			    			producers.add(lineInFile.trim());
			    			break;
			    		case 2 :
			    			mainActors.add(lineInFile.trim());
			    			break;
			    		case 3 :
			    			if(lineInFile.trim().length() == 4) {
			    				productionYear = (int) ((lineInFile.trim().charAt(0) - '0') * 1000 + (lineInFile.trim().charAt(1) - '0') * 100 + (lineInFile.trim().charAt(2) - '0') * 10 + (lineInFile.trim().charAt(3) - '0'));
			    			}
			    			break;
			    		case 4 :
			    			if(lineInFile.trim().length() == 1) {
			    				rating = (int) ((lineInFile.trim().charAt(0) - '0') * 1000 + (lineInFile.trim().charAt(1) - '0') * 100 + (lineInFile.trim().charAt(2) - '0') * 10 + (lineInFile.trim().charAt(3) - '0'));
			    			}
			    			break;
			    		case 5 :
			    			if(lineInFile.trim().length() == 1) {
			    				description += lineInFile.trim() + "\n";
			    			}
			    			break;
			    		case 6 :
			    			if(lineInFile.trim().length() == 1) {
			    				price = lineInFile.trim();
			    			}
			    			break;
			    		}
			    	}

			    	lineInFile = reader.readLine(); // Read the next line of the current file
			    }

			    reader.close();
			    
			    // addMovie(new Movie(listOfFile[i], theme, producers, mainActors, productionYear, rating, description, price));
			
			} catch (IOException e) { // Display a message if an error has occurred while reading in the files
		        System.out.println("An error occurred : We could not get saved informations");
		    }
		}
	}
}
