//816029006
import java.time.LocalDateTime;
public class Flight
{
    private String flightNo;
    private String destination;
    private String origin;
    private LocalDateTime flightDate;
    private LuggageManifest manifest;
    public String getFlightNo(){
        return flightNo;
    }
    public String getDestination(){
        return destination;
    }
    public String getOrigin(){
        return origin;
    }
    public LocalDateTime getFlightDate(){
        return flightDate;
    }
    public LuggageManifest getManifest(){
        return manifest;
    }
    public Flight(String flightNo, String destination, String origin, LocalDateTime flightDate)
    {
        this.flightNo = flightNo;
        this.destination = destination;
        this.origin = origin;
        this.flightDate = flightDate;
        manifest = new LuggageManifest();
    }
    public String checkInLuggage(Passenger p)
    {
        if(getFlightNo().equals(p.getFlightNo())){
            for(LuggageSlip l: getManifest().getSlips()){
                if(l.getOwner().getPassportNumber().equals(p.getPassportNumber()))
                    return "Passenger with Passport Number " + p.getPassportNumber() + " has already checked in";
            }
            return getManifest().addLuggage(p, this);
        }
        else{
            return "Invalid flight";
        }
    }
    public String printLuggageManifest(){
        return "FLIGHT " + getFlightNo() + " " + getManifest().toString();
    }
    public static int getAllowedLuggage(char cabinClass){
        if(cabinClass == 'F'){
            return 3;
        }
        if(cabinClass == 'B'){
            return 2;
        }
        if(cabinClass == 'P'){
            return 1;
        }
        else{
            return 0;
        }
    }
    public String toString(){
        return getFlightNo() + 
        " DESTINATION: " + getDestination() +
        " ORIGIN: " + getOrigin() + " " + getFlightDate();
    }
}
