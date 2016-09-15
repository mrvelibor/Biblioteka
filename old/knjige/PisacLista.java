package biblioteka.knjige;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

@SuppressWarnings("serial")
public class PisacLista extends ArrayList<Pisac> {
			
	private static final Comparator<Pisac> compPisac = new Comparator<Pisac>() {
		@Override
		public int compare(Pisac a, Pisac b) {
			return a.getSearch().compareTo(b.getSearch());
		}
	};
	
	private static final Comparator<Book> compBook = new Comparator<Book>() {
		@Override
		public int compare(Book a, Book b) {
			return a.getSearch().compareTo(b.getSearch());
		}
	};
	
	
	private final BookLista sve = new BookLista() {
		private static final long serialVersionUID = 1L;
		@Override
		public boolean add(Book k) {
			super.add(k);
			Collections.sort(this, compBook);
			return true;
		}
	};
	
	

	public void sort() {
		Collections.sort(this, compPisac);
	}
	
	@Override
	public boolean add(Pisac p) {
		for(Pisac a : this)
			if(p.getIme().compareToIgnoreCase(a.getIme()) == 0) return false;
		super.add(p);
		for(Book k : p.getKnjige())
			sve.add(k);
		sort();
		return true;
	}
	
	public Book add(Book k) {
		Book n = k.getPisac().add(k);
		if(n == k) sve.add(n);
		return n;
	}
	
	public boolean remove(Pisac p) {
		if(!p.editable) return false;
		for(Book k : p.getKnjige())
			sve.remove(k);
		return super.remove(p);
	}
	
	public boolean remove(Book k) {
		k.getPisac().remove(k);
		sve.remove(k);
		return true;
	}
	
	@Override
	public void clear() {
		super.clear();
		sve.clear();
	}
	
	@Override
	public Pisac[] toArray() {
		return toArray(new Pisac[size()]);
	}
	
	
	public PisacLista searchPisac(String s) {
		PisacLista lst = new PisacLista();
		for(Pisac p : this)
			if(p.editable)
				if(p.getSearch().contains(s.toLowerCase()))
					lst.add(p);
		return lst;
	}
	
	public BookLista searchBook(String s) {
		return sve.search(s);
	}
	
	public Book[] getKnjige() {
		return sve.toArray();
	}
	
	
	public void load(String f) throws IOException {
		if(!f.endsWith(".txt")) f += ".txt";
		File file = new File(f);
		if (!file.exists()) {
			add(new Pisac("<bez pisca>"));
			return;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "cp1250"));
		
		clear();
		Pisac tek = null;
		String line = null;
		while(true) {
			if((line = br.readLine()) == null) break;
			if(line.startsWith("/")) {
				tek = new Pisac(line.substring(1));
				add(tek);
			}
			else if(!line.isEmpty()) {
				Book k = new Book(line, tek);
				if(tek.add(k) == k)
					sve.add(k);
			}
		}
		
		br.close();
	}

	public void load(URL u) {
		clear();
		Pisac tek = null;
		Scanner scanner = null;
		try {
			scanner = new Scanner(u.openStream());
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.startsWith("/")) {
					tek = new Pisac(line.substring(1));
					add(tek);
				}
				else if(!line.isEmpty()) {
					Book k = new Book(line, tek);
					if(tek.add(k) == k)
						sve.add(k);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(scanner != null) scanner.close();
		}
	}
	
	public void save(String f) throws IOException {
		if(!f.endsWith(".txt")) f += ".txt";
		File file = new File(f);
		if (!file.exists())
			file.createNewFile();
		PrintWriter bw = new PrintWriter(file, "cp1250");
		
		for(Pisac p : this) {
			bw.println('/' + p.getIme());
			for(Book k : p.getKnjige())
				bw.println(k.getIme());
			bw.println();
		}
		
		bw.close();
	}

}
