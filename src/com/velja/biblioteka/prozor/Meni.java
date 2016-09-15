package com.velja.biblioteka.prozor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.velja.biblioteka.Biblioteka;
import com.velja.biblioteka.prozor.dijalog.Dijalog;

@SuppressWarnings("serial")
public class Meni extends JMenuBar {

	private final Prozor mProzor;

	private JMenuItem mSacuvaj;

	public Meni(Prozor p) {
		mProzor = p;
		createMenu();
		setFocusable(false);
	}

	private void createMenu() {
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu("Program");
		add(menu);
		{
			mSacuvaj = new JMenuItem("Saèuvaj");
			mSacuvaj.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Biblioteka.saveKorisnik();
				}
			});
			mSacuvaj.setEnabled(false);
			menu.add(mSacuvaj);

			menu.addSeparator();

			menuItem = new JMenuItem("Izaði");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mProzor.dispose();
				}
			});
			menu.add(menuItem);
		}

		menu = new JMenu("Podešavanja");
		add(menu);
		{
			menuItem = new JMenuItem("Promeni Boje");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Dijalog.promeniTemu();
				}
			});
			menu.add(menuItem);

			menuItem = new JMenuItem("Promeni Font");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Dijalog.promeniFont();
				}
			});
			menu.add(menuItem);

			menu.addSeparator();

			menuItem = new JMenuItem("Obriši podešavanja");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(Dijalog.potvrda("", "Vratiti podešavanja na podrazumevana?", Dijalog.NE) == 0) Biblioteka.clearPodesavanja();
				}
			});
			menu.add(menuItem);
		}

		menu = new JMenu("Pomoæ");
		add(menu);
		{
			menuItem = new JMenuItem("Pomoæ");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Nema ti pomoæi.", "Pomoæ", JOptionPane.QUESTION_MESSAGE);
				}
			});
			menu.add(menuItem);
			/*
			 * menuItem = new JMenuItem("Troubleshoot");
			 * menuItem.addActionListener(new ActionListener() { String
			 * testVersion = "1.7.0_25-b16", activeVersion =
			 * System.getProperty("java.runtime.version"); int status =
			 * getStatus();
			 * 
			 * int getStatus() { int comp =
			 * activeVersion.compareTo(testVersion); if(comp > 0) return
			 * JOptionPane.QUESTION_MESSAGE; else if(comp < 0) return
			 * JOptionPane.ERROR_MESSAGE; else return
			 * JOptionPane.INFORMATION_MESSAGE; }
			 * 
			 * @Override public void actionPerformed(ActionEvent e) {
			 * //System.out.print(System.getProperty("java.runtime.version"));
			 * JOptionPane.showMessageDialog( null,
			 * "Tested on Windows 7 Ultimate 64-Bit\n" +
			 * "with Java Runtime Environment:\n    v" + testVersion +
			 * "\n\nYou're using:\n    " +
			 * System.getProperty("sun.arch.data.model") + "-Bit JRE\n    v" +
			 * activeVersion, "Troubleshoot", status ); } });
			 * menu.add(menuItem);
			 */
			menu.addSeparator();

			menuItem = new JMenuItem("O programu");
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Biblioteka\n" + "    by Velibor Baèujkov\n" + "    2014.", "O programu", JOptionPane.INFORMATION_MESSAGE, Biblioteka.IKONICA);
				}
			});
			menu.add(menuItem);
		}
	}

	public void enableSave(boolean b) {
		mSacuvaj.setEnabled(b);
	}
}
