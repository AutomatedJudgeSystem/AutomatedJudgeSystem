//816029006
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
public class LuggageManagementSystem
{
    public static void main (String[] args){
        String[] str;
        ArrayList<Flight> flights = new ArrayList<Flight>();
        Scanner scanner;
        Passenger p;
        try{
            File file = new File("Flights.txt");
            scanner = new Scanner(file);
            System.out.println("Processing Flights.txt...");
            while(scanner.hasNextLine()){
                str = scanner.nextLine().split(" ");
                if(str.length < 9){
                    System.out.println("!!Too few arguments for Flight");
                }
                else if(str.length > 9){
                    System.out.println("!!Too many arguments for Flight");
                }
                else if(str[0].length() != 6){
                    System.out.println("!!Invalid Flight No.");
                }
                else if(str[1].length() != 3){
                    System.out.println("!!Invalid Destination");
                }
                else if(str[2].length() != 3){
                    System.out.println("!!Invalid Origin");
                }
                else{
                    try{
                        LocalDateTime d = LocalDateTime.of(Integer.parseInt(str[3]),Integer.parseInt(str[4]), Integer.parseInt(str[5]), Integer.parseInt(str[6]), Integer.parseInt(str[7]), Integer.parseInt(str[8]));                       
                        Flight f = new Flight(str[0], str[1], str[2], d);                    
                        flights.add(f);
                        System.out.println(f);
                    }
                    catch(Exception e){
                        System.out.println("!!Invalid arguments for Flight Date");
                    }
                }
            }
            scanner.close();
        }
        catch(Exception e){
            System.out.println("!!File 'Flights.txt' not found");
        }
        System.out.println("...Flights.txt processed successfully\n");
        try{
            File file = new File("Passengers.txt");
            scanner = new Scanner(file);
            System.out.println("Processing Passengers.txt...");
            while(scanner.hasNextLine()){
                str = scanner.nextLine().split(" ");
                if(str.length < 4){
                    System.out.println("!!Too few arguments for Passenger");
                }
                else if(str.length > 4){
                    System.out.println("!!Too many arguments for Passenger");
                }
                else if(str[0].length() != 8){
                    System.out.println("!!Invalid Passport Number");
                }
                else if(str[3].length() != 6){
                    System.out.println("!!Invalid Flight No.");
                }
                else {
                    boolean flightNotFound = true;
                    for(Flight f: flights){
                        if(f.getFlightNo().equals(str[3])){
                            p = new Passenger(str[0], str[1], str[2], str[3]);                    
                            System.out.println(f.checkInLuggage(p));
                            flightNotFound = false;
                        }
                    }
                    if(flightNotFound){
                        System.out.println("!!Invalid Flight No.");
                    }
                }
            }
            scanner.close();
        }
        catch(Exception e){
            System.out.println("!!File 'Passengers.txt' not found");
        }
        System.out.println("...Passengers.txt processed successfully\n");
        for(Flight f: flights){
            System.out.println(f.printLuggageManifest());
        }
    }
}
