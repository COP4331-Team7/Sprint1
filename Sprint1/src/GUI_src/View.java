package GUI_src;

import com.team7.Main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

class View
{
    private static final int WIDTH  = 800;
    private static final int HEIGHT = 800;
    private MyFrame frame = null;

    public View() 
    {
        // SwingUtilities.invokeLater causes the Runnable to be executed asynchronously on the Event Dispatch Thread:
        // It queues up a task (GUI update) on the EDT and instantly returns.
        // Used to prevent long tasks from freezing up the GUI
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        } );
    }
    
    // Create the GUI and show it.
    private void createAndShowGUI()
    {
        frame = new MyFrame( WIDTH, HEIGHT);                       // setup new frame
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );    // exit when the user closes the frame
        frame.setVisible( true );                                  // make the frame visible
    }

    public MyFrame getFrame() {
        return frame;
    }
}

class MyFrame extends JFrame
{
    private HomeScreen homeScreen = null;
    private MainScreen mainScreen = null;
    private UnitScreen unitScreen = null;
    private StructureScreen structureScreen = null;

    public MyFrame( int width, int height) 
    {
        this.setTitle( "OOP GUI" ); 	     
        this.setSize( width, height );                 
        addMenu();  

        // GUI components
        homeScreen = new HomeScreen();
        mainScreen = new MainScreen();
        unitScreen = new UnitScreen();
        structureScreen = new StructureScreen();

        setCurrScreen("HOME");
    }

    public HomeScreen getHomeScreen() {
            return homeScreen;
    }
    public MainScreen getMainScreen() {
            return mainScreen;
    }
    public UnitScreen getUnitScreen() {
            return unitScreen;
    }
    public StructureScreen getStructureScreen() {
            return structureScreen;
    }

    public void setCurrScreen(String selected_screen) {

        getContentPane().removeAll();   // clear screen

        if(selected_screen == "HOME") {  
           displayHomeScreen();
        } 
        else if (selected_screen == "MAIN") {
            displayMainScreen();
        }
        else if (selected_screen == "UNIT_OVERVIEW") {
           displayUnitOverviewScreen();
        }
        else if (selected_screen == "STRUCTURE_OVERVIEW") {
            displayStructureOverviewScreen();
        }

        revalidate();
        repaint();
    }

    private void displayHomeScreen() {
            this.getContentPane().add( homeScreen );
    }
    private void displayMainScreen() {
            this.getContentPane().add( mainScreen );
            mainScreen.giveCommandFocus();
    }
    private void displayUnitOverviewScreen() {
            this.getContentPane().add( unitScreen );
    }
    private void displayStructureOverviewScreen() {
            this.getContentPane().add( structureScreen );
    }

    private void addMenu() 
    {                       	   // addMenu() method used to setup a frame's menu bar          
        JMenu fileMenu = new JMenu( "File" );    
        JMenuItem exitItem = new JMenuItem( "Exit" ); 
        exitItem.addActionListener( new ActionListener() 	// define what happens when this menu item is selected
        {
            public void actionPerformed( ActionEvent event )
            {
                System.exit( 0 );                    
            }
        } );
        fileMenu.add( exitItem ); 

        JMenuItem drawMapItem = new JMenuItem( "Draw Map" ); 
        drawMapItem.addActionListener( new ActionListener()    // define what happens when this menu item is selected
        {
            public void actionPerformed( ActionEvent event )
            {
                mainScreen.drawMap();                   
            }
        } );
        fileMenu.add( drawMapItem );    

        JMenuItem saveItem = new JMenuItem( "Save image" );     // create a new menu item
        saveItem.addActionListener( new ActionListener()
                                   {
            public void actionPerformed( ActionEvent event )
            {
                saveImage();
            }                                                                      // given valid input, it will display an image
        } );
        fileMenu.add( saveItem );


        JMenuBar menuBar = new JMenuBar();                  // create a new menu bar
        menuBar.add( fileMenu );                           	// add the "File" menu to the menu bar
        this.setJMenuBar( menuBar );                        // attach the menu bar to this frame
    }


    private void saveImage()    // prompt the user to specify the size of the n by n image
    {
        
        BufferedImage temp_img = mainScreen.getMainViewImage();

        String inputString = JOptionPane.showInputDialog("ouput file?");
        try
        {
            File outputFile = new File( inputString );
            ImageIO.write( temp_img, "png", outputFile );
        }
        catch ( IOException e )
        {
            JOptionPane.showMessageDialog( this,
                                          "Error saving file",
                                          "oops!",
                                          JOptionPane.ERROR_MESSAGE );
        }
    }

}	

