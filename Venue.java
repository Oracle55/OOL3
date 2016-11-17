/**
 *
 *@author Moses Ilunga - (Will add what I did)
 *@author Ben Delzer - 
 *@author Christian Hansen -
 *@author Brock -
 *@author Tim Dusek -
 */
import java.util.*;
public class Venue
{
  public enum VenueType {AQUATIC, TRACK, GYM, OUTDOOR}
  
  private String name = "";
  private VenueType type;
  private int maxA = 3;
  private int maxFans = 0;
  private ArrayList<Fan> fans = new ArrayList<Fan>();
  
  public int compareTo(Venue other)
  {
    return (((Integer)getMaxFans()).compareTo(other.getMaxFans()));
  }
  
  public String toString()
  {
    return "Venue "+getName()+":\n\tVenueType:\t"+getType()+"\n\tMax Athletes:\t"+getMaxAths()+"\n\tMaxFans:\t"+getMaxFans()+"\n\tCurrent Fans:\t"+getCurrFans();
  }
  
  //Venues probably don't have fans when created, so left that attribute out.
  public Venue(String inName, VenueType inType, int inMaxA, int inMaxFans)
  {
    setName(inName);
    setType(inType);
    setMaxAths(inMaxA);
    setMaxFans(inMaxFans);
  }
  
  public void setName(String inName)
  {
    name = inName;
  }
  
  public String getName()
  {
    return name;
  }
  
  //I suppose a venue could be remodelled, so leaving this public
  public void setType(VenueType inType)
  {
    type = inType;
  }
  
  public VenueType getType()
  {
    return type;
  }
  
  public void setMaxAths(int inMax)
  {
    if (inMax >= 1)
    {
      maxA = inMax;
    }
  }
  
  public int getMaxAths()
  {
    return maxA;
  }
  
  public void setMaxFans(int inMax)
  {
    //Check that the new max won't kill off any fans currently in the Venue
    if (inMax >= getCurrFans())
    {
      maxFans = inMax;
    }
  }
  
  public int getMaxFans()
  {
    return maxFans;
  }
  
  /**
   * addFan allows a fan to enter the Venue, but if the current number of fans already acceeds the limit of fan aloud in the Venue then an exception is thrown and the fan is rejected from entering the Veneu.
   * 
   * @throws TooManyFansException
   */
  public void addFan(Fan inFan) throws TooManyFansException
  {
    if (getCurrFans() >= maxFans)
    {
      inFan.reject();
      throw new TooManyFansException();
    }
    else
      fans.add(inFan);
  }
  
  public Fan removeFan()
  {
    if (getCurrFans() <= 0)
    {
      System.out.println("More fans trying to leave the Venue than were actually inside the Venue!");
      return null;
    }
    else
    {
      return fans.remove(0);
    }
  }
  
  public int getCurrFans()
  {
    return fans.size();
  }
}
