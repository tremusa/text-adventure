package com.chewielouie.textadventure.action;

import com.chewielouie.textadventure.item.Item;
import com.chewielouie.textadventure.ModelLocation;
import com.chewielouie.textadventure.TextAdventureModel;
import com.chewielouie.textadventure.UserInventory;

public interface ActionFactory {
    public Action createShowInventoryAction( UserInventory inventory,
                                             TextAdventureModel model );
    public Action createInventoryItemAction( Item item,
                                             UserInventory inventory,
                                             ModelLocation location );
    public Action createExamineAction( Item item );
    public Action createUseWithAction( Item item,
                                       UserInventory inventory,
                                       ModelLocation location );
}
