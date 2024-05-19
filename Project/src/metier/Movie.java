package metier;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Movie {

    private static double reductionRate = 0.9;

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
    private double price;

    // Constructor
    public Movie(String title, int code, String theme, Date productionDate, String description, 
                 List<String> acteursPrincipaux, List<String> producteurs, List<Score> scores, 
                 List<Comment> comments, double price) {
        this.title = title;
        this.code = code;
        this.theme = theme;
        this.productionDate = productionDate;
        this.description = description;
        this.acteursPrincipaux = acteursPrincipaux != null ? new CopyOnWriteArrayList<>(acteursPrincipaux) : new CopyOnWriteArrayList<>();
        this.producteurs = producteurs != null ? new CopyOnWriteArrayList<>(producteurs) : new CopyOnWriteArrayList<>();
        this.scores = scores != null ? new CopyOnWriteArrayList<>(scores) : new CopyOnWriteArrayList<>();
        this.comments = comments != null ? new CopyOnWriteArrayList<>(comments) : new CopyOnWriteArrayList<>();
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
        return new CopyOnWriteArrayList<>(scores);
    }
    public List<Comment> getComments() {
        return new CopyOnWriteArrayList<>(comments);
    }
    public List<String> getActeursPrincipaux() {
        return new CopyOnWriteArrayList<>(acteursPrincipaux);
    }
    public List<String> getProducteurs() {
        return new CopyOnWriteArrayList<>(producteurs);
    }

    // Calculate
    public double getNoteMoyenne() {
        if (scores.isEmpty()) {
            return 0;
        }
        double totalRating = 0;
        for (Score score : scores) {
            totalRating += score.getValue();
        }
        return totalRating / scores.size();
    }
    public double calculateAverageRating() {
        if (scores.isEmpty()) {
            return 0; // Si aucun score n'est disponible, la note moyenne est 0
        }

        double totalRating = 0;
        for (Score score : scores) {
            totalRating += score.getValue();
        }
        return totalRating / scores.size();
    }
    public double calculateReducedPrice(User user) {
        if (user.getIsSubscribe()) {
            // Si l'utilisateur est abonné, appliquer une réduction de 10% sur le prix du film
            return reductionRate * 0.9;
        } else {
            // Si l'utilisateur n'est pas abonné, retourner le prix du film sans réduction
            return price;
        }
    }

    // Setters with validation
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    public void setProductionDate(Date productionDate) {
        if (productionDate.after(new Date())) {
            throw new IllegalArgumentException("Production date cannot be in the future");
        }
        this.productionDate = productionDate;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setActeursPrincipaux(List<String> acteursPrincipaux) {
        this.acteursPrincipaux = new CopyOnWriteArrayList<>(acteursPrincipaux);
    }
    public void setProducteurs(List<String> producteurs) {
        this.producteurs = new CopyOnWriteArrayList<>(producteurs);
    }

    // Modify fields
    public void modifierFilm(User user, String nouveauTheme, Date nouvelleProductionDate, 
                             String nouvelleDescription, List<String> nouveauxActeurs, 
                             List<String> nouveauxProducteurs) {
        if (user.getIsAdmin()) {
            this.theme = nouveauTheme;
            this.productionDate = nouvelleProductionDate;
            this.description = nouvelleDescription;
            if (nouveauxActeurs != null) {
                this.acteursPrincipaux = new CopyOnWriteArrayList<>(nouveauxActeurs);
            }
            if (nouveauxProducteurs != null) {
                this.producteurs = new CopyOnWriteArrayList<>(nouveauxProducteurs);
            }
            System.out.println("Film modifié par l'administrateur.");
        } else {
            System.out.println("Accès refusé. Seuls les administrateurs peuvent modifier les films.");
        }
    }
    public void addComment(int code, String text, Date publicationDate, boolean activated, User author) {
        Comment comment = new Comment(code, text, publicationDate, activated, this, author);
        comments.add(comment);
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public void removeComment(Comment comment, User user) {
        if (user.getIsAdmin() || comment.getAuthor().equals(user)) {
            comments.remove(comment);
        } else {
            throw new SecurityException("Accès refusé. Seuls les administrateurs ou les auteurs peuvent supprimer ce commentaire.");
        }
    }
    public void addScore(User user, long value, Date date) {
        // Vérifier si l'utilisateur a déjà donné une note pour ce film
        for (Score existingScore : scores) {
            if (existingScore.getUser() == user) {
                // Si l'utilisateur a déjà donné une note, vous pouvez choisir de ne rien faire ou de lancer une exception
                // Pour cet exemple, nous choisissons de ne rien faire
                return;
            }
        }
        // Si l'utilisateur n'a pas encore donné de note, ajouter la nouvelle note
        Score score = new Score(code, value, date, this, user);
        scores.add(score);
    }
    public void addScore(Score score) {
        scores.add(score);
    }
    public void addActeursPrincipaux(String acteurPrincipal) {
    	this.acteursPrincipaux.add(acteurPrincipal);
    }
    public void addProducteurs(String producteur) {
    	this.producteurs.add(producteur);
    }
}