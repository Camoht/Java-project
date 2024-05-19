package comparators;

import metier.Comment;

public class commentComparatorByPositivity implements java.util.Comparator<Comment> {
	
	@Override
    public int compare(Comment a, Comment b) {
        // return a.getDate().after(b.getDate());
		return 0;
    }
}