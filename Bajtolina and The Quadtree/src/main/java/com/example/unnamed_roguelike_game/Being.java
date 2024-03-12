package com.example.unnamed_roguelike_game;

public class Being {//klasa opisująca każdy byt w grze, który będzie miał podobne funkcje co gracz
    public static int nextCorrectID=2;
    public static double defaultAttack=10;
    public static double defaultDefence=5;
    public static double defaultMaxhealthPoints=10;
    public static double defaultSpeed=1;
    public static double defaultViewRange=4;
    public static double defaultRegenerationInPercent=5;

    private final int beingId;
    private Field field;
    private Leaf leaf;      //zmienne opisujące położenie bytu
    private double healthPoints;
    private double maxHealthPoints;
    private double attack;
    private double defense;
    private double speed;
    private double viewRange;
    private double defaultPriorityOfEventAction;
    private BeingType.enumBeingType type;
    private Equipment equipment;
    private char asciiRepresentation;
    private boolean isDead;
    private double healthRegenerationInPercent;
    private int experienceLevel;
    private int experiencePoints;
    private int eatenFlowers;

    public Being(int beingId,Field field,Leaf leaf,double healthPoints,double attack,double defense,double speed,double viewRange,double healthRegenerationInPercent,BeingType.enumBeingType type,DecisionsAndEventsQueue mainEventsQueue,double actualTimeInGame){
        this.field=field;
        this.leaf=leaf;
        this.beingId=beingId;
        this.healthPoints=healthPoints;
        this.attack=attack;
        this.defense=defense;
        this.speed=speed;
        speed=Math.min(speed,5);//żeby byt bnie był za szybki
        this.viewRange=viewRange;
        this.type=type;
        this.maxHealthPoints=healthPoints;
        this.setAsciiRepresentation();
        this.leaf.addBeing(this,field);
        this.isDead=false;
        this.healthRegenerationInPercent=healthRegenerationInPercent;
        this.experienceLevel=1;
        this.equipment=new Equipment();
        this.experiencePoints=0;
        this.eatenFlowers=0;
        field.setBeingOnThisField(this);
        if(type.equals(BeingType.enumBeingType.bajtolina)){
            PlayerDecisionEvent firstEvent = new PlayerDecisionEvent(actualTimeInGame, getDefaultPriorityOfEventAction(),this);
            mainEventsQueue.addSingleEvent(firstEvent);
        }else{
            this.setDefaultPriorityOfEventAction(-1000);
            AIBeingDecisionEvent event= new AIBeingDecisionEvent(actualTimeInGame,getDefaultPriorityOfEventAction(),this);
            mainEventsQueue.addSingleEvent(event);
        }
        HealthBeingEvent event1=new HealthBeingEvent(actualTimeInGame, getDefaultPriorityOfEventAction(),1,this);//ta jedynka to że leczy się co 1 ture ale to trzeba poprawic
        mainEventsQueue.addSingleEvent(event1);
    }
    public void attackAnotherBeing(Being target){
        target.setHealthPoints(target.getHealthPoints()-Math.max(1,MyRand.randomDouble(3,1,Math.max(1,this.attack-target.getDefense()))));
        if(target.getHealthPoints()<=0){
            target.deleteThisBeing(this);
        }
    }
    public void doNextAction(String nextEvent){
        if(nextEvent=="attackStart"){
            //atak
            //tutaj chyba nic nie trzeba dodawać
            //attackAnotherBeing(leaf.getLeafsFields()[AttackPointer.getX()][AttackPointer.getY()].getBeingOnThisField());
        }
        else{
            leaf.moveOfBeing(beingId,nextEvent);
        }
    }
    public void deleteThisBeing(Being beingWhoKillThis){
        this.leaf.getBeingMap().remove(this.beingId);
        this.field.setBeingOnThisField(null);
        field=null;
        leaf=null;
        this.isDead=true;
        beingWhoKillThis.moreExp(Math.toIntExact(Math.round(this.getMaxHealthPoints())));
    }
    public void levelUp(){
        double tempHealth=maxHealthPoints;
        maxHealthPoints*=1.02;
        maxHealthPoints=Math.ceil(maxHealthPoints);
        healthPoints+=maxHealthPoints-tempHealth;
        attack*=1.02;
        attack=Math.ceil(attack);
        defense*=1.02;
        defense=Math.ceil(defense);
        experienceLevel++;
    }
    public void moreExp(int exp){
        experiencePoints+=exp;
        while(experiencePoints>=100){
            experiencePoints-=100;
            levelUp();
        }
    }

