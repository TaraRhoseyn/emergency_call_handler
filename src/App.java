// imports
import java.util.*;
import java.io.*;


public class App {
    // class variables
    public static String [][] callTable = new String[20][6];
    public static boolean doContinue = true;
    public static int callCounter = 0;
    public static String incidentType = "";
    public static String unitTeam = "";
    public static String hashes = "------------------------------";
    // method to return incident type from user selection menu
    public static String findIncidentType(int s) {
        switch(s) {
            case 1:
                incidentType = "Road traffic collision";
                break;
            case 2:
                incidentType = "Assault";
                break;
            case 3:
                incidentType = "Murder";
                break;
            case 4:
                incidentType = "Brandishing a weapon";
                break;
            case 5:
                incidentType = "Theft/Burglary/Criminal damage";
                break;
            default:
                System.out.print("Error in switch statement for finding incident type");
        }
        return incidentType;
    }
    // method to return team unit from user selection menu
    public static String findUnitTeam(int s) {
        switch(s) {
            case 1:
                unitTeam = "Armed Response Unit";
            case 2:
                unitTeam = "Traffic Response";
                break;
            case 3:
                unitTeam = "Drunk/drug driving";
                break;
            case 4:
                unitTeam = "Road Closures";
                break;
            case 5:
                unitTeam = "Road Traffic Collision";
                break;
            case 6:
                unitTeam = "Theft";
                break;
            case 7:
                unitTeam = "Personal Injury (Assault)";
                break;
            case 8:
                unitTeam = "MIT Murder Investigation Team";
                break;
            default:
                System.out.print("Error in switch statement for finding unit team.");
        }
        return unitTeam;
    }
    // method to update 2d array of emergency calls
    public static void updateTable(int callCounter, String date, String time, String incidentType, String location, String desc, String teamUnit) {
        callTable[callCounter][0] = date;
        callTable[callCounter][1] = time;
        callTable[callCounter][2] = incidentType;
        callTable[callCounter][3] = location;
        callTable[callCounter][4] = desc;
        callTable[callCounter][5] = teamUnit;
    };

    // method to read cvs data

    public static void readData() {
        
    }

   



    // main method
    public static void main(String[] args) {
        try {
            // inits file
            FileWriter file = new FileWriter("file.csv");
            file.write("---- EMERGENCY CALL HANDLING ---- \n");
            file.write("Date:  Time:  Incident Type:  Location:  Description:  Team Assigned to Incident: \n");
            System.out.println("---- EMERGENCY CALL HANDLING ---- \n");
            System.out.println("This is an emergency call handling system. You will be asked a series of questions to log incidents to send out emergency teams. You are only able to log 20 calls. ");
            System.out.println(hashes);
            Scanner sc = new Scanner(System.in);
            // user input:
            while (doContinue && callCounter <= 19 ) {
                
                System.out.println("\nPlease enter the date of incident: ");
                String date = sc.nextLine();
                System.out.println(hashes+"\nPlease enter the time of incident: ");
                String time = sc.nextLine();
                System.out.println(hashes+"\nPlease select the following number that best describes the incident you're calling about:" +
                    " \nRoad traffic collision -- 1 \nAssault -- 2 \nMurder -- 3 \nBrandishing a weapon -- 4 \nTheft/Burglary/Criminal damage -- 5");
                int incidentSelector = sc.nextInt();
                String incidentType = findIncidentType(incidentSelector);
                // clears scanner:
                sc.nextLine();
                System.out.println(hashes+"\nPlease enter the location of incident: ");
                String location = sc.nextLine();
                System.out.println(hashes+"\nPlease describe the incident (max 256 characters): ");
                String desc = sc.nextLine();
                // tests if desc has too many chars
                if (desc.length() > 256) {
                    System.out.println("The description can only be 256 characters long. Your description is "+desc.length()+" characters long"+
                        "\nPlease try again:");
                    desc = "";
                    System.out.println(hashes+"\nPlease describe the incident (max 256 chars): ");
                    desc = sc.nextLine();
                };
                System.out.println(hashes+"\nPlease type and enter the corresponding number to assign a response unit to the incident:" +
                    "\nRoad Traffic Collision Investigation Unit:"+
                    "\n\tArmed Response Unit -- 1"+
                    "\n\tSpeeding -- 2"+"\n\tDrunk/drug driving -- 3"+"\n\tRoad Closures -- 4"+
                    "\n\tRoad Traffic Collision -- 5"+"\nCriminal Investigation Department:"+
                    "\n\tTheft -- 6"+"\n\tPersonal Injury(Assault) -- 7"+"\n\t Murder Investigation Team -- 8");
                int unitSelector = sc.nextInt();
                String teamUnit = findUnitTeam(unitSelector);
                sc.nextLine();
                int callsLeft = 20 - callCounter-1;
                System.out.println("------- NEW CALL ADDED--------");
                System.out.println(callsLeft+" call/s remaining that can be added.");
                System.out.println(hashes);
                updateTable(callCounter, date, time, incidentType, location, desc, teamUnit);
                callCounter++;
                // menu for users to proceed or save where they are
                if (callsLeft != 0) {
                    System.out.println("Do you wish to add another call? Type and enter the following numbers for your response: \nYES -- 1 \nNO -- 2" );
                    int continueStatus = sc.nextInt();
                    if (continueStatus == 2) {
                        doContinue = false;
                    };
                } else {
                    System.out.println("All 20 calls have been saved. A CVS file will now be produced.");
                }
            }
            System.out.println("All calls have been saved.");
            // displays the 2d array
            // for (int i = 0; i < callTable.length; i++) {
            //     System.out.println(Arrays.toString(callTable[i]));
            // };
            // iterates over 2d array and adds rows to file
            for (int i = 0; i < callTable.length; i++) {
                // not a particularly robust method of weeding out the nulls:
                if (callTable[i][0] != null) {
                    String row = (callTable[i][0] + ", " + callTable[i][1] + ", " + callTable[i][2] +
                    ", " + callTable[i][3] + ", " + callTable[i][4] + ", " + callTable[i][5]) + "\n";
                    file.write(row);
                }
            }
            file.close();
            // System.out.println("Your calls have been saved to a CSV file.\nDo you wish to view these calls? Type and enter the following numbers for your response:\nYES -- 1 \nNO -- 2");
            // int yesFile = sc.nextInt();
            sc.close();
           



            // reads data frome file:
            ArrayList<ArrayList<String>> calls = new ArrayList<ArrayList<String>>();
            int counterRowCounter = 0;
            File newfile = new File("file.csv");
            Scanner inputFile = new Scanner(newfile);
            while (inputFile.hasNextLine()) {
                String line = inputFile.nextLine();
                String values[] = line.split(",");
                calls.add(new ArrayList<String>(Arrays.asList(values)));
                counterRowCounter++;
            };
            inputFile.close();
            System.out.println(calls);
            // closes file
            
            

            
            
        }
        // handles user inputting wrong data type, eg continueStatus question
        catch (InputMismatchException e) {
            System.out.println("Oops. You've tried to enter information of the wrong type, eg letters instead of numbers. \n");
            e.printStackTrace();
        }
        // handles io (file) exceptions:
        catch (IOException e) {
            System.out.println("An IO exception has occurred: ");
            e.printStackTrace();
        }
        // generic error handler:
        catch (Exception e) {
            System.out.println("An exception has occurred:");
            e.printStackTrace();
        }
    };
};