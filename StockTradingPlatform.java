import java.util.*;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

class Transaction {
    private String type;
    private String stockName;
    private int quantity;
    private double amount;

    public Transaction(String type, String stockName, int quantity, double amount) {
        this.type = type;
        this.stockName = stockName;
        this.quantity = quantity;
        this.amount = amount;
    }

    public void display() {
        System.out.println(type + " | Stock: " + stockName +
                " | Qty: " + quantity +
                " | Amount: ₹" + amount);
    }
}

class User {
    private String name;
    private double balance;
    private HashMap<String, Integer> portfolio;
    private ArrayList<Transaction> transactions;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        portfolio = new HashMap<>();
        transactions = new ArrayList<>();
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;

        if (cost <= balance) {
            balance -= cost;
            portfolio.put(stock.getSymbol(),
                    portfolio.getOrDefault(stock.getSymbol(), 0) + quantity);

            transactions.add(new Transaction(
                    "BUY",
                    stock.getSymbol(),
                    quantity,
                    cost));

            System.out.println("Stock Purchased Successfully!");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        int owned = portfolio.getOrDefault(stock.getSymbol(), 0);

        if (owned >= quantity) {
            double revenue = stock.getPrice() * quantity;
            balance += revenue;

            portfolio.put(stock.getSymbol(), owned - quantity);

            transactions.add(new Transaction(
                    "SELL",
                    stock.getSymbol(),
                    quantity,
                    revenue));

            System.out.println("Stock Sold Successfully!");
        } else {
            System.out.println("Not Enough Shares!");
        }
    }

    public void viewPortfolio() {
        System.out.println("\n----- Portfolio -----");
        for (String stock : portfolio.keySet()) {
            System.out.println(stock + " : " + portfolio.get(stock) + " shares");
        }
        System.out.println("Balance: ₹" + balance);
    }

    public void viewTransactions() {
        System.out.println("\n----- Transaction History -----");
        for (Transaction t : transactions) {
            t.display();
        }
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Stock tata = new Stock("TATA", 1200);
        Stock reliance = new Stock("RELIANCE", 2500);
        Stock infosys = new Stock("INFOSYS", 1800);

        User user = new User("Akash", 100000);

        while (true) {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nMarket Data:");
                    System.out.println("TATA      : ₹" + tata.getPrice());
                    System.out.println("RELIANCE  : ₹" + reliance.getPrice());
                    System.out.println("INFOSYS   : ₹" + infosys.getPrice());
                    break;

                case 2:
                    System.out.print("Enter Stock (TATA/RELIANCE/INFOSYS): ");
                    String buyStock = sc.next().toUpperCase();

                    System.out.print("Quantity: ");
                    int buyQty = sc.nextInt();

                    if (buyStock.equals("TATA"))
                        user.buyStock(tata, buyQty);
                    else if (buyStock.equals("RELIANCE"))
                        user.buyStock(reliance, buyQty);
                    else if (buyStock.equals("INFOSYS"))
                        user.buyStock(infosys, buyQty);
                    else
                        System.out.println("Invalid Stock!");
                    break;

                case 3:
                    System.out.print("Enter Stock (TATA/RELIANCE/INFOSYS): ");
                    String sellStock = sc.next().toUpperCase();

                    System.out.print("Quantity: ");
                    int sellQty = sc.nextInt();

                    if (sellStock.equals("TATA"))
                        user.sellStock(tata, sellQty);
                    else if (sellStock.equals("RELIANCE"))
                        user.sellStock(reliance, sellQty);
                    else if (sellStock.equals("INFOSYS"))
                        user.sellStock(infosys, sellQty);
                    else
                        System.out.println("Invalid Stock!");
                    break;

                case 4:
                    user.viewPortfolio();
                    break;

                case 5:
                    user.viewTransactions();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}