public class WebApp implements Observer {

    @Override
    public void update(String stockName, double price) {
        System.out.println("Web App Notification: " + stockName + " price changed to Rs." + price);
    }
}