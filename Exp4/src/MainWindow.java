import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private SQLHandler handler = new SQLHandler();
    private InsertPage insertPage = new InsertPage(handler);
    private DeletePage deletePage = new DeletePage(handler);
    private UpdatePage updatePage = new UpdatePage(handler);
    private QueryPage queryPage = new QueryPage(handler);

    public MainWindow() throws HeadlessException {
        getContentPane().setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("查询", queryPage);
        tabbedPane.addTab("插入", insertPage);
        tabbedPane.addTab("删除", deletePage);
        tabbedPane.addTab("修改", updatePage);

        getContentPane().add(tabbedPane);
    }

    public static void main(String[] args) {
        JFrame app = new MainWindow();
        app.setTitle("图书管理系统");
        app.setSize(640, 480);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }
}
