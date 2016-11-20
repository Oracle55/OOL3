/**
 *
 *@author Moses Ilunga - (Will add what I did)
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparator
 *@author Brock -
 *@author Tim Dusek -
 */
public class OutOfMoneyException extends Exception
{
    public OutOfMoneyException()
    {
	super("Error: A fan tried to buy a ticket, but didn't have enough money to do so!");
    }
}
