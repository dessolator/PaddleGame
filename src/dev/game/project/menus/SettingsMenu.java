package dev.game.project.menus;

import java.util.ArrayList;
import org.lwjgl.opengl.Display;
import dev.game.project.menus.buttons.ApplyButton;
import dev.game.project.menus.buttons.Button;
import dev.game.project.menus.buttons.CancelButton;
import dev.game.project.menus.elements.CheckBox;
import dev.game.project.menus.elements.ColorsBox;
import dev.game.project.menus.elements.DisplayDropDown;
import dev.game.project.menus.elements.DropDown;

public class SettingsMenu extends Menu {
	@Override
	public void render() {
		//super.render();
	}

	@Override
	public void update() {
		//super.update();
	}

	ArrayList<CheckBox> myCheckBoxes;
	ArrayList<DropDown> myDropDowns;

	public SettingsMenu() {
		super();
		myButtons=new ArrayList<Button>();
		myCheckBoxes=new ArrayList<CheckBox>();
		myDropDowns=new ArrayList<DropDown>();
		myFrame=new SettingsFrame(
				Display.getWidth()/2,
				Display.getHeight()/2,
				Display.getWidth()/5,
				Display.getHeight()/2
				);
		myButtons.add(new CancelButton(
				Display.getWidth()/2,
				Display.getHeight()/2+Display.getHeight()/30,
				Display.getWidth()/10,
				Display.getHeight()/15)
		);
		myButtons.add(new ApplyButton(
				Display.getWidth()/2,
				Display.getHeight()/2+Display.getHeight()/14+Display.getHeight()/30,
				Display.getWidth()/10,
				Display.getHeight()/15)
		);
		myCheckBoxes.add(new ColorsBox(
				0,
				0,
				0,
				0)
		);
		myDropDowns.add(new DisplayDropDown(
				0,
				0,
				0,
				0)
		);
	}
	

}
