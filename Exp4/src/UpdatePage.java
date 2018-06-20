import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatePage extends JPanel implements ActionListener {
    private JTextField jtfName = new JTextField(15);
    private JTextField jtfAuthor = new JTextField(15);
    private JTextField jtfPublisher = new JTextField(15);
    private JTextField jtfISBN = new JTextField(15);
    private JTextField jtfPrice = new JTextField(15);

    private JButton jbtUpdate = new JButton("更新");
    private JButton jbtCheck = new JButton("确认");

    private SQLHandler handler;

    public UpdatePage(SQLHandler handler) {
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

        jbtUpdate.setEnabled(false);
        Box buttonsArea = Box.createHorizontalBox();
        buttonsArea.add(jbtCheck);
        buttonsArea.add(Box.createHorizontalGlue());
        buttonsArea.add(jbtUpdate);

        Box updateArea = Box.createVerticalBox();
        updateArea.add(inputArea);
        updateArea.add(Box.createVerticalStrut(5));
        updateArea.add(buttonsArea);

        add(updateArea);
    }

    private void registerHandlers() {
        jbtUpdate.addActionListener(this);
        jbtCheck.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtUpdate)
            handleUpdate();
        else if (e.getSource() == jbtCheck)
            handleCheck();
    }

    private void handleCheck() {
        String bookName = jtfName.getText();
        String author = jtfAuthor.getText();
        try {
            ResultSet rs = handler.queryByPrimaryKey(bookName, author);
            if (rs.next()) {
                jtfPublisher.setText(rs.getString("PUBLISHER"));
                jtfISBN.setText(rs.getString("ISBN"));
                jtfPrice.setText("" + rs.getFloat("PRICE"));
                jbtUpdate.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "未查到相应记录", "", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdate() {
        String name = jtfName.getText();
        String author = jtfAuthor.getText();
        String publisher = jtfPublisher.getText();
        String ISBN = jtfISBN.getText();
        try {
            float price = Float.parseFloat(jtfPrice.getText());
            handler.updateAnItem(name, author, publisher, ISBN, price);
            JOptionPane.showMessageDialog(this, "操作已完成！");
            jbtUpdate.setEnabled(false);
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
        jtfPrice.setText("");
    }
}