    public void regenerateHealthPoints(){
            healthPoints=Math.min(maxHealthPoints,healthPoints+maxHealthPoints*healthRegenerationInPercent/100);
    }
    public void changeBeingStatistics(double attackChange,double defenseChange,double speedChange,double maxHealthPointsChange){
        attack+=attackChange;
        defense+=defenseChange;
        speed+=(speedChange*0.1);
        maxHealthPoints+=maxHealthPointsChange;
        if(maxHealthPointsChange>0){
            healthPoints+=maxHealthPointsChange;
        }
        attack=Math.max(attack,1);
        defense=Math.max(defense,1);
        speed=Math.max(speed,0.01);
        speed=Math.min(speed,5);//żeby byt bnie był za szybki

        maxHealthPoints=Math.max(maxHealthPoints,1);
        healthPoints=Math.min(maxHealthPoints,healthPoints);
    }
    public void changeBeingStatistics(double attackChange,double defenseChange,double speedChange,double maxHealthPointsChange,double healthPointsChange){
        attack+=attackChange;
        defense+=defenseChange;
        speed+=(speedChange*0.1);
        maxHealthPoints+=maxHealthPointsChange;
        if(maxHealthPointsChange>0){
            healthPoints+=maxHealthPointsChange;
        }
        attack=Math.max(attack,1);
        defense=Math.max(defense,1);
        speed=Math.max(speed,0.01);
        speed=Math.min(speed,5);//żeby byt bnie był za szybki

        maxHealthPoints=Math.max(maxHealthPoints,1);
        healthPoints=Math.min(maxHealthPoints,healthPoints);

        healthPoints+=healthPointsChange;
        healthPoints=Math.min(healthPoints,maxHealthPoints);


        healthPoints=Math.max(1.,healthPoints);
    }


    public char getAsciiRepresentation() {
        return asciiRepresentation;
    }
    public int getBeingId() {
        return beingId;
    }

    public Field getField() {
        return field;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }
    public double getSpeed() {
        return speed;
    }

    public double getViewRange() {
        return viewRange;
    }

    public double getHealthPoints() {
        return healthPoints;
    }
    public double getMaxHealthPoints() {
        return maxHealthPoints;
    }
    public Leaf getLeaf() {
        return leaf;
    }

    public BeingType.enumBeingType getType() {
        return type;
    }
    public double getDefaultPriorityOfEventAction() {
        return defaultPriorityOfEventAction;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public double getHealthRegenerationInPercent() {
        return healthRegenerationInPercent;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public int getEatenFlowers() {
        return eatenFlowers;
    }

    public void setEatenFlowers(int eatenFlowers) {
        this.eatenFlowers = eatenFlowers;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public void setHealthRegenerationInPercent(double healthRegenerationInPercent) {
        this.healthRegenerationInPercent = healthRegenerationInPercent;
    }
    void setIsDead(boolean isDead) {
        this.isDead=isDead;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }
    public void setLeaf(Leaf leaf) {
        this.leaf = leaf;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
        speed=Math.min(speed,5);//żeby byt bnie był za szybki

    }

    public void setViewRange(double viewRange) {
        this.viewRange = viewRange;
    }

    public void setDefaultPriorityOfEventAction(double defaultPriorityOfEventAction) {
        this.defaultPriorityOfEventAction = defaultPriorityOfEventAction;
    }

    public void setType(BeingType.enumBeingType type) {
        this.type = type;
    }
    public void setMaxHealthPoints(double maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    //od tego momentu w dół możliwe, że trzeba będzie poprawić

    private void setAsciiRepresentation(){
        if(type.equals(BeingType.enumBeingType.bajtolina)) {
            asciiRepresentation='@';
        }
        //tutaj jeszcze tyle if ile będzie typów
        else {
            asciiRepresentation='E';
        }
    }
}
