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



public class Guns {

	private int width;
	private int height;
	private int damage;
	private Image gun_Pic;
	private int max_Ammo;
	private int ammo;
	private int type;
	private double fire_Delay;

	public Guns(int weapType, int wide, int high, int dmg, Image pic, int maxAmmo, double delay){
		width =wide;
		height=high;
		damage=dmg;
		gun_Pic=pic;
		max_Ammo=maxAmmo;
		ammo=maxAmmo;
		type=weapType;
		fire_Delay=delay;
	}
	public int getAmmo(){
		return ammo;
	}
	public void reload(){
		ammo=max_Ammo;
	}
	public void shotFired(){
		ammo-=1;
	}
	public Image getPic(){
		return gun_Pic;
	}
	public int getType(){
		return type;
	}
	public int getDMG(){
		return damage;
	}
	public double getDelay(){
		return fire_Delay;
	}


}
