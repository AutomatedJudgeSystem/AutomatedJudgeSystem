//816029006
import java.util.ArrayList;
public class LuggageManifest
{
    private ArrayList<LuggageSlip> slips;
    public LuggageManifest()
    {
        slips = new ArrayList<LuggageSlip>();
    }
    public ArrayList<LuggageSlip> getSlips(){
        return slips;
    }
    public String addLuggage(Passenger p, Flight f){
        double excessCost = 0;
        int numAllowed = 0; 
        int i = 0;
        int numLuggage = p.getNumLuggage();
        char cabinClass = p.getCabinClass();
        String label = " $0";
        LuggageSlip l;
        if(numLuggage == 0){
            return p.toString() + " No luggage to add.";
        }
        if(cabinClass == 'F'){
            numAllowed = 3;
        }
        if(cabinClass == 'B'){
            numAllowed = 2;
        }
        if(cabinClass == 'P'){
            numAllowed = 1;
        }
        else{
            numAllowed = 0;
        }
        if(numAllowed > numLuggage){
            for(i=0; i<numLuggage; i++){
                l = new LuggageSlip(p, f);
                slips.add(l);
            }
        }
        else{
            excessCost = getExcessLuggageCost(numLuggage, numAllowed);
            label = " $" + String.format("%.0f",excessCost);
            for(i=0; i<numLuggage; i++){
                l = new LuggageSlip(p, f, label);
                slips.add(l);
            }
        }
        return p.toString() + " Pieces added (" + numLuggage + ") Excess Cost:" + label;
    }
    public double getExcessLuggageCost(int numPieces, int numAllowedPieces){
        return (numPieces - numAllowedPieces) > 0 ? (numPieces - numAllowedPieces) * 35 : 0;
    }
    public String getExcessLuggageCostByPassenger(String passportNumber){
        for(LuggageSlip l: getSlips()){
            if(l.getOwner().getPassportNumber().equals(passportNumber)){
                if(l.getLabel() != "")
                    return l.getLabel();
                else
                    return "No Cost";
            }
        }   
        return "No excess luggage cost found.";
    }
    public String toString(){
        String luggageManifest = "LUGGAGE MANIFEST: \n";
        for(LuggageSlip l: getSlips()){
            luggageManifest += l.toString() + "\n";
        }
        return luggageManifest;
    }
}
