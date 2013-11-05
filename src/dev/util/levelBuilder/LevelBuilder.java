package dev.util.levelBuilder;

import java.io.File;
import org.jdom2.*;

public class LevelBuilder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//load/new level
		//if new level read level name
		//start gui for level builder
		//display selected block
		//if mouse click and no collision, place new block at mouse location
		//save

	}
	
	
	public void save(File saveFile){
		Document doc=new Document();
		Element root=new Element("blocks");
	}
	//save
	//create new document(JDOM?)
	//for each block
	//save block as xml object
	//create/open file
	//write document to file

	//load
	//open file
	//read nodes in document
	//for each xml element, read the brick,
	//add the brick to the array
	
	//display
	//use level clas from game
	//just don't call update
}