class HomeScreen extends JPanel {

    private HomeImage homeBackground = null;
    private HomeButtons homeButtons = null;

    public HomeScreen() {

        this.setLayout(new BorderLayout());

        homeBackground = new HomeImage();                
        homeButtons = new HomeButtons();

        this.add(homeBackground, BorderLayout.CENTER);
        this.add(homeButtons, BorderLayout.SOUTH);  
    }

    public HomeButtons getHomeButtons() {
            return homeButtons;
    }

}

class MainScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private MainViewImage mainAreaView = null;
    private MainViewInfo mainStatusInfo = null;
    private Command commandSelecter = null;

    public MainScreen() {

        this.setLayout(new BorderLayout());

        screenSelectBtns = new ScreenSelectButtons();
        mainAreaView = new MainViewImage();
        mainStatusInfo = new MainViewInfo();
        commandSelecter = new Command();

        this.add( screenSelectBtns, BorderLayout.NORTH );
        this.add( mainAreaView, BorderLayout.CENTER );

        JPanel temp = new JPanel(); // create JPanel to hold two other JPanels
                                    // only one JPanel can be in a specific location, so we add 2 panels to a single panel
                                    // then add the single panel where we want both panels

        temp.add( mainStatusInfo, BorderLayout.WEST );
        temp.add( commandSelecter, BorderLayout.SOUTH);

        this.add( temp, BorderLayout.SOUTH );
    }

    public void giveCommandFocus() {          // give JPanel (commandSelecter) focus to 'make its KeyListener is active'
        commandSelecter.setFocusable(true);
        commandSelecter.requestFocus();
    }

    public ScreenSelectButtons getScreenSelectButtons() {
            return screenSelectBtns;
    }

    public void drawMap() {
        mainAreaView.drawMap();
    }

    public BufferedImage getMainViewImage() {
        return mainAreaView.getCurrImage();
    }
}






class UnitScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private JScrollPane jScrollPane = null;

    public UnitScreen() {

        this.setLayout(new BorderLayout());

        screenSelectBtns = new ScreenSelectButtons();
        addScrollPane( this );

        this.add( screenSelectBtns, BorderLayout.NORTH );
    }

    public ScreenSelectButtons getScreenSelectButtons() {
            return screenSelectBtns;
    }

    private void addScrollPane(Container pane) {
            JPanel temp = new JPanel();
            temp.setLayout( new BorderLayout() );
            jScrollPane = new JScrollPane(temp);
            temp.setPreferredSize( new Dimension(800, 800*2) );
            pane.add( jScrollPane  );
    }
}

class StructureScreen extends JPanel {

    private ScreenSelectButtons screenSelectBtns = null;
    private JScrollPane jScrollPane = null;

    public StructureScreen() {

        this.setLayout(new BorderLayout());

        screenSelectBtns = new ScreenSelectButtons();
        addScrollPane( this );

        this.add( screenSelectBtns, BorderLayout.NORTH );
    }

    public ScreenSelectButtons getScreenSelectButtons() {
            return screenSelectBtns;
    }

    private void addScrollPane(Container pane) {
            JPanel temp = new JPanel();
            temp.setLayout( new BorderLayout() );
            jScrollPane = new JScrollPane(temp);
            temp.setPreferredSize( new Dimension(800, 800*2) );
            pane.add( jScrollPane  );
    }
}

class HomeButtons extends JPanel {

        private JButton playButton = null;
        private JButton quitButton = null;

        public HomeButtons() {

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0,1));

            playButton = new JButton("START GAME");
            quitButton = new JButton("QUIT");

            panel.add( playButton );
            panel.add( quitButton );

            this.add( panel, BorderLayout.CENTER );
       }

       public JButton getQuitButton() {
            return quitButton;
       }
       public JButton getPlayButton() {
            return playButton;
       }
}

class HomeImage extends JPanel {

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

class ScreenSelectButtons extends JPanel {

        private JButton mainScreenSelectButton;
        private JButton unitScreenSelectButton;
        private JButton structureScreenSelectButton;

