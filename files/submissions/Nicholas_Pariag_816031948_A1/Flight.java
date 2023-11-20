
/** 816031948 */

import java.time.LocalDateTime;
public class Flight
{
    // instance variables
    private String flightNo;  
    private String destination;
    private String origin;
    private LocalDateTime flightDate;
    private LuggageManifest manifest;

    /** Constructor for objects of class Flight*/
    public Flight(String flightNo, String destination, String origin, LocalDateTime flightDate)
    {
     //initialise instance variables
     this.flightNo = flightNo;
     this.destination = destination;
     this.origin = origin;
     this.flightDate = flightDate;
     manifest = new LuggageManifest();
    }

    public String checkInLuggage(Passenger p)
    {
     if(p.getFlightNo().equals(flightNo)) //compare passenger's flight number with the given flight number
     {
      Flight f = new Flight(flightNo, destination, origin, flightDate);
      return manifest.addLuggage(p, f);
     }
     
     return "Invalid flight\n"; //indicate invalid flight
    }
    
    public String printLuggageManifest()
    {
     return manifest.toString();
    }
    
    public static int getAllowedLuggage(char cabinClass) //allowed luggage to carry depends on cabin class
    {
     if(cabinClass == 'F')
      return 3;
     else if(cabinClass == 'B')
      return 2;
     else if(cabinClass == 'P')
      return 1;
     else
      return 0; 
    }
    
    public String toString()
    {
     return getFlightNo() + " DESTINATION: " + getDestination() + " ORIGIN:" + getOrigin() + " " + getFlightDate();
    }
    
    //accessors
    public String getFlightNo()
    {
     return flightNo;
    }
    
    public String getDestination()
    {
     return destination;
    }
    
    public String getOrigin()
    {
     return origin;
    }
    
    public LocalDateTime getFlightDate()
    {
     return flightDate;
    }
    
    public LuggageManifest getManifest()
    {
     return manifest;
    }
}
