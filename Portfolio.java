import java.util.HashMap;

public class Portfolio {

    private HashMap<String, Integer> stocks = new HashMap<>();
    private HashMap<String, Double> buyPrice = new HashMap<>();

    public void buyStock(String symbol, int quantity, double price) {
        stocks.put(symbol, stocks.getOrDefault(symbol, 0) + quantity);
        buyPrice.put(symbol, price);
    }

    public boolean sellStock(String symbol, int quantity) {

        if (!stocks.containsKey(symbol))
            return false;

        int current = stocks.get(symbol);

        if (quantity > current)
            return false;

        if (quantity == current)
            stocks.remove(symbol);
        else
            stocks.put(symbol, current - quantity);

        return true;
    }

    public HashMap<String, Integer> getStocks() {
        return stocks;
    }

    public double getBuyPrice(String symbol) {
        return buyPrice.get(symbol);
    }
}
