package boards;

import sprites.Cannon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlBoard extends JPanel implements ActionListener {
    private final JButton jbtMoveLeft = new JButton("←");
    private final JButton jbtMoveRight = new JButton("→");
    private final JButton jbtShoot = new JButton("开炮！");

    private Cannon cannon;

    public ControlBoard(Cannon cannon) {
        this.cannon = cannon;
        setLayout(new GridLayout(1, 3, 10, 10));

        jbtMoveLeft.addActionListener(this);
        jbtShoot.addActionListener(this);
        jbtMoveRight.addActionListener(this);

        add(jbtMoveLeft);
        add(jbtShoot);
        add(jbtMoveRight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbtMoveLeft) {
            cannon.moveLeft();
        } else if(e.getSource() == jbtMoveRight) {
            cannon.moveRight();
        } else if(e.getSource() == jbtShoot) {
            if(cannon.getBullets().size() < 10)
                cannon.shoot();
        }
    }
}
