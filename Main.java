import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static HashMap<String, Stock> market = new HashMap<>();

    public static void displayMarket() {

        System.out.println("\n------ Market Data ------");

        for (Stock s : market.values()) {
            System.out.println(s.getSymbol() + " : $" + s.getPrice());
        }
    }

    public static void buy(User user) {

        displayMarket();

        System.out.print("\nEnter Stock Symbol: ");
        String symbol = sc.next().toUpperCase();

        if (!market.containsKey(symbol)) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Quantity: ");
        int qty = sc.nextInt();

        Stock stock = market.get(symbol);

        double cost = qty * stock.getPrice();

        if (user.withdraw(cost)) {

            user.getPortfolio().buyStock(symbol, qty, stock.getPrice());

            System.out.println("Stock Purchased Successfully.");

        } else {

            System.out.println("Insufficient Balance.");
        }
    }

    public static void sell(User user) {

        System.out.print("Enter Stock Symbol: ");
        String symbol = sc.next().toUpperCase();

        System.out.print("Quantity: ");
        int qty = sc.nextInt();

        if (!market.containsKey(symbol)) {
            System.out.println("Invalid Stock.");
            return;
        }

        if (user.getPortfolio().sellStock(symbol, qty)) {

            double amount = qty * market.get(symbol).getPrice();

            user.deposit(amount);

            System.out.println("Stock Sold Successfully.");

        } else {

            System.out.println("Not enough shares.");
        }
    }

    public static void viewPortfolio(User user) {

        System.out.println("\n------ Portfolio ------");

        double investment = 0;
        double currentValue = 0;

        for (Map.Entry<String, Integer> entry :
                user.getPortfolio().getStocks().entrySet()) {

            String symbol = entry.getKey();
            int qty = entry.getValue();

            double buyPrice =
                    user.getPortfolio().getBuyPrice(symbol);

            double currentPrice =
                    market.get(symbol).getPrice();

            investment += qty * buyPrice;
            currentValue += qty * currentPrice;

            System.out.println(symbol + " x " + qty +
                    " | Buy: $" + buyPrice +
                    " | Current: $" + currentPrice);
        }

        System.out.println("-----------------------");
        System.out.println("Investment : $" + investment);
        System.out.println("Current Value : $" + currentValue);
        System.out.println("Profit/Loss : $" +
                (currentValue - investment));

        System.out.println("Wallet Balance : $" +
                user.getBalance());
    }

    public static void main(String[] args) {

        market.put("AAPL", new Stock("AAPL", 180));
        market.put("TSLA", new Stock("TSLA", 250));
        market.put("GOOG", new Stock("GOOG", 130));
        market.put("MSFT", new Stock("MSFT", 310));

        User user = new User("Investor", 10000);

        while (true) {

            System.out.println("\n===== Stock Trading Platform =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Wallet Balance");
            System.out.println("6. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayMarket();
                    break;

                case 2:
                    buy(user);
                    break;

                case 3:
                    sell(user);
                    break;

                case 4:
                    viewPortfolio(user);
                    break;

                case 5:
                    System.out.println("Wallet Balance: $" +
                            user.getBalance());
                    break;

                case 6:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}
