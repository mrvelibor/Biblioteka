package com.velja.biblioteka.prozor.dijalog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;

import com.velja.biblioteka.Biblioteka;
import com.velja.biblioteka.prozor.komponente.Dugme;
import com.velja.biblioteka.prozor.komponente.Ploca;
import com.velja.biblioteka.prozor.komponente.Polje;
import com.velja.knjige.Book;

@SuppressWarnings("serial")
public class IzmeniKnjigu extends Dijalog {

	private Book mBook;

	private final Polje mIme;

	protected IzmeniKnjigu() {
		super("Izmeni knjigu");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		mIme = new Polje();
		mIme.setPreferredSize(new Dimension(180, 21));
		mIme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					izmeni();
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
		mIme.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				mIme.selectAll();
			}
		});
		add(mIme);

		Ploca p = new Ploca();
		Dugme izmeni = new Dugme("Izmeni");
		izmeni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				izmeni();
				setVisible(false);
			}
		});
		p.add(izmeni);
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
		mBook = Biblioteka.getBook();
		mIme.setText(mBook.getName());
		mIme.requestFocusInWindow();
	}

	private void izmeni() {
		if(mBook.getName().compareTo(mIme.getText()) != 0) {
			mBook.setName(mIme.getText());
			mBook.getAuthor().sort();
			Biblioteka.changeBook(mBook);
		}
	}

}
