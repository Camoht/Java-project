package userInterface;

import java.util.List;
import java.util.Scanner;
import metier.*;

public class menu {
	
	// Fields
	private static String[][] toWrite = {
			{"Se connecter", "Créer un compte", "Mot de passe oublié"} , // In the Welcome menu // 0
			{"Veuillez renseigner votre :", "Email", "Mot de passe", "Mail ou Mot de passe incorrect"} , // In the connect menu // 1
			{"Veuillez renseigner votre :", "Nom", "Prénom", "Mot de passe", "Email"} , // In the create an account menu // 2
			{"Veuillez renseigner votre :", "Email", "Phrase secrête", "Mail ou Phrase secrête incorrect"} , // In forgotten password menu // 3
			{"Afficher mes données", "Rechercher un film", "Explorer la liste des film", "Explorer mon historique", "Visualiser mon panier"} , // In connected menu for client // 4
			{"Afficher statistiques", "Ajouter un film", "Ajouter un administrateur"} , // In connected menu for admin // 5
			{"Vos informations :", "Supprimer mon compte", "Modifier mon compte", "Votre compte a bien été supprimé"} , // Display informations about the user // 6
			{"Je souhaite modifier :", "Mon nom", "Mon prénom", "Mon mot de passe", "Mon Email", "Je le change pour :"} , // Modify the user's account //  7
			{"Select a movie to get details about it :"} , // Display the movie collection // 8
			{"Title :", "Catégories :", "Producteurs : ", "Acteurs principaux :", "Année de production :", "Avis du public :", "Description :", "Prix :", "Afficher les commentaires", "Acheter le film"} , // Display movie's details // 9
			{"Modifier les informations du film", "Supprimer le film"} , // In displayMovieDetails menu for admin // 10
			{"Je souhaite modifier :", "Title", "Catégories", "Producteurs", "Acteurs principaux", "Année de production", "Avis du public", "Description", "Prix"} , // Modify movie's information // 11
			{} , // Display movie's comments // 12
			{"Cacher un commentaire :"} , // In displayCommentOfMovie menu for admin // 13
			{"Rechercher par : ", "Titre",  "Catégorie", "Producteur", "Acteurs", "Année de production", "Avis du public", "Prix"} , // Search for a movie // 14
			
			{} , // Explore user's history
			{} , // See user's purchases
			{"Bienvenu !", "Au revoir !"} , // In main menu
			{"Quitter l'application", "Se déconnecter", "Annuler", "Retour au menu principal" ,  "Veuillez entrer un chiffre correspondant à une option ci-dessus"} // Other
		};
	private static String[] errors = {
			"Erreur : Nous n'avons pas réussi à vous connecter",
			"Erreur : Nous n'avons pas réussi à supprimer votre compte",
			"Erreur : Nous n'avons pas réussi à supprimer ce film"
	};
	private boolean continu = true;
	private int menuId = 0;
	private Bdd bdd = new Bdd();
	private User user;
	private int statu = 0; // 0 : not connected, 1 : is client, 2 : is admin
	private Movie movie;
	
