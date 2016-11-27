/**
 *
 *@author Moses Ilunga - Manual Sorting
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparator
 *@author Tim Dusek -
 */
public class TooManyAthletesException extends Exception
{
    /**
     *This constructor uses Exception to print a basic error message when there are too many athletes in the venue
     */
    public TooManyAthletesException()
    {
	super("Error: Too many Athletes for venue");
    }
}
