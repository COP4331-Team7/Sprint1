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
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        } );
    }
    
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
        JMenuBar menuBar = new JMenuBar();                  // create a new menu bar
        menuBar.add( fileMenu );                           	// add the "File" menu to the menu bar
        this.setJMenuBar( menuBar );                        // attach the menu bar to this frame
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
               image = ImageIO.read(new File("bg.jpg"));
            } 
            catch (IOException e) {}
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

class MainViewImage extends JPanel {

        private BufferedImage image;
        private Graphics2D g2d;
        private int MAP_IMAGE_WIDTH_IN_PIXELS;
        private int MAP_IMAGE_HEIGHT_IN_PIXELS;

        public MainViewImage()
        {
            MAP_IMAGE_WIDTH_IN_PIXELS = 700;
            MAP_IMAGE_HEIGHT_IN_PIXELS = 500;

            image = new BufferedImage(MAP_IMAGE_WIDTH_IN_PIXELS, MAP_IMAGE_HEIGHT_IN_PIXELS, BufferedImage.TYPE_INT_ARGB);
            g2d = (Graphics2D)image.createGraphics();
            drawMapArea();  
        }

        public void paintComponent( Graphics g )
        {
            super.paintComponent( g );
            g.drawImage( image, 50, 0, this );
        }

        public void drawMapArea() {
            g2d.setColor( new Color(210, 210, 230) );
            g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        }
}
            

class MainViewInfo extends JPanel {
        public MainViewInfo()
        {
            JPanel t = new JPanel();
            t.setLayout(new GridLayout(0,1));
            JLabel p = new JLabel("Current selection stats");
              p.setFont(new Font("Serif", Font.BOLD, 18));

             t.add(p);
            JLabel tt7 = new JLabel("Unit/Structure:");
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
       
        JPanel t = new JPanel();
        t.setLayout(new GridLayout(0,1));

        modeLabel = new JLabel("MODE (CONTROL + ↑/↓): ");
        typeLabel = new JLabel("TYPE (CONTROL + ←/→): ");
        typeInstanceLabel = new JLabel("TYPE INSTANCE (←/→): ");
        commandLabel = new JLabel("COMMAND (↑/↓): ");

        JLabel p = new JLabel("CONSTRUCT COMMAND BELOW");
        p.setFont(new Font("Serif", Font.BOLD, 22));

        t.add(p);

        t.add(modeLabel);
        t.add(typeLabel);
        t.add(typeInstanceLabel);
        t.add(commandLabel);
        t.add(new JLabel());
        t.add(new JLabel());
        this.add( t, BorderLayout.SOUTH );

        addKeyListener(this);
    }

    private void updateCommand() {


        modeLabel.setText("MODE (CONTROL + ↑/↓): " + ((currMode != -1)?modes[currMode]:"") );

        // type based off of mode
        if(currMode == 1)
            typeLabel.setText("TYPE (CONTROL + ←/→): " + ((currType != -1)?structureTypes[currType]:""));
        else if (currMode == 2)
            typeLabel.setText("TYPE (CONTROL + ←/→): " + ((currType != -1)?unitTypes[currType]:""));
        else if (currMode == 3)
            typeLabel.setText("TYPE (CONTROL + ←/→): " + ((currType != -1)?armySubTypes[currType]:""));
        else 
            typeLabel.setText("TYPE (CONTROL + ←/→): " );

        if(currType == 0)
            commandLabel.setText("COMMAND (↑/↓): " + ((currCommand != -1)?structureCommands[currCommand]:""));
        else if (currType == 1)
            commandLabel.setText("COMMAND (↑/↓): " + ((currCommand != -1)?unitCommands[currCommand]:""));
        else if (currType == 2)
            commandLabel.setText("COMMAND (↑/↓): " + ((currCommand != -1)?armyCommands[currCommand]:""));

    }


    public void keyTyped(KeyEvent e)    {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e)  {

        if(e.getKeyCode() == UP_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {
            currMode = ++currMode % modes.length; 

            currType = -1;
            updateCommand();
        }
        else if(e.getKeyCode() == DOWN_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {
            

            if (currMode > 0) currMode--;
            else currMode = modes.length - 1;

            currType = -1;
            updateCommand();
        }
        else if(e.getKeyCode() == LEFT_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {
            
            int size = 0;

            if(currMode == 1) 
                size = structureTypes.length;
            else if (currMode == 2)
                size = unitTypes.length;
            else if (currMode == 3)
                size = armySubTypes.length;

            if (currType > 0) currType--;
            else currType = size - 1;

            currCommand = -1;
            updateCommand();
        }
        else if(e.getKeyCode() == RIGHT_KEY_CODE && e.getModifiers() == CONTROL_KEY_CODE ) {

             int size = 0;

            if(currMode == 1) 
                size = structureTypes.length;
            else if (currMode == 2)
                size = unitTypes.length;
            else if (currMode == 3)
                size = armySubTypes.length;
            
            if(size != 0) 
                currType = ++currType % size; 

            currCommand = -1;
            updateCommand();
        }
        else if(e.getKeyCode() == UP_KEY_CODE) {

             int size = 0;

            if(currType == 0) 
                size = structureCommands.length;
            else if (currType == 1)
                size = unitCommands.length;
            else if (currType == 2)
                size = armyCommands.length;
            
            if(size != 0) 
                currCommand = ++currCommand % size; 

            updateCommand();
        }
        else if(e.getKeyCode() == DOWN_KEY_CODE) {

             int size = 0;

            if(currType == 0) 
                size = structureCommands.length;
            else if (currType == 1)
                size = unitCommands.length;
            else if (currType == 2)
                size = armyCommands.length;
            
            if (currCommand > 0) currCommand--;
            else currCommand = size - 1;

            updateCommand();
        }
    }

}