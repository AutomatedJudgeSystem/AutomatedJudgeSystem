//Student ID: 816030212
import java.time.LocalDateTime;

public class Flight{
    private String flightNo;
    private String destination;
    private String origin;
    private LocalDateTime flightDate;
    private LuggageManifest manifest;
    
    public Flight(String flightNo, String destination, String origin, LocalDateTime flightDate){
        this.flightNo = flightNo;
        this.destination = destination;
        this.origin = origin;
        this.flightDate = flightDate;
        this.manifest = new LuggageManifest();
    }
    
    //Accessors
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
    
    public String checkInLuggage(Passenger p){
        Flight f = new Flight(getFlightNo(), getDestination(), getOrigin(), getFlightDate());
        
        if(p.getFlightNo().equals(getFlightNo())){
            String addingLuggage = manifest.addLuggage(p, f);
            return addingLuggage;
        }
        else    
            return "Invalid flight";
    }
    
    public String printLuggageManifest(){
        String output = manifest.toString();
        return output;
    }
    
    public static int getAllowedLuggage(char cabinClass){
        switch(cabinClass){
            case 'F': return 3;
            case 'B': return 2;
            case 'P': return 1;
            case 'E': return 0;
        }
        return 0;
    }
    
    public String toString(){
        String flightDetails = getFlightNo() + " DESTINATION: " + getDestination() + " ORIGIN: " + getOrigin() + " " + getFlightDate();
        return flightDetails;
    }
}