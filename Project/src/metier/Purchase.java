package metier;

import java.util.Date;
import java.util.List;

public class Purchase {
    private int code;
    private User user;
    private Date dateAchat;
    private double montant;

    public Purchase(int code, User user, Date dateAchat, double montant) {
        this.code = code;
        this.user = user;
        this.dateAchat = dateAchat;
        this.montant = montant;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double calculerMontantPanier() {
        double montantTotal = 0;
        List<Movie> panier = user.getPanier();
        for (Movie movie : panier) {
            montantTotal += movie.getPrice();
        }
        return montantTotal;
    }

    public String genererFacture() {
        StringBuilder facture = new StringBuilder();
        facture.append(dateAchat).append("\n");
        facture.append(user.getCode()).append(" ").append(user.getNom()).append(" ").append(user.getPrenom()).append("\n");
        for (Movie movie : user.getPanier()) {
            facture.append("- ").append(movie.getTitle()).append(movie.getCode()).append(") : ").append(movie.getPrice()).append(" EUR\n");
        }
        facture.append(montant).append(" EUR\n");
        return facture.toString();
    }
}
