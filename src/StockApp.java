import com.google.gson.Gson;
import java.io.Reader;
import java.util.List;

public class StockApp {

    public void run() {
        System.out.println("Stockapp is running...");

        StockApiService apiService = new StockApiService();
        List<Stock> stocks = apiService.getStockData("MSFT","2024-01-01","2024-01-31");

        for (Stock stock : stocks) {
            System.out.println(stock.getDate() + " | Open: " + stock.getOpen() + " | Close: " + stock.getClose());
        }

        DatabaseService dbService = new DatabaseService();
        dbService.saveStocks(stocks, "MSFT");
    }
}
