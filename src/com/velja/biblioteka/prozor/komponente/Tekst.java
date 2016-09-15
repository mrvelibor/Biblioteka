package com.velja.biblioteka.prozor.komponente;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Tekst extends JLabel implements Tema {

	public Tekst(boolean b) {
		super();
		setFont(POZADINE.getFont());
		setBackground(POZADINE.getBackground());
		setForeground(POZADINE.getForeground());
		if(b) POZADINE.add(this);
	}

	public Tekst() {
		this(true);
	}

	public Tekst(String text, boolean b) {
		this(b);
		setText(text);
	}

	public Tekst(String text) {
		this(text, true);
	}

	public Tekst(String text, int alignment, boolean b) {
		this(text, b);
		setHorizontalAlignment(alignment);
	}

	public Tekst(String text, int alignment) {
		this(text, alignment, true);
	}

}
