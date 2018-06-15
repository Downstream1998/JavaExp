import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InsertPage extends JPanel implements ActionListener {
    private JTextField jtfName = new JTextField(15);
    private JTextField jtfAuthor = new JTextField(15);
    private JTextField jtfPublisher = new JTextField(15);
    private JTextField jtfISBN = new JTextField(15);
    private JTextField jtfPrice = new JTextField(15);

    SQLHandler handler;

    private JButton jbtInsert = new JButton("插入");

    public InsertPage(SQLHandler handler) {
        this.handler = handler;
        placeComponents();
        registerHandlers();
    }

    private void placeComponents() {
        setLayout(new FlowLayout());

        Box labels = Box.createVerticalBox();
        labels.add(new JLabel("书名："));
        labels.add(Box.createVerticalStrut(8));
        labels.add(new JLabel("作者："));
        labels.add(Box.createVerticalStrut(8));
        labels.add(new JLabel("出版社："));
        labels.add(Box.createVerticalStrut(8));
        labels.add(new JLabel("书号："));
        labels.add(Box.createVerticalStrut(8));
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
        jbtInsert.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = jtfName.getText();
        String author = jtfAuthor.getText();
        String publisher = jtfPublisher.getText();
        String ISBN = jtfISBN.getText();
        try {
            float price = Float.parseFloat(jtfPrice.getText());
            handler.insertAnItem(name, author, publisher, ISBN, price);
            JOptionPane.showMessageDialog(this, "插入成功！");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入合法的价格值", "", JOptionPane.WARNING_MESSAGE);
            jtfPrice.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
        jtfName.setText("");
        jtfAuthor.setText("");
        jtfPublisher.setText("");
        jtfISBN.setText("");
        jtfPublisher.setText("");
    }
}
