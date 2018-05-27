import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JPanel {
    private JTable shoppingList;
    private DefaultTableModel shoppingListContent;
    private JTextArea jtaInfo;
    private JButton jbtAdd;
    private JButton jbtDelete;
    private JButton jbtQuery;
    private JButton jbtDeleteAll;
    private JButton jbtPrint;
    private JButton jbtClear;
    private JButton jbtPay;
    private ButtonHandler handler;

    private MainWindow() {
        initializeAll();
        placeComponents();
        registerHandler();
    }

    private void initializeAll() {
        Object[] tableHead = {"品名", "单价", "数量", "总价"};
        shoppingListContent = new DefaultTableModel(tableHead, 0);
        shoppingList = new JTable(shoppingListContent);
        jtaInfo = new JTextArea();
        jbtAdd = new JButton("添加");
        jbtDelete = new JButton("删除");
        jbtQuery = new JButton("查询");
        jbtDeleteAll = new JButton("清空列表");
        jbtPrint = new JButton("打印清单");
        jbtClear = new JButton("清空消息窗");
        jbtPay = new JButton("结算");
        handler = new ButtonHandler();
    }

    private void placeComponents() {
        setLayout(new BorderLayout());

        jtaInfo.setBorder(new TitledBorder("消息区："));
        jtaInfo.setEditable(false);
        add(jtaInfo, BorderLayout.CENTER);

        JPanel buttonArea = new JPanel(new GridLayout(2, 4, 10, 5));

        buttonArea.add(jbtAdd);
        buttonArea.add(jbtDelete);
        buttonArea.add(jbtQuery);
        buttonArea.add(jbtDeleteAll);
        buttonArea.add(jbtPrint);
        buttonArea.add(jbtClear);
        buttonArea.add(jbtPay);
        buttonArea.setBorder(new TitledBorder("请选择功能："));
        add(buttonArea, BorderLayout.NORTH);
    }

    private void registerHandler() {
        jbtAdd.addActionListener(handler);
        jbtDelete.addActionListener(handler);
        jbtQuery.addActionListener(handler);
        jbtDeleteAll.addActionListener(handler);
        jbtPrint.addActionListener(handler);
        jbtClear.addActionListener(handler);
        jbtPay.addActionListener(handler);
    }

    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("超市购物结算模拟");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        MainWindow mainWindow = new MainWindow();
        mainWindow.setOpaque(true);
        frame.setContentPane(mainWindow);

        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // Creating and showing this application's GUI
        javax.swing.SwingUtilities.invokeLater(MainWindow::createAndShowGUI);
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jbtAdd) {
                new AddItem(shoppingListContent);
            } else if(e.getSource() == jbtDelete) {
                // TODO: 删除商品
                System.out.println("TODO：删除商品");
            } else if(e.getSource() == jbtQuery) {
                // TODO: 查询商品
                System.out.println("TODO：查询商品");
            } else if(e.getSource() == jbtDeleteAll) {
                // TODO: 清空列表
                System.out.println("TODO：清空列表");
            } else if(e.getSource() == jbtClear) {
                jtaInfo.setText("");
            } else if(e.getSource() == jbtPay) {
                // TODO: 结算
                System.out.println("TODO：结算");
            } else if(e.getSource() == jbtPrint) {
                // TODO: 打印清单
                System.out.println("TODO；打印清单");
            }
        }
    }
}