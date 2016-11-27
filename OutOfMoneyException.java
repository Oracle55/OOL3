/**
 *
 *@author Moses Ilunga - Manual Sorting
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparator
 *@author Tim Dusek -
 */
public class OutOfMoneyException extends Exception
{
    public OutOfMoneyException()
    {
	super("Error: A fan tried to buy a ticket, but didn't have enough money to do so!");
    }
}
