package com.example.unnamed_roguelike_game;

import java.io.IOException;
import java.util.Random;

public class AsciiMain {
    public static void main(String[] args) throws IOException {

//        double gameStartTime=0;
//        Quadtree quadtree=new Quadtree(3);
////        Being bajtolina=new Being(1, quadtree.getLeafList().get(0).getLeafsFields()[0][0],quadtree.getLeafList().get(0),1,1,1,1,1, BeingType.enumBeingType.bajtolina);
////        Being tempBeing1=new Being(2,quadtree.getLeafList().get(0).getLeafsFields()[2][2],quadtree.getLeafList().get(0),1,1,1,1,1, BeingType.enumBeingType.type1);
////        Being tempBeing2=new Being(3,quadtree.getLeafList().get(0).getLeafsFields()[2][2],quadtree.getLeafList().get(0),1,1,1,1,1, BeingType.enumBeingType.type1);
////        Being tempBeing3=new Being(4,quadtree.getLeafList().get(0).getLeafsFields()[2][2],quadtree.getLeafList().get(0),1,1,1,1,1, BeingType.enumBeingType.type1);
////        Being tempBeing4=new Being(5,quadtree.getLeafList().get(0).getLeafsFields()[2][2],quadtree.getLeafList().get(0),1,1,1,1,1, BeingType.enumBeingType.type1);
////        Being tempBeing5=new Being(6,quadtree.getLeafList().get(0).getLeafsFields()[2][2],quadtree.getLeafList().get(0),1,1,1,1,1, BeingType.enumBeingType.type1);
////        Being tempBeing6=new Being(7,quadtree.getLeafList().get(0).getLeafsFields()[2][2],quadtree.getLeafList().get(0),1,1,1,1,1, BeingType.enumBeingType.type1);
////        Being tempBeing7=new Being(8,quadtree.getLeafList().get(0).getLeafsFields()[2][2],quadtree.getLeafList().get(0),1,1,1,1,1, BeingType.enumBeingType.type1);
////        Being tempBeing8=new Being(9,quadtree.getLeafList().get(0).getLeafsFields()[2][2],quadtree.getLeafList().get(0),1,1,1,1,1, BeingType.enumBeingType.type1);
//
//        PlayerDecisionEvent firstEvent = new PlayerDecisionEvent(gameStartTime, bajtolina.getDefaultPriorityOfEventAction(),bajtolina);
//        DecisionsAndEventsQueue decisionsAndEventsQueue = new DecisionsAndEventsQueue(firstEvent);
//        decisionsAndEventsQueue.addSingleEvent(new AIBeingDecisionEvent(gameStartTime, tempBeing1.getDefaultPriorityOfEventAction(),tempBeing1));
//        decisionsAndEventsQueue.addSingleEvent(new AIBeingDecisionEvent(gameStartTime, tempBeing2.getDefaultPriorityOfEventAction(),tempBeing2));
//        decisionsAndEventsQueue.addSingleEvent(new AIBeingDecisionEvent(gameStartTime, tempBeing3.getDefaultPriorityOfEventAction(),tempBeing3));
//        decisionsAndEventsQueue.addSingleEvent(new AIBeingDecisionEvent(gameStartTime, tempBeing4.getDefaultPriorityOfEventAction(),tempBeing4));
//        decisionsAndEventsQueue.addSingleEvent(new AIBeingDecisionEvent(gameStartTime, tempBeing5.getDefaultPriorityOfEventAction(),tempBeing5));
//        decisionsAndEventsQueue.addSingleEvent(new AIBeingDecisionEvent(gameStartTime, tempBeing6.getDefaultPriorityOfEventAction(),tempBeing6));
//        decisionsAndEventsQueue.addSingleEvent(new AIBeingDecisionEvent(gameStartTime, tempBeing7.getDefaultPriorityOfEventAction(),tempBeing7));
//        decisionsAndEventsQueue.addSingleEvent(new AIBeingDecisionEvent(gameStartTime, tempBeing8.getDefaultPriorityOfEventAction(),tempBeing8));
//        Random random = new Random();
//
//        int temptest=0;
//        for(Field i[] : quadtree.getLeafList().get(0).getLeafsFields()){
//            for (Field j :i) {
//                if ((j.getType().equals(FieldType.enumFieldType.passageToAnotherLeafUp)) || (j.getType().equals(FieldType.enumFieldType.passageToAnotherLeafDown))){
//                    temptest++;
//                    continue;
//                }
//                    if(((int)Math.round(random.nextInt()%2)==0)&&(temptest>20)){
//                    j.setType(FieldType.enumFieldType.floor);
//                }
//                else{
//                    j.setType(FieldType.enumFieldType.floor);
//
//                }
//                temptest++;
//            }
//        }
//        bajtolina.getLeaf().printLeafOnAscii();
//        //leaf.getLeafsFields()[1][1].addArtifact(new Weapon(new ArtifactType(),leaf.getLeafsFields()[1][1],));
//        while(!decisionsAndEventsQueue.isEmpty()){//główna pętla gry
//            System.out.print("\033[H\033[2J");//czyszczenie konsoli
//            if(decisionsAndEventsQueue.getBeingWhoActualDoingSmt().equals(bajtolina)){
//                System.out.println(decisionsAndEventsQueue.getGameTime());
//                bajtolina.getLeaf().printLeafOnAscii();
//            }
//            bajtolina.setSpeed(2);
//            decisionsAndEventsQueue.addEvents(decisionsAndEventsQueue.doNextEvent());
//        }
    }
}
