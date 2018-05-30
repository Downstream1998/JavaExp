import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class QueryPage extends JDialog implements ActionListener {
    private int queryIndex;
    private Vector queriedData;
    private DefaultTableModel content;

    private JTextField jtfInput;
    private JTextArea jtaResult;
    private JButton jbtQuery;
    private JButton jbtQueryFirst;
    private JButton jbtQueryLast;

    public QueryPage(Frame owner, String title, DefaultTableModel content) {
        super(owner, title);
        this.content = content;

        initializeAll();
        placeComponents();
        registerHandlers();

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeAll() {
        jtfInput = new JTextField(5);
        jtaResult = new JTextArea();
        jtaResult.setEditable(false);
        jbtQuery = new JButton("查询");
        jbtQueryFirst = new JButton("查询第一个");
        jbtQueryLast = new JButton("查询最后一个");
    }

    private void placeComponents() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel buttons = new JPanel(new GridLayout(1, 2, 10, 5));
        buttons.add(jbtQueryFirst);
        buttons.add(jbtQueryLast);

        Box queryArea = Box.createHorizontalBox();
        queryArea.add(jtfInput);
        queryArea.add(Box.createHorizontalStrut(10));
        queryArea.add(jbtQuery);

        JPanel northArea = new JPanel(new GridLayout(3, 1, 10, 5));
        northArea.add(new JLabel("输入要查询商品的购买号，或者点击以下两个选项"));
        northArea.add(buttons);
        northArea.add(queryArea);

        c.add(northArea, BorderLayout.NORTH);

        jtaResult.setBorder(new TitledBorder("查询结果（品名/单价/数量/总价）"));
        c.add(jtaResult, BorderLayout.CENTER);
    }

    private void registerHandlers() {
        jbtQuery.addActionListener(this);
        jbtQueryFirst.addActionListener(this);
        jbtQueryLast.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                queryIndex = -1;
            }
        });
    }

    private void displayQueryResult() {
        jtaResult.setText("");
        for(Object obj : queriedData)
            jtaResult.append(obj + "\t");
    }

    public Vector getQueriedData() {
        return queriedData;
    }

    public void clearQueriedData() {
        queriedData = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Vector<Vector> data = content.getDataVector();
        if(e.getSource() == jbtQuery) {
            try {
                queryIndex = Integer.parseInt(jtfInput.getText()) - 1;
                queriedData = data.elementAt(queryIndex);
                displayQueryResult();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "输入错误，请重新输入！",
                        "数据输入错误", JOptionPane.ERROR_MESSAGE);
            } catch (ArrayIndexOutOfBoundsException ex) {
                String message = String.format("当前序号超出范围 (1 ~ %d)，请检查后重新输入", content.getRowCount());
                JOptionPane.showMessageDialog(this, message,
                        "行号超出范围", JOptionPane.ERROR_MESSAGE);
            }
            jtfInput.setText("");
        } else if(e.getSource() == jbtQueryFirst) {
            queriedData = data.firstElement();
            displayQueryResult();
        } else if(e.getSource() == jbtQueryLast) {
            queriedData = data.lastElement();
            displayQueryResult();
        }
    }
}
