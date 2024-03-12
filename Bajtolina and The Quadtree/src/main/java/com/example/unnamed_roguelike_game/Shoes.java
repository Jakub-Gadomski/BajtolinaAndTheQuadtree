package com.example.unnamed_roguelike_game;

public class Shoes extends Artifact{

    public Shoes(Field field, Integer numberOfPictureOnPicturesImageList, double valueOfAttackChange, double valueOfDefenceChange, double valueOfSpeedChange, double valueOfMaxHealthPointsChange) {
        super(field, numberOfPictureOnPicturesImageList, valueOfAttackChange, valueOfDefenceChange, valueOfSpeedChange, valueOfMaxHealthPointsChange);
    }

    public void swapArtifacts(Being being) {
        this.being=being;

        if(being.getEquipment().getShoes()!=null){
            this.being.getEquipment().getShoes().undoChangeBeingParameters();
            this.being.getEquipment().getShoes().setField(field);
            this.being.getEquipment().getShoes().setBeing(null);
            field.setArtifactOnThisField(being.getEquipment().getShoes());

        }
        else{
            field.setArtifactOnThisField(null);
        }
        this.being.getEquipment().setShoes(this);
        field=null;
        changeBeingParameters();
    }

}
