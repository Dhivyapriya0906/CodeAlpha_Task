import java.util.*;

class Stock {
    String symbol;
    String name;
    double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public void updatePrice() {
        double changePercent = (Math.random() * 10) - 5; // -5% to +5%
        price += (price * changePercent / 100);
        price = Math.round(price * 100.0) / 100.0;
    }

    public String toString() {
        return symbol + " - " + name + " : ₹" + price;
    }
}

class Transaction {
    String stockSymbol;
    int quantity;
    double priceAtPurchase;
    String type; // "BUY" or "SELL"
    Date dateTime;

    public Transaction(String symbol, int qty, double price, String type) {
        this.stockSymbol = symbol;
        this.quantity = qty;
        this.priceAtPurchase = price;
        this.type = type;
        this.dateTime = new Date();
    }

    public String toString() {
        return type + " " + quantity + " x " + stockSymbol + " @ ₹" + priceAtPurchase + " on " + dateTime;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    List<Transaction> history = new ArrayList<>();

    public void buyStock(Stock stock, int quantity) {
        holdings.put(stock.symbol, holdings.getOrDefault(stock.symbol, 0) + quantity);
        history.add(new Transaction(stock.symbol, quantity, stock.price, "BUY"));
    }

    public void sellStock(Stock stock, int quantity) {
        if (holdings.containsKey(stock.symbol) && holdings.get(stock.symbol) >= quantity) {
            holdings.put(stock.symbol, holdings.get(stock.symbol) - quantity);
            if (holdings.get(stock.symbol) == 0) {
                holdings.remove(stock.symbol);
            }
            history.add(new Transaction(stock.symbol, quantity, stock.price, "SELL"));
        } else {
            System.out.println("Not enough shares to sell.");
        }
    }

    public void viewPortfolio(Map<String, Stock> market) {
        System.out.println("\nYour Portfolio:");
        double total = 0.0;
        for (String symbol : holdings.keySet()) {
            int qty = holdings.get(symbol);
            double currentPrice = market.get(symbol).price;
            double value = qty * currentPrice;
            System.out.println(symbol + " - " + qty + " shares @ ₹" + currentPrice + " = ₹" + value);
            total += value;
        }
        System.out.println("Total Portfolio Value: ₹" + Math.round(total * 100.0) / 100.0);
    }

    public void viewHistory() {
        System.out.println("\nTransaction History:");
        for (Transaction t : history) {
            System.out.println(t);
        }
    }
}

class Market {
    Map<String, Stock> stockList = new HashMap<>();

    public Market() {
        stockList.put("TCS", new Stock("TCS", "Tata Consultancy Services", 3450));
        stockList.put("INFY", new Stock("INFY", "Infosys", 1520));
        stockList.put("RELIANCE", new Stock("RELIANCE", "Reliance Industries", 2745));
        stockList.put("HDFC", new Stock("HDFC", "HDFC Bank", 1600));
    }

    public void showMarket() {
        System.out.println("\nMarket Stocks:");
        for (Stock stock : stockList.values()) {
            System.out.println(stock);
        }
    }

    public void updatePrices() {
        for (Stock stock : stockList.values()) {
            stock.updatePrice();
        }
    }
}

public class task3 {
    static Scanner sc = new Scanner(System.in);
    static double userBalance = 100000.0;

    public static void main(String[] args) {
        Market market = new Market();
        Portfolio portfolio = new Portfolio();

        System.out.println("Welcome to the Stock Trading Platform");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Hello, " + name + "! Your starting balance: ₹" + userBalance);

        while (true) {
            market.updatePrices();
            System.out.println("\n===== MENU =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    market.showMarket();
                    break;

                case 2:
                    market.showMarket();
                    System.out.print("Enter Stock Symbol to BUY: ");
                    String buySymbol = sc.next().toUpperCase();
                    if (!market.stockList.containsKey(buySymbol)) {
                        System.out.println("Invalid Stock Symbol.");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int qtyBuy = sc.nextInt();
                    Stock buyStock = market.stockList.get(buySymbol);
                    double cost = qtyBuy * buyStock.price;
                    if (userBalance >= cost) {
                        portfolio.buyStock(buyStock, qtyBuy);
                        userBalance -= cost;
                        System.out.println("Bought " + qtyBuy + " x " + buySymbol + ". New Balance: ₹" + userBalance);
                    } else {
                        System.out.println("Not enough balance.");
                    }
                    break;

                case 3:
                    portfolio.viewPortfolio(market.stockList);
                    System.out.print("Enter Stock Symbol to SELL: ");
                    String sellSymbol = sc.next().toUpperCase();
                    if (!portfolio.holdings.containsKey(sellSymbol)) {
                        System.out.println("You do not own this stock.");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int qtySell = sc.nextInt();
                    Stock sellStock = market.stockList.get(sellSymbol);
                    if (portfolio.holdings.get(sellSymbol) >= qtySell) {
                        portfolio.sellStock(sellStock, qtySell);
                        double sale = qtySell * sellStock.price;
                        userBalance += sale;
                        System.out.println("Sold " + qtySell + " x " + sellSymbol + ". New Balance: ₹" + userBalance);
                    } else {
                        System.out.println("Not enough shares.");
                    }
                    break;

                case 4:
                    portfolio.viewPortfolio(market.stockList);
                    break;

                case 5:
                    portfolio.viewHistory();
                    break;

                case 6:
                    System.out.println("Thank you for using the platform, " + name + "!");
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}