package boards;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {
    private int score = 0;
    private JTextField jtfScoreBoard = new JTextField(4);
    private JLabel jlblTitle = new JLabel("当前已击落敌机数");

    public ScoreBoard() {
        setLayout(new BorderLayout());
        jtfScoreBoard.setFont(new Font("sans-serif", Font.PLAIN, 20));
        jtfScoreBoard.setText("0");
        jtfScoreBoard.setEditable(false);
        jlblTitle.setFont(new Font("sans-serif", Font.PLAIN, 16));

        add(jtfScoreBoard, BorderLayout.CENTER);
        add(jlblTitle, BorderLayout.NORTH);
    }

    public void increaseScore() {
        score++;
        jtfScoreBoard.setText("" + score);
    }
}