	// Initialize
 	public menu() {
		System.out.println(toWrite[toWrite.length - 2][0]); // Welcome the user
		
		while(continu) {
			redirect();
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
        int res = (int) (answer.charAt(0) - '0');
        
        // While the user has not written an integer between a and b, re ask him an answer
        while(answer.length() != 1 || res < a || res > b) {
        	System.out.println(toWrite[3]);
            answer = in.nextLine();
            res = (int) (answer.charAt(0) - '0');
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
			menuCreateAccount();
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
			
		/*
		case  :
			userHistory();
			break;
		case  :
			userPurchases();
			break;
		*/
			
		// Quit
		case -1 :
		default :
			this.continu = false;
			break;
 		}
 	}
 	private void initializeUser(String email, String password, int menuId) {
		this.user = this.bdd.getUser(email);
		if(this.user == null) { // Could not find the user in data base
			System.out.println(errors[0]);
			this.menuId = 0;
			
		} else { // Connect
			this.statu = this.bdd.connect(email, password);
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
	        
			// Quit the application if the user has ask so
	        if(answer[i].equals("0")) {
	        	continu = false;
	        	return;
	        }
		}

		// Change menu depending on the user answer
		if(this.bdd.connect(answer[0], answer[1]) != 0) { // Right id
			initializeUser(answer[0], answer[1], 4);
		} else { // Wrong id
 			System.out.println(toWrite[1][toWrite[1].length - 1]); // Let the user know, his gave the wrong id
			this.menuId = 1;
		} 
	}
	private void menuCreateAccount() { // 2
 		// Display choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]);
 		
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
	        
			// Quit the application if the user has ask so
	        if(answer[i].equals("0")) {
	        	continu = false;
	        	return;
	        }
		}

		// Create an account
		this.bdd.addUser(new User(answer[0], answer[1], answer[2], answer[3]));
		
		// And connect
		initializeUser(answer[0], answer[3], 4);
	}
	private void menuForgottenPassword() { // 3
 		// Display choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]);
 		
		// Create needed variables to get the user answer
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        String[] answer = new String[toWrite[3].length - 2];
        
 		// Ask the user his email
 		System.out.println(toWrite[3][0]);
		for(int i = 0; i < toWrite[3].length - 2; i++) {
			System.out.println(toWrite[3][i + 1]);
			
			// Get user answer
			answer[i] = in.nextLine();
	        
			// Quit the application if the user has ask so
	        if(answer[i].equals("0")) {
	        	continu = false;
	        	return;
	        }
		}
		
		// Test the id
		if(bdd.emailAndSecretSentenceInBdd(answer[0], answer[1]) != 0) { // Email and Secret sentence is correct
			/**/ //Modify the password
			initializeUser(answer[0], answer[1], 4);
			
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
		if(this.statu == 2) {
			for(int i = 0; i < toWrite[5].length; i++) {
				System.out.println((i + toWrite[4].length + 1) + " : " + toWrite[5][i]);
			}
		}
		
		// Get user answer
		int answer;
		if(this.statu == 2) {
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
			this.menuId = 7;
			break;
		case 3 : // Explore the movie collection
			this.menuId = 8;
			break;
		case 4 : // Explore history
			this.menuId = 9;
			break;
		case 5 : // See purchases
			this.menuId = 10;
			break;
		case 0 : // Disconnect
		default :
			this.user = null;
			this.statu = 0;
			this.menuId = 0;
			break;
		}
	}
	private void userInfo() { // 6
		// Display user informations
 		System.out.println(toWrite[6][0]);
 		/*
 		System.out.println(toWrite[2][1] + " : " + this.user.getSurname());
 		System.out.println(toWrite[2][2] + " : " + this.user.getName());
 		System.out.print(toWrite[2][3] + " : ");
 		for(int i = 0; i < this.user.getPassword(); i++) {
 			System.out.print("*");
 		}
 		System.out.println();
 		System.out.println(toWrite[2][3] + " : " + this.user.getMail());
 		*/
		
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the connected menu
		System.out.println(toWrite[6][0]);
		for(int i = 1; i < toWrite[6].length; i++) {
			System.out.println(i + " : " + toWrite[6][i]);
		}
		
		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, toWrite[6].length);

		// Change menu depending on the user answer
		switch(answer) {
		case 1 : // Delete account
			boolean success = this.bdd.deleteUser(this.user);
			if(success) { // Successfully deleted the account
				this.user = null;
				this.statu = 0;
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
			this.statu = 0;
			this.menuId = 0;
			break;
		}
	}
	private void modifyUserAccount() { // 7
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the connected menu
		System.out.println(toWrite[7][0]);
		for(int i = 1; i < toWrite[7].length - 1; i++) {
			System.out.println(i + " : " + toWrite[7][i]);
		}
		
		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, toWrite[7].length - 1);
		@SuppressWarnings("unused")
		Scanner in = new Scanner(System.in);
		String change;

		// Change menu depending on the user answer
		switch(answer) {
		
		/*
		case 1 : // Change user's name
			System.out.println(toWrite[7][toWrite[7].length]);
			change = in.nextLine();
			this.user.setName(change);
			break;
			
		case 2 : // Change user's surname
			System.out.println(toWrite[7][toWrite[7].length]);
			change = in.nextLine();
			this.user.setSurname(change);
			break;
			
		case 3 : // Change user's password
			System.out.println(toWrite[7][toWrite[7].length]);
			change = in.nextLine();
			this.user.setPassword(change);
			break;
			
		case 4 : // Change user's email
			System.out.println(toWrite[7][toWrite[7].length]);
			change = in.nextLine();
			this.user.setMail(change);
			break;
		*/
			
		case 0 : // Return to the connected menu
			this.menuId = 5;
			break;
			
		default : // Disconnect
			this.user = null;
			this.statu = 0;
			this.menuId = 0;
			break;
		}
	}
	private void displayMovieCollection(List<Movie> movies) { // 8
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the connected menu
		System.out.println(toWrite[8][0]);
		for(int i = 0; i < this.bdd.getMovies().size(); i++) {
			// System.out.println("(i + 1) : " + movies.get(i).getTitle());
		}
		
		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, toWrite[8].length + 1);

