package com.example.unnamed_roguelike_game;

public class Equipment {
    //klasa opisuje co znajduje się w ekwipunku bytu
    //byt może mieć maksymalnie jeden artefakt danego rodzaju

    //to trzeba będzie poprawić
    Artifact weapon;
    Artifact rangedWeapon;
    Artifact armor;
    Artifact shoes;
    Artifact helmet;
    public Equipment(){
        weapon=null;
        rangedWeapon=null;
        armor=null;
        shoes=null;
        helmet=null;
    }

    public void setWeapon(Artifact weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Artifact armor) {
        this.armor = armor;
    }


    public void setHelmet(Artifact helmet) {
        this.helmet = helmet;
    }

    public void setRangedWeapon(Artifact rangedWeapon) {
        this.rangedWeapon = rangedWeapon;
    }

    public void setShoes(Artifact shoes) {
        this.shoes = shoes;
    }



    public Artifact getArmor() {
        return armor;
    }


    public Artifact getHelmet() {
        return helmet;
    }

    public Artifact getRangedWeapon() {
        return rangedWeapon;
    }

    public Artifact getShoes() {
        return shoes;
    }

    public Artifact getWeapon() {
        return weapon;
    }
}
