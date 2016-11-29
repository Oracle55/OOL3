/**
 *
 *@author Moses Ilunga - Manual Sorting
 *@author Ben Delzer - 
 *@author Christian Hansen -
 *@author Tim Dusek -
 */
import java.util.*;
public class Venue implements Comparable<Venue>
{
    public enum VenueType {AQUATIC, TRACK, GYM, OUTDOOR}
    
    private String name = "";
    private VenueType type;
    private int maxA = 3;
    private int maxFans = 0;
    private ArrayList<Fan> fans = new ArrayList<Fan>();


    /**
     *compareTo takes in a Venue and compares the max fans of the two Venues so see which can hold more people
     *
     *@param other This is the venue being compared to the current one
     */
    public int compareTo(Venue other)
    {
 if(this.getMaxFans() > other.getMaxFans())
     {
  return 1;
     }
 else if(this.getMaxFans() < other.getMaxFans())
     {
  return -1;
     }
 else
     {
  return 0;
     }
    }

    /**
     *The toString method gives some information about the venue
     *
     *@return String This is some information about the venue
     */
    public String toString()
    {
 return "Venue "+getName()+":\n\tVenueType:\t"+getType()+"\n\tMax Athletes:\t"+getMaxAths()+"\n\tMaxFans:\t"+getMaxFans();
    }

    /**
     *This constructor takes inputs and assigns their values to the corresponding variables
     *
     *@param inName This is the name of the venue
     *@param inType This is the venue's type
     *@param inMaxA This is the maximum number of athletes that can be in the venue
     *@param inMaxFans This is the maximum number of fans that can be in the venue
     */
    //Venues probably don't have fans when created, so left that attribute out.
    public Venue(String inName, VenueType inType, int inMaxA, int inMaxFans)
    {
 setName(inName);
 setType(inType);
 setMaxAths(inMaxA);
 setMaxFans(inMaxFans);
    }

    /**
     *The setName method sets the name of the venue to the input
     *
     *@param inName This is the venue's name
     */
    public void setName(String inName)
    {
 name = inName;
    }

    /**
     *The getName method returns the name of the venue
     *
     *@return String This is the venue's name
     */
    public String getName()
    {
 return name;
    }

    /**
     *The setType method sets the venue's type to the input
     *
     *@param inType This is the venue's type
     */
    //I suppose a venue could be remodelled, so leaving this public
    public void setType(VenueType inType)
    {
 type = inType;
    }

    /**
     *The getType method returns the venue's type
     *
     *@return VenueType This is the type of venue
     */
    public VenueType getType()
    {
 return type;
    }

    /**
     *The setMaxAths method sets the maximum number of athletes that can enter the venue to the input
     *
     *@param inMax This is the maximum number of athletes that can enter the venue
     */
    public void setMaxAths(int inMax)
    {
 if (inMax >= 1)
     {
  maxA = inMax;
     }
    }

    /**
     *The getMaxAths method returns the maximum number of athletes who can enter the venue
     *
     *@return int This is the maximum number of athletes who can enter the venue
     */
    public int getMaxAths()
    {
 return maxA;
    }

    /**
     *The setMaxFans method sets the maxmimum number of athletes who can enter the venue
     *
     *@param inMax This is the maximum number of fans who can enter the venue
     */
    public void setMaxFans(int inMax)
    {
 //Check that the new max won't kill off any fans currently in the Venue
 if (inMax >= getCurrFans())
     {
  maxFans = inMax;
     }
    }

    /**
     *The getMaxFans method returns the maximum number of fans who can enter the venue
     *
     *@return int This is the maximum number of fans who can enter the venue
     */
    public int getMaxFans()
    {
 return maxFans;
    }
  
    /**
     *The addFan method allows a fan to enter the Venue, but if the current number of fans already acceeds the limit of fan aloud in the Venue then an exception is thrown and the fan is rejected from entering the Veneu.
     *
     *@param inFan This is the fan being added
     *
     *@throws TooManyFansException This is thrown when too many fans attempt to enter the venue
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

    /**
     *The removeFan method attempts to remove a fan from the venue
     *
     *@return Fan This is the fan being removed
     */
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

    /**
     *The getCurrFans method returns the number of fans currently in the venue
     *
     *@return int This is the current number of fans in the venue
     */
    public int getCurrFans()
    {
 return fans.size();
    }
}
