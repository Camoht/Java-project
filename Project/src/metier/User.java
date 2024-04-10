package metier;
import java.util.List;

public class User {
	
	// Fields
	private String email; // Id
	private String password;
	private String secretQuestion;
	private String username;
	private List<Movie> history;
	private List<Comment> comments;
	private List<Score> scores;
	
	// Initialize
 	public User(String email, String password, String secretQuestion, String username) {
		super();
		this.email = email;
		this.password = password;
		this.secretQuestion = secretQuestion;
		this.username = username;
	}

	// Getters
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public List<Movie> getHistory() {
		return history;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public List<Score> getScores() {
		return scores;
	}
	
	// Setters
	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	// Methods
	public boolean connect(String email, String password) {
		if(this.password.equals(password) && this.email.equals(email)){
			return true;
		}
		return false;
	}
	public void addMovieToHistory(Movie movie) {
		history.add(movie);
	}
	public void addScore(Score score) {
		scores.add(score);
	}
}