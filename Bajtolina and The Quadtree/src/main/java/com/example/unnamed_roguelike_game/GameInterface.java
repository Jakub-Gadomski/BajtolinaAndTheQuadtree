package com.example.unnamed_roguelike_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameInterface extends JFrame implements KeyListener {

    private static int fileSizeOnScreen=150;
    private static int numberOfRowsAndColumnOnScreen=7;
    private List<File> picturesList = new ArrayList<File>();
    private MyDrawPanel[][] graphicLeaf;
    private List<MyDrawPanel> graphicBeings=new ArrayList<MyDrawPanel>();
    private List<MyDrawPanel> graphicAtrifactsAndFlowers =new ArrayList<MyDrawPanel>();
    private Map<BeingType.enumBeingType,ImageIcon> iconBeingMap=new HashMap<BeingType.enumBeingType,ImageIcon>();
    private List<ImageIcon> imageIconList=new ArrayList<ImageIcon>();
    private HealthPointsRectPanel healthPointsRects=new HealthPointsRectPanel();
    private MyDrawPanel specialMarkToAttack;
    private JLayeredPane layeredGameMapPane;
    private JPanel mainGamePanel;
    private JPanel controlPanel;
    private JTextArea textOnTheRight;
    private String textOnTheRightValueOfPlayerStatistics;
    private GridBagConstraints gridBagConstraints;
    public GameInterface() {
        setTitle("Bajtolina and The Quadtree");
        setSize(1800, 1200);
        setLocationRelativeTo(null);//ustawia pojawiające się okno na środku
        setDefaultCloseOperation(EXIT_ON_CLOSE);//zatrzymuje program, gdy klikniemy "x" w prawym górnym rogu
        loadPictures();

        mainGamePanel=new JPanel(new GridBagLayout());
        mainGamePanel.setPreferredSize(new Dimension(100,50));
        mainGamePanel.setBackground(Color.BLACK);
        controlPanel=new JPanel();

        add(mainGamePanel);


        gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        layeredGameMapPane =new JLayeredPane();
        layeredGameMapPane.setPreferredSize(new Dimension(1050,900));
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        mainGamePanel.add(layeredGameMapPane,gridBagConstraints);


        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=0;


        controlPanel.setPreferredSize(new Dimension(400,900));
        controlPanel.setBackground(Color.WHITE);
        mainGamePanel.add(controlPanel,gridBagConstraints);

        textOnTheRight =new JTextArea();
        textOnTheRight.setEditable(false);
        textOnTheRight.setFocusable(false);
        controlPanel.add(textOnTheRight);


        graphicLeaf=new MyDrawPanel[numberOfRowsAndColumnOnScreen][numberOfRowsAndColumnOnScreen]; //te magiczne liczby określają rozmiar wyświetlanej tablicy

        for(int i=0;i<numberOfRowsAndColumnOnScreen;++i){
            for(int j=0;j<numberOfRowsAndColumnOnScreen;++j){

                graphicLeaf[i][j]=new MyDrawPanel(0,0,0,0,imageIconList.get(0));
                layeredGameMapPane.add(graphicLeaf[i][j],2);
            }
        }
        specialMarkToAttack=new MyDrawPanel(0,0,0,0,imageIconList.get(0));

        healthPointsRects.setBounds(1,1,1200,900);
        layeredGameMapPane.add(healthPointsRects,3);
        addKeyListener(this);
    }
    public void draw(Leaf leaf, Being bajtolina,Field drawField) {//przyjmuje liścia, którego wyświetlam, postać, względem której wyświetlam i ewentualny wskaźnik na atakowane pole, jeżeli włączony jest tryb ataku w przeciwnym razie będzie tam null
        leaf=drawField.getLeaf();
        if(bajtolina.getHealthPoints()<=0){
            textOnTheRightValueOfPlayerStatistics="Bajtolina\n";
            textOnTheRightValueOfPlayerStatistics+="attack:                        ";
            textOnTheRightValueOfPlayerStatistics+=0+"";
            textOnTheRightValueOfPlayerStatistics+="\n";
            textOnTheRightValueOfPlayerStatistics+="defence:                     ";
            textOnTheRightValueOfPlayerStatistics+=0+"";
            textOnTheRightValueOfPlayerStatistics+="\n";
            textOnTheRightValueOfPlayerStatistics+="speed:                        ";
            textOnTheRightValueOfPlayerStatistics+=0+"";
            textOnTheRightValueOfPlayerStatistics+="\n";
            textOnTheRightValueOfPlayerStatistics+="health points:             ";
            textOnTheRightValueOfPlayerStatistics+=0+"";
            textOnTheRightValueOfPlayerStatistics+="\n";
            textOnTheRightValueOfPlayerStatistics+="max health points:      ";
            textOnTheRightValueOfPlayerStatistics+=0+"";
            textOnTheRightValueOfPlayerStatistics+="\n";
            textOnTheRightValueOfPlayerStatistics+="exp lvl:                        ";
            textOnTheRightValueOfPlayerStatistics+=0+"";
            textOnTheRightValueOfPlayerStatistics+="\n";
            textOnTheRightValueOfPlayerStatistics+="exp pts:                       ";
            textOnTheRightValueOfPlayerStatistics+=0+"";

            textOnTheRightValueOfPlayerStatistics+="\n";
            textOnTheRightValueOfPlayerStatistics+="weapon:                      ";
            textOnTheRightValueOfPlayerStatistics+="0/0/0/0";

            textOnTheRightValueOfPlayerStatistics+="\n";
            textOnTheRightValueOfPlayerStatistics+="armor:                         ";
            textOnTheRightValueOfPlayerStatistics+="0/0/0/0";

            textOnTheRightValueOfPlayerStatistics+="\n";
            textOnTheRightValueOfPlayerStatistics+="helmet:                        ";
            textOnTheRightValueOfPlayerStatistics+="0/0/0/0";

            textOnTheRightValueOfPlayerStatistics+="\n";
            textOnTheRightValueOfPlayerStatistics+="shoes:                         ";
            textOnTheRightValueOfPlayerStatistics+="0/0/0/0";

            textOnTheRightValueOfPlayerStatistics+="\n";
            textOnTheRightValueOfPlayerStatistics+="ranged weapon:          ";
            textOnTheRightValueOfPlayerStatistics+="0/0/0/0";
            textOnTheRightValueOfPlayerStatistics+="\n";


            textOnTheRightValueOfPlayerStatistics+="game time:                  ";
            textOnTheRightValueOfPlayerStatistics+=MainWithGraphic.gameTime+"";
            textOnTheRightValueOfPlayerStatistics+="\n";

            textOnTheRightValueOfPlayerStatistics+="leaf number:                ";
            textOnTheRightValueOfPlayerStatistics+="-";
            textOnTheRightValueOfPlayerStatistics+="\n";


            textOnTheRightValueOfPlayerStatistics+="leaf number:                ";
            textOnTheRightValueOfPlayerStatistics+="-/20";
            textOnTheRightValueOfPlayerStatistics+="\n";

            if(MainWithGraphic.gameState==1){
                textOnTheRightValueOfPlayerStatistics+="victory!";
            }
            if(MainWithGraphic.gameState==-1){
                textOnTheRightValueOfPlayerStatistics+="game over";
            }
            textOnTheRight.setText(textOnTheRightValueOfPlayerStatistics);
            Field tempField1,tempField2;
            int temp;
            int x=0,y=0;//współrzędne lewego górnego wyświetlanego polax
            temp=(drawField.getX()+1)%2;
            x=drawField.getX()-3;
            y=drawField.getY()-3;

            healthPointsRects.clear();

            for(MyDrawPanel i:graphicBeings){
                if(i!=null){
                    i.setIcon(imageIconList.get(0));
                }
            }
            graphicBeings.clear();

            for(MyDrawPanel i: graphicAtrifactsAndFlowers){
                if(i!=null){
                    i.setIcon(imageIconList.get(0));
                }
            }
            graphicAtrifactsAndFlowers.clear();
            specialMarkToAttack.setIcon(imageIconList.get(0));

            for(int i=x;i<x+numberOfRowsAndColumnOnScreen;++i){
                for(int j=y;j<y+numberOfRowsAndColumnOnScreen;++j){
                    if((i>=0)&&(j>=0)&&(i<leaf.getHeight())&&(j< leaf.getWidth())){
                        if(AttackPointer.getActive()){
                            if((AttackPointer.getY()==j)&&(AttackPointer.getX()==i)){
                                specialMarkToAttack=new MyDrawPanel((j-y-temp*((drawField.getX()+1)%2))*150+((i-x+1)%2)*75,(i-x)*110,150,150,imageIconList.get(7));
                                layeredGameMapPane.add(specialMarkToAttack,0);
                            }
                        }
                        graphicLeaf[i-x][j-y].setIcon(imageIconList.get(leaf.getLeafsFields()[i][j].getPictureId()));
                        graphicLeaf[i-x][j-y].setXY((j-y-temp*((drawField.getX()+1)%2))*150+((i-x+1)%2)*75,(i-x)*110,150,150);
                        if(leaf.getLeafsFields()[i][j].getArtifactOnThisField()!=null){
                            graphicAtrifactsAndFlowers.add(new MyDrawPanel((j-y-temp*((drawField.getX()+1)%2))*150+((i-x+1)%2)*75,(i-x)*110,150,150,
                                    imageIconList.get(leaf.getLeafsFields()[i][j].getArtifactOnThisField().getNumberOfPictureOnImageList())));
                            layeredGameMapPane.add(graphicAtrifactsAndFlowers.get(graphicAtrifactsAndFlowers.size()-1),1);

                        }
                        if(leaf.getLeafsFields()[i][j].getBeingOnThisField()!=null){
                            graphicBeings.add(new MyDrawPanel((j-y-temp*((drawField.getX()+1)%2))*150+((i-x+1)%2)*75,(i-x)*110,150,150,
                                    iconBeingMap.get(leaf.getLeafsFields()[i][j].getBeingOnThisField().getType())));
                            layeredGameMapPane.add(graphicBeings.get(graphicBeings.size()-1),1);

                            if(leaf.getLeafsFields()[i][j].getBeingOnThisField().getHealthPoints()!=leaf.getLeafsFields()[i][j].getBeingOnThisField().getMaxHealthPoints()){
                                healthPointsRects.drawRects((j-y-temp*((drawField.getX()+1)%2))*150+((i-x+1)%2)*75,(i-x)*110,
                                        leaf.getLeafsFields()[i][j].getBeingOnThisField().getHealthPoints()/leaf.getLeafsFields()[i][j].getBeingOnThisField().getMaxHealthPoints()*100);
                            }
                        }
                    }
                    else{
                        graphicLeaf[i-x][j-y].setIcon(imageIconList.get(0));
                    }
                }
                temp++;
                temp%=2;
            }
            return;
        }
        textOnTheRightValueOfPlayerStatistics="Bajtolina\n";
        textOnTheRightValueOfPlayerStatistics+="attack:                        ";
        textOnTheRightValueOfPlayerStatistics+=bajtolina.getAttack()+"";
        textOnTheRightValueOfPlayerStatistics+="\n";
        textOnTheRightValueOfPlayerStatistics+="defence:                     ";
        textOnTheRightValueOfPlayerStatistics+=bajtolina.getDefense()+"";
        textOnTheRightValueOfPlayerStatistics+="\n";
        textOnTheRightValueOfPlayerStatistics+="speed:                        ";
        textOnTheRightValueOfPlayerStatistics+=bajtolina.getSpeed()+"";
        textOnTheRightValueOfPlayerStatistics+="\n";
        textOnTheRightValueOfPlayerStatistics+="health points:             ";
        textOnTheRightValueOfPlayerStatistics+=bajtolina.getHealthPoints()+"";
        textOnTheRightValueOfPlayerStatistics+="\n";
        textOnTheRightValueOfPlayerStatistics+="max health points:      ";
        textOnTheRightValueOfPlayerStatistics+=bajtolina.getMaxHealthPoints()+"";
        textOnTheRightValueOfPlayerStatistics+="\n";
        textOnTheRightValueOfPlayerStatistics+="exp lvl:                        ";
        textOnTheRightValueOfPlayerStatistics+=bajtolina.getExperienceLevel()+"";
        textOnTheRightValueOfPlayerStatistics+="\n";
        textOnTheRightValueOfPlayerStatistics+="exp pts:                       ";
        textOnTheRightValueOfPlayerStatistics+=bajtolina.getExperiencePoints()+"";
        textOnTheRightValueOfPlayerStatistics+="\n";
        textOnTheRightValueOfPlayerStatistics+="weapon:                      ";
        if(bajtolina.getEquipment().weapon!=null) {
            textOnTheRightValueOfPlayerStatistics += (int)Math.round(bajtolina.getEquipment().weapon.valueOfAttackChange) + "/" + (int)Math.round(bajtolina.getEquipment().weapon.valueOfDefenceChange) + "/" + (int)Math.round(bajtolina.getEquipment().weapon.valueOfSpeedChange) + "/" + (int)Math.round(bajtolina.getEquipment().weapon.valueOfMaxHealthPointsChange) + "";
        }else{
            textOnTheRightValueOfPlayerStatistics+="0/0/0/0";
        }
        textOnTheRightValueOfPlayerStatistics+="\n";
        textOnTheRightValueOfPlayerStatistics+="armor:                         ";
        if(bajtolina.getEquipment().armor!=null) {
            textOnTheRightValueOfPlayerStatistics += (int)Math.round(bajtolina.getEquipment().armor.valueOfAttackChange) + "/" + (int)Math.round(bajtolina.getEquipment().armor.valueOfDefenceChange) + "/" + (int)Math.round(bajtolina.getEquipment().armor.valueOfSpeedChange) + "/" + (int)Math.round(bajtolina.getEquipment().armor.valueOfMaxHealthPointsChange) + "";
        }else{
            textOnTheRightValueOfPlayerStatistics+="0/0/0/0";
        }
        textOnTheRightValueOfPlayerStatistics+="\n";
        textOnTheRightValueOfPlayerStatistics+="helmet:                        ";
        if(bajtolina.getEquipment().helmet!=null) {
            textOnTheRightValueOfPlayerStatistics += (int)Math.round(bajtolina.getEquipment().helmet.valueOfAttackChange) + "/" + (int)Math.round(bajtolina.getEquipment().helmet.valueOfDefenceChange) + "/" + (int)Math.round(bajtolina.getEquipment().helmet.valueOfSpeedChange) + "/" + (int)Math.round(bajtolina.getEquipment().helmet.valueOfMaxHealthPointsChange) + "";
        }else{
            textOnTheRightValueOfPlayerStatistics+="0/0/0/0";
        }
        textOnTheRightValueOfPlayerStatistics+="\n";
        textOnTheRightValueOfPlayerStatistics+="shoes:                         ";
        if(bajtolina.getEquipment().shoes!=null) {
            textOnTheRightValueOfPlayerStatistics += (int)Math.round(bajtolina.getEquipment().shoes.valueOfAttackChange) + "/" + (int)Math.round(bajtolina.getEquipment().shoes.valueOfDefenceChange) + "/" + (int)Math.round(bajtolina.getEquipment().shoes.valueOfSpeedChange) + "/" + (int)Math.round(bajtolina.getEquipment().shoes.valueOfMaxHealthPointsChange) + "";
        }else{
            textOnTheRightValueOfPlayerStatistics+="0/0/0/0";
        }
        textOnTheRightValueOfPlayerStatistics+="\n";
        textOnTheRightValueOfPlayerStatistics+="ranged weapon:          ";
        if(bajtolina.getEquipment().rangedWeapon!=null) {
            textOnTheRightValueOfPlayerStatistics += (int)Math.round(bajtolina.getEquipment().rangedWeapon.valueOfAttackChange) + "/" + (int)Math.round(bajtolina.getEquipment().rangedWeapon.valueOfDefenceChange) + "/" + (int)Math.round(bajtolina.getEquipment().rangedWeapon.valueOfSpeedChange) + "/" + (int)Math.round(bajtolina.getEquipment().rangedWeapon.valueOfMaxHealthPointsChange) + "";
        }else{
            textOnTheRightValueOfPlayerStatistics+="0/0/0/0";
        }
        textOnTheRightValueOfPlayerStatistics+="\n";

        textOnTheRightValueOfPlayerStatistics+="game time:                  ";
        textOnTheRightValueOfPlayerStatistics+=MainWithGraphic.gameTime+"";
        textOnTheRightValueOfPlayerStatistics+="\n";

        textOnTheRightValueOfPlayerStatistics+="leaf number:                ";
        textOnTheRightValueOfPlayerStatistics+=MainWithGraphic.bajtolina.getLeaf().getLevelOfLeaf()+"";
        textOnTheRightValueOfPlayerStatistics+="\n";


        textOnTheRightValueOfPlayerStatistics+="leaf number:                ";
        textOnTheRightValueOfPlayerStatistics+=MainWithGraphic.bajtolina.getEatenFlowers()+"/20";
        textOnTheRightValueOfPlayerStatistics+="\n";

        if(MainWithGraphic.gameState==1){
            textOnTheRightValueOfPlayerStatistics+="victory!";
        }
        if(MainWithGraphic.gameState==-1){
            textOnTheRightValueOfPlayerStatistics+="game over";
        }
        textOnTheRight.setText(textOnTheRightValueOfPlayerStatistics);
        Field tempField1,tempField2;
        int temp;
        int x=0,y=0;//współrzędne lewego górnego wyświetlanego polax
        temp=(bajtolina.getField().getX()+1)%2;
        x=bajtolina.getField().getX()-3;
        y=bajtolina.getField().getY()-3;

        healthPointsRects.clear();

        for(MyDrawPanel i:graphicBeings){
            if(i!=null){
                i.setIcon(imageIconList.get(0));
            }
        }
        graphicBeings.clear();

        for(MyDrawPanel i: graphicAtrifactsAndFlowers){
            if(i!=null){
                i.setIcon(imageIconList.get(0));
            }
        }
        graphicAtrifactsAndFlowers.clear();
        specialMarkToAttack.setIcon(imageIconList.get(0));

        for(int i=x;i<x+numberOfRowsAndColumnOnScreen;++i){
            for(int j=y;j<y+numberOfRowsAndColumnOnScreen;++j){
                if((i>=0)&&(j>=0)&&(i<leaf.getHeight())&&(j< leaf.getWidth())){
                    if(AttackPointer.getActive()){
                        if((AttackPointer.getY()==j)&&(AttackPointer.getX()==i)){
                            specialMarkToAttack=new MyDrawPanel((j-y-temp*((bajtolina.getField().getX()+1)%2))*150+((i-x+1)%2)*75,(i-x)*110,150,150,imageIconList.get(7));
                            layeredGameMapPane.add(specialMarkToAttack,0);
                        }
                    }
                    graphicLeaf[i-x][j-y].setIcon(imageIconList.get(leaf.getLeafsFields()[i][j].getPictureId()));
                    graphicLeaf[i-x][j-y].setXY((j-y-temp*((bajtolina.getField().getX()+1)%2))*150+((i-x+1)%2)*75,(i-x)*110,150,150);
                    if(leaf.getLeafsFields()[i][j].getArtifactOnThisField()!=null){
                        graphicAtrifactsAndFlowers.add(new MyDrawPanel((j-y-temp*((bajtolina.getField().getX()+1)%2))*150+((i-x+1)%2)*75,(i-x)*110,150,150,
                                imageIconList.get(leaf.getLeafsFields()[i][j].getArtifactOnThisField().getNumberOfPictureOnImageList())));
                        layeredGameMapPane.add(graphicAtrifactsAndFlowers.get(graphicAtrifactsAndFlowers.size()-1),1);

                    }
                    if(leaf.getLeafsFields()[i][j].getFlowerOfPowerOnThisField()!=null){
                        graphicAtrifactsAndFlowers.add(new MyDrawPanel((j-y-temp*((bajtolina.getField().getX()+1)%2))*150+((i-x+1)%2)*75,(i-x)*110,150,150,
                                imageIconList.get(leaf.getLeafsFields()[i][j].getFlowerOfPowerOnThisField().getNumberOfPictureOnImageList())));
                        layeredGameMapPane.add(graphicAtrifactsAndFlowers.get(graphicAtrifactsAndFlowers.size()-1),1);

                    }
                    if(leaf.getLeafsFields()[i][j].getBeingOnThisField()!=null){
                        graphicBeings.add(new MyDrawPanel((j-y-temp*((bajtolina.getField().getX()+1)%2))*150+((i-x+1)%2)*75,(i-x)*110,150,150,
                                iconBeingMap.get(leaf.getLeafsFields()[i][j].getBeingOnThisField().getType())));
                        layeredGameMapPane.add(graphicBeings.get(graphicBeings.size()-1),1);

                        if(leaf.getLeafsFields()[i][j].getBeingOnThisField().getHealthPoints()!=leaf.getLeafsFields()[i][j].getBeingOnThisField().getMaxHealthPoints()){
                            healthPointsRects.drawRects((j-y-temp*((bajtolina.getField().getX()+1)%2))*150+((i-x+1)%2)*75,(i-x)*110,
                                    leaf.getLeafsFields()[i][j].getBeingOnThisField().getHealthPoints()/leaf.getLeafsFields()[i][j].getBeingOnThisField().getMaxHealthPoints()*100);
                        }
                    }
                }
                else{
                    graphicLeaf[i-x][j-y].setIcon(imageIconList.get(0));
                }
            }
            temp++;
            temp%=2;
        }
    }
    private void loadPictures() {
        picturesList.add(new File("graphics/null.png"));                    //0 null
        imageIconList.add(new ImageIcon("graphics/null.png"));
        picturesList.add(new File("graphics/bajtolina.png"));               //1 bajtolina
        imageIconList.add(new ImageIcon("graphics/bajtolina.png"));
        iconBeingMap.put(BeingType.enumBeingType.bajtolina,imageIconList.get(imageIconList.size()-1));//mapowanie typu bytu i obrazka któy go reprezentuje
        picturesList.add(new File("graphics/emptyGreenFloor.png"));         //2 podłoga zielona
        imageIconList.add(new ImageIcon("graphics/emptyGreenFloor.png"));
        picturesList.add(new File("graphics/emptyGreenWall.png"));          //3 zielona ściana
        imageIconList.add(new ImageIcon("graphics/emptyGreenWall.png"));
        picturesList.add(new File("graphics/downArrowOnGreenFloor.png"));   //4 przejście w dół zielone
        imageIconList.add(new ImageIcon("graphics/downArrowOnGreenFloor.png"));
        picturesList.add(new File("graphics/upArrowOnGreenFloor.png"));     //5 przejście w górę zielone
        imageIconList.add(new ImageIcon("graphics/upArrowOnGreenFloor.png"));
        picturesList.add(new File("graphics/whiteHex.png"));                //6 biały hex
        imageIconList.add(new ImageIcon("graphics/whiteHex.png"));
        picturesList.add(new File("graphics/redAttackPointer.png"));        //7 czerwony wskaźnik ataku
        imageIconList.add(new ImageIcon("graphics/redAttackPointer.png"));
        picturesList.add(new File("graphics/BlackAnt.png"));                //8 czarna mrówka
        imageIconList.add(new ImageIcon("graphics/BlackAnt.png"));
        iconBeingMap.put(BeingType.enumBeingType.blackAnt,imageIconList.get(imageIconList.size()-1));//mapowanie typu bytu i obrazka któy go reprezentuje
        picturesList.add(new File("graphics/RedAnt.png"));                  //9 czerwona mrówka
        imageIconList.add(new ImageIcon("graphics/RedAnt.png"));
        iconBeingMap.put(BeingType.enumBeingType.redAnt,imageIconList.get(imageIconList.size()-1));//mapowanie typu bytu i obrazka któy go reprezentuje

        picturesList.add(new File("graphics/sword.png"));                  //10 miecz
        imageIconList.add(new ImageIcon("graphics/sword.png"));

        picturesList.add(new File("graphics/halberd.png"));                  //11 halabarda
        imageIconList.add(new ImageIcon("graphics/halberd.png"));

        picturesList.add(new File("graphics/spear.png"));                  //12 spear
        imageIconList.add(new ImageIcon("graphics/spear.png"));

        picturesList.add(new File("graphics/dagger.png"));                  //13 sztylet
        imageIconList.add(new ImageIcon("graphics/dagger.png"));

        picturesList.add(new File("graphics/brownSuit.png"));                  //14 brązowa zbroja
        imageIconList.add(new ImageIcon("graphics/brownSuit.png"));

        picturesList.add(new File("graphics/silverArmor.png"));                  //15 srebrna zbroja
        imageIconList.add(new ImageIcon("graphics/silverArmor.png"));

        //pozmieniać indeksy!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        picturesList.add(new File("graphics/silverShoes.png"));                  //16 srebrne buty
        imageIconList.add(new ImageIcon("graphics/silverShoes.png"));



        picturesList.add(new File("graphics/slingshot.png"));                  //17 proca
        imageIconList.add(new ImageIcon("graphics/slingshot.png"));

        picturesList.add(new File("graphics/silverHelmet.png"));                  //18 srebrny hełm
        imageIconList.add(new ImageIcon("graphics/silverHelmet.png"));

        picturesList.add(new File("graphics/flowerOfPower.png"));                  //19 kwiatek
        imageIconList.add(new ImageIcon("graphics/flowerOfPower.png"));

        picturesList.add(new File("graphics/flowerOfNoPower.png"));                  //20 kwiatek
        imageIconList.add(new ImageIcon("graphics/flowerOfNoPower.png"));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyFromKeyboardQueue.Q.add(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}