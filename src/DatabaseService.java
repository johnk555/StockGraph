import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseService {
    private static final String URL = "jdbc:mysql://localhost:3306/stocks_db";
    private static final String USER = "root";
    private static final String PASSWORD = "karelas159357";

    public void saveStocks(List<Stock> stocks, String symbol) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            String sql = "INSERT INTO stock_prices (stock_symbol, price_date, open_price, high_price, low_price, close_price, volume) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (Stock stock : stocks){
                stmt.setString(1, symbol);
                stmt.setDate(2, Date.valueOf(stock.getDate()));
                stmt.setDouble(3, stock.getOpen());
                stmt.setDouble(4, stock.getHigh());
                stmt.setDouble(5, stock.getLow());
                stmt.setDouble(6, stock.getClose());
                stmt.setDouble(7, stock.getVolume());
                stmt.addBatch();
            }

            stmt.executeBatch();
            System.out.println("Stocks inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
