/**
 *
 *@author Moses Ilunga - (Will add what I did)
 *@author Ben Delzer - 
 *@author Christian Hansen -
 *@author Brock -
 *@author Tim Dusek -
 */
import java.util.*;

/**
 * Event represents an Olympic Event that has Athletes compeating, Fan's observing, and residing within a Venue. Event primarily assigns medals to Athletes based off of focused random generation.
 * 
 *  String name holds the name of the Event
 *  Venue loc is the location that the Event will be hosted
 *  enum Status holdes the current status of each Event
 *  double pop represents the popularity of this Event
 *  ArrayList<Athtlete> aths holds all the Athletes that will be performing in this Event
 *  Status status holds the enum value from Status to show the current Status of this Event
 *  String results holds the finished results of the Event including the Athletes and the medals they have won, along with if they fainted or cheated
 *  Venue.VenueType myType holds the enum value of the kind of Venue is needed to hold this Event
 *  int heat holds how many stages there are in this Event
 *  int count holds what stage this Event is currently on
 */
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
  private int heat = 0;
  private int count = 1;
  
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
  
  /**
   * This constructor is to be used when there are multiple stages to an Event
   * 
   * @param String inName holds the Name of the Event
   * @param double inPop holds the popularity rating for this Event
   * @param Venue.VenueType inVen holds the enum value for the kind of Event
   * @param ArrayList<String> heat uses the array.size to represent the number of stages this Event needs to go throughle
   */
  public Event(String inName, double inPop, Venue.VenueType inVen, ArrayList<String> heat)
  {
    setName(inName);
    setPopularity(inPop);
    setVenueType(inVen);
    status = Status.SCHEDULED;
  }
  
  /**
   * setHeat sets the number of stages this Event needs to go through in order to fully complete
   * 
   * @param int inHeat holds the number of stages this Event needs to do hold in order to complete
   */
  public void setHeat(int inHeat)
  {
    heat = inHeat;
  }
  
  /**
   * getHeat returns the number of stages there are in this Event
   * 
   * @return heat holds the number of stages this Event needs to hold in order to complete
   */
  public int getHeat()
  {
    return heat;
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
  public double compareTo(Event inEvent)
  {
    return getPopularity() - inEvent.getPopularity();
  }
  
  /**
   * runEvent hosts the Event and allows the Athletes to compete against eachother a Gold, Silver, and Bronze medalist will be generated from this method
   * 
   * @throws CaughtCheatingException 
   * 
   */
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
    
    do
    {
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
      count++;
      
      while (tmp.size() < 3)
      {
        tmp.add(fainters.get(0));
      }
    }
    while(count <= getHeat());
    
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
    
    for(Athlete t: aths)
    {
      t.setScore(-(t.getScore()));
    }
    
    return max;
  }
  
}
