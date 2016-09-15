package biblioteka.knjige;

public class Book {
	
	private Pisac pisac;
	private String ime, search;
	
	
	public Book(String ime, Pisac p) {
		this.ime = new String(ime);
		search = ime.toLowerCase().replace('è', 'c').replace('æ', 'c').replace('š', 's').replace('ž', 'z').replace("ð", "dj");
		pisac = p;
	}
	
	
	public Pisac getPisac() {
		return pisac;
	}
	
	public String getIme() {
		return ime;
	}
	
	public String getSearch() {
		return search;
	}
	
	
	public void setPisac(Pisac p) {
		pisac = p;
	}
	
	public void setIme(String i) {
		ime = new String(i);
		search = ime.toLowerCase().replace('è', 'c').replace('æ', 'c').replace('š', 's').replace('ž', 'z').replace("ð", "dj");
	}
	
	
	@Override
	public String toString() {
		return ime;
	}
}
