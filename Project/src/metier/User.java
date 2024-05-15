package metier;
import java.util.List;
import java.util.ArrayList;

public class User {
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
    private List<Comment> comment;
    
    
    public User(int code, List<Comment> comment, boolean isAdmin, List<Movie> panier, List<Purchase> historiqueAchats, String email, String motDePasse, String nom, String prenom, String adresse, String phraseSecrete) {
        this.code=code;
    	this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.phraseSecrete = phraseSecrete;
        this.historiqueAchats = historiqueAchats;
        this.panier = panier;
        this.isAdmin = isAdmin;
        if (comment == null) {
            this.comment = new ArrayList<>();
        } else if (comment.isEmpty()) {
            this.comment = new ArrayList<>();
        } else {
            this.comment = comment;
        }

    }

    public int getCode() {
    	return code;
    }
    
    public void setCode(int code) {
    	this.code=code;
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPhraseSecrete() {
        return phraseSecrete;
    }

    public void setPhraseSecrete(String phraseSecrete) {
        this.phraseSecrete = phraseSecrete;
    }

    public List<Purchase> getHistoriqueAchats() {
        return historiqueAchats;
    }

    public void setHistoriqueAchats(List<Purchase> historiqueAchats) {
    	this.historiqueAchats = historiqueAchats;
    }
    
    
    public List<Movie> getPanier() {
        return panier;
    }
    
    public void setPanier(List<Movie> panier) {
    	this.panier=panier;
    }
    
    
    
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public List<Comment> getComment() {
    	return comment;
    }
    
    public void setComment(List<Comment> comment) {
    	this.comment=comment;
    }
    
    // methods
   
}
