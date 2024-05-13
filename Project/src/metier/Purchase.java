package metier;

public class Achat {
    private Film film;
    private Date dateAchat;
    private double montant;

    public Achat(Film film, Date dateAchat, double montant) {
        this.film = film;
        this.dateAchat = dateAchat;
        this.montant = montant;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
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
