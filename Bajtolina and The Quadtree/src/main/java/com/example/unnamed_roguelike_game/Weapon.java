package com.example.unnamed_roguelike_game;

public class Weapon extends Artifact{


    public Weapon(Field field, Integer numberOfPictureOnPicturesImageList, double valueOfAttackChange, double valueOfDefenceChange, double valueOfSpeedChange, double valueOfMaxHealthPointsChange) {
        super(field, numberOfPictureOnPicturesImageList, valueOfAttackChange, valueOfDefenceChange, valueOfSpeedChange, valueOfMaxHealthPointsChange);
    }

    public void swapArtifacts(Being being) {

        this.being=being;

        if(being.getEquipment().getWeapon()!=null){
            this.being.getEquipment().getWeapon().undoChangeBeingParameters();
            this.being.getEquipment().getWeapon().setField(field);
            this.being.getEquipment().getWeapon().setBeing(null);
            field.setArtifactOnThisField(being.getEquipment().getWeapon());

        }
        else{
            field.setArtifactOnThisField(null);
        }
        this.being.getEquipment().setWeapon(this);
        field=null;
        changeBeingParameters();
    }
}
