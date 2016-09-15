package com.velja.biblioteka.prozor;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.velja.biblioteka.Biblioteka;
import com.velja.biblioteka.prozor.dijalog.Dijalog;
import com.velja.biblioteka.prozor.komponente.Ploca;
import com.velja.knjige.Book;
import com.velja.knjige.Author;

@SuppressWarnings("serial")
public class Prozor extends JFrame {

	private final NaslovPloca mNaslov;
	private final ListePloca mListe;
	private final PretragaPloca mPretraga;
	private final PisacPloca mPisac;
	private final KnjigaPloca mBook;
	private final Meni mMeni;

	private boolean mScroll = false;

	public Prozor() {
		super("Biblioteka");
		setMinimumSize(new Dimension(Biblioteka.WIDTH, Biblioteka.HEIGHT));
		setLayout(new BorderLayout());

		add(mNaslov = new NaslovPloca(), BorderLayout.PAGE_START);
		add(mListe = new ListePloca(), BorderLayout.CENTER);

		Ploca ploca = new Ploca();
		ploca.setLayout(new BorderLayout());
		ploca.setPreferredSize(new Dimension(WIDTH, 120));
		{
			ploca.add(mPisac = new PisacPloca(), BorderLayout.LINE_START);
			ploca.add(mPretraga = new PretragaPloca(), BorderLayout.CENTER);
			ploca.add(mBook = new KnjigaPloca(), BorderLayout.LINE_END);
		}
		add(ploca, BorderLayout.PAGE_END);

		setJMenuBar(mMeni = new Meni(this));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void dispose() {
		int i = 1;
		if(Biblioteka.isChanged()) i = Dijalog.potvrda("Izlaz", "Saèuvati izmene?", Dijalog.ODUSTANI);

		switch(i)
			{
			case 0:
				Biblioteka.saveKorisnik();
			case 1:
				Biblioteka.savePodesavanja();
				Dijalog.disp();
				super.dispose();
			default:
			}
	}

	public void setLista(Author[] pisci) {
		mListe.set(pisci);
	}

	public void setLista(Book[] knjige) {
		mListe.set(knjige);
	}

	public void selectPisac(Author pisac, boolean scroll) {
		mScroll = scroll;
		if(pisac != null) {
			mNaslov.setPisac(pisac.getName());
			mNaslov.clearBook();
			if(scroll) mListe.select(pisac);
			mPisac.setButton(true);
		} else {
			if(mPretraga.hasSearch()) {
				if(Biblioteka.hasSearch()) if(scroll) mPretraga.search();
			} else {
				mNaslov.clearAll();
				mListe.clearKnjige();
			}
			mPisac.setButton(false);
		}
	}

	public void selectPisac(Author pisac) {
		selectPisac(pisac, true);
	}

	public void selectBook(Book knjiga) {
		if(knjiga != null) {
			mNaslov.setBook(knjiga.getName());
			mListe.select(knjiga);
			mBook.setButton(true);
		} else {
			if(!mScroll) {
				boolean b = mPretraga.hasSearch() && Biblioteka.hasSearch();
				if(b) mPretraga.search();
				else Biblioteka.clearAll();
			} else mNaslov.clearBook();
			mBook.setButton(false);
		}
	}

	public void setPisacNaslov(String naslov) {
		mNaslov.setPisac(naslov);
	}

	public void setBookNaslov(String naslov) {
		mNaslov.setBook(naslov);
	}

	public void refreshPisci() {
		mListe.refreshPisci();
	}

	public void refreshKnjige() {
		mListe.refreshKnjige();
	}

	public void clearAll() {
		mListe.clearAll();
		mNaslov.clearAll();
		mPretraga.clearAll();
	}

	public void enableSave(boolean b) {
		mMeni.enableSave(b);
	}

}
