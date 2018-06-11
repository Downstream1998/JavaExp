import javax.swing.*;
import java.awt.*;

public class InsertPage extends JPanel {
    private JTextField jtfName = new JTextField(15);
    private JTextField jtfAuthor = new JTextField(15);
    private JTextField jtfPublisher = new JTextField(15);
    private JTextField jtfISBN = new JTextField(15);
    private JTextField jtfPrice = new JTextField(15);

    private JButton jbtInsert = new JButton("插入");

    public InsertPage() {
        placeComponents();
        registerHandlers();
    }

    private void placeComponents() {
        setLayout(new BorderLayout());

        Box labels = Box.createVerticalBox();
        labels.add(new JLabel("书名："));
        labels.add(Box.createVerticalStrut(5));
        labels.add(new JLabel("作者："));
        labels.add(Box.createVerticalStrut(5));
        labels.add(new JLabel("出版社："));
        labels.add(Box.createVerticalStrut(5));
        labels.add(new JLabel("书号："));
        labels.add(Box.createVerticalStrut(5));
        labels.add(new JLabel("价格："));

        Box textFields = Box.createVerticalBox();
        textFields.add(jtfName);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(jtfAuthor);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(jtfPublisher);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(jtfISBN);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(jtfPrice);

        Box inputArea = Box.createHorizontalBox();
        inputArea.add(labels);
        inputArea.add(Box.createHorizontalStrut(10));
        inputArea.add(textFields);

        Box insertArea = Box.createVerticalBox();
        insertArea.add(inputArea);
        insertArea.add(Box.createVerticalStrut(5));
        insertArea.add(jbtInsert);

        add(insertArea, BorderLayout.CENTER);
    }

    private void registerHandlers() {

    }
}
