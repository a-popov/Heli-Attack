import java.io.File;
import java.io.Serializable;

public class Profile implements Serializable
{
	private String profileName;
	private int highscore;
	private int rank;
	private int deaths;
	private int helisDestroyed;
	private int bulletsFired;
	private double ratio;
	private int emblem;
	private int maxHealth;
	private int currentHealth;

	public Profile (String name, int emb)
	{
		profileName = name;
		emblem = emb;
		maxHealth=100;
		currentHealth=maxHealth;
	}


	public String getName ()
	{
		return profileName;
	}
	public int getCurrentHealth(){
		return currentHealth;
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public void healthDamage(int dmg){
		currentHealth-=dmg;
	}

	public void addScore (int score){
		highscore+=score;
	}

	public void setHighScore(int high)
	{
		highscore = high;
	}



	public void setHelis(int helis)
	{
		helisDestroyed = helis;
	}

	public void setBullets(int bullets)
	{
		bulletsFired = bullets;
	}

	public int getHighScore ()
	{
		return highscore;
	}


	public int getHelis ()
	{
		return helisDestroyed;
	}

	public int getBullets() 
	{
		return bulletsFired;
	}

	public int getEmblem()
	{
		return emblem;
	}

	public void addHelis()



	{



		helisDestroyed++;



	}




	public void addBullets(int bullets)



	{



		bulletsFired += bullets;



	}




	public void addDeath() 



	{



		deaths++;



	}
	public int getDeaths() 



	{



		return deaths;



	}



	public int getRank() 



	{



		if (highscore <= 100) {



			return 1;



		}



		else if (highscore <= 300) {



			return 2;



		} 



		else if (highscore <= 600) {



			return 3;



		} 



		else if (highscore <= 1000) {



			return 4;



		} 



		else if (highscore <= 1500) {



			return 5;



		} 



		else if (highscore <= 2100) {



			return 6;



		} 



		else if (highscore <= 2800) {



			return 7;



		} 



		else if (highscore <= 4600) {



			return 8;



		} 



		else if (highscore <= 5500) {



			return 9;



		} 



		else if (highscore <= 6500) {



			return 10;



		} 



		else if (highscore <= 7600) {



			return 11;



		} 



		else if (highscore <= 8700) {



			return 12;



		} 



		else if (highscore <= 9900) {



			return 13;



		} 



		else if (highscore <= 12000) {



			return 14;



		} 



		else if (highscore <= 13400) {



			return 15;



		} 



		else if (highscore <= 14900) {



			return 16;



		} 



		else if (highscore <= 16500) {



			return 17;



		} 



		else if (highscore <= 18200) {



			return 18;



		} 



		else if (highscore <= 20000) {



			return 19;



		} 



		else



			return 99;



	}
}
