package dev.game.project.menus.elements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ColorsBox extends CheckBox {
	private static boolean isColorChecked;

	public ColorsBox(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		readValFromFile();
		// TODO Auto-generated constructor stub
	}

	private void readValFromFile() {
		Scanner s = null;
		boolean first;
		try {
			s = new Scanner(new BufferedReader(new FileReader("Settings.ini")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		String c=s.next();
		while(!s.next().contentEquals("Color="));
		while(!s.hasNextBoolean()){
			s.next();
		}
		first=s.nextBoolean();
		s.close();
		isColorChecked=first;
		isChecked=first;
	}

	@Override
	public void update() {
		super.update();
		isColorChecked=isChecked;
	}

	/**
	 * @return the isColorChecked
	 */
	public static boolean isColorChecked() {
		return isColorChecked;
	}

	/**
	 * @param isColorChecked the isColorChecked to set
	 */
	public static void setColorChecked(boolean isColorChecked) {
		ColorsBox.isColorChecked = isColorChecked;
	}

}