        public ScreenSelectButtons() {
            mainScreenSelectButton      = new JButton("MAIN SCREEN");
            unitScreenSelectButton      = new JButton("UNIT OVERVIEW");
            structureScreenSelectButton = new JButton("STRUCTURE OVERVIEW");

            this.add( mainScreenSelectButton );
            this.add( unitScreenSelectButton );
            this.add( structureScreenSelectButton );
       }

       public JButton getMainScreenButton() {
            return mainScreenSelectButton;
       }
       public JButton getUnitScreenButton() {
            return unitScreenSelectButton;
       }
       public JButton getStructureScreenButton() {
            return structureScreenSelectButton;
       }
}

class MainViewImage extends JPanel implements MouseListener {

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


        public MainViewImage()
        {
            MAP_IMAGE_WIDTH_IN_PIXELS = 733;
            MAP_IMAGE_HEIGHT_IN_PIXELS = 469;

            image = new BufferedImage(MAP_IMAGE_WIDTH_IN_PIXELS, MAP_IMAGE_HEIGHT_IN_PIXELS, BufferedImage.TYPE_INT_ARGB);
            g2d = (Graphics2D)image.createGraphics();  
            addMouseListener(this);

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

            

class MainViewInfo extends JPanel {
        public MainViewInfo()
        {
            JPanel t = new JPanel();
            t.setLayout(new GridLayout(0,1));
            JLabel p = new JLabel("Statistics:");
            p.setFont(new Font("Serif", Font.BOLD, 18));

            t.add(p);   // large first label so the panel takes up more space and is spaced out
            JLabel tt7 = new JLabel("Unit/Structure:                                    ");   
            t.add(tt7);
            JLabel tt = new JLabel("Offensive damage:");
            t.add(tt);
            JLabel tt1 = new JLabel("Defensive damage:");
            t.add(tt1);
            JLabel tt2 = new JLabel("Armor:");
            t.add(tt2);
            JLabel tt3 = new JLabel("Health:");
            t.add(tt3);
            JLabel tt4 = new JLabel("Upkeep:");
            t.add(tt4);
            JLabel tt5 = new JLabel("Production rates:");
            t.add(tt5);
            JLabel tt6 = new JLabel("Items:");
            t.add(tt6);   
            this.add( t, BorderLayout.SOUTH );
        }
} 


class Command extends JPanel implements KeyListener {

    JLabel modeLabel;
    JLabel typeLabel;
    JLabel typeInstanceLabel;
    JLabel commandLabel;

    private final static String[] armyCommands = {          "attack [direction] applies to the battle-group", 
                                                            "defend [direction] applies to the battle-group", 
                                                            "move [changes the rally-point]",
                                                            "wait  [applies to the battle-group]",
                                                            "disband  [the army ceases to exist, all units go to standby]",
                                                            "decommission [destroy all units in the army]", 
                                                            "power down [applies to the battle-group]", 
                                                            "power up [applies to the battle-group]", 
                                                            "cancel queued orders [immediate effect]"};

    private final static String[] structureCommands = {"attack [direction]", 
                                                            "make[unit type]", 
                                                            "defend [direction]", 
                                                            "heal/repair unit", 
                                                            "decommission", 
                                                            "power down", 
                                                            "power up", 
                                                            "cancel queued orders [immediate effect]"};

    private final static String[] unitCommands = {"reinforce [army#]",
                                                    "decommission [destroy—immediate, power up not required]",
                                                     "power down", 
                                                     "power up" };


    private final static String[] types = { "structure", "unit", "army" };

    private final static String[] structureTypes = { "Base" };

    private final static String[] unitTypes = { "Explorer", "Colonist", "Ranged Unit", "Melee Unit" };


    private final static String[] modes = {  "RALLY POINT", 
                                               "STRUCTURE", 
                                                    "UNIT", 
                                                    "ARMY"  };                                                


    private final static String[] armySubTypes = {"ENTIRE ARMY", "BATTLE GROUP", "REINFORCEMENTS"};

    private int currMode = -1;
    private int currType = -1;
    private int currTypeInstance = -1;
    private int currCommand = -1;

    private static final int CONTROL_KEY_CODE = 4;
    private static final int LEFT_KEY_CODE = 37; 
    private static final int UP_KEY_CODE = 38;
    private static final int RIGHT_KEY_CODE = 39;
    private static final int DOWN_KEY_CODE = 40;

    public Command() {
       
        JPanel commandSelectPanel = new JPanel();
        commandSelectPanel.setLayout(new GridLayout(0,1));

        modeLabel = new JLabel("MODE (CONTROL + \u2191 / \u2193): "); //up / down arrow
        typeLabel = new JLabel("TYPE (CONTROL + \u2190 / \u2192): "); //left / right arrow
        typeInstanceLabel = new JLabel("TYPE INSTANCE (\u2190 / \u2192): "); //left / right arrow
        commandLabel = new JLabel("COMMAND (\u2191 / \u2193): "); //up / down arrow

        JLabel my_static_label = new JLabel("CONSTRUCT COMMAND BELOW");
        my_static_label.setFont(new Font("Serif", Font.BOLD, 22));

        commandSelectPanel.add(my_static_label);

        commandSelectPanel.add(modeLabel);
        commandSelectPanel.add(typeLabel);
        commandSelectPanel.add(typeInstanceLabel);
        commandSelectPanel.add(commandLabel);
        commandSelectPanel.add(new JLabel());
        commandSelectPanel.add(new JLabel());
        this.add( commandSelectPanel, BorderLayout.SOUTH );

        addKeyListener(this);
    }


    // update the text displaying the currently selected command
    private void updateCommand() {

        modeLabel.setText("MODE (CONTROL + \u2191 / \u2193): " + ((currMode != -1)?modes[currMode]:"") ); //up / down arrow

        // type based off of mode
        if(currMode == 1)
            typeLabel.setText("TYPE (CONTROL + \u2190 / \u2192): " + ((currType != -1)?structureTypes[currType]:"")); //left / right arrow
        else if (currMode == 2)
            typeLabel.setText("TYPE (CONTROL + \u2190 / \u2192): " + ((currType != -1)?unitTypes[currType]:"")); //left / right arrow
        else if (currMode == 3)
            typeLabel.setText("TYPE (CONTROL + \u2190 / \u2192): " + ((currType != -1)?armySubTypes[currType]:"")); //left / right arrow
        else 
            typeLabel.setText("TYPE (CONTROL + \u2190 / \u2192): " ); //left / right arrow

        if(currType == 0)
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?structureCommands[currCommand]:"")); //up / down arrow
        else if (currType == 1)
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?unitCommands[currCommand]:"")); //up / down arrow
        else if (currType == 2)
            commandLabel.setText("COMMAND (\u2191 / \u2193): " + ((currCommand != -1)?armyCommands[currCommand]:"")); //up / down arrow

    }


    public void keyTyped(KeyEvent e)    {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e)  {

        if(e.getKeyCode() == UP_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {
           
            currMode = ++currMode % modes.length; 

            currType = -1;      // when the user changes MODE, reset the currently selected TYPE 
        }
        else if(e.getKeyCode() == DOWN_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {
            
            if (currMode > 0) currMode--;        
            else currMode = modes.length - 1;

            currType = -1;      // when the user changes MODE, reset the currently selected TYPE
        }
        else if(e.getKeyCode() == LEFT_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {
           
            if (currType > 0) currType--;
            else currType = getNumTypes( currMode ) - 1;

            currCommand = -1;   // when the user changes TYPE, reset the currently selected COMMAND
        }
        else if(e.getKeyCode() == RIGHT_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {
           
            if( getNumTypes( currMode ) != 0) 
                currType = ++currType % getNumTypes( currMode ); 

            currCommand = -1;   // when the user changes TYPE, reset the currently selected COMMAND
        }
        else if(e.getKeyCode() == UP_KEY_CODE) {
            
            if( getNumCommands( currType ) != 0) 
                currCommand = ++currCommand % getNumCommands( currType ); 

        }
        else if(e.getKeyCode() == DOWN_KEY_CODE) {
           
            if (currCommand > 0) currCommand--;
            else currCommand = getNumCommands( currType ) - 1; 

        }

        updateCommand();
    }


    private int getNumTypes(int currMode) {        // get # of options the current MODE has  
            int size = 0;
            if(currMode == 1) 
                size = structureTypes.length;
            else if (currMode == 2)
                size = unitTypes.length;
            else if (currMode == 3)
                size = armySubTypes.length;
            return size;
    }

    private int getNumCommands(int currType) {      // get # of options the current TYPE has 
            int size = 0;
            if(currType == 0) 
                size = structureCommands.length;
            else if (currType == 1)
                size = unitCommands.length;
            else if (currType == 2)
                size = armyCommands.length;
            return size;
    }



}