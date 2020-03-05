/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JavaApplicationE4.logica;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author jbermudezb
 */
public class BoxNumber {

    private Font font = new Font("Tahoma", Font.BOLD, 16);
    private String number = "0";
    public int WIDTH = 60;
    public int HEIGHT = 32;
    public int x;
    public int y;
    private Color colorBg;
    private Dimension dimensionText;
    private Color[] color = {new Color(128, 128, 128), // gray
        new Color(192, 192, 192), //lightGray
        new Color(64, 64, 64), //darkGray
        new Color(255, 200, 0), //orange
        new Color(255, 255, 0), // yellow
        new Color(255, 0, 255), //magenta
        new Color(0, 0, 255), //blue
        new Color(255, 175, 175) //pink
        
    };

    /**
     * Constructor de clase
     */
    public BoxNumber() {
        Random rn = new Random();
        colorBg = color[rn.nextInt(color.length)];
    }

    public void draw(Graphics2D g2) {

        Polygon boxPolygon = new Polygon();
        boxPolygon.addPoint(x, y);
        boxPolygon.addPoint(x + WIDTH, y);
        boxPolygon.addPoint(x + WIDTH, y + HEIGHT);
        boxPolygon.addPoint(x, y + HEIGHT);

        g2.setColor(colorBg);
        g2.fillPolygon(boxPolygon);
        g2.setColor(new Color(0, 0, 0));
        g2.drawPolygon(boxPolygon);

        if (getNumber().length() > 0) {
            g2.setFont(font);
            g2.drawString(getNumber(), x + (WIDTH / 2 - dimensionText.width / 2), y + HEIGHT / 2 + dimensionText.height / 2);
        }

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
        dimensionText = calculateFontDimension(number);
    }

    public int getValue() {
        return Integer.valueOf(number);
    }

    /**
     * Obtiene las dimensiones de un texto
     *
     * @param String texto
     * @return Dimension
     */
    private Dimension calculateFontDimension(String text) {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setStroke(new BasicStroke(0.0f));
        FontRenderContext frc = g2.getFontRenderContext();
        TextLayout layout = new TextLayout(text, font, frc);
        layout.draw(g2, 0, 0);
        Rectangle2D bounds = layout.getBounds();
        bounds.setRect(bounds.getX(),
                bounds.getY(),
                bounds.getWidth(),
                bounds.getHeight());
        int w = (int) bounds.getWidth();
        int h = (int) bounds.getHeight();
        g2.draw(bounds);
        g2.dispose();
        return new Dimension(w, h);
    }
}
