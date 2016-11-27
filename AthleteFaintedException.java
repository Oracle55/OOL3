/**
 *
 *@author Moses Ilunga - Manual Sorting
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparator
 *@author Brock -
 */
public class AthleteFaintedException extends Exception
{
    /**
     *This constructor will use Exception to give an error message in the case that an athlete faints
     */
    public AthleteFaintedException()
    {
	super("Error: An Athlete ran of stamina and fainted");
    }
}
