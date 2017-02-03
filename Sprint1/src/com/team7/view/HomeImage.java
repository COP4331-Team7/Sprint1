package com.team7.view;

import com.team7.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomeImage extends JPanel {

        private BufferedImage image;
        private Graphics2D g2d;

        public HomeImage()
        {
            try {


               image = ImageIO.read(new File(String.valueOf(Main.class.getClass().getResource("/resources/bg.jpg")).replace("file:","")));
            }
            catch (IOException e) {
            }

            Dimension size = new Dimension( image.getWidth(), image.getHeight());
            setPreferredSize( size );
        }

        public void paintComponent( Graphics g )
        {
            super.paintComponent( g );
            g.drawImage( image, 0, 0, this );
        }
}
