package metier;
import java.util.Date;
import java.util.List;


public class Purchase {
	private int code;
    private List<Movie> movie;
    private Date dateAchat;
    private double montant;

    public Purchase(int code,List<Movie> movie, Date dateAchat, double montant) {
        this.code=code;
    	this.movie = movie;
        this.dateAchat = dateAchat;
        this.montant = montant;
    }

    public int getCode() {
    	return code;
    }
    
    public void setCode(int code) {
    	this.code=code;
    }
    
    public List<Movie> getMovie() {
        return movie;
    }

    public void setMovie(List<Movie> movie) {
        this.movie = movie;
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
    
}