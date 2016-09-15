package com.velja.biblioteka.prozor;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;

import com.velja.biblioteka.prozor.komponente.Kruzic;
import com.velja.biblioteka.prozor.komponente.Ploca;
import com.velja.biblioteka.prozor.komponente.Stil;

@SuppressWarnings("serial")
public class KomponenteLista extends Ploca {

	private final Kruzic mPozadina, mDugmici, mPolja;

	public KomponenteLista(ActionListener l) {
		super(new BorderLayout());
		Ploca p = new Ploca();
		{
			ButtonGroup group = new ButtonGroup();

			mPozadina = new Kruzic("pozadina");
			mPozadina.addActionListener(l);
			group.add(mPozadina);
			mPozadina.setSelected(true);
			p.add(mPozadina);

			mDugmici = new Kruzic("dugmiæi");
			mDugmici.addActionListener(l);
			group.add(mDugmici);
			p.add(mDugmici);

			mPolja = new Kruzic("polja");
			mPolja.addActionListener(l);
			group.add(mPolja);
			p.add(mPolja);
		}
		add(p, BorderLayout.CENTER);
	}

	public KomponenteLista() {
		this(null);
	}

	public Stil getSelected() {
		if(mPozadina.isSelected()) return POZADINE;
		if(mDugmici.isSelected()) return DUGMICI;
		if(mPolja.isSelected()) return POLJA;
		return null;
	}
}
