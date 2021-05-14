package AI_MAZE.GUI;

import javax.swing.JFrame;

public class StatisticsFrame extends JFrame {
    
    public static final int STATISTICS_WIDTH = 300;
    public static final int STATISTICS_HEIGHT = 600;
    
    public static final int STATISTICS_PANEL = 1;
    public static final int ACTIVE_STATISTICS = 2;
    
    private static ActiveStatistics activeStatistics;
    private static StatisticsPanel statisticsPanel;
    
    public StatisticsFrame () {
        this.setSize(STATISTICS_WIDTH+60, STATISTICS_HEIGHT+60);
        this.setLocation(100, 40);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("MAZE STATISTICS");
        this.setResizable(false);
        
        statisticsPanel = new StatisticsPanel();
        this.getContentPane().add(statisticsPanel);
        
        activeStatistics = new ActiveStatistics();
        this.getContentPane().add(activeStatistics);
        
        changeGameStatus(STATISTICS_PANEL);
    }
    
    public static void changeGameStatus(int status) {
        switch(status) {
            case STATISTICS_PANEL:
                statisticsPanel.setVisible(true);
                activeStatistics.setVisible(false);
            break;
            case ACTIVE_STATISTICS:
                activeStatistics.setVisible(true);
                statisticsPanel.setVisible(false);
            break;
        }
    }
}
