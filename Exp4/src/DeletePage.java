import javax.swing.*;
import java.awt.*;

public class DeletePage extends JPanel {
    private JTextField jtfName = new JTextField(15);

    private JButton jbtDelete = new JButton("删除");

    public DeletePage() {
        placeComponents();
        registerHandlers();
    }

    private void placeComponents() {
        setLayout(new BorderLayout());

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

    }
}
