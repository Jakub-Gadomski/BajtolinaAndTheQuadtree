package com.example.unnamed_roguelike_game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyDrawPanel extends JLabel {

    public MyDrawPanel(int x,int y,int width,int height,ImageIcon imageIcon){
        super(imageIcon);
        this.setBounds(x,y,width,height);
    }
    public void setXY(int x, int y,int width,int height) {
        this.setBounds(x,y,width,height);
    }
    public void setPicture(ImageIcon imageIcon) {
        this.setIcon(imageIcon);
    }
}
