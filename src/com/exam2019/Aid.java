package com.exam2019;

import java.util.Iterator;

/**
 * Class that represents a hearing aid item.
 */
public class Aid {

    //immutable fields that describing the aid item
    private final int aidID;
    private final String aidType;
    //aidID and aidType are final because they should not need to be changed after object has been created

    // mutable fields representing the aids current rental status, and name of renter (if any)
    private boolean availableForRent;
    private String nameOfRenter;

    /**
     * Constructor for a hearing aid object.
     * @param ID int number ID of aid, from 1001 to 9999
     * @param type string describing aid, or the type of aid
     */

    public Aid(int ID, String type){

        if(1000 < ID && ID < 10000) //ID must be from 1001 inclusive, to 9999 inclusive
        {
            this.aidID = ID;
        }
        else{ aidID = -1; } //ID is set to -1 to indicate an invalid ID

        if(type != null){ //test type string != null
            this.aidType = type;
        }
        else{this.aidType = "INVALID_TYPE";}
        //It would perhaps be a good idea to change aid types to a collection of enums,
        // to ensure that aides of same type always have the same string describing them, i.e.
        // if there are many aides of the same make/model, but they would still have unique aidID numbers.

        availableForRent = true; // a new aid is added to system with available status set to true.

        nameOfRenter = ""; //since aid is not rented, there is no renter name.
        // Empty strings can be checked using the String class method isEmpty(), which we can use for if-checks later.
    }

    //getters for all fields

    /**
     * Returns aid ID number.
     * @return aid ID number.
     */
    public int getAidID()
    {
        return aidID;
    }

    /**
     * Returns string describing aid or the type of the aid.
     * @return string describing aid or the type of the aid.
     */
    public String getAidType()
    {
        return aidType;
    }

    /**
     * Returns boolean representing the rental availability of the aid.
     * @return true if available, false if not currently available
     */
    public boolean getRentalAvailability()
    {
         return (availableForRent); // availableForRent = true means available.
    }

    /**
     * Returns the name of the current renter of the aid.
     * @return the name of the current renter of the aid
     */
    public String getNameOfRenter(){
        return nameOfRenter;
    }

    //getter for a string describing the aid and its current properties.
    /**
     * Returns a string describing status and info about aid. String format is:
     * "3003 Varslingsutstyr utleid til Per Olsen" if aid is being rented,
     * or "3003 Varslingsutstyr ledig" if aid is not currently rented
     * @return string describing status and info about aid
     */
    public String getAidInfo()
    {
        String infoString = "" + aidID + " " + aidType;
        if(!availableForRent){ // adds renter info if aid is being rented by someone
            infoString = infoString + " utleid til " + nameOfRenter;
        }
        else {
            infoString = infoString + " ledig";
        }
        return infoString;
    }

    /**
     * Sets the rental status to the boolean parameter newStatus, and returns true if status was successfully changed.
     * If newStatus is the same as the current status, the change fails and methods returns false.
     * @param newStatus boolean true if object is being returned, false if object is being rented out.
     * @return boolean true if rental status was successfully changed to the newStatus, false if it was not
     */
    public boolean setRentalStatus(boolean newStatus)
    {
        boolean success = false; //assume status change has failed as default

        if(availableForRent != newStatus){ // tests if current status is same as the new one
            availableForRent = newStatus; // set new rental status
            success = true;
        }

        return success;
    }

    /**
     * Sets name of renter to the specified string.
     * @param nameOfNewRenter the name of the new renter
     */
    public void setRenterName(String nameOfNewRenter)
    {
        //TODO add a test to check if there is a current renter when a new one is set?
        nameOfRenter = nameOfNewRenter;
    }

    /**
     * Compares this aids ID to the ID of aid passed as parameter, and returns boolean true if the two ID's are a match.
     * @param aid the aid object to compare this object with
     * @return true if ID's are a match
     */
    public boolean compareID(Aid aid){
        return (this.aidID == aid.getAidID()); //true if ID matches aidID
    }


}
