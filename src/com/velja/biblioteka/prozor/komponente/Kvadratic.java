package com.velja.biblioteka.prozor.komponente;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class Kvadratic extends JCheckBox implements Tema {

	public Kvadratic(String text, boolean sel, boolean b) {
		super(text, b);
		setFont(POZADINE.getFont());
		setBackground(POZADINE.getBackground());
		setForeground(POZADINE.getForeground());
		if(b) POZADINE.add(this);
	}

	public Kvadratic(String text, boolean sel) {
		this(text, sel, true);
	}

}
