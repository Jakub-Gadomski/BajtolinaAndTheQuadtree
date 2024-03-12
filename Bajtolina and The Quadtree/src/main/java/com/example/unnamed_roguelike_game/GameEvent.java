package com.example.unnamed_roguelike_game;
import java.util.List;

public abstract class GameEvent {
    protected double eventTime;
    protected double priority;
    protected double eventDurationTime;
    private static final double constTurnLength=1;
    protected Being being;
    GameEvent(double eventTime, double priority,double eventDurationTime,Being being) {
        this.eventTime = eventTime;
        this.priority = priority;
        this.eventDurationTime=eventDurationTime;
        this.being=being;
    }
    public abstract List<GameEvent> makeTheEventHappen();
    public double getEventTime() {
        return eventTime;
    }
    public double getPriority() {
        return priority;
    }
    public double getEventDurationTime() {
        return eventDurationTime;
    }
    public double getConstTurnLength() {
        return constTurnLength;
    }
    public Being getBeing(){
        return being;
    }
}
