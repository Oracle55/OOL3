/**
 *
 *@author Moses Ilunga - Manual Sorting of Athlete Endorsements
 *@author Ben Delzer - 
 *@author Christian Hansen - Comparable
 *@author Tim Dusek -
 */

import java.util.*;
public class Athlete implements Comparable<Athlete>
{
    private static int nullified = 0;
    private static int vacated = 0;
    private static int awarded = 0;
    private static int count = 0;
    private int number = 1;
    //I'm using an enum as the type prefered venue.  You don't have to.  You can use a String.
    //The benifit of enum is that only valid Strings will be allowed.
    //So I don't have to check for:
    //--Bad inputs
    //--Different Capitalizations
    private Venue.VenueType fav;
    private int stam = 100;
    private int skill = 5;
    private boolean cheater = false;
    private int endorsements = 0;
    private int rest = 0;
    //Using an array since I want exactly 3 boxes for medals
    //0 -> Gold
    //1 -> Silver
    //2 -> Bronze
    private int[] medals = { 0, 0, 0};
    private int score = 0;

    /**
     *This comparator will compare the current athlete's gold medal count to the input athlete's
     */
    public static Comparator<Athlete> GoldComparator = new Comparator<Athlete>()
    {
	public int compare(Athlete ath1, Athlete ath2)
	{
	    if(ath1.getMedals()[0] > ath2.getMedals()[0])
		{
		    return 1;
		}
	    else if(ath1.getMedals()[0] < ath2.getMedals()[0])
		{
		    return -1;
		}
	    else
		{
		    if(ath1.getSkill() > ath2.getSkill())
			{
			    return 1;
			}
		    else if(ath1.getSkill() < ath2.getSkill())
			{
			    return -1;
			}
		    else
			{
			    if(ath1.getEndorsements() > ath2.getEndorsements())
				{
				    return 1;
				}
			    else if(ath1.getEndorsements() < ath2.getEndorsements())
				{
				    return -1;
				}
			    else
				{
				    if(ath1.getMedals()[0] + ath1.getMedals()[1] + ath1.getMedals()[2] > ath2.getMedals()[0] + ath2.getMedals()[1] + ath2.getMedals()[2])
					{
					    return 1;
					}
				    else if(ath1.getMedals()[0] + ath1.getMedals()[1] + ath1.getMedals()[2] < ath2.getMedals()[0] + ath2.getMedals()[1] + ath2.getMedals()[2])
					{
					    return -1;
					}
				    else
					{
					    if(ath1.getNumber() > ath2.getNumber())
						{
						    return 1;
						}
					    else
						{
						    return -1;
						}
					}
				}
			}
		}
	}
    };

    /**
     *This comparator will compare the current athlete's silver medal count to the input athlete's
     */
	public static Comparator<Athlete> SilverComparator = new Comparator<Athlete>()
    {
	public int compare(Athlete ath1, Athlete ath2)
	{
	    if(ath1.getMedals()[1] > ath2.getMedals()[1])
		{
		    return 1;
		}
	    else if(ath1.getMedals()[1] < ath2.getMedals()[1])
		{
		    return -1;
		}
	    else
		{
		    if(ath1.getSkill() > ath2.getSkill())
			{
			    return 1;
			}
		    else if(ath1.getSkill() < ath2.getSkill())
			{
			    return -1;
			}
		    else
			{
			    if(ath1.getEndorsements() > ath2.getEndorsements())
				{
				    return 1;
				}
			    else if(ath1.getEndorsements() < ath2.getEndorsements())
				{
				    return -1;
				}
			    else
				{
				    if(ath1.getMedals()[0] + ath1.getMedals()[1] + ath1.getMedals()[2] > ath2.getMedals()[0] + ath2.getMedals()[1] + ath2.getMedals()[2])
					{
					    return 1;
					}
				    else if(ath1.getMedals()[0] + ath1.getMedals()[1] + ath1.getMedals()[2] < ath2.getMedals()[0] + ath2.getMedals()[1] + ath2.getMedals()[2])
					{
					    return -1;
					}
				    else
					{
					    if(ath1.getNumber() > ath2.getNumber())
						{
						    return 1;
						}
					    else
						{
						    return -1;
						}
					}
				}
			}
		}
	}
    };

