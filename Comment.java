package metier;

import java.util.Date;

public class Comment {
    private int code;
    private String text;
    private Date publicationDate;
    private boolean activated;
    private Movie movie;
    private User author;

    // Constructor
    public Comment(int code, String text, Date publicationDate, boolean activated, Movie movie, User author) {
        this.code = code;
        this.text = text;
        this.publicationDate = publicationDate;
        this.activated = activated;
        this.movie = movie;
        this.author = author;
    }

    // Getters
    public int getCode() {
        return code;
    }

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

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
