package userInterface;

import java.io.IOException;
import java.util.Scanner;
import metier.Bdd;
import metier.User;

public class menu {
	
	// Fields
	private static String[][] toWrite = {
			{"Se connecter", "Créer un compte", "Mot de passe oublié"} , // In the Welcome menu
			{"Veuillez renseigner votre :", "Email", "Mot de passe", "Mail ou Mot de passe incorrect"} , // In the connect menu
			{"Veuillez renseigner votre :", "Nom", "Prénom", "Mot de passe", "Email"} , // In the create an account menu
			{"Veuillez renseigner votre :", "Email", "Phrase secrête", "Mail ou Phrase secrête incorrect"} , // In forgotten password menu
			{"Afficher mes données", "Rechercher un film", "Explorer la liste des film", "Explorer mon historique", "Visualiser mon panier"} , // In connected menu for client
			{"Afficher statistiques", "Ajouter un film", "Ajouter un administrateur"} , // In connected menu for admin
			{"Bienvenu !", "Au revoir !"} , // In main menu
			{"Quitter l'application", "Se déconnecter", "Annuler", "Retour au menu principal" ,  "Veuillez entrer un chiffre correspondant à une option ci-dessus"} // Other
		};
	private boolean continu = true;
	private int menuId = 0;
	private Bdd bdd = new Bdd();
	private String email;
	private int statu = 0; // 0 : not connected, 1 : is client, 2 : is admin
	
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
			
		// Quit
		case -1 :
		default :
			this.continu = false;
			break;
 		}
 	}
 	private void menuWelcome() {
 		
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
	private void menuConnect() {
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
			this.email = answer[0];
			this.statu = this.bdd.connect(answer[0], answer[1]);
			this.menuId = 4;
		} else { // Wrong id
 			System.out.println(toWrite[1][toWrite[1].length - 1]); // Let the user know, his gave the wrong id
			this.menuId = 1;
		} 
	}
	private void menuCreateAccount() {
 		// Display choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]);
 		
		// Create needed variables to get the user answer
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
		this.email = answer[0];
		this.statu = 1;
		
		// Change menu
		this.menuId = 4;
	}
	private void menuForgottenPassword() {
 		// Display choices
 		System.out.println("0 : " + toWrite[toWrite.length - 1][2]);
 		
		// Create needed variables to get the user answer
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
			this.email = answer[0];
			this.statu = this.bdd.connect(answer[0], answer[1]);
			/**/ //Modify the password
			this.menuId = 4;
			
		} else { // Wrong email and wrong secret sentence
			System.out.println(toWrite[3][toWrite[3].length - 1]); // Let the user know he is wrong
			this.menuId = 3; 
		}
	}
	private void connected() {
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
		case 1 : // Searching for a movie
		case 2 : // Explore the movie collection
		case 3 : // Explore history
		case 4 : // See purchases
		case 0 : // Disconnect
		default :
			this.email = null;
			this.menuId = 0;
			break;
		}
	}
}