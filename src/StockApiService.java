import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

public class StockApiService {

    private static final String API_KEY = "f2d595185b98400892f2e6e8fabd8641";

     public List<Stock> getStockData(String symbol, String startDate, String endDate) {
         List<Stock> stocks = new ArrayList<>();

        try {
            String urlString = String.format(
                    "https://api.twelvedata.com/time_series?symbol=%s&interval=1day&start_date=%s&end_date=%s&apikey=%s",
                    symbol, startDate, endDate, API_KEY
            );

            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null){
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            JsonObject json = JsonParser.parseString(content.toString()).getAsJsonObject();
            JsonArray values = json.getAsJsonArray("values");

            for ( int i = 0; i < values.size(); i++){
                JsonObject value = values.get(i).getAsJsonObject();
                String date = value.get("datetime").getAsString();
                double open = value.get("open").getAsDouble();
                double high = value.get("high").getAsDouble();
                double low = value.get("low").getAsDouble();
                double close = value.get("close").getAsDouble();
                long volume = value.get("volume").getAsLong();

                stocks.add(new Stock(date,open,high,low,close,volume));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stocks;
    }
}
