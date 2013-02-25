package com.chewielouie.textadventure.serialisation;

import static org.junit.Assert.*;

import org.jmock.*;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.chewielouie.textadventure.Exit;
import com.chewielouie.textadventure.ExitFactory;
import com.chewielouie.textadventure.Item;
import com.chewielouie.textadventure.ItemFactory;
import com.chewielouie.textadventure.ModelLocation;

@RunWith(JMock.class)
public class ModelLocationDeserialiserTests {

    private Mockery mockery = new Mockery();

    @Test
    public void deserialise_extracts_id_after_stripping_trailing_newlines() {
        final ModelLocation location = mockery.mock( ModelLocation.class );
        ModelLocationDeserialiser d = new ModelLocationDeserialiser( location, null, null );
        mockery.checking( new Expectations() {{
            oneOf( location ).setId( "name" );
            ignoring( location );
        }});
        d.deserialise( "location id:name\n" );
    }

    @Test
    public void deserialise_finds_location_description() {
        final ModelLocation location = mockery.mock( ModelLocation.class );
        ModelLocationDeserialiser d = new ModelLocationDeserialiser( location, null, null );
        mockery.checking( new Expectations() {{
            oneOf( location ).setLocationDescription(
                "You are in a room.\n" +
                "It is a bit untidy." );
            ignoring( location );
        }});
        d.deserialise( "location id:name\n" +
                 "location description:You are in a room.\n" +
                                      "It is a bit untidy." );
    }

    @Test
    public void deserialise_extracts_location_description_up_to_exit_label() {
        final ModelLocation location = mockery.mock( ModelLocation.class );
        ModelLocationDeserialiser d = new ModelLocationDeserialiser( location, null, null );
        mockery.checking( new Expectations() {{
            oneOf( location ).setLocationDescription(
                "You are in a room.\n" +
                "It is a bit untidy.\n" );
            ignoring( location );
        }});
        d.deserialise( "location id:name\n" +
                 "location description:You are in a room.\n" +
                                      "It is a bit untidy.\n" +
                 "exit label:label\n" );
    }

    @Test
    public void deserialise_extracts_location_description_up_to_item() {
        final ModelLocation location = mockery.mock( ModelLocation.class );
        ModelLocationDeserialiser d = new ModelLocationDeserialiser( location, null, null );
        mockery.checking( new Expectations() {{
            oneOf( location ).setLocationDescription(
                "You are in a room.\n" +
                "It is a bit untidy.\n" );
            ignoring( location );
        }});
        d.deserialise( "location id:name\n" +
                 "location description:You are in a room.\n" +
                                      "It is a bit untidy.\n" +
                 "ITEM\n" );
    }

    @Test
    public void deserialise_extracts_exit() {
        final ModelLocation location = mockery.mock( ModelLocation.class );
        final Exit exit = mockery.mock( Exit.class );
        final ExitFactory exitFactory = mockery.mock( ExitFactory.class );
        ModelLocationDeserialiser d =
            new ModelLocationDeserialiser( location, null, exitFactory );

        mockery.checking( new Expectations() {{
            allowing( exitFactory ).create();
            will( returnValue( exit ) );
            ignoring( exitFactory );
            oneOf( exit ).deserialise( "exit 1\n" +
                                       "some more content" );
            ignoring( exit );
            ignoring( location );
        }});

        d.deserialise( "location id:name\n" +
                       "EXIT\nexit 1\n" +
                       "some more content" );
    }

    //@Test
    //public void deserialise_extracts_multiple_exits() {
        //final ModelLocation location = mockery.mock( ModelLocation.class );
        //final Exit exit1 = mockery.mock( Exit.class, "exit1" );
        //final Exit exit2 = mockery.mock( Exit.class, "exit2" );
        //final ExitFactory exitFactory = mockery.mock( ExitFactory.class );
        //ModelLocationDeserialiser d =
            //new ModelLocationDeserialiser( location, null, exitFactory );

        //mockery.checking( new Expectations() {{
            //atLeast( 1 ).of( exitFactory ).create();
                //will( onConsecutiveCalls(
                      //returnValue( exit1 ),
                      //returnValue( exit2 ) ) );
            //ignoring( exitFactory );
            //oneOf( exit1 ).deserialise( "exit 1 content\n" +
                                       //"some more content" );
            //ignoring( exit1 );
            //oneOf( exit2 ).deserialise( "exit 2 content\n" +
                                       //"some more content" );
            //ignoring( exit2 );
            //ignoring( location );
        //}});

