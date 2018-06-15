import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeletePage extends JPanel implements ActionListener {
    private JTextField jtfName = new JTextField(15);

    private JButton jbtDelete = new JButton("删除");

    private SQLHandler handler;

    public DeletePage(SQLHandler handler) {
        this.handler = handler;
        placeComponents();
        registerHandlers();
    }

    private void placeComponents() {
        setLayout(new FlowLayout());

        Box inputArea = Box.createHorizontalBox();
        inputArea.add(new JLabel("书名："));
        inputArea.add(Box.createHorizontalStrut(10));
        inputArea.add(jtfName);

        Box deleteArea = Box.createVerticalBox();
        deleteArea.add(Box.createVerticalGlue());
        deleteArea.add(inputArea);
        deleteArea.add(Box.createVerticalGlue());
        deleteArea.add(jbtDelete);

        add(deleteArea, BorderLayout.CENTER);
    }

    private void registerHandlers() {
        jbtDelete.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = jtfName.getText();
        try {
            handler.deleteAnItem(name);
            JOptionPane.showMessageDialog(this, "操作已完成");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
        jtfName.setText("");
    }
}
