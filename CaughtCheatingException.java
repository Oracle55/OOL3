/**
 *
 *@author Moses Ilunga - (Will add what I did)
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparator
 *@author Brock -
 */
import java.util.*;
public class CaughtCheatingException extends Exception
{
    private ArrayList<Athlete> cheaters = new ArrayList<Athlete>();
    public CaughtCheatingException()
    {
	super("Error: A Cheater has been caught!");
    }

    public CaughtCheatingException(ArrayList<Athlete> list)
    {
	super("Error: A Cheater has been caught!");
	cheaters = list;
    }
     
    public ArrayList<Athlete> getCheaters()
    {
	return cheaters;
    }
}
