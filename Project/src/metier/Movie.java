package metier;
import java.util.Date;
import java.util.List;

public class Movie {
	
	// Fields
	private int id;
	private String theme;
	private Date productionDate;
	private String description;
	private List<Person> staff;
	private List<Score> scores;
	private List<Comment> comments;
	
	// Initialize
	public Movie(String theme, Date productionDate, String description) {
		super();
		this.theme = theme;
		this.productionDate = productionDate;
		this.description = description;
	}
	
	// Getters
	public String getTheme() {
		return theme;
	}
	public Date getProductionDate() {
		return productionDate;
	}
	public String getDescription() {
		return description;
	}
	public List<Person> getStaff() {
		return staff;
	}
	public List<Score> getScores() {
		return scores;
	}
	public List<Comment> getComments() {
		return comments;
	}

	// Setters
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStaff(List<Person> staff) {
		this.staff = staff;
	}

	// Methods
	public void addComment(Comment comment) {
		comments.add(comment);
	}
	public void addScore(Score score) {
		scores.add(score);
	}
}