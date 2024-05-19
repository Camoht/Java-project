package metier;

import java.util.List;
import java.util.ArrayList;

public class User {
	
	// Fields
    private int code;
    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String adresse;
    private String phraseSecrete;
    private List<Purchase> historiqueAchats;
    private List<Movie> panier;
    private boolean isAdmin;
    private List<Comment> comments;
    private List<Score> scores;
    private boolean isSubscribe;

    // Constructor
    public User(boolean isSubscribe,int code, List<Comment> comments, boolean isAdmin, List<Movie> panier, List<Purchase> historiqueAchats,
                String email, String motDePasse, String nom, String prenom, String adresse, String phraseSecrete) {
        this.code = code;
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.phraseSecrete = phraseSecrete;
        this.historiqueAchats = historiqueAchats != null ? historiqueAchats : new ArrayList<>();
        this.panier = panier != null ? panier : new ArrayList<>();
        this.isAdmin = isAdmin;
        this.comments = comments != null ? comments : new ArrayList<>();
        this.scores = scores != null ? scores : new ArrayList<>();
        this.historiqueAchats = historiqueAchats != null ? historiqueAchats : new ArrayList<>();
    }
    
    // Getter
    public List<Comment> getComments() {
		return comments;
	}
	public List<Score> getScores() {
		return scores;
	}
    public boolean getIsSubscribe() {
    	return isSubscribe;
    }
    public int getCode() {
        return code;
    }
    public String getEmail() {
        return email;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getPhraseSecrete() {
        return phraseSecrete;
    }
    public List<Purchase> getHistoriqueAchats() {
        return historiqueAchats;
    }
    public List<Movie> getPanier() {
        return panier;
    }
    public boolean getIsAdmin() {
        return isAdmin;
    }
    public List<Comment> getComment() {
        return comments;
    }
    
    // Setter
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	public void setSubscribe(boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	public void setIsSubscribe(boolean isSubscribe) {
    	this.isSubscribe=isSubscribe;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setPhraseSecrete(String phraseSecrete) {
        this.phraseSecrete = phraseSecrete;
    }
    public void setHistoriqueAchats(List<Purchase> historiqueAchats) {
        this.historiqueAchats = historiqueAchats;
    }
    public void setPanier(List<Movie> panier) {
        this.panier = panier;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public void setComment(List<Comment> comment) {
        this.comments = comment;
    }

    // Modify fields
    public void addMovieToPanier(Movie movie) {
        if (movie != null) {
            panier.add(movie);
        }
    }
    public void removeMovieFromPanier(Movie movie) {
        if (movie != null && panier.contains(movie)) {
            panier.remove(movie);
        }
    }
    public void addPurchaseToHistorique(Purchase purchase) {
        historiqueAchats.add(purchase);
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public void addScore(Score score) {
    	scores.add(score);
    }
}
