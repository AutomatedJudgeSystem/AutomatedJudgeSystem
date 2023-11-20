
/**
 The Passenger class models passenger 
 who checks zero or more pieces of luggage into a flight

 816031948
 */

import java.util.Random;

public class Passenger
{
    // instance variables
    private String passportNumber;
    private String flightNo;
    private String firstName; 
    private String lastName; 
    private int numLuggage;
    private char cabinClass;

    /**Constructor for objects of class Passenger*/
    Passenger (String passportNumber, String firstName, String lastName, String flightNo)
    {
     this.passportNumber = passportNumber;
     this.firstName = firstName;
     this.lastName = lastName;
     this.flightNo = flightNo;
     this.numLuggage = getRandomNumber(0,4);
     assignRandomCabinClass();
    }

    //mutators
    public void assignRandomCabinClass() //assign a random number to determine cabin class
    {
     int i = getRandomNumber(0,4);
     
     if(i==0)
      cabinClass = 'B';
    
     else if(i==1)
      cabinClass = 'E';
      
     else if(i==2)
      cabinClass = 'F';
      
     else if(i==3)
      cabinClass = 'P';
    }
    
    
    //accessors
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
    
    
    private static int getRandomNumber(int min, int max)
    {
     return (int) ((Math.random() * (max - min)) + min);   
    }
    
    public String toString()
    {
     String s = "PP NO. " + getPassportNumber() + " NAME: " + getFirstName().toUpperCase().charAt(0) + ". " + getLastName().toUpperCase() + " NUMLUGGAGE: " + getNumLuggage() + " CLASS: " + getCabinClass();
     return s;
    }
}
