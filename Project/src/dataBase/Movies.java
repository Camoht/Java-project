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
import metier.Comment;
import metier.Movie;
import metier.Purchase;
import metier.Score;
import metier.User;

public class Movies {

	// Fields
	private List<Movie> movies = new ArrayList<Movie>();
	private static String[] movieFields = {
			"Code :",
		    "Theme :",
		    "Producers :",
		    "Main Actors :",
			"Production Date :",
		    "Score :",
			"Mean Score :",
			"Comment :",
		    "Description :",
		    "Price :"
	};

	// Getters
	public List<Movie> getMovies(){
		return movies;
	}
	public Movie getMovie(int code) {
		for (int i = 0; i < this.movies.size(); i++) {
			if (this.movies.get(i).getCode() == code) {
				return this.movies.get(i);
			}
		}
		return null;
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
	public void addMovie(String theme, Date productionDate, String description, List<String> acteursPrincipaux, List<String> producteurs) {
		this.movies.add(new Movie(this.movies.size() + 1, theme, productionDate, description, acteursPrincipaux, producteurs, null, null, 0, 0));
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
			    writer = new BufferedWriter(new FileWriter("Bdd/Movies/" +  movies.get(i).getCode() + ".txt"));
			    
			    writer.write(movieFields[0] + "\n");
			    writer.write(movies.get(i).getCode() + "\n");
			    writer.write(movieFields[1] + "\n");
			    writer.write(movies.get(i).getTheme() + "\n");
			    writer.write(movieFields[2] + "\n");
			    for (int j = 0; j < movies.get(i).getProducteurs().size(); j++){
			    	writer.write(movies.get(i).getProducteurs().get(j) + "\n");
			    }
			    writer.write(movieFields[3] + "\n");
			    for (int j = 0; j < movies.get(i).getActeursPrincipaux().size(); j++){
			    	writer.write(movies.get(i).getActeursPrincipaux().get(j) + "\n");
			    }
			    writer.write(movieFields[4] + "\n");
			    writer.write(movies.get(i).getProductionDate().getTime() + "\n");
			    writer.write(movieFields[5] + "\n");
			    for (int j = 0; j < movies.get(i).getScores().size(); j++){
				    writer.write(movies.get(i).getScores().get(j) + "\n");
			    }
			    writer.write(movieFields[6] + "\n");
			    writer.write(movies.get(i).getNoteMoyenne() + "\n");
			    writer.write(movieFields[7] + "\n");
			    for (int j = 0; j < movies.get(i).getComments().size(); j++){
				    writer.write(movies.get(i).getComments().get(j) + "\n");
			    }
			    writer.write(movieFields[8] + "\n");
			    writer.write(movies.get(i).getDescription() + "\n");
			    // Price
			    
			    writer.close();
			}
			
	    } catch (IOException e) { // Display a message if an error has occurred while creating the file / writing in the files
	        System.out.println("An error occurred : We could not save your modifications about movies");
	    }	
	}
	@SuppressWarnings("deprecation")
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
			int code = -1;
			String theme = "";
			List<String> producers = new ArrayList<String>();
			List<String> mainActors = new ArrayList<String>();
			Date productionDate = null;
			double meanScore = 0;
			String description = "";
			String temp = "";
			// Price
			
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
			    			code = Integer.parseInt(lineInFile.trim());
			    			break;
			    		case 1 :
			    			theme = lineInFile.trim();
			    			break;
			    		case 2 :
			    			producers.add(lineInFile.trim());
			    			break;
			    		case 3 :
			    			mainActors.add(lineInFile.trim());
			    			break;
			    		case 4 :
			    			productionDate = new Date(Long.parseLong(lineInFile.trim()));
			    			break;
			    		case 6 :
			    			temp = lineInFile.trim();
			    			if(temp.split("\\.").length >= 2) {
				    			meanScore = Integer.parseInt(temp.split("\\.")[0]) + 0.01 * Integer.parseInt(temp.split("\\.")[1]);
			    			} else {
				    			meanScore = Integer.parseInt(temp);
			    			}
			    			break;
			    		case 8 :
			    			description = lineInFile.trim();
			    			break;
			    		// Price
		    			default :
		    				break;
			    		}
			    	}

			    	lineInFile = reader.readLine(); // Read the next line of the current file
			    }

			    Movie movie = new Movie(code, theme, productionDate, description, mainActors, producers, null, null, meanScore, 0);
			    addMovie(movie);
			
			} catch (IOException e) { // Display a message if an error has occurred while reading in the files
		        System.out.println("An error occurred : We could not get saved informations");
		    }
		}
	}
}