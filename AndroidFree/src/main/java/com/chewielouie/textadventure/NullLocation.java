package com.chewielouie.textadventure;

import java.util.ArrayList;
import java.util.List;

public class NullLocation implements ModelLocation {
    public void addExit( String exitLabel, String destinationId ) {
    }

    public boolean exitable( String exitLabel ) {
        return false;
    }

    public String exitDestinationFor( String exitLabel ) {
        return "";
    }

    public String id() {
        return "";
    }

    public List<String> exits() {
        return new ArrayList<String>();
    }

    public String description() {
        return "";
    }
}
