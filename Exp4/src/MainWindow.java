import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private SQLHandler handler = new SQLHandler();
    private InsertPage insertPage = new InsertPage(handler);
    private DeletePage deletePage = new DeletePage(handler);
    private UpdatePage updatePage = new UpdatePage();
    private QueryPage queryPage = new QueryPage();

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
        app.pack();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }
}
