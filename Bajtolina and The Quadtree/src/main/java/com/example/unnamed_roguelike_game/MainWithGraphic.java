package com.example.unnamed_roguelike_game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainWithGraphic {
    public static Being bajtolina;
    public static DecisionsAndEventsQueue decisionsAndEventsQueue;
    public static double gameTime=0;
    public static int gameState =0;//-1=przegrana,1=wygrana,0=gratrwa
    public static List<Being> beingList=new ArrayList<Being>();
    public static void main(String[] args) {

        Field printField = null;
        double gameStartTime=0;
        decisionsAndEventsQueue = new DecisionsAndEventsQueue();
        Quadtree quadtree=new Quadtree(30);//na razie 3, żeby się szybciej generowało
        GameInterface gameInterface= new GameInterface();


        //List<Being> tempEnemyList= new ArrayList<>();
//        quadtree.getLeafList().get(0).getLeafsFields()[0][1].setFlowerOfPowerOnThisField(new FlowerOfPower(quadtree.getLeafList().get(0).getLeafsFields()[0][1]));
        //Being blackAnt=new Being(14, quadtree.getLeafList().get(0).getLeafsFields()[1][1],quadtree.getLeafList().get(0),10,10,5,1,4, 5,BeingType.enumBeingType.blackAnt,decisionsAndEventsQueue,gameStartTime);
        //blackAnt.setDefaultPriorityOfEventAction(-1000);


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameInterface.setVisible(true);
            }
        });
        while(!decisionsAndEventsQueue.isEmpty()){
            gameTime= decisionsAndEventsQueue.getGameTime();
            if(bajtolina.getField()!=null){ //obejście problemu na około poprzez dodanie niepotrzebnych ifów (jest to spowodowane brakiem czasu i zbliżającym się terminem na zakończenie gry)
                //System.out.println("GAME OVER");
                bajtolina.getField().BFS(bajtolina.getField());
                printField=bajtolina.getField();
               // break;
            }
            //główna pętla gry
                //System.out.println(decisionsAndEventsQueue.getGameTime());
                //bajtolina.getLeaf().printLeafOnAscii();
            if(bajtolina.getHealthPoints()<=0){
                gameState=-1;
                while(!decisionsAndEventsQueue.isEmpty()){
                    //ten while nie potrzebny ale tak na wszelki wypadek niech tutaj zostanie
                    decisionsAndEventsQueue.poll();
                }
                gameInterface.draw(bajtolina.getLeaf(),bajtolina,printField);
                break;
            }
            if(decisionsAndEventsQueue.getBeingWhoActualDoingSmt().getType().equals(BeingType.enumBeingType.bajtolina)){
                gameInterface.draw(bajtolina.getLeaf(),bajtolina,printField);
            }
            while(decisionsAndEventsQueue.getBeingWhoActualDoingSmt()==null){
                decisionsAndEventsQueue.poll();
            }
            if(bajtolina.getEatenFlowers()==20){//zmienić na 20
                gameState=1;
                gameInterface.draw(bajtolina.getLeaf(),bajtolina,printField);
                break;
            }
            if(!decisionsAndEventsQueue.isEmpty()){
                decisionsAndEventsQueue.addEvents(decisionsAndEventsQueue.doNextEvent());
            }
        }
    }
}