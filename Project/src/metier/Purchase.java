package metier;

import java.util.Date;
import java.util.List;

public class Purchase {
	
	// Fields
    private int code;
    private User user;
    private Date dateAchat;
    private double montant;
    private List<Movie> panier;

    // Constructor
    public Purchase(int code, User user, Date dateAchat, double montant, List<Movie> panier) {
        this.code = code;
        this.user = user;
        this.dateAchat = dateAchat;
        this.montant = montant;
        this.panier = panier;
    }

    // Getters
    public int getCode() {
        return code;
    }
    public User getUser() {
        return user;
    }
    public Date getDateAchat() {
        return dateAchat;
    }
    public double getMontant() {
        return montant;
    }
    public List<Movie> getPanier() {
		return panier;
	}

    // Setters
    public void setCode(int code) {
        this.code = code;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
	public void setPanier(List<Movie> panier) {
		this.panier = panier;
	}

    // Calculate
    public double calculerMontantPanier() {
        double montantTotal = 0;
        List<Movie> panier = user.getPanier();
        for (Movie movie : panier) {
            montantTotal += movie.getPrice();
        }
        return montantTotal;
    }
    static public double calculerMontantPanier(List<Movie> movies, User user) {
        double montantTotal = 0;
        for (Movie movie : movies) {
            montantTotal += movie.getPrice();
        }
        return montantTotal;
    }

	// Methods
    public String genererFacture() {
        StringBuilder facture = new StringBuilder();
        facture.append(dateAchat).append("\n");
        facture.append(user.getNom()).append(" ").append(user.getPrenom()).append("\n");
        for (Movie movie : panier) {
            facture.append("- ").append(movie.getTitle()).append(" - ").append(movie.getPrice()).append(" EUR\n");
        }
        facture.append(montant).append(" EUR");
        return facture.toString();
    }
}
