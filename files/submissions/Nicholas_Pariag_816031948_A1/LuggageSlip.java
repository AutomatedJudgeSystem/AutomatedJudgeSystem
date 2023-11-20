
/**
  models a paper slip that is generated for a piece of luggage when it is 
  checked into a flight for a given passenger
  
  816031948
 */
public class LuggageSlip
{
    // instance variables
    private Passenger owner;
    private static int luggageSlipIDCounter = 1;
    private String luggageSlipID;
    private String label;

    /** Constructor for objects of class LuggageSlip*/
    public LuggageSlip(Passenger p, Flight f)
    {
     owner = p;
     luggageSlipID = f.getFlightNo() + "_" + owner.getLastName() + "_" + getLuggageSlipIDCounter();
     label = "";
    }
    
    public LuggageSlip(Passenger p, Flight f, String label)
    {
     owner = p;
     luggageSlipID = f.getFlightNo() + "_" + owner.getLastName() + "_" + getLuggageSlipIDCounter();
     this.label = label;
     luggageSlipIDCounter++;
    }
    
    public boolean hasOwner(String passportNumber) //checks if owner has the given passport number
    {
     return owner.getPassportNumber().equals(passportNumber);
    }
    
    public String toString()
    {
     return getLuggageSlipID() + " PP NO. " + owner.getPassportNumber() + " NAME: " + owner.getFirstName().toUpperCase().charAt(0) + ". " + owner.getLastName().toUpperCase() + " NUMLUGGAGE: " + owner.getNumLuggage() + " CLASS: " + owner.getCabinClass() + " " + getLabel();    
    } 
    
    //accessors
    public Passenger getOwner()
    {
     return owner;
    }
    
    public int getLuggageSlipIDCounter()
    {
     return luggageSlipIDCounter;
    }
    
    public String getLuggageSlipID()
    {
     return luggageSlipID;
    }
    
    public String getLabel()
    {
     return label;
    }
}
