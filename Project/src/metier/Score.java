package metier;
import java.util.Date;

public class Score {

	// Fields
	private long value;
	private Date date;
	private Movie movie;
	private User author;
	
	// Initialize
	public Score(long value, Movie movie, User author) {
		super();
		this.value = value;
		this.movie = movie;
		this.author = author;
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
	public User getAuthor() {
		return author;
	}
	
	// Setters
	public void setValue(long value) {
		this.value = value;
	}

	// Methods
}
