package com.example.unnamed_roguelike_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PlayerDecisionEvent extends GameEvent {
    private String playerOrder=null;
    private Character inputFromKeyboard;
    private static final double playerDecisionEventDurationTime = 0;
    PlayerDecisionEvent(double eventTime, double priority,Being being) {
        super(eventTime, priority,playerDecisionEventDurationTime,being);
    }



    public void setPlayerOrder(String playerOrder) {
        this.playerOrder = playerOrder;
    }

    @Override
    public List<GameEvent> makeTheEventHappen() {//pobieranie rozkazów gracza będzie w tej funkcji, funkcja ta jest odpowiedzialna za częściową obsługę interfejsu gry
        while(KeyFromKeyboardQueue.Q.size()==0){
            //System.out.print("");//nie wiem, dlaczego, ale bez tego nie działa mi program i się pętli w nieskończoność
            playerOrder=playerOrder;//to lepsze niż to wyżej bo nic nie wypisuje
        }
        inputFromKeyboard=KeyFromKeyboardQueue.Q.element();
        KeyFromKeyboardQueue.Q.remove();
        playerOrder=inputFromKeyboard.toString();
        return this.getPlayerOrder(playerOrder);
    }
    public List<GameEvent> getPlayerOrder(String playerOrder) {

        List<GameEvent> eventsWhichAreCausedByThisEvent= new ArrayList<GameEvent>();
        GameEvent tempGameEvent=new BeingActionEvent(this.getEventTime()+this.getEventDurationTime(),being.getDefaultPriorityOfEventAction(),this.getConstTurnLength()/being.getSpeed(),being,playerOrder);
        eventsWhichAreCausedByThisEvent.add(tempGameEvent);

        return eventsWhichAreCausedByThisEvent;
    }
}
