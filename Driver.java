/**
*
*@author Moses Ilunga - (Will add what I did)
*@author Ben Delzer - 
*@author Christian Hansen -
*@author Brock -
*@author Tim-
*/
import java.io.*;
import java.util.*;
public class Driver
{
/*
     public static int[] convertMedals(int value)
     {
          //Making some assumptions that rather than medals being a total number
          //  that medals are recorded in GGSSBB format
          //Given that no one has one triple digit medals of a type, this _might_ be reasonable?
          int[] output = new int[3];
          if (value > 0 && value < 1000000)
          {
               for (int x = 2; x >= 0; x--)
               {
                    output[x] = value % 100;
                    value = value / 100;
               }
          }
          return output;
     }
*/
     public static void main(String[] args) throws FileNotFoundException
    {
	ArrayList<athlete> rCheated = new ArrayList<athlete>();
	ArrayList<athlete> rDidNotCheat = new ArrayList<athlete>();
	ArrayList<athlete> rGold = new ArrayList<athlete>();
	ArrayList<athlete> rSilver = new ArrayList<athlete>();
	ArrayList<athlete> rBronze = new ArrayList<athlete>();
	ArrayList<athlete> rOneMedal = new ArrayList<athlete>();
	ArrayList<athlete> rNoMedals = new ArrayList<athlete>();
	ArrayList<athlete> rAquatic = new ArrayList<athlete>();
	ArrayList<athlete> rTrack = new ArrayList<athlete>();
	ArrayList<athlete> rGym = new ArrayList<athlete>();
	ArrayList<athlete> rOutdoor = new ArrayList<athlete>();
	ArrayList<Athlete> masterRecurseList = new ArrayList<Athlete>();
         File athFile = new File("Athlete.txt");
         Scanner readAth = new Scanner(athFile);
         ArrayList<Athlete> ath = new ArrayList<Athlete>();
         ArrayList<Athlete> cheats = new ArrayList<Athlete>();
         String line;
         String[] parts;
         
         while(readAth.hasNext())
          {
              line = readAth.nextLine();
              parts = line.split(",");
              ath.add(new Athlete(Venue.VenueType.valueOf(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Boolean.parseBoolean(parts[3])));
         }
         readAth.close();
         
         File evFile = new File("Event.txt");
         Scanner readEv = new Scanner(evFile);
         ArrayList<Event> event = new ArrayList<Event>();
         
         while(readEv.hasNext())
         {
              line = readEv.nextLine();
              parts = line.split(",");
              event.add(new Event(parts[0], Double.parseDouble(parts[1]), Venue.VenueType.valueOf(parts[2])));
         }
         readEv.close();
         
         File veFile = new File("Venue.txt");
         Scanner readVe = new Scanner(veFile);
         ArrayList<Venue> venue = new ArrayList<Venue>();
         
         while(readVe.hasNext())
         {
              line = readVe.nextLine();
              parts = line.split(",");
              venue.add(new Venue(parts[0], Venue.VenueType.valueOf(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
         }
         readVe.close();
         
         Random rng = new Random();
         ArrayList<Fan> fan = new ArrayList<Fan>();
         for (int x = 0; x < 100000; x++)
         {
              int num = rng.nextInt(4);
              if (num == 0)
                   fan.add(new Fan(Venue.VenueType.AQUATIC));
              else if (num == 1)
                   fan.add(new Fan(Venue.VenueType.TRACK));
              else if (num == 2)
                   fan.add(new Fan(Venue.VenueType.OUTDOOR));
              else
                   fan.add(new Fan(Venue.VenueType.GYM));
         }
         
          //BEGIN OLYMPICS!
          System.out.println("The Olympics are about to begin!\n\n");
          long num = 0;
          double cap = 0.0;
          
          for (Event e : event)
          {
               //Assign Venue
               while (e.getLoc() == null)
               {
                    e.setLoc(venue.get(rng.nextInt(venue.size())));
               }
               
               
               //Fans?
               for(Fan f : fan)
               {
                    double go = 0.0;
                    if (f.getFav().equals(e.getVenueType()))
                         go = rng.nextDouble() * e.getPopularity();
                    else
                         go = rng.nextDouble() * .001;
                    
                    if (go > .5)
                    {
                         try
                         {
                              f.buyTicket();
                              e.getLoc().addFan(f);
                         }
                         catch (OutOfMoneyException ex)
                         {
                              //Fan doesn't get in due to try block!
                         }
                         catch(TooManyFansException ex)
                         {
                              Venue curr = e.getLoc();
                              for (Venue v: venue)
                              {
                                   if (v.getType().equals(curr.getType()) && v.getMaxFans() > curr.getMaxFans())
                                        curr = v;
                              }
                              if (!curr.equals(e.getLoc()))
                              {
                                   //move fans
                                   try
                                   {
                                        while (e.getLoc().getCurrFans()!=0)
                                             curr.addFan(e.getLoc().removeFan());
                                        curr.addFan(f);
                                        e.setLoc(curr);
                                   }
                                   catch (TooManyFansException e2)
                                   {
                                        System.err.println(e2.getMessage());
                                   }
                              }
                         }
                    }
               }
               
               //Athletes?
               ArrayList<Athlete> pref = new ArrayList<Athlete>();
               ArrayList<Athlete> notpref = new ArrayList<Athlete>();
               for(Athlete a : ath)
               {
                    if (a.mustRest() > 0)
                    {
                         a.rest(e.getVenueType());
                    }
                    else
                    {
                         if(a.getFav().equals(e.getVenueType()))
                              pref.add(a);
                         else
                              notpref.add(a);
                    }
                    
               }
               
               for(Athlete a : pref)
               {
                    try
                    {
                         if (e.getPopularity() + ((double) e.getLoc().getCurrFans()/e.getLoc().getMaxFans()) > rng.nextDouble() * 2.0)
                              e.addAthlete(a);
                    }
                    catch (TooManyAthletesException ex)
                    {
                         Venue curr = e.getLoc();
                         for (Venue v: venue)
                         {
                              if (v.getType().equals(curr.getType()) && v.getMaxAths() > curr.getMaxAths() && v.getMaxFans() >= curr.getMaxFans())
                                   curr = v;
                         }
                         if (!curr.equals(e.getLoc()))
                         {
                              //move fans
                              try
                              {
                                   while (e.getLoc().getCurrFans()!=0)
                                        curr.addFan(e.getLoc().removeFan());
                                   e.setLoc(curr);
                              }
                              catch (TooManyFansException e2)
                              {
                                   System.err.println(e2.getMessage());
                              }
                              e.setLoc(curr);
                         }
                    }
               }
               try
               {
                    for(Athlete a : notpref)
                    {
                         if (e.getPopularity() + ((double) e.getLoc().getCurrFans()/e.getLoc().getMaxFans()) > rng.nextDouble() * 2.0)
                              e.addAthlete(a);
                    }
               }
               catch (TooManyAthletesException ex)
               {
                    System.err.println(ex.getMessage());
               }
               
               //print
               System.out.println(e);
               
               boolean refund = false;
               //run
               try
               {
                    e.runEvent();
               }
               catch (CaughtCheatingException ex)
               {
                    for (Athlete c : ex.getCheaters())
                    {
                         ath.remove(c);
                         cheats.add(c);
                    }
               }
               catch (NotEnoughAthletesException ex)
               {
                    refund = true;
                    for (Athlete c : ex.getCheaters())
                    {
                         ath.remove(c);
                         cheats.add(c);
                    }
               }
               
               //print
               System.out.println(e);
               
               num += e.getLoc().getCurrFans();
               cap += e.getLoc().getCurrFans() / e.getLoc().getMaxFans() * 100.0;
               
               //Refund?
               Fan f = e.getLoc().removeFan();
               while (f != null)
               {
                    if (refund)
                    {
                         f.getRefund();
                    }
                    f = e.getLoc().removeFan();
               }
          }
         
          //Summary
          String[] metal = {"GOLD","SILVER","BRONZE","TOTAL"};
          for (int x = 0; x < 4; x++)
          {
               System.out.println("\n\nTop 10 " + metal[x] + " Winners\n");
               ArrayList<Athlete> tmp = new ArrayList<Athlete>();
               tmp.addAll(ath);
               for (int y = 0; y < 10; y++)
               {
                    System.out.println("\t" + (y+1) + ")\t" + max(tmp,x) +"\n");
               }
          }
          
//Cheaters
          int free = 0;
          for (Athlete a : ath)
          {
               if (a.getCheater())
                    free++;
          }
          System.out.println("\n\nCheaters caught: " + ((cheats.size() * 100) / (cheats.size() + free)) + "%");
          System.out.println("\n\nNullified Medals: " + (((Athlete.getVacant() + Athlete.getNulls()) * 100)/(Athlete.getVacant() + Athlete.getNulls() + Athlete.getAwarded())) + "%");
          
          //Attendence
          System.out.println("\n\nAverage Attendence per event: " + num/event.size());
          System.out.println("\n\nAverage Capacity per event: " + cap/event.size() +"%");
          //Final values
          System.out.println("\n\nThis Olympics was a success!!!\n\n");
          //Defining "most" as "0 or more"
          //Defining "few" as "all or fewer"
          //Defining "Strong" as "0 or more"
          //Defining "Full" as "0% - 100% capacity"
          //Note...I'd have docked some points for these definintions....
          
     }
     
     public static Athlete max(ArrayList<Athlete> list, int medal)
     {
          Athlete max = list.get(0);
          if (medal == 3)
          {

               
               for (Athlete a : list)
               {
                    if (max.getMedals()[0] + max.getMedals()[1] + max.getMedals()[2]< a.getMedals()[0] + a.getMedals()[1] + a.getMedals()[2])
                         max = a;
               }

          }
          else
          {

               
               for (Athlete a : list)
               {
                    if (max.getMedals()[medal] < a.getMedals()[medal])
                         max = a;
               }
          }
          list.remove(max);
          return max;
     }

    public ArrayList Recurse(ArrayList<Athlete> list)
    {

		int l=list.size();
		
		if (list[l].getCheater().equals(false))
		    {
			rCheated.add(list[l]);
		    }
		if (list[l].getCheater().equals(true))
		    {
			rDidNotCheat.add(list[l]);
		    }
		if (list[l].getMedals[0]>0)
		    {
			rGold.add(list[l]);
		    }
		if (list[l].getMedals[1]>0)
		    {
			rSilver.add(list[l]);
		    }
		if (list[l].getMedals[2]>0)
		    {
			rBronze.add(list[l]);
		    }
		if ((list[l].getMedals[0]>0) || (list[l].getMedals[1]>0) ||(list[l].getMedals[2]>0))
		    {
			rOneMedal.add(list[l]);
		    }
		if ((list[l].getMedals[0]==0) && (list[l].getMedals[1]==0) && (list[l].getMedals[2]==0))
		    {
			rNoMedals.add(list[l]);
		    }
		
		//rAquatic
		//rTrack
		//rGym
		//rOutdoor



	    }
	if(list.size()>0)
	    {
		recurse(list);

    }
}
