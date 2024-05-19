package comparators;

import metier.Movie;

public class movieComparatorByDate implements java.util.Comparator<Movie> {
	@Override
    public int compare(Movie a, Movie b) {
        return a.getProductionDate().after(b.getProductionDate()) == true ? 1 : 0;
    }
}