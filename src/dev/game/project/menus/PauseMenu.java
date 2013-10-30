package dev.game.project.menus;

import static org.lwjgl.opengl.GL11.glColor4f;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import dev.game.project.engine.DrawObject;
import dev.game.project.gameMechanics.PaddleGame;
import dev.game.project.menus.buttons.Button;
import dev.game.project.menus.buttons.MainMenuButton;
import dev.game.project.menus.buttons.ResumeGameButton;


public class PauseMenu extends Menu {
	public PauseMenu(){
		myButtons=new ArrayList<Button>();
		myButtons.add(new MainMenuButton(
				Display.getWidth()/2,
				Display.getHeight()/2-Display.getHeight()/7+Display.getHeight()/30,
				Display.getWidth()/10,
				Display.getHeight()/15)
		);
		myButtons.add(new ResumeGameButton(
				Display.getWidth()/2,
				Display.getHeight()/2+Display.getHeight()/14+Display.getHeight()/30,
				Display.getWidth()/10,
				Display.getHeight()/15)
		);
	}

	@Override
	public void render() {
		PaddleGame.getMyLevel().render();
		glColor4f(0f, 0f, 0f,0.5f);
		DrawObject.drawColoredRect(getCoordX(), getCoordY(), getDimX(), getDimY());
		Mouse.setGrabbed(false);
		for (Button b:myButtons)
			DrawObject.draw(b);
	}

	@Override
	public Texture getTexture() {
		return null;
	}

	@Override
	public float getCoordX() {
		return Display.getWidth()/2;
	}

	@Override
	public float getDimX() {
		return Display.getWidth();
	}

	@Override
	public float getCoordY() {
		return Display.getHeight()/2;
	}

	@Override
	public float getDimY() {
		return Display.getHeight();
	}

}
