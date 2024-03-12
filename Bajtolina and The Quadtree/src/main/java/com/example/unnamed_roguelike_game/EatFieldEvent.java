package com.example.unnamed_roguelike_game;

import java.util.ArrayList;
import java.util.List;

public class EatFieldEvent extends GameEvent{

    private double dinnerDuration=1/being.getSpeed();
    public EatFieldEvent(double eventTime, double priority,double eventDurationTime,Being being){
        super(eventTime,priority,eventDurationTime,being);
        this.priority=1000000009;//najwyższy priorytet nie wiem tylko czy tu powinien być minus czy plus
        this.eventDurationTime=0;//nic on nie trwa
    }
    @Override
    public List<GameEvent> makeTheEventHappen() {
        while(KeyFromKeyboardQueue.Q.isEmpty()){
            System.out.print("");//bez tego program się pętli nie wiem czemu
            //nie pętl się
        }

        List<GameEvent> temp = new ArrayList<>();
        String operationFromPlayer;
        operationFromPlayer=KeyFromKeyboardQueue.Q.peek().toString();
        KeyFromKeyboardQueue.Q.remove();
        if(operationFromPlayer.equals("s")){
            Field eatenField=being.getLeaf().getLeafsFields()[AttackPointer.getX()][AttackPointer.getY()];

            if((eatenField.getBeingOnThisField()==null)&&(eatenField.getType().equals(FieldType.enumFieldType.floor)&&(eatenField.getArtifactOnThisField()==null)&&(eatenField.getFlowerOfPowerOnThisField()==null))){

                AttackPointer.setActive(false);
//                for(int i=0;i<6;++i) {
//                    if (eatenField.getNeighbors()[i] != null) {
//                        eatenField.getNeighbors()[i].getNeighbors()[5 - i] = null; //nieprzypadkowy i zaplanowany od samego początku wzór
//                    }
//                }
                eatenField.setType(FieldType.enumFieldType.nulll);

                temp.add(new PlayerDecisionEvent(this.eventTime+dinnerDuration,being.getDefaultPriorityOfEventAction(),this.being));

            }
            else{
                temp.add(new EatFieldEvent(this.eventTime, this.priority, this.eventDurationTime, this.being));
            }
        }else if(operationFromPlayer.equals("a")) {
            AttackPointer.setXY(AttackPointer.getY()-1,AttackPointer.getX());
            temp.add(new EatFieldEvent(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }else if(operationFromPlayer.equals("w")) {
            AttackPointer.setXY(AttackPointer.getY()-1*(AttackPointer.getX()%2),AttackPointer.getX()-1);
            temp.add(new EatFieldEvent(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }else if(operationFromPlayer.equals("e")) {
            AttackPointer.setXY(AttackPointer.getY()+1*((AttackPointer.getX()+1)%2),AttackPointer.getX()-1);
            temp.add(new EatFieldEvent(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }else if(operationFromPlayer.equals("d")) {
            AttackPointer.setXY(AttackPointer.getY()+1,AttackPointer.getX());
            temp.add(new EatFieldEvent(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }else if(operationFromPlayer.equals("z")) {
            AttackPointer.setXY(AttackPointer.getY()-1*(AttackPointer.getX()%2),AttackPointer.getX()+1);
            temp.add(new EatFieldEvent(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }else if(operationFromPlayer.equals("x")) {
            AttackPointer.setXY(AttackPointer.getY()+1*((AttackPointer.getX()+1)%2),AttackPointer.getX()+1);
            temp.add(new EatFieldEvent(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }else if(operationFromPlayer.equals("c")) {//anuluj wbór
            AttackPointer.setActive(false);
            temp.add(new PlayerDecisionEvent(this.eventTime,being.getDefaultPriorityOfEventAction(),this.being));
        }
        else{
            temp.add(new EatFieldEvent(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }
        return temp;
    }
}
