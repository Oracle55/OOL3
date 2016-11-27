/**
 *
 *@author Moses Ilunga - Manual Sorting
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparator
 *@author Tim Dusek -
 */
import java.util.*;
public class NotEnoughAthletesException extends Exception
{
    private ArrayList<Athlete> cheaters = new ArrayList<Athlete>();

    /**
     *This constructor uses Exception to give a basic error message if there are not enough athletes to run the event
     */
    public NotEnoughAthletesException()
    {
	super("Error: Not Enough Athletes, can't run event");
    }
     
    /**
     *This constructor uses Exception to give an error message with the list of cheaters if a cheater is caught
     *
     *@param list This is the list of caught cheaters
     */
    public NotEnoughAthletesException(ArrayList<Athlete> list)
    {
	super("Error: A Cheater has been caught!");
	cheaters = list;
    }

    /**
     *The getCheaters method returns the list of caught cheaters
     *
     *@return ArrayList<Athlete> This is the list of caught cheaters
     */
    public ArrayList<Athlete> getCheaters()
    {
	return cheaters;
    }
}
