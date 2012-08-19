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

public class Helicopter {
	private Image heli_Pic;
	private Image heli_Pic_Right;
	private Image heli_Pic_Left;
	private Image destroyed_Pic_Right;
	private Image destroyed_Pic_Left;
	private int health;
	private int points;
	private int max_Health;
	private int barMultiplier;
	private int fire_Rate;
	private int heliHeight;
	private int heliWidth;
	private int heliType;
	private boolean alive;

	public Helicopter (int type, Image heliPic,Image heliPicLeft, int rateOfFire, int health_Points, Image destroyed_Heli_Right,Image destroyed_Heli_Left,int width, int height){
		heli_Pic=heliPic;
		heli_Pic_Right=heliPic;
		heli_Pic_Left=heliPicLeft;
		fire_Rate=rateOfFire;
		health=health_Points;
		max_Health=health_Points;
		destroyed_Pic_Right=destroyed_Heli_Right;
		destroyed_Pic_Left=destroyed_Heli_Left;
		heliHeight=height;
		heliWidth=width;
		heliType=type;
		if (heliType==1){
			points=100;
			barMultiplier=3;
		}
		else if (heliType==2){
			points=500;
			barMultiplier=1;
		}
		alive=true;
	}
	
	public void hit_Damage (int damage){
		health-=damage;
	}
	
	public int getWidth(){
		return heliWidth;
	}
	
	public int getHeight(){
		return heliHeight;
	}
	
	public void heli_Death (boolean right){
		alive=false;
		if (right==true){
			heli_Pic=destroyed_Pic_Right;
		}
		else if (right==false){
			heli_Pic=destroyed_Pic_Left;
		}

	}
	
	public int getPoints(){
		return points;
	}
	
	public Image getPic(){
		return heli_Pic;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int fire_Rate(){
		return fire_Rate;
	}
	
	public boolean getAlive(){
		return alive;
	}
	
	public void setDirection(Image direction){
		heli_Pic=direction;
	}
	
	public int getType(){
		return heliType;
	}
	public Image getLeftPic(){
		return heli_Pic_Left;
	}
	public Image getRightPic(){
		return heli_Pic_Right;
	}
	
	public int getMultiplier(){
		return barMultiplier;
	}

}
