import java.sql.*;

public class SQLHandler {
    private Connection con;
    private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=Books";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "Downstream";

    public enum ColumnName {
        NAME, AUTHOR, PUBLISHER, ISBN
    }

    public SQLHandler() {
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
            System.out.println("连接到数据库成功");
        } catch (ClassNotFoundException e) {
            System.err.println("找不到驱动：" + e.getMessage());
        } catch (SQLException e) {
            System.err.println("连接数据库失败：" + e.getMessage());
        }
    }

    public void insertAnItem(String bookName, String author, String publisher, String ISBN, float price)
            throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO BOOKS VALUES (?, ?, ?, ?, ?)");

        ps.setString(1, bookName);
        ps.setString(2, author);
        ps.setString(3, publisher);
        ps.setString(4, ISBN);
        ps.setFloat(5, price);

        ps.executeUpdate();
    }

    public void updateAnItem(String bookName, String author, String publisher, String ISBN, float price)
            throws SQLException {

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE BOOKS " +
                            "SET AUTHOR = ?, PUBLISHER = ?, ISBN = ?, PRICE = ? " +
                            "WHERE NAME = ?"
            );

            ps.setString(1, author);
            ps.setString(2, publisher);
            ps.setString(3, ISBN);
            ps.setFloat(4, price);

            ps.setString(5, bookName);

            ps.executeUpdate();

    }

    public ResultSet query(ColumnName column, String value) throws SQLException {
        StringBuilder queryString = new StringBuilder("SELECT * FROM BOOKS WHERE ");
        switch (column) {
            case NAME:
                queryString.append("NAME = ?");
                break;
            case AUTHOR:
                queryString.append("AUTHOR = ?");
                break;
            case PUBLISHER:
                queryString.append("PUBLISHER = ?");
                break;
            case ISBN:
                queryString.append("ISBN = ?");
                break;
        }

        PreparedStatement ps = con.prepareStatement(queryString.toString());
        ps.setString(1, value);

        return ps.executeQuery();
    }

    public void deleteAnItem(String bookName) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM BOOKS WHERE NAME = ?");

        ps.setString(1, bookName);

        ps.executeUpdate();
    }
}