    /**
     *This comparator will compare the current athlete's bronze medal count to the input athlete's
     */
	    public static Comparator<Athlete> BronzeComparator = new Comparator<Athlete>()
    {
	public int compare(Athlete ath1, Athlete ath2)
	{
	    if(ath1.getMedals()[2] > ath2.getMedals()[2])
		{
		    return 1;
		}
	    else if(ath1.getMedals()[2] < ath2.getMedals()[2])
		{
		    return -1;
		}
	    else
		{
		    if(ath1.getSkill() > ath2.getSkill())
			{
			    return 1;
			}
		    else if(ath1.getSkill() < ath2.getSkill())
			{
			    return -1;
			}
		    else
			{
			    if(ath1.getEndorsements() > ath2.getEndorsements())
				{
				    return 1;
				}
			    else if(ath1.getEndorsements() < ath2.getEndorsements())
				{
				    return -1;
				}
			    else
				{
				    if(ath1.getMedals()[0] + ath1.getMedals()[1] + ath1.getMedals()[2] > ath2.getMedals()[0] + ath2.getMedals()[1] + ath2.getMedals()[2])
					{
					    return 1;
					}
				    else if(ath1.getMedals()[0] + ath1.getMedals()[1] + ath1.getMedals()[2] < ath2.getMedals()[0] + ath2.getMedals()[1] + ath2.getMedals()[2])
					{
					    return -1;
					}
				    else
					{
					    if(ath1.getNumber() > ath2.getNumber())
						{
						    return 1;
						}
					    else
						{
						    return -1;
						}
					}
				}
			}
		}
	}
    };

    /**
     *This comparator will compare the current athlete's total medal count to the input athlete's
     */
		public static Comparator<Athlete> TotalMedalComparator = new Comparator<Athlete>()
    {
	public int compare(Athlete ath1, Athlete ath2)
	{
	    if(ath1.getMedals()[0] + ath1.getMedals()[1] + ath1.getMedals()[2] > ath2.getMedals()[0] + ath2.getMedals()[1] + ath2.getMedals()[2])
		{
		    return 1;
		}
	    else if(ath1.getMedals()[0] + ath1.getMedals()[1] + ath1.getMedals()[2] < ath2.getMedals()[0] + ath2.getMedals()[1] + ath2.getMedals()[2])
		{
		    return -1;
		}
	    else
		{
		    if(ath1.getSkill() > ath2.getSkill())
			{
			    return 1;
			}
		    else if(ath1.getSkill() < ath2.getSkill())
			{
			    return -1;
			}
		    else
			{
			    if(ath1.getEndorsements() > ath2.getEndorsements())
				{
				    return 1;
				}
			    else if(ath1.getEndorsements() < ath2.getEndorsements())
				{
				    return -1;
				}
			    else
				{
				    if(ath1.getNumber() > ath2.getNumber())
					{
					    return 1;
					}
				    else
					{
					    return -1;
					}
				}
			}
		}
	}
    };

    
    /**
     *compareTo takes in an Athlete and compares the  of the two Athletes so see who has the higher 
     *
     *@param other This is the Athlete that is being compared to the current one
     */
    public int compareTo(Athlete other)
    {
	if(this.getSkill() > other.getSkill())
	    {
		return 1;
	    }
	else if(this.getSkill() < other.getSkill())
	    {
		return -1;
	    }
	else
	    {
		return 0;
	    }
    }


    /**
     *The vacated method will increment the number of vacated medals due to cheaters
     */
    public static void vacated()
    {
	vacated++;
    }

