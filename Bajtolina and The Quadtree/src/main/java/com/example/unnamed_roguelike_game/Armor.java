package com.example.unnamed_roguelike_game;

public class Armor extends Artifact{


    public Armor(Field field, Integer numberOfPictureOnPicturesImageList, double valueOfAttackChange, double valueOfDefenceChange, double valueOfSpeedChange, double valueOfMaxHealthPointsChange) {
        super(field, numberOfPictureOnPicturesImageList, valueOfAttackChange, valueOfDefenceChange, valueOfSpeedChange, valueOfMaxHealthPointsChange);
    }

    @Override
    public void swapArtifacts(Being being) {
        this.being=being;

        if(being.getEquipment().getArmor()!=null){
            this.being.getEquipment().getArmor().undoChangeBeingParameters();
            this.being.getEquipment().getArmor().setField(field);
            this.being.getEquipment().getArmor().setBeing(null);
            field.setArtifactOnThisField(being.getEquipment().getArmor());

        }
        else{
            field.setArtifactOnThisField(null);
        }
        this.being.getEquipment().setArmor(this);
        field=null;
        changeBeingParameters();
    }

}
