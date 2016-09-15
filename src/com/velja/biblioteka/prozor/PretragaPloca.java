package com.velja.biblioteka.prozor;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.velja.biblioteka.Biblioteka;
import com.velja.biblioteka.prozor.komponente.Dugme;
import com.velja.biblioteka.prozor.komponente.Kvadratic;
import com.velja.biblioteka.prozor.komponente.Ploca;
import com.velja.biblioteka.prozor.komponente.Polje;
import com.velja.biblioteka.prozor.komponente.Tekst;

@SuppressWarnings("serial")
public class PretragaPloca extends Ploca {

	private final Polje mPolje;
	private final Dugme mTrazi, mBrisi;
	private final Kvadratic mPisci, mKnjige;

	public PretragaPloca() {
		super();
		setLayout(new GridLayout(4, 1));
		add(new JLabel());
		Ploca plo0 = new Ploca();
		{
			plo0.add(new Tekst("PRETRAGA", Tekst.CENTER));
		}
		add(plo0);

		Ploca plo1 = new Ploca();
		{
			mBrisi = new Dugme(" X ");
			mBrisi.setBorder(BorderFactory.createRaisedBevelBorder());
			mBrisi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Biblioteka.clearAll();
				}
			});
			plo1.add(mBrisi);

			mPolje = new Polje();
			mPolje.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) search();
				}

				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}
			});
			mPolje.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					mPolje.selectAll();
				}
			});
			mPolje.setPreferredSize(new Dimension(180, 23));
			plo1.add(mPolje);

			mTrazi = new Dugme(" traži ");
			mTrazi.setBorder(BorderFactory.createRaisedBevelBorder());
			mTrazi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					search();
				}
			});
			plo1.add(mTrazi);
		}
		add(plo1);

		Ploca plo2 = new Ploca();
		{
			mPisci = new Kvadratic("pisci", true);
			plo2.add(mPisci);
			mKnjige = new Kvadratic("knjige", true);
			plo2.add(mKnjige);
		}
		add(plo2);
	}

	public void search() {
		Biblioteka.search(mPolje.getText(), mPisci.isSelected(), mKnjige.isSelected());
	}

	public void clearAll() {
		mPolje.setText("");
	}
	
	public boolean hasSearch() {
		return !mPolje.getText().isEmpty();
	}

}