    /**
     *The getAwarded method will return the number of awards
     *
     *@return int This is the number of awards
     */
    public static int getAwarded()
    {
	return awarded;
    }

    /**
     * The getVacant method will return the number of vacant medals
     *
     *@return int This is the number of vacant medals
     */
    public static int getVacant()
    {
	return vacated;
    }

    /**
     * The getNulls method will return the number of medals that were nullified
     *
     *@return int This is the number of medals that were nullified
     */
    public static int getNulls()
    {
	return nullified;
    }


    /**
     *The setRest method will set how much rest the athlete needs
     *
     *@param input This is the amount of rest the athlete needs
     */
    public void setRest(int input)
    {
	if (input > 0)
	    rest = input;
    }

    /**
     *The rest method will rest the athlete, reducing the amount of rest remaining
     *
     *@param input This is the venue the athlete is resting at
     */
    public void rest(Venue.VenueType input)
    {
	Random rng = new Random();
	if (rest > 0 && input.equals(getFav()))
	    rest-=rng.nextInt(3)+3;
	else 
	    rest--;
	
	if (rest < 0)
	    rest = 0;
    }

    /**
     *The mustRest method will return how much the athlete must rest before they are fully rested
     *
     *@return int This is how much longer the athlete needs to rest
     */
    public int mustRest()
    {
	return rest;
    }

    /**
     * The getScore method will return the athlete's score
     *
     *@return int This is the athlete's score
     */
    public int getScore()
    {
	return score;
    }
    
    /**
     * setScore takes itself and adds the inputed amount to have a running total of the Athletes score used for multistep Event's
     * 
     *@param input This is the score being added to the current score
     */
    public void setScore(int input)
    {
	score = score + input;
    }

    /**
     *The getEndorsements method will return the number of endorsements the athlete has
     *
     *@return int This is the number of endorsements the athlete has
     */
    public int getEndorsements()
    {
	return endorsements;
    }

    /**
     *The addEndorsements method will add the amount of endorsements given in the input
     *
     *@param input This is the number of endorsements being added
     */
    public void addEndorsements(int input)
    {
	if (input + getEndorsements() >=0)
	    endorsements += input;
    }

    /**
     *The getCheater method will return whether or not the athlete is a cheater
     *
     *@return boolean This is whether or not the athlete is a cheater
     */
    public boolean getCheater()
    {
	return cheater;
    }

    /**
     *The setCheater method will set whether or not the athlete is a cheater
     *
     *@param boolean input This is whether or not the athlete is a cheater
     */
    public void setCheater(boolean input)
    {
	cheater = input;
    }

    /**
     *The equals method will check to see if the current athlete is the same as the input
     *
     *@param other This is the input that the current athlete is being compared to
     *
     *@return boolean This is whether or not the current athlete is equal to the input
     */
    public boolean equals(Athlete other)
    {
	return (getNumber() == other.getNumber());
    }

    /**
     *The toString method will return some information about the athlete
     *
     *@return String This is some information about the current athlete
     */
    public String toString()
    {
	return "Athlete " + getNumber() + ":\n\tWilling to Cheat:\t"+getCheater()+"\n\tEvents Must Rest:\t"+mustRest()+"\n\tPrefers:\t"+getFav()+" venues\n\tCurrent Stamina:\t"+getStam()+"\n\tSkill Level: " +getSkill()+"\n\tGold Medals:\t"+medals[0]+"\n\tSilver Medals:\t"+medals[1]+"\n\tBronze Medals:\t"+medals[2]+"\n\tEndorsements:\t"+getEndorsements();
    }

    /**
     *This constructor will take in some inputs and set the corresponding variables to those values
     *
     *@param inFav This is the athlete's favorite venue type
     *@param inStam This is the athlete's stamina
     *@param inSkill This is the athlete's skill
     *@param inCheat This is whether or not the athlete is a cheater
     */
    public Athlete(Venue.VenueType inFav, int inStam, int inSkill, boolean inCheat)
    {
	setNumber();
	setFav(inFav);
	setStam(inStam);
	setSkill(inSkill);
	setCheater(inCheat);
    }

