import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class main_Class extends Applet implements Runnable, Serializable, MouseMotionListener, MouseListener, KeyListener
{


	Profile currentProfile;//Current profile user is using

	ArrayList < Image > guns = new ArrayList < Image > ();//All images of the guns
	ArrayList < Guns > weapon = new ArrayList < Guns > ();//Gun objects
	ArrayList < Bullet > bullets = new ArrayList < Bullet > ();//Player bullet objects
	ArrayList < Bullet > heliBullets = new ArrayList < Bullet > ();//Helicopter bullet objects

	ArrayList < Integer > xCounters = new ArrayList < Integer > ();//Array that adds constant to every plaaaaaaaayer bullet x value
	ArrayList < Integer > heliXCounters = new ArrayList < Integer > ();//Array that adds constant to every helicopter bullet x value


	ArrayList < Helicopter > helis = new ArrayList < Helicopter > ();//Helicopter objects
	ArrayList < Image > heliExplosion = new ArrayList < Image > ();//Animation for helicopter death

	ArrayList < Integer > heliBulletDetectX = new ArrayList < Integer > ();//X values for helicopter bullets
	ArrayList < Integer > heliBulletDetectY = new ArrayList < Integer > ();//Y values for helicopter bullets
	ArrayList < Double > heliSlope = new ArrayList < Double > ();//Slopes for helicopter bullets
	ArrayList < Double > helixPos = new ArrayList < Double > ();//Calculates new x value
	ArrayList < Double > heliyPos = new ArrayList < Double > ();//Calculates new y value
	ArrayList < Integer > heliOriginX = new ArrayList < Integer > ();//Bullet's starting position
	ArrayList < Integer > heliOriginY = new ArrayList < Integer > ();//Bullet's starting position

	ArrayList < Integer > bulletDetectX = new ArrayList < Integer > ();//Player bullet's x value
	ArrayList < Integer > bulletDetectY = new ArrayList < Integer > ();//Player bullet's y value
	ArrayList < Double > slope = new ArrayList < Double > ();//Slope for player bullets
	ArrayList < Double > xPos = new ArrayList < Double > ();//Calculates new x value
	ArrayList < Double > yPos = new ArrayList < Double > ();//Calculates new y value
	ArrayList < Integer > originX = new ArrayList < Integer > ();//Starting position for bullet
	ArrayList < Integer > originY = new ArrayList < Integer > ();//Starting position for bullet

	ArrayList < Integer > bullet3_Count = new ArrayList < Integer > ();//Array for the laser weapon counter

	Random r = new Random ();//Random number generator for helicopter accuracy


	Dimension offDimension;//??
	Image offImage;//??
	Graphics offGraphics;//??

	private Image dbImage;//??
	private Graphics dbg;//??

	private int standCount;//Counter for standing animation
	private int runCount;//counter for running animation
	private int jumpCount;//counter for jumping animation
	private int keyPressed;//Holds the key value of the key pressed
	private int charX;//Character x position
	private int charY;//character y position
	private int xpos;//Mouse x position
	private int ypos;//Mouse y position
	private int xpress;//X position of mouse pressed
	private int ypress;//y position of mouse pressed
	private int current_Gun;//Number corresponding with the gun being used
	private int xCounter;//Hold the number that will be added to bullets x value
	private int numBullet;//Number of bullets fired
	private int screenX;//BG position
	private int heliX;//Helicopter's x position
	private int heliY;//Helicopter's y position
	private int explosionX;//Explosion animation's x position
	private int explosionY;//Explosion animation's y position
	private double opp;//Used for arm angle calc
	private double adj;//Used for arm angle calc
	private double hyp;//Used for arm angle calc
	private Double armAngle;//The angle that the arm is being rotated

	private double heliOpp;//Used for slop calc
	private double heliAdj;//Used for slop calc
	private double heliHyp;//Used for slop calc
	private double heliBulletAngle;//Angle of the helicopter's bullet towards player
	private int clickCount;//Number of times mouse is clicked during gameplay
	private int heliCount;//Number of helicopters killed
	private int explodeCount;//Counter for explosion animation
	private int nukeY;//Y position of nuke dropped
	private int bulletCount3;//Counter for laser animation
	private int flashCount;//Coutner for nuke flash
	private int weaponDropY;//x position of the weapon crate
	private int weaponDropX;//y position of the weapon crate
	private int deathCount;//Counter for death animation

	private int gameOverBGCount;//Counter for the game over screen
	private int paraX;//X position of parachuter
	private int paraY;//y position of parachuter

	Rectangle2D rect;//Used for font correction

	private boolean face_Left;//If character is facing left
	private boolean face_Right;//If character is facing right
	private boolean standing;//If character is standing
	private boolean runRight;//if character is running right
	private boolean runLeft;//if character is running left
	private boolean jumpRight;//if character is jumping right
	private boolean jumpLeft;//if character is jumping left
	private boolean jumping;//if character is jumping
	private boolean rise;//if character is rising
	private boolean fall;//if character is falling during jump
	private boolean shoot;//if character is shooting
	private boolean drawBullet;//if bullet should be drawn
	private boolean leftBoundary;//If character is at a boundary
	private boolean rightBoundary;//If character is at a boundary
	private boolean rightWall;//If character is at a boundary
	private boolean leftWall;//If character is at a boundary
	private boolean flyRight;//If heli is flying right
	private boolean flyLeft;//If heli is flying left
	private boolean game_Start;//If gameplay has started
	private boolean main_Menu;//If main menu is active
	private boolean startFlash;//Flash for button
	private boolean instructFlash;//Flash for button
	private boolean scoreFlash;//Flash for button
	private boolean quitFlash;//Flash for button
	private boolean falling;//If character is fallign
	private boolean explosion;//If heli is exploding
	private boolean profileSelected;//If a profile has been selected
	private boolean profile1Flash;//Flash for button
	private boolean profile2Flash;//Flash for button
	private boolean profile3Flash;//Flash for button
	private boolean profile4Flash;//Flash for button
	private boolean profile5Flash;//Flash for button
	private boolean profile6Flash;//Flash for button
	private boolean crashRight;//If helicopter is facing Right when crashing
	private boolean crashLeft;//If helicopter is facing Left when crashing
	private boolean heliShoot;//If heli is shooting
	private boolean phase1;//If player is in phase 1 of map
	private boolean phase2;//If player is in phase 2 of map
	private boolean phase3;//If player is in phase 3 of map
	private boolean transition;//If map is transitioning phases
	private boolean nukeAnimation;//If the nuke is dropping
	private boolean nukeFinished;///once nuke has dropped
	private boolean createProfileScreen;//Create profile page
	private boolean profileCreated;//If profile has been created
	private boolean buttonCreated;//Created button
	private boolean createFlash;//flash for button
	private boolean closeFlash;//flash for button
	private boolean profile1;//Profile is active
	private boolean profile2;//Profile is active
	private boolean profile3;//Profile is active
	private boolean profile4;//Profile is active
	private boolean profile5;//Profile is active
	private boolean profile6;//Profile is active
	private boolean statsScreen;//On statistics screen
	private boolean deleteFlash;//flash for button
	private boolean backFlash;//flash for button
	private boolean continueFlash;//flash for button
	private boolean leftFlash;//flash for button
	private boolean rightFlash;//flash for button
	private boolean instructionsScreen;//flash for button
	private boolean flashOnce;//Initial animation due to animation glitch
	private boolean weaponFall;//If crate is falling
	private boolean weaponFallen;//If crate has fallen
	private boolean weaponOwned;//If the crate weapon is already owned
	private boolean deathAnimation;//If dying animation is active
	private boolean dead;//If animation has finished
	private boolean levelUnlocked;//If Player has unloced the entire level
	private boolean levelAvailable;//Level is available
	private boolean parachuteAnimation;//Animation in the beginning
	private boolean[] fireAvailable=new boolean [10];//Checks if the weapon is able to fire
	private boolean deathScreen;//Game over screen

	private Image background;//Background pic
	private Image pos;//Current character position
	private Image char1_Standing;//Standing position
	private Image char1_StandingLeft;//Facing left standing position
	private Image[] char1_Run_Right = new Image [8];//Running images
	private Image[] char1_Run_Left = new Image [8];//Running images
	private Image[] char1_Jump_Right = new Image [8];//Jumping images
	private Image[] char1_Jump_Left = new Image [8];//Jumping images
	private Image[] heli_Explode = new Image [17];//Explosion images
	private Image weap_gun1;//Image for gun
	private Image weap_gun2;//Image for gun
	private Image weap_gun3;//Image for gun
	private Image weap_gun4;//Image for gun
	private Image weap_gun5;//Image for gun
	private Image weap_gun6;//Image for gun
	private Image weap_gun7;//Image for gun
	private Image weap_gun8;//Image for gun
	private Image weap_gun9;//Image for gun
	private Image weap_gun10;//Image for gun
	private Image bullet_1;//Image for gun bullet
	private Image bullet_2;//Image for gun bullet
	private Image[] bullet_3 = new Image [12];//Image for gun bullet
	private Image bullet_4;//Image for gun bullet
	private Image bullet_5;//Image for gun bullet
	private Image bullet_6;//Image for gun bullet
	private Image bullet_7;//Image for gun bullet
	private Image bullet_8;//Image for gun bullet
	private Image bullet_9;//Image for gun bullet
	private Image[] nukeFlash = new Image [18];//Image for nuke
	private Image bullet_10;//Image for gun bullet

	private Image heli_Small;//small Helicopter image
	private Image heli_Small_Left;//heli facing left
	private Image heli_Big;//Big heli image
	private Image heli_Big_Left;//Big heli facing left
	private Image menu_BG;//Menu background
	private Image menu_Title;//Title button
	private Image menu_List;//Menu list frame
	private Image menu_List_Start;//start buttong
	private Image menu_List_Instruct;//instructions button
	private Image menu_List_Score;//leaderboards button
	private Image menu_List_Quit;//quit button
	private Image heli_Destroyed_Small_Right;//Heli destroyed image
	private Image heli_Destroyed_Small_Left;//Heli destroyed image
	private Image heli_Destroyed_Big_Right;//Heli destroyed image
	private Image heli_Destroyed_Big_Left;//Heli destroyed image

	private Image profileButton;//profile image
	private Image profileButton2;//profile image flashing
	private Image createButton;//button image
	private Image createButton2;//button image flashing
	private Image instructions;//instructions image
	private Image keys1;//instructions image
	private Image keys_up;//instructions image
	private Image keys_left;//instructions image
	private Image keys_right;//instructions image
	private Image mouse;//instructions image
	private Image close;//Close X
	private Image close2;//Close X flashing
	private Image stats;//Statistics box
	private Image profileBadge;//Emblem image
	private Image delete1;//Delete button
	private Image delete2;//Delete button flashing
	private Image continue1;//Start game button
	private Image continue2;//Start game button flashing
	private Image back1;//Return button
	private Image back2;//Return buttong flashing
	private Image[] emblems = new Image [15];//All emblems pics
	private Image arrowLeft;//Arrow for emblem change
	private Image arrowRight;//Arrow for emblem change
	private Image arrowLeft2;//Arrow for emblem change
	private Image arrowRight2;//Arrow for emblem change
	private Image emblemBack;//Arrow for emblem change

	private Image weap2Box;//Weapon crates
	private Image weap3Box;//Weapon crates
	private Image weap4Box;//Weapon crates
	private Image weap5Box;//Weapon crates
	private Image weap6Box;//Weapon crates
	private Image weap7Box;//Weapon crates
	private Image weap8Box;//Weapon crates
	private Image weap9Box;//Weapon crates
	private Image weap10Box;//Weapon crates
	private Image dropBox;//Current crate falling

	private Image healthBar;//Character health bar
	private Image healthBarEmblem;//Emblem for the health bar
	private Image[] deathImages = new Image [7];//Character dying images

	private Image infinite;//Image for infinite icon

	private Image hudBar;//Bar showing ammo, heli count

	private Image[] gameOver_BG= new Image [6];//BG for the game over screen
	private Image[] youDied= new Image [5];//Text saying you are dead
	private Image gameOverText;//""

	private Image parachuter;//Image for the parachuting character

	private Image arrowFWD;//Arrow image
	Guns dropWeapon;//Object for the crate dropping weapon
	Guns weaponPickup;//Object for the weapon dropped

	private String currentUser;//name of current user

	Font font;//fonts
	Font smallF;
	Font bigF;
	Font normal;

	Guns current_Weapon;//Current weapon used
	Bullet bullet1;//Bullet for current weapon

	private ProfileSystem profiles;//Creates the profile system

	private TextField pName;//textfield for user name

	File f;//creates file

	ObjectInputStream input;//File IO

	private int emblemCounter;//counter that goes through emblem list
	private Image heliBulletPic;//Pic of the heli bullet

	FontMetrics fm;

	private AudioClip sniper;//Sound effect
	private AudioClip rocket;//Sound effect
	private AudioClip pistol;//Sound effect
	private AudioClip lazer;//Sound effect
	private AudioClip chaingun;//Sound effect
	private AudioClip ammo;//Sound effect
	private AudioClip explosionSound;//Sound effect
	private AudioClip mainTheme;//Sound effect
	private AudioClip inGame;//Sound effect
	private AudioClip nuke1;//Sound effect
	private AudioClip nuke2;//Sound effect

	int counter;
	int expCounter;//Counter for score
	int helisDestroyed;//Number of helis destroyed
	int bulletsFired;//Number of bullets fired

	private boolean nukeSound;//If nuke is dropped (sound)
	private boolean scoresScreen;//Leaderboards page
	private boolean scoresInput;

	ArrayList <Integer> scores = new ArrayList <Integer>();//Scores for the profiles

	Color fullHealth = new Color(5,113,0);//Green colour for full health

	long now=0;//Delay timer
	long[] lastFire=new long[10];//Delay timer
	long[] fireReady= new long[10];//Delay timer

	public void init ()
	{


		profiles = new ProfileSystem ();
		f = new File ("profiles.txt");//Name if file for file IO

		fm = null;

		rect = null;


		char1_Standing = getImage (getDocumentBase (), "char_1_Standing.gif");//Position image


		char1_StandingLeft = getImage (getDocumentBase (), "char_1_StandingLeft.gif");//Position image


		char1_Run_Right [0] = getImage (getDocumentBase (), "run_Right1.png");//Running image
		char1_Run_Right [1] = getImage (getDocumentBase (), "run_Right2.png");//Running image
		char1_Run_Right [2] = getImage (getDocumentBase (), "run_Right3.png");//Running image
		char1_Run_Right [3] = getImage (getDocumentBase (), "run_Right4.png");//Running image
		char1_Run_Right [4] = getImage (getDocumentBase (), "run_Right5.png");//Running image
		char1_Run_Right [5] = getImage (getDocumentBase (), "run_Right6.png");//Running image
		char1_Run_Right [6] = getImage (getDocumentBase (), "run_Right7.png");//Running image
		char1_Run_Right [7] = getImage (getDocumentBase (), "run_Right8.png");//Running image

		char1_Run_Left [0] = getImage (getDocumentBase (), "run_Left1.png");//Running image
		char1_Run_Left [1] = getImage (getDocumentBase (), "run_Left2.png");//Running image
		char1_Run_Left [2] = getImage (getDocumentBase (), "run_Left3.png");//Running image
		char1_Run_Left [3] = getImage (getDocumentBase (), "run_Left4.png");//Running image
		char1_Run_Left [4] = getImage (getDocumentBase (), "run_Left5.png");//Running image
		char1_Run_Left [5] = getImage (getDocumentBase (), "run_Left6.png");//Running image
		char1_Run_Left [6] = getImage (getDocumentBase (), "run_Left7.png");//Running image
		char1_Run_Left [7] = getImage (getDocumentBase (), "run_Left8.png");//Running image

		char1_Jump_Right [0] = getImage (getDocumentBase (), "jump_Right1.png");//Jumping image
		char1_Jump_Right [1] = getImage (getDocumentBase (), "jump_Right2.png");//Jumping image
		char1_Jump_Right [2] = getImage (getDocumentBase (), "jump_Right3.png");//Jumping image
		char1_Jump_Right [3] = getImage (getDocumentBase (), "jump_Right4.png");//Jumping image
		char1_Jump_Right [4] = getImage (getDocumentBase (), "jump_Right4.png");//Jumping image
		char1_Jump_Right [5] = getImage (getDocumentBase (), "jump_Right3.png");//Jumping image
		char1_Jump_Right [6] = getImage (getDocumentBase (), "jump_Right2.png");//Jumping image
		char1_Jump_Right [7] = getImage (getDocumentBase (), "jump_Right1.png");//Jumping image

		char1_Jump_Left [0] = getImage (getDocumentBase (), "jump_Left1.png");//Jumping image
		char1_Jump_Left [1] = getImage (getDocumentBase (), "jump_Left2.png");//Jumping image
		char1_Jump_Left [2] = getImage (getDocumentBase (), "jump_Left3.png");//Jumping image
		char1_Jump_Left [3] = getImage (getDocumentBase (), "jump_Left4.png");//Jumping image
		char1_Jump_Left [4] = getImage (getDocumentBase (), "jump_Left4.png");//Jumping image
		char1_Jump_Left [5] = getImage (getDocumentBase (), "jump_Left3.png");//Jumping image
		char1_Jump_Left [6] = getImage (getDocumentBase (), "jump_Left2.png");//Jumping image
		char1_Jump_Left [7] = getImage (getDocumentBase (), "jump_Left1.png");//Jumping image

		deathImages[0]= getImage (getDocumentBase (), "death1.png");//Dying image
		deathImages[1]= getImage (getDocumentBase (), "death2.png");//Dying image
		deathImages[2]= getImage (getDocumentBase (), "death3.png");//Dying image
		deathImages[3]= getImage (getDocumentBase (), "death4.png");//Dying image
		deathImages[4]= getImage (getDocumentBase (), "death5.png");//Dying image
		deathImages[5]= getImage (getDocumentBase (), "death6.png");//Dying image
		deathImages[6]= getImage (getDocumentBase (), "death7.png");//Dying image

		weap_gun1 = getImage (getDocumentBase (), "weap_Pistol.png");//Gun images
		weap_gun2 = getImage (getDocumentBase (), "weap_gun2.png");//Gun images
		weap_gun3 = getImage (getDocumentBase (), "weap_gun3.png");//Gun images
		weap_gun4 = getImage (getDocumentBase (), "weap_gun4.png");//Gun images
		weap_gun5 = getImage (getDocumentBase (), "weap_gun5.png");//Gun images
		weap_gun6 = getImage (getDocumentBase (), "weap_gun6.png");//Gun images
		weap_gun7 = getImage (getDocumentBase (), "weap_gun7.png");//Gun images
		weap_gun8 = getImage (getDocumentBase (), "weap_gun8.png");//Gun images
		weap_gun9 = getImage (getDocumentBase (), "weap_gun9.png");//Gun images
		weap_gun10 = getImage (getDocumentBase (), "weap_gun10.png");//Gun images



		heli_Explode [0] = getImage (getDocumentBase (), "explode1.png");//Explosion images
		heli_Explode [1] = getImage (getDocumentBase (), "explode2.png");//Explosion images
		heli_Explode [2] = getImage (getDocumentBase (), "explode3.png");//Explosion images
		heli_Explode [3] = getImage (getDocumentBase (), "explode4.png");//Explosion images
		heli_Explode [4] = getImage (getDocumentBase (), "explode5.png");//Explosion images
		heli_Explode [5] = getImage (getDocumentBase (), "explode6.png");//Explosion images
		heli_Explode [6] = getImage (getDocumentBase (), "explode7.png");//Explosion images
		heli_Explode [7] = getImage (getDocumentBase (), "explode8.png");//Explosion images
		heli_Explode [8] = getImage (getDocumentBase (), "explode9.png");//Explosion images
		heli_Explode [9] = getImage (getDocumentBase (), "explode10.png");//Explosion images
		heli_Explode [10] = getImage (getDocumentBase (), "explode11.png");//Explosion images
		heli_Explode [11] = getImage (getDocumentBase (), "explode12.png");//Explosion images
		heli_Explode [12] = getImage (getDocumentBase (), "explode13.png");//Explosion images
		heli_Explode [13] = getImage (getDocumentBase (), "explode14.png");//Explosion images
		heli_Explode [14] = getImage (getDocumentBase (), "explode15.png");//Explosion images
		heli_Explode [15] = getImage (getDocumentBase (), "explode16.png");//Explosion images
		heli_Explode [16] = getImage (getDocumentBase (), "explode17.png");//Explosion images

		heliBulletPic = getImage (getDocumentBase (), "heli_Bullet.png");//Heli bullet
		bullet_1 = getImage (getDocumentBase (), "bullet1.png");//Weapon bullets
		bullet_2 = getImage (getDocumentBase (), "bullet1.png");//Weapon bullets
		bullet_3 [0] = getImage (getDocumentBase (), "bullet_3_1.png");//Weapon bullets
		bullet_3 [1] = getImage (getDocumentBase (), "bullet_3_2.png");//Weapon bullets
		bullet_3 [2] = getImage (getDocumentBase (), "bullet_3_3.png");//Weapon bullets
		bullet_3 [3] = getImage (getDocumentBase (), "bullet_3_4.png");//Weapon bullets
		bullet_3 [4] = getImage (getDocumentBase (), "bullet_3_5.png");//Weapon bullets
		bullet_3 [5] = getImage (getDocumentBase (), "bullet_3_6.png");//Weapon bullets
		bullet_3 [6] = getImage (getDocumentBase (), "bullet_3_7.png");//Weapon bullets
		bullet_3 [7] = getImage (getDocumentBase (), "bullet_3_8.png");//Weapon bullets
		bullet_3 [8] = getImage (getDocumentBase (), "bullet_3_9.png");//Weapon bullets
		bullet_3 [9] = getImage (getDocumentBase (), "bullet_3_10.png");//Weapon bullets
		bullet_3 [10] = getImage (getDocumentBase (), "bullet_3_11.png");//Weapon bullets
		bullet_3 [11] = getImage (getDocumentBase (), "bullet_3_12.png");//Weapon bullets
		bullet_4 = getImage (getDocumentBase (), "bullet1.png");//Weapon bullets
		bullet_5 = getImage (getDocumentBase (), "bullet_5.png");//Weapon bullets
		bullet_6 = getImage (getDocumentBase (), "bullet_6.png");//Weapon bullets
		bullet_7 = getImage (getDocumentBase (), "bullet1.png");//Weapon bullets
		bullet_8 = getImage (getDocumentBase (), "bullet_8.png");//Weapon bullets
		bullet_9 = getImage (getDocumentBase (), "bullet_9.png");//Weapon bullets
		nukeFlash [0] = getImage (getDocumentBase (), "flash1.png");//Nuke Images
		nukeFlash [1] = getImage (getDocumentBase (), "flash2.png");//Nuke Images
		nukeFlash [2] = getImage (getDocumentBase (), "flash3.png");//Nuke Images
		nukeFlash [3] = getImage (getDocumentBase (), "flash4.png");//Nuke Images
		nukeFlash [4] = getImage (getDocumentBase (), "flash5.png");//Nuke Images
		nukeFlash [5] = getImage (getDocumentBase (), "flash6.png");//Nuke Images
		nukeFlash [6] = getImage (getDocumentBase (), "flash7.png");//Nuke Images
		nukeFlash [7] = getImage (getDocumentBase (), "flash8.png");//Nuke Images
		nukeFlash [8] = getImage (getDocumentBase (), "flash9.png");//Nuke Images
		nukeFlash [9] = getImage (getDocumentBase (), "flash10.png");//Nuke Images
		nukeFlash [10] = getImage (getDocumentBase (), "flash11.png");//Nuke Images
		nukeFlash [11] = getImage (getDocumentBase (), "flash12.png");//Nuke Images
		nukeFlash [12] = getImage (getDocumentBase (), "flash13.png");//Nuke Images
		nukeFlash [13] = getImage (getDocumentBase (), "flash14.png");//Nuke Images
		nukeFlash [14] = getImage (getDocumentBase (), "flash15.png");//Nuke Images
		nukeFlash [15] = getImage (getDocumentBase (), "flash16.png");//Nuke Images
		nukeFlash [16] = getImage (getDocumentBase (), "flash17.png");//Nuke Images
		nukeFlash [17] = getImage (getDocumentBase (), "flash18.png");//Nuke Images
		bullet_10 = getImage (getDocumentBase (), "bullet_10.png");//Weapon bullets

		background = getImage (getDocumentBase (), "egypt_map.png");//Map BG

		heli_Small = getImage (getDocumentBase (), "heli_Small.png");//small heli
		getImage (getDocumentBase (), "heli_Small.png");//Small heli
		heli_Small_Left = getImage (getDocumentBase (), "heli_Small_Left.png");//Small heli
		heli_Big = getImage (getDocumentBase (), "heli_Big.png");//Big heli
		getImage (getDocumentBase (), "heli_Big.png");//Big heli
		heli_Big_Left = getImage (getDocumentBase (), "heli_Big_Left.png");//Big heli
		menu_BG = getImage (getDocumentBase (), "menu_BG.png");//Main menu BG
		menu_Title = getImage (getDocumentBase (), "menu_Title.png");//image for frame
		menu_List = getImage (getDocumentBase (), "menu_List.png");//Menu list image
		menu_List_Start = getImage (getDocumentBase (), "menu_Start.gif");//button image
		menu_List_Instruct = getImage (getDocumentBase (), "menu_Instruct.gif");//button image
		menu_List_Score = getImage (getDocumentBase (), "menu_Score.gif");//button image
		menu_List_Quit = getImage (getDocumentBase (), "menu_Quit.gif");//button image

		profileButton = getImage (getDocumentBase (), "profile_Button.gif");//button image
		profileButton2 = getImage (getDocumentBase (), "profile_Button2.png");//button image

		createButton = getImage (getDocumentBase (), "create_Button.png");//button image
		createButton2 = getImage (getDocumentBase (), "create_Button2.gif");//button image


		heli_Destroyed_Small_Right = getImage (getDocumentBase (), "heli_Destroyed_Right.gif");//Destroyed heli pic
		heli_Destroyed_Small_Left = getImage (getDocumentBase (), "heli_Destroyed_Left.gif");//Destroyed heli pic
		heli_Destroyed_Big_Right = getImage (getDocumentBase (), "heli_Destroyed_Big_Right.gif");//Destroyed heli pic
		heli_Destroyed_Big_Left = getImage (getDocumentBase (), "heli_Destroyed_Big_Left.gif");//Destroyed heli pic

		instructions = getImage (getDocumentBase (), "instructions.png");//Instructions page image

		keys1 = getImage (getDocumentBase (), "keys.png");//Instructions page image
		keys_right = getImage (getDocumentBase (), "keys_right.png");//Instructions page image
		keys_left = getImage (getDocumentBase (), "keys_left.png");//Instructions page image
		keys_up = getImage (getDocumentBase (), "keys_up.png");//Instructions page image

		mouse = getImage (getDocumentBase (), "Mouse.png");//Instructions page image

		close = getImage (getDocumentBase (), "close.png");//Instructions page image
		close2 = getImage (getDocumentBase (), "close2.png");//Instructions page image

		stats = getImage (getDocumentBase (), "stats.png");//Statistics page

		profileBadge = getImage (getDocumentBase (), "profileBadge.gif");//Emblem of user

		delete1 = getImage (getDocumentBase (), "deleteButton1.png");//Button image
		delete2 = getImage (getDocumentBase (), "deleteButton2.gif");//Button image
		continue1 = getImage (getDocumentBase (), "continueButton1.png");//Button image
		continue2 = getImage (getDocumentBase (), "continueButton2.gif");//Button image
		back1 = getImage (getDocumentBase (), "backButton1.png");//Button image
		back2 = getImage (getDocumentBase (), "backButton2.gif");//Button image

		emblems [0] = getImage (getDocumentBase (), "emblem1.png");//Emblem images
		emblems [1] = getImage (getDocumentBase (), "emblem2.png");//Emblem images
		emblems [2] = getImage (getDocumentBase (), "emblem3.png");//Emblem images
		emblems [3] = getImage (getDocumentBase (), "emblem4.png");//Emblem images
		emblems [4] = getImage (getDocumentBase (), "emblem5.png");//Emblem images
		emblems [5] = getImage (getDocumentBase (), "emblem6.png");//Emblem images
		emblems [6] = getImage (getDocumentBase (), "emblem7.png");//Emblem images
		emblems [7] = getImage (getDocumentBase (), "emblem8.png");//Emblem images
		emblems [8] = getImage (getDocumentBase (), "emblem9.png");//Emblem images
		emblems [9] = getImage (getDocumentBase (), "emblem10.png");//Emblem images
		emblems [10] = getImage (getDocumentBase (), "emblem11.png");//Emblem images
		emblems [11] = getImage (getDocumentBase (), "emblem12.png");//Emblem images
		emblems [12] = getImage (getDocumentBase (), "emblem13.png");//Emblem images
		emblems [13] = getImage (getDocumentBase (), "emblem14.png");//Emblem images
		emblems [14] = getImage (getDocumentBase (), "emblem15.png");//Emblem images

		arrowLeft = getImage (getDocumentBase (), "arrowLeft.png");//Arrow for emblem select
		arrowRight = getImage (getDocumentBase (), "arrowRight.png");//Arrow for emblem select
		arrowLeft2 = getImage (getDocumentBase (), "arrowLeft2.png");//Arrow for emblem select
		arrowRight2 = getImage (getDocumentBase (), "arrowRight2.png");//Arrow for emblem select
		emblemBack = getImage (getDocumentBase (), "emblemBack.png");//Arrow for emblem select

		weap2Box = getImage (getDocumentBase (), "weap2Box.png");//weapon crate
		weap3Box = getImage (getDocumentBase (), "weap3Box.png");//weapon crate
		weap4Box = getImage (getDocumentBase (), "weap4Box.png");//weapon crate
		weap5Box = getImage (getDocumentBase (), "weap5Box.png");//weapon crate
		weap6Box = getImage (getDocumentBase (), "weap6Box.png");//weapon crate
		weap7Box = getImage (getDocumentBase (), "weap7Box.png");//weapon crate
		weap8Box = getImage (getDocumentBase (), "weap8Box.png");//weapon crate
		weap9Box = getImage (getDocumentBase (), "weap9Box.png");//weapon crate
		weap10Box = getImage (getDocumentBase (), "weap10Box.png");//weapon crate

		healthBar = getImage (getDocumentBase (), "healthBar.png");//Health bar
		healthBarEmblem= getImage (getDocumentBase (), "healthBarEmblem.png");//Emblem for health bar

		hudBar= getImage (getDocumentBase (), "hudBar.png");//HUD bar
		infinite= getImage (getDocumentBase (), "infinite.png");//infinite sign

		gameOver_BG[0]=getImage(getDocumentBase (), "gameOver_BG2.png");//Game over animation
		gameOver_BG[1]=getImage(getDocumentBase (), "gameOver_BG3.png");//Game over animation
		gameOver_BG[2]=getImage(getDocumentBase (), "gameOver_BG4.png");//Game over animation
		gameOver_BG[3]=getImage(getDocumentBase (), "gameOver_BG5.png");//Game over animation
		gameOver_BG[4]=getImage(getDocumentBase (), "gameOver_BG6.png");//Game over animation
		gameOver_BG[5]=getImage(getDocumentBase (), "gameOver_BG7.png");//Game over animation

		youDied[0]=getImage(getDocumentBase (), "youDied1.png");//Game over animation
		youDied[1]=getImage(getDocumentBase (), "youDied2.png");//Game over animation
		youDied[2]=getImage(getDocumentBase (), "youDied3.png");//Game over animation
		youDied[3]=getImage(getDocumentBase (), "youDied4.png");//Game over animation
		youDied[4]=getImage(getDocumentBase (), "youDied5.png");//Game over animation

		gameOverText=getImage(getDocumentBase (), "gameOver.gif");//Game over animation

		parachuter=getImage(getDocumentBase (), "parachuter.png");//Image for parachuter

		arrowFWD=getImage(getDocumentBase (), "arrowFWD.gif");//Arrow point FWD


		guns.add (weap_gun1);//adds gun images to array
		guns.add (weap_gun2);//adds gun images to array
		guns.add (weap_gun3);//adds gun images to array
		guns.add (weap_gun4);//adds gun images to array
		guns.add (weap_gun5);//adds gun images to array
		guns.add (weap_gun6);//adds gun images to array
		guns.add (weap_gun7);//adds gun images to array
		guns.add (weap_gun8);//adds gun images to array
		guns.add (weap_gun9);//adds gun images to array
		guns.add (weap_gun10);//adds gun images to array

		//Sets all created booleans to false except for the ones that relate to the main menu (because it starts when program runs
		standing = true;
		runRight = false;
		runLeft = false;
		jumpRight = false;
		jumpLeft = false;
		jumping = false;
		rise = false;
		fall = false;
		face_Right = true;
		face_Left = false;
		shoot = false;
		drawBullet = false;
		//Sets all created booleans to false except for the ones that relate to the main menu (because it starts when program runs
		leftBoundary = false;
		rightBoundary = false;
		rightWall = false;
		leftWall = false;
		flyRight = false;
		flyLeft = false;
		game_Start = false;
		main_Menu = true;
		startFlash = false;
		//Sets all created booleans to false except for the ones that relate to the main menu (because it starts when program runs
		instructFlash = false;
		scoreFlash = false;
		quitFlash = false;
		falling = false;
		explosion = true;
		profileSelected = false;
		//Sets all created booleans to false except for the ones that relate to the main menu (because it starts when program runs
		profile1Flash = false;
		profile2Flash = false;
		profile3Flash = false;
		profile4Flash = false;
		profile5Flash = false;
		profile6Flash = false;
		crashRight = false;
		crashLeft = false;
		heliShoot = false;
		phase1 = false;
		phase2 = false;
		phase3 = false;
		//Sets all created booleans to false except for the ones that relate to the main menu (because it starts when program runs
		transition = false;
		nukeAnimation = false;
		nukeFinished = false;
		createProfileScreen = false;
		profileCreated = false;
		buttonCreated = false;
		createFlash = false;
		closeFlash = false;
		profile1 = false;
		profile2 = false;
		profile3 = false;
		profile4 = false;
		profile5 = false;
		profile6 = false;
		//Sets all created booleans to false except for the ones that relate to the main menu (because it starts when program runs
		statsScreen = false;
		backFlash = false;
		deleteFlash = false;
		continueFlash = false;
		leftFlash = false;
		rightFlash = false;
		instructionsScreen = false;
		flashOnce = true;
		//Sets all created booleans to false except for the ones that relate to the main menu (because it starts when program runs
		weaponFall=false;
		weaponFallen=false;
		weaponOwned=false;
		deathAnimation=false;
		dead=false;
		parachuteAnimation=false;
		//Sets all created booleans to false except for the ones that relate to the main menu (because it starts when program runs
		fireAvailable[0] = true;
		fireAvailable [1]= true;
		fireAvailable [2]= true;
		fireAvailable [3]= true;
		fireAvailable [4]= true;
		fireAvailable [5]= true;
		fireAvailable [6]= true;
		fireAvailable [7]= true;
		fireAvailable [8]= true;
		fireAvailable [9]= true;
		//Sets all created booleans to false except for the ones that relate to the main menu (because it starts when program runs
		levelUnlocked=false;
		levelAvailable=false;

		deathScreen=false;

		//Initializes all integer values to their beginning value 
		standCount = 0;
		runCount = 0;
		jumpCount = 0;
		keyPressed = 0;
		charX = -50;
		charY = 515;
		armAngle = 0.0;
		opp = 0;
		adj = 0;
		hyp = 0;
		current_Gun = 0;
		xCounter = 0;
		numBullet = 0;
		screenX = 0;
		heliX = -300;
		heliY = 100;
		heliCount = 0;
		explosionX = 0;
		explosionY = 0;
		nukeY = -100;
		bulletCount3 = 0;

		//Initializes all integer values to their beginning value 
		clickCount = 0;

		heliOpp = 0;
		heliAdj = 0;
		heliHyp = 0;
		//Initializes all integer values to their beginning value 
		explodeCount = 0;
		//Initializes all integer values to their beginning value 
		currentUser = "";
		//Initializes all integer values to their beginning value 
		emblemCounter = 0;
		deathCount=0;
		//Initializes all integer values to their beginning value 
		gameOverBGCount=0;
		paraX=500;
		paraY=-200;

		//Sound effect initialization
		rocket = getAudioClip (getDocumentBase (), "Rocket.wav"); //Sound effect initialization
		pistol = getAudioClip (getDocumentBase (), "GunShot.wav");//Sound effect initialization
		sniper = getAudioClip (getDocumentBase (), "Sniper.wav"); //Sound effect initialization
		lazer = getAudioClip (getDocumentBase (), "Lazer.wav");//Sound effect initialization
		chaingun = getAudioClip (getDocumentBase (), "Chaingun.wav");//Sound effect initialization
		ammo = getAudioClip (getDocumentBase (), "Ammo.wav"); //Unused
		explosionSound = getAudioClip (getDocumentBase (), "Explosion.wav");//Sound effect initialization
		mainTheme = getAudioClip (getDocumentBase (), "MenuTheme.wav");//Sound effect initialization
		inGame = getAudioClip (getDocumentBase (), "InGame.wav");//Sound effect initialization
		nuke1 = getAudioClip (getDocumentBase (), "nuke1.wav");//Sound effect initialization
		nuke2 = getAudioClip (getDocumentBase (), "nuke2.wav");//Sound effect initialization


		//Initializes stat coutners to 0
		counter = 0;   
		expCounter = 0;
		helisDestroyed = 0;
		bulletsFired = 0;

		//Does not play nuke
		nukeSound = false;
		//Leaderboards page set to false
		scoresScreen = false;
		scoresInput = false;

		//Fonts initialized
		font = new Font ("Weltron Urban", Font.PLAIN, 85);
		smallF = new Font ("Weltron Urban", Font.PLAIN, 30);
		bigF = new Font ("Weltron Urban", Font.PLAIN, 45);
		normal = new Font ("Calibri", Font.PLAIN, 16);
		//Creates first helicopter
		helis.add (new Helicopter (1, heli_Small,heli_Small_Left, 10, 20, heli_Destroyed_Small_Right, heli_Destroyed_Small_Left,122,65));

		//Sets text field
		pName = new TextField ("", 75);
		pName.setBounds (480, 310, 120, 20);

		//Adds action listeners
		addKeyListener (this);
		addMouseListener (this);
		addMouseMotionListener (this);
	}


	public void start ()
	{
		//Screen size
		resize (1000, 650);

		// define a new thread
		Thread th = new Thread (this);
		// start this thread
		th.start ();

		//Initializes all the guns and their stats
		Guns gun1 = new Guns (1, 89, 50, 1, guns.get (0), (int)Double.POSITIVE_INFINITY,0);
		Guns gun2 = new Guns (2, 89, 50, 20, guns.get (1), 30,5);
		Guns gun3 = new Guns (3, 89, 50, 1000, guns.get (2), 3,10);
		Guns gun4 = new Guns (4, 89, 50, 1, guns.get (3), 250,0.1);
		Guns gun5 = new Guns (5, 89, 50, 50, guns.get (4), 10,4);
		Guns gun6 = new Guns (6, 89, 50, 20, guns.get (5), 20,3);
		Guns gun7 = new Guns (7, 89, 50, 5, guns.get (6), 100,0.3);
		Guns gun8 = new Guns (8, 89, 50, 100, guns.get (7), 5,7);
		Guns gun9 = new Guns (9, 89, 50, 9999, guns.get (8), 1,20);
		Guns gun10 = new Guns (10, 89, 50, 40, guns.get (9), 15,4);

		//Sets the timer variable to the current system time
		now = System.currentTimeMillis ();

		//Adds pistol to the users arsenal
		weapon.add (gun1);

		//Sets the starting weapon as the pistol
		current_Weapon = weapon.get (0);

		inGame.play();
	}


	public void update (Graphics g)
	{

		// initialize buffer
		if (dbImage == null)
		{
			dbImage = createImage (this.getSize ().width, this.getSize ().height);
			dbg = dbImage.getGraphics ();
		}


		// clear screen in background
		dbg.setColor (getBackground ());
		dbg.fillRect (0, 0, this.getSize ().width, this.getSize ().height);

		// draw elements in background
		dbg.setColor (getForeground ());
		paint (dbg);

		// draw image on the screen
		g.drawImage (dbImage, 0, 0, this);

	}


	public void run ()
	{
		// Sets thread priority low
		Thread.currentThread ().setPriority (Thread.MIN_PRIORITY);
		while (true)
		{
			//If the menu is true
			if (main_Menu == true)
			{
				//Checks if the mouse is within start button the boundary
				if (xpos >= 340 && xpos <= 615 && ypos >= 210 && ypos <= 270)
				{
					//Flashes the start button -> all other flashes are false
					startFlash = true;
					instructFlash = false;
					quitFlash = false;
					scoreFlash = false;
				}
				else if (xpos >= 340 && xpos <= 615 && ypos >= 275 && ypos <= 335)//Checks if the mouse is within start button the boundary
				{
					//Flashes the instructions button -> all other flashes are false
					instructFlash = true;
					startFlash = false;
					quitFlash = false;
					scoreFlash = false;
				}
				else if (xpos >= 340 && xpos <= 615 && ypos >= 340 && ypos <= 400)//Checks if the mouse is within start button the boundary
				{
					//Flashes the leaderboards button -> all other flashes are false
					scoreFlash = true;
					quitFlash = false;
					instructFlash = false;
					startFlash = false;
				}
				else if (xpos >= 340 && xpos <= 615 && ypos >= 405 && ypos <= 465)//Checks if the mouse is within start button the boundary
				{
					//Flashes the quit button -> all other flashes are false
					quitFlash = true;
					instructFlash = false;
					startFlash = false;
					scoreFlash = false;
				}
				else//If mouse position is not within one of the button boundaries
				{
					//All flashes are set to false
					quitFlash = false;
					instructFlash = false;
					startFlash = false;
					scoreFlash = false;
				}

				//If mouse has been pressed in the start boundary
				if (xpress>=340&&xpress<=615&&ypress>=210&&ypress<=270){
					profileSelected = true;//Goes to the profiles page
					main_Menu=false;
				}
				//If mouse has been pressed in the leaderboards boundary
				else if (xpress >= 340 &&xpress<=615&&ypress>=340&&ypress<=400) {
					main_Menu = false;//displays leaderboards
					scoresScreen = true;
					scoresInput = true;
				}
				//If mouse has been pressed in the instructions boundary
				else if (xpress>=340&&xpress<=615&&ypress>=275&&ypress<=335){
					instructionsScreen = true;	//Displays instruction screen
					main_Menu = false;
				}
				//If mouse has been pressed in the quit boundary
				else if (xpress>=340&&xpress<=615&&ypress>=405&&ypress<=465){
					System.exit(0);//Closes game
				}

				//resets x and y values of the mouse clicked
				xpress = 0;
				ypress = 0;

			}
			if (scoresScreen == true) {//If leaderboards are being displayed
				if (scoresInput == true) {
					//Writes the scores to the file
					try
					{
						if (f.exists ()) //Check if file exists
						{
							input = new ObjectInputStream (
									new FileInputStream ("profiles.txt")); //File to use when inputing the objects
							profiles = (ProfileSystem) input.readObject (); //Save the file info to this array
							input.close (); //close the file
							////System.out.println(profiles.getSize());
						}
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace ();
					}

					//Calls the head node of the linked list into a variable
					Node nod = profiles.listProfiles();
					if (scores.size() == 0) {//Checks if there are any scores avaiable 
						for (int i = 0; i < profiles.getSize(); i++) {
							scores.add(nod.getProfile().getHighScore());//traverses through nodes
							nod = nod.getNext();
						}

					}
					Comparator comparator = Collections.reverseOrder();
					Collections.sort(scores, comparator);
					scoresInput = false;
				}
				if (xpos >= 12 && xpos <= 12+280 && ypos >= 573 && ypos <= 573+61) {//If mouse is in the back button boundary
					backFlash = true;//displays the back button flash
				}
				else {
					backFlash = false;
				}

				if (xpress >= 12 && xpress <= 12+280 && ypress >= 573 && ypress <= 573+61) {//If back button is clicked
					scoresScreen = false;//returns to main menu
					main_Menu = true;
				}
				//Resets mouse clicked variables
				xpress = 0;
				ypress = 0;
			}
			if (instructionsScreen == true) {//If the instructions screen is active
				if (xpos >= 12 && xpos <= 12+280 && ypos >= 573 && ypos <= 573+61) {
					backFlash = true;//If mouse is within boundaries
				}
				else {
					backFlash = false;
				}

				if (xpress >= 12 && xpress <= 12+280 && ypress >= 573 && ypress <= 573+61) {//If back button has been clicked
					instructionsScreen = false;//returns to main menu
					main_Menu = true;
				}
				//Resets mouse clicked variables
				xpress = 0;
				ypress = 0;
			}

			if (profileSelected == true)//If profile create page has been selected
			{
				try
				{//Writes profiles to the file
					if (f.exists ()) //Check if file exists
					{
						input = new ObjectInputStream (
								new FileInputStream ("profiles.txt")); //File to use when inputing the objects
						profiles = (ProfileSystem) input.readObject (); //Save the file info to this array
						input.close (); //close the file

					}
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace ();
				}
				//Sets all current profiles to flase
				profile1 = false;
				profile2 = false;
				profile3 = false;
				profile4 = false;
				profile5 = false;
				profile6 = false;


				//if mouse is within boundary
				if (xpos >= 50 && xpos <= (50 + 275) && ypos >= 175 && ypos <= 375)
				{
					//flashes the profile
					profile1Flash = true;
					profile2Flash = false;
					profile3Flash = false;
					profile4Flash = false;
					profile5Flash = false;
					profile6Flash = false;
				}
				//same as above
				else if (xpos >= 362 && xpos <= (362 + 275) && ypos >= 175 && ypos <= 375)
				{
					profile1Flash = false;
					profile2Flash = true;
					profile3Flash = false;
					profile4Flash = false;
					profile5Flash = false;
					profile6Flash = false;
				}
				//same as above
				else if (xpos >= 675 && xpos <= (675 + 275) && ypos >= 175 && ypos <= 375)
				{
					profile1Flash = false;
					profile2Flash = false;
					profile3Flash = true;
					profile4Flash = false;
					profile5Flash = false;
					profile6Flash = false;
				}
				//same as above
				else if (xpos >= 50 && xpos <= (50 + 275) && ypos >= 400 && ypos <= 600)
				{
					profile1Flash = false;
					profile2Flash = false;
					profile3Flash = false;
					profile4Flash = true;
					profile5Flash = false;
					profile6Flash = false;
				}
				//same as above
				else if (xpos >= 362 && xpos <= (362 + 275) && ypos >= 400 && ypos <= 600)
				{
					profile1Flash = false;
					profile2Flash = false;
					profile3Flash = false;
					profile4Flash = false;
					profile5Flash = true;
					profile6Flash = false;
				}
				//same as above
				else if (xpos >= 675 && xpos <= (675 + 275) && ypos >= 400 && ypos <= 600)
				{
					profile1Flash = false;
					profile2Flash = false;
					profile3Flash = false;
					profile4Flash = false;
					profile5Flash = false;
					profile6Flash = true;
				}
				//If mouse is not within any boundary
				else
				{
					profile1Flash = false;
					profile2Flash = false;
					profile3Flash = false;
					profile4Flash = false;
					profile5Flash = false;
					profile6Flash = false;

				}

			}

			if (profileSelected == true)
			{
				//If profile is selected
				try
				{
					if (f.exists ()) //Check if file exists
					{
						input = new ObjectInputStream (
								new FileInputStream ("profiles.txt")); //File to use when inputing the objects
						profiles = (ProfileSystem) input.readObject (); //Save the file info to this array
						input.close (); //close the file

					}
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace ();
				}
				//Resets variable counters
				helisDestroyed = 0;
				bulletsFired = 0;
				//Sets all profiles to false
				profile1 = false;
				profile2 = false;
				profile3 = false;
				profile4 = false;
				profile5 = false;
				profile6 = false;
				//Same as previos block
				if (xpos >= 50 && xpos <= (50 + 275) && ypos >= 175 && ypos <= 375)
				{
					profile1Flash = true;
					profile2Flash = false;
					profile3Flash = false;
					profile4Flash = false;
					profile5Flash = false;
					profile6Flash = false;
				}
				//Same as previos block
				else if (xpos >= 362 && xpos <= (362 + 275) && ypos >= 175 && ypos <= 375)
				{
					profile1Flash = false;
					profile2Flash = true;
					profile3Flash = false;
					profile4Flash = false;
					profile5Flash = false;
					profile6Flash = false;
				}
				//Same as previos block
				else if (xpos >= 675 && xpos <= (675 + 275) && ypos >= 175 && ypos <= 375)
				{
					profile1Flash = false;
					profile2Flash = false;
					profile3Flash = true;
					profile4Flash = false;
					profile5Flash = false;
					profile6Flash = false;
				}
				//Same as previos block
				else if (xpos >= 50 && xpos <= (50 + 275) && ypos >= 400 && ypos <= 600)
				{
					profile1Flash = false;
					profile2Flash = false;
					profile3Flash = false;
					profile4Flash = true;
					profile5Flash = false;
					profile6Flash = false;
				}
				//Same as previos block
				else if (xpos >= 362 && xpos <= (362 + 275) && ypos >= 400 && ypos <= 600)
				{
					profile1Flash = false;
					profile2Flash = false;
					profile3Flash = false;
					profile4Flash = false;
					profile5Flash = true;
					profile6Flash = false;
				}
				//Same as previos block
				else if (xpos >= 675 && xpos <= (675 + 275) && ypos >= 400 && ypos <= 600)
				{
					profile1Flash = false;
					profile2Flash = false;
					profile3Flash = false;
					profile4Flash = false;
					profile5Flash = false;
					profile6Flash = true;
				}
				//Same as previos block
				else
				{
					profile1Flash = false;
					profile2Flash = false;
					profile3Flash = false;
					profile4Flash = false;
					profile5Flash = false;
					profile6Flash = false;

				}

				//profiles.profileSelected();
				//p = profiles.next();

				//If mouse is clicked in the 1st profile space
				if (xpress >= 50 && xpress <= (50 + 275) && ypress >= 175 && ypress <= 375)
				{
					//If profile does not exist
					if (profiles.getSize () < 1)
					{
						//Directs top create profile page
						createProfileScreen = true;
						//sets initial variables
						buttonCreated = false;
						emblemCounter = 0;
					}
					else
					{
						//if profile exists
						profile1 = true;//Sets current profile to profile 1
						statsScreen = true;//displays stats for profile 1
					}
					profileSelected = false;
				}
				//Same as above for profile 2
				else if (xpress >= 362 && xpress <= (362 + 275) && ypress >= 175 && ypress <= 375)
				{
					if (profiles.getSize () < 2)
					{
						createProfileScreen = true;
						buttonCreated = false;
						emblemCounter = 0;
					}
					else
					{
						profile2 = true;
						statsScreen = true;
					}
					profileSelected = false;
				}
				//Same as above for profile 3
				else if (xpress >= 675 && xpress <= (675 + 275) && ypress >= 175 && ypress <= 375)
				{
					if (profiles.getSize () < 3)
					{
						createProfileScreen = true;
						buttonCreated = false;
						emblemCounter = 0;
					}
					else
					{
						profile3 = true;
						statsScreen = true;
					}
					profileSelected = false;
				}
				//Same as above for profile 4
				else if (xpress >= 50 && xpress <= (50 + 275) && ypress >= 400 && ypress <= 600)
				{
					if (profiles.getSize () < 4)
					{
						createProfileScreen = true;
						buttonCreated = false;
						emblemCounter = 0;
					}
					else
					{
						profile4 = true;
						statsScreen = true;
					}
					profileSelected = false;
				}
				//Same as above for profile 5
				else if (xpress >= 362 && xpress <= (362 + 275) && ypress >= 400 && ypress <= 600)
				{
					if (profiles.getSize () < 5)
					{
						createProfileScreen = true;
						buttonCreated = false;
						emblemCounter = 0;
					}
					else
					{
						profile5 = true;
						statsScreen = true;
					}
					profileSelected = false;
				}
				//Same as above for profile 6
				else if (xpress >= 675 && xpress <= (675 + 275) && ypress >= 400 && ypress <= 600)
				{
					if (profiles.getSize () < 6)
					{
						createProfileScreen = true;
						buttonCreated = false;
						emblemCounter = 0;
					}
					else
					{
						profile6 = true;
						statsScreen = true;
					}
					profileSelected = false;
				}
				//Resets mouse clicked vars
				xpress = 0;
				ypress = 0;
			}
			//If statistics are being displayed
			if (statsScreen == true)
			{//Shows flashing buttons if withing boundary
				if (xpos >= 325 && xpos <= 325 + 280 && ypos >= 573 && ypos <= 573 + 61)
				{
					deleteFlash = true;
					backFlash = false;
					continueFlash = false;
				}
				else if (xpos >= 12 && xpos <= 12 + 280 && ypos >= 573 && ypos <= 573 + 61)
				{//Shows flashing buttons if withing boundary
					deleteFlash = false;
					backFlash = true;
					continueFlash = false;
				}
				else if (xpos >= 637 && xpos <= 637 + 350 && ypos >= 573 && ypos <= 573 + 61)
				{//Shows flashing buttons if withing boundary
					deleteFlash = false;
					backFlash = false;
					continueFlash = true;
				}
				else
				{//Turns all flashing off
					deleteFlash = false;
					backFlash = false;
					continueFlash = false;
				}

				//***************************************************************************************************************************
				if (xpress >= 325 && xpress <= 325 + 280 && ypress >= 573 && ypress <= 573 + 61)
				{

					try
					{
						input = new ObjectInputStream (
								new FileInputStream ("profiles.txt")); //File to use when inputing the objects
						profiles = (ProfileSystem) input.readObject (); //Save the file info to this array
						input.close (); //close the file

					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace ();
					}

					profiles.deleteProfile (currentUser);
					currentUser = "";

					try
					{ //Try and Catch
						ObjectOutputStream output = new ObjectOutputStream (
								new FileOutputStream ("profiles.txt"));
						output.writeObject (profiles);
						output.close (); //Closes it
					}
					catch (Exception e)
					{

					}

					profile1 = false;
					profile2 = false;
					profile3 = false;
					profile4 = false;
					profile5 = false;
					profile6 = false;

					statsScreen = false;
					profileSelected = true;
					xpress=0;
					ypress=0;


				}
				//**********************************************************************************************************************

				//If back button is pressed
				else if (xpress >= 12 && xpress <= 12 + 280 && ypress >= 573 && ypress <= 573 + 61)
				{
					//returns to the profiles page
					profileSelected = true;
					statsScreen = false;
				}
				//If continue to game button is pressed
				else if (xpress >= 637 && xpress <= 637 + 350 && ypress >= 573 && ypress <= 573 + 61)
				{
					//begins game with parachuter animation
					parachuteAnimation=true;
					statsScreen = false;
				}

				xpress = 0;
				ypress = 0;
			}
			//If the profile is to be created
			if (createProfileScreen == true)
			{
				//Checks how many profiles there are and sets the corresponding profile to true
				if (profiles.getSize() == 0) {
					profile1 = true;
				}
				//Checks how many profiles there are and sets the corresponding profile to true
				else if (profiles.getSize() == 1) {
					profile2 = true;
				}
				//Checks how many profiles there are and sets the corresponding profile to true
				else if (profiles.getSize() == 2) {
					profile3 = true;
				}
				//Checks how many profiles there are and sets the corresponding profile to true
				else if (profiles.getSize() == 3) {
					profile4 = true;
				}
				//Checks how many profiles there are and sets the corresponding profile to true
				else if (profiles.getSize() == 4) {
					profile5 = true;
				}
				//Checks how many profiles there are and sets the corresponding profile to true
				else if (profiles.getSize() == 5) {
					profile6 = true;
				}

				//If create buttons not drawn
				if (buttonCreated == false)
				{
					//Adds textfield
					add (pName);
					//Creates finished button
					buttonCreated = true;
				}
				//Flashing for button
				if (xpos >= 350 && xpos <= (350 + 280) && ypos >= 410 && ypos <= (410 + 61))
				{
					createFlash = true;
				}
				else
				{
					createFlash = false;
				}

				//if the emblem arrow is pressed
				if (xpress >= 434 && xpress <= 454 && ypress >= 335 && ypress <= 355)
				{
					//	traverses through the emblems
					emblemCounter++;
					if (emblemCounter == 15)
						emblemCounter = 0;
				}
				//if the emblem arrow is pressed
				else if (xpress >= 385 && xpress <= 405 && ypress >= 335 && ypress <= 355)
				{
					//					traverses through the emblems
					emblemCounter--;
					if (emblemCounter == -1)
						emblemCounter = 14;
				}
				//Emblems arrow button flashes
				if (xpos >= 434 && xpos <= 454 && ypos >= 335 && ypos <= 355)
				{
					rightFlash = true;
					leftFlash = false;
				}
				//Emblems arrow button flashes
				else if (xpos >= 385 && xpos <= 405 && ypos >= 335 && ypos <= 355)
				{
					leftFlash = true;
					rightFlash = false;
				}
				else
				{
					leftFlash = false;
					rightFlash = false;
				}

				//If create button is pressed
				if (xpress >= 350 && xpress <= (350 + 280) && ypress >= 410 && ypress <= (410 + 61))
				{
					//Writes new profile to file
					try
					{
						if (f.exists ()) //Check if file exists
						{
							input = new ObjectInputStream (
									new FileInputStream ("profiles.txt")); //File to use when inputing the objects
							profiles = (ProfileSystem) input.readObject (); //Save the file info to this array
							input.close (); //close the file

						}
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace ();
					}
					//Calls profile class to add new profile object
					profiles.addProfile (pName.getText (), emblemCounter);

					try
					{ //Try and Catch
						ObjectOutputStream output = new ObjectOutputStream (
								new FileOutputStream ("profiles.txt"));
						output.writeObject (profiles);
						output.close (); //Closes it
					}
					catch (Exception e)
					{

					}
					//Profile screen is set to false
					createProfileScreen = false;
					//removes textfield
					remove (pName);
					//profile has been created
					profileCreated = true;

					//sets current profile to new profile created
					Profile p = profiles.profileSelected ();
					//traverses if profile created is not the first one
					for (int i = 0 ; i < profiles.getSize () ; i++)
					{
						p = profiles.next ();
					}
				}
				//Resets mouse clicked vars
				xpress = 0;
				ypress = 0;
			}
			//If the profile created page done (shows instructions
			if (profileCreated == true)
			{
				//Close buttong flashing
				if (xpos >= 700 && xpos <= 732 && ypos >= 220 && ypos <= 252)
				{
					closeFlash = true;
				}
				else
				{
					closeFlash = false;
				}
				//If close button is pressed
				if (xpress >= 700 && xpress <= 732 && ypress >= 220 && ypress <= 252)
				{
					//Starts game with parachuter animation
					parachuteAnimation=true;
					profileCreated=false;
				}

				xpress = 0;
				ypress = 0;
			}

			//If the parachuter animation is finished and actual game has started
			if (game_Start == true)
			{
				//Stores current profile into variable
				currentProfile=getCurrentProfile();

				//Only occurs if character is not dead or dying
				if (dead==false&&deathAnimation==false){
					//If the map is not transitioning
					if (transition == false)
					{
						//If the character is in the standing position
						if (standing == true)
						{
							if (face_Right == true)
							{//If the character is facing right
								pos = char1_Standing;
							}
							else if (face_Left = true)
							{//If the character is facing left
								pos = char1_StandingLeft;
							}
							standCount += 1;
							//Counter for character animation
							if (standCount == 2)
							{
								standCount = 0;
							}
						}
						//If the D key is pressed
						if (keyPressed == 68)
						{
							//Begins running right
							runRight = true;
							face_Right = true;
							face_Left = false;
							standing = false;
						}
						//If the A key is pressed
						else if (keyPressed == 65)
						{
							//begins running Left
							runLeft = true;
							face_Left = true;
							face_Right = false;
							standing = false;
						}
						//If W key is pressed
						else if (keyPressed == 87 && jumping == false)
						{
							//If character is facing right
							if (face_Right == true && falling == false)
							{
								//Character starts jumping right
								jumpRight = true;
								rise = true;
								keyPressed = 0;
							}
							//If character is facing left
							else if (face_Left == true && falling == false)
							{
								//Character begins jumping left
								jumpLeft = true;
								rise = true;
								keyPressed = 0;
							}
							standing = false;
						}
						//If E key is pressed
						else if (keyPressed == 69)
						{
							//Goes to next gun (change weapons)
							if (current_Gun != weapon.size () - 1)
							{
								current_Gun += 1;
							}
							//If the current gun is the last gun, goes to first gun
							else if (current_Gun == weapon.size () - 1)
							{
								current_Gun = 0;
							}
							//Sets current gun to the next gun
							current_Weapon = weapon.get (current_Gun);
							keyPressed = 0;
						}
						//if Q key is pressed
						else if (keyPressed == 81)
						{
							//if the current gun is not the first gun
							if (current_Gun != 0)
							{
								//goes to previous gun
								current_Gun -= 1;
							}
							else if (current_Gun == 0)//iff current gun is the first gun
							{
								//goes to last gun
								current_Gun = weapon.size () - 1;
							}
							//Assigns current gun to the previous gun
							current_Weapon = weapon.get (current_Gun);
							keyPressed = 0;
						}

					}
					//If character is jumping right
					if (jumpRight == true)
					{
						//character is now jumping and not standing
						jumping = true;
						standing = false;
						//If the runRight button is also being pressed
						if (runRight == true && rightWall == false && rightBoundary == false)
						{
							//If the entire level is unlocked and character is at the right edge of the screen
							if (levelAvailable==true&&charX>=900){
								screenX-=15;
							}
							else {
								//If the level is not unlocked then the character simply moves across the screen
								charX += 15;
							}
							//if the level is fully available
							if (levelAvailable==true){
								//Sets boundaries for the map
								if (charX >= 610+screenX && charX < 640+screenX)
								{

									charY -= 15;
								}
								if (charX >= 220+1640+screenX && charX <= 475+1640+screenX)
									//Sets boundaries for the map
								{
									charY += 7;
									if (standing == true)
									{
										charY -= 7;
									}
								}
								//Sets boundaries for the map
								if (charX >= 745+1640+screenX && charX < 895+1640+screenX && rightBoundary == false)
								{
									charY -= 2;
								}
							}
							//If character is in the first phase
							if (phase1 == true)
							{
								//Sets boundaries for the map
								if (charX >= 610 && charX < 640)
								{

									charY -= 15;
								}
							}
							//If character is in third phase
							if (phase3 == true)
							{
								//Sets boundaries for the map
								if (charX >= 220 && charX <= 475)
								{
									charY += 7;
								}
								//Sets boundaries for the map
								if (charX >= 745 && charX < 895 && rightBoundary == false)
								{
									charY -= 2;
								}
							}


						}

						//If the character is rising during the jump
						if (rise == true)
						{
							//Decreases y value (to go up
							charY -= 30;
							//sets jumping picture
							pos = char1_Jump_Right [jumpCount];
							//Increased counter
							jumpCount += 1;
							if (jumpCount == 4)
							{//If the character is at peak of jump
								rise = false;
								fall = true;//begins falling
							}
							else
							{
								//Does a delay if the character is rising or falling
								try
								{
									Thread.sleep (100);
								}
								catch (InterruptedException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace ();
								}
							}
						}
						if (fall == true)//If the character is returning from the jump
						{
							//Increase Y pos (to come back down)
							charY += 30;
							//Sets positions image
							pos = char1_Jump_Right [jumpCount];
							//Increases coutner
							jumpCount += 1;
							//If jump is finished
							if (jumpCount == 8)
							{
								//Sets all jump variables to false
								fall = false;
								jumpRight = false;
								jumping = false;
								if (runRight == false && falling == false)
								{
									//Sets standing to true
									standing = true;

									keyPressed = 0;
								}
								//resets coutner
								jumpCount = 0;
							}
							//adds delay
							try
							{
								Thread.sleep (100);
							}
							catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace ();
							}
						}

					}
					//Same as above but for jumping facing left
					else if (jumpLeft == true)
					{
						jumping = true;
						standing = false;
						if (runLeft == true && leftWall == false && leftBoundary == false)//Same as above but for jumping facing left
						{
							if (levelAvailable==true&&charX<=100){
								screenX+=15;
							}
							else {//Same as above but for jumping facing left
								charX -= 15;
							}
							if (levelAvailable==true){//Same as above but for jumping facing left
								if (charX >= 610+screenX && charX < 640+screenX)
								{

									charY -= 15;
								}
								if (charX >= 220+1640+screenX && charX <= 475+1640+screenX)//Same as above but for jumping facing left
								{
									charY -= 7;
									if (standing == true)
									{
										charY += 7;
									}
								}
								if (charX >= 745+1640+screenX && charX < 895+1640+screenX && rightBoundary == false)//Same as above but for jumping facing left
								{
									charY += 2;
								}
							}//Same as above but for jumping facing left
							if (phase1 == true)
							{
								if (charX >= 610 && charX < 640)
								{

									charY += 15;
								}
							}//Same as above but for jumping facing left
							if (phase3 == true)
							{
								if (charX >= 220 && charX <= 475)
								{
									charY -= 7;
								}
								if (charX >= 745 && charX < 895 && rightBoundary == false)
								{
									charY += 2;
								}
							}

						}

						//Same as above but for jumping facing left
						if (rise == true)
						{
							charY -= 30;
							pos = char1_Jump_Left [jumpCount];
							jumpCount += 1;
							if (jumpCount == 4)
							{
								rise = false;
								fall = true;
							}
							else//Same as above but for jumping facing left
							{
								try
								{
									Thread.sleep (100);
								}
								catch (InterruptedException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace ();
								}
							}
						}
						if (fall == true)//Same as above but for jumping facing left
						{
							charY += 30;
							pos = char1_Jump_Left [jumpCount];
							jumpCount += 1;
							if (jumpCount == 8)//Same as above but for jumping facing left
							{
								fall = false;
								jumpLeft = false;
								jumping = false;
								if (runLeft == false && falling == false)
								{
									standing = true;
									keyPressed = 0;
								}//Same as above but for jumping facing left
								jumpCount = 0;
							}
							try
							{
								Thread.sleep (100);
							}
							catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace ();
							}
						}

					}
					//If character is running right
					else if (runRight == true)
					{
						standing = false;//No longer standing
						if (rightBoundary == false && rightWall == false)//If there are no boundaries blocking the character
						{
							//If the level is unlocked and character is at the right edge
							if (levelAvailable==true&&charX>=900){
								//moves the screen
								screenX-=15;
							}
							else {//If level is not unlocked
								//Moves character
								charX += 15;
							}
							leftWall = false;
						}
						//Running images
						pos = char1_Run_Right [runCount];
						//increases counter
						runCount += 1;
						if (runCount == 8)//If animation is finished
						{
							//resets animation
							runCount = 0;
						}
						try
						{//adds delay
							Thread.sleep (80);
						}
						catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace ();
						}
						//Boundaries for level
						if (levelAvailable==true){
							if (charX >= 610+screenX && charX < 640+screenX)
							{

								charY -= 15;
							}
							if (charX >= 220+1640+screenX && charX <= 475+1640+screenX)//Boundaries for level
							{
								charY += 7;
								if (standing == true)
								{
									charY -= 7;
								}
							}
							if (charX >= 745+1640+screenX && charX < 895+1640+screenX && rightBoundary == false)//Boundaries for level
							{
								charY -= 2;
							}
						}


						if (phase1 == true)
						{//Boundaries for level
							if (charX >= 610 && charX < 640)
							{

								charY -= 15;
							}
						}
						if (phase3 == true)
						{//Boundaries for level
							if (charX >= 220 && charX <= 475)
							{
								charY += 7;
								if (standing == true)
								{
									charY -= 7;
								}
							}
							if (charX >= 745 && charX < 895 && rightBoundary == false)//Boundaries for level
							{
								charY -= 2;
							}
						}

					}

					else if (runLeft == true)//Same as above but for running left
					{
						standing = false;
						if (leftBoundary == false && leftWall == false)//Same as above but for running left
						{
							if (levelAvailable==true&&charX<=100&&screenX<0){
								screenX+=15;
							}
							else {
								charX -= 15;
							}
							rightWall = false;
						}

						//Same as above but for running left
						pos = char1_Run_Left [runCount];
						runCount += 1;
						if (runCount == 8)
						{
							runCount = 0;
						}
						try
						{
							Thread.sleep (80);
						}
						catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace ();
						}
						//Same as above but for running left
						if (levelAvailable==true){
							if (charX >= 610+screenX && charX < 640+screenX)
							{

								charY += 15;
							}
							//Same as above but for running left
							if (charX >= 220+1640+screenX && charX <= 475+1640+screenX)
							{
								charY -= 7;
								if (standing == true)
								{
									charY += 7;
								}
							}
							//Same as above but for running left
							if (charX >= 745+1640+screenX && charX < 895+1640+screenX && rightBoundary == false)
							{
								charY += 2;
							}
						}
						//Same as above but for running left
						if (phase1 == true)
						{
							if (charX >= 610 && charX < 640)
							{

								charY += 15;
							}
						}
						if (phase3 == true)//Same as above but for running left
						{
							if (charX >= 220 && charX <= 475)
							{
								charY -= 7;
								if (standing == true)
								{
									charY += 7;
								}
							}
							//Same as above but for running left
							if (charX >= 745 && charX < 895 && rightBoundary == false)
							{
								charY += 2;
							}
						}


					}
					//Ensures screen does not move
					if (screenX > 0)
					{
						screenX = 0;
					}
					//If the character is at the left edge
					if (charX <= 0)
					{
						//Cannot move farther left
						leftBoundary = true;
					}
					else if (charX >= 960)//Same as above for right boundary
					{
						rightBoundary = true;
					}
					else
					{//If character is not at a boundary
						rightBoundary = false;
						leftBoundary = false;
					}
					//If the character is shooting
					if (shoot == true)
					{
						//Draws the bulelt
						drawBullet = true;

					}
					//Boundaries for the level
					if (phase1 == true && transition == false)
					{
						if (charX <= 145 && charY > 515)//Boundaries for the level
						{
							leftWall = true;
						}
						else if (charX == 145 && charY <= 565)//Boundaries for the level
						{
							leftWall = false;
						}
						//Boundaries for the level

						if (charX >= 145 && charX <= 300 && charY >= 515 && jumping == false)//Boundaries for the level
						{
							if (charY < 590)//Boundaries for the level
							{
								charY += 15;
								pos = char1_Jump_Right [4];
								falling = true;
							}
							else if (charY >= 590)//Boundaries for the level
							{

								falling = false;

							}

						}
						//Boundaries for the level
						if (charX > 280 && charX < 505 && charY < 595 && jumping == false)
						{
							charY += 10;
							try
							{
								Thread.sleep (50);
							}
							catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace ();
							}
							falling = true;
						}
						else if (charX >= 595)//Boundaries for the level
						{
							falling = false;
						}
						//Boundaries for the level

						if (fall == true && charX < 145 && charX > 0 && charY <= 515 && charY >= 485)
						{
							//Boundaries for the level
							charY = 515;
							jumpRight = false;
							jumpLeft = false;
							jumping = false;
							jumpCount = 0;
							fall = false;
							rise = false;
							standing = true;
						}
						//Boundaries for the level
						if (charX >= 640 && charX <= 670 && charY > 500)
						{
							rightWall = true;

						}
						//Boundaries for the level
						else if (charX >= 640 && charX <= 670 && charY <= 500)
						{
							rightWall = false;
						}
						//Boundaries for the level
						if (charX > 145 && charX < 595 && jumping == false && falling == false)
						{
							charY = 595;
						}
						//Boundaries for the level
						if (fall == true && charX > 640 && charX < 700 && charY <= 500 && charY >= 470)
						{

							charY = 500;
							jumpRight = false;
							jumpLeft = false;
							jumping = false;
							jumpCount = 0;
							fall = false;
							rise = false;
							standing = true;
						}
						//Boundaries for the level
						if (heliCount == 3 && helis.get (heliCount).getAlive () == false && charX > 900 && phase1 == true&&explosion==false)
						{
							transition = true;
						}
					}

					//Boundaries for the level
					if (phase2 == true && transition == false)
					{
						if (charX >= 310 && charX <= 350 && charY > 440)//Boundaries for the level
						{
							rightWall = true;
						}
						else if (charX >= 310 && charX < 350 && charY <= 440)//Boundaries for the level
						{
							rightWall = false;
						}

						if (fall == true && charX >= 310 && charX < 360 && charY <= 440 && charY >= 410)//Boundaries for the level
						{
							//Boundaries for the level
							charY = 440;
							jumpRight = false;
							jumpLeft = false;
							jumping = false;
							jumpCount = 0;
							fall = false;
							rise = false;
							standing = true;
						}
						if (charX < 310 && charY < 500 && jumping == false)//Boundaries for the level
						{
							charY += 15;
							if (face_Right == true)//Boundaries for the level
							{
								pos = char1_Jump_Right [4];
							}
							else if (face_Left == true)//Boundaries for the level
							{
								pos = char1_Jump_Left [4];
							}
							try
							{
								Thread.sleep (30);
							}
							catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace ();
							}
						}
						//If the helicopters for this phase is finished
						if (heliCount == 7 && helis.get (heliCount).getAlive () == false && charX > 900 && phase2 == true&&explosion==false)
						{
							//Moves onto next phase
							transition = true;
						}

					}

					if (phase3 == true && transition == false)//Boundaries for the level
					{
						if (charX >= 910)//Boundaries for the level
						{
							rightBoundary = true;
						}
						if (charX >= 10 && charX <= 205 && jumping == false)//Boundaries for the level
						{
							charY = 440;
						}
						if (charX >= 475 && charX <= 730 && jumping == false)//Boundaries for the level
						{
							charY = 565;
						}
						if (heliCount >= 12 && helis.get (heliCount).getAlive () == false &&explosion==false)//Boundaries for the level
						{
							levelUnlocked=true;
						}
					}

					//If the phases are transitioning
					if (transition == true)
					{
						//Makes all movement false
						runRight = false;
						runLeft = false;
						jumpRight = false;
						jumpLeft = false;
						jumping = false;
						falling = false;
						shoot=false;
						//Moves the screen to the left
						weaponDropX-=25;
						screenX -= 25;
						charX -= 25;
						//Adds delay
						try
						{
							Thread.sleep (25);
						}
						catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace ();
						}
						//Once the character has moved to far, the phase changes
						if (charX < 100 || screenX <= -2632)
						{
							if (phase1 == true)
							{
								phase1 = false;
								phase2 = true;
							}
							else if (phase2 == true)
							{
								phase2 = false;
								phase3 = true;
							}
							//Boundaries for the level
							weaponFall=false;
							weaponFallen=false;
							weaponPickup=null;
							dropWeapon=null;
							transition = false;


						}
					}

					//If the helicopter is past the screen
					if (heliX <= -300)
					{
						//Flies other way
						flyRight = true;
						flyLeft = false;
					}
					if (heliX >= 1100)//If the helicopter is past the screen
					{
						//flies other way
						flyLeft = true;
						flyRight = false;
					}
					//If heli is flying right
					
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					//***************************************************************************************************************
					
					if (flyRight == true)
					{
						heliX += 10;
						flyLeft = false;
						helis.get (heliCount).setDirection (helis.get(heliCount).getRightPic());
						if (runRight != true && runLeft != true && jumping != true)
						{
							try
							{
								Thread.sleep (80);
							}
							catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace ();
							}
						}

					}
					if (flyLeft == true)
					{
						heliX -= 10;
						flyRight = false;
						helis.get (heliCount).setDirection (helis.get(heliCount).getLeftPic());
						if (runRight != true && runLeft != true && jumping != true)
						{
							try
							{
								Thread.sleep (80);
							}
							catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace ();
							}
						}

					}

					if (helis.get (heliCount).getAlive () == true)
					{

						long currentTime = System.currentTimeMillis ();
						if (heliX>0&&heliX<1000){
							if (helis.get(heliCount).getType()==1){
								if (currentTime-now >= 2000)
								{
									heliShoot = true;
								}

							}
							else if (helis.get(heliCount).getType()==2){
								if (currentTime-now >= 500)
								{
									heliShoot = true;
								}

							}
						}

						if (heliShoot == true)
						{
							int h = r.nextInt ((charX + 20) - (charX - 20)) + (charX - 20);
							heliBullets.add (new Bullet (50, heliBulletAngle, heliX + 30, heliY + 30, 7, 7, heliX + 30, heliY + 60, heliBulletPic));
							heliSlope.add (((double) ((charY) - heliY + 60)) / ((double) (h - (heliX + 30))));

							helixPos.add ((double) heliX + 60);
							heliyPos.add ((double) heliY + 30);
							heliBulletDetectX.add (heliX + 60);
							heliBulletDetectY.add (heliY + 30);
							heliOriginX.add (heliX + 60);
							heliOriginY.add (heliY + 30);
							now = System.currentTimeMillis ();

							heliXCounters.add (0);
							heliShoot = false;
						}



						//p.1: 580-715 p.3 175-505, 760-1000

					}
					if (weaponPickup!=null&&weaponFallen==false){
						weaponFall=true;
						dropWeapon=weaponPickup;

					}

					if (weaponFall==true){
						weaponDropY+=5;
						if (runRight==true||runLeft==true||jumping==true||flyRight==true||flyLeft==true){
							weaponDropY+=5;
						}
						else{
							try {
								Thread.sleep(40);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (phase1==true&&weaponDropX>=-100&&weaponDropX<145&&weaponDropY>=530){
							weaponDropY=530;
							weaponFall=false;
							weaponFallen=true;
						}
						else if (phase1==true&&weaponDropX>=145&&weaponDropX<=680&&weaponDropY>=615){
							weaponDropY=615;
							weaponFall=false;
							weaponFallen=true;
						}
						else if (phase1==true&&weaponDropX>=670&&weaponDropX<1000&&weaponDropY>=520){
							weaponDropY=520;
							weaponFall=false;
							weaponFallen=true;
						}
						else if (phase2==true&&weaponDropX>=-100&&weaponDropX<340&&weaponDropY>=520){
							weaponDropY=520;
							weaponFall=false;
							weaponFallen=true;
						}
						else if (phase2==true&&weaponDropX>=340&&weaponDropX<1000&&weaponDropY>=460){
							weaponDropY=460;
							weaponFall=false;
							weaponFallen=true;
						}
						else if (phase3==true&&weaponDropX>=-100&&weaponDropX<220&&weaponDropY>=460){
							weaponDropY=460;
							weaponFall=false;
							weaponFallen=true;
						}
						else if (phase3==true&&weaponDropX>=470&&weaponDropX<1000&&weaponDropY>=585){
							weaponDropY=585;
							weaponFall=false;
							weaponFallen=true;
						}
						else if (levelAvailable==true){
							if (weaponDropX>=-100+screenX&&weaponDropX<145+screenX&&weaponDropY>=530){
								weaponDropY=530;
								weaponFall=false;
								weaponFallen=true;
							}
							else if (weaponDropX>=145+screenX&&weaponDropX<=680+screenX&&weaponDropY>=615){
								weaponDropY=615;
								weaponFall=false;
								weaponFallen=true;
							}
							else if (weaponDropX>=670+screenX&&weaponDropX<1000+screenX&&weaponDropY>=520){
								weaponDropY=520;
								weaponFall=false;
								weaponFallen=true;
							}
							else if (weaponDropX>=-100+820+screenX&&weaponDropX<340+820+screenX&&weaponDropY>=520){
								weaponDropY=520;
								weaponFall=false;
								weaponFallen=true;
							}
							else if (weaponDropX>=340+820+screenX&&weaponDropX<1000+820+screenX&&weaponDropY>=460){
								weaponDropY=460;
								weaponFall=false;
								weaponFallen=true;
							}
							else if (weaponDropX>=-100+1640+screenX&&weaponDropX<220+1640+screenX&&weaponDropY>=460){
								weaponDropY=460;
								weaponFall=false;
								weaponFallen=true;
							}
							else if (weaponDropX>=470+1640+screenX&&weaponDropX<1000+1640+screenX&&weaponDropY>=585){
								weaponDropY=585;
								weaponFall=false;
								weaponFallen=true;
							}
						}


					}
					if (weaponFallen==true){
						if (charX>weaponDropX-40&&charX<weaponDropX+40&&charY>weaponDropY-30){

							for (int i=0;i<weapon.size();i++){

								if (dropWeapon.getType()==weapon.get(i).getType()){
									weapon.get(i).reload();
									weaponOwned=true;
									break;
								}
								else{
									weaponOwned=false;

								}
							}
							if (weaponOwned==false){
								weapon.add(dropWeapon);
							}


							weaponFallen=false;
							dropWeapon=null;
							weaponPickup=null;
						}
					}

					if (currentProfile.getCurrentHealth()<=0&&deathAnimation==false){
						deathAnimation=true;
						runRight=false;
						shoot=false;
						runLeft=false;
						jumping=false;
						jumpRight=false;
						jumpLeft=false;

					}

					if (fireAvailable[0]==false){
						fireReady[0]=System.currentTimeMillis ();

						for (int i=0;i<weapon.size();i++){
							if (weapon.get(i).getType()==1){

								if (fireReady[0]-lastFire[0]>=(int)(weapon.get(i).getDelay()*1000)){
									fireAvailable[0]=true;
								}
							}
						}
					}
					if (fireAvailable[1]==false){
						fireReady[1]=System.currentTimeMillis ();
						for (int i=0;i<weapon.size();i++){
							if (weapon.get(i).getType()==2){

								if (fireReady[1]-lastFire[1]>=(int)(weapon.get(i).getDelay()*1000)){
									fireAvailable[1]=true;
								}
							}
						}
					}
					if (fireAvailable[2]==false){
						fireReady[2]=System.currentTimeMillis ();
						for (int i=0;i<weapon.size();i++){
							if (weapon.get(i).getType()==3){

								if (fireReady[2]-lastFire[2]>=(int)(weapon.get(i).getDelay()*1000)){
									fireAvailable[2]=true;
								}
							}
						}
					}
					if (fireAvailable[3]==false){
						fireReady[3]=System.currentTimeMillis ();
						for (int i=0;i<weapon.size();i++){
							if (weapon.get(i).getType()==4){

								if (fireReady[3]-lastFire[3]>=(int)(weapon.get(i).getDelay()*1000)){
									fireAvailable[3]=true;
								}
							}
						}
					}
					if (fireAvailable[4]==false){
						fireReady[4]=System.currentTimeMillis ();
						for (int i=0;i<weapon.size();i++){
							if (weapon.get(i).getType()==5){

								if (fireReady[4]-lastFire[4]>=(int)(weapon.get(i).getDelay()*1000)){
									fireAvailable[4]=true;
								}
							}
						}
					}
					if (fireAvailable[5]==false){
						fireReady[5]=System.currentTimeMillis ();
						for (int i=0;i<weapon.size();i++){
							if (weapon.get(i).getType()==6){

								if (fireReady[5]-lastFire[5]>=(int)(weapon.get(i).getDelay()*1000)){
									fireAvailable[5]=true;
								}
							}
						}
					}
					if (fireAvailable[6]==false){
						fireReady[6]=System.currentTimeMillis ();
						for (int i=0;i<weapon.size();i++){
							if (weapon.get(i).getType()==7){

								if (fireReady[6]-lastFire[6]>=(int)(weapon.get(i).getDelay()*1000)){
									fireAvailable[6]=true;
								}
							}
						}
					}
					if (fireAvailable[7]==false){
						fireReady[7]=System.currentTimeMillis ();
						for (int i=0;i<weapon.size();i++){
							if (weapon.get(i).getType()==8){

								if (fireReady[7]-lastFire[7]>=(int)(weapon.get(i).getDelay()*1000)){
									fireAvailable[7]=true;
								}
							}
						}
					}
					if (fireAvailable[8]==false){
						fireReady[8]=System.currentTimeMillis ();
						for (int i=0;i<weapon.size();i++){
							if (weapon.get(i).getType()==9){

								if (fireReady[8]-lastFire[8]>=(int)(weapon.get(i).getDelay()*1000)){
									fireAvailable[8]=true;
								}
							}
						}
					}
					if (fireAvailable[9]==false){
						fireReady[9]=System.currentTimeMillis ();
						for (int i=0;i<weapon.size();i++){
							if (weapon.get(i).getType()==10){

								if (fireReady[9]-lastFire[9]>=(int)(weapon.get(i).getDelay()*1000)){
									fireAvailable[9]=true;
								}
							}
						}
					}

					if (levelUnlocked==true){
						phase1=false;
						phase2=false;
						phase3=false;
						transition=false;
						levelAvailable=true;
					}

					if (levelAvailable==true){

						if (charX>=145+screenX&&charX<=300+screenX&& charY >= 515 && jumping == false){
							if (charY < 590)
							{
								charY += 15;
								pos = char1_Jump_Right [4];
								falling = true;
							}
							else if (charY >= 590)
							{

								falling = false;

							}
						}
						if (charX <= 145+screenX && charY > 515)
						{
							leftWall = true;
						}
						else if (charX == 145+screenX && charY <= 565)
						{
							leftWall = false;
						}

						if (charX > 280+screenX && charX < 505+screenX && charY < 595 && jumping == false)
						{
							charY += 10;
							try
							{
								Thread.sleep (50);
							}
							catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace ();
							}
							falling = true;
						}
						else if (charX >= 595+screenX)
						{
							falling = false;
						}


						if (fall == true && charX <= (145+screenX) && charX > (0+screenX) && charY <= 520 && charY >= 485)
						{

							charY = 515;
							jumpRight = false;
							jumpLeft = false;
							jumping = false;
							jumpCount = 0;
							fall = false;
							rise = false;
							standing = true;
						}

						if (charX<640+screenX&&charX>600+screenX&&charY<=565){
							charY += 15;
							pos = char1_Jump_Right [4];


						}
						if (charX >= 640+screenX && charX <= 670+screenX && charY > 500)
						{
							rightWall = true;

						}
						else if (charX >= 640+screenX && charX <= 670+screenX && charY <= 500)
						{
							rightWall = false;
						}

						if (charX > 145+screenX && charX < 595+screenX && jumping == false && falling == false)
						{
							charY = 595;
						}


						if (fall == true && charX > 640+screenX && charX < 700+screenX && charY <= 500 && charY >= 470)
						{
							charY = 500;
							jumpRight = false;
							jumpLeft = false;
							jumping = false;
							jumpCount = 0;
							fall = false;
							rise = false;
							standing = true;
						}




						if (charX >= (310+820+screenX) && charX <= (350+820+screenX) && charY > 440)
						{
							rightWall = true;
						}
						else if (charX >= (310+820+screenX) && charX < (350+820+screenX) && charY <= 440)
						{
							rightWall = false;
						}

						if (fall == true && charX >= 310+820+screenX && charX < 360+820+screenX && charY <= 440 && charY >= 410)
						{

							charY = 440;
							jumpRight = false;
							jumpLeft = false;
							jumping = false;
							jumpCount = 0;
							fall = false;
							rise = false;
							standing = true;
						}

						if (charX < 310+820+screenX && charY < 500 && jumping == false)
						{
							charY += 15;
							if (face_Right == true)
							{
								pos = char1_Jump_Right [4];
							}
							else if (face_Left == true)
							{
								pos = char1_Jump_Left [4];
							}
							try
							{
								Thread.sleep (30);
							}
							catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace ();
							}
						}


						if (charX >= 910+1640+screenX)
						{
							rightBoundary = true;
						}
						if (charX >= 10+1640+screenX && charX <= 205+1640+screenX && jumping == false)
						{
							charY = 440;
						}
						if (charX >= 475+1640+screenX && charX <= 730+1640+screenX && jumping == false)
						{
							charY = 565;
						}
					}

					//0-145->515, 145-580->595, 670-1000->500
					//0-340->500, 340-1000->440
					//0-220->440, 470-1000->565
				} 

				if (dead==true){
					deathCount=0;
					if (gameOverBGCount==280){
						if (deathScreen==false){
							main_Menu=true;
							game_Start=false;
							phase1=false;
							phase2=false;
							phase3=false;
							levelUnlocked=false;
							levelAvailable=false;
							dead=false;
							gameOverBGCount=0;

						}
					}
					else{
						deathScreen=true;
					}


				}
			}

			repaint ();
			// Increases thread priority to max
			Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);
		}
	}


	public Node transverse (Node current, int times)
	{
		Node temp = current;
		int t = times;
		if (t == 0)
			return temp;
		else
		{
			t = t - 1;
			temp = temp.getNext ();
			return transverse (temp, t);
		}

	}


	public void paint (Graphics g)
	{

		Graphics2D g2d = (Graphics2D) g;

		AffineTransform origXform = g2d.getTransform ();
		AffineTransform newXform = (AffineTransform) (origXform.clone ());
		AffineTransform newXform2 = (AffineTransform) (origXform.clone ());
		AffineTransform newXform3 = (AffineTransform) (origXform.clone ());

		g.setFont (font);
		g.setColor (Color.lightGray);

		if (main_Menu == true)
		{
			if (flashOnce == true)
			{
				g.drawImage (nukeFlash [flashCount], 0, 0, this);
				g.drawImage (bullet_3 [bulletCount3], 0, 0, this);
				g.drawImage(deathImages[deathCount],0,0,this);
				deathCount+=1;
				flashCount += 1;

				bulletCount3 += 1;
				if (deathCount>=6){
					deathCount=6;
				}
				if (bulletCount3 >= 11)
				{
					bulletCount3 = 11;
				}
				if (flashCount >= 17)
				{
					bulletCount3=0;
					flashCount = 0;
					deathCount=0;
					flashOnce = false;
				}
			}


			g.drawImage (menu_BG, 0, 0, this);
			g.drawImage (menu_List, 0, 0, this);
			g.drawImage (menu_Title, 0, 0, this);
			if (startFlash == true)
			{
				g.drawImage (menu_List_Start, 0, 0, this);
			}
			else if (instructFlash == true)
			{
				g.drawImage (menu_List_Instruct, 0, 0, this);
			}
			else if (scoreFlash == true)
			{
				g.drawImage (menu_List_Score, 0, 0, this);
			}
			else if (quitFlash == true)
			{
				g.drawImage (menu_List_Quit, 0, 65, this);
			}


		}

		if (scoresScreen == true) {
			g.drawImage(menu_BG,0,0,this);
			g.drawImage(menu_Title,0,0,this);
			g.drawImage(instructions, 260, 200, this);

			g.setFont(smallF);

			if (scores.size() > 0) {
				for (int s = 0; s < scores.size(); s++) {
					System.out.println(s);
					g.drawString ("Score: " + scores.get(s), 575, 250 + 30*s);

					for (int i = 0; i < scores.size(); i++) {
						if (scores.get(s) == transverse(profiles.listProfiles(), i).getProfile().getHighScore()) {
							g.drawString(transverse(profiles.listProfiles(), i).getProfile().getName(), 415, 250 + 30*s);
							i = scores.size();
						}
					}

					if (s == 0) {
						g.drawString((s + 1) + "st", 300, 250);
					}
					if (s == 1) {
						g.drawString((s + 1) + "nd", 300, 280);
					}
					if (s == 2) {
						g.drawString((s + 1) + "rd", 300, 310);
					}
					if (s >= 3) {
						g.drawString((s + 1) + "th", 300, 340);
					}
				}
			}
			else {
				g.setFont(font);
				g.drawString("NO RESULTS", 310, 300);
			}

			g.setFont(font);

			if (backFlash == true) 
				g.drawImage(back2, 12, 573, this);
			else
				g.drawImage(back1, 12, 573, this);

		}


		if (profileSelected == true) {

			g.drawImage(menu_BG,0,0,this);
			g.drawImage(menu_Title,0,0,this);

			g.drawImage(profileButton2, 50, 175, this);
			g.drawImage(profileButton2, 362, 175, this);
			g.drawImage(profileButton2, 675, 175, this);
			g.drawImage(profileButton2, 50, 400, this);
			g.drawImage(profileButton2, 362, 400, this);
			g.drawImage(profileButton2, 675, 400, this);

			if (profile1Flash == true)
				g.drawImage(profileButton, 50, 175, this);
			else if (profile2Flash == true)
				g.drawImage(profileButton, 362, 175, this);
			else if (profile3Flash == true)
				g.drawImage(profileButton, 675, 175, this);
			else if (profile4Flash == true)
				g.drawImage(profileButton, 50, 400, this);
			else if (profile5Flash == true)
				g.drawImage(profileButton, 362, 400, this);
			else if (profile6Flash == true)
				g.drawImage(profileButton, 675, 400, this);

			int size = profiles.getSize();
			int textWidth;

			Node head = null;

			Profile user = null;

			if (size >= 1)	
				head = profiles.listProfiles();

			if(size < 1) { 
				g.drawString("Profile", 70, 260);
				g.drawString("1", 170, 330);
			}
			else {
				g.setFont(bigF);

				fm = g.getFontMetrics ();

				user = head.getProfile();

				currentUser = user.getName();

				rect = fm.getStringBounds (currentUser, g);

				textWidth = (int) (rect.getWidth ());

				g.drawString (currentUser, (275 - textWidth) / 2 + 50, 220);

				g.setFont(smallF);

				g.drawString ("Score: " + Integer.toString(user.getHighScore()), 180, 292);
				g.drawString ("Rank " + Integer.toString(user.getRank()), 83, 367);

				g.setFont(bigF);

				g.drawImage (emblemBack, 70, 240, this);

				g.drawImage(emblems[user.getEmblem()], 70, 240, this);

				g.setFont(font);
			}

			if(size < 2) {
				g.drawString("Profile", 382, 260);
				g.drawString("2", 482, 330);
			}	
			else {
				g.setFont(bigF);

				fm = g.getFontMetrics ();

				user = transverse(profiles.listProfiles(),1).getProfile();

				currentUser = user.getName();

				rect = fm.getStringBounds (currentUser, g);

				textWidth = (int) (rect.getWidth ());

				g.drawString (currentUser, (275 - textWidth) / 2 + 362, 220);

				g.setFont(smallF);

				g.drawString ("Score: " + Integer.toString(user.getHighScore()), 362 + 130, 292);
				g.drawString ("Rank " + Integer.toString(user.getRank()), 362 + 33, 367);

				g.setFont(bigF);

				g.drawImage (emblemBack, 382, 240, this);

				g.drawImage(emblems[user.getEmblem()], 382, 240, this);

				g.setFont(font);
			}

			if(size < 3) {
				g.drawString("Profile", 695, 260);
				g.drawString("3", 795, 330);
			}
			else {
				g.setFont(bigF);

				fm = g.getFontMetrics ();

				user = transverse(head,2).getProfile();

				currentUser = user.getName();

				rect = fm.getStringBounds (currentUser, g);

				textWidth = (int) (rect.getWidth ());

				g.drawString (currentUser, (275 - textWidth) / 2 + 675, 220);

				g.setFont(smallF);

				g.drawString ("Score: " + Integer.toString(user.getHighScore()), 675 + 130, 292);
				g.drawString ("Rank " + Integer.toString(user.getRank()), 675 + 33, 367);

				g.setFont(bigF);

				g.drawImage (emblemBack, 695, 240, this);

				g.drawImage(emblems[user.getEmblem()], 695, 240, this);

				g.setFont(font);
			}

			if(size < 4) {
				g.drawString("Profile", 70, 485);
				g.drawString("4", 170, 485+70);
			}
			else {
				g.setFont(bigF);

				fm = g.getFontMetrics ();

				user = transverse(head,3).getProfile();

				currentUser = user.getName();

				rect = fm.getStringBounds (currentUser, g);

				textWidth = (int) (rect.getWidth ());

				g.drawString (currentUser, (275 - textWidth) / 2 + 50, 445);

				g.setFont(smallF);

				g.drawString ("Score: " + Integer.toString(user.getHighScore()), 180, 292);
				g.drawString ("Rank " + Integer.toString(user.getRank()), 83, 367);

				g.setFont(bigF);

				g.drawImage (emblemBack, 70, 465, this);

				g.drawImage(emblems[user.getEmblem()], 70, 465, this);

				g.setFont(font);
			}

			if(size < 5) {
				g.drawString("Profile", 382, 485);
				g.drawString("5", 482, 485+70);
			}
			else {
				g.setFont(bigF);

				fm = g.getFontMetrics ();

				user = transverse(head, 4).getProfile();

				currentUser = user.getName();

				rect = fm.getStringBounds (currentUser, g);

				textWidth = (int) (rect.getWidth ());

				g.drawString (currentUser, (275 - textWidth) / 2 + 362, 445);

				g.setFont(smallF);

				g.drawString ("Score: " + Integer.toString(user.getHighScore()), 362 + 130, 292);
				g.drawString ("Rank " + Integer.toString(user.getRank()), 362 + 33, 367);

				g.setFont(bigF);

				g.drawImage (emblemBack, 382, 465, this);

				g.drawImage(emblems[user.getEmblem()], 382, 465, this);

				g.setFont(font);
			}

			if (size < 6) {
				g.drawString("Profile", 695, 485);
				g.drawString("6", 795, 485+70);
			}
			else {
				g.setFont(bigF);

				fm = g.getFontMetrics ();

				user = transverse(head, 5).getProfile();

				currentUser = user.getName();

				rect = fm.getStringBounds (currentUser, g);

				textWidth = (int) (rect.getWidth ());

				g.drawString (currentUser, (275 - textWidth) / 2 + 675, 445);

				g.setFont(smallF);

				g.drawString ("Score: " + Integer.toString(user.getHighScore()), 675 + 130, 292);
				g.drawString ("Rank " + Integer.toString(user.getRank()), 675 + 33, 367);

				g.setFont(bigF);

				g.drawImage (emblemBack, 695, 465, this);

				g.drawImage(emblems[user.getEmblem()], 695, 465, this);

				g.setFont(font);
			}
		}

		if (instructionsScreen == true) {
			g.drawImage(menu_BG,0,0,this);
			g.drawImage(menu_Title,0,0,this);

			if (backFlash == true) 
				g.drawImage(back2, 12, 573, this);
			else
				g.drawImage(back1, 12, 573, this);

			g.drawImage(instructions, 260, 200, this);

			g.drawImage(keys1, 382, 375, this);
			g.drawImage(keys_right, 517, 418, this);
			g.drawImage(keys_left, 330, 418, this);
			g.drawImage(keys_up, 432, 315, this);

			g.setFont(bigF);
			g.setColor(Color.BLACK);

			g.drawString("Instructions", 392, 250);

			g.setColor(Color.lightGray);
			g.setFont(smallF);

			g.drawString("Move", 270, 428);
			g.drawString("Left", 272, 455);
			g.drawString("Move", 567, 428);
			g.drawString("Right", 565, 455);
			g.drawString("Jump", 419, 310);

			g.drawImage(mouse, 660, 305, this);
			g.drawString("Shoot", 650, 435);
			g.setFont(font);

		}

		if (statsScreen == true) {
			g.drawImage(menu_BG,0,0,this);
			g.drawImage(menu_Title,0,0,this);
			g.drawImage(stats, 253, 162, this);
			g.drawImage(profileBadge, 375, 205, this);

			if (deleteFlash == true)
				g.drawImage(delete2, 325, 573, this);
			else 
				g.drawImage(delete1, 325, 573, this);
			if (continueFlash == true)
				g.drawImage(continue2, 637, 573, this);
			else 
				g.drawImage(continue1, 637, 573, this);
			if (backFlash == true) 
				g.drawImage(back2, 12, 573, this);
			else
				g.drawImage(back1, 12, 573, this);

			int size = profiles.getSize();
			int textWidth;

			Node head = profiles.listProfiles();

			Profile user = null;

			if(profile1 == true && profiles.getSize() > 0) {
				g.setFont(bigF);

				fm = g.getFontMetrics ();

				user = head.getProfile();

				currentUser = user.getName();

				rect = fm.getStringBounds (currentUser, g);

				textWidth = (int) (rect.getWidth ());

				g.drawString (currentUser, (250 - (465-373) - textWidth) / 2 + 465, 260);

				g.setFont(smallF);

				g.drawString ("Lifetime Score: " + Integer.toString(user.getHighScore()), 385, 383);
				g.drawString ("Rank " + Integer.toString(user.getRank()), 509, 299);
				g.drawString ("Total Helicopters Destroyed: " + Integer.toString(user.getHelis()), 315, 418);
				//459
				g.drawString ("Total Bullets Fired: " + Integer.toString(user.getBullets()), 353, 480);

				g.setFont(bigF);

				g.setFont(font);
			}

			else if (profile2 == true && profiles.getSize() > 1) {
				g.setFont(bigF);

				fm = g.getFontMetrics ();

				user = transverse(head,1).getProfile();

				currentUser = user.getName();

				rect = fm.getStringBounds (currentUser, g);

				textWidth = (int) (rect.getWidth ());

				g.drawString (currentUser, (250 - (465-373) - textWidth) / 2 + 465, 260);

				g.setFont(smallF);

				g.drawString ("Lifetime Score: " + Integer.toString(user.getHighScore()), 385, 383);
				g.drawString ("Rank " + Integer.toString(user.getRank()), 509, 299);
				g.drawString ("Total Helicopters Destroyed: " + Integer.toString(user.getHelis()), 315, 418);
				//459
				g.drawString ("Total Bullets Fired: " + Integer.toString(user.getBullets()), 353, 480);

				g.setFont(bigF);

				g.setFont(font);
			}

			else if (profile3 == true && profiles.getSize() > 2){
				g.setFont(bigF);

				fm = g.getFontMetrics ();

				user = transverse(head,2).getProfile();

				currentUser = user.getName();

				rect = fm.getStringBounds (currentUser, g);

				textWidth = (int) (rect.getWidth ());

				g.drawString (currentUser, (250 - (465-373)- textWidth) / 2 + 465, 260);

				g.setFont(smallF);

				g.drawString ("Lifetime Score: " + Integer.toString(user.getHighScore()), 385, 383);
				g.drawString ("Rank " + Integer.toString(user.getRank()), 509, 299);
				g.drawString ("Total Helicopters Destroyed: " + Integer.toString(user.getHelis()), 315, 418);
				//459
				g.drawString ("Total Bullets Fired: " + Integer.toString(user.getBullets()), 353, 480);

				g.setFont(bigF);

				g.setFont(font);
			}

			else if (profile4 == true && profiles.getSize() > 3){
				g.setFont(bigF);

				fm = g.getFontMetrics ();

				user = transverse(head,3).getProfile();

				currentUser = user.getName();

				rect = fm.getStringBounds (currentUser, g);

				textWidth = (int) (rect.getWidth ());

				g.drawString (currentUser, (250 - (465-373)- textWidth) / 2 + 465, 260);

				g.setFont(smallF);

				g.drawString ("Lifetime Score: " + Integer.toString(user.getHighScore()), 385, 383);
				g.drawString ("Rank " + Integer.toString(user.getRank()), 509, 299);
				g.drawString ("Total Helicopters Destroyed: " + Integer.toString(user.getHelis()), 315, 418);
				//459
				g.drawString ("Total Bullets Fired: " + Integer.toString(user.getBullets()), 353, 480);

				g.setFont(bigF);

				g.setFont(font);
			}

			else if (profile5 == true && profiles.getSize() > 4){
				g.setFont(bigF);

				fm = g.getFontMetrics ();

				user = transverse(head, 4).getProfile();

				currentUser = user.getName();

				rect = fm.getStringBounds (currentUser, g);

				textWidth = (int) (rect.getWidth ());

				g.drawString (currentUser, (250 - (465-373)- textWidth) / 2 + 465, 260);

				g.setFont(smallF);

				g.drawString ("Lifetime Score: " + Integer.toString(user.getHighScore()), 385, 383);
				g.drawString ("Rank " + Integer.toString(user.getRank()), 509, 299);
				g.drawString ("Total Helicopters Destroyed: " + Integer.toString(user.getHelis()), 315, 418);
				//459
				g.drawString ("Total Bullets Fired: " + Integer.toString(user.getBullets()), 353, 480);

				g.setFont(bigF);

				g.setFont(font);
			}

			else if (profile6 == true && profiles.getSize() > 5){
				g.setFont(bigF);

				fm = g.getFontMetrics ();

				user = transverse(head, 5).getProfile();

				currentUser = user.getName();

				rect = fm.getStringBounds (currentUser, g);

				textWidth = (int) (rect.getWidth ());

				g.drawString (currentUser, (250 - (465-373)- textWidth) / 2 + 465, 260);

				g.setFont(smallF);

				g.drawString ("Lifetime Score: " + Integer.toString(user.getHighScore()), 385, 383);
				g.drawString ("Rank " + Integer.toString(user.getRank()), 509, 299);
				g.drawString ("Total Helicopters Destroyed: " + Integer.toString(user.getHelis()), 315, 418);
				//459
				g.drawString ("Total Bullets Fired: " + Integer.toString(user.getBullets()), 353, 480);

				g.setFont(bigF);

				g.setFont(font);
			}

			if (user != null) {
				g.drawImage(emblemBack, 384,220, this);
				g.drawImage(emblems[user.getEmblem()], 384, 220, this);
			}

		}


		if (createProfileScreen == true)
		{
			g.drawImage (menu_BG, 0, 0, this);
			g.drawImage (menu_Title, 0, 0, this);

			g.drawImage (profileButton, 353, 190, this);

			g.drawImage (emblems [emblemCounter], 370, 240, this);

			if (rightFlash == true)
				g.drawImage (arrowRight2, 434, 335, this);
			else
				g.drawImage (arrowRight, 434, 335, this);

			if (leftFlash == true)
				g.drawImage (arrowLeft2, 385, 335, this);
			else
				g.drawImage (arrowLeft, 385, 335, this);

			g.setFont (bigF);
			g.setColor (Color.BLACK);
			g.drawString ("PROFILE BADGE", 373, 235);
			g.setColor (Color.lightGray);

			g.setFont (smallF);
			g.drawString ("YOUR NAME:", 474, 285);
			g.setFont (font);

			if (createFlash == true)
				g.drawImage (createButton2, 350, 410, this);
			else
				g.drawImage (createButton, 350, 410, this);
		}

		if (profileCreated == true)
		{
			g.drawImage (background, screenX, 0, this);

			g.drawImage (instructions, 260, 210, this);

			g.drawImage (keys1, 382, 385, this);
			g.drawImage (keys_right, 517, 428, this);
			g.drawImage (keys_left, 330, 428, this);
			g.drawImage (keys_up, 432, 325, this);

			g.setFont (bigF);
			g.setColor (Color.BLACK);

			g.drawString ("Instructions", 392, 260);

			g.setColor (Color.lightGray);
			g.setFont (smallF);

			g.drawString ("Move", 270, 438);
			g.drawString ("Left", 272, 465);
			g.drawString ("Move", 567, 438);
			g.drawString ("Right", 565, 465);
			g.drawString ("Jump", 419, 320);

			g.drawImage (mouse, 660, 315, this);
			g.drawString ("Shoot", 650, 445);

			if (closeFlash == true)
			{
				g.drawImage (close2, 700, 220, this);
			}
			else
			{
				g.drawImage (close, 700, 220, this);
			}

			g.setFont (font);
		}

		if (parachuteAnimation==true){
			g.drawImage (background, screenX, -240, this);
			g.drawImage (parachuter,paraX,paraY,this);
			paraX-=14;
			paraY+=15;
			//	System.out.println(paraY);
			if (paraY>=500){
				parachuteAnimation=false;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				paraX=500;
				paraY=-250;
				game_Start=true;
				phase1=true;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}




		if (game_Start == true)
		{
			g.drawImage (background, screenX, -240, this);

			if (charX<0){
				g.drawImage(arrowFWD,10,400,this);

			}

			g.drawImage(healthBar,10,0,this);
			if (currentProfile.getCurrentHealth()<=100&&currentProfile.getCurrentHealth()>66){
				g.setColor(fullHealth);
			}
			else if (currentProfile.getCurrentHealth()<=66&&currentProfile.getCurrentHealth()>33){
				g.setColor(Color.YELLOW);
			}
			else if (currentProfile.getCurrentHealth()<=33&&currentProfile.getCurrentHealth()>0){
				g.setColor(Color.RED);
			}
			g.fillRect(100, 30, (int)(currentProfile.getCurrentHealth()*2.5), 20);

			g.setColor(Color.RED);
			//g.fillRect(105,55,240df,12);

			g.drawImage(healthBarEmblem,10,0,this);
			g.drawImage(emblems[currentProfile.getEmblem()],10,0,this);

			g.drawImage(hudBar, 600,0,this);
			g.setColor(Color.WHITE);
			g.setFont(normal);
			g.drawString("x", 740, 40);
			if (current_Weapon.getType()==1){
				g.drawImage(infinite,760,30,this);
			}
			else{

				g.drawString(((Integer)(current_Weapon.getAmmo())).toString(),760,40);
			}
			g.drawString("Score:", 115, 83);
			g.drawString(((Integer)(currentProfile.getHighScore())).toString(), 160, 83);

			g.drawString("x", 740, 70);
			g.drawString(((Integer)(heliCount)).toString(),760,70);

			//System.out.println((int)(current_Weapon.getDelay()*1000)-(fireReady[current_Weapon.getType()-1]-lastFire[current_Weapon.getType()-1]));

			//delayBar=240-(int)((current_Weapon.getDelay()*1000)-(fireReady[current_Weapon.getType()-1]-lastFire[current_Weapon.getType()-1]));
			//if (delayBar>=240){
			//	delayBar=240;
			//}
			//
			//g.fillRect(105,55,delayBar,12);
			//fireReady[0]-lastFire[0]>=(int)(weapon.get(i).getDelay()*1000)

			if (helis.get (heliCount).getAlive () == true)
			{
				g.drawImage (helis.get (heliCount).getPic (), heliX, heliY, this);
				g.setColor (Color.red);
				g.fillRect (heliX - 5, heliY, 3, helis.get (heliCount).getHealth () * helis.get (heliCount).getMultiplier ());
				if ((explosion == false && heliX > 750) || (flyLeft == true))
				{
					crashLeft = true;
					crashRight = false;
				}
				else if ((explosion == false && heliX < 750) || (flyRight == true))
				{
					crashRight = true;
					crashLeft = false;
				}
			}
			else if (helis.get (heliCount).getAlive () == false)
			{
				if (nukeSound == true && current_Weapon.getType()==9) {
					nuke2.play();
					nukeSound = false;
				}

				g.drawImage (helis.get (heliCount).getPic (), heliX, heliY, this);


				if (crashRight == true)
				{
					heliX += 10;
				}
				else if (crashLeft == true)
				{
					heliX -= 10;
				}
				heliY += 15;

				if (heliX > 1000 || heliY > 650)
				{

					if (phase1 == true && heliCount < 3)
					{
						helis.add (new Helicopter (1, heli_Small,heli_Small_Left, 10, 20, heli_Destroyed_Small_Right, heli_Destroyed_Small_Left,122,65));
						heliCount += 1;
						heliX = -300;
						heliY = 100;

					}
					else if (phase2 == true && heliCount < 7)
					{
						helis.add (new Helicopter (1, heli_Small,heli_Small_Left, 10, 20, heli_Destroyed_Small_Right, heli_Destroyed_Small_Left,122,65));
						heliCount += 1;
						heliX = -300;
						heliY = 100;
					}
					else if (phase3 == true && heliCount < 12)
					{
						helis.add (new Helicopter (1, heli_Small,heli_Small_Left, 10, 20, heli_Destroyed_Small_Right, heli_Destroyed_Small_Left,122,65));
						heliCount += 1;
						heliX = -300;
						heliY = 100;
					}
					if (heliCount>=12){

						levelUnlocked=true;


						Random randomGenerator = new Random();
						int randomInt=1+(randomGenerator.nextInt(2));

						if (randomInt==1){
							helis.add (new Helicopter (1, heli_Small,heli_Small_Left, 10, 20, heli_Destroyed_Small_Right, heli_Destroyed_Small_Left,122,65));
							heliCount += 1;
							heliX = -300;
							heliY = 100;
						}
						else if (randomInt==2){
							helis.add (new Helicopter (2, heli_Big,heli_Big_Left, 5, 80, heli_Destroyed_Big_Right, heli_Destroyed_Big_Left,215,95));
							heliCount += 1;
							heliX = -300;
							heliY = 100;
						}

					}

				}

			}

			g.drawImage (pos, charX, charY, this);
			opp = charY + 15 - ypos;
			adj = charX + 10 - xpos;
			hyp = Math.sqrt ((adj * adj) + (opp * opp));
			if (adj < 0)
			{
				armAngle = Math.atan (opp / adj);
			}
			else if (opp != 0 && hyp != 0 && adj != 0)
			{
				armAngle = Math.asin (opp / hyp);
				armAngle = Math.PI + armAngle;
			}
			else
			{
				armAngle = 0.0;
			}
			if (adj == 0 && opp != 0)
			{
				armAngle = -(3 * Math.PI) / 2;
			}

			if (explosion == true)
			{

				g.drawImage (heli_Explode [explodeCount], explosionX, explosionY, this);
				if (explodeCount < 16)
				{
					explodeCount += 1;
				}
				if (explodeCount == 16)
				{
					explodeCount = 0;
					explosion = false;
				}

			}

			heliOpp = heliY - charY - 25;
			heliAdj = heliX + 60 - charX + 20;
			heliHyp = Math.sqrt ((heliAdj * heliAdj) + (heliOpp * heliOpp));
			if (heliAdj < 0)
			{
				heliBulletAngle = Math.atan (heliOpp / heliAdj);
			}
			else if (heliOpp != 0 && heliHyp != 0 && heliAdj != 0)
			{
				heliBulletAngle = Math.asin (heliOpp / heliHyp);
				heliBulletAngle = Math.PI + heliBulletAngle;
			}
			else
			{
				heliBulletAngle = 0.0;
			}
			if (heliAdj == 0 && heliOpp != 0)
			{
				heliBulletAngle = -(3 * Math.PI) / 2;
			}


			if (heliBullets.size () > 0)
			{

				for (int i = 0 ; i < heliBullets.size () ; i++)
				{

					if (heliSlope.get(i)>1.0||heliSlope.get(i)<-1.0){
						helixPos.set(i, (double)(((1/heliSlope.get(i)))*(heliBulletDetectY.get(i))));
						heliBulletDetectY.set(i,heliBulletDetectY.get(i)+10);

						heliBulletDetectX.set(i,(int)(Math.round(helixPos.get(i)))+heliOriginX.get(i));

					}
					else if (heliSlope.get (i) <= 1.0||heliSlope.get(i)>=-1.0)
					{
						heliyPos.set (i, ((double) (-(heliSlope.get (i)) * (heliBulletDetectX.get (i) - heliOriginX.get (i)))));
						heliBulletDetectY.set (i, heliOriginY.get (i) - ((int) (Math.round (heliyPos.get (i)))));


						if (heliOriginX.get(i) > charX)
						{
							heliBulletDetectX.set (i, heliBulletDetectX.get (i) - 10);
						}
						else if (heliOriginX.get(i) < charX)
						{
							heliBulletDetectX.set (i, heliBulletDetectX.get (i) + 10);
						}
					}


					if (heliBulletDetectX.get (i) >= 1000 || heliBulletDetectY.get (i) > 650)
					{
						heliBullets.get (i).setActive (false);
					}
					if (heliBullets.get (i).getActive () == true)
					{
						g.drawImage (heliBullets.get (i).getPic (), heliBulletDetectX.get (i), heliBulletDetectY.get (i), this);
						if (heliBulletDetectX.get (i) > charX && heliBulletDetectX.get (i) < charX + 40 && heliBulletDetectY.get (i) > charY && heliBulletDetectY.get (i) < charY + 50)
						{
							heliBullets.get (i).setActive (false);
							currentProfile.healthDamage(10*(helis.get(heliCount).getType()));

						}
					}

				}
			}


			//**************
			if (dead==false&&deathAnimation==false){
				newXform.rotate (armAngle, charX + 15, charY + 23);
				g2d.setTransform (newXform);

				g.drawImage (current_Weapon.getPic (), charX - 17, charY - 15, this);

				newXform.rotate (-armAngle, charX + 15, charY + 23);
				g2d.setTransform (newXform);
				//**************
			}
			if (drawBullet == true)
			{


				for (int i = 0 ; i < numBullet ; i++)
				{

					if (xpress != 0 && ypress != 0)
					{

						if (slope.get (i) > 1.0)
						{

							xPos.set (i, (double) (originX.get (i) + ((1 / slope.get (i)) * (double) ((originY.get (i) - bulletDetectY.get (i))))));
							bulletDetectX.set (i, (int) (Math.round (xPos.get (i))));
							if (slope.get (i) < 1.6)
							{
								bulletDetectY.set (i, bulletDetectY.get (i) - 17);
							}
							else
							{
								bulletDetectY.set (i, bulletDetectY.get (i) - 20);
							}
						}
						if (slope.get (i) <= 1.0 && slope.get (i) >= -1.0 && xpress > charX)
						{

							yPos.set (i, ((double) (slope.get (i) * (bulletDetectX.get (i) - originX.get (i)))));
							bulletDetectY.set (i, originY.get (i) - ((int) (Math.round (yPos.get (i)))));

							if (slope.get (i) > 0.43)
							{
								bulletDetectX.set (i, bulletDetectX.get (i) + 18);
							}
							else
							{
								bulletDetectX.set (i, bulletDetectX.get (i) + 20);
							}

						}

						if (slope.get (i) < -1.0 && xpress < charX && ypress < charY)
						{

							xPos.set (i, (double) (originX.get (i) + ((1 / slope.get (i)) * (double) ((originY.get (i) - bulletDetectY.get (i))))));
							bulletDetectX.set (i, (int) (Math.round (xPos.get (i))));
							if (slope.get (i) < -1.6)
							{
								bulletDetectY.set (i, bulletDetectY.get (i) - 20);
							}
							else if (slope.get (i) >= -1.7)
							{
								bulletDetectY.set (i, bulletDetectY.get (i) - 17);
							}


						}
						if (slope.get (i) > -1.0 && xpress < charX && ypress < charY)
						{
							xPos.set (i, (double) (originX.get (i) + ((1 / slope.get (i)) * (double) ((originY.get (i) - bulletDetectY.get (i))))));
							bulletDetectX.set (i, (int) (Math.round (xPos.get (i))));
							bulletDetectX.set (i, bulletDetectX.get (i) - 20);
						}


						//g.drawLine(originX.get(i),originY.get(i),bulletDetectX.get(i),bulletDetectY.get(i));

					}
					if (bullets.get (i).getActive () == true)
					{
						if (bullets.get (i).getType () != 3 && bullets.get (i).getType () != 9)
						{
							xCounters.set (i, xCounters.get (i) + 20);

						}

						if (bullets.get (i).getType () == 3)
						{
							//bullets.get(i).setY(bullets.get(i).getY()-20);
							bullets.get (i).setPic (bullet_3 [bullet3_Count.get (i)]);
							bullet3_Count.set (i, bullet3_Count.get (i) + 1);
							if (bullet3_Count.get (i) >= 11)
							{
								bullet3_Count.set (i, 11);
								bullets.get (i).setActive (false);
							}

						}


						if (bullets.get (i).getType () == 9 && nukeAnimation == false)
						{
							nukeAnimation = true;
						}



						newXform2.rotate (bullets.get (i).getAngle (), bullets.get (i).getxAnchor (), bullets.get (i).getyAnchor ());
						g2d.setTransform (newXform2);
						if (bullets.get (i).getType () != 9)
						{
							g.drawImage (bullets.get (i).getPic (), bullets.get (i).getX () + xCounters.get (i), bullets.get (i).getY (), this);
						}
						newXform2.rotate (-bullets.get (i).getAngle (), bullets.get (i).getxAnchor (), bullets.get (i).getyAnchor ());
						g2d.setTransform (newXform2);

						//                                      if (bullets.get(i).getActive()==true){
						//                                              g.drawImage(bullets.get(i).getPic(),bulletDetectX.get(i),bulletDetectY.get(i),this);
						//                                      }
					}
				}
				try
				{
					Thread.sleep (50);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace ();
				}
			}


			if (nukeAnimation == true)
			{
				if (nukeY < 600)
				{
					g.drawImage (bullet_9, 450, nukeY, this);
					nukeY += 10;
				}
				else if (nukeY >= 600)
				{
					g.drawImage (nukeFlash [flashCount], 0, 0, this);
					flashCount += 1;
					try
					{
						Thread.sleep (50);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace ();
					}
					if (flashCount == 11)
					{
						//background=map_Destroyed;
						nukeFinished = true;
					}
					if (flashCount == 17)
					{
						nukeY = -100;
						flashCount = 0;
						nukeAnimation = false;
					}

				}
			}



			for (int i = 0 ; i < numBullet ; i++)
			{
				if (bullets.get (i).getType () != 3 && bullets.get (i).getType () != 9)
				{
					if (bulletDetectX.get (i) > heliX && bulletDetectX.get (i) < heliX + helis.get(heliCount).getWidth() && bulletDetectY.get (i) > 100 && bulletDetectY.get (i) < (100+helis.get(heliCount).getHeight()) && helis.get (heliCount).getAlive () == true)
					{
						if (bullets.get (i).getActive () == true)
						{
							bullets.get (i).setActive (false);
							helis.get (heliCount).hit_Damage (current_Weapon.getDMG ());
							if (helis.get (heliCount).getHealth () <= 0)
							{

								flyLeft = false;
								flyRight = false;
								if (helis.get(heliCount).getAlive()==true){
									explosion = true;
									addScore(getCurrentProfile(),helis.get(heliCount).getPoints());

									weaponPickup=dropPackage();
									if (weaponPickup!=null){
										weaponDropX=heliX;
										weaponDropY=100;
										weaponFallen=false;
									}


								}
								explosionX = heliX;
								explosionY = heliY - 50;
								helis.get (heliCount).heli_Death (crashRight);

							}
						}
					}
				}
				if (bullets.get (i).getType () == 9)
				{
					if (nukeFinished == true)
					{
						if (bullets.get (i).getActive () == true)
						{
							bullets.get (i).setActive (false);
							helis.get (heliCount).hit_Damage (current_Weapon.getDMG ());
							if (helis.get (heliCount).getHealth () <= 0)
							{
								if (helis.get(heliCount).getAlive()==true){
									explosion = true;
									nukeFinished=false;
									addScore(getCurrentProfile(),helis.get(heliCount).getPoints());

									weaponPickup=dropPackage();
									if (weaponPickup!=null){
										weaponDropX=heliX;
										weaponDropY=100;
										weaponFallen=false;
									}

								}
								helis.get (heliCount).heli_Death (crashRight);
								flyLeft = false;
								flyRight = false;

								explosionX = heliX;
								explosionY = heliY - 50;

							}
						}
					}
				}


				if (bullets.get (i).getType () == 3 && bullets.get (i).getActive () == true)
				{

					for (int x = 0 ; x < 1500 ; x++)
					{
						yPos.set (i, Math.abs (((double) (slope.get (i) * (bulletDetectX.get (i) - originX.get (i))))));
						bulletDetectY.set (i, originY.get (i) - ((int) (Math.round (yPos.get (i)))));

						bulletDetectX.set (i, bulletDetectX.get (i) + 1);
						if ((originY.get (i) - ((int) (Math.round (yPos.get (i))))) >= 100 && (originY.get (i) - ((int) (Math.round (yPos.get (i))))) <= 165)
						{

							if (bulletDetectX.get (i) >= heliX && bulletDetectX.get (i) <= heliX + helis.get(heliCount).getWidth())
							{

								if (bullets.get (i).getActive () == true)
								{
									bullets.get (i).setActive (false);
									nukeFinished = false;
									helis.get (heliCount).hit_Damage (current_Weapon.getDMG ());
									if (helis.get (heliCount).getHealth () <= 0)
									{
										if (helis.get(heliCount).getAlive()==true){
											explosion = true;
											addScore(getCurrentProfile(),helis.get(heliCount).getPoints());

											weaponPickup=dropPackage();
											if (weaponPickup!=null){
												weaponFallen=false;
												weaponDropX=heliX;
												weaponDropY=100;
											}


										}
										helis.get (heliCount).heli_Death (crashRight);
										flyLeft = false;
										flyRight = false;

										explosionX = heliX;
										explosionY = heliY - 50;

									}
								}
							}
						}
					}
				}
			}

			if (weaponFall==true||weaponFallen==true){
				g.drawImage(dropBox,weaponDropX,weaponDropY,this);

			}
			if (deathAnimation==true&&dead==false){
				pos=deathImages[deathCount];
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				deathCount+=1;

				if (deathCount>=7){
					deathCount=7;
					dead=true;
					deathAnimation=false;
				}
			}

			if (deathScreen==true){

				g.drawImage (gameOverText,0,-150,this);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gameOverBGCount+=1;
				if (gameOverBGCount>=280){

					deathScreen=false;
				}

			}

			if (heliCount == 3 && helis.get (heliCount).getAlive () == false && phase1 == true&&explosion==false&&transition==false)
			{
				g.drawImage(arrowFWD, 900,400,this);
			}
			if (heliCount == 7 && helis.get (heliCount).getAlive () == false && phase2 == true&&explosion==false&&transition==false)
			{
				g.drawImage(arrowFWD, 900,400,this);
			}


		}
	}

	public Guns dropPackage(){

		Random randomGenerator = new Random();

		Guns dropGun=null;

		int randomInt = randomGenerator.nextInt(400);


		Guns gun2 = new Guns (2, 89, 50, 20, guns.get (1), 30,5);
		Guns gun3 = new Guns (3, 89, 50, 1000, guns.get (2), 3,10);
		Guns gun4 = new Guns (4, 89, 50, 1, guns.get (3), 250,0.1);
		Guns gun5 = new Guns (5, 89, 50, 50, guns.get (4), 10,4);
		Guns gun6 = new Guns (6, 89, 50, 20, guns.get (5), 20,3);
		Guns gun7 = new Guns (7, 89, 50, 5, guns.get (6), 100,0.3);
		Guns gun8 = new Guns (8, 89, 50, 100, guns.get (7), 5,7);
		Guns gun9 = new Guns (9, 89, 50, 9999, guns.get (8), 1,20);
		Guns gun10 = new Guns (10, 89, 50, 40, guns.get (9), 15,4);

		if (heliX>0&&heliX<1000&&(phase1==true&&(heliX<580||heliX>715))||(phase3==true&&(heliX<175||(heliX>505&&heliX<760)))||phase2==true){
			if (randomInt>=0&&randomInt<20){
				dropGun=new Guns (2, 89, 50, 20, guns.get (1), 30,5);
				dropBox=weap2Box;
			}
			else if (randomInt>=20&&randomInt<30){
				dropGun=new Guns (3, 89, 50, 1000, guns.get (2), 3,10);
				dropBox=weap3Box;
			}
			else if (randomInt>=30&&randomInt<50){
				dropGun=new Guns (4, 89, 50, 1, guns.get (3), 250,0.1);
				dropBox=weap4Box;
			}
			else if (randomInt>=70&&randomInt<95){
				dropGun=new Guns (5, 89, 50, 50, guns.get (4), 10,4);
				dropBox=weap5Box;
			}
			else if (randomInt>=95&&randomInt<115){
				dropGun=new Guns (6, 89, 50, 20, guns.get (5), 20,3);
				dropBox=weap6Box;
			}
			else if (randomInt>=115&&randomInt<145){
				dropGun=new Guns (7, 89, 50, 5, guns.get (6), 100,0.3);
				dropBox=weap7Box;
			}
			else if (randomInt>=140&&randomInt<155){
				dropGun=new Guns (8, 89, 50, 100, guns.get (7), 5,7);
				dropBox=weap8Box;
			}
			else if (randomInt>=155&&randomInt<160){
				dropGun= new Guns (9, 89, 50, 9999, guns.get (8), 1,20);
				dropBox=weap9Box;
			}
			else if (randomInt>=160&&randomInt<180){
				dropGun= new Guns (10, 89, 50, 40, guns.get (9), 15,4);
				dropBox=weap10Box;
			}
		}
		return dropGun;
	}




	public void addScore (Profile p, int scoreAdded){

		p.addScore(scoreAdded);
		p.addHelis();
		p.addBullets(bulletsFired);

		try { //Try and Catch
			ObjectOutputStream output = new ObjectOutputStream (
					new FileOutputStream ("profiles.txt"));
			output.writeObject (profiles);
			output.close (); //Closes it
		}
		catch (Exception e)
		{

		}
	}



	public Profile getCurrentProfile(){

		Profile p = null;

		if(profile1 == true) {

			Node head = profiles.listProfiles();

			p = transverse(head, 0).getProfile();

		}
		if(profile2 == true) {

			Node head = profiles.listProfiles();

			p = transverse(head, 1).getProfile();

		}
		if(profile3 == true) {

			Node head = profiles.listProfiles();

			p = transverse(head, 2).getProfile();

		}
		if(profile4 == true) {

			Node head = profiles.listProfiles();

			p = transverse(head, 3).getProfile();

		}
		if(profile5 == true) {

			Node head = profiles.listProfiles();

			p = transverse(head, 4).getProfile();

		}
		if(profile6 == true) {

			Node head = profiles.listProfiles();

			p = transverse(head, 5).getProfile();

		}

		return p;
	}


	public void mouseMoved (MouseEvent me)  //Method to obtain the position of the mouse
	{

		xpos = me.getX ();
		ypos = me.getY ();

	}


	public void keyPressed (KeyEvent key)
	{
		// TODO Auto-generated method stub

		keyPressed = key.getKeyCode ();

	}


	public void keyReleased (KeyEvent key)
	{
		// TODO Auto-generated method stub
		keyPressed = 0;
		if (key.getKeyCode () == 68)
		{

			runRight = false;
			if (jumping == false)
			{
				standing = true;
			}
		}
		if (key.getKeyCode () == 65)
		{
			runLeft = false;
			if (jumping == false)
			{
				standing = true;
			}
		}

	}


	public void keyTyped (KeyEvent key)
	{
		// TODO Auto-generated method stub

	}


	@ Override
	public void mouseDragged (MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}


	@ Override
	public void mouseClicked (MouseEvent me)
	{


	}


	@ Override
	public void mouseEntered (MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}


	@ Override
	public void mouseExited (MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}


	@ Override
	public void mousePressed (MouseEvent me)
	{
		xpress = me.getX ();
		ypress = me.getY ();

		if (game_Start == true)
		{

			bulletDetectX.add (charX + 45);
			bulletDetectY.add (charY + 15);

			xCounter = 0;
			bullet3_Count.add (0);
			if (current_Weapon.getAmmo()>0){
				if (current_Weapon.getType () == 1)
				{
					if (fireAvailable[0]==true){
						//pistol.play();
						bulletsFired++;
						bullet1 = new Bullet (1, armAngle, charX + 15, charY + 23, 8, 8, charX + 45, charY + 15, bullet_1);
						fireAvailable[0]=false;
						lastFire[0]=System.currentTimeMillis ();
					}

				}
				else if (current_Weapon.getType () == 2)
				{
					if (fireAvailable[1]==true){
						bulletsFired++;
						sniper.play();
						bullet1 = new Bullet (2, armAngle, charX + 15, charY + 23, 8, 8, charX + 55, charY + 15, bullet_2);
						fireAvailable[1]=false;
						lastFire[1]=System.currentTimeMillis ();
					}
				}
				else if (current_Weapon.getType () == 3)
				{
					if (fireAvailable[2]==true){
						lazer.play();
						bulletsFired++;
						bullet1 = new Bullet (3, armAngle, charX + 15, charY + 23, 8, 8, charX + 60, charY - 10, bullet_3 [0]);
						fireAvailable[2]=false;
						lastFire[2]=System.currentTimeMillis ();
					}

				}
				else if (current_Weapon.getType () == 4)
				{
					if (fireAvailable[3]==true){
						bulletsFired++;
						//System.out.println("4");
						if (counter == 3)
							counter = 0;
						else if (counter == 0) {
							chaingun.play();
							counter++;
						}
						else 
							counter++;
						bullet1 = new Bullet (4, armAngle, charX + 15, charY + 23, 8, 8, charX + 45, charY + 20, bullet_4);
						fireAvailable[3]=false;
						lastFire[3]=System.currentTimeMillis ();
					}
				}
				else if (current_Weapon.getType () == 5)
				{
					if (fireAvailable[4]==true){
						bulletsFired++;
						rocket.play();
						bullet1 = new Bullet (5, armAngle, charX + 15, charY + 23, 8, 8, charX + 45, charY + 10, bullet_5);
						fireAvailable[4]=false;
						lastFire[4]=System.currentTimeMillis ();
					}
				}
				else if (current_Weapon.getType () == 6)
				{
					if (fireAvailable[5]==true){
						bulletsFired++;
						rocket.play();
						bullet1 = new Bullet (6, armAngle, charX + 15, charY + 23, 8, 8, charX + 45, charY + 20, bullet_6);
						fireAvailable[5]=false;
						lastFire[5]=System.currentTimeMillis ();
					}
				}
				else if (current_Weapon.getType () == 7)
				{
					if (fireAvailable[6]==true){
						bulletsFired++;
						pistol.play();
						bullet1 = new Bullet (7, armAngle, charX + 15, charY + 23, 8, 8, charX + 45, charY + 20, bullet_7);
						fireAvailable[6]=false;
						lastFire[6]=System.currentTimeMillis ();
					}
				}
				else if (current_Weapon.getType () == 8)
				{
					if (fireAvailable[7]==true){
						bulletsFired++;
						rocket.play();
						bullet1 = new Bullet (8, armAngle, charX + 15, charY + 23, 8, 8, charX + 45, charY - 5, bullet_8);
						fireAvailable[7]=false;
						lastFire[7]=System.currentTimeMillis ();
					}
				}
				else if (current_Weapon.getType () == 9)
				{
					if (fireAvailable[8]==true){
						bulletsFired++;
						nuke1.play();
						nukeSound = true;
						bullet1 = new Bullet (9, armAngle, charX + 15, charY + 23, 8, 8, charX + 45, charY + 15, bullet_9);
						fireAvailable[8]=false;
						lastFire[8]=System.currentTimeMillis ();
					}
				}
				else if (current_Weapon.getType () == 10)
				{
					if (fireAvailable[9]==true){
						bulletsFired++;
						//System.out.println("10");
						rocket.play();
						bullet1 = new Bullet (10, armAngle, charX + 15, charY + 23, 8, 8, charX + 45, charY + 15, bullet_10);
						fireAvailable[9]=false;
						lastFire[9]=System.currentTimeMillis ();
					}
				}


				if (bullet1!=null){
					shoot = true;
					bullets.add (bullet1);
					bullet1=null;
					current_Weapon.shotFired();
					numBullet = bullets.size ();
					xCounters.add (xCounter);
					clickCount += 1;
					slope.add (((double) ((charY + 25) - ypress)) / ((double) (xpress - (charX + 15))));

					xPos.add ((double) charX + 15);
					yPos.add ((double) charY + 25);
					bulletDetectX.add (charX + 15);
					bulletDetectY.add (charY + 25);
					originX.add (charX + 15);
					originY.add (charY + 25);
				}



			}

		}
		//      bulletDetectY=500;

	}


	@ Override
	public void mouseReleased (MouseEvent arg0)
	{
		if (jumping == false)
		{
			standing = true;
		}

		shoot = false;
	}
}