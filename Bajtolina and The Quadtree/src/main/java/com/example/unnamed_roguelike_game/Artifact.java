package com.example.unnamed_roguelike_game;

public abstract class Artifact {


    protected Field field;//jeżeli jest nullem to artefakt posiada jakiś byt
    protected double valueOfAttackChange;

    protected double valueOfDefenceChange;
    protected double valueOfSpeedChange;
    protected double valueOfMaxHealthPointsChange;

    protected Being being;
    private Integer numberOfPictureOnImageList;

    public Artifact(Field field,Integer numberOfPictureOnPicturesImageList,double valueOfAttackChange,double valueOfDefenceChange,double valueOfSpeedChange,double valueOfMaxHealthPointsChange){
        this.field=field;
        field.setArtifactOnThisField(this);
        this.numberOfPictureOnImageList=numberOfPictureOnPicturesImageList;
        this.valueOfSpeedChange=valueOfSpeedChange;
        this.valueOfMaxHealthPointsChange=valueOfMaxHealthPointsChange;
        this.valueOfDefenceChange=valueOfDefenceChange;
        this.valueOfAttackChange=valueOfAttackChange;
        being=null;
    }
    public void changeBeingParameters() {
        if(this!=null) {
            being.changeBeingStatistics(valueOfAttackChange, valueOfDefenceChange, valueOfSpeedChange, valueOfMaxHealthPointsChange);
        }
    }
    public void undoChangeBeingParameters() {
        if(this!=null) {
            being.changeBeingStatistics(-valueOfAttackChange, -valueOfDefenceChange, -valueOfSpeedChange, -valueOfMaxHealthPointsChange);
        }
    }
    public abstract void swapArtifacts(Being being);//swap wykonywany na artefakcie który się zakłada , NIE na tym który się zdejmuje
    public void setBeing(Being being) {
        this.being = being;
    }
    public void setParametrs(double valueOfAttackChange,double valueOfDefenceChange,double valueOfSpeedChange,double valueOfMaxHealthPointsChange){
        this.valueOfAttackChange=valueOfAttackChange;
        this.valueOfDefenceChange=valueOfDefenceChange;
        this.valueOfSpeedChange=valueOfSpeedChange;
        this.valueOfMaxHealthPointsChange=valueOfMaxHealthPointsChange;
    }

    public Integer getNumberOfPictureOnImageList() {
        return numberOfPictureOnImageList;
    }

    public Being getBeing() {
        return being;
    }

    public Field getField() {
        return field;
    }
    public void setField(Field field) {
        this.field = field;
    }
}
