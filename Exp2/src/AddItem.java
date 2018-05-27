import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItem extends JFrame implements ActionListener {
    private DefaultTableModel content;
    private JTextField jtfName;
    private JTextField jtfPrice;
    private JTextField jtfQuantity;
    private JButton jbtCommit;
    private JButton jbtClose;

    public AddItem(DefaultTableModel content) {
        this.content = content;
        initializeAll();
        placeComponents();
        registerHandles();

        setTitle("添加商品");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        requestFocus();
    }

    private void initializeAll() {
        jtfName = new JTextField(15);
        jtfPrice = new JTextField(15);
        jtfQuantity = new JTextField(15);
        jbtCommit = new JButton("确认");
        jbtClose = new JButton("关闭");
    }

    private void placeComponents() {
        Container con = getContentPane();

        Box labels = Box.createVerticalBox();
        labels.add(new JLabel("输入商品名"));
        labels.add(Box.createVerticalStrut(5));
        labels.add(new JLabel("输入商品单价"));
        labels.add(Box.createVerticalStrut(5));
        labels.add(new JLabel("输入商品数量"));

        Box textFields = Box.createVerticalBox();
        textFields.add(jtfName);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(jtfPrice);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(jtfQuantity);

        Box inputArea = Box.createHorizontalBox();
        inputArea.add(labels);
        inputArea.add(Box.createHorizontalStrut(10));
        inputArea.add(textFields);

        Box buttonArea = Box.createHorizontalBox();
        buttonArea.add(jbtCommit);
        buttonArea.add(Box.createHorizontalStrut(10));
        buttonArea.add(jbtClose);

        Box panel = Box.createVerticalBox();
        panel.add(inputArea);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buttonArea);

        con.setLayout(new BorderLayout());
        con.add(panel, BorderLayout.CENTER);
    }

    private void registerHandles() {
        jbtClose.addActionListener(this);
        jbtCommit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbtCommit) {
            String name = jtfName.getText();
            try {
                double price = Double.parseDouble(jtfPrice.getText());
                int quantity = Integer.parseInt(jtfQuantity.getText());

                double totalPrice = price * quantity;
                Object[] row = {name, price, quantity, totalPrice};
                content.addRow(row);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "信息输入有误，请重新输入！");
                jtfQuantity.setText("");
                jtfPrice.setText("");
            }
        } else if(e.getSource() == jbtClose) {
            setVisible(false);
            dispose();
        }
    }
}