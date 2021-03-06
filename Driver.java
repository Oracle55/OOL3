 /**
 *
 *@author Moses Ilunga - Manual Sorting of athlete endorsements and event turn out
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparator
 *@author Tim Dusek - Recursion
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
 static ArrayList<Athlete> rCheated = new ArrayList<Athlete>();
        static ArrayList<Athlete> rDidNotCheat = new ArrayList<Athlete>();
        static ArrayList<Athlete> rGold = new ArrayList<Athlete>();
        static ArrayList<Athlete> rSilver = new ArrayList<Athlete>();
        static ArrayList<Athlete> rBronze = new ArrayList<Athlete>();
        static ArrayList<Athlete> rOneMedal = new ArrayList<Athlete>();
        static ArrayList<Athlete> rNoMedals = new ArrayList<Athlete>();
        static ArrayList<Athlete> rAquatic = new ArrayList<Athlete>();
        static ArrayList<Athlete> rTrack = new ArrayList<Athlete>();
        static ArrayList<Athlete> rGym = new ArrayList<Athlete>();
        static ArrayList<Athlete> rOutdoor = new ArrayList<Athlete>();
 static ArrayList<Athlete> athRecurseList = new ArrayList<Athlete>();
  
    public static void main(String[] args) throws FileNotFoundException
    {
 File athFile = new File("Athlete.txt");
 Scanner readAth = new Scanner(athFile);
 ArrayList<Athlete> ath = new ArrayList<Athlete>();
 ArrayList<Athlete> cheats = new ArrayList<Athlete>();
 String line;
 String[] parts;
 ArrayList<String> heat = new ArrayList<String>();
    
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
      
  if(parts.length > 3)
      {
   for(int x = 3; x <= parts.length - 1; x++)
       {
    heat.add(parts[x]);
       }
   event.add(new Event(parts[0], Double.parseDouble(parts[1]), Venue.VenueType.valueOf(parts[2]), heat));
      }
  else
      {
   event.add(new Event(parts[0], Double.parseDouble(parts[1]), Venue.VenueType.valueOf(parts[2])));
      }
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

 Athlete[] tempAth = ath.toArray(new Athlete[0]);

 Event[] tempEv = event.toArray(new Event[0]);

 Venue[] tempVen = venue.toArray(new Venue[0]);
 
 Arrays.sort(tempAth);
 Arrays.sort(tempEv);
 Arrays.sort(tempVen);

 for(Athlete a : tempAth)
     {
  System.out.println(a.toString());
     }
 for(Event e : tempEv)
     {
  System.out.println(e.toString());
     }
 for(Venue v : tempVen)
     {
  System.out.println(v.toString());
     }
 
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
     e.addFanCount();
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
   System.out.println("derpEvent");
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
  System.out.println("derpEventdone");
  //print
  System.out.println(e);
      
  num += e.getLoc().getCurrFans();
  cap += e.getFanCount();
      
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
   for (int i=0;i<ath.size();i++)
 {
     athRecurseList.add(ath.get(i));
 }
    Recurse(athRecurseList);
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
 //System.out.println("\n\nCheaters caught: " + ((cheats.size() * 100) / (cheats.size() + free)) + "%");
 System.out.println("\n\nNullified Medals: " + (((Athlete.getVacant() + Athlete.getNulls()) * 100)/(Athlete.getVacant() + Athlete.getNulls() + Athlete.getAwarded())) + "%");
    
 //Attendence
 System.out.println("\n\nAverage Attendence per event: " + num/event.size());
 System.out.println("\n\nAverage Capacity per event: " + (event.size()/cap)*100 +"%");
 int totalHap = 0;
 for(Fan f: fan)
     {
  f.setHappiness();
     }
 for(Fan f: fan)
     {
  totalHap = totalHap + f.getHappiness();
     }
 System.out.println("\n\nAverage Happiness of Fans: " + totalHap/fan.size());
    
 System.out.println("\n\nTop 10 unhappiest Fans\n");
 ArrayList<Fan> tmpF = new ArrayList<Fan>();
 tmpF.addAll(fan);
 Collections.sort(tmpF);
 for (int y = 0; y < 10; y++)
     {
  System.out.println("\t" + (y+1) + ")\t" + tmpF.get(y) +"\n");
     }

 System.out.println("\n\nTop 10 happiest Fans\n");
 Collections.reverse(tmpF);
 for (int y = 0; y < 10; y++)
     {
  System.out.println("\t" + (y+1) + ")\t" + tmpF.get(y) +"\n");
     }
 
 System.out.println("\n\nTop 10 most Endorsed Athletes\n");
 ArrayList<Athlete> tmpA = new ArrayList<Athlete>();
 tmpA.addAll(ath);
 selectionSort(tmpA);
  Collections.reverse(tmpA);
 for (int y = 0; y < 10; y++)
     {
  System.out.println("\t" + (y+1) + ")\t" + maxE(tmpA) +"\n");
     }
    
 System.out.println("\n\nTop 10 most attended Events\n");
 ArrayList<Event> tmpE = new ArrayList<Event>();
 tmpE.addAll(event);
 BubbleSort(tmpE);
 for (int y = 0; y < 10; y++)
     {
  System.out.println("\t" + (y+1) + ")\t" + maxC(tmpE) +"\n");
     }
    
 System.out.println("\n\nTop 10 least attended Events\n");
 ArrayList<Event> tmpM = new ArrayList<Event>();
 tmpM.addAll(event);
 BubbleSort(tmpM);
 Collections.reverse(tmpM);
 for (int y = 0; y < 10; y++)
     {
  System.out.println("\t" + (y+1) + ")\t" + minC(tmpM) +"\n");
     }
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
  
    /**
     * this max is meant to find the happiest fan
     * 
     * @param List holds all the fans in the olympics
     * @return max which is the happiest fan
     */
    public static Fan max(ArrayList<Fan> list)
    {
 Fan max = list.get(0);
    
 for (Fan a : list)
     {
  if (max.getHappiness() < a.getHappiness())
      max = a;
     }
 list.remove(max);
 return max;
    }
  
    /**
     * maxE is meant to find the most endorsed Athlete
     * 
     * @param List holds all the athletes in the olympics
     * @return max which is the most endorsed Athlete
     */
    public static Athlete maxE(ArrayList<Athlete> list)
    {
 Athlete max = list.get(0);
    
 for (Athlete a : list)
     {
  if (max.getEndorsements() < a.getEndorsements())
      max = a;
     }
 list.remove(max);
 return max;
    }
  
    /**
     * maxC is meant to find the most attended event
     * 
     * @param List holds all the events in the olympics
     * @return max which is the most attended event
     */
    public static Event maxC(ArrayList<Event> list)
    {
 Event max = list.get(0);
    
 for (Event a : list)
     {
  if (max.getFanCount() < a.getFanCount())
      max = a;
     }
 list.remove(max);
 return max;
    }
  
    /**
     * minC is meant to find the least attended event
     * 
     * @param List holds all the events in the olympics
     * @return max which is the least attended event
     */
    public static Event minC(ArrayList<Event> list)
    {
 Event max = list.get(0);
    
 for (Event a : list)
     {
  if (max.getFanCount() > a.getFanCount())
      max = a;
     }
 list.remove(max);
 return max;
    }
 
  /**
     * selectionSort is meant to sort athletes from their endorsements
     * 
     * @param inArr holds ArrayList of athletes
     * @return inArr returns the sorted Athletes with their endorsements in ascending order
     */
    
     public static ArrayList<Athlete> selectionSort(ArrayList<Athlete> inArr) // This specific selection sorts by endoresements 
    {
         int minIndex;
         Athlete smaller = inArr.get(0);
         
        for(int i = 0; i < inArr.size() - 1; i++)
        {
            minIndex = i;
            for(int j = i+1; j < inArr.size(); j++)
            {
                if (inArr.get(i).getEndorsements() < inArr.get(minIndex).getEndorsements())
                {
                    minIndex = j;
                }
            }
            
            if(minIndex != i)
            {
          Collections.swap(inArr, minIndex, i); //Collections has a swap method, I was trying to do too much instead of using something existing
            }
           
        }
         return inArr;
    }
     
     /**
      *BubbleSort is meant to sort an array list. This specific one sorts it from greatest to smallest
      *@param  num is an arraylist of events
      *
      */
     public static void BubbleSort(ArrayList<Event> num)
     {
     Event temp = num.get(0); //Set the tmep to an arbitrary value
  
        for(int i = 0; i < num.size();  i++) //Iterate through the entire array of events
        {
            for(int j = 1; j < num.size() - i; j++) //We now know the final value is in the correct spot, so we don't need to check it, that's why we use num.size()-1
            {
                if (num.get(j-1).getFanCount() > num.get(j).getFanCount()) //If the previous value is larger than the current, swap previous and current
                {
              Collections.swap(num, j-1, j); //Same as above, I was doing too much extra work with swapping, when Collections has a method for it
                }
            }
        } 
     }
 public static void Recurse(ArrayList<Athlete> list)
    {
        int l=list.size()-1;

        if (list.get(l).getCheater()==(false))
        {
            rCheated.add(list.get(l));
        }
        if (list.get(l).getCheater()==(true))
        {
            rDidNotCheat.add(list.get(l));
        }
        if (list.get(l).getMedals()[0]>0)
        {
            rGold.add(list.get(l));
        }
        if (list.get(l).getMedals()[1]>0)
        {
            rSilver.add(list.get(l));
        }
        if (list.get(l).getMedals()[2]>0)
        {
            rBronze.add(list.get(l));
        }
        if ((list.get(l).getMedals()[0]>0) || (list.get(l).getMedals()[1]>0) ||(list.get(l).getMedals()[2]>0))
        {
            rOneMedal.add(list.get(l));
        }
        if ((list.get(l).getMedals()[0]==0) && (list.get(l).getMedals()[1]==0) && (list.get(l).getMedals()[2]==0))
        {
            rNoMedals.add(list.get(l));
        }
        if (list.get(l).getFav().equals("Aquatic"))
        {
            rAquatic.add(list.get(l));
        }
        if (list.get(l).getFav().equals("Track"))
        {
            rTrack.add(list.get(l));
       }
        if (list.get(l).getFav().equals("Gym"))
        {
            rGym.add(list.get(l));    
        }
        if (list.get(l).getFav().equals("Outdoor"))
        {
            rOutdoor.add(list.get(l));
        }
        if(list.size()>0)
        {
     list.remove(l);
     if (list.size()>1)
  {
      Recurse(list);
  }
        }
        if(list.size()==0)
        {
         System.out.println("Athletes willing to cheat:");
         for(int i = 0; i < rCheated.size(); i++) 
            {   
            System.out.println(rCheated.get(l));
        }
         System.out.println("Athletes who did not cheat:");
         for(int i = 0; i < rDidNotCheat.size(); i++) 
            {   
            System.out.println(rDidNotCheat.get(l));
        } 
        System.out.println("Athletes who won gold:");
         for(int i = 0; i < rGold.size(); i++) 
            {   
            System.out.println(rGold.get(l));
        }
        System.out.println("Athletes who won silver:");
         for(int i = 0; i < rSilver.size(); i++) 
            {   
            System.out.println(rSilver.get(l));
        }
        System.out.println("Athletes who won bronze:");
         for(int i = 0; i < rBronze.size(); i++) 
            {   
            System.out.println(rBronze.get(l));
        }
        System.out.println("Athletes who won at least 1 medal:");
         for(int i = 0; i < rOneMedal.size(); i++) 
            {   
            System.out.println(rOneMedal.get(l));
        }
        System.out.println("Athletes who won 0 medals:");
         for(int i = 0; i < rNoMedals.size(); i++) 
            {   
            System.out.println(rNoMedals.get(l));
        }
        System.out.println("Athletes who prefer aquatic:");
         for(int i = 0; i < rAquatic.size(); i++) 
            {   
            System.out.println(rAquatic.get(l));
        }
        System.out.println("Athletes who prefer track");
         for(int i = 0; i < rTrack.size(); i++) 
            {   
            System.out.println(rTrack.get(l));
        }
        System.out.println("Athletes who prefer gym:");
         for(int i = 0; i < rGym.size(); i++) 
            {   
            System.out.println(rGym.get(l));
        }
        System.out.println("Athletes who prefer outdoor:");
         for(int i = 0; i < rOutdoor.size(); i++) 
            {   
            System.out.println(rOutdoor.get(l));
        }
      }
    }
  }


