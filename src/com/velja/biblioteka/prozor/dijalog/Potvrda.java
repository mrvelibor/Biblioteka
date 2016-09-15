package com.velja.biblioteka.prozor.dijalog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

import com.velja.biblioteka.prozor.komponente.Dugme;
import com.velja.biblioteka.prozor.komponente.Ploca;
import com.velja.biblioteka.prozor.komponente.Tekst;

@SuppressWarnings("serial")
public class Potvrda extends Dijalog {

	private int mOdgovor;

	private void addButtons(int i) {
		Ploca p1 = new Ploca(false);
		add(p1);

		Dugme d;

		d = new Dugme(i == NE || i == ODUSTANI ? "Da" : "OK", false);
		d.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mOdgovor = 0;
				dispose();
			}
		});
		p1.add(d);
		if(i < NE) return;

		d = new Dugme("Ne", false);
		d.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mOdgovor = 1;
				dispose();
			}
		});
		p1.add(d);
		if(i < ODUSTANI) return;

		d = new Dugme("Odustani", false);
		d.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mOdgovor = 2;
				dispose();
			}
		});
		p1.add(d);
	}

	protected Potvrda(String naslov, String tekst, int dugmici) {
		super(naslov, true);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		Ploca p0 = new Ploca(false);
		p0.add(new Tekst(tekst, false));
		add(p0);
		addButtons(dugmici);
		mOdgovor = dugmici;

		pack();
		setVisible(true);
	}

	@Override
	protected void refresh() {
	}

	public int getOdgovor() {
		return mOdgovor;
	}
}
