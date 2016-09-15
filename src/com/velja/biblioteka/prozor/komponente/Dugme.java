package com.velja.biblioteka.prozor.komponente;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Dugme extends JButton implements Tema {

	public Dugme(String text, boolean b) {
		super(text);
		setFont(DUGMICI.getFont());
		setBackground(DUGMICI.getBackground());
		setForeground(DUGMICI.getForeground());
		if(b) DUGMICI.add(this);
	}

	public Dugme(String text) {
		this(text, true);
	}

}
