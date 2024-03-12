package com.example.unnamed_roguelike_game;

public class RangedWeapon extends Artifact{

    public RangedWeapon(Field field, Integer numberOfPictureOnPicturesImageList, double valueOfAttackChange, double valueOfDefenceChange, double valueOfSpeedChange, double valueOfMaxHealthPointsChange) {
        super(field, numberOfPictureOnPicturesImageList, valueOfAttackChange, valueOfDefenceChange, valueOfSpeedChange, valueOfMaxHealthPointsChange);
    }

    public void swapArtifacts(Being being) {
        this.being=being;

        //tutaj nie ma tych 2 funkcji bo atak dystansowy będzie działał inaczej
        if(being.getEquipment().getRangedWeapon()!=null){
            this.being.getEquipment().getRangedWeapon().setField(field);
            this.being.getEquipment().getRangedWeapon().setBeing(null);
            field.setArtifactOnThisField(being.getEquipment().getRangedWeapon());

        }
        else{
            field.setArtifactOnThisField(null);
        }
        this.being.getEquipment().setRangedWeapon(this);
        field=null;

    }
}
