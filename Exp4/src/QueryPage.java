import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class QueryPage extends JPanel {
    private JTextField jtfName = new JTextField(15);
    private JTextField jtfAuthor = new JTextField(15);
    private JTextField jtfPublisher = new JTextField(15);
    private JTextField jtfISBN = new JTextField(15);

    private JButton jbtQuery = new JButton("查询");

    private JTextArea jtaInfo = new JTextArea();

    public QueryPage() {
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

        Box textFields = Box.createVerticalBox();
        textFields.add(jtfName);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(jtfAuthor);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(jtfPublisher);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(jtfISBN);

        Box inputArea = Box.createHorizontalBox();
        inputArea.add(labels);
        inputArea.add(Box.createHorizontalStrut(10));
        inputArea.add(textFields);

        Box queryArea = Box.createVerticalBox();
        queryArea.add(inputArea);
        queryArea.add(Box.createVerticalStrut(5));
        queryArea.add(jbtQuery);

        jtaInfo.setBorder(new TitledBorder("查询结果"));

        add(queryArea, BorderLayout.CENTER);
        add(jtaInfo, BorderLayout.SOUTH);
    }

    private void registerHandlers() {

    }
}
