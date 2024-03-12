package com.example.unnamed_roguelike_game;

import java.util.*;

public class Leaf {//ta klasa opisuje liść
    private int height;
    private int  width;
    private Field[][] leafsFields;
    private int levelOfLeaf;
    private Map<Integer, Being> beingMap = new TreeMap<>();
    private Map<Integer,Artifact> artifactMap=new TreeMap<>();
    public void generate(){

        Random random=new Random();
        for(int i=0;i<height;++i){
            for(int j=0;j<width;++j) {
                if (Integer.toUnsignedLong(random.nextInt())%2!=0 ) {
                    leafsFields[i][j] = new Field(i * width + j, true, i, j, FieldType.enumFieldType.floor, this);
                }
                else{
                    leafsFields[i][j] = new Field(i * width + j, true, i, j, FieldType.enumFieldType.wall, this);
                }
            }
        }


    //ustalanie sąsiadów
        Field[] neighbors = new Field[7];
        //sąsiedztwo pola o współrzędnych 0 0
        neighbors[0] = null;
        neighbors[1] = null;
        neighbors[2] = null;
        neighbors[3] = leafsFields[0][1];
        neighbors[4] = leafsFields[1][0];
        neighbors[5] = leafsFields[1][1];
        neighbors[6] = leafsFields[0][0];
        leafsFields[0][0].setNeighbors(neighbors);

        //sąsiedztwo pola o współrzędnych 0 width-1
        neighbors[0] = null;
        neighbors[1] = null;
        neighbors[2] = leafsFields[0][width-2];
        neighbors[3] = null;
        neighbors[4] = leafsFields[1][width-1];
        neighbors[5] = null;
        neighbors[6] = leafsFields[0][width-1];
        leafsFields[0][width - 1].setNeighbors(neighbors);

        //sąsiedztwo pól o współrzędnych height-1 0
        if(height%2==0){
            neighbors[0] = null;
            neighbors[1] = leafsFields[height-2][0];
            neighbors[2] = null;
            neighbors[3] = leafsFields[height-1][1];
            neighbors[4] = null;
            neighbors[5] = null;
            neighbors[6] = leafsFields[height-1][0];
            leafsFields[height-1][0].setNeighbors(neighbors);
        }
        else {
            neighbors[0] = leafsFields[height-2][0];
            neighbors[1] = leafsFields[height-2][1];
            neighbors[2] = null;
            neighbors[3] = leafsFields[height-1][1];
            neighbors[4] = null;
            neighbors[5] = null;
            neighbors[6] = leafsFields[height-1][0];
            leafsFields[height-1][0].setNeighbors(neighbors);
        }

        //sąsiedztwo pól o współrzędnych height-1 width-1
        if(height%2==0){
            neighbors[0] = leafsFields[height-2][width-2];
            neighbors[1] = leafsFields[height-2][width-1];
            neighbors[2] = leafsFields[height-1][width-2];
            neighbors[3] = null;
            neighbors[4] = null;
            neighbors[5] = null;
            neighbors[6] = leafsFields[height-1][width-1];
            leafsFields[height-1][width-1].setNeighbors(neighbors);
        }
        else {
            neighbors[0] = leafsFields[height-2][width-1];
            neighbors[1] = null;
            neighbors[2] = leafsFields[height-1][width-2];
            neighbors[3] = null;
            neighbors[4] = null;
            neighbors[5] = null;
            neighbors[6] = leafsFields[height-1][width-1];
            leafsFields[height-1][width-1].setNeighbors(neighbors);
        }
        //sąsiedztwo pól "na górze liścia" z wyłączeniem skrajnie lewego i skrajnie prawego
        for (int i=1;i<width-1;++i) {
            neighbors[0] = null;
            neighbors[1] = null;
            neighbors[2] = leafsFields[0][i-1];
            neighbors[3] = leafsFields[0][i+1];
            neighbors[4] = leafsFields[1][i];
            neighbors[5] = leafsFields[1][i+1];
            neighbors[6] = leafsFields[0][i];
            leafsFields[0][i].setNeighbors(neighbors);
        }
        //sąsiedztwo pól "na dole liścia" z wyłączeniem skrajnie lewego i skrajnie prawego
        for (int i=1;i<width-1;++i) {
            if(height%2==0){
                neighbors[0] = leafsFields[height-2][i-1];
                neighbors[1] = leafsFields[height-2][i];
                neighbors[2] = leafsFields[height-1][i-1];
                neighbors[3] = leafsFields[height-1][i+1];
                neighbors[4] = null;
                neighbors[5] = null;
                neighbors[6] = leafsFields[height-1][i];
                leafsFields[height-1][i].setNeighbors(neighbors);
            }
            else{
                neighbors[0] = leafsFields[height-2][i];
                neighbors[1] = leafsFields[height-2][i+1];
                neighbors[2] = leafsFields[height-1][i-1];
                neighbors[3] = leafsFields[height-1][i+1];
                neighbors[4] = null;
                neighbors[5] = null;
                neighbors[6] = leafsFields[height-1][i];
                leafsFields[height-1][i].setNeighbors(neighbors);
            }
        }
        //sąsiedztwo pól "z lewej strony liścia" z wyłączeniem skrajnie górnego i skrajnie dolnego
        for (int i=1;i<height-1;++i) {
            if(i%2==0){
                neighbors[0] = leafsFields[i-1][0];
                neighbors[1] = leafsFields[i-1][1];
                neighbors[2] = null;
                neighbors[3] = leafsFields[i][1];
                neighbors[4] = leafsFields[i+1][0];
                neighbors[5] = leafsFields[i+1][1];
                neighbors[6] = leafsFields[i][0];
                leafsFields[i][0].setNeighbors(neighbors);
            }
            else{
                neighbors[0] = null;
                neighbors[1] = leafsFields[i-1][0];
                neighbors[2] = null;
                neighbors[3] = leafsFields[i][1];
                neighbors[4] = null;
                neighbors[5] = leafsFields[i+1][0];
                neighbors[6] = leafsFields[i][0];
                leafsFields[i][0].setNeighbors(neighbors);
            }
        }
        //sąsiedztwo pól "z prawej strony liścia" z wyłączeniem skrajnie górnego i skrajnie dolnego
        for (int i=1;i<height-1;++i) {
            if(i%2==0){
                neighbors[0] = leafsFields[i-1][width-1];
                neighbors[1] = null;
                neighbors[2] = leafsFields[i][width-2];
                neighbors[3] = null;
                neighbors[4] = leafsFields[i+1][width-1];
                neighbors[5] = null;
                neighbors[6] = leafsFields[i][width-1];
                leafsFields[i][width-1].setNeighbors(neighbors);
            }
            else{
                neighbors[0] = leafsFields[i-1][width-2];
                neighbors[1] = leafsFields[i-1][width-1];
                neighbors[2] = leafsFields[i][width-2];
                neighbors[3] = null;
                neighbors[4] = leafsFields[i+1][width-2];
                neighbors[5] = leafsFields[i+1][width-1];
                neighbors[6] = leafsFields[i][width-1];
                leafsFields[i][width-1].setNeighbors(neighbors);
            }
        }

        //sąsiedztwo pól w środku liścia
        for(int i=1;i<height-1;++i)
        {
            if(i%2==0){
                for(int j=1;j<width-1;++j){
                    neighbors[0] = leafsFields[i-1][j];
                    neighbors[1] = leafsFields[i-1][j+1];
                    neighbors[2] = leafsFields[i][j-1];
                    neighbors[3] = leafsFields[i][j+1];
                    neighbors[4] = leafsFields[i+1][j];
                    neighbors[5] = leafsFields[i+1][j+1];
                    neighbors[6] = leafsFields[i][j];
                    leafsFields[i][j].setNeighbors(neighbors);
                }
            }
            else{
                for(int j=1;j<width-1;++j){
                    neighbors[0] = leafsFields[i-1][j-1];
                    neighbors[1] = leafsFields[i-1][j];
                    neighbors[2] = leafsFields[i][j-1];
                    neighbors[3] = leafsFields[i][j+1];
                    neighbors[4] = leafsFields[i+1][j-1];
                    neighbors[5] = leafsFields[i+1][j];
                    neighbors[6] = leafsFields[i][j];
                    leafsFields[i][j].setNeighbors(neighbors);
                }
            }
        }

        leafsFields[0][0].setType(FieldType.enumFieldType.floor);
        leafsFields[0][0].BFS(leafsFields[0][0]);
        while(!isCorrect()){
            Field temp=leafsFields[random.nextInt(height)][random.nextInt(width)];
            temp.setType(FieldType.enumFieldType.floor);
            leafsFields[0][0].BFS(leafsFields[0][0]);
        }
    }
    public void generateBeingsArtifactsAndFlowers(){

        Random random=new Random();
        double levelFactor=0.55+levelOfLeaf*0.3;//te liczby wynikają z nieudanych prób zbalansowanie gry
        for(Field[] i:leafsFields){
            for(Field j:i) {
                if ((j.getBeingOnThisField() == null) && (j.getType().equals(FieldType.enumFieldType.floor))) {
                    if (random.nextInt(100) < 6) {//3 procent szans, że będzie potwór
                        BeingType.enumBeingType temp;
                        if (random.nextBoolean()) {
                            temp = BeingType.enumBeingType.redAnt;
                        } else {
                            temp = BeingType.enumBeingType.blackAnt;
                        }
                        Being newBeing = new Being(Being.nextCorrectID, j, this, Being.defaultMaxhealthPoints * levelFactor, Being.defaultAttack * levelFactor, Being.defaultAttack * levelFactor, Being.defaultSpeed * levelFactor, Being.defaultViewRange, Being.defaultRegenerationInPercent * levelFactor, temp, MainWithGraphic.decisionsAndEventsQueue, 0);//wszystko dodane w czasie 0
                        MainWithGraphic.beingList.add(newBeing);
                        Being.nextCorrectID++;
                    }
                }
                if ((j.getBeingOnThisField() == null) && (j.getType().equals(FieldType.enumFieldType.floor))) {
                    if (random.nextInt(100) < 50) {//2 procent szans, że będzie artefakt
                        Artifact temp;
                        int modulo1 = (int) (5 * levelFactor), modulo2 = (int) (2 * levelFactor);
                        int caseArt = random.nextInt(9);
                        if (caseArt == 0) {
                            temp = new Armor(j, 14, random.nextInt(modulo2), random.nextInt(modulo1), random.nextInt(modulo2), random.nextInt(modulo2));
                        } else if (caseArt == 1) {
                            temp = new Armor(j, 15, random.nextInt(modulo2), random.nextInt(modulo1), random.nextInt(modulo2), random.nextInt(modulo2));
                        } else if (caseArt == 2) {
                            temp = new Weapon(j, 10, random.nextInt(modulo1), random.nextInt(modulo2), random.nextInt(modulo2), random.nextInt(modulo2));
                        } else if (caseArt == 3) {
                            temp = new Weapon(j, 11, random.nextInt(modulo1), random.nextInt(modulo2), random.nextInt(modulo2), random.nextInt(modulo2));
                        } else if (caseArt == 4) {
                            temp = new Weapon(j, 12, random.nextInt(modulo1), random.nextInt(modulo2), random.nextInt(modulo2), random.nextInt(modulo2));

                        } else if (caseArt == 5) {
                            temp = new Weapon(j, 13, random.nextInt(modulo1), random.nextInt(modulo2), random.nextInt(modulo2), random.nextInt(modulo2));

                        } else if (caseArt == 6) {
                            temp = new Shoes(j, 16, random.nextInt(modulo2), random.nextInt(modulo2), random.nextInt(modulo2), random.nextInt(modulo2));

                        } else if (caseArt == 7) {
                            temp = new RangedWeapon(j, 17, random.nextInt(modulo1), random.nextInt(modulo2), random.nextInt(modulo2), random.nextInt(modulo2));

                        } else if (caseArt == 8) {
                            temp = new Helmet(j, 18, random.nextInt(modulo2), random.nextInt(modulo2), random.nextInt(modulo2), random.nextInt(modulo1));

                        }
                    }
                }
                if ((j.getBeingOnThisField() == null) && (j.getType().equals(FieldType.enumFieldType.floor) && (j.getArtifactOnThisField() == null))) {
                    if(random.nextInt(200)<10){
                        j.setFlowerOfPowerOnThisField(new FlowerOfPower(j));
                    }
                }
            }
        }
    }
    public Leaf(int height,int width,int levelOfLeaf) {
        this.height=height;
        this.width=width;
        this.levelOfLeaf=levelOfLeaf;
        leafsFields=new Field[height][width];
        this.generate();
        this.generateBeingsArtifactsAndFlowers();
    }
    private boolean isCorrect(){
        for(Field[] i:leafsFields){
            for(Field j:i){
                if((j.isVisited()==false)&&(!j.getType().equals(FieldType.enumFieldType.wall))){
                    return false;
                }
            }
        }
        double numberOfWall=0;
        for(Field[] i:leafsFields){
            for(Field j:i){
                if(j.getType().equals(FieldType.enumFieldType.wall)){
                    numberOfWall++;
                }
            }
        }
        if(numberOfWall<width*height*0.30){
            Random random=new Random();
            for(int i=0;i<height;++i){
                for(int j=0;j<width;++j) {
                    if (Integer.toUnsignedLong(random.nextInt())%2!=0 ) {
                        leafsFields[i][j].setType(FieldType.enumFieldType.floor);
                    }
                    else{
                        leafsFields[i][j].setType(FieldType.enumFieldType.wall);
                    }
                }
            }
            leafsFields[0][0].setType(FieldType.enumFieldType.floor);
            return false;
        }
        return true;
    }
    public Field[][] getLeafsFields() {
        return leafsFields;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getLevelOfLeaf() {
        return levelOfLeaf;
    }

    public Map<Integer, Being> getBeingMap() {
        return beingMap;
    }

    public void addBeing(Being being, Field field){//dodaje jakiś byt being na pole field do konkretnego liścia
        being.setField(field);
        being.setLeaf(this);
        beingMap.put(being.getBeingId(), being);
    }
    public void moveOfBeing(int beingId,String direction){
        /*System.out.println("TUTAJ:");
        System.out.println(beingMap.size());*/
        Field temporaryField=beingMap.get(beingId).getField();


        Field temporaryNeighborFields[] = temporaryField.getNeighbors();//dostaję listę z id sąsiadów
        Field destinationField=null;
        if(direction=="LU") {
            destinationField=temporaryNeighborFields[0];
        }
        else if(direction=="RU"){
            destinationField=temporaryNeighborFields[1];
        }
        else if(direction=="LL"){
            destinationField=temporaryNeighborFields[2];
        }
        else if(direction=="RR"){
            destinationField=temporaryNeighborFields[3];
        }
        else if(direction=="LD"){
            destinationField=temporaryNeighborFields[4];
        }
        else if(direction=="RD"){
            destinationField=temporaryNeighborFields[5];
        }
        else if(direction=="WaitOrChangeLeaf"){
            destinationField=temporaryNeighborFields[6];

            //zjadanie kwiatka mocy
            if(temporaryField.getFlowerOfPowerOnThisField()!=null) {
                temporaryField.getFlowerOfPowerOnThisField().visit(beingMap.get(beingId));
            }
        }

        if((destinationField!=null)&&(destinationField.getType()!= FieldType.enumFieldType.wall)&&(destinationField.getType()!= FieldType.enumFieldType.nulll)&&
                (destinationField.getBeingOnThisField()==null)) {// sprawdzenie, czy nie wychodzimy poza mapę
            if(destinationField.getLeaf()!=this) {
                beingMap.get(beingId).getField().setBeingOnThisField(null);
                beingMap.get(beingId).setLeaf(destinationField.getLeaf());
                destinationField.getLeaf().addBeing(beingMap.get(beingId),destinationField);
                destinationField.setBeingOnThisField(beingMap.get(beingId));
                beingMap.get(beingId).setField(destinationField);
                beingMap.remove(beingId);
            }else {
                destinationField.setBeingOnThisField(beingMap.get(beingId));
                beingMap.get(beingId).getField().setBeingOnThisField(null);
                beingMap.get(beingId).setField(destinationField);
            }
        } else if ((destinationField!=null)&&(destinationField.getType()!= FieldType.enumFieldType.nulll)&&(destinationField.getType()!= FieldType.enumFieldType.wall)) {
            beingMap.get(beingId).attackAnotherBeing(destinationField.getBeingOnThisField());
            if(MainWithGraphic.bajtolina.getHealthPoints()<=0){
                while(!MainWithGraphic.decisionsAndEventsQueue.isEmpty()){
                    MainWithGraphic.decisionsAndEventsQueue.poll();
                }
            }
        }
    }
    public void printLeafOnAscii() {
        for(int i=0;i<height;++i){
            if(i%2==0){
                System.out.print(" ");
            }
            for(int j=0;j<width;++j){

                if(leafsFields[i][j].getBeingOnThisField()==null){
                    System.out.print(leafsFields[i][j].getAsciiRepresentation());
                }
                else{
                    System.out.print(leafsFields[i][j].getBeingOnThisField().getAsciiRepresentation());
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
