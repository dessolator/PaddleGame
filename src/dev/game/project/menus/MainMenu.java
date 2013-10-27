package dev.game.project.menus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;

import dev.game.project.menus.buttons.Button;
import dev.game.project.menus.buttons.ExitGameButton;
import dev.game.project.menus.buttons.NewGameButton;
import dev.game.project.menus.buttons.ScoresButton;
import dev.game.project.menus.buttons.SettingsButton;

public class MainMenu extends Menu {
	public MainMenu(){
		myButtons=new ArrayList<Button>();
		myFrame=new MainMenuFrame(
				Display.getWidth()/2,
				Display.getHeight()/2,
				Display.getWidth()/5,
				Display.getHeight()/2
				);
		myButtons.add(new ExitGameButton(
				Display.getWidth()/2,
				Display.getHeight()/2-Display.getHeight()/7+Display.getHeight()/30,
				Display.getWidth()/10,
				Display.getHeight()/15)
		);
		myButtons.add(new ScoresButton(
				Display.getWidth()/2,
				Display.getHeight()/2-Display.getHeight()/14+Display.getHeight()/30,
				Display.getWidth()/10,
				Display.getHeight()/15)
		);
		myButtons.add(new SettingsButton(
				Display.getWidth()/2,
				Display.getHeight()/2+Display.getHeight()/30,
				Display.getWidth()/10,
				Display.getHeight()/15)
		);
		myButtons.add(new NewGameButton(
				Display.getWidth()/2,
				Display.getHeight()/2+Display.getHeight()/14+Display.getHeight()/30,
				Display.getWidth()/10,
				Display.getHeight()/15)
		);
		try {
			background=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/mainMenuBackground.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
