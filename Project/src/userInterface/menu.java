package userInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dataBase.*;
import metier.*;

public class menu {
	
	// Fields
	private static String[][] toWrite = {
			{"Se connecter", "Créer un compte", "Mot de passe oublié"} , // In the Welcome menu // 0
			{"Veuillez renseigner votre :", "Email", "Mot de passe", "Mail ou Mot de passe incorrect"} , // In the connect menu // 1
			{"Veuillez renseigner votre :", "Nom", "Prénom", "Mot de passe", "Email", "Adresse", "Votre mot préféré", "Cet adresse est déjà utilisée par un autre utilisateur, veuillez en choisir une autre"} , // In the create an account menu // 2
			{"Veuillez renseigner votre :", "Email", "Phrase secrête", "Mail ou Phrase secrête incorrect", "Sélectionner votre nouveau mot de passe"} , // In forgotten password menu // 3
			{"Afficher mes données", "Rechercher un film", "Explorer la liste des films", "Visualiser mon panier", "Visualiser mes achats"} , // In connected menu for client // 4
			{"Afficher statistiques", "Ajouter un film", "Ajouter un administrateur"} , // In connected menu for admin // 5
			{"Vos informations :", "Abonné", "Vous avez un compte administrateur", "Je souhaite : ", "Supprimer mon compte", "Modifier mon compte", "Votre compte a bien été supprimé"} , // Display informations about the user // 6
			{"Je souhaite modifier :", "Mon nom", "Mon prénom", "Mon mot de passe", "Mon Email", "Je change mon abonnement", "Je le change pour :"} , // Modify the user's account //  7
			{"Afficher les détails du film :", "Afficher plus de film"} , // Display the movie collection // 8
			{"Titre", "Catégories", "Producteurs", "Acteurs principaux", "Date de production", "Note moyenne", "Description", "Prix", "Afficher les commentaires", "Ajouter le film à mon panier", "Attribuer une note", "Ajouter un commentaire"} , // Display movie's details // 9
			{"Modifier les informations du film", "Supprimer le film"} , // In displayMovieDetails menu for admin // 10
			{"Je souhaite modifier :", "Titre", "Catégories", "Producteurs", "Acteurs principaux", "Année de production", "Description", "Prix", "Je le change pour :", "J'ajoute", "Je supprime"} , // Modify movie's information // 11
			{"Ajouter un commentaire", "Aucun commentaire n'a été fait pour ce film", "Trier les commentaires par Date", "Trier les commentaires positifs", "Trier les commentaires négatifs"} , // Display movie's comments // 12
			{"Cacher un commentaire :"} , // In displayCommentOfMovie menu for admin // 13
			{"Rechercher par : ", "Titre",  "Catégorie", "Producteur", "Acteurs", "Année de production", "Avis du public", "Prix", "Sélectionner votre recherche"} , // Search for a movie // 14
			{"J'ai attribué la note", "Je souhaite modifier cette note", "Je laisse cette note", "J'ajoute la note (entre 1 et 10) :"}, // Add score // 15
			{"Je rédige mon commentaire"}, // Add comment // 16
			{"Valider", "Changer les caractéristiques du film", "Ajouter",  "Titre", "Thème", "Date de production", "Description", "Acteur principal", "Producteur", "Prix"}, // Add movie //17
			{"Facture numéro ", "Date d'achat", "Prix", "Vous n'avez aucun achat"}, // Display purchase //18
			{"Prix total", "Votre panier", "Payer", "Supprimer élément du panier", "Voir le détail d'un film", "Votre panier est vide"}, // Display panier //19
	 		{"La base de données contient ", " utilisateurs", " abonnés", " films", "Le nombre moyen de commentaires par film est ", "La note moyenne par film est "}, // Display statistics //20
			
			{"Bienvenu !", "Au revoir !"} , // In main menu
			{"Quitter l'application", "Se déconnecter", "Retour", "Retour au menu principal" ,  "Veuillez entrer un chiffre correspondant à une option ci-dessus"} // Other
		};
	private static String[] errors = {
			"Erreur : Nous n'avons pas réussi à vous connecter",
			"Erreur : Nous n'avons pas réussi à supprimer votre compte",
			"Erreur : Nous n'avons pas réussi à supprimer ce film"
	};
	private static int nbMovie = 3;
	private int nbMovieToDisplay = nbMovie;
	private boolean continu = true;
	private int menuId = 0;
	private Bdd bdd = new Bdd();
	private User user;
	private Movie movie;
	
	// Initialize
 	public menu() {
		System.out.println(toWrite[toWrite.length - 2][0]); // Welcome the user
		
		// Pour mes tests
		/*
		this.user = this.bdd.getUsers().getUser(1);
		this.movie = this.bdd.getMovies().getMovie(1);
		this.menuId = 2;
		*/
		
		while(continu) {
			redirect();
			System.out.println();
			System.out.println();
		}

		System.out.println(toWrite[toWrite.length - 2][1]); // Inform the user he has quit the application
		bdd.close(); // Save new bdd
	}

