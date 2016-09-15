package com.velja.biblioteka.prozor.komponente;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import com.velja.biblioteka.Biblioteka;

public class Stil {

	private final Font mDefFont;
	private final Color mDefBackground, mDefForeground;

	private final String mName;
	private final ArrayList<Component> mList = new ArrayList<>();

	private Font mFont;
	private Color mBackground, mForeground;

	public Stil(String name, Component component) {
		mName = new String(name);
		mDefFont = component.getFont();
		mDefBackground = component.getBackground();
		mDefForeground = component.getForeground();

		Font f = Biblioteka.getFont(mName);
		if(f == null) f = mDefFont;
		mFont = f;

		Color bg = Biblioteka.getBackground(mName);
		if(bg == null) bg = mDefBackground;
		mBackground = bg;

		Color fg = Biblioteka.getForeground(mName);
		if(fg == null) fg = mDefForeground;
		mForeground = fg;
	}

	public boolean add(Component component) {
		return mList.add(component);
	}

	public boolean remove(Component component) {
		return mList.remove(component);
	}

	public void setBackground(Color color) {
		if(color == null) color = mDefBackground;
		if(mBackground == color) return;
		mBackground = color;
		
		if(mBackground != mDefBackground) Biblioteka.setBackground(mName, mBackground);
		else Biblioteka.setBackground(mName, null);
		for(Component c : mList)
			c.setBackground(mBackground);
	}

	public void setForeground(Color color) {
		if(color == null) color = mDefForeground;
		if(mForeground == color) return;
		mForeground = color;
		
		if(mForeground != mDefForeground) Biblioteka.setForeground(mName, mForeground);
		else Biblioteka.setForeground(mName, null);
		for(Component c : mList)
			c.setForeground(mForeground);
	}

	public void setFont(Font font) {
		if(font == null) font = mDefFont;
		if(mFont == font) return;
		mFont = font;
		
		if(mFont != mDefFont) Biblioteka.setFont(mName, mFont);
		else Biblioteka.setFont(mName, null);
		for(Component c : mList)
			c.setFont(mFont);
	}

	public void setDefaults() {
		setBackground(null);
		setForeground(null);
		setFont(null);
	}

	public Color getBackground() {
		return mBackground;
	}

	public Color getForeground() {
		return mForeground;
	}

	public Font getFont() {
		return mFont;
	}

	@Override
	public String toString() {
		return mName;
	}
}
