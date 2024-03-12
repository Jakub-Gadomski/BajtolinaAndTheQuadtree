package com.example.unnamed_roguelike_game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class HealthPointsRectPanel extends JPanel {
    private List<Rectangle2D> healthPointsRectList;
    private static final int rectHeight = 15;
    private static final int rectWidth = 140;

    public HealthPointsRectPanel(){
        healthPointsRectList=new ArrayList<Rectangle2D>();
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D d2 = (Graphics2D) g;
        boolean mod2=false;
        for(Rectangle2D i:healthPointsRectList){
            if(mod2){
                d2.setColor(Color.BLACK);
            }
            else{
                d2.setColor(Color.RED);
            }
            mod2=!mod2;
            d2.fillRect(i.getBounds().x, i.getBounds().y, i.getBounds().width,i.getBounds().height);
        }
    }
    public void drawRects(int x, int y,double actualHealthPointsInPercent) {
        healthPointsRectList.add(new Rectangle2D.Double(x,y,rectWidth*actualHealthPointsInPercent/100,rectHeight));
        healthPointsRectList.add(new Rectangle2D.Double(x+rectWidth*actualHealthPointsInPercent/100,y,rectWidth-rectWidth*actualHealthPointsInPercent/100,rectHeight));
        paintComponent(getGraphics());

    }
    public void clear() {
        healthPointsRectList.clear();
        paintComponent(getGraphics());
    }

}
