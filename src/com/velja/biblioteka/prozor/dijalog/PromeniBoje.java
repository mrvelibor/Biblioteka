package com.velja.biblioteka.prozor.dijalog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JColorChooser;

import com.velja.biblioteka.prozor.KomponenteLista;
import com.velja.biblioteka.prozor.komponente.Dugme;
import com.velja.biblioteka.prozor.komponente.Ploca;
import com.velja.biblioteka.prozor.komponente.Stil;
import com.velja.biblioteka.prozor.komponente.Tema;

@SuppressWarnings("serial")
public class PromeniBoje extends Dijalog implements Tema {

	private final JColorChooser mPaleta;
	private final KomponenteLista mKomponente;

	protected PromeniBoje() {
		super("Tema");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		Ploca p0 = new Ploca();
		mPaleta = new JColorChooser(POZADINE.getBackground());
		p0.add(mPaleta);
		add(p0);

		add(mKomponente = new KomponenteLista(null));

		Ploca p2 = new Ploca();
		Dugme izmeniBG = new Dugme("Postavi boju pozadine");
		izmeniBG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				izmeni(true);
			}
		});
		p2.add(izmeniBG);
		Dugme izmeniFG = new Dugme("Postavi boju teksta");
		izmeniFG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				izmeni(false);
			}
		});
		p2.add(izmeniFG);
		Dugme izadji = new Dugme("Izaði");
		izadji.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		p2.add(izadji);
		add(p2);

		pack();
	}

	@Override
	protected void refresh() {
	}

	private void izmeni(boolean bg) {
		Stil b = mKomponente.getSelected();
		Color c = mPaleta.getColor();
		if(bg) {
			b.setBackground(c);
			if(b == POZADINE) mPaleta.setBackground(c);
		} else b.setForeground(c);
	}

}
