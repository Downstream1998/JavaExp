import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class BalancingPage extends JDialog {
    DefaultTableModel content;

    JTable table;
    JButton jbtPay;
    JLabel jlblMessage;

    public BalancingPage(Frame owner, String title, DefaultTableModel content) {
        super(owner, title);
        this.content = content;

        initializeAll();
        placeComponents();
        registHandlers();


        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeAll() {
        table = new JTable(content);
        jbtPay = new JButton("点击结算");
        jlblMessage = new JLabel();
    }

    private void placeComponents() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        Box northBox = Box.createHorizontalBox();
        northBox.add(new JLabel("以下是你购买的商品"));
        northBox.add(Box.createHorizontalGlue());
        northBox.add(jbtPay);

        c.add(northBox, BorderLayout.NORTH);
        c.add(new JScrollPane(table), BorderLayout.CENTER);
        c.add(jlblMessage, BorderLayout.SOUTH);
    }

    private void registHandlers() {
        jbtPay.addActionListener(e -> {
            String message = String.format("你购买了 %d 件商品，需支付 ￥%.2f", content.getRowCount(), calculateTotalPrice());
            jlblMessage.setText(message);
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                jlblMessage.setText("");
            }
        });
    }

    private double calculateTotalPrice() {
        double total = 0.0;
        Vector<Vector> data = content.getDataVector();
        for(Vector row : data) total += (double) row.lastElement();
        return total;
    }
}
