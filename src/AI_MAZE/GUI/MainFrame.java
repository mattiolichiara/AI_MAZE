package AI_MAZE.GUI;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    
    public static final int PANEL_WIDTH = 600;
    public static final int PANEL_HEIGHT = 600;
    
    public static final int MENU = 1;
    public static final int LEVELS = 2;
    public static final int MODE = 3;
    public static final int TUTORIAL = 4;
    public static final int MAIN_PANEL = 5;
    public static final int AI_MAIN_PANEL = 6;
    
    private static Menu menu;
    private static Levels levels;
    private static GameModality gameMode;
    private static Tutorial tutorial;
    private static MainPanel mainPanel;
    private static AI_MainPanel ai_MainPanel;
    private static StatisticsFrame statisticsFrame;
    private static MazeSolvedFrame mazeSolvedFrame;
    
    public MainFrame () {
        this.setSize(PANEL_WIDTH+60, PANEL_HEIGHT+60);
        this.setLocation(480, 40);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("MAZE");
        this.setResizable(false);
        
        menu = new Menu();
        this.getContentPane().add(menu);
        
        levels = new Levels();
        this.getContentPane().add(levels);
        
        gameMode = new GameModality();
        this.getContentPane().add(gameMode);
        
        tutorial = new Tutorial();
        this.getContentPane().add(tutorial);
        
        mainPanel = new MainPanel();
        this.getContentPane().add(mainPanel);
        
        ai_MainPanel = new AI_MainPanel();
        this.getContentPane().add(ai_MainPanel);
        
        statisticsFrame = new StatisticsFrame();
        statisticsFrame.setVisible(true); 
        
        mazeSolvedFrame = new MazeSolvedFrame(-1); 
                 
        changeGameStatus(MENU);
    }
    
    public static void changeGameStatus(int status) {
        switch(status) {
            case MENU:
                mainPanel.setVisible(false);
                levels.setVisible(false);
                menu.setVisible(true);
                tutorial.setVisible(false);
                gameMode.setVisible(false);
                StatisticsFrame.changeGameStatus(StatisticsFrame.STATISTICS_PANEL);
                mazeSolvedFrame.setVisible(false);
                ai_MainPanel.setVisible(false);
            break;
            case LEVELS:
                mainPanel.setVisible(false);
                levels.setVisible(true);
                menu.setVisible(false);
                tutorial.setVisible(false);
                gameMode.setVisible(false);
                StatisticsFrame.changeGameStatus(StatisticsFrame.STATISTICS_PANEL);
                mazeSolvedFrame.setVisible(false);
                ai_MainPanel.setVisible(false);
            break;
            case MODE:
                mainPanel.setVisible(false);
                levels.setVisible(false);
                menu.setVisible(false);
                tutorial.setVisible(false);
                gameMode.setVisible(true);
                StatisticsFrame.changeGameStatus(StatisticsFrame.STATISTICS_PANEL);
                mazeSolvedFrame.setVisible(false);
                ai_MainPanel.setVisible(false);
            break;
            case TUTORIAL:
                mainPanel.setVisible(false);
                levels.setVisible(false);
                menu.setVisible(false);
                tutorial.setVisible(true);
                gameMode.setVisible(false);
                StatisticsFrame.changeGameStatus(StatisticsFrame.STATISTICS_PANEL);
                mazeSolvedFrame.setVisible(false);
                ai_MainPanel.setVisible(false);
            break;
            case MAIN_PANEL:
                mainPanel.setVisible(true);
                levels.setVisible(false);
                menu.setVisible(false);
                tutorial.setVisible(false);
                gameMode.setVisible(false);
                StatisticsFrame.changeGameStatus(StatisticsFrame.ACTIVE_STATISTICS);  
                mazeSolvedFrame.setVisible(false);
                ai_MainPanel.setVisible(false);
                mainPanel.grabFocus();
            break;
            case AI_MAIN_PANEL:
                mainPanel.setVisible(false);
                levels.setVisible(false);
                menu.setVisible(false);
                tutorial.setVisible(false);
                gameMode.setVisible(false);
                StatisticsFrame.changeGameStatus(StatisticsFrame.ACTIVE_STATISTICS);  
                mazeSolvedFrame.setVisible(false);
                ai_MainPanel.setVisible(true);
                ai_MainPanel.grabFocus();
                break;
        }
    }
    
    public static void pausedStatus() {
        mazeSolvedFrame = new MazeSolvedFrame(4); 
        mazeSolvedFrame.setVisible(true);
    }
    
    /*public static void pausedVisible(int type, boolean bool) {
        mazeSolvedFrame.setVisible(bool);
        if(bool==false) {
            mazeSolvedFrame = null;
        }
    }*/
    
    public static MazeSolvedFrame getMazeSolvedFrame() {
        return mazeSolvedFrame;
    }
    
    public static void changeSolvedStatus(int type) {
                mainPanel.setVisible(false);
                levels.setVisible(true);
                menu.setVisible(false);
                tutorial.setVisible(false);
                gameMode.setVisible(false);
                StatisticsFrame.changeGameStatus(StatisticsFrame.STATISTICS_PANEL);
                mazeSolvedFrame = new MazeSolvedFrame(type); 
                mazeSolvedFrame.setVisible(true);
    }
}
