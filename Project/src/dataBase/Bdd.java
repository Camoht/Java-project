package dataBase;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import metier.*;

public class Bdd {

	// Fields
	private Movies movies;
	private Users users;
	private Comments comments;
	private Purchases purchases;
	private Scores scores;
	
	// Initialize 
	public Bdd() {
		this.movies = new Movies() ;
		this.users = new Users() ;
		this.comments = new Comments() ;
		this.purchases = new Purchases() ;
		this.scores = new Scores() ;
		
		//test();
		readSavedBdd();
		testSavedBdd();
	}
	
	// Publics functions
	public void test() {
		// Users
		this.users.addUser(false, "email", "mdp", "nom", "prenom", "adresse", "mot");
		//this.users.addUser(true, "emailA", "mdpA", "nomA", "prenomA", "adresseA", "motA");
		// Purchase
		/*
		this.users.addPurchase();
		this.users.addPurchase();
		*/
		// Movies
		@SuppressWarnings("deprecation")
		Date date = new Date(2003, 03, 05);
		List<String> acteursPrincipaux = new ArrayList<>();
		acteursPrincipaux.add("acteurA");
		acteursPrincipaux.add("ActeurB");
		List<String> producteurs = new ArrayList<>();
		producteurs.add("producteurA");
		producteurs.add("producteurB");
		this.movies.addMovie("theme", date, "description", acteursPrincipaux, producteurs);
		// Comment
		this.comments.addComment("text", this.getMovies().getMovie(1), this.getUsers().getUser(1));
		// Score
		/*
		this.users.addScore();
		this.users.addScore();
		*/
	}
	public void testSavedBdd() {
		// User
		for (int i = 0; i < this.getUsers().getUsers().size(); i++) {
			System.out.println("User numéro = " + this.getUsers().getUsers().get(i).getCode());
			System.out.println("Email = " + this.getUsers().getUsers().get(i).getEmail());
			System.out.println("Mot de passe = " + this.getUsers().getUsers().get(i).getMotDePasse());
			System.out.println("Nom = " + this.getUsers().getUsers().get(i).getNom());
			System.out.println("Prenom = " + this.getUsers().getUsers().get(i).getPrenom());
			System.out.println("Adresse = " + this.getUsers().getUsers().get(i).getAdresse());
			System.out.println("Mot secret = " + this.getUsers().getUsers().get(i).getPhraseSecrete());
			System.out.println("Est Admin = " + this.getUsers().getUsers().get(i).getIsAdmin());
			System.out.println();
		}
		// Movie
		for (int i = 0; i < this.getMovies().getMovies().size(); i++) {
			System.out.println("Movie numéro = " + this.getMovies().getMovies().get(i).getCode());
			System.out.println("Description = " + this.getMovies().getMovies().get(i).getDescription());
			System.out.println("Nombre de vote = " + this.getMovies().getMovies().get(i).getNombreDeVotes());
			System.out.println("Moyenne des notes = " + this.getMovies().getMovies().get(i).getNoteMoyenne());
			System.out.println("Theme = " + this.getMovies().getMovies().get(i).getTheme());
			System.out.println("Acteurs principaux = " + this.getMovies().getMovies().get(i).getActeursPrincipaux());
			System.out.println("Producteurs = " + this.getMovies().getMovies().get(i).getProducteurs());
			System.out.println("Date de production = " + this.getMovies().getMovies().get(i).getProductionDate().getDay() + this.getMovies().getMovies().get(i).getProductionDate().getDay() + "/" + this.getMovies().getMovies().get(i).getProductionDate().getMonth() + "/" + this.getMovies().getMovies().get(i).getProductionDate().getYear());
			System.out.println();
		}
		// Comment
		for (int i = 0; i < this.comments.getComments().size(); i++) {
			System.out.println("Comment numéro = " + this.comments.getComments().get(i).getCode());
			System.out.println("Texte = " + this.comments.getComments().get(i).getText());
			System.out.println("Publication Date = " + this.comments.getComments().get(i).getPublicationDate());
			System.out.println("Activated = " /*+ this.comments.getComments().get(i).getActivated()*/);
			System.out.println("Movie id = " + this.comments.getComments().get(i).getMovie().getCode());
			System.out.println("Author id = " + this.comments.getComments().get(i).getAuthor().getCode());
			System.out.println();
		}
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