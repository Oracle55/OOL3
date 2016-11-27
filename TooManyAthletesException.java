/**
 *
 *@author Moses Ilunga - Manual Sorting
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparator
 *@author Tim Dusek -
 */
public class TooManyAthletesException extends Exception
{
    public TooManyAthletesException()
    {
	super("Error: Too many Athletes for venue");
    }
}
