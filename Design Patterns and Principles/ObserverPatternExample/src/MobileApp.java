public class MobileApp implements Observer {

    @Override
    public void update(String stockName, double price) {
        System.out.println("Mobile App Notification: " + stockName + " price changed to Rs." + price);
    }
}