package com.velja.biblioteka.prozor.komponente;

import java.awt.LayoutManager;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ploca extends JPanel implements Tema {

	public Ploca(boolean b) {
		super();
		setFont(POZADINE.getFont());
		setBackground(POZADINE.getBackground());
		setForeground(POZADINE.getForeground());
		if(b) POZADINE.add(this);
	}

	public Ploca() {
		this(true);
	}

	public Ploca(LayoutManager layout, boolean b) {
		this(b);
		setLayout(layout);
	}

	public Ploca(LayoutManager layout) {
		this(layout, true);
	}

}
