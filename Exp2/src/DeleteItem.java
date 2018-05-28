import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DeleteItem extends JDialog {
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

        c.add(inputArea, BorderLayout.NORTH);

        jtaResult.setBorder(new TitledBorder("查询结果"));

        c.add(jtaResult, BorderLayout.CENTER);

        Box buttons = Box.createHorizontalBox();
        buttons.add(jbtOK);
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(jbtCancel);

        Box confirmArea = Box.createVerticalBox();
        confirmArea.add(new JLabel("单击“确认”删除商品，单击“取消”关闭该窗口"));
        confirmArea.add(Box.createVerticalStrut(5));
        confirmArea.add(buttons);

        c.add(confirmArea, BorderLayout.SOUTH);
    }
}
