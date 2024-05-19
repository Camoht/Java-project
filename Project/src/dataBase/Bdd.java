package dataBase;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		
		test();
		readSavedBdd();
		//testSavedBdd();
	}
	
	// Publics functions
	@SuppressWarnings("deprecation")
	public void test() {
		// Users
		this.users.addUser(true, "email", "mdp", "nom", "prenom", "adresse", "mot");
		this.users.addUser(true, "emailA", "mdpA", "nomA", "prenomA", "adresseA", "motA");
		// Purchase
		this.addPurchase(1, 12);
		this.addPurchase(1, 4);
		// Movies
		Date date = new Date(2003, 03, 05);
		List<String> acteursPrincipaux = new ArrayList<>();
		acteursPrincipaux.add("acteurA");
		acteursPrincipaux.add("ActeurB");
		List<String> producteurs = new ArrayList<>();
		producteurs.add("producteurA");
		producteurs.add("producteurB");
		this.movies.addMovie("titre", "theme", date, "description", acteursPrincipaux, producteurs, 2);

		date = new Date(2001, 12, 05);
		acteursPrincipaux = new ArrayList<>();
		acteursPrincipaux.add("acteurA2");
		acteursPrincipaux.add("ActeurB2");
		producteurs = new ArrayList<>();
		producteurs.add("producteurA2");
		producteurs.add("producteurB2");
		this.movies.addMovie("titre2", "theme2", date, "description2", acteursPrincipaux, producteurs, 3);
		// Comment
		this.comments.addComment("text du commentaire", this.getMovies().getMovie(1), this.getUsers().getUser(1));
		//this.comments.getComment(1).setActivated(false);
		this.movies.getMovie(1).addComment(this.comments.getComment(1));
		this.users.getUser(1).addMovieToPanier(this.movies.getMovie(1));
		this.users.getUser(1).addMovieToPanier(this.movies.getMovie(2));
		// Score
		//this.scores.addScore(new Score(1, 4, null, this.movies.getMovie(1), this.users.getUser(1)));
	}
	@SuppressWarnings("deprecation")
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
			System.out.print("Purchases numéro = ");
			for(int j = 0; j < this.getUsers().getUsers().get(i).getHistoriqueAchats().size(); j++) {
				System.out.print(this.getUsers().getUsers().get(i).getHistoriqueAchats().get(j).getCode());
				if(j != this.getUsers().getUsers().get(i).getHistoriqueAchats().size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println();
			System.out.println();
		}
		// Movie
		for (int i = 0; i < this.getMovies().getMovies().size(); i++) {
			System.out.println("Movie numéro = " + this.getMovies().getMovies().get(i).getCode());
			System.out.println("Titre = " + this.getMovies().getMovies().get(i).getTitle());
			System.out.println("Description = " + this.getMovies().getMovies().get(i).getDescription());
			System.out.println("Nombre de vote = " + this.getMovies().getMovies().get(i).getScores().size());
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
			System.out.println("Activated = " + this.comments.getComments().get(i).isActivated());
			System.out.println("Movie id = " + this.comments.getComments().get(i).getMovie().getCode());
			System.out.println("Author id = " + this.comments.getComments().get(i).getAuthor().getCode());
			System.out.println();
		}
		// Purchase
		for (int i = 0; i < this.purchases.getPurchases().size(); i++) {
			System.out.println("Purchase numéro = " + this.purchases.getPurchases().get(i).getCode());
			System.out.println("Montant = " + this.purchases.getPurchases().get(i).getMontant());
			System.out.println("Date d'achat = " + this.purchases.getPurchases().get(i).getDateAchat());
			System.out.println("Code de l'acheteur = " + this.purchases.getPurchases().get(i).getUser().getCode());
			System.out.println();
		}
		// Score
		for (int i = 0; i < this.scores.getScores().size(); i++) {
			System.out.println("Score numéro = " + this.scores.getScores().get(i).getCode());
			System.out.println("Montant = " + this.scores.getScores().get(i).getValue());
			System.out.println("Movie numéro = " + this.scores.getScores().get(i).getMovie().getCode());
			System.out.println("User numéro = " + this.scores.getScores().get(i).getUser().getCode());
			System.out.println();
		}
	}
	public void close() {
		saveBdd();
		testSavedBdd();
		deleteBddFile(new File("Bdd"));
	}
	public void saveBdd() {
		deleteBddFile(new File("Bdd")); // Delete old bdd
		movies.saveMovies();
		users.savedUsers();
		comments.saveComments();
		purchases.savePurchases();
		scores.saveScores();
	}
	private void readSavedBdd() {

		// Check if there is saved data, if not : stop the function (there is no data to read)
		if(! new File("Bdd").exists()) {
			return;
		}
		
		// Create every detected objects
		movies.readSavedMovies();
		users.readSavedUsers();
		comments.readSavedComments(movies, users);
		purchases.readSavedPurchases(users, movies);
		scores.readSavedScores(users, movies);
		
		// Connect objects between them
		for(int i = 0; i < purchases.getPurchases().size(); i++) { // Connect Purchases
			this.users.getUser(this.purchases.getPurchase(i).getUser().getCode()).addPurchaseToHistorique(this.purchases.getPurchase(i)); // Add Purchase to User
		}
		for(int i = 0; i < scores.getScores().size(); i++) { // Connect Scores
			this.users.getUser(this.scores.getScore(i).getUser().getCode()).addScore(this.scores.getScore(i)); // Add Score to User
			this.movies.getMovie(this.scores.getScore(i).getMovie().getCode()).addScore(this.scores.getScore(i)); // Add Score to Movie
		}
		for(int i = 0; i < scores.getScores().size(); i++) { // Connect Comment
			this.users.getUser(this.comments.getComment(i).getAuthor().getCode()).addComment(this.comments.getComment(i)); // Add Comment to User
			this.movies.getMovie(this.comments.getComment(i).getMovie().getCode()).addComment(this.comments.getComment(i)); // Add Comment to Movie
		}
	}
	boolean deleteBddFile(File directoryToBeDeleted) { // Delete Bdd files
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	        	deleteBddFile(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
	
	// Getters
	public Movies getMovies() {
		return this.movies;
	}
	public Users getUsers() {
		return this.users;
	}
	public Comments getComments() {
		return this.comments;
	}
	public Purchases getPurchases() {
		return this.purchases;
	}
	public Scores getScores() {
		return this.scores;
	}

	// Adders
	public void addComment(int userId, int movieId, String text) {
		int commentId = this.comments.getComments().size() + 1; // Get Comment id

		// If the user has never made a comment about this movie
		this.comments.addComment(text, this.movies.getMovie(movieId), this.users.getUser(userId)); // Create comment object
		this.movies.getMovie(movieId).addComment(this.comments.getComment(commentId)); // Add the comment object to the movie object
		this.users.getUser(userId).addComment(this.comments.getComment(commentId)); // Add the comment object to the user object
	}
	public void addScore(int userId, int movieId, Long value) {
		
		// If the user has already made a comment about this movie
		for(int i = 0; i < this.users.getUser(userId).getScores().size(); i++) {
			if(this.users.getUser(userId).getScores().get(i).getMovie().getCode() == movieId) {
				this.users.getUser(userId).getScores().get(i).setValue(value); // Change score content
				return;
			}
		}
		
		int scoreId = this.scores.getScores().size() + 1; // Get Score id
		
		// If the user has never gave a score about this movie
		this.scores.addScore(value, this.movies.getMovie(movieId), this.users.getUser(userId));
		this.movies.getMovie(movieId).addScore(this.scores.getScore(scoreId)); // Add the score object to the movie object
		this.users.getUser(userId).addScore(this.scores.getScore(scoreId)); // Add the score object to the user object
	}
	public void addPurchase(int userId, long montant) {
		int purchaseId = this.purchases.getPurchases().size() + 1; // Get Purchase id
		this.purchases.addPurchase(this.users.getUser(userId), montant, this.users.getUser(userId).getPanier());
		this.users.getUser(userId).addPurchaseToHistorique(this.purchases.getPurchase(purchaseId));
	}
}