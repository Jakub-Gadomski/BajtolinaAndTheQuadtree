package com.example.unnamed_roguelike_game;

import java.util.ArrayList;
import java.util.List;

public class HealthBeingEvent extends GameEvent{
    public HealthBeingEvent(double eventTime, double priority,double eventDurationTime,Being being){
        super(eventTime,priority,eventDurationTime, being);
    }
    @Override
    public List<GameEvent> makeTheEventHappen() {
        being.regenerateHealthPoints();//reeneracja o 10 pkt procentowych
        List<GameEvent> nextEvents=new ArrayList<>();
        nextEvents.add(new HealthBeingEvent(eventTime+eventDurationTime,priority,eventDurationTime,being));
        return nextEvents;
    }
}
