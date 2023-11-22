//Student ID: 816030212
import java.util.ArrayList;

public class LuggageSlip{
    //Attributes
    private Passenger owner;
    private static int luggageSlipIDCounter = 1;
    private String luggageSlipID;
    private String label;
    
    //Constructor
    public LuggageSlip(Passenger p, Flight f){
        owner = p;
        //luggageSlipID = p.getFlightNo() + "_" + p.getLastName() + "_" + luggageSlipIDCounter; 
        //luggageSlipIDCounter++;
        setLuggageSlipID(p);
        label = "";
    }
    
    public LuggageSlip(Passenger p, Flight f, String label){
        this(p, f); //calls previous constructor
        this.label = label;
    }
    
    //Accessors
    public Passenger getOwner(){
        return owner;
    }
    
    public int getLuggageSlipIDCounter(){
        return luggageSlipIDCounter;
    }
    
    public String getLuggageSlipID(){
        return luggageSlipID;
    }
    
    public String getLabel(){
        return label;
    }
    
    public boolean hasOwner(String passportNumber){
        if(this.owner.getPassportNumber().equals(passportNumber))
            return true;
        else    
            return false;
    }
    
    public String toString(){
        String luggageSlipDetails = getLuggageSlipID() + " PP NO: " + getOwner().getPassportNumber() + " NAME: " + 
                                    getOwner().getFirstName().substring(0,1) + "." + getOwner().getLastName() + " NUMLUGGAGE: " +
                                    getOwner().getNumLuggage() + " CLASS: " + getOwner().getCabinClass() + " $" + getLabel(); 
        return luggageSlipDetails;
    }
    
    private void setLuggageSlipID(Passenger p){
        luggageSlipID = p.getFlightNo() + "_" + p.getLastName() + "_" + luggageSlipIDCounter;
        luggageSlipIDCounter++;
        
    }    
    
}
