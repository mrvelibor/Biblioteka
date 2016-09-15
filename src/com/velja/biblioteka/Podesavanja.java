package com.velja.biblioteka;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

@SuppressWarnings("serial")
public class Podesavanja extends Properties {

	private boolean mChanged = false;

	public Podesavanja(String file) {
		super();
		load(file);
	}

	public void load(String fileName) {
		InputStream input = null;
		try {
			input = new FileInputStream(fileName);
			load(input);
			mChanged = false;
		} catch(FileNotFoundException e) {
			setDefaults();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void save(String fileName) {
		OutputStream output = null;
		try {
			output = new FileOutputStream(fileName);
			store(output, null);
			mChanged = false;
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(output != null) {
				try {
					output.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public boolean isChanged() {
		return mChanged;
	}

	@Override
	public Object setProperty(String key, String value) {
		mChanged = true;
		return super.setProperty(key, value);
	}
	
	@Override
	public Object remove(Object key) {
		Object obj = super.remove(key);
		if(obj != null) mChanged = true;
		return obj;
	}

	public void setDefaults() {
		String korisnik = getProperty("korisnik", "Korisnik");
		clear();
		setProperty("korisnik", korisnik);
	}

	static String fontStyle(Font font) {
		switch(font.getStyle())
			{
			case Font.BOLD:
				return "BOLD";
			case Font.ITALIC:
				return "ITALIC";
			case Font.BOLD | Font.ITALIC:
				return "BOLDITALIC";
			default:
				return "PLAIN";
			}
	}

	public void setFont(String component, Font font) {
		String option = component + "Font";
		if(font != null) setProperty(option, font.getName() + '-' + fontStyle(font) + '-' + font.getSize());
		else remove(option);
	}

	public void setBackground(String component, Color color) {
		String option = component + "BGColor";
		if(color != null) setProperty(option, Integer.toString(color.getRGB()));
		else remove(option);
	}

	public void setForeground(String component, Color color) {
		String option = component + "FGColor";
		if(color != null) setProperty(option, Integer.toString(color.getRGB()));
		else remove(option);
	}

	public String getFont(String component) {
		return getProperty(component + "Font", "");
	}

	public String getBackground(String component) {
		return getProperty(component + "BGColor", "");
	}

	public String getForeground(String component) {
		return getProperty(component + "FGColor", "");
	}

}
