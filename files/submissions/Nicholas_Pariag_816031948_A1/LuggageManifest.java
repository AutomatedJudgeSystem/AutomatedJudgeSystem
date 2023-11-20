
/** 816031948 */

import java.util.ArrayList;
import java.util.Iterator;

public class LuggageManifest
{
    // instance variables
    private ArrayList <LuggageSlip> slips;

    /** Constructor for objects of class LuggageManifest */
    public LuggageManifest()
    {
     // initialise instance variables
     slips = new ArrayList <> ();
    }
    
    /** Add one or more new LuggageSlip objects to the slips collection based on the number of pieces of 
      luggage that a Passenger has */
      
    public String addLuggage(Passenger p, Flight f)
    {
     int numAllowedLuggage = f.getAllowedLuggage(p.getCabinClass()); //check the number of allowed pieces
     int numLuggage = p.getNumLuggage();
     
     slips.add(new LuggageSlip(p, f));
     
     double excessLuggageCost = getExcessLuggageCost(numLuggage, numAllowedLuggage); //calculate the cost of excess luggage
     String label = getExcessLuggageCostByPassenger(p.getPassportNumber()); // update label - the excess luggage cost is the new label
     
     String s = "No Luggage to add \n";
     
     for(int i=0; i<numLuggage; i++)
     {
      if(numLuggage == 0)
       s = "No Luggage to add \n"; //indicate if a passenger has no luggage to add
       
      else if(label.equals("No Cost"))
      {
       slips.add(new LuggageSlip(p, f, " "));
       s = "Pieces Added: " + "(" + (int)excessLuggageCost/35 + ")." +
                       " Excess Cost: $" + (int)excessLuggageCost + "\n"; //output extra luggage added and the cost
      }
       
      else
      {
       s = "Pieces Added: " + "(" + (int)excessLuggageCost/35 + ")." +
                       " Excess Cost: $" + (int)excessLuggageCost + "\n";
       slips.add(new LuggageSlip(p, f, label)); //add label
      }
     }
     
     System.out.println(p);
     return s;
    }
    
    public double getExcessLuggageCost(int numPieces, int numAllowedPieces)
    {
     double excessLuggageCost;
     
     if(numPieces > numAllowedPieces)
      excessLuggageCost = (numPieces - numAllowedPieces) * 35; //calculates the cost of excess luggage
     else
      excessLuggageCost = 0;
      
     return excessLuggageCost;
    }
    
    public String getExcessLuggageCostByPassenger(String passportNumber)
    {
     /** Returns the total cost of excess luggage (if any) on 
     the manifest for a Passenger with a given passport 
     number*/
     
     String s = "No Cost"; //if there is no excess cost
    
     for(LuggageSlip l: slips)
     {
      if(l.hasOwner(passportNumber)) //search for passenger's passport number
      {
       if(getExcessLuggageCost(l.getOwner().getNumLuggage(), Flight.getAllowedLuggage(l.getOwner().getCabinClass())) > 0)
        s = "$" + (int)getExcessLuggageCost(l.getOwner().getNumLuggage(), 
                                            Flight.getAllowedLuggage(l.getOwner().getCabinClass())); //output excess luggage cost, if any
      }
     }
     return s;
    }
    
    public String toString()
    {
     /** Return a String representation of the aggregated 
         state of a LuggageManifest by traversing and 
         returning the String representation of each 
         LuggageSlip (if present) */
     
     Iterator<LuggageSlip> iter = slips.iterator();
     String s = "\n";
     
     while(iter.hasNext())
     { // while there are slips in the ArrayList
      LuggageSlip l = iter.next(); // get the next slip in list
      Passenger owner = l.getOwner();
      
      if(!l.getLabel().equals("")) //output slip objects that have an updated label
       s = s + l.toString() + "\n";
     }
     return s;
    }

}
