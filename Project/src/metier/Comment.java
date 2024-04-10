package metier;
import java.util.Date;

public class Comment {

	// Fields
	private String text;
	private Date publicationDate;
	private boolean activated;
	private Movie movie;
	private User author;
	
	// Initialize
	public Comment(String text, Movie movie, User author) {
		super();
		this.text = text;
		this.movie = movie;
		this.author = author;
		this.activated = true;
		this.publicationDate = new Date();
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

	// Setters
	public void setText(String text) {
		this.text = text;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	// Methods
}