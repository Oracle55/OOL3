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

    /**
     *This constructor will use Exception to give an error message in the case that an athlete is caught cheating
     */
    public CaughtCheatingException()
    {
	super("Error: A Cheater has been caught!");
    }

    /**
     *This constructor will use Exception to give an error message if an athlete is caught cheating, and will give the list of all caught cheaters
     *
     *@param list This is the list of all caught cheaters
     */
    public CaughtCheatingException(ArrayList<Athlete> list)
    {
	super("Error: A Cheater has been caught!");
	cheaters = list;
    }

    /**
     *The getCheaters method will return the list of all caught cheaters
     *
     *@return ArrayList<Athlete> This is the list of all caught cheaters
     */
    public ArrayList<Athlete> getCheaters()
    {
	return cheaters;
    }
}
