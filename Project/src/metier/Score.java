package metier;
import java.util.Date;

public class Score {

	// Fields
	private int code;
	private long value;
	private Date date;
	private Movie movie;
	private User user;
	
	// Initialize
    public Score(int code, long value, Date date, Movie movie, User user) {
        this.code = code;
        this.value = value;
        this.date = date;
        this.movie = movie;
        this.user = user;
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
		return user;
	}
	
	// Setters
	public void setValue(long value) {
		this.value = value;
	}

	// Methods

	//public void 
}
