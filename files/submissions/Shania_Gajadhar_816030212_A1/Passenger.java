//Student ID: 816030212
import java.util.Random;
import java.util.ArrayList;

public class Passenger{
    //Attributes
    private String passportNumber;
    private String flightNo;
    private String firstName;
    private String lastName;
    private int numLuggage;
    private char cabinClass;
    
    //Constructor
    public Passenger(String passportNumber, String firstName, String lastName, String flightNo){
        //Passenger p = new Passenger(passportNumber,firstName,lastName,flightNo);
        this.passportNumber = passportNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.flightNo = flightNo;
        determineNumLuggage();
        assignRandomCabinClass();
    }
    
    private void determineNumLuggage(){ //helper method to set numLuggage
        Random random = new Random();
        numLuggage = random.nextInt(4);
    }
    
    //Accessors
    public String getPassportNumber(){
        return passportNumber;
    }
    
    public String getFlightNo(){
        return flightNo;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public int getNumLuggage(){
        return numLuggage;
    }
    
    public char getCabinClass(){
        return cabinClass;
    }
    
    public String toString(){
        String details = "PP NO: " + getPassportNumber() + " NAME: " + getFirstName().substring(0,1)
                        + "." + getLastName() + " NUMLUGGAGE: " + getNumLuggage() +
                        " CLASS: " + getCabinClass() + " FLIGHTNO: " + getFlightNo();
        return details;
    }
    
    private void assignRandomCabinClass(){
        //ArrayList<Passenger> cabins = new ArrayList<Passenger>();
        //classes.add(new Passenger('F'));
        //int index = random.nextInt(classes.size());
        //for(int i = 0; i < 4; i++){
         //   cabins.add(new Passenger(classes[i]));
        //} 
        char[] classes = {'F','B','P','E'};
        Random random = new Random();
        int index = random.nextInt(4);
        
        cabinClass = classes[index];
    }
    
}
