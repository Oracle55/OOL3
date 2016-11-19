/**
 *
 *@author Moses Ilunga - (Will add what I did)
 *@author Ben Delzer - 
 *@author Christian Hansen -
 *@author Brock -
 *@author Tim Dusek -
 */

import java.util.*;
public class Athlete
{
  private static int nullified = 0;
  private static int vacated = 0;
  private static int awarded = 0;
  private static int count = 0;
  private int number = 1;
  //I'm using an enum as the type prefered venue.  You don't have to.  You can use a String.
  //The benifit of enum is that only valid Strings will be allowed.
  //So I don't have to check for:
  //--Bad inputs
  //--Different Capitalizations
  private Venue.VenueType fav;
  private int stam = 100;
  private int skill = 5;
  private boolean cheater = false;
  private int endorsements = 0;
  private int rest = 0;
  //Using an array since I want exactly 3 boxes for medals
  //0 -> Gold
  //1 -> Silver
  //2 -> Bronze
  private int[] medals = { 0, 0, 0};
  private int score = 0;
  
  public static void vacated()
  {
    vacated++;
  }
  
  public static int getAwarded()
  {
    return awarded;
  }
  
  public static int getVacant()
  {
    return vacated;
  }
  
  public static int getNulls()
  {
    return nullified;
  }
  
  public void setRest(int input)
  {
    if (input > 0)
      rest = input;
  }
  
  public void rest(Venue.VenueType input)
  {
    Random rng = new Random();
    if (rest > 0 && input.equals(getFav()))
      rest-=rng.nextInt(3)+3;
    else 
      rest--;
    
    if (rest < 0)
      rest = 0;
  }
  
  public int mustRest()
  {
    return rest;
  }
  
  public int getScore()
  {
    return score;
  }
  
  /**
   * setScore takes itself and adds the inputed amount to have a running total of the Athletes score used for multistep Event's
   * 
   */
  public void setScore(int input)
  {
    score = score + input;
  }
  
  public int getEndorsements()
  {
    return endorsements;
  }
  
  public void addEndorsements(int input)
  {
    if (input + getEndorsements() >=0)
      endorsements += input;
  }
  
  public boolean getCheater()
  {
    return cheater;
  }
  
  public void setCheater(boolean input)
  {
    cheater = input;
  }
  
  public boolean equals(Athlete other)
  {
    return (getNumber() == other.getNumber());
  }
  
  public int compareTo(Athlete other)
  {
    return getSkill() - other.getSkill();
  }
  
  public String toString()
  {
    return "Athlete " + getNumber() + ":\n\tWilling to Cheat:\t"+getCheater()+"\n\tEvents Must Rest:\t"+mustRest()+"\n\tPrefers:\t"+getFav()+" venues\n\tCurrent Stamina:\t"+getStam()+"\n\tSkill Level: " +getSkill()+"\n\tGold Medals:\t"+medals[0]+"\n\tSilver Medals:\t"+medals[1]+"\n\tBronze Medals:\t"+medals[2]+"\n\tEndorsements:\t"+getEndorsements();
  }
  
  public Athlete(Venue.VenueType inFav, int inStam, int inSkill, boolean inCheat)
  {
    setNumber();
    setFav(inFav);
    setStam(inStam);
    setSkill(inSkill);
    setCheater(inCheat);
  }
  
  public void setNumber()
  {
    number = ++count;
  }
  
  public int getNumber()
  {
    return number;
  }
  
  public void setFav(Venue.VenueType inFav)
  {
    fav = inFav;
  }
  
  public Venue.VenueType getFav()
  {
    return fav;
  }
  
  private void setStam(int inStam)
  {
    if (inStam > 100)
    {
      inStam = 100;
    }
    else if (inStam < 0)
    {
      inStam = 0;
    }
    stam = inStam;
  }
  
  public void changeStam(int adjust)throws AthleteFaintedException
  {
    if (adjust + stam > 100)
    {
      System.out.println("Athlete " + getNumber() + " is at full Stamina and wasted " + (adjust + stam - 100) + " units of recovered Stamina in the process.");
      stam = 100;
    }
    else if (adjust + stam <= 0)
    {
      stam = 0;
      setRest(3);
      throw new AthleteFaintedException();
    }
    else
    {
      stam += adjust;
    }
  }
  
  public int getStam()
  {
    return stam;
  }
  
  private void setSkill(int inSkill)
  {
    if (inSkill >= 0 && inSkill <= 10)
    {
      skill = inSkill;
    }
  }
  
  public int getSkill()
  {
    return skill;
  }
  
  private void setMedals(int[] inMedals)
  {
    if (inMedals.length == 3 && inMedals[0] >= 0 && inMedals[1] >= 0 && inMedals[2] >= 0)
    {
      medals = inMedals;
    }
  }
  
  public void addMedal(int rank)
  {
    if (rank < 4 && rank > 0)
    {
      medals[rank-1]++;
      awarded++;
    }
  }
  
  public int[] getMedals()
  {
    return medals;
  }
  
  public void strip()
  {
    nullified += medals[0] + medals[1] + medals[2];
    awarded -= medals[0] + medals[1] + medals[2];
    medals[0] = 0;
    medals[1] = 0;
    medals[2] = 0;
    endorsements =0;
    System.out.println("\n\n\nHELLO EVERYONE!!!  IT TURNS OUT THAT #" +getNumber()+" IS A CHEATER!!!\n\n\n");
  }
}
