package metier;
import java.util.List;

public class Bdd {

	// Fields
	private List<Person> people;
	private List<Movie> movies;
	private List<User> users;
	
	// Initialize
	public Bdd() {
		super();
	}
	
	// Getters
	
	// Setters
	
	// Methods
	public void addPerson(Person person) {
		people.add(person);
	}
	public void addMovie(Movie movie) {
		movies.add(movie);
	}
	public void addUser(User user) {
		users.add(user);
	}
}