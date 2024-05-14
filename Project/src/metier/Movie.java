package metier;
import java.util.Date;
import java.util.List;


public class Movie {

	// Fields
	private int code;
	private String theme;
	private Date productionDate;
	private String description;
	private List<String> acteursPrincipaux;
	private List<String> producteurs;
	private List<Score> scores;
	private List<Comment> comments;
	private double noteMoyenne;
	private int nombreDeVotes;
	
	// Initialize
	public Movie(String theme, Date productionDate, String description) {
		super();
		this.theme = theme;
		this.productionDate = productionDate;
		this.description = description;
	}
	
	// Getters
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
	}
