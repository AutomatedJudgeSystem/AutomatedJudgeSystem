//Student ID: 816030212
import java.util.ArrayList;
import java.time.LocalDateTime;

public class LuggageManifest{
    private ArrayList<LuggageSlip> slips;
    
 
    //Constructor
    public LuggageManifest(){
        slips = new ArrayList<LuggageSlip>();
    }
    
    //Accessor
    public ArrayList<LuggageSlip> getSlips(){
        return slips;
    }
    
    public String addLuggage(Passenger p, Flight f){
        int allowedPieces;
        double excessCost; 
        String ec;
        
        if(p.getNumLuggage() == 0){
            return "\nNo luggage to add";
        }
        else{
        for(int i = 0; i < p.getNumLuggage(); i++){
            allowedPieces = f.getAllowedLuggage(p.getCabinClass());
            excessCost = getExcessLuggageCost(p.getNumLuggage(),allowedPieces);
            ec = String.valueOf(excessCost);
            LuggageSlip ls = new LuggageSlip(p, f, ec);
            slips.add(ls);
        }
        String output = "\nPP NO: " + p.getPassportNumber() + " NAME: " + p.getFirstName().substring(0,1) +
                        "." + p.getLastName() + " NUMLUGGAGE: " + p.getNumLuggage() + " CLASS: " + p.getCabinClass()
                        + "\n" + "Pieces Added: (" + p.getNumLuggage() + "). " + "Excess Cost: $" + 
                        getExcessLuggageCost(p.getNumLuggage(),f.getAllowedLuggage(p.getCabinClass()));
        return output;
        }
    }
    
    
    public double getExcessLuggageCost(int numPieces, int numAllowedPieces){
        double totalCost = 0;
        
        if(numPieces > numAllowedPieces){
            int excessLuggage = numPieces - numAllowedPieces;
            totalCost = excessLuggage * 35.00;
            return totalCost;
        }
        else{
            totalCost = 0;
            return totalCost;
        }
    }
    
    public String getExcessLuggageCostByPassenger(String passportNumber){
        LocalDateTime t = LocalDateTime.of(2023, 2, 11, 15, 00, 00);
        Flight f = new Flight("","","",t); 
        
        for(int  i = 0; i < slips.size(); i++){
            LuggageSlip ls = slips.get(i);
            if(ls.getOwner().getPassportNumber().equals(passportNumber)){
                //f = new Flight(f.getFlightNo(), f.getDestination(), f.getOrigin(), f.getFlightDate());
                double totalCost = getExcessLuggageCost(ls.getOwner().getNumLuggage(), f.getAllowedLuggage(ls.getOwner().getCabinClass()));
                
                if(totalCost == 0)
                    return "No Cost";
                else
                    return String.valueOf(totalCost);
            }
            else
                return "Passenger not found";
        }
        return null;
    }
    
    public String toString(){
        String output = new String();
        
        for(int i = 0; i < slips.size(); i++){
            LuggageSlip ls = slips.get(i);
            output = ls.toString();
        }
        return output;
    }
}