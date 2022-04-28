import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        RequestDBService rs = new RequestDBService();
        rs.getConnect();

        //Удаление данных из таблицы
        //rs.DeleteRecordsFromTable("headers");

        //Вывод всех записей
        rs.getAllrecords();
        System.out.println("\nВвод\n");
        //Парсинг и вставка данных
        rs.insertRecords(Parser.getDocumentBody());
        System.out.println("\nВывод\n");
        rs.getAllrecords();
        rs.CloseDB();
    }
}
