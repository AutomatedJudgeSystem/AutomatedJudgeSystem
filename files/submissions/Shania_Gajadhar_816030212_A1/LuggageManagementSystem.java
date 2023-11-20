//Student ID: 816030212
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

//TO DO: make sure all methods are working properly and fix any errors. Clean up program

public class LuggageManagementSystem{
    public static void main(String[] args){
        Passenger p;
        Flight f;
        LuggageSlip ls;
        LuggageManifest lm;
        LocalDateTime t = LocalDateTime.of(2023, 2, 11, 15, 00, 00);
        int count1 = 0;
        int count2 = 0;
        //System.out.println(lm.toString());
        
        //Arrays for each attribute in Passenger class
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        ArrayList<String> passportNumbers = new ArrayList<String>();
        ArrayList<String> firstNames = new ArrayList<String>();
        ArrayList<String> lastNames = new ArrayList<String>();
        ArrayList<String> flightNos = new ArrayList<String>();
        
        //Arrays for attribites in Flight class
        ArrayList<Flight> flights = new  ArrayList<Flight>();
        ArrayList<String> flightNos2 = new ArrayList<String>();
        ArrayList<String> destinations = new ArrayList<String>();
        ArrayList<String> origins = new ArrayList<String>();
        
        //Reading from passenger file
        try{
            File passengersFile = new File("passengers.txt");
            Scanner input = new Scanner(passengersFile);
            String passengerData = " ";
            while(input.hasNextLine()){
                passengerData = input.nextLine();
                String[] variable = passengerData.split(" ");
                
                //adding data to various arrays
                passportNumbers.add(variable[0]);
                firstNames.add(variable[1]);
                lastNames.add(variable[2]);
                flightNos.add(variable[3]);                 
                count1++;
                //LuggageSlip luggageSlipData = new LuggageSlip(p, f);
                //System.out.println(luggageSlipData.toString());
            }
        }
        catch(Exception e){
            
        }
        
        //Reading from flights file
        try{
            File flightsFile = new File("flights.txt");
            Scanner input2 = new Scanner(flightsFile);
            String flightData = " ";
            
            while(input2.hasNextLine()){
                flightData = input2.nextLine();
                String[] variables = flightData.split(" ");
                
                //adding data to various arrays
                flightNos.add(variables[0]);
                destinations.add(variables[1]);
                origins.add(variables[2]);
                count2++;
            }
        }
        catch(Exception e){
            
        }
        
        //Storing passengers' data in passenger array 
        for(int i = 0; i < passportNumbers.size(); i++){
            passengers.add(new Passenger(passportNumbers.get(i), firstNames.get(i), lastNames.get(i),flightNos.get(i)));
        }
        
        //Storing flights' data in flight array 
        for(int j = 0; j < count2; j++){
            //LocalDateTime s = LocalDateTime.of(2023, 2, 11, 15, 00, 00);
            flights.add(new Flight(flightNos.get(j), destinations.get(j), origins.get(j), t));
        }
        
        //Testing cases for passenger class
        System.out.println("Passenger class: \n");
        for(int a = 0 ; a < passengers.size(); a++){
            System.out.println(passengers.get(a));
        }
        
        //Testing cases for flight class
        System.out.println("\nFlight class: \n");
        for(int b = 0 ; b < count2; b++){
            System.out.println(flights.get(b));
        }
        
        //Testing checkInLuggage method (which comprises of addLuggage and getAllowedLuggage)
        System.out.println("\ncheckInLuggage method: \n");
        for(int c = 0; c < count2; c++){
            System.out.println((flights.get(c)).checkInLuggage(passengers.get(c)));
        }
        
        //Testing printLuggageManifest function(which consists of LuggageManifest and LuggageSlip toString())
        System.out.println("\nprintLuggageManifest method: \n");
        for(int d = 0; d < count2; d++){
            System.out.println((flights.get(d)).printLuggageManifest());
        }
        /*
        System.out.println("\nLuggageSlip toString method: \n");
        for(int e = 0; e < count1; e++){
            ls = new LuggageSlip(passengers.get(e), flights.get(e));
            System.out.println(ls.toString());
        }*/
    }
}