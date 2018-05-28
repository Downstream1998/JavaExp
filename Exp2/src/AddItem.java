import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddItem extends JDialog implements ActionListener {
    private JTextField jtfName;
    private JTextField jtfPrice;
    private JTextField jtfQuantity;
    private JButton jbtCommit;

    private Object[] rowData;

    public AddItem(Frame owner, String title) {
        super(owner, title);
        initializeAll();
        placeComponents();
        registerHandles();

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeAll() {
        jtfName = new JTextField(15);
        jtfPrice = new JTextField(15);
        jtfQuantity = new JTextField(15);
        jbtCommit = new JButton("确认");
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

        Box panel = Box.createVerticalBox();
        panel.add(inputArea);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buttonArea);

        con.setLayout(new BorderLayout());
        con.add(panel, BorderLayout.CENTER);
    }

    private void registerHandles() {
        jbtCommit.addActionListener(this);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                rowData = null;
            }
        });
    }

    public Object[] getRowData() {
        return rowData;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbtCommit) {
            String name = jtfName.getText();
            try {
                double price = Double.parseDouble(jtfPrice.getText());
                int quantity = Integer.parseInt(jtfQuantity.getText());

                double totalPrice = price * quantity;
                rowData = new Object[]{name, price, quantity, totalPrice};

                jtfName.setText("");
                jtfPrice.setText("");
                jtfQuantity.setText("");

                setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "信息输入有误，请重新输入！");
                jtfQuantity.setText("");
                jtfPrice.setText("");
            }
        }
    }
}