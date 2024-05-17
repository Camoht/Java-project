package metier;
import java.util.Date;
import java.util.List;


public class Movie {

	// Fields
	private int code;
	private String title;
	private String theme;
	private Date productionDate;
	private String description;
	private List<String> acteursPrincipaux;
	private List<String> producteurs;
	private List<Score> scores;
	private List<Comment> comments;
	private double noteMoyenne;
	private int nombreDeVotes;
	private double price;
	
	// Initialize
	public Movie(String title,int code, String theme, Date productionDate, String description, List<String> acteursPrincipaux,
			List<String> producteurs, List<Score> scores, List<Comment> comments, double noteMoyenne, int nombreDeVotes,
			double price) {
		super();
		this.title=title;
		this.code = code;
		this.theme = theme;
		this.productionDate = productionDate;
		this.description = description;
		this.acteursPrincipaux = acteursPrincipaux;
		this.producteurs = producteurs;
		this.scores = scores;
		this.comments = comments;
		this.noteMoyenne = noteMoyenne;
		this.nombreDeVotes = nombreDeVotes;
		this.price = price;
	}

	// Getters
	public String getTitle() {
		return title;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getCode() {
        return code;
    }

	public String getTheme() {
		return theme;
	}
	
	public Date getProductionDate() {
		return productionDate;
	}
	
	public String getDescription() {
		return description;
	}

	public List<Score> getScores() {
		return scores;
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public int getNombreDeVotes() {
        return nombreDeVotes;
    }

	public double getNoteMoyenne() {
        return noteMoyenne;
    }

	public List<String> getActeursPrincipaux() {
        return acteursPrincipaux;
    }

	public List<String> getProducteurs() {
        return producteurs;
    }

	
	// Setters
	public void setTitle(String title) {
		this.title=title;
	}
	
	public void setPrice(double price) {
		this.price=price;
	}
	
    public void setCode(int code) {
       	this.code = code;
    }
	
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

   	public void setNombreDeVotes(int nombreDeVotes) {
       	this.nombreDeVotes = nombreDeVotes;
    }

	public void setNoteMoyenne(double noteMoyenne) {
		this.noteMoyenne = noteMoyenne;
    }

	public void setActeursPrincipaux(List<String> acteursPrincipaux) {
        this.acteursPrincipaux = acteursPrincipaux;
    }

	public void setProducteurs(List<String> producteurs) {
        this.producteurs = producteurs;
    }
	
	// Methods
	public void addComment(Comment comment) {
		comments.add(comment);
	}
	
	public void addScore(Score score) {
		scores.add(score);
	}
	
	public void modifierFilm(User user, String nouveauTheme, Date nouvelleProductionDate, String nouvelleDescription, List<String> nouveauxActeurs, List<String> nouveauxProducteurs) {
			if (user.getIsAdmin()) {
				this.theme = nouveauTheme;
				this.productionDate = nouvelleProductionDate;
				this.description = nouvelleDescription;
				this.acteursPrincipaux = nouveauxActeurs;
				this.producteurs = nouveauxProducteurs;
				System.out.println("Film modifié par l'administrateur.");
			} else {
				System.out.println("Accès refusé. Seuls les administrateurs peuvent modifier les films.");
				}
	}
			
    public void addComment(int code, String text, Date publicationDate, boolean activated, User author) {
        Comment comment = new Comment(code, text, publicationDate, activated, this, author);
        comments.add(comment);
    }
    
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }
    
    
    
    public void addScore(int code, long value, Date date, User user) {
        Score score = new Score(code, value, date, this, user);
        scores.add(score);
        updateAverageRating(); // Met à jour la note moyenne après l'ajout d'un nouveau score
    }

    // Méthode pour mettre à jour la note moyenne du film
    public void updateAverageRating() {
        double totalRating = 0;
        for (Score score : scores) {
            totalRating += score.getValue();
        }
        noteMoyenne = totalRating / scores.size();
    }
    
    
    
    
	}