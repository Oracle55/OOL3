/**
 *
 *@author Moses Ilunga - Manual Sorting
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparator
 *@author Tim Dusek -
 */
public class TooManyFansException extends Exception
{
    public TooManyFansException()
    {
	super("Error: Too many fans for venue");
    }
}
