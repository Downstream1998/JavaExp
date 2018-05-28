import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
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

    // 对话框
    private AddItem addItem;

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

        addItem = new AddItem(this, "添加商品");
        addItem.setModal(true);
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

    public static void main(String[] args) {
        MainWindow frame = new MainWindow();
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jbtAdd) {
                addItem.setVisible(true);
                shoppingListContent.addRow(addItem.getRowData());
                System.out.println("输入的商品信息为：");
                for(Object x : addItem.getRowData())
                    System.out.print(x + "\t");
                System.out.println();
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