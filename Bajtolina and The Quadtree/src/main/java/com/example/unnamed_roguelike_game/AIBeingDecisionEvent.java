package com.example.unnamed_roguelike_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIBeingDecisionEvent extends GameEvent{
    private String AIDecision=null;
    private static final double AIDecisionEventDurationTime = 0;

    AIBeingDecisionEvent(double eventTime, double priority, Being being) {
        super(eventTime, priority, AIDecisionEventDurationTime, being);
    }


    public List<GameEvent> AIMakeDecision(String AIDecision) {

        List<GameEvent> eventsWhichAreCausedByThisEvent= new ArrayList<GameEvent>();
        GameEvent tempGameEvent=new BeingActionEvent(this.getEventTime()+this.getEventDurationTime(),being.getDefaultPriorityOfEventAction(),this.getConstTurnLength()/being.getSpeed(),being,AIDecision);
        eventsWhichAreCausedByThisEvent.add(tempGameEvent);

        return eventsWhichAreCausedByThisEvent;
    }

    public void setAIDecision() {
        Field temp=being.getField();
        int direction =6;
        int min=1000000009;
        for(int i=0;i<=6;++i){
            if((temp.getNeighbors()[i]!=null)&&((temp.getNeighbors()[i].getBeingOnThisField()==null)||(temp.getNeighbors()[i].getBeingOnThisField().getType().equals(BeingType.enumBeingType.bajtolina)))) {
                if ((temp.getNeighbors()[i].getDistanceFromBajtolina() < being.getViewRange()) && (temp.getNeighbors()[i].getDistanceFromBajtolina() < min)) {
                    direction = i;
                    min = temp.getNeighbors()[i].getDistanceFromBajtolina();
                }
                if((i==6)&&(!temp.getLeaf().equals(MainWithGraphic.bajtolina.getLeaf()))){
                    temp.setDistanceFromBajtolina(1000000009);
                    if(!temp.getNeighbors()[6].equals(temp)){
                        direction=6;
                    }
                }
            }
        }

        //te napisy nie są bez sensu i nie są losowe
       if(direction==0) {
            AIDecision="wwww";
        } else if (direction==1) {
            AIDecision="eeee";
        } else if (direction==2) {
            AIDecision="aaaa";
        } else if (direction==3) {
            AIDecision="dddd";
        } else if (direction==4) {
            AIDecision="zzzz";
        } else if (direction==5) {
            AIDecision="xxxx";
        } else if (direction==6) {
            AIDecision="ssss";
        }
    }

    @Override
    public List<GameEvent> makeTheEventHappen() {
        this.setAIDecision();
        return AIMakeDecision(this.AIDecision);
    }
}
