package com.chewielouie.textadventure;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocationExitTests {

    @Test
    public void exit_is_visible_by_default() {
        assertTrue( new LocationExit( "", "" ).visible() );
    }

    @Test
    public void exit_can_be_made_invisible() {
        LocationExit exit = new LocationExit( "", "" );
        exit.setInvisible();
        assertFalse( exit.visible() );
    }

    @Test
    public void exit_can_be_made_visible_again() {
        LocationExit exit = new LocationExit( "", "" );
        exit.setInvisible();
        exit.setVisible();
        assertTrue( exit.visible() );
    }

    @Test
    public void exit_id_is_blank_by_default() {
        LocationExit exit = new LocationExit( "", "" );
        assertEquals( "", exit.id() );
    }

    @Test
    public void exit_id_can_be_set() {
        LocationExit exit = new LocationExit( "", "" );
        exit.setID( "exit id" );
        assertEquals( "exit id", exit.id() );
    }

    @Test
    public void exit_has_a_default_direction_hint_of_dont_care() {
        LocationExit exit = new LocationExit( "label", "destination" );

        assertEquals( Exit.DirectionHint.DontCare, exit.directionHint() );
    }

    @Test
    public void can_set_a_direction_hint() {
        LocationExit exit = new LocationExit( "label", "destination", Exit.DirectionHint.North );

        assertEquals( Exit.DirectionHint.North, exit.directionHint() );
    }

    @Test
    public void two_exits_with_the_same_value_should_be_equal() {
        LocationExit exit1 = new LocationExit( "label", "destination", Exit.DirectionHint.North );
        LocationExit exit2 = new LocationExit( "label", "destination", Exit.DirectionHint.North );

        assertEquals( exit1, exit2 );
    }

    @Test
    public void two_exits_with_different_labels_should_not_be_equal() {
        LocationExit exit1 = new LocationExit( "label a", "destination", Exit.DirectionHint.North );
        LocationExit exit2 = new LocationExit( "label b", "destination", Exit.DirectionHint.North );

        assertNotEquals( exit1, exit2 );
    }


    @Test
    public void two_exits_with_different_destinations_should_not_be_equal() {
        LocationExit exit1 = new LocationExit( "label", "destination a", Exit.DirectionHint.North );
        LocationExit exit2 = new LocationExit( "label", "destination b", Exit.DirectionHint.North );

        assertNotEquals( exit1, exit2 );
    }


    @Test
    public void two_exits_with_different_direction_hints_should_not_be_equal() {
        LocationExit exit1 = new LocationExit( "label", "destination", Exit.DirectionHint.North );
        LocationExit exit2 = new LocationExit( "label", "destination", Exit.DirectionHint.South );

        assertNotEquals( exit1, exit2 );
    }

    @Test
    public void a_exit_is_not_equal_to_a_non_exit() {
        LocationExit exit = new LocationExit( "label", "destination", Exit.DirectionHint.North );
        Object notAnExit = new Object();

        assertNotEquals( exit, notAnExit );
    }

    @Test
    public void two_exits_with_the_same_value_should_have_the_same_hashcode() {
        LocationExit exit1 = new LocationExit( "label", "destination", Exit.DirectionHint.North );
        LocationExit exit2 = new LocationExit( "label", "destination", Exit.DirectionHint.North );

        assertEquals( exit1.hashCode(), exit2.hashCode() );
    }

    @Test
    public void two_exits_with_different_labels_should_have_different_hashcodes() {
        LocationExit exit1 = new LocationExit( "label a", "destination", Exit.DirectionHint.North );
        LocationExit exit2 = new LocationExit( "label b", "destination", Exit.DirectionHint.North );

        assertNotEquals( exit1.hashCode(), exit2.hashCode() );
    }

    @Test
    public void two_exits_with_different_destinations_should_have_different_hashcodes() {
        LocationExit exit1 = new LocationExit( "label", "destination a", Exit.DirectionHint.North );
        LocationExit exit2 = new LocationExit( "label", "destination b", Exit.DirectionHint.North );

        assertNotEquals( exit1.hashCode(), exit2.hashCode() );
    }

    @Test
    public void two_exits_with_different_direction_hints_should_have_different_hashcodes() {
        LocationExit exit1 = new LocationExit( "label", "destination", Exit.DirectionHint.North );
        LocationExit exit2 = new LocationExit( "label", "destination", Exit.DirectionHint.South );

        assertNotEquals( exit1.hashCode(), exit2.hashCode() );
    }

    @Test
    public void deserialise_extracts_exit_label() {
        LocationExit exit = new LocationExit();
        exit.deserialise( "exit label:label\n" +
                          "exit destination:destination" );
        assertEquals( "label", exit.label() );
    }

    @Test
    public void deserialise_extracts_exit_destination() {
        LocationExit exit = new LocationExit();
        exit.deserialise( "exit label:label\n" +
                          "exit destination:destination" );
        assertEquals( "destination", exit.destination() );
    }

    @Test
    public void deserialise_extracts_exit_direction_hint() {
        LocationExit exit = new LocationExit();
        exit.deserialise( "exit label:label\n" +
                          "exit destination:destination\n" +
                          "exit direction hint:East" );
        assertEquals( Exit.DirectionHint.East, exit.directionHint() );
    }

    @Test
    public void deserialise_exit_direction_hint_is_optional() {
        LocationExit exit = new LocationExit();
        exit.deserialise( "exit label:label\n" +
                          "exit destination:destination" );
        assertEquals( Exit.DirectionHint.DontCare, exit.directionHint() );
    }

    @Test
    public void deserialise_exit_is_visible_by_default() {
        LocationExit exit = new LocationExit();
        exit.deserialise( "exit label:label\n" +
                          "exit destination:destination" );
        assertTrue( exit.visible() );
    }

    @Test
    public void deserialise_extracts_exit_is_not_visible() {
        LocationExit exit = new LocationExit();
        exit.deserialise( "exit label:label\n" +
                          "exit destination:destination\n" +
                          "exit is not visible:" );
        assertFalse( exit.visible() );
    }

    @Test
    public void deserialise_extracts_exit_id() {
        LocationExit exit = new LocationExit();
        exit.deserialise( "exit label:label\n" +
                          "exit destination:destination\n" +
                          "exit id:exit id" );
        assertEquals( "exit id", exit.id() );
    }
}
