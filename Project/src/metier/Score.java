package metier;
import java.util.Date;

public class Score {

	// Fields
	private long value;
	private Date date;
	private Movie movie;
	private User user;
	
	// Initialize
	public Score(long value, Movie movie, User user) {
		super();
		this.value = value;
		this.movie = movie;
		this.user = user;
		this.date = new Date();
	}
	
	// Getters
	public long getValue() {
		return value;
	}
	public Date getDate() {
		return date;
	}
	public Movie getMovie() {
		return movie;
	}
	public User getUser() {
		return User;
	}
	
	// Setters
	public void setValue(long value) {
		this.value = value;
	}

	// Methods

	public void 
}
