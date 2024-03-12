package com.example.unnamed_roguelike_game;

import java.util.ArrayList;
import java.util.List;

///Uwaga na przekazywanie następnych wydarzeń
public class StartAttackEventForPlayer extends GameEvent{
    private double attackDuration=1/being.getSpeed();
    StartAttackEventForPlayer(double eventTime, double priority, double eventDurationTime, Being being) { //ten being w tym Evencie to Bajtolina
        super(eventTime, priority, eventDurationTime, being);
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
            Being target=being.getLeaf().getLeafsFields()[AttackPointer.getX()][AttackPointer.getY()].getBeingOnThisField();
            if((target!=null)&&(target!=being)){
                being.attackAnotherBeing(target);
                AttackPointer.setActive(false);
                temp.add(new PlayerDecisionEvent(this.eventTime+attackDuration,being.getDefaultPriorityOfEventAction(),this.being));
                being.getEquipment().getRangedWeapon().undoChangeBeingParameters();
                if(being.getEquipment().getWeapon()!=null){
                    being.getEquipment().getWeapon().changeBeingParameters();

                }
                //koniec wyboru i atak na to co jest w tym miejscu
            }
            else{
                temp.add(new StartAttackEventForPlayer(this.eventTime, this.priority, this.eventDurationTime, this.being));
            }
        }else if(operationFromPlayer.equals("a")) {
            AttackPointer.setXY(AttackPointer.getY()-1,AttackPointer.getX());
            temp.add(new StartAttackEventForPlayer(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }else if(operationFromPlayer.equals("w")) {
            AttackPointer.setXY(AttackPointer.getY()-1*(AttackPointer.getX()%2),AttackPointer.getX()-1);
            temp.add(new StartAttackEventForPlayer(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }else if(operationFromPlayer.equals("e")) {
            AttackPointer.setXY(AttackPointer.getY()+1*((AttackPointer.getX()+1)%2),AttackPointer.getX()-1);
            temp.add(new StartAttackEventForPlayer(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }else if(operationFromPlayer.equals("d")) {
            AttackPointer.setXY(AttackPointer.getY()+1,AttackPointer.getX());
            temp.add(new StartAttackEventForPlayer(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }else if(operationFromPlayer.equals("z")) {
            AttackPointer.setXY(AttackPointer.getY()-1*(AttackPointer.getX()%2),AttackPointer.getX()+1);
            temp.add(new StartAttackEventForPlayer(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }else if(operationFromPlayer.equals("x")) {
            AttackPointer.setXY(AttackPointer.getY()+1*((AttackPointer.getX()+1)%2),AttackPointer.getX()+1);
            temp.add(new StartAttackEventForPlayer(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }else if(operationFromPlayer.equals("q")) {//anuluj wbór
            AttackPointer.setActive(false);
            temp.add(new PlayerDecisionEvent(this.eventTime,being.getDefaultPriorityOfEventAction(),this.being));
            if(being.getEquipment().getWeapon()!=null){
                being.getEquipment().getWeapon().changeBeingParameters();

            }
            being.getEquipment().getRangedWeapon().undoChangeBeingParameters();
        }
        else{
            temp.add(new StartAttackEventForPlayer(this.eventTime, this.priority, this.eventDurationTime, this.being));
        }
        return temp;
    }
}
