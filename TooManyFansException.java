/**
 *
 *@author Moses Ilunga - Manual Sorting
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparator
 *@author Tim Dusek -
 */
public class TooManyFansException extends Exception
{
    /**
     *This constructor uses Exception to print a basic error message when too many fans try to enter the venue
     */
    public TooManyFansException()
    {
	super("Error: Too many fans for venue");
    }
}
