package com.example.unnamed_roguelike_game;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeingActionEvent extends GameEvent{
    private final double priorityOfStartAttackEventForPlayer = -1000000009;//priorytrt ataku, chodzi o to aby jak sie wybiera co atakowac to gra ma sie "zapauzować"
    private String actionDescribe;//opis akcji wyrażony poprzez krótki napis, który pozwali odróżnić działania bytu, może to być znak wczytany z klawiatury
    BeingActionEvent(double eventTime, double priority, double eventDurationTime, Being being, String actionDescribe) {
        super(eventTime,priority,eventDurationTime,being);
        this.actionDescribe=actionDescribe;
    }

    public Set<Pair<Integer,Integer>> makeTempSet(Field field,int range,int maxRange){
        Set<Pair<Integer,Integer>> tempSet = new HashSet<>();
        if(range>maxRange){
            tempSet.clear();
            return tempSet;
        }
        tempSet.add(new Pair<>(field.getY(),field.getX()));
        Set<Pair<Integer,Integer>> tempSet2 = new HashSet<>();
        for(Field i:field.getNeighbors()){
            if(i!=null){
                tempSet2=makeTempSet(i,range+1,maxRange);
                for(Pair<Integer,Integer> j:tempSet2){
                    tempSet.add(j);
                }
            }
        }
        return tempSet;
    }
    @Override
    public List<GameEvent> makeTheEventHappen() {
        List<GameEvent> EventsWhichAreCausesByThisEvent= new ArrayList<GameEvent>();
        GameEvent tempEvent=null;
        //odzielne przypadki tutaj muszą być dla gracza a oddzielne dla innych bytów
        if(actionDescribe.equals("w")){
            being.doNextAction("LU");
            tempEvent=new PlayerDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);
        } else if (actionDescribe.equals("e")) {
            being.doNextAction("RU");
            tempEvent=new PlayerDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);
        }else if (actionDescribe.equals("a")) {
            being.doNextAction("LL");
            tempEvent=new PlayerDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);
        }else if (actionDescribe.equals("d")) {
            being.doNextAction("RR");
            tempEvent=new PlayerDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);
        }else if (actionDescribe.equals("z")) {
            being.doNextAction("LD");
            tempEvent=new PlayerDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);
        }else if (actionDescribe.equals("x")) {
            being.doNextAction("RD");
            tempEvent=new PlayerDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);
        }else if (actionDescribe.equals("s")) {
            being.doNextAction("WaitOrChangeLeaf");
            tempEvent=new PlayerDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);
        } else if (actionDescribe.equals("q")) {//start atakowania dystansowego przez gracza
            //being.doNextAction("attackStart"); to nie w tym miejscu nie w tym Evenci
            //tutaj dodaję dostępne dla attackPointera pola
            if(being.getEquipment().getRangedWeapon()!=null) {
                if(being.getEquipment().getWeapon()!=null) {
                    being.getEquipment().getWeapon().undoChangeBeingParameters();
                }
                being.getEquipment().getRangedWeapon().changeBeingParameters();
                Set<Pair<Integer, Integer>> tempSet = new HashSet<>();
                tempSet = makeTempSet(being.getField(), 0, 2);
                AttackPointer.setAvailablePositions(tempSet);
                AttackPointer.setActive(true);
                AttackPointer.setXY(being.getField().getY(), being.getField().getX());
                tempEvent = new StartAttackEventForPlayer(eventTime, priorityOfStartAttackEventForPlayer, 0, being);//to trzeba zmienić
                EventsWhichAreCausesByThisEvent.add(tempEvent);
            }else {
                tempEvent=new PlayerDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
                EventsWhichAreCausesByThisEvent.add(tempEvent);
            }
        } else if (actionDescribe.equals("c")) {
            Set<Pair<Integer, Integer>> tempSet = new HashSet<>();
            tempSet = makeTempSet(being.getField(), 0, 1);
            AttackPointer.setAvailablePositions(tempSet);
            AttackPointer.setActive(true);
            AttackPointer.setXY(being.getField().getY(), being.getField().getX());
            tempEvent = new EatFieldEvent(eventTime, priorityOfStartAttackEventForPlayer, 0, being);//to trzeba zmienić
            EventsWhichAreCausesByThisEvent.add(tempEvent);

        } else if (actionDescribe.equals("r")) {//podnieś artefakt
            if(being.getField().getArtifactOnThisField()!=null){
                being.getField().getArtifactOnThisField().swapArtifacts(being);
            }
            tempEvent=new PlayerDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);
        }
        //tutaj są dla nie graczy
        else if(actionDescribe.equals("wwww")){
            being.doNextAction("LU");
            tempEvent=new AIBeingDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);

        } else if (actionDescribe.equals("eeee")) {
            being.doNextAction("RU");
            tempEvent=new AIBeingDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);

        }else if (actionDescribe.equals("aaaa")) {
            being.doNextAction("LL");
            tempEvent=new AIBeingDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);

        }else if (actionDescribe.equals("dddd")) {
            being.doNextAction("RR");
            tempEvent=new AIBeingDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);

        }else if (actionDescribe.equals("zzzz")) {
            being.doNextAction("LD");
            tempEvent=new AIBeingDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);


        }else if (actionDescribe.equals("xxxx")) {
            being.doNextAction("RD");
            tempEvent=new AIBeingDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);

        }
        else if (actionDescribe.equals("ssss")) {
            being.doNextAction("WaitOrChangeLeaf");
            tempEvent=new AIBeingDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);
        }
        //to jest jak gracz poda zły znak
        else{
            tempEvent=new PlayerDecisionEvent(eventTime+eventDurationTime, being.getDefaultPriorityOfEventAction(),being);
            EventsWhichAreCausesByThisEvent.add(tempEvent);
        }

        return EventsWhichAreCausesByThisEvent;
    }
}
