import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPage extends JPanel implements ActionListener {
    private JTextField jtfName = new JTextField(15);
    private JTextField jtfAuthor = new JTextField(15);
    private JTextField jtfPublisher = new JTextField(15);
    private JTextField jtfISBN = new JTextField(15);

    private JButton jbtQuery = new JButton("查询");

    private JTextArea jtaInfo = new JTextArea();

    private JScrollPane jspInfoView = new JScrollPane(jtaInfo);

    private SQLHandler handler;

    public QueryPage(SQLHandler handler) {
        this.handler = handler;
        placeComponents();
        registerHandlers();
    }

    private void placeComponents() {
        setLayout(new BorderLayout());

        Box labels = Box.createVerticalBox();
        labels.add(new JLabel("书名："));
        labels.add(Box.createVerticalStrut(8));
        labels.add(new JLabel("作者："));
        labels.add(Box.createVerticalStrut(8));
        labels.add(new JLabel("出版社："));
        labels.add(Box.createVerticalStrut(8));
        labels.add(new JLabel("书号："));

        Box textFields = Box.createVerticalBox();
        textFields.add(jtfName);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(jtfAuthor);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(jtfPublisher);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(jtfISBN);

        Box inputArea = Box.createHorizontalBox();
        inputArea.add(labels);
        inputArea.add(Box.createHorizontalStrut(10));
        inputArea.add(textFields);

        Box queryArea = Box.createVerticalBox();
        queryArea.add(inputArea);
        queryArea.add(Box.createVerticalStrut(5));
        queryArea.add(jbtQuery);

        JPanel jplQuery = new JPanel(new FlowLayout());
        jplQuery.add(queryArea);
        jtaInfo.setEditable(false);
        jspInfoView.setBorder(new TitledBorder("查询结果"));

        add(jplQuery, BorderLayout.NORTH);
        add(jspInfoView, BorderLayout.CENTER);
    }

    private void registerHandlers() {
        jbtQuery.addActionListener(this);
    }

    private void printQueryResult(ResultSet rs) throws SQLException {
        int count = 1;
        while (rs.next()) {
            jtaInfo.append("#" + count + "\n");
            jtaInfo.append("书名：" + rs.getString("NAME") + "\n");
            jtaInfo.append("作者：" + rs.getString("AUTHOR") + "\n");
            jtaInfo.append("出版社：" + rs.getString("PUBLISHER") + "\n");
            jtaInfo.append("书号：" + rs.getString("ISBN") + "\n");
            jtaInfo.append("价格：" + rs.getFloat("PRICE") + "\n");
            jtaInfo.append("\n");
            count++;
        }
        if (count == 1)
            jtaInfo.append("无结果");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jtaInfo.setText("");

        String bookName = jtfName.getText();
        String author = jtfAuthor.getText();
        String publisher = jtfPublisher.getText();
        String ISBN = jtfISBN.getText();
        ResultSet rs = null;

        try {
            if (bookName.length() != 0) {
                rs = handler.query(SQLHandler.ColumnName.NAME, bookName);
            } else if (author.length() != 0) {
                rs = handler.query(SQLHandler.ColumnName.AUTHOR, author);
            } else if (publisher.length() != 0) {
                rs = handler.query(SQLHandler.ColumnName.PUBLISHER, publisher);
            } else if (ISBN.length() != 0) {
                rs = handler.query(SQLHandler.ColumnName.ISBN, ISBN);
            }

            printQueryResult(rs);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }

        jtfName.setText("");
        jtfAuthor.setText("");
        jtfPublisher.setText("");
        jtfISBN.setText("");
    }
}
