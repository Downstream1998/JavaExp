package boards;

import sprites.Cannon;

import javax.swing.*;
import java.awt.*;

public class AdditionBoard extends JPanel {
    public AdditionBoard(Cannon cannon) {
        JTextArea jtaHowToPlay = new JTextArea();
        jtaHowToPlay.setEditable(false);
        jtaHowToPlay.setRequestFocusEnabled(false);
        jtaHowToPlay.setText("玩法：\n" +
                "←：炮口左偏\n" +
                "→：炮口右偏\n" +
                "开火：发射");
        jtaHowToPlay.setFont(new Font("sans-serif", Font.PLAIN, 16));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke()));
        add(jtaHowToPlay, BorderLayout.NORTH);
        add(new ControlBoard(cannon), BorderLayout.SOUTH);
        add(GameBoard.scoreBoard, BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 450);
    }
}
