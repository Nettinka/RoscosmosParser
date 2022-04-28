import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestDBService {
    private static Boolean isSuccess = true;
    private static Connection con;
    private static Statement statement;
    private static ResultSet resSet;

    public Statement getConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:results.sqlite");
            //System.out.println("Connect!");
            statement = con.createStatement();
            return statement;
        } catch (Exception e) {
            this.isSuccess = false;
            e.getMessage();
            return null;
        }
    }

    public void insertRecords(List<String> titleList) throws SQLException {
        if (isSuccess) {
            for (String s : titleList) {
                statement.execute("INSERT INTO 'headers' ('title') VALUES ('" + s + "'); ");
            }
            System.out.println("Таблица заполнена");
        }
    }

    public void DeleteRecordsFromTable(String name) throws SQLException {
        statement = con.createStatement();
        statement.execute("DELETE FROM '" + name +"';");

        System.out.println("Данные удалены.");
    }

    public void CreateDB() throws SQLException {
        statement = con.createStatement();
        statement.execute("CREATE TABLE if not exists 'headers' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'title' text);");

        System.out.println("Таблица создана или уже существует.");
    }

    public Map<Integer, String> getAllrecords() throws SQLException {
        Map<Integer, String> mapOfRec = new HashMap<>();
        if (isSuccess) {
            resSet = statement.executeQuery("SELECT * FROM headers");

            while (resSet.next()) {
                int id = resSet.getInt("id");
                String title = resSet.getString("title");
                System.out.println("ID = " + id);
                System.out.println("title = " + title);
                System.out.println();
                mapOfRec.put(id, title);
            }
        }
        return mapOfRec;
    }

    public void CloseDB() throws SQLException {
        con.close();
        statement.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }

}
