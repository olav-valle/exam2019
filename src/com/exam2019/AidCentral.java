package com.exam2019;

import java.util.HashMap;
import java.util.Iterator;

public class AidCentral {

    private final HashMap<Integer, Aid> aidRegistry;
    //each aid has a unique aidID, which work well as the keys in a HashMap
    private final String centralName;

    public AidCentral(String name)
    {
        aidRegistry = new HashMap<>();
        this.centralName = name;
    }

    /**
     * Adds an existing aid object to the registry. Fails if registry already contains an aid object with an
     * aid ID number that is identical to the one being added.
     * @param aid the aid object to be added
     * @return true if aid object was successfully added, false if it failed
     */
    public boolean registerNewAid(Aid aid)
    {
        boolean addedSuccessfully;
        if(aid != null) { //check that we're not being passed a null object parameter

            if (aidRegistry.containsKey(aid.getAidID())) {

                addedSuccessfully = false; //aid was not added to HashMap because it already contains an aid with the same ID.

                //here we could also use the compareID() method in the Aid class, but we already have aidID's
                // of all aids in registry stored as keys for the HashMap.
            }
            else {
                aidRegistry.put(aid.getAidID(), aid); //HashMap key is aidID, value is Aid object
                addedSuccessfully = true;
            }
        }
        else {
            addedSuccessfully = false; //if aid object was null
        }
        return addedSuccessfully; // confirms if adding was a success or not
    }

    /**
     * Registers a new rental of an aid with the specified aid ID number, to the person specified. Requires that a
     * name is given for the person renting the aid.
     * Fails if aid item is already being rented by someone else.
     * Returns false if rental failed, and true if rental succeded.
     * @param aid the aid item that is to be rented out
     * @param renterName the name of the renter
     */

    public boolean registerNewRental(Aid aid, String renterName)
    {
        //TODO refactor to use int aidID as parameter instead of Aid object.
        boolean success = false; //assume rental process failed to start

        if(aid.getRentalAvailability()){ //if aid is available for rental

            if(renterName == null || renterName.isEmpty()){ //if renter name is non-null and not blank
                //Here I would try to add check for if renter name is just whitespace " ",
                // or if it's too short but I'm not sure I have time

                renterName = "INVALID_RENTER_NAME"; //name of renter was invalid
            }
            else{ // aid is available, and renter name is OK, or has been marked as invalid

                aidRegistry.put(aid.getAidID(), aid);
                success = true;
            }
        }
        else {
            success = false; //aid was not available for rental
        }
        return success;
    }

    /**
     * Registers an aid item as returned, marking it as available for loan and removing name of current renter.
     * Fails if the aid item is not currently being rented by anyone.
     * @param aid the aid item that is being returned.
     * @return true if item was successfully marked as returned, false if not.
     */
    public boolean registerEndOfRental(Aid aid)
    {
        //TODO refactor to use int aidID as parameter
        boolean success = false;
        if(!aid.getRentalAvailability()){ //if aid item is set as rented
            aid.setRenterName(""); //sets renter name blank
            aid.setRentalStatus(true);
            success = true;
        }

        return success;
    }

    /**
     * Returns iterator of strings describing all aid objects held in registry.
     * @return Iterator of strings describing all aid objects held in registry.
     */
    public Iterator getAidIterator()
    {
        return aidRegistry.values().stream().map(Aid::getAidInfo).iterator();
    }

    /**
     * Returns iterator of aid objects containg all available aids of the specified type.
     * Iterator will be empty if no aid items matched the type.
     * @param type String of the type of the aid items to find.
     */
    public Iterator getAvailableAidType(String type)
    {
        return aidRegistry.values().stream() //makes a stream of values in HashMap
                .filter(Aid::getRentalAvailability) //filters those that are available
                .filter(aid -> aid.getAidInfo().equals(type)) //filters those that match specified type
                .iterator(); //returns iterator of the remaining aid items.
    }


}
