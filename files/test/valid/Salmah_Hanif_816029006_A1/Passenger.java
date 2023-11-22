//816029006
import java.util.Random;
public class Passenger
{
    private String passportNumber;
    private String flightNo;
    private String firstName;
    private String lastName;
    private int numLuggage;
    private char cabinClass;
    public String getPassportNumber()
    {
        return passportNumber;
    }
    public String getFlightNo()
    {
        return flightNo;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public int getNumLuggage()
    {
        return numLuggage;
    }     
    public char getCabinClass()
    {
        return cabinClass;
    }
    public Passenger(String passportNumber, String firstName, String lastName, String flightNo)
    {
        Random r = new Random();
        this.passportNumber = passportNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.flightNo = flightNo;
        this.numLuggage = r.nextInt(4);
        assignRandomCabinClass();
    }
    private void assignRandomCabinClass(){
        Random r = new Random();
        int x = r.nextInt(4);
        if(x == 3){
            this.cabinClass = 'F';
        }
        if(x == 2){
            this.cabinClass = 'B';
        }
        if(x == 1){
            this.cabinClass = 'P';
        }
        else{
            this.cabinClass = 'E';
        }
    }
    public String toString(){
        return "PP NO. " + getPassportNumber() + 
        " NAME: " + getFirstName().substring(0,1) + "." + getLastName() + 
        " NUMLUGGAGE: " + getNumLuggage() + 
        " CLASS: " + getCabinClass();
    }
}
