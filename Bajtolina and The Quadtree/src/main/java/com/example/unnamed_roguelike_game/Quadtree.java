package com.example.unnamed_roguelike_game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//ta klasa przechowuje drzewo i informacje o połączeniu między liśćmi
public class Quadtree {

    ///zmienić te wartości rozmiarów pól bo są takie tylko do testowania
    private static int minWidth=15;//minimum tutaj mogę ustawić 7
    private static int minHeight=15;//minimum tutaj mogę ustawić 7
    private static int maxWidth=25-minWidth;
    private static int maxHeight=25-minHeight;
    List<Leaf> leafList =new ArrayList<Leaf>();
    public  Quadtree(int numberOfLeafs){
        this.generate(numberOfLeafs);
    }
    public void addLef(int height,int width){
        leafList.add(new Leaf(height,width,leafList.size()+1));
    }
    public void addPassageBetweenLeafs(Leaf L1,Leaf L2){//funkcja łączy liście L1 i L2 zmieniając pewne pola podłogi na pola przejścia, zakładam, że L1 jest niższym poziomem niż L2
        Random random=new Random();
        int l1x,l1y,l2x,l2y;
        Field F1=null,F2=null;
        do{
            l1x=random.nextInt();
            if(l1x<0){
                l1x*=-1;
            }
            l1y=random.nextInt();
            if(l1y<0){
                l1y*=-1;
            }
            l1x%=L1.getWidth();
            l1y%=L1.getHeight();
            F1=L1.getLeafsFields()[l1y][l1x];
        }while(F1.equals(FieldType.enumFieldType.floor));//nie wiem, dlaczego, ale tutaj działa bez negacji, mimo iż powinno działać z negacją, więc zostawiam bez negacji, bo po co zmieniać cos co działa
        do{
            l2x=random.nextInt();
            if(l2x<0){
                l2x*=-1;
            }
            l2y=random.nextInt();
            if(l2y<0){
                l2y*=-1;
            }
            l2x%=L2.getWidth();
            l2y%=L2.getHeight();
            F2=L2.getLeafsFields()[l2y][l2x];
        }while (F2.equals(FieldType.enumFieldType.floor));//tutaj to samo
        F1.setType(FieldType.enumFieldType.passageToAnotherLeafDown);
        F2.setType(FieldType.enumFieldType.passageToAnotherLeafUp);
        F1.getNeighbors()[6]=F2;
        F2.getNeighbors()[6]=F1;
    }
    public void generate(int numberOfLeafs){//tworzy drzewo, na którym rozegra się rozgrywka
        int width,height;
        Leaf tempLeaf1=null;
        Leaf tempLeaf2=null;
        Random random=new Random();
        for(int i=0;i<numberOfLeafs;++i) {
            width=random.nextInt();
            height=random.nextInt();
            if(width<0){
                width=width*-1;
            }
            if(height<0){
                height=height*-1;
            }
            width=width%maxWidth+minWidth;
            height=height%maxHeight+minHeight;
            this.addLef(height,width);
        }
        Iterator<Leaf> iter=leafList.iterator();
        while(iter.hasNext()){
            tempLeaf2=tempLeaf1;
            tempLeaf1=iter.next();
            if((tempLeaf1!=null)&&(tempLeaf2!=null)){
                addPassageBetweenLeafs(tempLeaf1,tempLeaf2);
            }
        }
        MainWithGraphic.bajtolina=new Being(1, getLeafList().get(0).getLeafsFields()[0][0],getLeafList().get(0),20,15,10,1,1,0.5, BeingType.enumBeingType.bajtolina,MainWithGraphic.decisionsAndEventsQueue,0);

    }
    public List<Leaf> getLeafList() {
        return leafList;
    }
}
