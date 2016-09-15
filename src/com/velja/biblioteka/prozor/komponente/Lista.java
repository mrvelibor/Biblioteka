package com.velja.biblioteka.prozor.komponente;

import javax.swing.JList;

@SuppressWarnings("serial")
public class Lista<C> extends JList<C> implements Tema {

	public Lista(C[] components, boolean b) {
		super(components);
		setFont(POLJA.getFont());
		setBackground(POLJA.getBackground());
		setForeground(POLJA.getForeground());
		if(b) POLJA.add(this);
	}

	public Lista(C[] components) {
		this(components, true);
	}

}
