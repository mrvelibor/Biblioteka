package com.velja.biblioteka.prozor.dijalog;

import javax.swing.JDialog;

import com.velja.biblioteka.Biblioteka;
import com.velja.biblioteka.prozor.Prozor;

@SuppressWarnings("serial")
public abstract class Dijalog extends JDialog {

	public static final int DA = 0, NE = 1, ODUSTANI = 2;

	protected static final Prozor sProzor = Biblioteka.sProzor;

	public static void init() {
	}

	public static void pck() {
		dodajK.pack();
		dodajP.pack();
		izmeniK.pack();
		izmeniP.pack();
		promeniTemu.pack();
		promeniFont.pack();
	}

	public static void disp() {
		dodajK.dispose();
		dodajP.dispose();
		izmeniK.dispose();
		izmeniP.dispose();
		promeniTemu.dispose();
		promeniFont.dispose();
	}

	protected Dijalog(String naslov, boolean modal) {
		super(sProzor, naslov);
		setResizable(false);
		setType(Type.POPUP);
		setDefaultCloseOperation(modal ? DISPOSE_ON_CLOSE : HIDE_ON_CLOSE);
		setModal(modal);
	}

	protected Dijalog(String naslov) {
		this(naslov, false);
	}

	@Override
	public void setVisible(boolean b) {
		if(b) {
			if(!isVisible()) {
				refresh();
				setLocationRelativeTo(sProzor);
			} else requestFocus();
		}
		if(isModal()) sProzor.setEnabled(!b);
		super.setVisible(b);
	}

	@Override
	public void dispose() {
		setVisible(false);
		super.dispose();
	}

	protected abstract void refresh();

	private static final Dijalog dodajK = new DodajKnjigu(),
			dodajP = new DodajPisca(), izmeniK = new IzmeniKnjigu(),
			izmeniP = new IzmeniPisca(), promeniTemu = new PromeniBoje(),
			promeniFont = new PromeniFont();

	public static void dodajKnjigu() {
		dodajK.setVisible(true);
	}

	public static void dodajPisca() {
		dodajP.setVisible(true);
	}

	public static void izmeniKnjigu() {
		izmeniK.setVisible(true);
	}

	public static void izmeniPisca() {
		izmeniP.setVisible(true);
	}

	public static void promeniTemu() {
		promeniTemu.setVisible(true);
	}

	public static void promeniFont() {
		promeniFont.setVisible(true);
	}

	public static int potvrda(String naslov, String tekst, int dugmici) {
		return new Potvrda(naslov, tekst, dugmici).getOdgovor();
	}
}
