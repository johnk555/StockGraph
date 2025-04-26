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

     public String getStockData(String symbol, String startDate, String endDate) {
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

            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
