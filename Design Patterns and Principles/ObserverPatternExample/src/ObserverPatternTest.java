public class ObserverPatternTest {

    public static void main(String[] args) {

        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        stockMarket.setStockPrice("TCS", 3850.50);

        System.out.println();

        stockMarket.deregisterObserver(webApp);

        stockMarket.setStockPrice("INFY", 1480.75);
    }
}