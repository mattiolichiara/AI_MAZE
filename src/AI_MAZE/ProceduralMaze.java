package AI_MAZE;

import AI_MAZE.GUI.MainFrame;

public class ProceduralMaze {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        System.out.println("Code Running!");
    }
    
}


//fitting solution is if the piece is not in a blocked position, it can go up only if that upper cell hasn't been visited
//depth first search anche per il player, perchè se rimane bloccato e gli unici muri liberi sono di una cella già visitata non deve essere passato
//alla prossima generazione

//movimento: ogni movimento deve essere randomico ma verso i muri liberi e mai visitati, se a metà percorso e a fine generazione si trova verso muri
//liberi passa all'altra generazione, però ci deve sta la cazzo di mutazione che mi mette un botto di dubbi