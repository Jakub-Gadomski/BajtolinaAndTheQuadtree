package com.example.unnamed_roguelike_game;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Field implements GraphicItem{
    private int fieldID;
    private boolean isPartOfLeaf;//czy pole jest częścią liścia, czy jest poza liściem
    private int x,y;//współrzędne na liściu
    private Field[] neighbors = new Field[7];//Sąsiednie pola. Jeśli mniej niż 7 sąsiednich pól to pola, w których nie ma nic, powinny mieć wartość -1
    //[0]-lewo góra [1]-prawo góra [2]-lewo [3]-prawo [4]-lewo dół [5]-prawo dół [6]-albo to to samo pole albo to pole w innym liściu
    private FieldType.enumFieldType type;
    private char asciiRepresentation;
    private Being beingOnThisField;
    private Leaf leaf;
    private Artifact artifactOnThisField;
    private int distanceFromBajtolina;
    private boolean isVisited;
    private FlowerOfPower flowerOfPowerOnThisField;
    public Field(int fieldID,boolean isPartOfLeaf,int x,int y,FieldType.enumFieldType type,Leaf leaf){
        this.fieldID=fieldID;
        this.isPartOfLeaf=isPartOfLeaf;
        this.x=x;
        this.y=y;
        this.type=type;
        this.leaf=leaf;
        this.setAsciiRepresentation();
        flowerOfPowerOnThisField=null;
        artifactOnThisField=null;
    }
    public void setType(FieldType.enumFieldType type) {
        this.type = type;
        //this.setAsciiRepresentation();
    }
    public void BFS(Field start){
        Queue<Field> queue=new LinkedList<>();
        Field temp;
        for(Field[] i:leaf.getLeafsFields()){
            for(Field j:i){
                if(j!=null){
                    if(!j.getType().equals(FieldType.enumFieldType.wall)){
                        j.setVisited(false);
                    }else {
                        j.setVisited(true);
                        j.setDistanceFromBajtolina(1000000009);
                    }
                }
            }
        }//ustawiam, które pola są odwiedzone
        queue.add(start);
        start.setDistanceFromBajtolina(0);
        start.setVisited(true);
        Leaf leaf=start.getLeaf();
        while(!queue.isEmpty()){
            temp= queue.poll();
            for(int i=0;i<6;++i){
                if(temp.getNeighbors()[i]!=null){
                    if(!temp.getNeighbors()[i].isVisited()){
                        temp.getNeighbors()[i].setVisited(true);
                        temp.getNeighbors()[i].setDistanceFromBajtolina(temp.getDistanceFromBajtolina()+1);
                        queue.add(temp.getNeighbors()[i]);
                    }
                }
            }
        }

    }
    public char getAsciiRepresentation() {
        return asciiRepresentation;
    }
    public void setBeingOnThisField(Being beingOnThisField) {
        this.beingOnThisField = beingOnThisField;
    }
    public int getFieldID(){
        return fieldID;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Being getBeingOnThisField() {
        return beingOnThisField;
    }
    public FieldType.enumFieldType getType() {
        return type;
    }
    public boolean getIsPartOfLeaf() {
        return isPartOfLeaf;
    }
    public Field[] getNeighbors(){
        return neighbors;
    }

    public FlowerOfPower getFlowerOfPowerOnThisField() {
        return flowerOfPowerOnThisField;
    }

    public void setFlowerOfPowerOnThisField(FlowerOfPower flowerOfPowerOnThisField) {
        this.flowerOfPowerOnThisField = flowerOfPowerOnThisField;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public void setDistanceFromBajtolina(int distanceFromBajtolina) {
        this.distanceFromBajtolina = distanceFromBajtolina;
    }
    public int getDistanceFromBajtolina() {
        return distanceFromBajtolina;
    }
    public boolean isVisited() {
        return isVisited;
    }
    public void setNeighbors(Field[] neighbors) {
        for(int i=0;i<6;++i){
            this.neighbors[i]=neighbors[i];
        }
    }
    public void setArtifactOnThisField(Artifact artifactOnThisField) {
        this.artifactOnThisField = artifactOnThisField;
    }
    public Artifact getArtifactOnThisField() {
        return artifactOnThisField;
    }
    public Leaf getLeaf() {
        return leaf;
    }
    private void setAsciiRepresentation(){
        if(type.equals(FieldType.enumFieldType.floor)){
            asciiRepresentation='.';
        } else if (type.equals(FieldType.enumFieldType.wall)) {
            asciiRepresentation='#';
        } else if (type.equals(FieldType.enumFieldType.passageToAnotherLeafUp)) {
            asciiRepresentation='>';
        } else if (type.equals(FieldType.enumFieldType.passageToAnotherLeafDown)) {
            asciiRepresentation='<';
        }
    }
    @Override
    public int getPictureId() {
        if(type.equals(FieldType.enumFieldType.floor)){
            return 2;
        } else if (type.equals(FieldType.enumFieldType.wall)) {
            return 3;
        } else if (type.equals(FieldType.enumFieldType.passageToAnotherLeafUp)) {
            return 4;
        } else if (type.equals(FieldType.enumFieldType.passageToAnotherLeafDown)) {
            return 5;
        }
        return 0;
    }
}
