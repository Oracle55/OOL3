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
    public NotEnoughAthletesException()
    {
	super("Error: Not Enough Athletes, can't run event");
    }
     
     
    public NotEnoughAthletesException(ArrayList<Athlete> list)
    {
	super("Error: A Cheater has been caught!");
	cheaters = list;
    }
     
    public ArrayList<Athlete> getCheaters()
    {
	return cheaters;
    }
}
