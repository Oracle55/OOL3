public class OutOfMoneyException extends Exception
{
     public OutOfMoneyException()
     {
          super("Error: A fan tried to buy a ticket, but didn't have enough money to do so!");
     }
}