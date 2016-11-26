/**
 *
 *@author Moses Ilunga - Manual Sorting
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparator
 *@author Tim Dusek - 
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
