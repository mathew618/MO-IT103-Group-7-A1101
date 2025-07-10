/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.group7compprog;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author lol21
 */

public class GradientPanel extends JPanel {

    private Color color1 = Color.CYAN;
    private Color color2 = Color.BLUE;

    public GradientPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();
        
        GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
        
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        if (color1 != null) {
            this.color1 = color1;
            repaint();
        }
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        if (color2 != null) {
            this.color2 = color2;
            repaint();
        }
    }
}
