package comparators;

import metier.Comment;

public class commentComparatorByDate implements java.util.Comparator<Comment> {
	
	@Override
    public int compare(Comment a, Comment b) {
        return a.getPublicationDate().after(b.getPublicationDate()) == true ? 1 : 0;
    }
}