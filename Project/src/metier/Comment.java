package metier;
import java.util.Date;

public class Comment {

	// Fields
	private int code;
	private String text;
	private Date publicationDate;
	private boolean activated;
	private Movie movie;
	private User author;
	
	// Initialize
	public Comment(int code, String text, Date publicationDate, boolean activated, Movie movie, User author) {
	    this.code = code;
	    this.text = text;
	    this.publicationDate = publicationDate;
	    this.activated = activated;
	    this.movie = movie;
	    this.author = author;
	}
	
	// Getters
	public String getText() {
		return text;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public boolean isActivated() {
		return activated;
	}
	public Movie getMovie() {
		return movie;
	}
	public User getAuthor() {
		return author;
	}
	
	public int getCode() {
		return code;
	}

	// Setters
	public void setText(String text) {
		this.text = text;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public void setCode(int code) {
		this.code=code;
	}
 
	public void setAuthor(User author) {
		this.author = author;
	}
	// Methods
	
	public void disableComment(Movie movie, User user, Comment comment) {
		if (user.getIsAdmin()) {
			if (this.activated==false) {
			} else {
				this.activated=false;
}
	}
	
	}
}
