package metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Bdd {

	// Fields
	private List<Movie> movies = new ArrayList<Movie>();
	private List<Admin> admin = new ArrayList<Admin>();
	private List<User> users = new ArrayList<User>();
	private List<Comment> comments= new ArrayList<Comment>();
	private List<Purchase> purchases= new ArrayList<Purchase>();
	private static String[] movieFields = {
		    "Theme :",
		    "Producers :",
		    "Main Actors :",
			"Production Year :",
		    "Rating :",
		    "Description :",
		    "Price :"
	};
	private static String[] userFields = {
		    "Password :",
		    "Surname :",
		    "Name :",
		    "Subscriber :"		
	};
	private static String[] adminFields = userFields;
	private static String[] commentFields = {
		    "Author :",
		    "Movie :",
		    "Description :",
		    "Positive :",
		    "Active :"
	};
	
	// Initialize and close
	public Bdd() {
		super();
		
		readSavedBdd();
	}
	public void close() {
		saveBdd();
	}
		
	// Public methods
	public void addAdmin(Admin admin) {
		this.admin.add(admin);
	}
	public void addMovie(Movie movie) {
		this.movies.add(movie);
	}
	public void addUser(User user) {
		this.users.add(user);
	}
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
	public void savedUsers() {
		try {
			
			// Create the necessary folders if there are not
			new File("Bdd").mkdirs();
			new File("Bdd/Users").mkdirs();
			
			// Needed variable to write data in files
		    BufferedWriter writer;
			
			// Save Users :
			for (int i = 0; i < this.users.size(); i++) {
				
				// Write data in the file
			    writer = new BufferedWriter(new FileWriter("Bdd/Users/" /*+  users.get(i).getEmail()*/));
			    
			    /*
			    writer.write(userFields[0] + "\n");
			    writer.write(users.get(i).getPassword() + "\n");
			    writer.write(userFields[1] + "\n");
			    writer.write(users.get(i).getSurname() + "\n");
			    writer.write(userFields[2] + "\n");
			    writer.write(users.get(i).getName() + "\n");
			    writer.write(userFields[3] + "\n");
			    writer.write(users.get(i).getSubscriber() + "\n");
			    */
			    /*
			    for (int j = 0; j < users.get(i).getPurchases().length; j++){
			    	writer.write(userFields[4] + "\n");
				    writer.write(userFields[5] + "\n");
				    for (int k = 0; k < users.get(i).getPurchases().get(j).length; k++){
				    	writer.write(users.get(i).getPurchases().get(j).getMovies().get(k) + "\n");
				    }
				    writer.write(userFields[6] + "\n");
					writer.write(users.get(i).getPurchases().get(j).getDate() + "\n");
				    writer.write(userFields[7] + "\n");
					writer.write(users.get(i).getPurchases().get(j).getisPayed() + "\n");
			    }
				*/
			    
			    writer.close();
			}
	
	    } catch (IOException e) { // Display a message if an error has occurred while creating the file / writing in the files
	        System.out.println("An error occurred : We could not save your modifications");
	    }
	}
	public void saveAdmins() {
		try {
			
			// Create the necessary folders if there are not
			new File("Bdd").mkdirs();
			new File("Bdd/Admins").mkdirs();
			
			// Needed variable to write data in files
		    BufferedWriter writer;

			// Save Admins :
			for (int i = 0; i < this.admin.size(); i++) {
				
				// Write data in the file
			    writer = new BufferedWriter(new FileWriter("Bdd/Admins/" /*+  admin.get(i).getEmail()*/));
			    
			    /*
			    writer.write("Password : \n");
			    writer.write(admin.get(i).getPassword() + "\n");
			    writer.write("Surname : \n");
			    writer.write(admin.get(i).getSurname() + "\n");
			    writer.write("Name : \n");
			    writer.write(admin.get(i).getName() + "\n");
			    writer.write("Subscriber : \n");
			    writer.write(admin.get(i).getSubscriber() + "\n");
				*/
			    
			    writer.close();
			}
	
	    } catch (IOException e) { // Display a message if an error has occurred while creating the file / writing in the files
	        System.out.println("An error occurred : We could not save your modifications");
	    }
	}
	public void saveComments() {
		try {
			
			// Create the necessary folders if there are not
			new File("Bdd").mkdirs();
			new File("Bdd/Comments").mkdirs();
			
			// Needed variable to write data in files
		    BufferedWriter writer;

			// Save Comments :
			for (int i = 0; i < this.admin.size(); i++) {
				
				// Write data in the file
			    writer = new BufferedWriter(new FileWriter("Bdd/Comments/" /*+  comments.get(i).getId()*/));
			    
			    /*
			    writer.write(commentFields[0] + "\n");
			    writer.write(comments.get(i).getAuthor() + "\n");
			    writer.write(commentFields[1] + "\n");
			    writer.write(comments.get(i).getMovie() + "\n");
			    writer.write(commentFields[2] + "\n");
			    writer.write(comments.get(i).getDescription() + "\n");
			    writer.write(commentFields[3] + "\n");
			    writer.write(comments.get(i).getPositive() + "\n");
			    writer.write(commentFields[4] + "\n");
			    writer.write(comments.get(i).getActive() + "\n");
				*/
			    
			    writer.close();
			}
	
	    } catch (IOException e) { // Display a message if an error has occurred while creating the file / writing in the files
	        System.out.println("An error occurred : We could not save your modifications");
	    }
	}
	public void saveBdd() {
		saveMovies();
		savedUsers();
		saveAdmins();
		saveComments();
	}
	public int connect(String email, String password) {
		for (int i = 0; i < this.users.size(); i++) {
			/*
			if(this.users.get(i).getEmail.equals(email) && this.users.get(i).getPassword.equals(password)) {
				return 1;
			}
			*/
		}
		for (int i = 0; i < this.admin.size(); i++) {
			/*
			if(this.admin.get(i).getEmail.equals(email) && this.admin.get(i).getPassword.equals(password)) {
				return 2;
			}
			*/
		}
		return 0;
	}
	public int emailAndSecretSentenceInBdd(String email, String secretSentence) {
		for (int i = 0; i < this.users.size(); i++) {
			/*
			if(this.users.get(i).getEmail.equals(email) && this.users.get(i).getSecretSentence.equals(secretSentence)) {
				return 1;
			}
			*/
		}
		for (int i = 0; i < this.admin.size(); i++) {
			/*
			if(this.admin.get(i).getEmail.equals(email) && this.users.get(i).getSecretSentence.equals(secretSentence)) {
				return 2;
			}
			*/
		}
		return 0;
	}
	
	// Private methods
	private void readSavedMovies() {
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
			    	for(int j = 0; j < Bdd.movieFields.length; j++) {
				    	if (lineInFile.equals(Bdd.movieFields[j])){
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
	@SuppressWarnings("deprecation")
	private void readSavedUsers() {
		File bddUSersDirectory = new File("Bdd/Users");
		
		// Check if there is saved data, if not : stop the function (there is no data to read)
		if(! bddUSersDirectory.exists()) { 
			System.out.println("Bdd does not exist");
			return;
		}

		// Get saved Users
		File[] listOfFile = bddUSersDirectory.listFiles();
		for (int i = 0; i < listOfFile.length; i++) { // For each existing file
			
			// Create needed variables to add a user
			String password;
			String surname;
			String name;
			boolean subscriber;
			
			try {
				
				// Create needed variables
				BufferedReader reader = new BufferedReader(new FileReader(listOfFile[i])); // to read the current file
			    int idField = -1; // Correspond to an id of the actual saving field
			    boolean isTitle; // true : the read line is a title corresponding to an idField; false : the read line is a field content

			    String lineInFile = reader.readLine(); // Read the first line of the current file
			    while(lineInFile != null) {
		    		isTitle = false;
			    	
		    		// Get the idFields if the line is one
			    	for(int j = 0; j < Bdd.userFields.length; j++) {
				    	if (lineInFile.equals(Bdd.userFields[j])){
				    		idField = j;
				    		isTitle = true;
				    	}
			    	}
			    	
			    	// Get the value of the current field
			    	if(! isTitle) { // The line is a field content
			    		switch(idField) {
			    		case 0 :
			    			password = lineInFile.trim();
			    			break;
			    		case 1 :
			    			surname = lineInFile.trim();
			    			break;
			    		case 2 :
			    			name = lineInFile.trim();
			    			break;
			    		case 3 :
			    			subscriber = lineInFile.trim().equals("true");
			    			break;
			    		}
			    	}

			    	lineInFile = reader.readLine(); // Read the next line of the current file
			    }

			    reader.close();
			    
			    // addUser(new User(listOfFile[i], password, surname, name, subscriber));
			
			} catch (IOException e) { // Display a message if an error has occurred while reading in the files
		        System.out.println("An error occurred : We could not get saved informations");
		    }
		}
	}
	private void readSavedAdmin() {
		File bddAdminsDirectory = new File("Bdd/Admins");
		
		// Check if there is saved data, if not : stop the function (there is no data to read)
		if(! bddAdminsDirectory.exists()) {
			return;
		}

		// Get saved Users
		File[] listOfFile = bddAdminsDirectory.listFiles();
		for (int i = 0; i < listOfFile.length; i++) { // For each existing file
			
			// Create needed variables to add a user
			String password = "";
			String surname = "";
			String name = "";
			boolean subscriber = false;
			
			try {
				
				// Create needed variables
				BufferedReader reader = new BufferedReader(new FileReader(listOfFile[i])); // to read the current file
			    int idField = -1; // Correspond to an id of the actual saving field
			    boolean isTitle; // true : the read line is a title corresponding to an idField; false : the read line is a field content

			    String lineInFile = reader.readLine(); // Read the first line of the current file
			    while(lineInFile != null) {
		    		isTitle = false;
			    	
		    		// Get the idFields if the line is one
			    	for(int j = 0; j < Bdd.adminFields.length; j++) {
				    	if (lineInFile.equals(Bdd.adminFields[j])){
				    		idField = j;
				    		isTitle = true;
				    	}
			    	}
			    	
			    	// Get the value of the current field
			    	if(! isTitle) { // The line is a field content
			    		switch(idField) {
			    		case 0 :
			    			password = lineInFile.trim();
			    			break;
			    		case 1 :
			    			surname = lineInFile.trim();
			    			break;
			    		case 2 :
			    			name = lineInFile.trim();
			    			break;
			    		case 3 :
			    			subscriber = lineInFile.trim().equals("true");
			    			break;
			    		}
			    	}

			    	lineInFile = reader.readLine(); // Read the next line of the current file
			    }

			    reader.close();
			    
			    // addAdmin(new User(listOfFile[i], password, surname, name, subscriber));
			
			} catch (IOException e) { // Display a message if an error has occurred while reading in the files
		        System.out.println("An error occurred : We could not get saved informations");
		    }
		}
	}
	private void readSavedComments() {
		File bddCommentsDirectory = new File("Bdd/Comments");
		
		// Check if there is saved data, if not : stop the function (there is no data to read)
		if(! bddCommentsDirectory.exists()) {
			return;
		}

		// Get saved Comments
		File[] listOfFile = bddCommentsDirectory.listFiles();
		for (int i = 0; i < listOfFile.length; i++) { // For each existing file
			
			// Create needed variables to add a user
			String author = "";
			String movie = "";
			String description = "";
			boolean positive = false;
			boolean active = false;
			
			try {
				
				// Create needed variables
				BufferedReader reader = new BufferedReader(new FileReader(listOfFile[i])); // to read the current file
			    int idField = -1; // Correspond to an id of the actual saving field
			    boolean isTitle; // true : the read line is a title corresponding to an idField; false : the read line is a field content

			    String lineInFile = reader.readLine(); // Read the first line of the current file
			    while(lineInFile != null) {
		    		isTitle = false;
			    	
		    		// Get the idFields if the line is one
			    	for(int j = 0; j < Bdd.commentFields.length; j++) {
				    	if (lineInFile.equals(Bdd.commentFields[j])){
				    		idField = j;
				    		isTitle = true;
				    	}
			    	}
			    	
			    	// Get the value of the current field
			    	if(! isTitle) { // The line is a field content
			    		switch(idField) {
			    		case 0 :
			    			author = lineInFile.trim();
			    			break;
			    		case 1 :
			    			movie = lineInFile.trim();
			    			break;
			    		case 2 :
			    			description += lineInFile.trim();
			    			break;
			    		case 3 :
			    			positive = lineInFile.trim().equals("true");
			    			break;
			    		case 4 :
			    			active = lineInFile.trim().equals("true");
			    			break;
			    		}
			    	}

			    	lineInFile = reader.readLine(); // Read the next line of the current file
			    }

			    reader.close();
			    
			    // addAdmin(new User(listOfFile[i], password, surname, name, subscriber));
			} catch (IOException e) { // Display a message if an error has occurred while reading in the files
		        System.out.println("An error occurred : We could not get saved informations");
		    }
		}
	}
	private void readSavedBdd() {

		// Check if there is saved data, if not : stop the function (there is no data to read)
		if(! new File("Bdd").exists()) {
			return;
		}
		
		readSavedMovies();
		readSavedUsers();
		readSavedAdmin();
		readSavedComments();
	}
}