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
import com.velja.knjige.Author;

@SuppressWarnings("serial")
public class DodajPisca extends Dijalog {
	
	private final Polje mIme;
	
	protected DodajPisca() {
		super("Dodaj pisca");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		mIme = new Polje();
		mIme.setPreferredSize(new Dimension(180, 23));
		mIme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					Biblioteka.add(new Author(mIme.getText()));
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
		Dugme dodaj = new Dugme("Dodaj");
		dodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dodaj();
				mIme.requestFocusInWindow();
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
		mIme.setText("");
		mIme.requestFocusInWindow();
	}
	
	private void dodaj() {
		String ime = mIme.getText();
		if(ime.isEmpty()) {
			mIme.requestFocusInWindow();
			return;
		}
		Biblioteka.add(new Author(ime));
	}
	
}
