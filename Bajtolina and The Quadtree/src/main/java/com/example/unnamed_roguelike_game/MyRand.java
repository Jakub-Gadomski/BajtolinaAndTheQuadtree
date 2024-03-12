package com.example.unnamed_roguelike_game;

import java.util.Random;

public class MyRand {
    private static Random random=new Random();
    public static double randomDouble(int numberOfDice,double minValue,double maxValue){
        double tempMin,tempMax,temp;
        double result = 0;
        tempMin=minValue/numberOfDice;
        tempMax=maxValue/numberOfDice;
        for(int i=0;i<numberOfDice;++i){
            temp=random.nextDouble()*tempMax;
            temp=Math.max(temp,-temp);
            while(temp>tempMax){
                temp-=tempMax-tempMin;
            }
            result+=temp;
        }
        return result;
    }
}
