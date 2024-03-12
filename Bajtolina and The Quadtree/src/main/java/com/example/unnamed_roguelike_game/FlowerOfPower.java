package com.example.unnamed_roguelike_game;

import java.util.Random;

public class FlowerOfPower {
    private Field field;
    private boolean visited;
    private double attackChange;
    private double speedChange;
    private double defenceChange;
    private double maxHealthPointChange;
    private double healthPointChange;

    public FlowerOfPower(Field field){
        Random random=new Random();
        visited=false;
        attackChange=random.nextInt(3);
        defenceChange=random.nextInt(3);
        speedChange=random.nextInt(3);
        maxHealthPointChange=random.nextInt(1);
        healthPointChange=-MyRand.randomDouble(4,0.,50.);//4 krotny rzut "kością" możliwe wyniki sa z przedziału 0-30

    }

    public void visit(Being being){
        being.setEatenFlowers(being.getEatenFlowers()+1);
        //funkcja wykonuje się w lefe w miejscu jak byt się rusza bo tak jest szybciej
        healthPointChange=-MyRand.randomDouble(4,being.getHealthPoints()/4,being.getHealthPoints()/4*3);//4 krotny rzut "kością" możliwe wyniki sa z przedziału 0-30

        if(!visited) {
            visited = true;
            being.changeBeingStatistics(attackChange, defenceChange, speedChange, maxHealthPointChange, healthPointChange);
        }
    }
    public int getNumberOfPictureOnImageList(){
        if(!visited) {
            return 19;
        }else {
            return 20;
        }
    }
}
