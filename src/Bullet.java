import java.applet.*;
import java.awt.*;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;


public class Bullet {
	private int height;
	private int width;
	private int xPos;
	private int yPos;
	private double bulletAngle;
	private int anchorX;
	private int anchorY;
	private boolean active;
	private Image bullet_Pic;
	private int type;

	public Bullet(int bullet_Type, double angle,int xAnchor, int yAnchor,int high, int wide, int posX, int posY, Image pic){
		height=high;
		width=wide;
		xPos=posX;
		yPos=posY;
		bullet_Pic=pic;
		bulletAngle=angle;
		anchorX=xAnchor;
		anchorY=yAnchor;
		active=true;
		type = bullet_Type;
	}

	public Image getPic(){
		return bullet_Pic;
	}

	public int getType(){
		return type;
	}
	
	public void setActive (boolean appear){
		active=appear;
	}

	public boolean getActive(){
		return active;
	}
	public double getAngle(){
		return bulletAngle;
	}
	public int getxAnchor(){
		return anchorX;
	}
	public int getyAnchor(){
		return anchorY;

	}
	public int getX(){
		return xPos;
	}
	public int getY(){
		return yPos;
	}
	
	public void setY(int newY){
		yPos=newY;
	}
	public void setPic(Image newPic){
		bullet_Pic=newPic;
	}


}
