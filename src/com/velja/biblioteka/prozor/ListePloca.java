package com.velja.biblioteka.prozor;

import java.awt.GridLayout;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.velja.biblioteka.Biblioteka;
import com.velja.biblioteka.prozor.komponente.Lista;
import com.velja.biblioteka.prozor.komponente.Ploca;
import com.velja.knjige.Book;
import com.velja.knjige.Author;

@SuppressWarnings("serial")
public class ListePloca extends Ploca {

	private static boolean sAnotherList = false;

	private class Selector extends DefaultListSelectionModel {

		private boolean mGestureStarted = false;

		@Override
		public void setSelectionInterval(int index0, int index1) {
			if(!mGestureStarted || sAnotherList) {
				if(isSelectedIndex(index0)) super.removeSelectionInterval(index0, index1);
				else super.setSelectionInterval(index0, index1);
			}
			mGestureStarted = true;
		}

		@Override
		public void setValueIsAdjusting(boolean isAdjusting) {
			if(isAdjusting == false) {
				mGestureStarted = false;
				sAnotherList = true;
			}
		}

	}

	private final Lista<Author> mPisci = new Lista<Author>(Biblioteka.getPisci());
	private final Lista<Book> mKnjige = new Lista<Book>(Biblioteka.getKnjige());

	public ListePloca() {
		super();
		setLayout(new GridLayout(1, 2));
		mPisci.setSelectionModel(new Selector());
		mPisci.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Biblioteka.selPisac(mPisci.getSelectedValue());
				refreshKnjige();
			}
		});
		mPisci.setLayoutOrientation(Lista.VERTICAL);
		add(new JScrollPane(mPisci, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

		mKnjige.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Biblioteka.selBook(mKnjige.getSelectedValue());
			}
		});
		mKnjige.setSelectionModel(new Selector());
		mKnjige.setLayoutOrientation(Lista.VERTICAL);
		add(new JScrollPane(mKnjige, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
	}

	public void refreshPisci() {
		mPisci.setListData(Biblioteka.getPisci());
	}

	public void refreshKnjige() {
		Author pisac = Biblioteka.getPisac();
		if(pisac != null) mKnjige.setListData(pisac.toArray());
	}

	public void clearKnjige() {
		mKnjige.setListData(Biblioteka.getKnjige());
	}

	public void clearAll() {
		refreshPisci();
		clearKnjige();
	}

	public void set(Author[] pisci) {
		mPisci.setListData(pisci);
	}

	public void set(Book[] knjige) {
		mKnjige.setListData(knjige);
	}

	public void select(Author pisac) {
		mPisci.setSelectedValue(pisac, true);
	}

	public void select(Book knjiga) {
		mKnjige.setSelectedValue(knjiga, true);
	}

}
