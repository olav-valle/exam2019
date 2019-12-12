package com.exam2019;

import java.util.HashMap;

public class AidCentral {

    private final HashMap<Integer, Aid> aidRegistry;
    //each aid has a unique aidID, which work well as the keys in a HashMap
    private final String centralName;

    public AidCentral(String name)
    {
        aidRegistry = new HashMap<>();
        this.centralName = name;
    }
}