		// Change menu depending on the user answer
		switch(answer) {
			
		case 0 : // Return to the connected menu
			this.menuId = 5;
			break;
			
		default : // Display details about a movie
			this.menuId = 9;
			this.movie = this.bdd.getMovies().get(answer - 1);
			break;
		}
	}
	private void displayMovieDetails() { // 9 // 10
		// Display movie informations
		/*
		System.out.println(toWrite[9][0]);
		System.out.println(this.movie.getTitle());
		System.out.println(toWrite[9][1]);
		System.out.println(this.movie.getTheme());
		System.out.println(toWrite[9][2]);
		for(int i = 0; i < this.movie.getProducers().length; i++) {
			System.out.println(this.movie.getProducers().get(i));
		}
		System.out.println(toWrite[9][3]);
		for(int i = 0; i < this.movie.getActors().length; i++) {
			System.out.println(this.movie.getActors().get(i));
		}
		System.out.println(toWrite[9][4]);
		System.out.println(this.movie.getProductionDate());
		System.out.println(toWrite[9][5]);
		System.out.println(this.movie.getScores());
		System.out.println(toWrite[9][6]);
		System.out.println(this.movie.getDescription());
		System.out.println(toWrite[9][7]);
		System.out.println(this.movie.getPrice());
		*/
		
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the previous menu
 		System.out.println("1 : " + toWrite[toWrite.length - 1][3]); // To return to connected menu
		for(int i = 0; i < toWrite[9].length - 8; i++) {
			System.out.println((i + 3) + " : " + toWrite[9][8 + i]);
		}

		// Add admin choices if the user is one
		if(this.statu == 2) {
			for(int i = 0; i < toWrite[10].length; i++) {
				System.out.println((i + 3) + " : " + toWrite[10][8]);
			}
		}
		
		// Get user answer
		int answer;
		if(this.statu == 2) { // If he is an admin
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
			// this.user.addPurchase(movie);
			this.menuId = 9;
			break;
			
		case 4 : // Modify movie's informations
			this.menuId = 11;
			break;
			
		case 5 : // Delete movie
			boolean success = this.bdd.deleteMovie(this.movie);
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
			this.statu = 0;
			this.menuId = 0;
			break;
		}
	}
	private void modifyMovie() { // 11
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the previous menu (displayMovieDetails)
 		System.out.println("1 : " + toWrite[toWrite.length - 1][3]); // To return to the connected menu
		System.out.println(toWrite[11][0]);
		for(int i = 1; i < toWrite[11].length - 1; i++) {
			System.out.println((i + 1) + " : " + toWrite[11][i]);
		}
		
		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, toWrite[11].length - 1 + 2);
		@SuppressWarnings("unused")
		Scanner in = new Scanner(System.in);
		String change;

		// Change menu depending on the user answer
		switch(answer) {
		
		/*
		case 2 : // Change movie's title
			System.out.println(toWrite[7][toWrite[7].length]);
			change = in.nextLine();
			this.movie.setTitle(change);
			break;
			
		case 3 : // Change movie's themes
			System.out.println(toWrite[7][toWrite[7].length]);
			change = in.nextLine();
			this.movie.setTheme(change);
			break;
			
		case 4 : // Change movie's producers
			System.out.println(toWrite[7][toWrite[7].length]);
			change = in.nextLine();
			this.movie.setProducers(change);
			break;
			
		case 5 : // Change movie's production year
			System.out.println(toWrite[7][toWrite[7].length]);
			change = in.nextLine();
			this.movie.setProductionYear(change);
			break;
			
		case 6 : // Change movie's score
			System.out.println(toWrite[7][toWrite[7].length]);
			change = in.nextLine();
			this.movie.setScore(change);
			break;
			
		case 7 : // Change movie's description
			System.out.println(toWrite[7][toWrite[7].length]);
			change = in.nextLine();
			this.movie.setDescriptor(change);
			break;
			
		case 8 : // Change movie's price
			System.out.println(toWrite[7][toWrite[7].length]);
			change = in.nextLine();
			this.movie.setPrice(change);
			break;
		*/
			
		case 0 : // Return to the connected menu
			this.movie = null;
			this.menuId = 5;
			break;
			
		case 1 : // Return to the displayMovieDetails menu
			this.menuId = 10;
			break;
			
		default : // Disconnect
			this.user = null;
			this.statu = 0;
			this.menuId = 0;
			break;
		}
	}
	private void displayCommentOfMovie() { // 12 // 13
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the previous menu (displayMovieDetails)
 		System.out.println("1 : " + toWrite[toWrite.length - 1][3]); // To return to the connected menu
 		
 		if(this.statu == 2) { // Add admin choices if the user is one
 	 		System.out.println(toWrite[12][0]);
 			for(int i = 0; i < this.movie.getComments().size(); i++) { // Display comments
 				/* System.out.println("(i + 2) : " + "De " + this.movie.getComments().get(i).getAuthor() + " à " + this.movie.getComments().get(i).getDate());
 				 * System.out.println(this.movie.getComments().get(i).getContent());
 				 * System.out.println();
 				 */
 			}
 			
 		} else { // Display comments
 			for(int i = 0; i < this.movie.getComments().size(); i++) {
 				/* System.out.println("De " + this.movie.getComments().get(i).getAuthor() + " à " + this.movie.getComments().get(i).getDate());
 				 * System.out.println(this.movie.getComments().get(i).getContent());
 				 * System.out.println();
 				 */
 			}
 		}
		
		// Get user answer
		int answer;
		if(this.statu == 2) {
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
			
		default : // Hide the selected comment
			// this.movie.getComments().get(answer - 2).hide();
			this.menuId = 11;
			break;
		}
	}
	private void searchMovie() { // 14
 		// Display user choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]); // To return to the connected menu
		System.out.println(toWrite[14][0]);
		for(int i = 1; i < toWrite[14].length; i++) {
			// System.out.println("(i + 1) : " + toWrite[14][i]);
		}
		
		// Get user answer
		int answer = getInputAsIntBetweenAandB(0, toWrite[14].length + 1);
		@SuppressWarnings("unused")
		Scanner in = new Scanner(System.in);
		String search;

		// Change menu depending on the user answer
		switch(answer) {
			
		/*
		case 1 : // Return to the connected menu
			displayMovieCollection(searchMovieByTitle());
			break;
			
		case 2 : // Return to the connected menu
			displayMovieCollection(searchMovieByTheme());
			break;
			
		case 3 : // Return to the connected menu
			displayMovieCollection(searchMovieByProducer());
			break;
			
		case 4 : // Return to the connected menu
			displayMovieCollection(searchMovieByActor());
			break;
			
		case 5 : // Return to the connected menu
			displayMovieCollection(searchMovieByProductionDate());
			break;
			
		case 6 : // Return to the connected menu
			displayMovieCollection(searchMovieByScore());
			break;
			
		case 7 : // Return to the connected menu
			displayMovieCollection(searchMovieByPrice());
			break;
		*/

		case 0 : // Return to the connected menu
		default : // Display details about a movie
			this.menuId = 5;
			break;
		}
	}
}