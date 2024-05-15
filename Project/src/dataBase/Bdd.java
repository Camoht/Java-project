package dataBase;

import java.io.File;
import java.util.List;

import metier.*;

public class Bdd {

	// Fields
	private Movies movies;
	private Users users;
	private Comments comments;
	private Purchases purchases;
	private Scores scores;
	
	// Publics functions
	public Bdd() {
		this.movies = new Movies() ;
		this.users = new Users() ;
		this.comments = new Comments() ;
		this.purchases = new Purchases() ;
		this.scores = new Scores() ;
		
		readSavedBdd();
	}
	public void close() {
		saveBdd();
	}
	public void saveBdd() {
		// Delete old bdd
		/*
		 * 
		 */
		movies.saveMovies();
		users.savedUsers();
		comments.saveComments();
	}
	private void readSavedBdd() {

		// Check if there is saved data, if not : stop the function (there is no data to read)
		if(! new File("Bdd").exists()) {
			return;
		}
		
		movies.readSavedMovies();
		users.readSavedUsers();
		comments.readSavedComments();
	}

	// Getters
	public Movies getMovies() {
		return this.movies;
	}
	public Users getUsers() {
		return this.users;
	}
}