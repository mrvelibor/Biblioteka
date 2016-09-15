package biblioteka.knjige;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@SuppressWarnings("serial")
public class BookLista extends ArrayList<Book> {
	
	private static final Comparator<Book>
		abc = new Comparator<Book>() {
			@Override
			public int compare(Book a, Book b) {
				return a.getSearch().compareTo(b.getSearch());
			}
		},
		cba = new Comparator<Book>() {
			@Override
			public int compare(Book a, Book b) {
				return b.getSearch().compareTo(a.getSearch());
			}
		};

	private static Comparator<Book> comp = abc;
	
	public static void setComp(boolean b) {
		if(b) comp = abc;
		else comp = cba;
	}
	
	
	public void sort() {
		Collections.sort(this, comp);
	}
	
	@Override
	public boolean add(Book k) {
		super.add(k);
		sort();
		return true;
	}
	
	@Override
	public Book[] toArray() {
		return toArray(new Book[size()]);
	}

	public BookLista search(String s) {
		BookLista lst = new BookLista();
		for(Book k : this)
			if(k.getSearch().contains(s.toLowerCase()))
				lst.add(k);
		return lst;
	}

}
