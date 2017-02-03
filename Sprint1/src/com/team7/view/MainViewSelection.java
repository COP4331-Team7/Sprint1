package com.team7.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

class MainViewSelection extends JPanel implements MouseListener {

    public static BufferedImage image;
    private Graphics2D g2d;
    private final static int WIDTH = 200;
    private final static int HEIGHT = 200;
    //
    public int x_center, y_center;    // where the window in focused on

    public MainViewSelection()
    {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = (Graphics2D)image.createGraphics();
        Dimension size = new Dimension( image.getWidth(), image.getHeight());
        setPreferredSize( size );
        addMouseListener(this);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        x_center = 0;
        y_center = 0;
        drawMapArea();
    }


    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        g.drawImage( image, 0, 0, this );
    }

    public void drawMapArea() {
        g2d.setColor( new Color(255, 255, 255, 155) );
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        g2d.setColor( new Color( 100, 170, 140) );
        g2d.fillRect(x_center, y_center, 110, 70);
        g2d.setColor( new Color( 0, 0, 0, 255) );
        g2d.drawRect(x_center, y_center, 110, 70);
        repaint();
    }


    // TODO: Check this function
    public void setFocus(int x, int y) {
        x_center = (int)(x * WIDTH / 20);
        y_center = (int)(y * HEIGHT / 20);

        if(x_center < 0)              // adjust if out of bounds
            x_center = 0;
        else if (x_center > 90)
            x_center = 90;

        if(y_center < 0)
            y_center = 0;
        else if(y_center > 130)
            y_center = 130;

        drawMapArea();
    }


    // implement MouseListener interface methods:
    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {

//        double x_offset = ( e.getX() );
//        double y_offset = ( e.getY() );

    }
}