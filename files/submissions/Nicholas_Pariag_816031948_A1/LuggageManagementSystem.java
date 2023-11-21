
/** 816031948 */

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Formatter;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class LuggageManagementSystem
{
    public static void main(String[] args)
    {
     //
     //Flight yyz = new Flight("BW600", "POS", "YYZ", d); 
     String date, flight, passenger;
     Flight f;
     Passenger p;
     
     int numFlights = 0;
     int numPassengers = 0;
     
     Flight[] flights = new Flight[50]; //array to store flight objects
     Passenger[] passengers = new Passenger[50]; //array to store passenger objects
     
     
     try
     {
      File f1 = new File("flights.txt");
      Scanner s1 = new Scanner(f1);
      LuggageManifest l = new LuggageManifest();

      while(s1.hasNextLine())
      {
       flight = s1.nextLine(); //read flight attributes
       
       f = createFlight(flight.split(",")); //create flight after spiltting flight data into its attributes
       flights[numFlights] = f; //add flight object to array
       numFlights++;
      }
      
      s1.close();
     }
     
     catch(FileNotFoundException e)
     {
       System.out.println("Error opening file");
     }
     
     
     try
     {
      File f2 = new File("passengers.txt");
      Scanner s2 = new Scanner(f2);
      LuggageManifest l = new LuggageManifest();

      while(s2.hasNextLine())
      {
       passenger = s2.nextLine(); //read passenger attributes
       p = createPassenger(passenger.split(",")); //create passenger after splitting line into attributes
       passengers[numPassengers] = p; //add passenger object to array
       numPassengers++;
      }
      
      s2.close();
     }
     
     catch(FileNotFoundException e)
     {
       System.out.println("Error opening file");
     }
     
     
     for(int i=0; i < numPassengers; i++)
     {
      Flight fl = searchFlight(flights, numFlights, passengers[i].getFlightNo());
      
      if(fl == null)
      {
       System.out.println(passengers[i].getFlightNo()); //invalid flight number (does not exist)
       System.out.println(passengers[i]); //passenger data
       
       fl = searchFlight(flights, numFlights, flights[numFlights-1].getFlightNo()); //default flight to use when passenger's flight is null
       System.out.println(fl.checkInLuggage(passengers[i]));
      }
      
      else
      {
       System.out.println(fl); //output passenger's valid flight
       System.out.println(fl.checkInLuggage(passengers[i])); //result of checking in passenger's luggage into a flight
      }
     }
     
     System.out.println("LUGGAGE MANIFEST:");
     for(int i=0; i < numFlights; i++)
      System.out.println(flights[i].printLuggageManifest()); //output the passenger's luggage slips for a flight

    }
    
    public static Flight createFlight(String[] flight)
     {
      //determine flight attributes
      String flightNo = flight[0];
      String destination = flight[1];
      String source = flight[2];
      
      LocalDateTime d = LocalDateTime.of(2023, 1, 23, 10, 00, 00); 
 
      //create flight
      Flight f = new Flight(flightNo, destination, source, d);
      return f;
     }
     
    public static Passenger createPassenger(String[] passenger)
    {
     //determine passenger attributes
     String passportNumber = passenger[0];
     String firstName = passenger[1];
     String lastName = passenger[2];
     String flightNo = passenger[3];
     
     //create passenger
     Passenger p = new Passenger(passportNumber, firstName, lastName, flightNo);
     return p;
    }
    
    public static Flight searchFlight(Flight flights[], int numFlights, String flightNo) //searches for a flight number and returns the flight
    {
     for(int i=0; i < numFlights; i++)
     {
      if(flights[i].getFlightNo().equals(flightNo))   
       return flights[i];
     }
     return null; //if flight does not exist
    }
    
}