        //d.deserialise( "location id:name\n" +
                       //"EXIT\nexit 1 content\n" +
                       //"some more content" +
                       //"EXIT\nexit 2 content\n" +
                       //"some more content" );
    //}

    //@Test
    //public void deserialise_adds_extracted_exits_to_location() {
        //final ModelLocation location = mockery.mock( ModelLocation.class );
        //final Exit exit1 = mockery.mock( Exit.class, "exit1" );
        //final Exit exit2 = mockery.mock( Exit.class, "exit2" );
        //final ExitFactory exitFactory = mockery.mock( ExitFactory.class );
        //ModelLocationDeserialiser d =
            //new ModelLocationDeserialiser( location, null, exitFactory );

        //mockery.checking( new Expectations() {{
            //atLeast( 1 ).of( exitFactory ).create();
                //will( onConsecutiveCalls(
                      //returnValue( exit1 ),
                      //returnValue( exit2 ) ) );
            //ignoring( exitFactory );
            //ignoring( exit1 );
            //ignoring( exit2 );
            //oneOf( location ).addExit( exit1 );
            //oneOf( location ).addExit( exit2 );
            //ignoring( location );
        //}});

        //d.deserialise( "location id:name\n" +
                       //"EXIT\nexit 1 content\n" +
                       //"and more exit content\n" +
                       //"EXIT\nexit 2 content\n" +
                       //"and more exit content\n" );
    //}

    @Test
    public void deserialise_extracts_item() {
        final ModelLocation location = mockery.mock( ModelLocation.class );
        final Item item = mockery.mock( Item.class );
        final ItemFactory itemFactory = mockery.mock( ItemFactory.class );
        ModelLocationDeserialiser d =
            new ModelLocationDeserialiser( location, itemFactory, null );

        mockery.checking( new Expectations() {{
            allowing( itemFactory ).create();
            will( returnValue( item ) );
            ignoring( itemFactory );
            oneOf( item ).deserialise( "item name:item content\n" +
                                       "and more item content" );
            ignoring( item );
            ignoring( location );
        }});

        d.deserialise( "location id:name\n" +
                       "ITEM\nitem name:item content\n" +
                       "and more item content" );
    }

    @Test
    public void deserialise_extracts_multiple_items() {
        final ModelLocation location = mockery.mock( ModelLocation.class );
        final Item item1 = mockery.mock( Item.class, "item1" );
        final Item item2 = mockery.mock( Item.class, "item2" );
        final ItemFactory itemFactory = mockery.mock( ItemFactory.class );
        ModelLocationDeserialiser d =
            new ModelLocationDeserialiser( location, itemFactory, null );

        mockery.checking( new Expectations() {{
            atLeast( 1 ).of( itemFactory ).create();
                will( onConsecutiveCalls(
                      returnValue( item1 ),
                      returnValue( item2 ) ) );
            ignoring( itemFactory );
            oneOf( item1 ).deserialise( "item 1 content\n" +
                                        "and more item content\n" );
            ignoring( item1 );
            oneOf( item2 ).deserialise( "item 2 content\n" +
                                        "and more item content\n" );
            ignoring( item2 );
            ignoring( location );
        }});

        d.deserialise( "location id:name\n" +
                       "ITEM\nitem 1 content\n" +
                       "and more item content\n" +
                       "ITEM\nitem 2 content\n" +
                       "and more item content\n" );
    }

    @Test
    public void deserialise_adds_extracted_items_to_location() {
        final ModelLocation location = mockery.mock( ModelLocation.class );
        final Item item1 = mockery.mock( Item.class, "item1" );
        final Item item2 = mockery.mock( Item.class, "item2" );
        final ItemFactory itemFactory = mockery.mock( ItemFactory.class );
        ModelLocationDeserialiser d =
            new ModelLocationDeserialiser( location, itemFactory, null );

        mockery.checking( new Expectations() {{
            atLeast( 1 ).of( itemFactory ).create();
                will( onConsecutiveCalls(
                      returnValue( item1 ),
                      returnValue( item2 ) ) );
            ignoring( itemFactory );
            ignoring( item1 );
            ignoring( item2 );
            oneOf( location ).addItem( item1 );
            oneOf( location ).addItem( item2 );
            ignoring( location );
        }});

        d.deserialise( "location id:name\n" +
                       "ITEM\nitem 1 content\n" +
                       "and more item content\n" +
                       "ITEM\nitem 2 content\n" +
                       "and more item content\n" );
    }
}


