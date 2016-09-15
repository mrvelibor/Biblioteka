package com.velja.biblioteka;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.velja.biblioteka.prozor.Prozor;
import com.velja.biblioteka.prozor.dijalog.Dijalog;
import com.velja.biblioteka.prozor.komponente.Tema;
import com.velja.knjige.Book;
import com.velja.knjige.Author;
import com.velja.knjige.AuthorList;

public class Biblioteka {

	public static final int WIDTH = 640, HEIGHT = 480;
	public static final Icon IKONICA = new ImageIcon(Biblioteka.class.getResource("/ikonica.png"));
	public static final String CONFIG = "podesavanja.cfg";

	private static final Podesavanja sPodesavanja = new Podesavanja(CONFIG);

	private static final AuthorList mPisci = new AuthorList();
	private static Author mPisac = null;
	private static Book mBook = null;

	private static boolean mChanged = false;
	private static boolean mPretraga = false;

	public static final Prozor sProzor = new Prozor();

	public static void loadKorisnik(String ime) {
		if(!ime.isEmpty()) {
			sPodesavanja.setProperty("korisnik", ime);
			mPisci.clear();
			mPisac = null;
			mBook = null;
			setChanged(false);
			sProzor.enableSave(false);
		} else return;
		loadKorisnik();
	}

	private static void loadKorisnik() {
		mPisci.load(sPodesavanja.getProperty("korisnik"));
		sProzor.clearAll();
	}

	public static void saveKorisnik() {
		mPisci.save(sPodesavanja.getProperty("korisnik"));
		setChanged(false);
		sProzor.enableSave(false);
	}

	public static void savePodesavanja() {
		if(sPodesavanja.isChanged()) sPodesavanja.save(CONFIG);
	}

	public static void setFont(String component, Font font) {
		sPodesavanja.setFont(component.toLowerCase(), font);
	}

	public static void setBackground(String component, Color color) {
		sPodesavanja.setBackground(component.toLowerCase(), color);
	}

	public static void setForeground(String component, Color color) {
		sPodesavanja.setForeground(component.toLowerCase(), color);
	}

	public static void clearPodesavanja() {
		sPodesavanja.setDefaults();
		Tema.POZADINE.setDefaults();
		Tema.DUGMICI.setDefaults();
		Tema.POLJA.setDefaults();
	}

	public static String getKorisnik() {
		return sPodesavanja.getProperty("korisnik");
	}

	public static Font getFont(String component) {
		String colorCode = sPodesavanja.getFont(component.toLowerCase());
		if(colorCode.isEmpty()) return null;
		else return Font.decode(colorCode);
	}

	public static Color getBackground(String component) {
		String colorCode = sPodesavanja.getBackground(component.toLowerCase());
		if(colorCode.isEmpty()) return null;
		else return new Color(Integer.parseInt(colorCode));
	}

	public static Color getForeground(String component) {
		String colorCode = sPodesavanja.getForeground(component.toLowerCase());
		if(colorCode.isEmpty()) return null;
		else return new Color(Integer.parseInt(colorCode));
	}

	public static void setChanged(boolean b) {
		mChanged = b;
		sProzor.enableSave(b);
	}

	public static boolean isChanged() {
		return mChanged;
	}

	public static boolean hasPisac() {
		return mPisac != null;
	}

	public static boolean hasBook() {
		return mBook != null;
	}

	public static Author getPisac() {
		return mPisac;
	}

	public static Book getBook() {
		return mBook;
	}

	public static Author[] getPisci() {
		return mPisci.toArray();
	}

	public static Book[] getKnjige() {
		return mPisci.getBooks().toArray();
	}

	public static void selPisac(Author pisac, boolean scroll) {
		if(mPisac != pisac) sProzor.selectPisac(mPisac = pisac, scroll);
	}

	public static void selPisac(Author pisac) {
		sProzor.selectPisac(mPisac = pisac, true);
	}

	public static void selBook(Book knjiga) {
		if(knjiga == mBook) return;
		if(knjiga != null) selPisac(knjiga.getAuthor(), false);
		sProzor.selectBook(mBook = knjiga);
	}

	public static void add(Author pisac) {
		clearAll();
		if(mPisci.add(pisac)) {
			sProzor.refreshPisci();
			setChanged(true);
		} else {
			int index = mPisci.getIndex(pisac);
			pisac = mPisci.get(index);
		}
		selPisac(pisac);
	}

	public static void add(Book knjiga) {
		setChanged(mPisci.add(knjiga));
		clearAll();
		selPisac(knjiga.getAuthor());
		selBook(knjiga);
	}

	public static void remove(Author pisac) {
		// if(!pisac.editable) return;
		mPisci.remove(mPisac);
		selPisac(null);
		sProzor.refreshPisci();
		setChanged(true);
	}

	public static void remove(Book knjiga) {
		mPisci.remove(mBook);
		selBook(null);
		sProzor.refreshKnjige();
		setChanged(true);
	}

	public static void changePisac(Author pisac) {
		mPisci.sort();
		sProzor.refreshPisci();
		selPisac(pisac);
		setChanged(true);
	}

	public static void changeBook(Book knjiga) {
		sProzor.refreshKnjige();
		selBook(knjiga);
		setChanged(true);
	}

	public static void search(String query, boolean pisac, boolean knjiga) {
		if(query.isEmpty()) {
			clearAll();
			return;
		}
		if(pisac || knjiga) {
			mPretraga = true;
			selBook(null);
			selPisac(null, false);
			if(pisac) {
				sProzor.setLista(mPisci.searchAuthors(query).toArray());
				if(!knjiga) sProzor.setLista(new Book[0]);
			}
			if(knjiga) {
				sProzor.setLista(mPisci.searchBooks(query).toArray());
				if(!pisac) sProzor.setLista(new Author[0]);
			}
			sProzor.setPisacNaslov(query);
			sProzor.setBookNaslov("pretraga");
		}
	}

	public static void clearAll() {
		selPisac(null);
		selBook(null);
		sProzor.clearAll();
		mPretraga = false;
	}

	public static boolean hasSearch() {
		return mPretraga;
	}

	public static void main(String[] args) {
		System.setProperty("file.encoding", "cp1250");
		loadKorisnik();
		Dijalog.init();

		/*
		 * Scanner s = null; try { URL url = new URL(
		 * "https://dl.dropboxusercontent.com/s/8p16kj7dawzyr9u/Mici.txt?dl=1&token_hash=AAH4yqnwbltseq9EFMYdf0MERGT2wrbguOYekmPOLAVPSg"
		 * ); s = new Scanner(url.openStream()); while (s.hasNextLine())
		 * System.out.println(s.nextLine()); } catch (IOException e) {
		 * e.printStackTrace(); } finally { if(s != null) s.close(); }
		 */
	}

}
