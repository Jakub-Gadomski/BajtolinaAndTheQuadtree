package com.example.unnamed_roguelike_game;

public class Helmet extends Artifact {

    public Helmet(Field field, Integer numberOfPictureOnPicturesImageList, double valueOfAttackChange, double valueOfDefenceChange, double valueOfSpeedChange, double valueOfMaxHealthPointsChange) {
        super(field, numberOfPictureOnPicturesImageList, valueOfAttackChange, valueOfDefenceChange, valueOfSpeedChange, valueOfMaxHealthPointsChange);
    }

    @Override
    public void swapArtifacts(Being being) {
        this.being=being;

        if(being.getEquipment().getHelmet()!=null){
            this.being.getEquipment().getHelmet().undoChangeBeingParameters();
            this.being.getEquipment().getHelmet().setField(field);
            this.being.getEquipment().getHelmet().setBeing(null);
            field.setArtifactOnThisField(being.getEquipment().getHelmet());

        }
        else{
            field.setArtifactOnThisField(null);
        }
        this.being.getEquipment().setHelmet(this);
        field=null;
        changeBeingParameters();
    }
}
