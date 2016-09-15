package com.velja.biblioteka.prozor.dijalog;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import com.velja.biblioteka.prozor.KomponenteLista;
import com.velja.biblioteka.prozor.komponente.Dugme;
import com.velja.biblioteka.prozor.komponente.Kvadratic;
import com.velja.biblioteka.prozor.komponente.Ploca;
import com.velja.biblioteka.prozor.komponente.Spisak;
import com.velja.biblioteka.prozor.komponente.Stil;
import com.velja.biblioteka.prozor.komponente.Tekst;
import com.velja.biblioteka.prozor.komponente.Tema;

@SuppressWarnings("serial")
public class PromeniFont extends Dijalog implements Tema {

	private final Spisak<String> mFontovi;
	private final Spisak<String> mVelicina;
	private final Kvadratic mBold, mItalic;
	private final KomponenteLista mKomponente;

	protected PromeniFont() {
		super("Font");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		Ploca p0 = new Ploca();
		p0.add(new Tekst("Font:", JLabel.LEFT));
		mFontovi = new Spisak<String>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
		mFontovi.setSelectedItem("Dialog");
		p0.add(mFontovi);
		add(p0);

		Ploca p1 = new Ploca();
		p1.add(new Tekst("Velicina:", JLabel.LEFT));
		mVelicina = new Spisak<String>();
		for(int i = 7; i <= 21; mVelicina.addItem(Integer.toString(i++)));
		mVelicina.setSelectedItem("12");
		p1.add(mVelicina);
		p1.add(mBold = new Kvadratic("bold", true));
		p1.add(mItalic = new Kvadratic("italic", false));
		add(p1);

		add(mKomponente = new KomponenteLista(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshLists();
			}
		}));
		refreshLists();

		Ploca p3 = new Ploca();
		Dugme izmeni = new Dugme("Postavi font");
		izmeni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				izmeni();
			}
		});
		p3.add(izmeni);
		Dugme izadji = new Dugme("Izaði");
		izadji.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		p3.add(izadji);
		add(p3);

		pack();
	}

	@Override
	protected void refresh() {
		mFontovi.requestFocusInWindow();
	}

	private void refreshLists() {
		Font f = mKomponente.getSelected().getFont();
		mFontovi.setSelectedItem(f.getName());
		mVelicina.setSelectedItem(Integer.toString(f.getSize()));
		mBold.setSelected(f.isBold());
		mItalic.setSelected(f.isItalic());
	}

	private void izmeni() {
		Stil k = mKomponente.getSelected();
		String name = (String) mFontovi.getSelectedItem();
		int size = Integer.parseInt(((String) mVelicina.getSelectedItem())), style = Font.PLAIN;
		if(mBold.isSelected()) style |= Font.BOLD;
		if(mItalic.isSelected()) style |= Font.ITALIC;
		k.setFont(new Font(name, style, size));
		Dijalog.pck();
	}

}
