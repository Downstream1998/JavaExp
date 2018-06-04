package boards;

import sprites.Cannon;

import javax.swing.*;
import java.awt.*;

public class AdditionBoard extends JPanel {
    public AdditionBoard(Cannon cannon) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke()));
        add(new ControlBoard(cannon), BorderLayout.SOUTH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 450);
    }
}
