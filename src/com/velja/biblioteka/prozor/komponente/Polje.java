package com.velja.biblioteka.prozor.komponente;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Polje extends JTextField implements Tema {

	public Polje(boolean b) {
		super();
		setFont(POLJA.getFont());
		setBackground(POLJA.getBackground());
		setForeground(POLJA.getForeground());
		if(b) POLJA.add(this);
	}

	public Polje() {
		this(true);
	}

}
