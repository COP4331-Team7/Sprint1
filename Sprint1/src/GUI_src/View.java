package GUI_src;

import com.team7.Main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class View
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