package com.team7.View;

import com.team7.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainViewImage extends JPanel implements MouseListener {

        public static BufferedImage image;
        private Graphics2D g2d;
        private int MAP_IMAGE_WIDTH_IN_PIXELS;
        private int MAP_IMAGE_HEIGHT_IN_PIXELS;
        //
        public int x_center, y_center;    // where the window in focused on
        public int x_dest, y_dest;    // where the window in focused on
        //
        private BufferedImage tileImage_1;
        private BufferedImage tileImage_2;
        private BufferedImage tileImage_3;
        private BufferedImage tileImage_4;
        //
        private BufferedImage mapImage;
        public int[][] imageTerrains;
        private MainViewSelection mainViewSelection;

        public MainViewImage( MainViewSelection ms )
        {
            MAP_IMAGE_WIDTH_IN_PIXELS = 733;
            MAP_IMAGE_HEIGHT_IN_PIXELS = 469;

            image = new BufferedImage(MAP_IMAGE_WIDTH_IN_PIXELS, MAP_IMAGE_HEIGHT_IN_PIXELS, BufferedImage.TYPE_INT_ARGB);
            g2d = (Graphics2D)image.createGraphics();
            addMouseListener(this);

            this.mainViewSelection = ms;

            // load tile images
            try {
               tileImage_1 = ImageIO.read(new File(String.valueOf(Main.class.getClass().getResource("/resources/mountain_img.png")).replace("file:","")));
               tileImage_2 = ImageIO.read(new File(String.valueOf(Main.class.getClass().getResource("/resources/hills_img.png")).replace("file:","")));
               tileImage_3 = ImageIO.read(new File(String.valueOf(Main.class.getClass().getResource("/resources/sand_img.jpg")).replace("file:","")));
               tileImage_4 = ImageIO.read(new File(String.valueOf(Main.class.getClass().getResource("/resources/grass_img.jpg")).replace("file:","")));


               //  ========================
                mapImage = new BufferedImage(1340 , 1340, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = (Graphics2D)mapImage.createGraphics();
                int tileSize = 67;

            // tileImage1 = Mountain
            // tileImage2 = Hill
            // tileImage3 = Desert
            // tileImage4 = Flatland

                imageTerrains = new int[20][20];

                for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 20; j++){

                    int x = tileSize * i;
                    int y = tileSize * j;

                    if (i >= 0 && i <= 4){
                        if (j >= 0 && j <= 4){
                          imageTerrains[i][j] =  1;
                          imageTerrains[19-i][19-j] =  1;
                        } else if (j > 4 && j <= 9){
                            imageTerrains[i][j] =  2;
                          imageTerrains[19-i][19-j] =  2;
                        } else if (j > 9 && j <= 14){
                           imageTerrains[i][j] =  3;
                          imageTerrains[19-i][19-j] =  3;
                        } else if (j > 14 && j <= 19){
                            imageTerrains[i][j] =  4;
                          imageTerrains[19-i][19-j] =  4;
                        }
                    } else{
                        if (j >= 0 && j <= 4){
                            imageTerrains[i][j] =  2;
                          imageTerrains[19-i][19-j] =  2;
                        } else if (j > 4 && j <= 9){
                          imageTerrains[i][j] =  4;
                          imageTerrains[19-i][19-j] =  4;
                        } else if (j > 9 && j <= 14){
                           imageTerrains[i][j] =  3;
                          imageTerrains[19-i][19-j] =  3;
                        } else if (j > 14 && j <= 19){
                          imageTerrains[i][j] =  4;
                          imageTerrains[19-i][19-j] =  4;
                        }
                    }
                }
                }
                }
            catch (IOException e) {}
            //  ========================

            drawMapArea();


            x_center = 0;
            y_center = 0;
            x_dest = 0;
            y_dest = 0;
            drawMap();
        }


        private BufferedImage drawSubsectionOfMap(int x, int y) {


            BufferedImage tempImg = new BufferedImage(733, 439, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2ds = (Graphics2D)tempImg.createGraphics();

            for(int i = 0; i < 11; i++) {                               // tile index on sub-screen
                for(int j = 0; j < 7; j++) {

                    int xx = x + i;                                             // tile index on whole map
                    int yy = y + j;

                    if(xx < 0)              // adjust if out of bounds
                        xx = 0;
                    else if (xx > 19)
                        xx = 19;

                    if(yy < 0)
                        yy = 0;
                    else if(yy > 19)
                        yy = 19;

                    if(imageTerrains[xx][yy] == 1) {
                        g2ds.drawImage(tileImage_1, i*67, j*67, null);
                    }
                    else if (imageTerrains[xx][yy] == 2) {
                        g2ds.drawImage(tileImage_2, i*67, j*67, null);
                    }
                    else if (imageTerrains[xx][yy] == 3) {
                        g2ds.drawImage(tileImage_3, i*67, j*67, null);
                    }
                    else if (imageTerrains[xx][yy] == 4) {
                        g2ds.drawImage(tileImage_4, i*67, j*67, null);
                    }

                }
            }

             g2ds.setColor(Color.ORANGE);
             g2ds.drawLine((11/2)*67 + 67/2 - 10, (7/2)*67 + 67/2, (11/2)*67 + 67/2 + 10, (7/2)*67 + 67/2 );
             g2ds.drawLine((11/2)*67 + 67/2 , (7/2)*67 + 67/2 - 10, (11/2)*67 + 67/2, (7/2)*67 + 67/2 + 10 );

            return tempImg;
        }

        private BufferedImage drawOffsetSubsectionOfMap(int x, int y, boolean[] types) {


            BufferedImage tempImg = new BufferedImage(733, 439, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2ds = (Graphics2D)tempImg.createGraphics();

            for(int i = 0; i < 11; i++) {                               // tile index on sub-screen
                for(int j = 0; j < 7; j++) {

                    int xx = x + i;                                             // tile index on whole map
                    int yy = y + j;

                    if(xx < 0)              // adjust if out of bounds
                        xx = 0;
                    else if (xx > 19)
                        xx = 19;

                    if(yy < 0)
                        yy = 0;
                    else if(yy > 19)
                        yy = 19;

                    int x_offsett = 0;
                    int y_offsett = 0;
                    if(types[0] == true) {
                        x_offsett = -67/2;
                    }
                    if(types[2] == true) {
                        x_offsett = 67/2;
                    }
                    else if(types[1] == true) {
                        y_offsett = -67/2;
                    }
                    else if(types[3] == true) {
                        y_offsett = 67/2;
                    }

                    if(imageTerrains[xx][yy] == 1) {
                        g2ds.drawImage(tileImage_1, i*67 + x_offsett, j*67 + y_offsett, null);
                    }
                    else if (imageTerrains[xx][yy] == 2) {
                        g2ds.drawImage(tileImage_2, i*67 + x_offsett, j*67 + y_offsett, null);
                    }
                    else if (imageTerrains[xx][yy] == 3) {
                        g2ds.drawImage(tileImage_3, i*67 + x_offsett, j*67 + y_offsett, null);
                    }
                    else if (imageTerrains[xx][yy] == 4) {
                        g2ds.drawImage(tileImage_4, i*67 + x_offsett, j*67 + y_offsett, null);
                    }

                }
            }

             g2ds.setColor(Color.ORANGE);
             g2ds.drawLine((11/2)*67 + 67/2 - 10, (7/2)*67 + 67/2, (11/2)*67 + 67/2 + 10, (7/2)*67 + 67/2 );
             g2ds.drawLine((11/2)*67 + 67/2 , (7/2)*67 + 67/2 - 10, (11/2)*67 + 67/2, (7/2)*67 + 67/2 + 10 );

            return tempImg;
        }


        public void paintComponent( Graphics g )
        {
            super.paintComponent( g );
            g.drawImage( image, 33, 0, this );
        }

    public void drawMapArea() {
        g2d.setColor( new Color(210, 210, 230) );
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        repaint();
    }

    public void drawMap() {
        image = drawSubsectionOfMap(11/2 - 11/2, 7/2 - 7/2);
        repaint();
    }

    private int[] getFocus() {
        int[] center = {x_center, y_center};
        return center;
    }


        public BufferedImage getCurrImage() {
            return image;
        }

        // implement MouseListener interface methods:
        public void mousePressed(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {}

        public void mouseEntered(MouseEvent e) {}

        public void mouseExited(MouseEvent e) {}

        public void mouseClicked(MouseEvent e) {

           double x_offset = (e.getX() - 733/2 - 33)/(double)67;   // offset in number of tiles
           double y_offset = -1*(469/2 - e.getY())/(double)67;

           if(x_offset % 1 > 0.5) {
            x_offset += 1;
           }
           else if(x_offset % 1 < -0.5) {
            x_offset += -1;
           }

           if(y_offset % 1 > 0.5) {
            y_offset += 1;
           }
           else if(y_offset % 1 < -0.5) {
            y_offset += -1;
           }

           // System.out.println("focus at (" + (int)x_center + ", " + (int)y_center + ")" );
           // System.out.println("go towards (" + (int)x_offset + ", " + (int)y_offset + ")" );

           x_dest = x_center + (int)x_offset;
           y_dest = y_center + (int)y_offset;

            if(x_dest < 0)              // adjust if out of bounds
                x_dest = 0;
            else if (x_dest > 19)
                x_dest = 19;

            if(y_dest < 0)
                y_dest = 0;
            else if(y_dest > 19)
                y_dest = 19;

            mainViewSelection.setFocus((int)(x_dest/(double)19*90), (int)(y_dest/(double)19*130));
            if( x_center != x_dest || y_center != y_dest) {

                    new Thread( new Runnable()
                    {
                        public void run()
                        {
                            int x_diff = (x_dest - x_center);
                            int y_diff = (y_dest - y_center);

                            int delta_x = 0, delta_y = 0;

                            if(x_diff != 0) {
                                delta_x = ((x_dest - x_center) > 0) ? 1 : -1;
                            }
                            if(y_diff != 0) {
                                delta_y = ((y_dest - y_center) > 0) ? 1 : -1;
                            }

                            while (x_diff != 0 || y_diff != 0) {    // while view isnt focused on destination tile


                                boolean[] types = new boolean[4];

                                if(x_diff > 0) {
                                    types[0] = true;
                                }
                                if(y_diff > 0) {
                                    types[1] = true;
                                }

                                if(x_diff < 0) {
                                    types[2] = true;
                                }
                                if(y_diff < 0) {
                                        types[3] = true;
                                }

                                final BufferedImage mapSubsection1 = drawOffsetSubsectionOfMap(x_center, y_center, types);
                                SwingUtilities.invokeLater( new Runnable()   // queue frame i on EDT for display
                                {
                                    public void run()
                                    {
                                        image = mapSubsection1;
                                        repaint();
                                    }
                                });

                                if(x_diff != 0) {                   // move focus 1 unit towards destination
                                    x_center += delta_x;
                                    x_diff -= delta_x;
                                }
                                if(y_diff != 0) {
                                    y_center += delta_y;
                                    y_diff -= delta_y;
                                }

                                try{
                                    Thread.sleep(75);
                                }
                                catch(Exception e) {}

                                final BufferedImage mapSubsection = drawSubsectionOfMap(x_center, y_center);
                                SwingUtilities.invokeLater( new Runnable()   // queue frame i on EDT for display
                                {
                                    public void run()
                                    {
                                        image = mapSubsection;
                                        repaint();
                                    }
                                });

                                try{
                                    Thread.sleep(75);
                                }
                                catch(Exception e) {}

                            }   // end of while loop

                        }
                    } ).start();

            }
        }
}
