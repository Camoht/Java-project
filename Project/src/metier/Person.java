package metier;
import java.util.List;

public class Person {
	
	// Fields
	private int id;
	private List<String> jobs;
	private String name;
	private String surname;
	private List<Movie> movies;

	// Initialize
	public Person(List<String> jobs, String name, String surname) {
		super();
		this.jobs = jobs;
		this.name = name;
		this.surname = surname;
	}
	
	// Getters
	public int getId() {
		return id;
	}
	public List<String> getJobs() {
		return jobs;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public List<Movie> getMovies() {
		return movies;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	// Methods
	public boolean isAdmin() {
		return false;
	}
	public void addMovie(Movie movie) {
		movies.add(movie);
	}
	public void addJob(String job) {
		jobs.add(job);
	}
}