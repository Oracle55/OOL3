/**
*
*@author Moses Ilunga - (Will add what I did)
*@author Ben Delzer - 
*@author Christian Hansen -
*@author Brock -
*@author Tim Dusek -
*/
import java.util.*;
public class Event
{
     //Using an enum...You could have used a String instead
     public enum Status {SCHEDULED, ACTIVE, COMPLETED}
     
     private String name = "";
     private Venue loc;
     private double pop = 0.0;
     private ArrayList<Athlete> aths = new ArrayList<Athlete>();
     private Status status;
     private String results = "TBD";
     private Venue.VenueType myType = null;
     
     public Venue.VenueType getVenueType()
     {
          return myType;
     }
     
     public void setVenueType(Venue.VenueType input)
     {
          myType = input;
     }
     
     public String toString()
     {
          String athletes = "\n-----\n";
          for (Athlete a:aths)
               athletes = athletes + a + "\n-----\n";
          return "Event "+getName()+":\n\tVenue:\n-----\n"+getLoc()+"\n-----\n\tVenue Type:"+getVenueType()+"\n\tPopuluarity:\t"+getPopularity()+"\n\tStatus: "+getStatus()+"\n\tResults:\t"+getResults()+"\n\tAtheletes:\n" + athletes;
     }
     
     //Leaving status and results out of full parameter constructor since:
     //--Status will always start as SCHEDULED
     //--Results won't be known until Event concludes
     public Event(String inName, double inPop, Venue.VenueType inVen)
     {
          setName(inName);
          setPopularity(inPop);
          setVenueType(inVen);
          status = Status.SCHEDULED;
     }
     
     public void setName(String inName)
     {
          name = inName;
     }
     
     public String getName()
     {
          return name;
     }
     
     public void setLoc(Venue inLoc)
     {
          if (inLoc.getType().equals(getVenueType()))
               loc = inLoc;
     }
     
     public Venue getLoc()
     {
          return loc;
     }
     
     public void setPopularity(double inPop)
     {
          if (inPop >= 0.0 && inPop <= 1.0)
          {
               pop = inPop;
          }
     }
     
     public double getPopularity()
     {
          return pop;
     }
     
     public void setAthList(ArrayList<Athlete> inAths)
     {
          if (inAths.size() <= getLoc().getMaxAths())
          {
               aths.clear();
               aths.addAll(inAths);
          }
     }
     
     public void addAthlete(Athlete inAth) throws TooManyAthletesException
     {
          if (aths.size() + 1 <= getLoc().getMaxAths())
          {
               aths.add(inAth);
          }
          else 
               throw new TooManyAthletesException();
     }
     
     public void removeAthlete(Athlete inAth)
     {
          aths.remove(inAth);
     }
     
     public ArrayList<Athlete> getAthList()
     {
          return aths;
     }
     
     private void advanceStatus()
     {
          if(status == Status.SCHEDULED)
          {
               status = Status.ACTIVE;
          }
          else if (status == Status.ACTIVE)
          {
               status = Status.COMPLETED;
          }
     }
     
     public Status getStatus()
     {
          return status;
     }
     
     private void setResults(String inResults)
     {
          results = inResults;
     }
     
     public String getResults()
     {
          return results;
     }
     
     /**
      * compareTo takes in an Event and compare the popularity of the two Event's so see who has the higher popularity
      * 
      * @param Event inEvent is the Event being used to compare against this Event
      */
     public int compareTo(Event inEvent)
     {
       return getPopularity() - inEvent.getPopularity();
     }
     
     public boolean runEvent() throws CaughtCheatingException, NotEnoughAthletesException
     {
          if (aths.size() < 3)
          {
               throw new NotEnoughAthletesException();

          }
          
          //advance status
          advanceStatus();
          Random rng = new Random();
          //Clone Athlete List so I have a copy I can play with
          ArrayList<Athlete> tmp = new ArrayList<Athlete>();
          ArrayList<Athlete> cheaters = new ArrayList<Athlete>();
          ArrayList<Athlete> fainters = new ArrayList<Athlete>();
                    
          for (Athlete t : aths)
          {
               try
               {
                    if (getVenueType().equals(t.getFav()))
                         t.changeStam((rng.nextInt(10) + 1) * -1);
                    else
                         t.changeStam((rng.nextInt(16) + 5) * -1);

                    if (t.getCheater())
                    {
                         if ( rng.nextInt(20)==0)
                              throw new CaughtCheatingException();
                         else
                         {
                              t.setScore(rng.nextInt(16) + 5 + t.getSkill());
                         }
                    }
                    else
                         t.setScore(rng.nextInt(20) + 1 + t.getSkill());
                    
                    if (t.getStam() > 0)
                         tmp.add(t);
               }
               catch (CaughtCheatingException e)
               {
                    t.strip();
                    cheaters.add(t);
               }
               catch (AthleteFaintedException e)
               {
                    fainters.add(t);
               }
          }

          for (Athlete c : cheaters)
               tmp.remove(c);
          for (Athlete f : fainters)
               tmp.remove(f);
          
          setResults("Results:\n");
          boolean noMedal = false;
          Athlete placed = max(tmp);
          if (placed != null)
          {
               setResults(getResults() + "\t\tGOLD:\t#" + placed.getNumber() + "\n");
               placed.addMedal(1);
               if (getLoc().getCurrFans() > 0)
                    placed.addEndorsements((int)(getPopularity() * (rng.nextInt(getLoc().getCurrFans()-1) + 2)));
               tmp.remove(placed);
          }
          else 
          {
               Athlete.vacated();
               noMedal = true;
          }
          placed = max(tmp);
          if (placed != null)
          {
               setResults(getResults() + "\t\tSILVER:\t#" + placed.getNumber() + "\n");
               placed.addMedal(2);
               if (getLoc().getCurrFans() > 0)
                    placed.addEndorsements((int)(getPopularity() * (rng.nextInt(getLoc().getCurrFans()-1) + 2) * .5));
               tmp.remove(placed);
          }
          else 
          {
               Athlete.vacated();
               noMedal = true;
          }
          placed = max(tmp);
          if (placed != null)
          {
               setResults(getResults() + "\t\tBRONZE:\t#" + placed.getNumber() + "\n");
               placed.addMedal(3);
               if (getLoc().getCurrFans() > 0)
                    placed.addEndorsements((int)(getPopularity() * (rng.nextInt(getLoc().getCurrFans()-1) + 2) * .25));
          }
          else 
           {
               Athlete.vacated();
               noMedal = true;
          }
          //advance status
          advanceStatus();
          if (noMedal)
               throw new NotEnoughAthletesException(cheaters);
          if (cheaters.size() > 0)
               throw new CaughtCheatingException(cheaters);
          return true;
     }
     
     private Athlete max(ArrayList<Athlete> list)
     {
          if (list.size() == 0)
               return null;
          Athlete max = list.get(0);
          for (Athlete a : list)
          {
               if (max.getScore() < a.getScore())
                    max = a;
          }
          return max;
     }
     
}
