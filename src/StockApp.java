public class StockApp {

    public void run() {
        System.out.println("Stockapp is running...");

        StockApiService apiService = new StockApiService();
        String data = apiService.getStockData("AAPL","2024-01-01","2024-01-31");

        System.out.println(data);
    }
}