	// Private functions
 	private int getInputAsIntBetweenAandB(int a, int b) {
 		
 		// Scan user answer
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        
        // Translate it into an integer
        int res = -1;
        try {
            res = Integer.parseInt(answer);
        } catch (NumberFormatException e) { // The answer could not be converted to integer
            res = a - 1;
        }
        
        // While the user has not written an integer between a and b, re ask him an answer
        while(res < a || res > b) {
        	System.out.println(toWrite[toWrite.length - 1][4]);
            answer = in.nextLine();
            if(answer.length() > 0) {
                res = (int) (answer.charAt(0) - '0');
            }
        }
        
 		return res;
 	}
 	private void redirect() {
 		switch (this.menuId) {
		case 0 :
			menuWelcome();
			break;
		case 1 :
			menuConnect();
			break;
		case 2 :
			menuCreateAccount(false);
			break;
		case 3 :
			menuForgottenPassword();
			break;
		case 4 :
		case 5 :
			connected();
			break;
		case 6 :
			userInfo();
			break;
		case 7 :
			modifyUserAccount();
			break;
		case 8 :
			displayMovieCollection(this.bdd.getMovies());
			break;
		case 9 :
		case 10 :
			displayMovieDetails();
			break;
		case 11 :
			modifyMovie();
			break;
		case 12 :
		case 13 :
			displayCommentOfMovie();
			break;
		case 14 :
			searchMovie();
			break;
		case 15 :
			giveScore();
			break;
		case 16 :
			giveComment();
			break;
		case 17 :
			addMovie();
			break;
		case 18 :
			displayHistoriqueAchats();
			break;
		case 19 :
			displayPanier();
			break;
		case 20 :
			displayStatistics();
			break;
			
		// Quit
		case -1 :
		default :
			this.continu = false;
			break;
 		}
 	}
 	private void initializeUser(String email, String password, int menuId) {
 		
		this.user = this.bdd.getUsers().connect(email, password); // Get user
		
		if(this.user == null) { // Could not find the user in data base
			System.out.println(errors[0]);
			this.menuId = 0;
			
		} else { // Connect
			this.menuId = menuId;
		}
 	}
 	private void initializeUserWithSentence(String email, String secretSentence, int menuId) {
 		
		this.user = this.bdd.getUsers().connectWithSentence(email, secretSentence);// Get user
		
		if(this.user == null) { // Could not find the user in data base
			System.out.println(errors[0]);
			this.menuId = 0;
			
		} else { // Connect
			this.user.setMotDePasse(secretSentence);
			this.menuId = menuId;
		}
 	}
 	private void menuWelcome() { // 0
 		
 		// Display choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][0]);
		for(int i = 0; i < toWrite[0].length; i++) {
			System.out.println((i + 1) + " : " + toWrite[0][i]);
		}

		// Get the user answer
		int answer = getInputAsIntBetweenAandB(0, 4);
		
		// Change menu according to the user answer
		if(answer == 0) { // Quit
			this.menuId = -1;
		} else {
			this.menuId = answer;
		}
	}
 	@SuppressWarnings("resource")
	private void menuConnect() { // 1
 		
 		// Display choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]);
 		
		// Create needed variables to get the user answer
		Scanner in = new Scanner(System.in);
        String[] answer = new String[toWrite[1].length - 1];
        
 		// Ask the user his information to connect
 		System.out.println(toWrite[1][0]);
		for(int i = 0; i < toWrite[1].length - 2; i++) {
			System.out.println(toWrite[1][i + 1]);
			
			// Get user answer
			answer[i] = in.nextLine();
	        
			// Return to the main menu if the user has ask so
	        if(answer[i].equals("0")) {
	        	this.menuId = 0;
	        	return;
	        }
		}

		// Change menu depending on the user answer
		if(this.bdd.getUsers().connect(answer[0], answer[1]) != null) { // Right id
			initializeUser(answer[0], answer[1], 4);
		} else { // Wrong id
 			System.out.println(toWrite[1][toWrite[1].length - 1]); // Let the user know, his gave the wrong id
			this.menuId = 1;
		} 
	}
	private void menuCreateAccount(boolean isAdmin) { // 2
		
 		// Display choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // Return to the previous menu
 		
		// Create needed variables to get the user answer
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        String[] answer = new String[toWrite[2].length - 1];
        
 		// Ask the user his information to create an account
 		System.out.println(toWrite[2][0]);
		for(int i = 0; i < toWrite[2].length - 2; i++) {
			System.out.println(toWrite[2][i + 1]);
			
			// Get user answer
			answer[i] = in.nextLine();
	        
			// Return to the main menu if the user has ask so
	        if(answer[i].equals("0")) {
	        	this.menuId = 0;
	        	return;
	        }
		}

		// Create an account
		boolean addUser = this.bdd.getUsers().addUser(isAdmin, answer[3], answer[2], answer[0], answer[1], answer[4], answer[5]);
		
		// Display a message if the indicated email has already been used
		if(!addUser) {
	 		System.out.println(toWrite[2][toWrite[2].length - 1]);
	 		return;
		}
		
		// And connect if the user is not an administrator adding an administrator account
		if(!isAdmin) {
			initializeUser(answer[3], answer[2], 4);
		}
	}
	private void menuForgottenPassword() { // 3
		
 		// Display choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]);
 		
		// Create needed variables to get the user answer
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        String[] answer = new String[toWrite[3].length - 3];
        
 		// Ask the user his email
 		System.out.println(toWrite[3][0]);
		for(int i = 0; i < toWrite[3].length - 3; i++) {
			System.out.println(toWrite[3][i + 1]);
			
			// Get user answer
			answer[i] = in.nextLine();
	        
			// Return to the main menu if the user has ask so
	        if(answer[i].equals("0")) {
	        	this.menuId = 0;
	        	return;
	        }
		}
		
		// Test the id
		if(this.bdd.getUsers().emailAndSecretSentenceInBdd(answer[0], answer[1]) == 1) { // Email and Secret sentence is correct
			
			// Connect
			initializeUserWithSentence(answer[0], answer[1], 4);
			
			// Modify the password
			String password = "";
	 		System.out.println(toWrite[3][toWrite[3].length - 1]);
			password = in.nextLine();
			this.user.setMotDePasse(password);
			
		} else { // Wrong email and wrong secret sentence
			System.out.println(toWrite[3][toWrite[3].length - 1]); // Let the user know he is wrong
			this.menuId = 3; 
		}
	}
	private void connected() { // 4 // 5
		
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][1]); // To disconnect
		for(int i = 0; i < toWrite[4].length; i++) {
			System.out.println((i + 1) + " : " + toWrite[4][i]);
		}
		
		// Add admin choices if the user is one
		if(this.user.getIsAdmin()) {
			for(int i = 0; i < toWrite[5].length; i++) {
				System.out.println((i + toWrite[4].length + 1) + " : " + toWrite[5][i]);
			}
		}
		
		// Get user answer
		int answer;
		if(this.user.getIsAdmin()) {
			answer = getInputAsIntBetweenAandB(0, toWrite[4].length + toWrite[5].length);
		} else {
			answer = getInputAsIntBetweenAandB(0, toWrite[4].length);
		}

		// Change menu depending on the user answer
		switch(answer) {
		case 1 : // Display my informations
			this.menuId = 6;
			break;
		case 2 : // Searching for a movie
			this.menuId = 14;
			break;
		case 3 : // Explore the movie collection
			this.menuId = 8;
			break;
		case 4 : // See purchases
			this.menuId = 19;
			break;
		case 5 : // Display history Achat
			this.menuId = 18;
			break;
		case 6 : // Display statistics
			this.menuId = 20;
			break;
		case 7 : // Add movie
			this.menuId = 17;
			break;
		case 8 : // Add administrator
			menuCreateAccount(true);
			break;
		case 0 : // Disconnect
		default :
			this.user = null;
			this.menuId = 0;
			break;
		}
	}
	private void userInfo() { // 6

 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the connected menu
 		
		// Display user informations
 		System.out.println(toWrite[6][0]);
 		System.out.println(toWrite[2][1] + " : " + this.user.getNom());
 		System.out.println(toWrite[2][2] + " : " + this.user.getPrenom());
 		System.out.print(toWrite[2][3] + " : ");
 		for(int i = 0; i < this.user.getMotDePasse().length(); i++) {
 			System.out.print("*");
 		}
 		System.out.println();
 		System.out.println(toWrite[2][4] + " : " + this.user.getEmail());
 		if(this.user.getIsSubscribe()) {
 			System.out.println(toWrite[6][1] + " : Oui");
 		} else {
 			System.out.println(toWrite[6][1] + " : Non");
 		}
 		if(this.user.getIsAdmin()) {
			System.out.println(toWrite[6][2] + " : Oui");
		}
		System.out.println();
		
 		// Display user choices
		System.out.println(toWrite[6][3]);
		for(int i = 1; i < toWrite[6].length - 4; i++) {
			System.out.println(i + " : " + toWrite[6][i + 3]);
		}
		
		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, toWrite[6].length - 2);

		// Change menu depending on the user answer
		switch(answer) {
		case 1 : // Delete account
			boolean success = this.bdd.getUsers().deleteUser(this.user, this.bdd.getScores(), this.bdd.getComments(), this.bdd.getPurchases());
			if(success) { // Successfully deleted the account
				this.user = null;
				this.menuId = 0;
			} else { // Could not delete the account
				System.out.println(menu.errors[1]);
				this.menuId = 5;
			}
			break;

		case 2 : // Modify account
			this.menuId = 7;
			break;
			
		case 0 : // Return to the connected menu
			this.menuId = 5;
			break;
			
		default : // Disconnect
			this.user = null;
			this.menuId = 0;
			break;
		}
	}
	private void modifyUserAccount() { // 7
		
		// Display user informations
 		System.out.println(toWrite[6][0]);
 		System.out.println(toWrite[2][1] + " : " + this.user.getNom());
 		System.out.println(toWrite[2][2] + " : " + this.user.getPrenom());
 		System.out.print(toWrite[2][3] + " : ");
 		for(int i = 0; i < this.user.getMotDePasse().length(); i++) {
 			System.out.print("*");
 		}
 		System.out.println();
 		System.out.println(toWrite[2][4] + " : " + this.user.getEmail());
 		if(this.user.getIsSubscribe()) {
 			System.out.println(toWrite[6][1] + " : Oui");
 		} else {
 			System.out.println(toWrite[6][1] + " : Non");
 		}
 		if(this.user.getIsAdmin()) {
			System.out.println(toWrite[6][2] + " : Oui");
		}
		System.out.println();
		
		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the connected menu
		System.out.println(toWrite[7][0]);
		for(int i = 1; i < toWrite[7].length - 1; i++) {
			System.out.println(i + " : " + toWrite[7][i]);
		}
		
		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, toWrite[7].length - 1);
		@SuppressWarnings({"resource" })
		Scanner in = new Scanner(System.in);
		String change;

		// Change menu depending on the user answer
		switch(answer) {
		
		case 1 : // Change user's name
			System.out.println(toWrite[7][toWrite[7].length - 1]);
			change = in.nextLine();
			this.user.setNom(change);
			break;
			
		case 2 : // Change user's surname
			System.out.println(toWrite[7][toWrite[7].length - 1]);
			change = in.nextLine();
			this.user.setPrenom(change);
			break;
			
		case 3 : // Change user's password
			System.out.println(toWrite[7][toWrite[7].length - 1]);
			change = in.nextLine();
			this.user.setMotDePasse(change);
			break;
			
		case 4 : // Change user's email
			System.out.println(toWrite[7][toWrite[7].length - 1]);
			change = in.nextLine();
			this.user.setEmail(change);
			break;
			
		case 5 : // Subscribe or unsubscribe
			if(this.user.getIsSubscribe()) {
				this.user.setIsSubscribe(false);
			} else {
				this.user.setIsSubscribe(true);
			}
			break;
			
		case 0 : // Return to the connected menu
			this.menuId = 5;
			break;
			
		default : // Disconnect
			this.user = null;
			this.menuId = 0;
			break;
		}
	}
	private void displayMovieCollection(Movies movies) { // 8
		
		int more = 0;
		
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the connected menu
		if(this.bdd.getMovies().getMovies().size() > nbMovieToDisplay) { // Ask if the user want to see more movies
			System.out.println("1 : " + toWrite[8][1]);
			more = 1;
		}
		System.out.println(toWrite[8][0]); // Select a movie to get details about it
		for(int i = 0; i < this.bdd.getMovies().getMovies().size() && i < nbMovieToDisplay; i++) {
			System.out.println((i + more + 1) + " : " + movies.getMovies().get(i).getTitle()); // Display movie's title
		}
		
		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, toWrite[8].length + 1);

		// Change menu depending on the user answer
		switch(answer) {
			
		case 0 : // Return to the connected menu
			this.nbMovieToDisplay = nbMovie;
			this.menuId = 5;
			break;
			
		case 1 :
			if(more == 1) { // Display more movies
				this.nbMovieToDisplay += nbMovie;
				break;
			} else { // Display details about a movie
				this.menuId = 9;
				this.movie = this.bdd.getMovies().getMovies().get(answer - 1 - more);
				break;
			}
			
		default : // Display details about a movie
			this.menuId = 9;
			this.movie = this.bdd.getMovies().getMovies().get(answer - 1 - more);
			break;
		}
	}
	private void displayMovieDetails() { // 9 // 10
		
		// Display movie informations
		System.out.println(toWrite[9][0] + " : " + this.movie.getTitle());
		System.out.println(toWrite[9][1] + " : " + this.movie.getTheme());
		System.out.print(toWrite[9][2] + " : ");
		for(int i = 0; i < this.movie.getProducteurs().size(); i++) {
			System.out.print(this.movie.getProducteurs().get(i));
			if(i != this.movie.getProducteurs().size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
		System.out.print(toWrite[9][3] + " : ");
		for(int i = 0; i < this.movie.getActeursPrincipaux().size(); i++) {
			System.out.print(this.movie.getActeursPrincipaux().get(i));
			if(i != this.movie.getActeursPrincipaux().size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
		System.out.println(toWrite[9][4] + " : " + this.movie.getProductionDate());
		System.out.println(toWrite[9][5] + " : " + this.movie.getNoteMoyenne());
		System.out.println(toWrite[9][6] + " : " + this.movie.getDescription());
		System.out.print(toWrite[9][7] + " : ");
		System.out.println(this.movie.calculateReducedPrice(this.user) + " euros");
		System.out.println();
		
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the previous menu
 		System.out.println("1 : " + toWrite[toWrite.length - 1][3]); // To return to connected menu
		for(int i = 0; i < toWrite[9].length - 8; i++) {
			System.out.println((i + 2) + " : " + toWrite[9][8 + i]);
		}

		// Add admin choices if the user is one
		if(this.user.getIsAdmin()) {
			for(int i = 0; i < toWrite[10].length; i++) {
				System.out.println((i + 6) + " : " + toWrite[10][i]);
			}
		}
		
		// Get user answer
		int answer;
		if(this.user.getIsAdmin()) { // If he is an admin
			answer = getInputAsIntBetweenAandB(0, toWrite[9].length + toWrite[10].length);
		} else { // If he is a client
			answer = getInputAsIntBetweenAandB(0, toWrite[9].length);
		}
		
		// Change menu depending on the user answer
		switch(answer) {
		
		case 0 : // Return to the previous menu (displayMovieCollection)
			this.movie = null;
			this.menuId = 8;
			break;
			
		case 1 : // Return to the connected menu
			this.movie = null;
			this.menuId = 5;
			break;
			
		case 2 : // Display movie comments
			this.menuId = 12;
			break;
			
		case 3 : // Buy movie
			this.user.addMovieToPanier(movie);
			this.menuId = 9;
			break;
			
		case 4 : // Give the movie a grade
			this.menuId = 15;
			break;
			
		case 5 : // Add comment
			this.menuId = 16;
			break;
			
		case 6 : // Modify movie's informations
			this.menuId = 11;
			break;
			
		case 7 : // Delete movie
			boolean success = this.bdd.getMovies().deleteMovie(this.movie, this.bdd.getScores(), this.bdd.getComments());
			if(success) { // Successfully deleted the movie
				this.movie = null;
			} else { // Could not delete the movie
				System.out.println(menu.errors[2]);
			}
			this.menuId = 5;
			break;
			
		default : // Disconnect
			this.movie = null;
			this.user = null;
			this.menuId = 0;
			break;
		}
	}
	private void modifyMovie() { // 11
		
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the previous menu (displayMovieDetails)
 		System.out.println("1 : " + toWrite[toWrite.length - 1][3]); // To return to the connected menu
		System.out.println(toWrite[11][0]);
		for(int i = 1; i < toWrite[11].length - 3; i++) {
			System.out.println((i + 1) + " : " + toWrite[11][i]);
		}
		
		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, toWrite[11].length - 1 + 2);
		@SuppressWarnings({"resource" })
		Scanner in = new Scanner(System.in);
		String change;

		// Change menu depending on the user answer
		switch(answer) {
		
		case 2 : // Change movie's title
			System.out.println(toWrite[11][toWrite[11].length - 3]);
			change = in.nextLine();
			this.movie.setTitle(change);
			break;
			
		case 3 : // Change movie's themes
			System.out.println(toWrite[11][toWrite[11].length - 3]);
			change = in.nextLine();
			this.movie.setTheme(change);
			break;
			
		case 4 : // Change movie's producers
			
			// Display user choice
			System.out.println("0 : " + toWrite[11][toWrite[11].length - 2]);
			System.out.println("1 : " + toWrite[11][toWrite[11].length - 1]);

			// Get user answer
			answer = getInputAsIntBetweenAandB(0, 1);

			switch(answer) {
			case 0 : // Add one producer
				System.out.println(toWrite[11][toWrite[11].length - 2] + " :");
				change = in.nextLine();
				this.movie.getProducteurs();
				break;
			case 1 : // Delete all producers
				this.movie.setProducteurs(null);
				break;
			default :
				break;
			}
			
			break;
			
		case 5 : // Change movie's actors
			
			// Display user choice
			System.out.println("0 : " + toWrite[11][toWrite[11].length - 2]);
			System.out.println("1 : " + toWrite[11][toWrite[11].length - 1]);

			// Get user answer
			answer = getInputAsIntBetweenAandB(0, 1);

			switch(answer) {
			case 0 : // Add actor
				System.out.println(toWrite[11][toWrite[11].length - 2] + " :");
				change = in.nextLine();
				this.movie.addActeursPrincipaux(change);
				break;
			case 1 : // Delete all actors
				this.movie.setActeursPrincipaux(null);
				break;
			default :
				break;
			}
			
			break;
			
		// case 6 : // Change movie's production year
			
		case 7 : // Change movie's description
			System.out.println(toWrite[11][toWrite[11].length - 3]);
			change = in.nextLine();
			this.movie.setDescription(change);
			break;
			
		case 8 : // Change movie's price
			System.out.println(toWrite[11][toWrite[11].length - 3]);
			this.movie.setPrice(getInputAsIntBetweenAandB(0, 9));
			break;
			
		case 0 : // Return to the displayMovieDetails menu
			this.menuId = 10;
			break;
			
		default : // Return to the connected menu
			this.movie = null;
			this.menuId = 5;
			break;
		}
	}
	private void displayCommentOfMovie() { // 12 // 13
		
		// Check if all comments are hidden
		boolean allHidden = true;
		for(int i = 0; i < this.movie.getComments().size(); i++) {
			if(this.movie.getComments().get(i).isActivated()) {
				allHidden = false;
				break;
			}
		}
		
		// Notice the user if there is no comments
		if(this.movie.getComments().size() == 0 && allHidden) { 
			System.out.println(toWrite[12][1]);
		}
		
		// For user only (not admin)
		if(!this.user.getIsAdmin()) { 
			// Notice the user that there is no comment, if all comment are hidden
			if(allHidden) { 
				System.out.println(toWrite[12][1]);
			}
			
			// Display comments
 			for(int i = 0; i < this.movie.getComments().size(); i++) {
 				if(this.movie.getComments().get(i).isActivated()) {
 	 				System.out.println("De " + this.movie.getComments().get(i).getAuthor().getNom() + " le " + this.movie.getComments().get(i).getPublicationDate());
 	 				System.out.println(this.movie.getComments().get(i).getText());
 	 				System.out.println();
 				}
 			}
		}
 			
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the previous menu (displayMovieDetails)
 		System.out.println("1 : " + toWrite[toWrite.length - 1][3]); // To return to the connected menu
	 	System.out.println("2 : " + toWrite[12][0]);
 		
 		// Add admin choices if the user is one
 		if(this.user.getIsAdmin()) { 
 			if(this.movie.getComments().size() != 0) { // For each existing comment
 	 		 	System.out.println(toWrite[13][0]); // Selection choice
 	 			for(int i = 0; i < this.movie.getComments().size(); i++) { // Display comments
 	 				System.out.print((i + 3) + " : " + "De " + this.movie.getComments().get(i).getAuthor().getNom() + " le " + this.movie.getComments().get(i).getPublicationDate());
 	 				if(!this.movie.getComments().get(i).isActivated()) { // Notice admin if the comment is hidden
 	 					System.out.print(" (désactivé)");
 	 				}
 	 				System.out.println();
 	 				System.out.println(this.movie.getComments().get(i).getText());
 	 				System.out.println();
 	 			}
 			}
 		}
		
		// Get user answer
		int answer;
		if(this.user.getIsAdmin()) {
			answer = getInputAsIntBetweenAandB(0, toWrite[11].length + toWrite[12].length);
		} else {
			answer = getInputAsIntBetweenAandB(0, toWrite[11].length);
		}

		// Change menu depending on the user answer
		switch(answer) {
			
		case 0 : // Return to the the previous menu (displayMovieDetails)
			this.menuId = 9;
			break;
			
		case 1 : // Return to the connected menu
			this.menuId = 5;
			break;
			
		case 2 : // Add comment
			this.menuId = 16;
			break;
			
		default : // Hide the selected comment
			this.bdd.getComments().getComment(answer - 2).setActivated(false);
			this.menuId = 12;
			break;
		}
	}
	private void searchMovie() { // 14
		
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the connected menu
		System.out.println(toWrite[14][0]);
		for(int i = 1; i < toWrite[14].length - 1; i++) {
			System.out.println((i) + " : " + toWrite[14][i]);
		}
		
		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, toWrite[14].length + 1);
		@SuppressWarnings({"resource" })
		Scanner in = new Scanner(System.in);
		String search;
		List<Movie> foundedMovies = new ArrayList<>();

		// Change menu depending on the user answer
		switch(answer) {
			
		case 1 : // Return to the connected menu
			System.out.println(toWrite[14][toWrite[14].length - 1]);
			search = in.nextLine();
			foundedMovies = this.bdd.getMovies().searchMovieByTitle(search);
			break;
			
		case 2 : // Return to the connected menu
			System.out.println(toWrite[14][toWrite[14].length - 1]);
			search = in.nextLine();
			foundedMovies = this.bdd.getMovies().searchMovieByTheme(search);
			break;
			
		case 3 : // Return to the connected menu
			System.out.println(toWrite[14][toWrite[14].length - 1]);
			search = in.nextLine();
			foundedMovies = this.bdd.getMovies().searchMovieByProducer(search);
			break;
			
		case 4 : // Return to the connected menu
			System.out.println(toWrite[14][toWrite[14].length - 1]);
			search = in.nextLine();
			foundedMovies = this.bdd.getMovies().searchMovieByActor(search);
			break;
			
		case 5 : // Return to the connected menu
			System.out.println(toWrite[14][toWrite[14].length - 1]);
			Date productionDate = new Date();
			foundedMovies = this.bdd.getMovies().searchMovieByProductionDate(productionDate);
			break;
			
		case 6 : // Return to the connected menu
			System.out.println(toWrite[14][toWrite[14].length - 1]);
			long value = Long.parseLong(in.nextLine());
			foundedMovies = this.bdd.getMovies().searchMovieByScore(value);
			break;
			
		case 7 : // Return to the connected menu
			System.out.println(toWrite[14][toWrite[14].length - 1]);
			double price = Double.parseDouble(in.nextLine());
			foundedMovies = this.bdd.getMovies().searchMovieByPrice(price, this.user);
			break;

		case 0 : // Return to the connected menu
		default : // Display details about a movie
			this.menuId = 5;
			return;
		}

		this.menuId = 8;
		displayMovieCollection(new Movies(foundedMovies));
	}
	private void giveScore() { // 15
		
		boolean change = true;
		int answer;
		
		// Check if the user has already gave a score to this movie
		for(int i = 0; i < this.user.getScores().size(); i++) {
			if(this.user.getScores().get(i).getMovie().getCode() == this.movie.getCode()) { 
				
				// Display user choices
				System.out.println(toWrite[15][0] + " : " + this.user.getScores().get(i).getValue());
				for(int j = 1; j < toWrite[15].length - 1; j++) {
					System.out.println(j + " : " + toWrite[15][j]);
				}
				
				// Get user answer
				answer = getInputAsIntBetweenAandB(0, 1);
				
				// Change menu depending on the user answer
				switch(answer) {
					
				case 2 : // Do not change the score
					change = false;
					break;
					
				default : // Change the score
					break;
				}

				break; // Stop searching if user already gave a score to this movie (because you found it)
			}
		}
		
		// The user changed his mind and do not want to change score
		if(!change) {
			return;
		}
		
		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the previous menu
		System.out.println(toWrite[15][toWrite[15].length - 1]); // Write score
		
		// Get user answer
		answer = getInputAsIntBetweenAandB(0, 10);
		
		// Change score depending on the user answer
		switch(answer) {
		
		case 0 : // Return to the previous menu
			break;
			
		default : // Change the score
			this.bdd.addScore(this.user.getCode(), this.movie.getCode(), Long.valueOf(answer));
			break;
		}
		
		this.menuId = 9; // In every case, return to the previous menu at the end of choice
	}
	private void giveComment() { // 16
		
		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the previous menu
		System.out.println(toWrite[16][toWrite[16].length - 1]); // Write comment
		
		// Get user answer
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String answer = in.nextLine();
		
		// Change comment depending on the user answer
		switch(answer) {
		
		case "0" : // Return to the previous menu
			break;
			
		default : // Change the comment
			this.bdd.addComment(this.user.getCode(), this.movie.getCode(), answer);
			break;
		}
		
		this.menuId = 12; // In every case, return to the previous menu at the end of choice
	}	
	@SuppressWarnings("deprecation")
	private void addMovie() { // 17
		
		// Necessary variables
		boolean validated = false;
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int answer = 0;

		// Movie Fields
		String title = "";
		String theme = "";
		Date productionDate = new Date();
		String description = "";
		List<String> acteursPrincipaux = new ArrayList<>();
		List<String> producteurs = new ArrayList<>();
		double price = 0;
		
		while(!validated) { // While the admin has not validated his selection

			// Display admin choices
	 		System.out.println("0. " + toWrite[toWrite.length - 1][2]); // To return to the previous (connected) menu
	 		System.out.println("1. " + toWrite[17][0]); // Validate
	 		// Movie fields
	 		System.out.println(toWrite[17][1] + " : ");
	 		System.out.println(title);
			System.out.println("2. " + toWrite[17][3] + " : " + title);
			System.out.println("3. " + toWrite[17][4] + " : " + theme);
			System.out.println("4. " + toWrite[17][5] + " : " + productionDate);
			System.out.println("5. " + toWrite[17][6] + " : " + description);
			System.out.print("6. " + toWrite[17][7] + " : ");
			for(int i = 0; i < acteursPrincipaux.size(); i++) { // Display actors
				System.out.print(acteursPrincipaux.get(i));
				if(i != acteursPrincipaux.size() - 1) { // Display separator
					System.out.print(", ");
				}
			}
			System.out.println();
			System.out.print("7. " + toWrite[17][8] + " : ");
			for(int i = 0; i < producteurs.size(); i++) { // Display producers
				System.out.print(producteurs.get(i));
				if(i != producteurs.size() - 1) { // Display separator
					System.out.print(", ");
				}
			}
			System.out.println();
			System.out.println("8. " + toWrite[17][9] + " : " + price);
			
			// Get user answer
			answer = getInputAsIntBetweenAandB(0, 8);
			
			// Change menu or movie fields depending on user answer
			switch(answer) {
			case 0 : // returns to the previous menu
				this.menuId = 4;
				break;

			case 1 : // Validate selection
				this.bdd.getMovies().addMovie(title, theme, productionDate, description, acteursPrincipaux, producteurs, price);
				validated = true;
				this.menuId = 4;
				break;

			// Get Movie content
			case 2 :
				title = in.nextLine();
				System.out.println(title);
				break;

			case 3 :
				theme = in.nextLine();
				break;

			case 4 :
				productionDate = new Date(in.nextLine());
				break;

			case 5 :
				description = in.nextLine();
				break;

			case 6 :
				acteursPrincipaux.add(in.nextLine());
				break;

			case 7 :
				producteurs.add(in.nextLine());
				break;

			case 8 :
				price = Long.valueOf(getInputAsIntBetweenAandB(0, 9));
				break;
			}
		}
	}
	private void displayHistoriqueAchats() { // 18
		
		// Check if user has historiqueAchats
		if(this.user.getHistoriqueAchats().size() == 0) {
			System.out.println(toWrite[18][toWrite[18].length - 1]);
		}
		
		for (int i = 0; i < this.user.getHistoriqueAchats().size(); i++) { // For each Purchase of the user
			// Display purchase informations
			System.out.println(this.user.getHistoriqueAchats().get(i).genererFacture());
		}
		
		this.menuId = 4;
	}
	private void displayPanier() { // 19
		
		// Display a message and quit the menu if the user has nothing on his panier
		if(this.user.getPanier().size() == 0) {
			System.out.println(toWrite[19][toWrite[19].length - 1]);
			this.menuId = 4;
			return;
		}
		
		long price = 0;
		
		// Display movies in panier
		System.out.println(toWrite[19][1] + " :");
		
		for(int i = 0; i < this.user.getPanier().size(); i++) {
			System.out.print(this.user.getPanier().get(i).getTitle());
			
			// Calculate and display price
			if(this.user.getIsSubscribe()) { // Reduced price if the user is subscribed
				price += (80/100) * this.user.getPanier().get(i).getPrice();
				System.out.print(" - " + this.user.getPanier().get(i).getPrice() + " euros");
			} else {
				price += this.user.getPanier().get(i).getPrice();
				System.out.print(" - " + this.user.getPanier().get(i).getPrice() + " euros");
			}
			System.out.println();
		}
		System.out.println(toWrite[19][0] + " : " + price + " euros"); // Display price
		
		// Display user's choice
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the previous menu (connected menu)
		for(int i = 2; i < toWrite[19].length - 1; i++) {
			System.out.println((i - 1) + " : " + toWrite[19][i]);
		}

		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, toWrite[19].length - 1);

		// Change menu or movie fields depending on user answer
		switch(answer) {
		case 0 : // returns to the previous menu
			this.menuId = 4;
			break;
			
		case 1 : // Pay
			this.bdd.addPurchase(this.user.getCode(), price);
			this.user.setPanier(new ArrayList<>());
			this.menuId = 4;
			break;
			
		case 2 : // Delete element from panier
			
			answer = chooseMovieInPanier(); // Select the movie

			// Change menu or movie fields depending on user answer
			switch(answer) {
			case 0 : // returns to the previous menu
				this.menuId = 4;
				break;
			default : // remove movie
				this.menuId = 19;
				this.user.getPanier().remove(answer - 1);
				break;
			}
			break;
			
		case 3 : // Display movie details
			
			answer = chooseMovieInPanier(); // Select the movie

			// Change menu or movie fields depending on user answer
			switch(answer) {
			case 0 : // returns to the previous menu
				this.menuId = 4;
				return;
			default : // display movie details
				this.menuId = 9;
				this.movie = this.bdd.getMovies().getMovie(this.user.getPanier().get(answer - 1).getCode());
				return;
			}
		}
	}
	private int chooseMovieInPanier() {
		
		// Show user choice between movies
		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the previous menu (connected menu)
		for(int i = 0; i < this.user.getPanier().size(); i++) {
			System.out.println((i + 1) + " : " + this.user.getPanier().get(i).getTitle());
		}
		
		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, this.user.getPanier().size());
		
		return answer;
	}
 	private void displayStatistics() { // 20
 		
		// Display Statistics
		System.out.println(toWrite[20][0] + this.bdd.getUsers().getNbAccount() + toWrite[20][1]);
		System.out.println(toWrite[20][0] + this.bdd.getUsers().getNbSubscribers() + toWrite[20][2]);
		System.out.println(toWrite[20][0] + this.bdd.getMovies().getMovies().size() + toWrite[20][3]);
		System.out.println(toWrite[20][4] + this.bdd.getMovies().getMediumNumberOfCommentPerMovie());
		System.out.println(toWrite[20][5] + this.bdd.getMovies().getMediumScore());
		
		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, toWrite[20].length);

		// Change menu depending on the user answer
		switch(answer) {

		case 0 : // Return to the connected menu
		default : // Display details about a movie
			this.menuId = 5;
			break;
		}
	}
}