    /**
     *The setNumber method will set a unique number for the athlete
     */
    public void setNumber()
    {
	number = ++count;
    }

    /**
     *The getNumber method will return the athlete's number
     *
     *@return int This is the athlete's number
     */
    public int getNumber()
    {
	return number;
    }

    /**
     *The setFav method will set the athlete's favorite venue type to the input
     *
     *@param inFav This is the athlete's venue type
     */
    public void setFav(Venue.VenueType inFav)
    {
	fav = inFav;
    }

    /**
     *The getFav method will return the athlete's favorite venue tpye
     *
     *@return Venue.VenueType This is the athlete's favorite venue type
     */
    public Venue.VenueType getFav()
    {
	return fav;
    }

    /**
     *The setStam method will set the athlete's stamina to the input
     *
     *@param inStam This is the athlete's stamina
     */
    private void setStam(int inStam)
    {
	if (inStam > 100)
	    {
		inStam = 100;
	    }
	else if (inStam < 0)
	    {
		inStam = 0;
	    }
	stam = inStam;
    }


    /**
     *The changeStam method will adjust the athlete's stamina by the given amount
     *
     *@param adjust This is how much the athlete's stamina will be changed by
     *
     *@throws AthleteFaintedException This is thrown if the athlete's stamina reaches zero
     */
    public void changeStam(int adjust)throws AthleteFaintedException
    {
	if (adjust + stam > 100)
	    {
		System.out.println("Athlete " + getNumber() + " is at full Stamina and wasted " + (adjust + stam - 100) + " units of recovered Stamina in the process.");
		stam = 100;
	    }
	else if (adjust + stam <= 0)
	    {
		stam = 0;
		setRest(3);
		throw new AthleteFaintedException();
	    }
	else
	    {
		stam += adjust;
	    }
    }
    
    /**
     *The getStam method will return how much stamina the athlete has left
     *
     *@return int This is how much stamina the athlete has left
     */
    public int getStam()
    {
	return stam;
    }

    /**
     *The setSkill method will set the athlete's skill level to the input
     *
     *@param inSkill This is the athlete's skill
     */
    private void setSkill(int inSkill)
    {
	if (inSkill >= 0 && inSkill <= 10)
	    {
		skill = inSkill;
	    }
    }

    /**
     *The getSkill method will return the athlete's skill level
     *
     *@return int This is the athlete's skill
     */
    public int getSkill()
    {
	return skill;
    }

    /**
     *The setMedals method sets the athletes medals to the input
     *
     *@param inMedals This array is the number of gold, silver, and bronze medals the athlete has
     */
    private void setMedals(int[] inMedals)
    {
	if (inMedals.length == 3 && inMedals[0] >= 0 && inMedals[1] >= 0 && inMedals[2] >= 0)
	    {
		medals = inMedals;
	    }
    }

    /**
     *The addMedal method will add a medal to the athlete's collection
     *
     *@param rank This is the place the athlete took
     */
    public void addMedal(int rank)
    {
	if (rank < 4 && rank > 0)
	    {
		medals[rank-1]++;
		awarded++;
	    }
    }

    /**
     *The getMedals method returns the array of medals the athlete has
     *
     *@return int[] This array is the number of gold, silver, and bronze medals the athlete has
     */
    public int[] getMedals()
    {
	return medals;
    }

    /**
     * The strip method will remove all medals from an athlete if they are found to be cheating
     */
    public void strip()
    {
	nullified += medals[0] + medals[1] + medals[2];
	awarded -= medals[0] + medals[1] + medals[2];
	medals[0] = 0;
	medals[1] = 0;
	medals[2] = 0;
	endorsements =0;
	System.out.println("\n\n\nHELLO EVERYONE!!!  IT TURNS OUT THAT #" +getNumber()+" IS A CHEATER!!!\n\n\n");
    }
}
