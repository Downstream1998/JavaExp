package boards;

import sprites.Cannon;
import sprites.Direction;

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
            cannon.setDirection(Direction.LEFT);
        } else if(e.getSource() == jbtMoveRight) {
            cannon.moveRight();
            cannon.setDirection(Direction.RIGHT);
        } else if(e.getSource() == jbtShoot) {
            // TODO: 添加发射动作
        }
    }
}
