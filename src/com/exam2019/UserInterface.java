package com.exam2019;

import java.util.Scanner;

public class UserInterface {

    // Constants representing the different menu choices
    private static final int ADD_HEARING_AID = 1;
    private static final int LIST_ALL_HEARING_AIDS = 2;
    private static final int NEW_RENTAL = 3;
    private static final int REGISTER_AID_RETURN = 4;
    private static final int EXIT = 9;

    private AidCentral aidCentral;



    private UserInterface()
    {
        aidCentral = new AidCentral("Hearing Aid Central");
        addTestAids();
    }
    /**
     * Presents the menu for the user, and awaits input from the user. The menu
     * choice selected by the user is being returned.
     *
     * @return the menu choice by the user as a positive number starting from 1.
     *                 If 0 is returned, the user has entered a wrong value
     */
    private int showMenu()

    {
        int menuChoice = 0;

        System.out.println("\n***** Hearing Aid Central v0.1 *****\n");
        System.out.println("1. Add hearing aid");
        System.out.println("2. List all hearing aids");
        System.out.println("3. New rental. [Not Yet Implemented]");
        System.out.println("4. Register aid item return. [Not Yet Implemented]");

        //TODO: Add more menus


        System.out.println("9. Quit"); // Or another number than 9
        System.out.println("\nPlease select from the menu.\n");
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt())
        {
            menuChoice = sc.nextInt();
        } else
        {
            System.out.println("You must enter a number, not text");
        }
        return menuChoice;
    }



    /**
     * Starts the application. This is the main loop of the application,
     * presenting the menu, retrieving the selected menu choice from the user,
     * and executing the selected functionality.
     */
    public void start() {
        boolean finished = false;

        // The while-loop will run as long as the user has not selected
        // to quit the application
        while (!finished) {
            int menuChoice = this.showMenu();
            switch (menuChoice)
            {
                case ADD_HEARING_AID:
                    addNewAid();
                    break;

                case LIST_ALL_HEARING_AIDS:
                    //TODO refactor to own method if I have time
                    System.out.println("Listing all hearing aids currently registered in system.");
                    System.out.println("--------------------------------------------------------");
                    aidCentral.getAidIterator().forEachRemaining(info -> System.out.println(info));
                    break;

                case NEW_RENTAL:
                    registerNewRental();
                    break;

                case REGISTER_AID_RETURN:
                    registerAidReturn();
                    break;


                case EXIT:
                    System.out.println("Thank you for using the AidCentral app!\n");
                    finished = true;
                    break;

                default:
                    System.out.println("Unrecognized menu selected..");
                    break;
            }
        }
    }

    /**
     * Walks the user through adding a new aid object to registry.
     */
    private void addNewAid()
    {
        //here I still need to add more user feedback earlier in the process, for if the act of adding a new aid
        // fails because it already exists in the list.
        //The registerNewAid method in AidCentral returns a boolean, and this can be used to
        // check if the process was successful.

        boolean continueProcess = true;
        int aidID = -1; //inits aidID to invalid ID
        String aidType = "";//inits aidType to empty string
        //TODO finish user prompts for aid ID number and type description.
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID number of the aid. (A whole number from 1001 to 9999). ");

        if (sc.hasNextInt()) {
             aidID = sc.nextInt();
            sc.nextLine(); //fixes problem of nextInt() leaving a line break behind
            if (aidID > 10000 || 1000 > aidID){ //aidID is invalid
                System.out.println("The aid ID must be a number from 1001 to 9999");
                continueProcess = false;
            }
        }
        else {
            System.out.println("The aid ID must be a number.");
            continueProcess = false; //input was invalid, we are ending the process

            //If I had more time I would like to find a better way of handling this,
            // perhaps by continuing to request a correct ID after telling the user that their
            // input was incorrect, and letting them chose to abort if they wish.
        }

        if(continueProcess){ //previous input was valid
            System.out.println("Enter the type of the aid item.");
            if(sc.hasNext()){ //if there is an input
                aidType = sc.next();
            }
        }
        else {
            System.out.println("Input was invalid, aborting process of adding new aid.");
        }

        if(continueProcess){ //if everything has been correct so far
            Aid newAid = new Aid(aidID, aidType); //make new Aid object
            if(aidCentral.registerNewAid(newAid)){ //hand over newAid to aidCentral
                System.out.println("New aid was successfully created and added!");
                System.out.println(newAid.getAidInfo());//print the info string of the added aid item.
            }
            else {
                System.out.println("There is already an aid with that ID in the system. Aid creation was aborted.");
            }
        }
    }

    /**
     * Walks the user through getting a loan for a hearing aid.
     */
    private void registerNewRental()
    {
        //this method does not work.

        // Here I would also have liked to have added a way to list available aides of a type the user specified
        // by using the getAvailableAidType() method in AidCentral.

        Scanner sc = new Scanner(System.in);
        //If I had more time I would add a way to check user input for correctness,
        // and give more feedback to user in case of error.
        System.out.println("Please enter aid ID of the aid you want to rent.");
        int aidID = sc.nextInt();
        //my plan here was to refactor registerNewRental in AidCentral to take an aid ID
        // instead of an Aid object like it does now.
        sc.nextLine();
        System.out.println("Please enter the name of the person renting the aid.");

    }
    /**
     * Walks the user through registering the return of a rented aid item.
     */
    private void registerAidReturn()
    {
        //I did not have time to implement this method, but it would have involved asking user for
        // the ID of the aid item being retuned, and then calling the registerEndOfRental() method in AidCentral
        System.out.println("Not Yet Implemented.");
    }

    /**
     * Static method to start app.
     * @param args command line arguments as a fixed size array of Strings
     */
    public static void main(String[] args)
    {
        UserInterface ui = new UserInterface();
        ui.start();
    }

    /**
     * adds sample aid items to registry for testing purposes.
     */
    private void addTestAids()
    {
        Aid aid1 = new Aid(1002, "Høreapparat");
            aid1.setRentalStatus(false);
            aid1.setRenterName("Per Olsen");
            aidCentral.registerNewAid(aid1);

        Aid aid2 = new Aid(2003, "Samtaleforsterker");
            aid2.setRentalStatus(false);
            aid2.setRenterName("Trine Jensen");
            aidCentral.registerNewAid(aid2);

        Aid aid3 = new Aid(3003, "Varslingsutstyr");
            aidCentral.registerNewAid(aid3);
    }
}
