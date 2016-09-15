package com.velja.biblioteka.prozor.komponente;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public interface Tema {

	public static final Stil POZADINE = new Stil("Pozadina", new JLabel()),
			DUGMICI = new Stil("Dugmici", new JButton()),
			POLJA = new Stil("TekstPolja", new JTextField());

}
