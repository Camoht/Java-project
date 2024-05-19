package comparators;

import metier.Movie;

public class movieMostAppreciated implements java.util.Comparator<Movie> {
	@Override
    public int compare(Movie a, Movie b) {
        return (int) (a.calculateAverageRating() - b.calculateAverageRating());
    }
}