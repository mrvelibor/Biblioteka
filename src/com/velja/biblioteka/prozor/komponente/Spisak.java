package com.velja.biblioteka.prozor.komponente;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Spisak<C> extends JComboBox<C> implements Tema {

	public Spisak() {
		super();
		setFont(POZADINE.getFont());
		setBackground(POZADINE.getBackground());
		setForeground(POZADINE.getForeground());
		POZADINE.add(this);
	}

	public Spisak(C[] components) {
		super(components);
		setFont(POZADINE.getFont());
		setBackground(POZADINE.getBackground());
		setForeground(POZADINE.getForeground());
		POZADINE.add(this);
	}

}
