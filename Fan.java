/**
 *
 *@author Moses Ilunga - (Will add what I did)
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparator
 *@author Brock -
 *@author Tim Dusek -
 */
import java.util.*;
public class Fan implements Comparable<Fan>
{
    /**
     * @int wallet holds the amount of money this Fan owns
     * @Venue.VenueType fav hold this Fans' favorite kind of Venue
     * @int happiness holds a numerical value of how happy the Fan is with the Events it has attended
     * @int attended holds how many Events this Fan has attended
     * @int refunded holds how many Events this Fan has not been able to enter because of lack of money
     * @int rejected holds how many Events this Fan was not aloud to attend due to lack of space in Venue
     * @int ID is a static counter that is used to assign unique ID numbers to each Fan
     * @int fanID holds this Fans' unique ID number
     */
    private int wallet;
    private Venue.VenueType fav;
    private int happiness = 0;
    private int attended = 0;
    private int refunded = 0;
    private int rejected = 0;
    private static int ID = 0;
    private int fanID = 0;

    /**
     *compareTo takes in a Fan and compares the happiness of the two Fans so see which is happier
     *
     * @param Fan other This is the fan being compared to the current one
     */
    public int compareTo(Fan other)
    {
	if(this.getHappiness() > other.getHappiness())
	    {
		return 1;
	    }
	else if(this.getHappiness() < other.getHappiness())
	    {
		return -1;
	    }
	else
	    {
		return 0;
	    }
    }
    
    public String toString()
    {
	return "This Fan:\n\t ID\t" + getFanID() + "\n\tLikes:\t"+getFav()+"\n\tHas in Wallet:\t"+getWallet()+"\n\tHappiness rating:\t"+ getHappiness();
    }
  
    /**
     * This is the only constructor for Fan that sets their favorite Event and how much money they have while assigning them an ID number for rating purposes
     * 
     */
    public Fan(Venue.VenueType inFav)
    {
	setFav(inFav);
	setWallet();
	setID(ID);
	ID++; 
    }
  
    /**
     * getID returns the static counter used for assigning unique ID numbers to each Fan
     * 
     * @return ID the static counter used to assign unique ID numbers to Fans
     */
    public static int getID()
    {
	return ID;
    }
    /**
     * getFanID returns this Fans' unique ID number 
     * 
     * @return fanID
     */
    public int getFanID()
    {
	return fanID;
    }
  
    /**
     * setID assigns a unique ID number to this Fan
     * 
     * @param int inID is a static counter that is used to assign unique ID numbers to each Event
     */
    public void setID(int inID)
    {
	fanID = inID;
    }
    /**
     * reject adds to the amount of events this fan was not aloud to enter
     */
    public void reject()
    {
	rejected++;
    }
  
    /**
     * setHappiness takes info about how many Events this fan has: attended, was refunded from, or was not aloud to attend, along with how much money they still have and computes them together to represent a numberical value of how happy this fan is
     * 
     */
    public void setHappiness()
    {
	happiness = ((attended * 10) + (wallet) - (refunded * 20) - (rejected * 40));
    }
  
    /**
     * getHappiness returns how happy this Fan was with the Olymipics
     * 
     * @return happiness holds the numerical value for how happy this fan was with the Olympics
     */
    public int getHappiness()
    {
	return happiness;
    }
  
    public void setFav(Venue.VenueType input)
    {
	fav = input;
    }
  
    public Venue.VenueType getFav()
    {
	return fav;
    }
  
    public int getWallet()
    {
	return wallet;
    }
  
    private void setWallet()
    {
	Random rng = new Random();
	wallet = (rng.nextInt(50) + 1) * 10;
    }
  
    /**
     * getRefund returns 10 dollars to the Fans' wallet and adds to the number of refunded events this Fan has been to.
     */
    public void getRefund()
    {
	wallet += 10;
	refunded++;
    }
  
    /**
     * buyTicket allows a Fan to enter the Event by paying 10 dollars out of their wallet. If the Fan does not have enough money then an exception is thrown. If not then the number of succefully attended Events is increased.
     * 
     * @throws OutOfMoneyException
     */
    public void buyTicket() throws OutOfMoneyException
    {
	if (wallet < 10)
	    throw new OutOfMoneyException();
	wallet -= 10;
	attended++;
    }
  
  
}
