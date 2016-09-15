package com.velja.biblioteka.prozor;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;

import com.velja.biblioteka.Biblioteka;
import com.velja.biblioteka.prozor.komponente.Ploca;
import com.velja.biblioteka.prozor.komponente.Tekst;

@SuppressWarnings("serial")
public class NaslovPloca extends Ploca {

	private final Tekst mPisac = new Tekst("BIBLIOTEKA", Tekst.CENTER) {
		@Override
		public void setFont(Font f) {
			super.setFont(f.deriveFont(Font.BOLD, f.getSize() + 4));
		}
	}, mBook = new Tekst("", Tekst.CENTER) {
		@Override
		public void setFont(Font f) {
			super.setFont(f.deriveFont(Font.ITALIC, f.getSize() + 4));
		}
	};

	public NaslovPloca() {
		super();
		setPreferredSize(new Dimension(Biblioteka.WIDTH, 90));
		setLayout(new GridLayout(4, 1));
		add(new JLabel());
		add(mPisac);
		add(mBook);
	}

	public void setPisac(String s) {
		mPisac.setText(s);
	}

	public void setBook(String s) {
		mBook.setText(s);
	}

	public void clearPisac() {
		mPisac.setText("BIBLIOTEKA");
	}

	public void clearBook() {
		mBook.setText("");
	}

	public void clearAll() {
		clearPisac();
		mBook.setText(Biblioteka.getKorisnik());
	}

}
