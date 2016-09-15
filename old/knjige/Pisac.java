package biblioteka.knjige;

public class Pisac {
	
	public final boolean editable;
	
	private String ime, search;
	private final BookLista knjige = new BookLista();
	
	
	public Pisac(String ime) {
		this.ime = new String(ime);
		search = ime.toLowerCase().replace('�', 'c').replace('�', 'c').replace('�', 's').replace('�', 'z').replace("�", "dj");
		editable = !ime.startsWith("<");
	}
	
	
	public String getIme() {
		return ime;
	}
	
	public String getSearch() {
		return search;
	}
	
	public Book[] getKnjige() {
		return knjige.toArray();
	}
	
	public boolean nemaBook() {
		return knjige.isEmpty();
	}
	
	
	public void setIme(String i) {
		if(editable) {
			ime = new String(i);
			search = ime.toLowerCase().replace('�', 'c').replace('�', 'c').replace('�', 's').replace('�', 'z').replace("�", "dj");
		}
	}
	
	public Book add(Book k) {
		for(Book a : knjige)
			if(a.getIme().compareToIgnoreCase(k.getIme()) == 0)
				return a;
		if(k.getPisac() == null)
			k.setPisac(this);
		knjige.add(k);
		return k;
	}
	
	public Book add(String i) {
		return add(new Book(i, this));
	}
	
	public void remove(Book k) {
		knjige.remove(k);
	}
	
	public void remove(String i) {
		for(Book k : knjige)
			if(k.getIme().compareToIgnoreCase(i) == 0) {
				knjige.remove(k);
				return;
			}
	}
	
	public void sort() {
		knjige.sort();
	}
	
	
	@Override
	public String toString() {
		return ime;
	}
}
