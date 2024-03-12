package com.example.unnamed_roguelike_game;

import java.util.Comparator;

public class EventComparator implements Comparator<GameEvent> {

    @Override
    public int compare(GameEvent o1, GameEvent o2) {
        if(o1.getEventTime()<o2.getEventTime()) {
            return -1;
        } else if ((o1.getPriority()<o2.getPriority())&&(o1.getEventTime()==o2.getEventTime())) {
            return -1;
        } else return 1;
    }
}
