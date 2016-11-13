/**
*
*@author Moses Ilunga - (Will add what I did)
*@author Ben Delzer - 
*@author Christian Hansen -
*@author Brock -
*@author Tim Dusek -
*/
import java.util.*;
public class Fan
{
     private int wallet;
     private Venue.VenueType fav;
     
     public String toString()
     {
          return "This Fan:\n\tLikes:\t"+getFav()+"\n\tHas in Wallet:\t"+getWallet();
     }
     
     public Fan(Venue.VenueType inFav)
     {
          setFav(inFav);
          setWallet();
     }
     
     public void setFav(Venue.VenueType input)
     {
          fav = input;
     }
     
     public Venue.VenueType getFav()
     {
          return fav;
     }
     
     public int getWallet()
     {
          return wallet;
     }
     
     private void setWallet()
     {
          Random rng = new Random();
          wallet = (rng.nextInt(50) + 1) * 10;
     }
     
     public void getRefund()
     {
          wallet += 10;
     }
     
     public void buyTicket() throws OutOfMoneyException
     {
          if (wallet < 10)
               throw new OutOfMoneyException();
          wallet -= 10;
     }
     
     
}
