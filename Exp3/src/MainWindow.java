import boards.GameBoard;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    MainWindow() {
        setLayout(new BorderLayout());
        GameBoard board = new GameBoard();

        Thread t = new Thread(board);
        t.start();
        getContentPane().add(board, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MainWindow frame = new MainWindow();
        frame.setTitle("炮打飞机小游戏");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
