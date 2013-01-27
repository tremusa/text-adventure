package com.chewielouie.textadventure;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.jmock.*;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class BasicModelTests {

    private Mockery mockery = new Mockery();

    @Test
    public void asking_for_the_current_location_before_one_is_set_returns_a_default_null_location() {
        BasicModel model = new BasicModel();

        assertTrue( model.currentLocation() instanceof NullLocation );
    }

    @Test
    public void first_location_added_is_the_starting_location() {
        Location loc1 = new Location( "loc1" );
        Location loc2 = new Location( "loc2" );
        BasicModel model = new BasicModel();
        model.addLocation( loc1 );
        model.addLocation( loc2 );

        assertEquals( loc1, model.currentLocation() );
    }

    @Test
    public void leaving_a_location_changes_the_current_location() {
        Location loc1 = new Location( "loc1" ) {{
            addExit( "north", "loc2" );
        }};
        Location loc2 = new Location( "loc2" );
        BasicModel model = new BasicModel();
        model.addLocation( loc1 );
        model.addLocation( loc2 );

        model.moveThroughExit( "north" );

        assertEquals( loc2, model.currentLocation() );
    }

    @Test
    public void leaving_a_location_by_an_invalid_exit_does_not_change_the_current_location() {
        Location loc1 = new Location( "loc1" ) {{
            addExit( "north", "loc2" );
        }};
        Location loc2 = new Location( "loc2" );
        BasicModel model = new BasicModel();
        model.addLocation( loc1 );
        model.addLocation( loc2 );

        model.moveThroughExit( "not an exit" );

        assertEquals( loc1, model.currentLocation() );
    }

    @Test
    public void current_location_description_is_taken_from_the_current_location() {
        final ModelLocation location = mockery.mock( ModelLocation.class );
        final String description = "description of this location";
        mockery.checking( new Expectations() {{
            oneOf( location ).description();
            will( returnValue( description ) );
            ignoring( location );
        }});
        BasicModel model = new BasicModel();
        model.addLocation( location );

        assertEquals( description, model.currentLocationDescription() );
    }

    @Test
    public void current_location_exits_are_taken_from_the_current_location() {
        final ModelLocation location = mockery.mock( ModelLocation.class );
        final List<String> exits = new ArrayList<String>();
        exits.add( "north" );
        exits.add( "south" );
        mockery.checking( new Expectations() {{
            oneOf( location ).exits();
            will( returnValue( exits ) );
            ignoring( location );
        }});
        BasicModel model = new BasicModel();
        model.addLocation( location );

        assertEquals( exits, model.currentLocationExits() );
    }
}

