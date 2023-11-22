//816029006
public class LuggageSlip
{
    private Passenger owner; 
    private static int luggageSlipIDCounter = 1;
    private String luggageSlipID;
    private String label;
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
    public LuggageSlip(Passenger p, Flight f)
    {
        owner = p;
        luggageSlipID = p.getFlightNo() + "_" + p.getLastName() + "_" + getLuggageSlipIDCounter();
        luggageSlipIDCounter++;
        label = "";
    }
    public LuggageSlip(Passenger p, Flight f, String label)
    {
        owner = p;
        luggageSlipID = p.getFlightNo() + "_" + p.getLastName() + "_" + getLuggageSlipIDCounter();
        luggageSlipIDCounter++;
        this.label = label;
    }
    public boolean hasOwner(String passportNumber)
    {
        return getOwner().getPassportNumber().equals(passportNumber);
    }
    public String toString(){
        return getLuggageSlipID() + 
        " PP NO. " + getOwner().getPassportNumber() + 
        " NAME: " + getOwner().getFirstName().substring(0,1) + 
        "." + getOwner().getLastName() + 
        " NUMLUGGAGE: " + getOwner().getNumLuggage() + 
        " CLASS: " + getOwner().getCabinClass() + getLabel();
    }
}
