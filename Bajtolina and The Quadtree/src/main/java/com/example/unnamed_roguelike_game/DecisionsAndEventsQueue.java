package com.example.unnamed_roguelike_game;

import java.util.List;
import java.util.PriorityQueue;

public class DecisionsAndEventsQueue {
    private PriorityQueue<GameEvent> gameEventPriorityQueue=new PriorityQueue<GameEvent>(new EventComparator());//stworzenie kolejki priorytetowej zdarzeń i ustawienie komparatora na taki, który sam stworzyłem i jest poprawny.
    DecisionsAndEventsQueue(GameEvent firstGameEvent){
        gameEventPriorityQueue.add(firstGameEvent);
    }
    DecisionsAndEventsQueue(){}//nie wiem, czy to potrzebne
    public List<GameEvent> doNextEvent(){
        while(getBeingWhoActualDoingSmt().getIsDead()){
            gameEventPriorityQueue.poll();
        }
        return gameEventPriorityQueue.poll().makeTheEventHappen();
    }
    public void poll(){
        gameEventPriorityQueue.poll();
    }
    public void addEvents(List<GameEvent> gameEventsToAdd){
        for(GameEvent i:gameEventsToAdd){
            gameEventPriorityQueue.add(i);
        }
    }
    public void addSingleEvent(GameEvent gameEvent){
        gameEventPriorityQueue.add(gameEvent);
    }
    public boolean isEmpty(){
        return gameEventPriorityQueue.isEmpty();
    }
    public double getGameTime(){
        double time;
        GameEvent copy=gameEventPriorityQueue.poll();
        time=copy.eventTime;
        gameEventPriorityQueue.add(copy);
        return time;
    }
    public Being getBeingWhoActualDoingSmt(){
        return gameEventPriorityQueue.peek().getBeing();
    }
}
