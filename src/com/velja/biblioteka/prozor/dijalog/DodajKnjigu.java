package com.velja.biblioteka.prozor.dijalog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import com.velja.biblioteka.Biblioteka;
import com.velja.biblioteka.prozor.komponente.Dugme;
import com.velja.biblioteka.prozor.komponente.Ploca;
import com.velja.biblioteka.prozor.komponente.Polje;
import com.velja.biblioteka.prozor.komponente.Spisak;
import com.velja.knjige.Book;
import com.velja.knjige.Author;

@SuppressWarnings("serial")
public class DodajKnjigu extends Dijalog {

	private final Spisak<Author> mLista;
	private final Polje mNaziv, mKod;

	protected DodajKnjigu() {
		super("Dodaj knjigu");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		mLista = new Spisak<Author>();
		mLista.setPreferredSize(new Dimension(180, 23));
		add(mLista);

		mNaziv = new Polje();
		mNaziv.setPreferredSize(new Dimension(180, 23));
		mNaziv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					dodaj();
					setVisible(false);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		mNaziv.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				mNaziv.selectAll();
			}
		});
		add(mNaziv);

		mKod = new Polje();
		mKod.setPreferredSize(new Dimension(180, 23));
		mKod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					dodaj();
					setVisible(false);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		mKod.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				mKod.selectAll();
			}
		});
		add(mKod);

		Ploca p = new Ploca();
		Dugme dodaj = new Dugme("Dodaj");
		dodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dodaj();
				mNaziv.requestFocusInWindow();
			}
		});
		p.add(dodaj);
		Dugme izadji = new Dugme("Izaði");
		izadji.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		p.add(izadji);
		add(p);

		pack();
	}

	@Override
	protected void refresh() {
		mLista.setModel(new DefaultComboBoxModel<Author>(Biblioteka.getPisci()));
		Author p = Biblioteka.getPisac();
		if(p != null) mLista.setSelectedItem(p);
		else mLista.setSelectedIndex(0);
		mNaziv.setText("");
		mKod.setText("");
		mNaziv.requestFocusInWindow();
	}

	private void dodaj() {
		String naziv = mNaziv.getText();
		if(naziv.isEmpty()) {
			mNaziv.requestFocusInWindow();
			return;
		}
		Author pisac = (Author) mLista.getSelectedItem();
		Book knjiga = new Book(naziv, pisac, false);
		knjiga.changeCode(mKod.getText());
		Biblioteka.add(knjiga);
	}

}