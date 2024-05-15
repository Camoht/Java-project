package comparators;

import metier.Movie;

public class movieComparatorByDate implements java.util.Comparator<Movie> {
	@Override
    public int compare(Movie a, Movie b) {
        // return a.getDate().after(b.getDate());
		return 0;
    }
}