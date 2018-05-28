import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class DeleteItem extends JDialog implements ActionListener {
    private int deletedRowIndex;
    private boolean canBeDeleted;
    private Vector deletedRowData;
    private DefaultTableModel content;

    private JTextField jtfInput;
    private JTextArea jtaResult;
    private JButton jbtQuery;
    private JButton jbtOK;
    private JButton jbtCancel;

    public DeleteItem(Frame owner, String title, DefaultTableModel content) {
        super(owner, title);
        this.content = content;

        initializeAll();
        placeComponents();
        registerHandles();

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeAll() {
        jtfInput = new JTextField(15);
        jtaResult = new JTextArea();
        jtaResult.setEditable(false);
        jbtQuery = new JButton("查询");
        jbtOK = new JButton("确定");
        jbtCancel = new JButton("取消");
    }

    private void placeComponents() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        Box inputArea = Box.createVerticalBox();
        inputArea.add(new JLabel("请输入商品号："));
        inputArea.add(Box.createVerticalStrut(5));
        inputArea.add(jtfInput);
        inputArea.add(Box.createVerticalStrut(5));
        inputArea.add(jbtQuery);

        c.add(inputArea, BorderLayout.NORTH);

        jtaResult.setBorder(new TitledBorder("查询结果（品名/单价/数量/总价）"));

        c.add(jtaResult, BorderLayout.CENTER);

        Box buttons = Box.createHorizontalBox();
        buttons.add(Box.createHorizontalGlue());
        buttons.add(jbtOK);
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(jbtCancel);
        buttons.add(Box.createHorizontalGlue());

        JPanel confirmArea = new JPanel(new GridLayout(2, 1, 5, 5));
        confirmArea.add(new JLabel("点击“确定”删除该商品并关闭窗口，或点击“取消”直接关闭窗口"));
        confirmArea.add(buttons);

        c.add(confirmArea, BorderLayout.SOUTH);
    }

    private void registerHandles() {
        jbtQuery.addActionListener(this);
        jbtOK.addActionListener(this);
        jbtCancel.addActionListener(this);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                deletedRowData = null;
                deletedRowIndex = -1;
            }
        });
    }

    private void displayQueryResult() {
        jtaResult.setText("");
        for(Object obj : deletedRowData)
            jtaResult.append(obj + "\t");
    }

    public Vector getDeletedRowData() {
        return deletedRowData;
    }

    public int getDeletedRowIndex() {
        return deletedRowIndex;
    }

    public boolean isCanBeDeleted() {
        return canBeDeleted;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbtQuery) {
            Vector<Vector> data = content.getDataVector();
            try {
                deletedRowIndex = Integer.parseInt(jtfInput.getText()) - 1;
                deletedRowData = data.elementAt(deletedRowIndex);
                displayQueryResult();
                jbtOK.setEnabled(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "输入错误，请重新输入",
                        "数据输入错误", JOptionPane.ERROR_MESSAGE);
                jtfInput.setText("");
            } catch (ArrayIndexOutOfBoundsException ex) {
                String message = String.format("当前序号超出范围 (1 ~ %d)，请检查后重新输入", data.size());
                JOptionPane.showMessageDialog(this, message,
                        "行号超出范围", JOptionPane.ERROR_MESSAGE);
                jtfInput.setText("");
            }
        } else if(e.getSource() == jbtOK) {
            canBeDeleted = true;
            jtfInput.setText("");
            jtaResult.setText("");
            setVisible(false);
            dispose();
        } else if(e.getSource() == jbtCancel) {
            canBeDeleted = false;
            jtfInput.setText("");
            jtaResult.setText("");
            setVisible(false);
            dispose();
        }
    }
}
