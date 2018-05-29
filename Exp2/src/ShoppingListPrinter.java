import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShoppingListPrinter extends JDialog {
    JTable table;
    DefaultTableModel content;

    public ShoppingListPrinter(Frame owner, String title, DefaultTableModel content) {
        super(owner, title);
        this.content = content;

        initializeAll();
        placeComponents();

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeAll() {
        table = new JTable(content);
    }

    private void placeComponents() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        c.add(new JLabel("以下是你购买的商品"), BorderLayout.NORTH);
        c.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
