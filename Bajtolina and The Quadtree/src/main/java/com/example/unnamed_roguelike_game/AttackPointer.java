package com.example.unnamed_roguelike_game;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class AttackPointer {
    private static int x;
    private static int y;
    private static Set< Pair<Integer,Integer>> availablePositions;
    private static boolean active;
    AttackPointer(){
        availablePositions=new HashSet<Pair<Integer,Integer>>();
        active=false;
    }

    public static void setActive(boolean active) {
        AttackPointer.active = active;
    }

    public static void setXY(Integer y,Integer x){
        Pair<Integer,Integer> temp = new Pair<Integer,Integer>(y,x);

        if(availablePositions.contains(temp)){
            AttackPointer.x=x;
            AttackPointer.y=y;
        }
    }
    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }
    public static boolean getActive(){
        return active;
    }

    public static void setAvailablePositions(Set<Pair<Integer, Integer>> availablePositions) {
        AttackPointer.availablePositions = availablePositions;
    }
}
