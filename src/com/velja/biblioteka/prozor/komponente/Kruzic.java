package com.velja.biblioteka.prozor.komponente;

import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class Kruzic extends JRadioButton implements Tema {

	public Kruzic(boolean b) {
		super();
		setFont(POZADINE.getFont());
		setBackground(POZADINE.getBackground());
		setForeground(POZADINE.getForeground());
		if(b) POZADINE.add(this);
	}

	public Kruzic() {
		this(true);
	}

	public Kruzic(String text, boolean b) {
		super(text);
		setFont(POZADINE.getFont());
		setBackground(POZADINE.getBackground());
		setForeground(POZADINE.getForeground());
		if(b) POZADINE.add(this);
	}

	public Kruzic(String text) {
		this(text, true);
	}

}
